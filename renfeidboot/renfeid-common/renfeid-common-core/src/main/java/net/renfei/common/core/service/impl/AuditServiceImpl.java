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

import net.renfei.common.api.utils.JacksonUtil;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.repositories.CoreCommentsMapper;
import net.renfei.common.core.repositories.entity.CoreCommentsWithBLOBs;
import net.renfei.common.core.service.AuditService;
import net.renfei.common.core.service.EmailService;
import net.renfei.common.core.service.aliyun.AliyunService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author renfei
 */
@Service
public class AuditServiceImpl implements AuditService {
    private final static Logger logger = LoggerFactory.getLogger(AuditServiceImpl.class);
    private final EmailService emailService;
    private final SystemConfig systemConfig;
    private final AliyunService aliyunService;
    private final CoreCommentsMapper coreCommentsMapper;

    public AuditServiceImpl(EmailService emailService,
                            SystemConfig systemConfig,
                            AliyunService aliyunService,
                            CoreCommentsMapper coreCommentsMapper) {
        this.emailService = emailService;
        this.systemConfig = systemConfig;
        this.aliyunService = aliyunService;
        this.coreCommentsMapper = coreCommentsMapper;
    }

    @Async
    @Override
    public void auditComment(long commentId) {
        CoreCommentsWithBLOBs comment = coreCommentsMapper.selectByPrimaryKey(commentId);
        // 检查评论内容，包括昵称和内容
        if (aliyunService.textScan(comment.getContent())
                && (comment.getAuthor() != null && aliyunService.textScan(comment.getAuthor()))) {
            comment.setIsDelete(false);
            coreCommentsMapper.updateByPrimaryKeyWithBLOBs(comment);
            sendNotify(comment, null);
        } else {
            // 发送通知
            List<String> content = new ArrayList<>();
            content.add("很高兴能与你取得联系。您的评论留言收到了新的动态：");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            content.add("在 " + sdf.format(comment.getAddtime()) + " 时您写到：");
            content.add(comment.getContent());
            content.add("----");
            content.add("很遗憾，评论内容未能通过机器自动审查，内容被隐藏，如果您对此有异议，请回复邮件，我们将为您人工处理。");
            sendNotify(comment, content);
            // 您提交的内容可能包含不健康或不和谐的内容，未能通过机器自动审查，请重试。
            logger.warn("评论未能通过机器自动审查。内容为：{}", JacksonUtil.obj2String(comment));
        }
    }

    @Async
    @Override
    public void sendNotify(CoreCommentsWithBLOBs comment, List<String> content) {
        String sent = "", sentName = "";
        if (content != null && !content.isEmpty()) {
            sent = comment.getAuthorEmail();
            sentName = comment.getAuthor();
        } else {
            content = new ArrayList<>();
            content.add("很高兴能与你取得联系。您的评论留言收到了新的动态：");
            content.add(comment.getContent());
            content.add("----");
            if (comment.getParentId() == null || comment.getParentId() == 0) {
                sent = "i@renfei.net";
                sentName = "RenFei";
            } else {
                CoreCommentsWithBLOBs parentComment = coreCommentsMapper.selectByPrimaryKey(comment.getParentId());
                if (parentComment != null) {
                    sent = parentComment.getAuthorEmail();
                    sentName = parentComment.getAuthor();
                    String link = systemConfig.getSiteDomainName();
                    SystemTypeEnum systemTypeEnum = SystemTypeEnum.valueOf(parentComment.getSysType());
                    link += systemTypeEnum.getUriPath() + "/" + comment.getObjectId();
                    switch (systemTypeEnum) {
                        case POSTS:
                            try {
                                content.add("回顾：<a href=\"" + link + "\">点此链接回到当时的文章</a>");
                            } catch (Exception ignored) {
                            }
                            break;
                        default:
                            return;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    content.add("在 " + sdf.format(parentComment.getAddtime()) + " 时您写到：");
                    content.add(parentComment.getContent());
                    content.add("Visit Topic to respond.");
                } else {
                    logger.error("根据评论[{}]的ParentId[{}]，未能找到父级评论，通知被取消。", comment.getId(), comment.getParentId());
                    return;
                }
            }
        }
        emailService.send(sent, sentName, "您在[RENFEI.NET]的评论留言有新的动态", content);
    }
}
