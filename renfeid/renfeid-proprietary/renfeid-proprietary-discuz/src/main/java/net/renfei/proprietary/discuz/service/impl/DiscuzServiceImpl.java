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
package net.renfei.proprietary.discuz.service.impl;

import net.renfei.common.api.utils.ListUtils;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.utils.DateUtils;
import net.renfei.common.core.utils.IpUtils;
import net.renfei.discuz.ucenter.client.Client;
import net.renfei.proprietary.discuz.model.DiscuzInfo;
import net.renfei.proprietary.discuz.repositories.*;
import net.renfei.proprietary.discuz.repositories.entity.*;
import net.renfei.proprietary.discuz.service.DiscuzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @author renfei
 */
@Service
public class DiscuzServiceImpl implements DiscuzService {
    private static final Logger logger = LoggerFactory.getLogger(DiscuzServiceImpl.class);
    private final SystemConfig systemConfig;
    private final DiscuzCommonMemberDOMapper memberMapper;
    private final DiscuzUcenterMembersDOMapper membersMapper;
    private final DiscuzCommonUsergroupDOMapper userGroupMapper;
    private final DiscuzForumPostDOMapper discuzForumPostMapper;
    private final DiscuzCommonMemberCountDOMapper memberCountMapper;
    private final DiscuzCommonMemberStatusDOMapper memberStatusMapper;
    private final DiscuzCommonMemberProfileDOMapper memberProfileMapper;
    private final DiscuzCommonMemberFieldHomeDOMapper memberFieldHomeMapper;
    private final DiscuzCommonMemberFieldForumDOMapper memberFieldForumMapper;

    public DiscuzServiceImpl(SystemConfig systemConfig,
                             DiscuzCommonMemberDOMapper memberMapper,
                             DiscuzUcenterMembersDOMapper membersMapper,
                             DiscuzCommonUsergroupDOMapper userGroupMapper,
                             DiscuzForumPostDOMapper discuzForumPostMapper,
                             DiscuzCommonMemberCountDOMapper memberCountMapper,
                             DiscuzCommonMemberStatusDOMapper memberStatusMapper,
                             DiscuzCommonMemberProfileDOMapper memberProfileMapper,
                             DiscuzCommonMemberFieldHomeDOMapper memberFieldHomeMapper,
                             DiscuzCommonMemberFieldForumDOMapper memberFieldForumMapper) {
        this.systemConfig = systemConfig;
        this.memberMapper = memberMapper;
        this.membersMapper = membersMapper;
        this.userGroupMapper = userGroupMapper;
        this.discuzForumPostMapper = discuzForumPostMapper;
        this.memberCountMapper = memberCountMapper;
        this.memberStatusMapper = memberStatusMapper;
        this.memberProfileMapper = memberProfileMapper;
        this.memberFieldHomeMapper = memberFieldHomeMapper;
        this.memberFieldForumMapper = memberFieldForumMapper;
    }

    @Override
    public DiscuzInfo getDiscuzInfo(String userName) {
        DiscuzCommonMemberDOExample memberExample = new DiscuzCommonMemberDOExample();
        DiscuzUcenterMembersDOExample membersExample = new DiscuzUcenterMembersDOExample();
        DiscuzCommonUsergroupDOExample userGroupExample = new DiscuzCommonUsergroupDOExample();
        DiscuzCommonMemberCountDOExample memberCountExample = new DiscuzCommonMemberCountDOExample();
        membersExample.createCriteria().andUsernameEqualTo(userName);
        DiscuzInfo discuzInfo = new DiscuzInfo();
        try {
            DiscuzUcenterMembersDO members = ListUtils.getOne(membersMapper.selectByExample(membersExample));
            if (members != null) {
                int uid = members.getUid();
                memberExample.createCriteria().andUidEqualTo(uid);
                DiscuzCommonMemberDO member = ListUtils.getOne(memberMapper.selectByExample(memberExample));
                if (member != null) {
                    discuzInfo.setPoints(member.getCredits());
                    userGroupExample.createCriteria().andGroupidEqualTo(member.getGroupid());
                    DiscuzCommonUsergroupDO userGroup = ListUtils.getOne(userGroupMapper.selectByExample(userGroupExample));
                    if (userGroup != null) {
                        discuzInfo.setUserGroup(userGroup.getGrouptitle());
                    } else {
                        logger.error("未能查询到用户【{}】的 DiscuzCommonUsergroup 信息", userName);
                        discuzInfo.setPoints(member.getCredits());
                    }
                } else {
                    logger.error("未能查询到用户【{}】的 DiscuzCommonMember 信息", userName);
                    discuzInfo.setPoints(0);
                }
                memberCountExample.createCriteria().andUidEqualTo(uid);
                DiscuzCommonMemberCountDO memberCount = ListUtils.getOne(memberCountMapper.selectByExample(memberCountExample));
                if (memberCount != null) {
                    discuzInfo.setPostsCount(memberCount.getPosts());
                    discuzInfo.setOltime(memberCount.getOltime());
                    discuzInfo.setPrestige(memberCount.getExtcredits1());
                    discuzInfo.setMoney(memberCount.getExtcredits2());
                    discuzInfo.setContribution(memberCount.getExtcredits3());
                    discuzInfo.setEssenceCount(memberCount.getDigestposts());
                } else {
                    logger.error("未能查询到用户【{}】的 DiscuzCommonMemberCount 信息", userName);
                    discuzInfo.setPostsCount(0);
                    discuzInfo.setOltime((short) 0);
                    discuzInfo.setPrestige(0);
                    discuzInfo.setMoney(0);
                    discuzInfo.setContribution(0);
                    discuzInfo.setEssenceCount((short) 0);
                }
            } else {
                logger.error("未能查询到用户【{}】的 DiscuzUcenterMembers 信息", userName);
                discuzInfo.setPoints(0);
                discuzInfo.setUserGroup("-");
                discuzInfo.setPostsCount(0);
                discuzInfo.setOltime((short) 0);
                discuzInfo.setPrestige(0);
                discuzInfo.setMoney(0);
                discuzInfo.setContribution(0);
                discuzInfo.setEssenceCount((short) 0);
            }
            return discuzInfo;
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            return null;
        }
    }

    @Override
    public List<DiscuzForumPostDO> getAllPost() {
        DiscuzForumPostDOExample example = new DiscuzForumPostDOExample();
        example.createCriteria()
                .andFirstEqualTo(1)
                .andInvisibleEqualTo(0)
                .andStatusGreaterThanOrEqualTo(0);
        try {
            return discuzForumPostMapper.selectByExampleWithBLOBs(example);
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            return null;
        }
    }

    @Override
    public String uCenterSynLogin(String username) {
        DiscuzUcenterMembersDOExample discuzUcenterMembersExample = new DiscuzUcenterMembersDOExample();
        discuzUcenterMembersExample.createCriteria().andUsernameEqualTo(username);
        DiscuzUcenterMembersDO discuzUcenterMembers = ListUtils.getOne(membersMapper.selectByExample(discuzUcenterMembersExample));
        if (discuzUcenterMembers != null) {
            try {
                assert systemConfig != null;
                Client client =
                        new Client(systemConfig.getUCenter().getApi(),
                                null,
                                systemConfig.getUCenter().getKey(),
                                systemConfig.getUCenter().getAppId(),
                                systemConfig.getUCenter().getConnect());
                String script = client.ucUserSynlogin(discuzUcenterMembers.getUid());
                logger.info("uc script:{}", script);
                if (!ObjectUtils.isEmpty(script)) {
                    String[] strings = script.split("src=\"");
                    String script2 = "";
                    if (strings.length == 3) {
                        script2 += strings[1].replace("\" reload=\"1\"></script><script type=\"text/javascript\" ", "");
                        script2 += "|";
                        script2 += strings[2].replace("\" reload=\"1\"></script>", "");
                    } else if (strings.length == 2) {
                        script2 += strings[1].replace("\" reload=\"1\"></script>", "");
                    } else {
                        logger.warn("strings.length != 3,script:{}", script);
                    }
                    // 将http转为https
                    script = script2.replace("http://", "https://");
                    return script;
                } else {
                    logger.warn("根据UserName：{}，论坛登录脚本为空。", username);
                }
            } catch (Exception exception) {
                logger.error(exception.getMessage(), exception);
            }
        } else {
            logger.warn("根据UserName：{}，未找到论坛用户，所以没有论坛登录脚本。", username);
        }
        return null;
    }

    @Override
    public void uCenterSynSignUp(String username, String email, HttpServletRequest request) {
        try {
            Client client =
                    new Client(systemConfig.getUCenter().getApi(),
                            null,
                            systemConfig.getUCenter().getKey(),
                            systemConfig.getUCenter().getAppId(),
                            systemConfig.getUCenter().getConnect());
            client.ucUserRegister(username, UUID.randomUUID().toString(), email);
            // 向Discuz表里插入用户
            DiscuzUcenterMembersDOExample discuzUcenterMembersExample = new DiscuzUcenterMembersDOExample();
            discuzUcenterMembersExample.createCriteria().andUsernameEqualTo(username);
            DiscuzUcenterMembersDO discuzUcenterMembers = ListUtils.getOne(membersMapper.selectByExample(discuzUcenterMembersExample));
            if (discuzUcenterMembers != null) {
                DiscuzCommonMemberDO commonMemberDO = new DiscuzCommonMemberDO();
                commonMemberDO.setUid(discuzUcenterMembers.getUid());
                commonMemberDO.setEmail(email.trim().toLowerCase());
                commonMemberDO.setUsername(username.trim().toLowerCase());
                commonMemberDO.setGroupid((short) 10);
                commonMemberDO.setRegdate((int) DateUtils.getUnixTimestamp());
                commonMemberDO.setTimeoffset("9999");
                commonMemberDO.setEmailstatus(1);
                memberMapper.insertSelective(commonMemberDO);
                DiscuzCommonMemberCountDO commonMemberCountDO = new DiscuzCommonMemberCountDO();
                commonMemberCountDO.setUid(discuzUcenterMembers.getUid());
                memberCountMapper.insertSelective(commonMemberCountDO);
                DiscuzCommonMemberFieldForumDOWithBLOBs commonMemberFieldForumDO = new DiscuzCommonMemberFieldForumDOWithBLOBs();
                commonMemberFieldForumDO.setUid(discuzUcenterMembers.getUid());
                commonMemberFieldForumDO.setMedals("");
                commonMemberFieldForumDO.setSightml("");
                commonMemberFieldForumDO.setGroupterms("");
                commonMemberFieldForumDO.setGroups("");
                memberFieldForumMapper.insertSelective(commonMemberFieldForumDO);
                DiscuzCommonMemberFieldHomeDOWithBLOBs commonMemberFieldHomeDO = new DiscuzCommonMemberFieldHomeDOWithBLOBs();
                commonMemberFieldHomeDO.setUid(discuzUcenterMembers.getUid());
                commonMemberFieldHomeDO.setSpacecss("");
                commonMemberFieldHomeDO.setBlockposition("");
                commonMemberFieldHomeDO.setRecentnote("");
                commonMemberFieldHomeDO.setSpacenote("");
                commonMemberFieldHomeDO.setPrivacy("");
                commonMemberFieldHomeDO.setFeedfriend("");
                commonMemberFieldHomeDO.setAcceptemail("");
                commonMemberFieldHomeDO.setMagicgift("");
                commonMemberFieldHomeDO.setStickblogs("");
                memberFieldHomeMapper.insertSelective(commonMemberFieldHomeDO);
                DiscuzCommonMemberProfileDOWithBLOBs commonMemberProfileDO = new DiscuzCommonMemberProfileDOWithBLOBs();
                commonMemberProfileDO.setUid(discuzUcenterMembers.getUid());
                commonMemberProfileDO.setBio("");
                commonMemberProfileDO.setInterest("");
                commonMemberProfileDO.setField1("");
                commonMemberProfileDO.setField2("");
                commonMemberProfileDO.setField3("");
                commonMemberProfileDO.setField4("");
                commonMemberProfileDO.setField5("");
                commonMemberProfileDO.setField6("");
                commonMemberProfileDO.setField7("");
                commonMemberProfileDO.setField8("");
                memberProfileMapper.insertSelective(commonMemberProfileDO);
                DiscuzCommonMemberStatusDO commonMemberStatusDO = new DiscuzCommonMemberStatusDO();
                commonMemberStatusDO.setUid(discuzUcenterMembers.getUid());
                commonMemberStatusDO.setRegip(IpUtils.getIpAddress(request));
                commonMemberStatusDO.setLastip(IpUtils.getIpAddress(request));
                commonMemberStatusDO.setLastvisit((int) DateUtils.getUnixTimestamp());
                commonMemberStatusDO.setLastactivity((int) DateUtils.getUnixTimestamp());
                commonMemberStatusDO.setLastsendmail(0);
                commonMemberStatusDO.setInvisible(0);
                commonMemberStatusDO.setBuyercredit((short) 0);
                commonMemberStatusDO.setSellercredit((short) 0);
                commonMemberStatusDO.setFavtimes(0);
                commonMemberStatusDO.setSharetimes(0);
                commonMemberStatusDO.setProfileprogress((byte) 0);
                memberStatusMapper.insertSelective(commonMemberStatusDO);
            }
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
        }
    }
}
