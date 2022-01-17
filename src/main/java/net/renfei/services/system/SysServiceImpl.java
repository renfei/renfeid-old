package net.renfei.services.system;

import net.renfei.domain.UserDomain;
import net.renfei.domain.user.User;
import net.renfei.exception.BusinessException;
import net.renfei.model.*;
import net.renfei.model.system.RegionVO;
import net.renfei.model.system.UserDetail;
import net.renfei.repositories.*;
import net.renfei.repositories.model.*;
import net.renfei.services.*;
import net.renfei.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static net.renfei.config.SystemConfig.SESSION_AUTH_MODE;
import static net.renfei.controllers.BaseController.SESSION_KEY;

/**
 * 系统基础服务
 *
 * @author renfei
 */
@Service
public class SysServiceImpl extends BaseService implements SysService {
    private static final Logger logger = LoggerFactory.getLogger(SysServiceImpl.class);
    private static final String REDIS_KEY_BLOG = REDIS_KEY + "system:";
    private final LeafService leafService;
    private final RedisService redisService;
    private final SysRegionMapper regionMapper;
    private final SysSiteMenuMapper sysSiteMenuMapper;
    private final SysSecretKeyMapper sysSecretKeyMapper;
    private final SysSiteFooterMenuMapper siteFooterMenuMapper;
    private final SysSiteFriendlyLinkMapper siteFriendlyLinkMapper;

    public SysServiceImpl(LeafService leafService,
                          RedisService redisService,
                          SysRegionMapper regionMapper,
                          SysSiteMenuMapper sysSiteMenuMapper,
                          SysSecretKeyMapper sysSecretKeyMapper,
                          SysSiteFooterMenuMapper siteFooterMenuMapper,
                          SysSiteFriendlyLinkMapper siteFriendlyLinkMapper) {
        this.leafService = leafService;
        this.redisService = redisService;
        this.regionMapper = regionMapper;
        this.sysSiteMenuMapper = sysSiteMenuMapper;
        this.sysSecretKeyMapper = sysSecretKeyMapper;
        this.siteFooterMenuMapper = siteFooterMenuMapper;
        this.siteFriendlyLinkMapper = siteFriendlyLinkMapper;
    }

    /**
     * 获取行政代码
     *
     * @param regionCode 行政代码
     * @return 下一级行政代码列表
     */
    @Override
    public List<RegionVO> getRegion(String regionCode) {
        if (regionCode.length() != 6) {
            regionCode = null;
        }
        SysRegionExample example = new SysRegionExample();
        example.setOrderByClause("region_code");
        if (ObjectUtils.isEmpty(regionCode)) {
            example.createCriteria().andRegionCodeLike("__0000");
        } else if (regionCode.endsWith("0000")) {
            // 直辖市单独处理：北京、天津、上海、重庆、香港、澳门、
            if (regionCode.startsWith("110") || regionCode.startsWith("120")
                    || regionCode.startsWith("310") || regionCode.startsWith("500")
                    || regionCode.startsWith("810") || regionCode.startsWith("820")) {
                example.createCriteria().andRegionCodeLike(regionCode.substring(0, 2) + "____");
            } else {
                example.createCriteria().andRegionCodeLike(regionCode.substring(0, 2) + "__00");
            }
        } else if (regionCode.endsWith("00")) {
            example.createCriteria().andRegionCodeLike(regionCode.substring(0, 4) + "__");
        } else {
            example.createCriteria().andRegionCodeEqualTo(regionCode);
        }
        List<SysRegion> regionList = regionMapper.selectByExample(example);
        if (ObjectUtils.isEmpty(regionList)) {
            return new CopyOnWriteArrayList<>();
        }
        List<RegionVO> regionVoList = new CopyOnWriteArrayList<>();
        for (SysRegion region : regionList
        ) {
            if (region.getRegionCode().equals(regionCode)) {
                if (!"460400".equals(regionCode)
                        && !"441900".equals(regionCode)
                        && !"442000".equals(regionCode)) {
                    continue;
                }
            }
            RegionVO regionVO = new RegionVO();
            BeanUtils.copyProperties(region, regionVO);
            regionVoList.add(regionVO);
        }
        if ("410000".equals(regionCode)) {
            // 单独处理济源市（县级市）的情况
            example = new SysRegionExample();
            example.createCriteria().andRegionCodeEqualTo("419001");
            SysRegion region = CommonUtil.getOne(regionMapper.selectByExample(example));
            RegionVO regionVO = new RegionVO();
            BeanUtils.copyProperties(region, regionVO);
            regionVoList.add(regionVO);
        } else if ("420000".equals(regionCode)) {
            // 单独处理湖北省的情况
            example = new SysRegionExample();
            example.createCriteria().andRegionCodeLike("4290__");
            List<SysRegion> regionXinJiangList = regionMapper.selectByExample(example);
            for (SysRegion region : regionXinJiangList
            ) {
                RegionVO regionVO = new RegionVO();
                BeanUtils.copyProperties(region, regionVO);
                regionVoList.add(regionVO);
            }
        } else if ("650000".equals(regionCode)) {
            // 单独处理新疆维吾尔自治区直辖县级市的情况
            example = new SysRegionExample();
            example.createCriteria().andRegionCodeLike("65900_");
            List<SysRegion> regionXinJiangList = regionMapper.selectByExample(example);
            for (SysRegion region : regionXinJiangList
            ) {
                RegionVO regionVO = new RegionVO();
                BeanUtils.copyProperties(region, regionVO);
                regionVoList.add(regionVO);
            }
        } else if ("460000".equals(regionCode)) {
            // 单独处理海南省直辖县级市的情况
            example = new SysRegionExample();
            example.createCriteria().andRegionCodeLike("4690__");
            List<SysRegion> regionXinJiangList = regionMapper.selectByExample(example);
            for (SysRegion region : regionXinJiangList
            ) {
                RegionVO regionVO = new RegionVO();
                BeanUtils.copyProperties(region, regionVO);
                regionVoList.add(regionVO);
            }
        } else if ("830000".equals(regionCode)) {
            // 单独处理台湾省直辖县级市的情况
            example = new SysRegionExample();
            example.createCriteria().andRegionCodeLike("8390__");
            List<SysRegion> regionXinJiangList = regionMapper.selectByExample(example);
            for (SysRegion region : regionXinJiangList
            ) {
                RegionVO regionVO = new RegionVO();
                BeanUtils.copyProperties(region, regionVO);
                regionVoList.add(regionVO);
            }
        } else if ("429004".equals(regionCode) || "429021".equals(regionCode)
                || "429006".equals(regionCode) || "429005".equals(regionCode)
                || "659001".equals(regionCode) || "659009".equals(regionCode)
                || "659008".equals(regionCode) || "659007".equals(regionCode)
                || "659006".equals(regionCode) || "659005".equals(regionCode)
                || "659004".equals(regionCode) || "659003".equals(regionCode)
                || "659002".equals(regionCode) || "469001".equals(regionCode)
                || "469029".equals(regionCode) || "469028".equals(regionCode)
                || "469027".equals(regionCode) || "469026".equals(regionCode)
                || "469025".equals(regionCode) || "469024".equals(regionCode)
                || "469023".equals(regionCode) || "469022".equals(regionCode)
                || "469021".equals(regionCode) || "469007".equals(regionCode)
                || "469006".equals(regionCode) || "469005".equals(regionCode)
                || "469002".equals(regionCode) || "839001".equals(regionCode)
                || "839013".equals(regionCode) || "839012".equals(regionCode)
                || "839011".equals(regionCode) || "839009".equals(regionCode)
                || "839008".equals(regionCode) || "839007".equals(regionCode)
                || "839006".equals(regionCode) || "839005".equals(regionCode)
                || "839004".equals(regionCode) || "839003".equals(regionCode)
                || "429024".equals(regionCode) || "429025".equals(regionCode)
                || "429026".equals(regionCode) || "839002".equals(regionCode)
                || "419001".equals(regionCode)) {
            // 单独处理直辖县级市的情况
            example = new SysRegionExample();
            example.createCriteria().andRegionCodeEqualTo(regionCode);
            SysRegion region = CommonUtil.getOne(regionMapper.selectByExample(example));
            RegionVO regionVO = new RegionVO();
            BeanUtils.copyProperties(region, regionVO);
            regionVoList.add(regionVO);
        }
        return regionVoList;
    }

    /**
     * 获取网站友情链接列表
     *
     * @return
     */
    @Override
    public List<LinkTree> getSysSiteFriendlyLinkList() {
        SysSiteFriendlyLinkExample example = new SysSiteFriendlyLinkExample();
        example.setOrderByClause("order_id ASC");
        example.createCriteria()
                .andIsDeleteEqualTo(false)
                .andAuditPassEqualTo(true)
                .andLinkTypeEqualTo(1);
        List<SysSiteFriendlyLinkWithBLOBs> siteFriendlyLinks =
                siteFriendlyLinkMapper.selectByExampleWithBLOBs(example);
        List<LinkTree> linkList = new CopyOnWriteArrayList<>();
        if (!siteFriendlyLinks.isEmpty()) {
            siteFriendlyLinks.forEach(linkDO -> {
                LinkTree linkTree = LinkTree.builder()
                        .href(linkDO.getSitelink())
                        .rel("noopener")
                        .style("")
                        .target("_blank")
                        .text(linkDO.getSitename())
                        .build();
                linkList.add(linkTree);
            });
        }
        return linkList;
    }

    /**
     * 【危险】在服务器主机上执行命令
     *
     * @param cmd 命令
     * @return
     * @throws IOException
     */
    @Override
    public String execCmd(String cmd) throws IOException {
        Process p = Runtime.getRuntime().exec(cmd);
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    /**
     * 【危险】在服务器主机上执行命令
     *
     * @param cmd 命令
     * @return
     * @throws IOException
     */
    @Override
    public String execCmd(String[] cmd) throws IOException {
        Process p = Runtime.getRuntime().exec(cmd);
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    /**
     * 获取服务器公钥
     */
    @Override
    public Map<Integer, String> secretKey() {
        Map<Integer, String> map = RSAUtils.genKeyPair(2048);
        if (ObjectUtils.isEmpty(map)) {
            return null;
        }
        //保存
        String uuid = UUID.randomUUID().toString();
        SysSecretKeyWithBLOBs secretKeyDO = new SysSecretKeyWithBLOBs();
        secretKeyDO.setId(leafService.getId().getId());
        secretKeyDO.setUuid(uuid);
        secretKeyDO.setCreateTime(new Date());
        secretKeyDO.setPrivateKey(map.get(1));
        secretKeyDO.setPublicKey(map.get(0));
        sysSecretKeyMapper.insertSelective(secretKeyDO);
        map.put(1, uuid);
        return map;
    }

    @Override
    public Map<String, String> setSecretKey(ReportPublicKeyVO reportPublicKeyVO) throws BusinessException {
        SysSecretKeyExample example = new SysSecretKeyExample();
        example.createCriteria()
                .andUuidEqualTo(reportPublicKeyVO.getSecretKeyId());
        SysSecretKeyWithBLOBs secretKeyDO = ListUtils.getOne(sysSecretKeyMapper.selectByExampleWithBLOBs(example));
        if (ObjectUtils.isEmpty(secretKeyDO)) {
            throw new BusinessException("secretKeyId不正确");
        }
        String clientKey;
        try {
            clientKey = RSAUtils.decrypt(reportPublicKeyVO.getPublicKey(), secretKeyDO.getPrivateKey());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new BusinessException("publicKey解密失败");
        }
        String aes = StringUtils.getRandomString(16);
        String aesEnc;
        try {
            aesEnc = RSAUtils.encrypt(aes, clientKey.replaceAll("\n", ""));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new BusinessException("服务器内部错误，使用RSA客户端公钥加密失败");
        }
        //保存AES
        String uuid = UUID.randomUUID().toString();
        SysSecretKeyWithBLOBs setPrivateKey = new SysSecretKeyWithBLOBs();
        setPrivateKey.setId(leafService.getId().getId());
        setPrivateKey.setUuid(uuid);
        setPrivateKey.setCreateTime(new Date());
        setPrivateKey.setPrivateKey(aes);
        sysSecretKeyMapper.insertSelective(setPrivateKey);
        Map<String, String> map = new HashMap<>();
        map.put("keyid", uuid);
        map.put("aeskey", aesEnc);
        return map;
    }

    /**
     * 根据秘钥ID解密AES密文
     */
    @Override
    public String decrypt(String string, String keyId) {
        SysSecretKeyExample secretKeyExample = new SysSecretKeyExample();
        secretKeyExample.createCriteria()
                .andUuidEqualTo(keyId);
        SysSecretKeyWithBLOBs secretKey = ListUtils.getOne(sysSecretKeyMapper.selectByExampleWithBLOBs(secretKeyExample));
        if (ObjectUtils.isEmpty(secretKey)) {
            throw new BusinessException("AESKeyId不存在");
        } else {
            try {
                string = AESUtils.decrypt(string, secretKey.getPrivateKey());
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
                SentryUtils.captureException(ex);
                throw new BusinessException("加密密文解密失败");
            }
            return string;
        }
    }

    @Override
    public PageHead getPageHead() {
        assert SYSTEM_CONFIG != null;
        PageHead pageHead = new PageHead();
        pageHead.setTitle("");
        pageHead.setDescription("");
        pageHead.setKeywords("");
        pageHead.setAuthor(SYSTEM_CONFIG.getPageHead().getAuthor());
        pageHead.setCopyright(SYSTEM_CONFIG.getPageHead().getCopyright());
        pageHead.setDnsPrefetch(SYSTEM_CONFIG.getPageHead().getDnsPrefetch());
        pageHead.setOgProtocol(null);
        pageHead.setFavicon(SYSTEM_CONFIG.getPageHead().getFavicon());
        pageHead.setFbAppId(SYSTEM_CONFIG.getPageHead().getFbAppId());
        pageHead.setFbPages(SYSTEM_CONFIG.getPageHead().getFbPages());
        pageHead.setAppleTouchIcon(SYSTEM_CONFIG.getPageHead().getAppleTouchIcon());
        pageHead.setCss(SYSTEM_CONFIG.getPageHead().getCss());
        pageHead.setCssText("");
        pageHead.setJsText("");
        return pageHead;
    }

    @Override
    public PageHeader getPageHeader(HttpServletRequest request) {
        PageHeader pageHeader = null;
        String redisKey = REDIS_KEY_BLOG + "PageHeader";
        assert SYSTEM_CONFIG != null;
        if (SYSTEM_CONFIG.isEnableRedis()) {
            // 查询是否曾经缓存过对象，有缓存直接吐出去
            if (redisService.hasKey(redisKey)) {
                Object object = redisService.get(redisKey);
                if (object instanceof PageHeader) {
                    pageHeader = (PageHeader) object;
                }
            }
        }
        if (pageHeader == null) {
            pageHeader = new PageHeader();
            SysSiteMenuExample example = new SysSiteMenuExample();
            example.setOrderByClause("order_number");
            example.createCriteria()
                    .andPidIsNull();
            pageHeader.setMenus(convertSiteMenu(sysSiteMenuMapper.selectByExample(example)));
            if (SYSTEM_CONFIG.isEnableRedis()) {
                redisService.set(redisKey, pageHeader, SYSTEM_CONFIG.getDefaultCacheSeconds());
            }
        }
        Object object;
        if (SESSION_AUTH_MODE.equals(SYSTEM_CONFIG.getAuthMode())) {
            object = request.getSession().getAttribute(SESSION_KEY);
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            object = authentication.getPrincipal();
        }
        if (object instanceof UserDetail) {
            UserDetail userDetail = (UserDetail) object;
            Optional<UserDetail> optUserDetail = Optional.of(userDetail);
            pageHeader.setUser(optUserDetail
                    .flatMap(UserDetail::getUserDomain)
                    .flatMap(UserDomain::getUser)
                    .orElse(null));
        }
        return pageHeader;
    }

    @Override
    public PageFooter getPageFooter() {
        assert SYSTEM_CONFIG != null;
        PageFooter pageFooter = null;
        String redisKey = REDIS_KEY_BLOG + "PageFooter";
        if (SYSTEM_CONFIG.isEnableRedis()) {
            // 查询是否曾经缓存过对象，有缓存直接吐出去
            if (redisService.hasKey(redisKey)) {
                Object object = redisService.get(redisKey);
                if (object instanceof PageFooter) {
                    pageFooter = (PageFooter) object;
                }
            }
        }
        if (pageFooter == null) {
            SysSiteFooterMenuExample example = new SysSiteFooterMenuExample();
            example.setOrderByClause("order_number");
            example.createCriteria()
                    .andPidIsNull();
            pageFooter = new PageFooter();
            pageFooter.setShowFriendlyLink(SYSTEM_CONFIG.isShowFriendlyLink());
            pageFooter.setFriendlyLink(this.getSysSiteFriendlyLinkList());
            pageFooter.setVersion(SYSTEM_CONFIG.getVersion());
            pageFooter.setBuildTime(SYSTEM_CONFIG.getBuildTime());
            pageFooter.setFooterMenuLinks(convertFooterMenu(siteFooterMenuMapper.selectByExample(example)));
            pageFooter.setSmallMenu(smallMenuList());
            pageFooter.setJss(SYSTEM_CONFIG.getPageFooter().getJss());
            pageFooter.setJsText("var _hmt = _hmt || [];\n"
                    + "(function() {\n"
                    + "  var hm = document.createElement(\"script\");\n"
                    + "  var analytics_bd = '" + SYSTEM_CONFIG.getBaidu().getTongji() + "';\n"
                    // 为了防止爬虫扫描到统计代码的key，将URL地址打碎成数组
                    // 原地址：hm.src = https://hm.baidu.com/hm.js?<key>
                    + "  hm.src = ['ht','t','ps',':/','/h','m','.','ba','i','d','u.c','o','m/','h','m','.j','s?',analytics_bd].join('');\n"
                    + "  var s = document.getElementsByTagName(\"script\")[0]; \n"
                    + "  s.parentNode.insertBefore(hm, s);\n"
                    + "})();\n");
            if (SYSTEM_CONFIG.isEnableRedis()) {
                redisService.set(redisKey, pageFooter, SYSTEM_CONFIG.getDefaultCacheSeconds());
            }
        }
        return pageFooter;
    }

    private List<LinkTree> convertSiteMenu(List<SysSiteMenu> sysSiteMenus) {
        List<LinkTree> menus = new CopyOnWriteArrayList<>();
        for (SysSiteMenu sysSiteMenu : sysSiteMenus
        ) {
            LinkTree linkSubTree = LinkTree.builder()
                    .href(sysSiteMenu.getMenuLink())
                    .text(sysSiteMenu.getMenuText())
                    .target(sysSiteMenu.getIsNewWin() ? "_blank" : "_self")
                    .build();
            linkSubTree.setSubLink(setSiteMenuSubLink(sysSiteMenu.getId()));
            menus.add(linkSubTree);
        }
        return menus;
    }

    private List<LinkTree> setSiteMenuSubLink(Long pid) {
        SysSiteMenuExample example = new SysSiteMenuExample();
        example.setOrderByClause("order_number");
        example.createCriteria()
                .andPidEqualTo(pid);
        assert sysSiteMenuMapper != null;
        List<SysSiteMenu> sysSiteMenus = sysSiteMenuMapper.selectByExample(example);
        if (sysSiteMenus == null || sysSiteMenus.size() < 1) {
            return null;
        }
        return convertSiteMenu(sysSiteMenus);
    }

    private List<LinkTree> smallMenuList() {
        List<LinkTree> menus = new CopyOnWriteArrayList<>();
        String split = "@";
        // 前端是居右，这里需要倒序输出
        assert SYSTEM_CONFIG != null;
        for (int i = SYSTEM_CONFIG.getFooterSmallMenu().size() - 1; i >= 0; i--) {
            LinkTree linkTree = LinkTree.builder()
                    .href(SYSTEM_CONFIG.getFooterSmallMenu().get(i).split(split)[1])
                    .text(SYSTEM_CONFIG.getFooterSmallMenu().get(i).split(split)[0])
                    .target("_blank")
                    .build();
            menus.add(linkTree);
        }
        return menus;
    }

    private List<FooterMenuLinks> convertFooterMenu(List<SysSiteFooterMenu> sysSiteMenus) {
        List<FooterMenuLinks> menus = new CopyOnWriteArrayList<>();
        for (SysSiteFooterMenu sysSiteMenu : sysSiteMenus
        ) {
            FooterMenuLinks linkSubTree = new FooterMenuLinks();
            linkSubTree.setTitle(sysSiteMenu.getMenuText());
            linkSubTree.setLinks(setFooterMenuSubLink(sysSiteMenu.getId()));
            menus.add(linkSubTree);
        }
        return menus;
    }

    private List<LinkTree> setFooterMenuSubLink(Long pid) {
        SysSiteFooterMenuExample example = new SysSiteFooterMenuExample();
        example.setOrderByClause("order_number");
        example.createCriteria()
                .andPidEqualTo(pid);
        assert siteFooterMenuMapper != null;
        List<SysSiteFooterMenu> sysSiteMenus = siteFooterMenuMapper.selectByExample(example);
        if (sysSiteMenus == null || sysSiteMenus.size() < 1) {
            return null;
        }
        List<LinkTree> menus = new CopyOnWriteArrayList<>();
        for (SysSiteFooterMenu sysSiteMenu : sysSiteMenus
        ) {
            LinkTree linkSubTree = LinkTree.builder()
                    .href(sysSiteMenu.getMenuLink())
                    .text(sysSiteMenu.getMenuText())
                    .target(sysSiteMenu.getIsNewWin() ? "_blank" : "_self")
                    .build();
            menus.add(linkSubTree);
        }
        return menus;
    }
}
