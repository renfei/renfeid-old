package net.renfei.services.kitbox;

import com.github.pagehelper.PageHelper;
import net.renfei.domain.CommentDomain;
import net.renfei.domain.comment.Comment;
import net.renfei.domain.kitbox.KitBoxTypeEnum;
import net.renfei.domain.user.User;
import net.renfei.exception.BusinessException;
import net.renfei.model.APIResult;
import net.renfei.model.DnsTypeEnum;
import net.renfei.model.LinkTree;
import net.renfei.model.StateCodeEnum;
import net.renfei.model.kitbox.IcpQueryVo;
import net.renfei.model.kitbox.KitBoxMenus;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.repositories.KitboxDneyesRecordLogMapper;
import net.renfei.repositories.KitboxDneyesRecordMapper;
import net.renfei.repositories.KitboxIcpCacheMapper;
import net.renfei.repositories.KitboxShortUrlMapper;
import net.renfei.repositories.model.*;
import net.renfei.services.BaseService;
import net.renfei.services.KitBoxService;
import net.renfei.services.RedisService;
import net.renfei.services.SysService;
import net.renfei.utils.*;
import org.apache.hc.core5.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
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
    private static final Logger logger = LoggerFactory.getLogger(KitBoxServiceImpl.class);
    private static final String REDIS_KEY_KITBOX = REDIS_KEY + "kitbox:";
    public static final String NETWORK_TOOL = "networkTool";
    public static final String DEVELOPMENT_TOOL = "developmentTool";
    public static final String ENCRYPTION_TOOL = "encryptionTool";
    public static final String OTHER_TOOL = "otherTool";
    public static final String DNEYES_DOMAIN = "dneyes.net";
    private final SysService sysService;
    private final RedisService redisService;
    private final KitboxShortUrlMapper shortUrlMapper;
    private final KitboxIcpCacheMapper icpCacheMapper;
    private final KitboxDneyesRecordMapper kitboxDneyesRecordMapper;
    private final KitboxDneyesRecordLogMapper kitboxDneyesRecordLogMapper;

    public KitBoxServiceImpl(SysService sysService,
                             RedisService redisService,
                             KitboxShortUrlMapper kitboxShortUrlMapper,
                             KitboxIcpCacheMapper kitboxIcpCacheMapper,
                             KitboxDneyesRecordMapper kitboxDneyesRecordMapper,
                             KitboxDneyesRecordLogMapper kitboxDneyesRecordLogMapper) {
        this.sysService = sysService;
        this.redisService = redisService;
        this.shortUrlMapper = kitboxShortUrlMapper;
        this.icpCacheMapper = kitboxIcpCacheMapper;
        this.kitboxDneyesRecordMapper = kitboxDneyesRecordMapper;
        this.kitboxDneyesRecordLogMapper = kitboxDneyesRecordLogMapper;
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
        assert systemConfig != null;
        if (systemConfig.isEnableRedis()) {
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
            networkToolLinks.add(buildLinkTree(KitBoxTypeEnum.NETWORK_ICP));
            networkToolLinks.add(buildLinkTree(KitBoxTypeEnum.NETWORK_GETMYIP));
            networkToolLinks.add(buildLinkTree(KitBoxTypeEnum.NETWORK_CLIENV));
            // 暂时移除DNeyeS工具，国内云主机商不允许开放 53端口，需要境外部署
            // networkToolLinks.add(buildLinkTree(KitBoxTypeEnum.NETWORK_DNEYES));
            kitBoxMenus.add(KitBoxMenus.builder()
                    .title("网络工具")
                    .elementId(NETWORK_TOOL)
                    .isOpen(false)
                    .links(networkToolLinks)
                    .build());
            List<LinkTree> developmentToolLinks = new ArrayList<>();
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_UUID));
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_FREEMARKER_TEST));
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_XPATH_TEST));
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_STR_HUMP_LINE_CONVERT));
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_BYTE_UNIT_CONVERSION));
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_UEDITOR));
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_WORD_IK_ANALYZE));
            developmentToolLinks.add(buildLinkTree(KitBoxTypeEnum.DEVELOP_KEY_WORD));
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
            if (systemConfig.isEnableRedis()) {
                redisService.set(redisKey, kitBoxMenus, systemConfig.getDefaultCacheSeconds());
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
        assert systemConfig != null;
        if (systemConfig.isEnableRedis()) {
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
            if (systemConfig.isEnableRedis()) {
                redisService.set(redisKey, commentList, systemConfig.getDefaultCacheSeconds());
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

    @Override
    public IcpQueryVo.IcpInfo queryIcpInfo(String domain, Boolean refresh) {
        domain = domain.toLowerCase();
        if (!StringUtils.isDomain(domain)) {
            throw new BusinessException("请输入正确的域名格式");
        }
        String redisKey = REDIS_KEY_KITBOX + "icp:" + domain;
        IcpQueryVo.IcpInfo icpInfo = null;
        assert systemConfig != null;
        if (refresh != null && refresh) {
            // 强制刷新，删除缓存
            if (systemConfig.isEnableRedis()) {
                redisService.del(redisKey);
            }
        }
        if (systemConfig.isEnableRedis()) {
            // 查询是否曾经缓存过对象，有缓存直接吐出去
            if (redisService.hasKey(redisKey)) {
                Object object = redisService.get(redisKey);
                if (object instanceof List) {
                    icpInfo = (IcpQueryVo.IcpInfo) object;
                }
            }
        }
        if (icpInfo == null) {
            // 在 Redis 中没查到
            if (refresh == null || !refresh) {
                // 去数据库查
                KitboxIcpCacheExample example = new KitboxIcpCacheExample();
                example.createCriteria()
                        .andDomainEqualTo(domain);
                KitboxIcpCache kitboxIcpCache = ListUtils.getOne(icpCacheMapper.selectByExample(example));
                if (kitboxIcpCache != null) {
                    icpInfo = new IcpQueryVo.IcpInfo();
                    BeanUtils.copyProperties(kitboxIcpCache, icpInfo);
                }
            }
        }
        if (icpInfo == null) {
            // 去工信部查询，并更新数据库
            try {
                icpInfo = queryIcpInfo(domain);
            } catch (IOException | ParseException e) {
                logger.error("ICP查询服务暂时不可用", e);
                throw new BusinessException("服务暂时不可用");
            }
            if (icpInfo != null) {
                // 更新数据库
                KitboxIcpCacheExample example = new KitboxIcpCacheExample();
                example.createCriteria()
                        .andDomainEqualTo(domain);
                KitboxIcpCache kitboxIcpCache = ListUtils.getOne(icpCacheMapper.selectByExample(example));
                if (kitboxIcpCache != null) {
                    BeanUtils.copyProperties(icpInfo, kitboxIcpCache);
                    kitboxIcpCache.setCacheTime(DateUtils.getDate("yyyy-MM-dd hh:mm:ss"));
                    icpInfo.setCacheTime(DateUtils.getDate("yyyy-MM-dd hh:mm:ss"));
                    icpCacheMapper.updateByPrimaryKey(kitboxIcpCache);
                } else {
                    kitboxIcpCache = new KitboxIcpCache();
                    BeanUtils.copyProperties(icpInfo, kitboxIcpCache);
                    kitboxIcpCache.setCacheTime(DateUtils.getDate("yyyy-MM-dd hh:mm:ss"));
                    icpInfo.setCacheTime(DateUtils.getDate("yyyy-MM-dd hh:mm:ss"));
                    icpCacheMapper.insertSelective(kitboxIcpCache);
                }
            }
        }
        return icpInfo;
    }

    /**
     * 生成一个 DNeyeS 子域名
     *
     * @param user    登陆用户
     * @param request 请求对象
     * @return
     */
    @Override
    public String generateDneyesSubdomain(User user, HttpServletRequest request) {
        KitboxDneyesRecordExample example = new KitboxDneyesRecordExample();
        String subdomain = StringUtils.getRandomString(6).toLowerCase();
        subdomain += "." + DNEYES_DOMAIN;
        example.createCriteria().andSubDomainEqualTo(subdomain);
        while (!kitboxDneyesRecordMapper.selectByExample(example).isEmpty()) {
            subdomain = StringUtils.getRandomString(6).toLowerCase();
            subdomain += "." + DNEYES_DOMAIN;
            example.createCriteria().andSubDomainEqualTo(subdomain);
        }
        KitboxDneyesRecord kitboxDneyesRecord = new KitboxDneyesRecord();
        kitboxDneyesRecord.setCreateIp(IpUtils.getIpAddress(request));
        kitboxDneyesRecord.setCreateTime(new Date());
        kitboxDneyesRecord.setSubDomain(subdomain);
        kitboxDneyesRecord.setCreateUserId(user == null ? null : user.getId());
        kitboxDneyesRecordMapper.insertSelective(kitboxDneyesRecord);
        return subdomain;
    }

    /**
     * 查询 DNeyeS 子域名解析记录
     *
     * @param subdomain 子域名
     * @param user      登陆用户
     * @return
     */
    @Override
    public List<KitboxDneyesRecordLog> queryDneyesRecordLog(String subdomain, User user) {
        if (subdomain == null || subdomain.isEmpty()) {
            return new ArrayList<>();
        }
        subdomain = subdomain.toLowerCase();
        if (subdomain.endsWith("." + DNEYES_DOMAIN)) {
            KitboxDneyesRecordExample example = new KitboxDneyesRecordExample();
            example.createCriteria().andSubDomainEqualTo(subdomain);
            KitboxDneyesRecord dneyesRecord = ListUtils.getOne(kitboxDneyesRecordMapper.selectByExample(example));
            if (dneyesRecord == null) {
                return new ArrayList<>();
            }
            if (dneyesRecord.getCreateUserId() != null) {
                if (user == null || !dneyesRecord.getCreateUserId().equals(user.getId())) {
                    return new ArrayList<>();
                }
            }
            KitboxDneyesRecordLogExample recordLogExample = new KitboxDneyesRecordLogExample();
            recordLogExample.setOrderByClause("log_time DESC");
            recordLogExample.createCriteria()
                    .andSubDomainLike("%" + subdomain);
            return kitboxDneyesRecordLogMapper.selectByExampleWithBLOBs(recordLogExample);
        } else {
            return new ArrayList<>();
        }
    }

    private IcpQueryVo.IcpInfo queryIcpInfo(String domain) throws IOException, ParseException {
        IcpQueryUtil icpQueryUtil = new IcpQueryUtil();
        IcpQueryVo icpQueryVo = icpQueryUtil.queryIcpInfo(domain);
        if (icpQueryVo != null && icpQueryVo.getCode() == 200 && icpQueryVo.getSuccess()) {
            if (icpQueryVo.getParams().getList().isEmpty()) {
                return null;
            }
            return icpQueryVo.getParams().getList().get(0);
        } else {
            logger.error("ICP查询服务暂时不可用");
            throw new BusinessException("服务暂时不可用");
        }
    }

    private LinkTree buildLinkTree(KitBoxTypeEnum kitBoxTypeEnum) {
        assert systemConfig != null;
        return LinkTree.builder()
                .href(systemConfig.getSiteDomainName() + kitBoxTypeEnum.getUrl())
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
