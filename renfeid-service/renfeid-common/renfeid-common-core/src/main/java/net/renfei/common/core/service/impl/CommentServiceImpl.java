/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.common.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.api.utils.StringUtils;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.api.constant.enums.SystemSettingEnum;
import net.renfei.common.core.entity.Comment;
import net.renfei.common.core.entity.CommentTree;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.repositories.CoreCommentsMapper;
import net.renfei.common.core.repositories.entity.CoreCommentsExample;
import net.renfei.common.core.repositories.entity.CoreCommentsWithBLOBs;
import net.renfei.common.core.service.*;
import net.renfei.common.core.utils.IpUtils;
import net.renfei.uaa.api.entity.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static net.renfei.common.core.config.RedisConfig.REDIS_KEY_DATABASE;
import static net.renfei.common.core.config.SystemConfig.*;

/**
 * 评论服务
 *
 * @author renfei
 */
@Service
public class CommentServiceImpl implements CommentService {
    private final static Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final static String REDIS_KEY = REDIS_KEY_DATABASE + ":comment:";
    private final RedisService redisService;
    private final AuditService auditService;
    private final SystemConfig systemConfig;
    private final SystemService systemService;
    private final SnowflakeService snowflakeService;
    private final IP2LocationService ip2LocationService;
    private final CoreCommentsMapper coreCommentsMapper;

    public CommentServiceImpl(RedisService redisService,
                              AuditService auditService,
                              SystemConfig systemConfig,
                              SystemService systemService,
                              SnowflakeService snowflakeService,
                              IP2LocationService ip2LocationService,
                              CoreCommentsMapper coreCommentsMapper) {
        this.redisService = redisService;
        this.auditService = auditService;
        this.systemConfig = systemConfig;
        this.systemService = systemService;
        this.snowflakeService = snowflakeService;
        this.ip2LocationService = ip2LocationService;
        this.coreCommentsMapper = coreCommentsMapper;
    }

    @Override
    public APIResult submitComment(Comment comment, boolean isOwner, HttpServletRequest request) {
        // 检查全局评论开关
        List<String> strings = systemService.querySystemSetting(SystemSettingEnum.GLOBAL_COMMENT_STATUS);
        if (strings.isEmpty() || !"OPENED".equals(strings.get(0))) {
            logger.warn("当前评论系统未开启，评论请求被拒绝。");
            throw new BusinessException("当前评论系统未开启，评论请求被拒绝");
        }
        // TODO 检查被评论的对象是否允许评论
        if (ObjectUtils.isEmpty(comment.getObjectId())) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("objectId 不能为空")
                    .build();
        }
        if (ObjectUtils.isEmpty(comment.getContent())) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("评论内容不能为空")
                    .build();
        }
        if (comment.getSysType() == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("SysType 不能为空")
                    .build();
        }
        if (comment.getContent() != null && comment.getContent().length() >= MAX_COMMENT_LENGTH) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("评论内容长度超过系统允许最大值：" + MAX_COMMENT_LENGTH)
                    .build();
        }
        if (comment.getAuthorEmail() != null && comment.getAuthorEmail().length() >= MAX_USERNAME_LENGTH) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("邮箱地址长度超过系统允许最大值：" + MAX_USERNAME_LENGTH)
                    .build();
        }
        if (comment.getAuthor() != null && comment.getAuthor().length() >= MAX_USERNAME_LENGTH) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("作者昵称长度超过系统允许最大值：" + MAX_USERNAME_LENGTH)
                    .build();
        }
        if (comment.getAuthorUrl() != null && comment.getAuthorUrl().length() >= MAX_LINK_LENGTH) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("网站链接长度超过系统允许最大值：" + MAX_LINK_LENGTH)
                    .build();
        }
        UserDetail currentUserDetail = systemService.currentUserDetail();
        if (currentUserDetail != null) {
            comment.setAuthorId(currentUserDetail.getId());
            comment.setAuthor(currentUserDetail.getUsername());
            comment.setAuthorEmail(currentUserDetail.getEmail());
        }
        if (ObjectUtils.isEmpty(comment.getAuthor())) {
            throw new BusinessException("评论昵称不能为空");
        }
        if (ObjectUtils.isEmpty(comment.getAuthorEmail())) {
            throw new BusinessException("电子邮箱不能为空");
        }
        if (!StringUtils.isEmail(comment.getAuthorEmail())) {
            throw new BusinessException("电子邮箱格式不正确");
        }
        comment.setId(snowflakeService.getId("").getId() + "");
        comment.setIsOwner(isOwner);
        comment.setAddtime(new Date());
        // 审核通过后恢复
        comment.setIsDelete(systemConfig.getEnableAudit() && !isOwner);
        comment.setAuthorIp(IpUtils.getIpAddress(request));
        try {
            comment.setAuthorAddress(ip2LocationService.ipQueryAddress(IpUtils.getIpAddress(request)));
        } catch (Exception e) {
            comment.setAuthorAddress("Unknown");
            logger.error(e.getMessage(), e);
        }
        CoreCommentsWithBLOBs coreComments = convert(comment);
        coreCommentsMapper.insert(coreComments);
        String redisKey = REDIS_KEY + coreComments.getSysType() + ":" + coreComments.getObjectId();
        if (systemConfig.getEnableAudit() && !isOwner) {

            if (systemConfig.getEnableCache()) {
                // 异步调用审核和通知机制
                auditService.auditComment(Long.parseLong(comment.getId()), redisService, redisKey);
            } else {
                auditService.auditComment(Long.parseLong(comment.getId()), redisService, null);
            }
        } else {
            auditService.sendNotify(coreComments, null);
            if (systemConfig.getEnableCache()) {
                redisService.del(redisKey);
            }
        }
        return APIResult.success();
    }

    @Override
    public APIResult<ListData<Comment>> queryCommentList(boolean haveDelete, int pages, int rows) {
        CoreCommentsExample example = new CoreCommentsExample();
        example.setOrderByClause("addtime DESC");
        CoreCommentsExample.Criteria criteria = example.createCriteria();
        if (!haveDelete) {
            criteria.andIsDeleteEqualTo(false);
        }
        ListData<Comment> commentListData = new ListData<>();
        try (Page<CoreCommentsWithBLOBs> page = PageHelper.startPage(pages, rows)) {
            coreCommentsMapper.selectByExampleWithBLOBs(example);
            commentListData.setPageNum(page.getPageNum());
            commentListData.setPageSize(page.getPageSize());
            commentListData.setStartRow(page.getStartRow());
            commentListData.setEndRow(page.getEndRow());
            commentListData.setTotal(page.getTotal());
            commentListData.setPages(page.getPages());
            List<Comment> comments = new ArrayList<>();
            for (CoreCommentsWithBLOBs coreComment : page.getResult()
            ) {
                comments.add(convert(coreComment));
            }
            commentListData.setData(comments);
        }
        return new APIResult<>(commentListData);
    }

    @Override
    public APIResult replyComment(long commentId, String content, HttpServletRequest request) {
        CoreCommentsWithBLOBs coreComment = coreCommentsMapper.selectByPrimaryKey(commentId);
        if (coreComment == null) {
            throw new BusinessException("回复的目标评论数据不存在");
        }
        Comment comment = new Comment();
        comment.setObjectId(coreComment.getObjectId() + "");
        comment.setSysType(SystemTypeEnum.valueOf(coreComment.getSysType()));
        comment.setParentId(commentId + "");
        comment.setAuthorUrl(systemConfig.getSiteDomainName());
        comment.setContent(content);
        return this.submitComment(comment, true, request);
    }

    @Override
    public APIResult deleteComment(long commentId) {
        CoreCommentsWithBLOBs coreComment = coreCommentsMapper.selectByPrimaryKey(commentId);
        if (coreComment == null) {
            throw new BusinessException("删除的目标评论数据不存在");
        }
        coreComment.setIsDelete(true);
        coreCommentsMapper.updateByPrimaryKeySelective(coreComment);
        if (systemConfig.getEnableCache()) {
            String redisKey = REDIS_KEY + coreComment.getSysType() + ":" + coreComment.getObjectId();
            redisService.del(redisKey);
        }
        return APIResult.success();
    }

    @Override
    public List<CommentTree> queryCommentTree(SystemTypeEnum sysType, long objectId, CommentTree reply) {
        List<CommentTree> commentTreeList = new ArrayList<>();
        String redisKey = REDIS_KEY + sysType.toString() + ":" + objectId;
        if (reply != null) {
            redisKey += ":" + reply.getId();
        }
        if (systemConfig.getEnableCache()) {
            // 启用了缓存
            if (redisService.hasKey(redisKey)) {
                Object object = redisService.get(redisKey);
                if (object instanceof List) {
                    commentTreeList = (List<CommentTree>) object;
                }
            }
        }
        if (commentTreeList == null || commentTreeList.isEmpty()) {
            CoreCommentsExample example = new CoreCommentsExample();
            example.setOrderByClause("addtime DESC");
            CoreCommentsExample.Criteria criteria = example.createCriteria();
            criteria.andIsDeleteEqualTo(false)
                    .andSysTypeEqualTo(sysType.name())
                    .andObjectIdEqualTo(objectId);
            if (reply == null) {
                criteria.andParentIdIsNull();
            } else {
                criteria.andParentIdEqualTo(Long.parseLong(reply.getId()));
            }
            List<CoreCommentsWithBLOBs> coreComments = coreCommentsMapper.selectByExampleWithBLOBs(example);
            if (!ObjectUtils.isEmpty(coreComments)) {
                for (CoreCommentsWithBLOBs coreComment : coreComments
                ) {
                    CommentTree commentTree = convertTree(coreComment);
                    commentTree.setChildren(queryCommentTree(sysType, objectId, commentTree));
                    commentTreeList.add(commentTree);
                }
                redisService.set(redisKey, commentTreeList);
            }
        }
        return commentTreeList;
    }

    private CoreCommentsWithBLOBs convert(Comment comment) {
        CoreCommentsWithBLOBs coreComments = new CoreCommentsWithBLOBs();
        coreComments.setId(ObjectUtils.isEmpty(comment.getId()) ? null : Long.parseLong(comment.getId()));
        coreComments.setSysType(comment.getSysType().toString());
        coreComments.setObjectId(ObjectUtils.isEmpty(comment.getObjectId()) ? null : Long.parseLong(comment.getObjectId()));
        coreComments.setAuthorId(ObjectUtils.isEmpty(comment.getAuthorId()) ? null : Long.parseLong(comment.getAuthorId()));
        coreComments.setAddtime(comment.getAddtime());
        coreComments.setIsDelete(comment.getIsDelete());
        coreComments.setParentId(ObjectUtils.isEmpty(comment.getParentId()) ? null : Long.parseLong(comment.getParentId()));
        coreComments.setIsOwner(comment.getIsOwner());
        coreComments.setAuthor(comment.getAuthor());
        coreComments.setAuthorEmail(comment.getAuthorEmail());
        coreComments.setAuthorUrl(comment.getAuthorUrl());
        coreComments.setAuthorIp(comment.getAuthorIp());
        coreComments.setAuthorAddress(comment.getAuthorAddress());
        coreComments.setContent(comment.getContent());
        return coreComments;
    }

    private Comment convert(CoreCommentsWithBLOBs coreComments) {
        Comment comment = new Comment();
        comment.setId(coreComments.getId() + "");
        comment.setSysType(SystemTypeEnum.valueOf(coreComments.getSysType()));
        comment.setObjectId(coreComments.getObjectId() == null ? null : coreComments.getObjectId() + "");
        comment.setAuthorId(coreComments.getAuthorId() == null ? null : coreComments.getAuthorId() + "");
        comment.setAddtime(coreComments.getAddtime());
        comment.setIsDelete(coreComments.getIsDelete());
        comment.setParentId(coreComments.getParentId() == null ? null : coreComments.getParentId() + "");
        comment.setIsOwner(coreComments.getIsOwner());
        comment.setAuthor(coreComments.getAuthor());
        comment.setAuthorEmail(coreComments.getAuthorEmail());
        comment.setAuthorUrl(coreComments.getAuthorUrl());
        comment.setAuthorIp(coreComments.getAuthorIp());
        comment.setAuthorAddress(coreComments.getAuthorAddress());
        comment.setContent(coreComments.getContent());
        return comment;
    }

    private CommentTree convertTree(CoreCommentsWithBLOBs coreComments) {
        CommentTree commentTree = new CommentTree();
        commentTree.setId(coreComments.getId() + "");
        commentTree.setAddtime(coreComments.getAddtime());
        commentTree.setIsOwner(coreComments.getIsOwner());
        commentTree.setAuthor(coreComments.getAuthor());
        commentTree.setAuthorUrl(coreComments.getAuthorUrl());
        commentTree.setAuthorAddress(coreComments.getAuthorAddress());
        commentTree.setContent(coreComments.getContent());
        return commentTree;
    }
}
