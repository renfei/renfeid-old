package net.renfei.services.discuz;

import net.renfei.discuz.repositories.*;
import net.renfei.discuz.repositories.entity.*;
import net.renfei.model.discuz.DiscuzInfo;
import net.renfei.services.BaseService;
import net.renfei.services.DiscuzService;
import net.renfei.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author renfei
 */
@Service
public class DiscuzServiceImpl extends BaseService implements DiscuzService {
    private static final Logger logger = LoggerFactory.getLogger(DiscuzServiceImpl.class);
    private final DiscuzCommonMemberDOMapper memberMapper;
    private final DiscuzUcenterMembersDOMapper membersMapper;
    private final DiscuzCommonUsergroupDOMapper userGroupMapper;
    private final DiscuzForumPostDOMapper discuzForumPostMapper;
    private final DiscuzCommonMemberCountDOMapper memberCountMapper;

    public DiscuzServiceImpl(DiscuzCommonMemberDOMapper memberMapper,
                             DiscuzUcenterMembersDOMapper membersMapper,
                             DiscuzCommonUsergroupDOMapper userGroupMapper,
                             DiscuzForumPostDOMapper discuzForumPostMapper,
                             DiscuzCommonMemberCountDOMapper memberCountMapper) {
        this.memberMapper = memberMapper;
        this.membersMapper = membersMapper;
        this.userGroupMapper = userGroupMapper;
        this.discuzForumPostMapper = discuzForumPostMapper;
        this.memberCountMapper = memberCountMapper;
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
}
