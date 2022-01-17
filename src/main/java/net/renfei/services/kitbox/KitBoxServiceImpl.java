package net.renfei.services.kitbox;

import com.github.pagehelper.PageHelper;
import net.renfei.domain.CommentDomain;
import net.renfei.domain.comment.Comment;
import net.renfei.domain.kitbox.KitBoxTypeEnum;
import net.renfei.domain.user.User;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.model.APIResult;
import net.renfei.model.DnsTypeEnum;
import net.renfei.model.LinkTree;
import net.renfei.model.StateCodeEnum;
import net.renfei.model.kitbox.KitBoxMenus;
import net.renfei.repositories.KitboxShortUrlMapper;
import net.renfei.repositories.model.KitboxShortUrl;
import net.renfei.repositories.model.KitboxShortUrlExample;
import net.renfei.services.BaseService;
import net.renfei.services.KitBoxService;
import net.renfei.services.RedisService;
import net.renfei.services.SysService;
import net.renfei.utils.ListUtils;
import net.renfei.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    private final SysService sysService;
    private final RedisService redisService;
    private final KitboxShortUrlMapper shortUrlMapper;

    public KitBoxServiceImpl(SysService sysService,
                             RedisService redisService,
                             KitboxShortUrlMapper kitboxShortUrlMapper) {
        this.sysService = sysService;
        this.redisService = redisService;
        this.shortUrlMapper = kitboxShortUrlMapper;
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
            List<LinkTree> networkToolLinks = new ArrayList<>();
            networkToolLinks.add(buildLinkTree(KitBoxTypeEnum.NETWORK_IP));
            networkToolLinks.add(buildLinkTree(KitBoxTypeEnum.NETWORK_DIGTRACE));
            networkToolLinks.add(buildLinkTree(KitBoxTypeEnum.NETWORK_DNSQPSE));
            networkToolLinks.add(buildLinkTree(KitBoxTypeEnum.NETWORK_WHOIS));
            networkToolLinks.add(buildLinkTree(KitBoxTypeEnum.NETWORK_GETMYIP));
            networkToolLinks.add(buildLinkTree(KitBoxTypeEnum.NETWORK_CLIENV));
            kitBoxMenus.add(KitBoxMenus.builder()
                    .title("网络工具")
                    .elementId(NETWORK_TOOL)
                    .isOpen(false)
                    .links(networkToolLinks)
                    .build());
            List<LinkTree> developmentToolLinks = new ArrayList<>();
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_UUID));
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_FREEMARKER_TEST));
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_STR_HUMP_LINE_CONVERT));
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_BYTE_UNIT_CONVERSION));
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_UEDITOR));
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_WORD_IK_ANALYZE));
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_PORT_NUMBER_LIST));
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_PLIST));
            kitBoxMenus.add(KitBoxMenus.builder()
                    .title("开发类工具")
                    .elementId(DEVELOPMENT_TOOL)
                    .isOpen(false)
                    .links(developmentToolLinks)
                    .build());
            List<LinkTree> encryptionToolLinks = new ArrayList<>();
            encryptionToolLinks.add(buildLinkTree(KitBoxTypeEnum.ENCRYPTION_RANDOM_PASSWORD));
            encryptionToolLinks.add(buildLinkTree(KitBoxTypeEnum.ENCRYPTION_MD5));
            encryptionToolLinks.add(buildLinkTree(KitBoxTypeEnum.ENCRYPTION_SHA1));
            encryptionToolLinks.add(buildLinkTree(KitBoxTypeEnum.ENCRYPTION_SHA256));
            encryptionToolLinks.add(buildLinkTree(KitBoxTypeEnum.ENCRYPTION_SHA512));
            encryptionToolLinks.add(buildLinkTree(KitBoxTypeEnum.ENCRYPTION_URL16));
            kitBoxMenus.add(KitBoxMenus.builder()
                    .title("加解密工具")
                    .elementId(ENCRYPTION_TOOL)
                    .isOpen(false)
                    .links(encryptionToolLinks)
                    .build());
            List<LinkTree> otherToolLinks = new ArrayList<>();
            otherToolLinks.add(buildLinkTree(KitBoxTypeEnum.OTHER_QRCODE));
            otherToolLinks.add(buildLinkTree(KitBoxTypeEnum.OTHER_SHORT_URL));
            otherToolLinks.add(buildLinkTree(KitBoxTypeEnum.OTHER_INDEXING));
            kitBoxMenus.add(KitBoxMenus.builder()
                    .title("其他类工具")
                    .elementId(OTHER_TOOL)
                    .isOpen(false)
                    .links(otherToolLinks)
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
                kitBoxMenu.setOpen(true);
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

    /**
     * 执行 dig 命令
     *
     * @param domain
     * @return
     */
    @Override
    public APIResult<String> execDigTrace(String domain, DnsTypeEnum dnsTypeEnum) {
        assert sysService != null;
        if (dnsTypeEnum == null) {
            dnsTypeEnum = DnsTypeEnum.A;
        }
        if (StringUtils.isDomain(domain)) {
            try {
                return new APIResult<>(sysService.execCmd("dig " + domain.trim() + " " + dnsTypeEnum + " +trace"));
            } catch (IOException e) {
                return APIResult.builder()
                        .code(StateCodeEnum.Error)
                        .message("内部服务器错误。\nInternal server error.")
                        .build();
            }
        } else {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("域名格式不正确。\nIncorrect format of domain name.")
                    .build();
        }
    }

    @Override
    public APIResult<String> execWhois(String domain) {
        if (StringUtils.isDomain(domain)) {
            try {
                String result = sysService.execCmd("whois -H " + domain.trim());
                StringBuilder whoisInfo = new StringBuilder();
                if (!ObjectUtils.isEmpty(result)) {
                    String[] infos = result.split("\n");
                    boolean start = false;
                    for (String info : infos
                    ) {
                        if (info.startsWith("   ")) {
                            info = info.replace("   ", "");
                        }
                        if (info.startsWith("Domain Name")) {
                            start = true;
                        }
                        if (info.startsWith(">>> ")) {
                            start = false;
                        }
                        if (start) {
                            whoisInfo.append(info).append("\n");
                        }
                    }
                }
                return new APIResult<>(whoisInfo.toString());
            } catch (IOException e) {
                return APIResult.builder()
                        .code(StateCodeEnum.Error)
                        .message("内部服务器错误。\nInternal server error.")
                        .build();
            }
        } else {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("域名格式不正确。\nIncorrect format of domain name.")
                    .build();
        }
    }

    /**
     * 获取ShortUrl对象
     *
     * @param shortUrl 短网址
     * @return
     */
    @Override
    public KitboxShortUrl getShortUrl(String shortUrl) {
        if (shortUrl == null || shortUrl.length() == 0) {
            return null;
        }
        PageHelper.startPage(1, 1);
        KitboxShortUrlExample shortUrlExample = new KitboxShortUrlExample();
        shortUrlExample.setOrderByClause("add_time ASC");
        shortUrlExample.createCriteria()
                .andShortUrlEqualTo(shortUrl)
                .andStateCodeEqualTo(1);
        KitboxShortUrl shortUrl1 = ListUtils.getOne(shortUrlMapper.selectByExample(shortUrlExample));
        if (shortUrl1 != null) {
            shortUrl1.setViews(shortUrl1.getViews() + 1);
            updateShortUrl(shortUrl1);
        }
        return shortUrl1;
    }

    @Override
    public KitboxShortUrl createShortUrl(String url, User user) {
        KitboxShortUrl shortUrl = new KitboxShortUrl();
        shortUrl.setShortUrl(getShortUrl());
        shortUrl.setUrl(url);
        shortUrl.setAddTime(new Date(System.currentTimeMillis()));
        shortUrl.setAddUser(user == null ? null : user.getId());
        shortUrl.setStateCode(1);
        shortUrl.setViews(0L);
        shortUrlMapper.insertSelective(shortUrl);
        return shortUrl;
    }

    @Override
    public void updateShortUrl(KitboxShortUrl shortUrl) {
        shortUrlMapper.updateByPrimaryKeySelective(shortUrl);
    }

    private LinkTree buildLinkTree(KitBoxTypeEnum kitBoxTypeEnum) {
        assert SYSTEM_CONFIG != null;
        return LinkTree.builder()
                .href(SYSTEM_CONFIG.getSiteDomainName() + kitBoxTypeEnum.getUrl())
                .rel(kitBoxTypeEnum.getReadme())
                .text(kitBoxTypeEnum.getTitle())
                .build();
    }

    private String getShortUrl() {
        KitboxShortUrl lastShortUrl = getLastShortUrl();
        int len = 4;
        if (lastShortUrl != null) {
            len = lastShortUrl.getShortUrl().length();
        }
        return getShortUrl(len);
    }

    private String getShortUrl(int len) {
        String url = StringUtils.getRandomString(len);
        for (int i = 0; !check(url); i++) {
            if (i > 5) {
                len++;
            }
            url = StringUtils.getRandomString(len);
        }
        return url;
    }

    private boolean check(String url) {
        return getShortUrl(url) == null;
    }

    /**
     * 查询最新的一条记录
     *
     * @return
     */
    private KitboxShortUrl getLastShortUrl() {
        PageHelper.startPage(1, 1);
        KitboxShortUrlExample shortUrlExample = new KitboxShortUrlExample();
        shortUrlExample.setOrderByClause("add_time DESC");
        shortUrlExample.createCriteria();
        return ListUtils.getOne(shortUrlMapper.selectByExample(shortUrlExample));
    }
}
