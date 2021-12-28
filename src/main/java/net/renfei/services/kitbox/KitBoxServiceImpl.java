package net.renfei.services.kitbox;

import net.renfei.domain.CommentDomain;
import net.renfei.domain.comment.Comment;
import net.renfei.domain.kitbox.KitBoxTypeEnum;
import net.renfei.domain.system.SystemTypeEnum;
import net.renfei.model.LinkTree;
import net.renfei.model.kitbox.KitBoxMenus;
import net.renfei.services.BaseService;
import net.renfei.services.KitBoxService;
import net.renfei.services.RedisService;
import net.renfei.utils.ApplicationContextUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 工具箱服务
 *
 * @author renfei
 */
@Service
public class KitBoxServiceImpl extends BaseService implements KitBoxService {
    private static final String REDIS_KEY_KITBOX = REDIS_KEY + "kitbox:";
    public static final String NETWORK_TOOL = "networkTool";
    public static final String DEVELOPMENT_TOOL = "developmentTool";
    public static final String ENCRYPTION_TOOL = "encryptionTool";
    public static final String OTHER_TOOL = "otherTool";
    private RedisService redisService;

    {
        if (SYSTEM_CONFIG.isEnableRedis()) {
            redisService = (RedisService) ApplicationContextUtil.getBean("redisServiceImpl");
        }
    }

    /**
     * 获取工具箱左侧菜单
     *
     * @return
     */
    @Override
    public List<KitBoxMenus> getKitBoxMenus() {
        String redisKey = REDIS_KEY_KITBOX + "menus";
        List<KitBoxMenus> kitBoxMenus = null;
        assert SYSTEM_CONFIG != null;
        if (SYSTEM_CONFIG.isEnableRedis()) {
            // 查询是否曾经缓存过对象，有缓存直接吐出去
            if (redisService.hasKey(redisKey)) {
                Object object = redisService.get(redisKey);
                if (object instanceof List) {
                    kitBoxMenus = (List<KitBoxMenus>) object;
                }
            }
        }
        if (kitBoxMenus == null) {
            kitBoxMenus = new CopyOnWriteArrayList<>();
            kitBoxMenus.add(KitBoxMenus.builder()
                    .title("网络工具")
                    .elementId(NETWORK_TOOL)
                    .isOpen(false)
                    .links(new ArrayList<LinkTree>() {
                        {
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.NETWORK_IP.getUrl())
                                    .rel(KitBoxTypeEnum.NETWORK_IP.getReadme())
                                    .text(KitBoxTypeEnum.NETWORK_IP.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.NETWORK_DIGTRACE.getUrl())
                                    .rel(KitBoxTypeEnum.NETWORK_DIGTRACE.getReadme())
                                    .text(KitBoxTypeEnum.NETWORK_DIGTRACE.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.NETWORK_DNSQPSE.getUrl())
                                    .rel(KitBoxTypeEnum.NETWORK_DNSQPSE.getReadme())
                                    .text(KitBoxTypeEnum.NETWORK_DNSQPSE.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.NETWORK_WHOIS.getUrl())
                                    .rel(KitBoxTypeEnum.NETWORK_WHOIS.getReadme())
                                    .text(KitBoxTypeEnum.NETWORK_WHOIS.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.NETWORK_GETMYIP.getUrl())
                                    .rel(KitBoxTypeEnum.NETWORK_GETMYIP.getReadme())
                                    .text(KitBoxTypeEnum.NETWORK_GETMYIP.getTitle())
                                    .build());
                        }
                    })
                    .build());
            kitBoxMenus.add(KitBoxMenus.builder()
                    .title("开发类工具")
                    .elementId(DEVELOPMENT_TOOL)
                    .isOpen(false)
                    .links(new ArrayList<LinkTree>() {
                        {
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.DEVELOP_UUID.getUrl())
                                    .rel(KitBoxTypeEnum.DEVELOP_UUID.getReadme())
                                    .text(KitBoxTypeEnum.DEVELOP_UUID.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.DEVELOP_FREEMARKER_TEST.getUrl())
                                    .rel(KitBoxTypeEnum.DEVELOP_FREEMARKER_TEST.getReadme())
                                    .text(KitBoxTypeEnum.DEVELOP_FREEMARKER_TEST.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.DEVELOP_STR_HUMP_LINE_CONVERT.getUrl())
                                    .rel(KitBoxTypeEnum.DEVELOP_STR_HUMP_LINE_CONVERT.getReadme())
                                    .text(KitBoxTypeEnum.DEVELOP_STR_HUMP_LINE_CONVERT.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.DEVELOP_BYTE_UNIT_CONVERSION.getUrl())
                                    .rel(KitBoxTypeEnum.DEVELOP_BYTE_UNIT_CONVERSION.getReadme())
                                    .text(KitBoxTypeEnum.DEVELOP_BYTE_UNIT_CONVERSION.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.DEVELOP_UEDITOR.getUrl())
                                    .rel(KitBoxTypeEnum.DEVELOP_UEDITOR.getReadme())
                                    .text(KitBoxTypeEnum.DEVELOP_UEDITOR.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.DEVELOP_WORD_IK_ANALYZE.getUrl())
                                    .rel(KitBoxTypeEnum.DEVELOP_WORD_IK_ANALYZE.getReadme())
                                    .text(KitBoxTypeEnum.DEVELOP_WORD_IK_ANALYZE.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.DEVELOP_PORT_NUMBER_LIST.getUrl())
                                    .rel(KitBoxTypeEnum.DEVELOP_PORT_NUMBER_LIST.getReadme())
                                    .text(KitBoxTypeEnum.DEVELOP_PORT_NUMBER_LIST.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.DEVELOP_PLIST.getUrl())
                                    .rel(KitBoxTypeEnum.DEVELOP_PLIST.getReadme())
                                    .text(KitBoxTypeEnum.DEVELOP_PLIST.getTitle())
                                    .build());
                        }
                    })
                    .build());
            kitBoxMenus.add(KitBoxMenus.builder()
                    .title("加解密工具")
                    .elementId(ENCRYPTION_TOOL)
                    .isOpen(false)
                    .links(new ArrayList<LinkTree>() {
                        {
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.ENCRYPTION_RANDOM_PASSWORD.getUrl())
                                    .rel(KitBoxTypeEnum.ENCRYPTION_RANDOM_PASSWORD.getReadme())
                                    .text(KitBoxTypeEnum.ENCRYPTION_RANDOM_PASSWORD.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.ENCRYPTION_MD5.getUrl())
                                    .rel(KitBoxTypeEnum.ENCRYPTION_MD5.getReadme())
                                    .text(KitBoxTypeEnum.ENCRYPTION_MD5.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.ENCRYPTION_SHA1.getUrl())
                                    .rel(KitBoxTypeEnum.ENCRYPTION_SHA1.getReadme())
                                    .text(KitBoxTypeEnum.ENCRYPTION_SHA1.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.ENCRYPTION_SHA256.getUrl())
                                    .rel(KitBoxTypeEnum.ENCRYPTION_SHA256.getReadme())
                                    .text(KitBoxTypeEnum.ENCRYPTION_SHA256.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.ENCRYPTION_SHA512.getUrl())
                                    .rel(KitBoxTypeEnum.ENCRYPTION_SHA512.getReadme())
                                    .text(KitBoxTypeEnum.ENCRYPTION_SHA512.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.ENCRYPTION_URL16.getUrl())
                                    .rel(KitBoxTypeEnum.ENCRYPTION_URL16.getReadme())
                                    .text(KitBoxTypeEnum.ENCRYPTION_URL16.getTitle())
                                    .build());
                        }
                    })
                    .build());
            kitBoxMenus.add(KitBoxMenus.builder()
                    .title("其他类工具")
                    .elementId(OTHER_TOOL)
                    .isOpen(false)
                    .links(new ArrayList<LinkTree>() {
                        {
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.OTHER_QRCODE.getUrl())
                                    .rel(KitBoxTypeEnum.OTHER_QRCODE.getReadme())
                                    .text(KitBoxTypeEnum.OTHER_QRCODE.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.OTHER_SHORT_URL.getUrl())
                                    .rel(KitBoxTypeEnum.OTHER_SHORT_URL.getReadme())
                                    .text(KitBoxTypeEnum.OTHER_SHORT_URL.getTitle())
                                    .build());
                            this.add(LinkTree.builder()
                                    .href(SYSTEM_CONFIG.getSiteDomainName() + KitBoxTypeEnum.OTHER_INDEXING.getUrl())
                                    .rel(KitBoxTypeEnum.OTHER_INDEXING.getReadme())
                                    .text(KitBoxTypeEnum.OTHER_INDEXING.getTitle())
                                    .build());
                        }
                    })
                    .build());
            if (SYSTEM_CONFIG.isEnableRedis()) {
                redisService.set(redisKey, kitBoxMenus, SYSTEM_CONFIG.getDefaultCacheSeconds());
            }
        }
        return kitBoxMenus;
    }

    /**
     * 获取工具箱左侧菜单，包含展开状态
     *
     * @param key 应当展开的菜单ID
     * @return
     */
    @Override
    public List<KitBoxMenus> getKitBoxMenus(String key) {
        List<KitBoxMenus> kitBoxMenus = getKitBoxMenus();
        for (KitBoxMenus kitBoxMenu : kitBoxMenus
        ) {
            if (kitBoxMenu.getElementId().equals(key)) {
                kitBoxMenu.setIsOpen(true);
                break;
            }
        }
        return kitBoxMenus;
    }

    /**
     * 获取工具的评论
     *
     * @param kitBoxTypeEnum 菜单类型
     * @return
     */
    @Override
    public List<Comment> getCommentList(KitBoxTypeEnum kitBoxTypeEnum) {
        String redisKey = REDIS_KEY_KITBOX + "comment:" + kitBoxTypeEnum.toString();
        List<Comment> commentList = null;
        assert SYSTEM_CONFIG != null;
        if (SYSTEM_CONFIG.isEnableRedis()) {
            // 查询是否曾经缓存过对象，有缓存直接吐出去
            if (redisService.hasKey(redisKey)) {
                Object object = redisService.get(redisKey);
                if (object instanceof List) {
                    commentList = (List<Comment>) object;
                }
            }
        }
        if (commentList == null) {
            commentList = new CommentDomain(SystemTypeEnum.KITBOX, (long) kitBoxTypeEnum.getId()).getCommentList();
            if (SYSTEM_CONFIG.isEnableRedis()) {
                redisService.set(redisKey, commentList, SYSTEM_CONFIG.getDefaultCacheSeconds());
            }
        }
        return commentList;
    }
}
