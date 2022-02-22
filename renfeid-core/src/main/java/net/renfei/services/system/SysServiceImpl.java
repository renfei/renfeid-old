package net.renfei.services.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.domain.UserDomain;
import net.renfei.domain.user.User;
import net.renfei.exception.BusinessException;
import net.renfei.model.*;
import net.renfei.model.log.LogLevelEnum;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.system.*;
import net.renfei.repositories.*;
import net.renfei.repositories.model.*;
import net.renfei.services.BaseService;
import net.renfei.services.LeafService;
import net.renfei.services.RedisService;
import net.renfei.services.SysService;
import net.renfei.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 系统基础服务
 *
 * @author renfei
 */
@Service
public class SysServiceImpl extends BaseService implements SysService {
    private static final Logger logger = LoggerFactory.getLogger(SysServiceImpl.class);
    private static final String REDIS_KEY_BLOG = REDIS_KEY + "system:";
    private static final String SYSTEM_OPERATION_STATUS_KEY = "SYSTEM_OPERATION_STATUS";
    private final LeafService leafService;

    private final RedisService redisService;
    private final SysLogsMapper sysLogsMapper;
    private final SysRoleMapper sysRoleMapper;
    private final SysMenuMapper sysMenuMapper;
    private final SysRegionMapper regionMapper;
    private final UserDetailUtils userDetailUtils;
    private final SysApiListMapper sysApiListMapper;
    private final SysSettingMapper sysSettingMapper;
    private final SysSiteMenuMapper sysSiteMenuMapper;
    private final SysSecretKeyMapper sysSecretKeyMapper;
    private final SysAccountRoleMapper sysAccountRoleMapper;
    private final SysSiteFooterMenuMapper siteFooterMenuMapper;
    private final SysRolePermissionMapper sysRolePermissionMapper;
    private final SysSiteFriendlyLinkMapper siteFriendlyLinkMapper;

    public SysServiceImpl(LeafService leafService,
                          RedisService redisService,
                          SysLogsMapper sysLogsMapper,
                          SysRoleMapper sysRoleMapper,
                          SysMenuMapper sysMenuMapper,
                          SysRegionMapper regionMapper,
                          UserDetailUtils userDetailUtils,
                          SysApiListMapper sysApiListMapper,
                          SysSettingMapper sysSettingMapper,
                          SysSiteMenuMapper sysSiteMenuMapper,
                          SysSecretKeyMapper sysSecretKeyMapper,
                          SysAccountRoleMapper sysAccountRoleMapper,
                          SysSiteFooterMenuMapper siteFooterMenuMapper,
                          SysRolePermissionMapper sysRolePermissionMapper,
                          SysSiteFriendlyLinkMapper siteFriendlyLinkMapper) {
        this.leafService = leafService;
        this.redisService = redisService;
        this.sysLogsMapper = sysLogsMapper;
        this.sysRoleMapper = sysRoleMapper;
        this.sysMenuMapper = sysMenuMapper;
        this.regionMapper = regionMapper;
        this.userDetailUtils = userDetailUtils;
        this.sysApiListMapper = sysApiListMapper;
        this.sysSettingMapper = sysSettingMapper;
        this.sysSiteMenuMapper = sysSiteMenuMapper;
        this.sysSecretKeyMapper = sysSecretKeyMapper;
        this.sysAccountRoleMapper = sysAccountRoleMapper;
        this.siteFooterMenuMapper = siteFooterMenuMapper;
        this.sysRolePermissionMapper = sysRolePermissionMapper;
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
        assert systemConfig != null;
        PageHead pageHead = new PageHead();
        pageHead.setTitle("");
        pageHead.setDescription("");
        pageHead.setKeywords("");
        pageHead.setAuthor(systemConfig.getPageHead().getAuthor());
        pageHead.setCopyright(systemConfig.getPageHead().getCopyright());
        pageHead.setDnsPrefetch(systemConfig.getPageHead().getDnsPrefetch());
        pageHead.setOgProtocol(null);
        pageHead.setFavicon(systemConfig.getPageHead().getFavicon());
        pageHead.setFbAppId(systemConfig.getPageHead().getFbAppId());
        pageHead.setFbPages(systemConfig.getPageHead().getFbPages());
        pageHead.setAppleTouchIcon(systemConfig.getPageHead().getAppleTouchIcon());
        pageHead.setCss(systemConfig.getPageHead().getCss());
        pageHead.setCssText("");
        pageHead.setJsText("");
        return pageHead;
    }

    @Override
    public PageHeader getPageHeader(HttpServletRequest request) {
        PageHeader pageHeader = null;
        String redisKey = REDIS_KEY_BLOG + "PageHeader";
        assert systemConfig != null;
        if (systemConfig.isEnableRedis()) {
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
            if (systemConfig.isEnableRedis()) {
                redisService.set(redisKey, pageHeader, systemConfig.getDefaultCacheSeconds());
            }
        }
        UserDetail userDetail = userDetailUtils.getUserDetail(request);
        Optional<UserDetail> optUserDetail = Optional.ofNullable(userDetail);
        User user = optUserDetail
                .flatMap(UserDetail::getUserDomain)
                .flatMap(UserDomain::getUser)
                .orElse(null);
        pageHeader.setUser(user);
        return pageHeader;
    }

    @Override
    public PageFooter getPageFooter() {
        assert systemConfig != null;
        PageFooter pageFooter = null;
        String redisKey = REDIS_KEY_BLOG + "PageFooter";
        if (systemConfig.isEnableRedis()) {
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
            pageFooter.setShowFriendlyLink(systemConfig.isShowFriendlyLink());
            pageFooter.setFriendlyLink(this.getSysSiteFriendlyLinkList());
            pageFooter.setVersion(systemConfig.getVersion());
            pageFooter.setBuildTime(systemConfig.getBuildTime());
            pageFooter.setFooterMenuLinks(convertFooterMenu(siteFooterMenuMapper.selectByExample(example)));
            pageFooter.setSmallMenu(smallMenuList());
            pageFooter.setJss(systemConfig.getPageFooter().getJss());
            pageFooter.setJsText("var _hmt = _hmt || [];\n"
                    + "(function() {\n"
                    + "  var hm = document.createElement(\"script\");\n"
                    + "  var analytics_bd = '" + systemConfig.getBaidu().getTongji() + "';\n"
                    // 为了防止爬虫扫描到统计代码的key，将URL地址打碎成数组
                    // 原地址：hm.src = https://hm.baidu.com/hm.js?<key>
                    + "  hm.src = ['ht','t','ps',':/','/h','m','.','ba','i','d','u.c','o','m/','h','m','.j','s?',analytics_bd].join('');\n"
                    + "  var s = document.getElementsByTagName(\"script\")[0]; \n"
                    + "  s.parentNode.insertBefore(hm, s);\n"
                    + "})();\n"
                    + "\n"
                    // Google Analytics
                    + "window.dataLayer = window.dataLayer || [];\n" +
                    "  function gtag(){dataLayer.push(arguments);}\n" +
                    "  gtag('js', new Date());\n" +
                    "\n" +
                    "  gtag('config', '" + systemConfig.getGoogle().getAnalytics() + "');");
            if (systemConfig.isEnableRedis()) {
                redisService.set(redisKey, pageFooter, systemConfig.getDefaultCacheSeconds());
            }
        }
        return pageFooter;
    }

    /**
     * 系统接口列表
     * 用于给角色分配权限使用
     *
     * @param user  登陆用户
     * @param pages 页码
     * @param rows  每页行数
     * @return
     */
    @Override
    public ListData<SysApi> getSysApiList(User user, String pages, String rows) {
        assert systemConfig != null;
        ListData<SysApi> sysApiListData = null;
        boolean securityAdmin = false;
        List<SysAccountRole> accountRoles = null;
        if (systemConfig.getSuperTubeUserName().equals(user.getUserName())) {
            // 超管，不限制权限，直接查全部
            securityAdmin = true;
        } else {
            // 判断角色
            SysAccountRoleExample accountRoleExample = new SysAccountRoleExample();
            accountRoleExample.createCriteria().andAccountIdEqualTo(user.getId());
            accountRoles = sysAccountRoleMapper.selectByExample(accountRoleExample);
            if (!accountRoles.isEmpty()) {
                for (SysAccountRole accountRole : accountRoles
                ) {
                    if (accountRole.getRoleId() == 2) {
                        securityAdmin = true;
                        break;
                    }
                }
            }
        }
        if (securityAdmin) {
            // 超管或安全管理员，不限制权限，直接查全部
            SysApiListExample example = new SysApiListExample();
            Page<SysApiList> page = PageHelper.startPage(
                    NumberUtils.parseInt(pages, 1), NumberUtils.parseInt(rows, 10));
            sysApiListMapper.selectByExample(example);
            sysApiListData = new ListData<>(page);
            List<SysApi> sysApiList = new ArrayList<>();
            page.getResult().forEach(sysApi -> {
                SysApi api = new SysApi();
                BeanUtils.copyProperties(sysApi, api);
                sysApiList.add(api);
            });
            sysApiListData.setData(sysApiList);
        } else {
            // 判断用户权限，用户只能获取到自己拥有权限的 API列表
            List<Long> roleIds = new ArrayList<>();
            if (!accountRoles.isEmpty()) {
                accountRoles.forEach(sysAccountRole -> roleIds.add(sysAccountRole.getRoleId()));
                SysRolePermissionExample rolePermissionExample = new SysRolePermissionExample();
                rolePermissionExample.createCriteria()
                        .andPermissionTypeEqualTo("API")
                        .andRoleIdIn(roleIds);
                List<SysRolePermission> sysRolePermissions =
                        sysRolePermissionMapper.selectByExample(rolePermissionExample);
                if (!sysRolePermissions.isEmpty()) {
                    List<Long> apiIds = new ArrayList<>();
                    sysRolePermissions.forEach(sysRolePermission -> apiIds.add(sysRolePermission.getPermissionId()));
                    SysApiListExample example = new SysApiListExample();
                    example.createCriteria()
                            .andIdIn(apiIds);
                    Page<SysApiList> page = PageHelper.startPage(
                            NumberUtils.parseInt(pages, 1), NumberUtils.parseInt(rows, 10));
                    sysApiListMapper.selectByExample(example);
                    sysApiListData = new ListData<>(page);
                    List<SysApi> sysApiList = new ArrayList<>();
                    page.getResult().forEach(sysApi -> {
                        SysApi api = new SysApi();
                        BeanUtils.copyProperties(sysApi, api);
                        sysApiList.add(api);
                    });
                    sysApiListData.setData(sysApiList);
                }
            }
        }
        return sysApiListData;
    }

    @Override
    public List<SysApiList> getSysApiAllList() {
        SysApiListExample example = new SysApiListExample();
        return sysApiListMapper.selectByExample(example);
    }

    /**
     * 获取系统角色
     * 只能获取到登陆用户自己拥有的角色
     * 超管和安全管理员可以获取所有角色
     *
     * @param user  登陆用户
     * @param pages 页码
     * @param rows  每页行数
     * @return
     */
    @Override
    public ListData<SysRoleVO> getSysRoleList(User user, String pages, String rows) {
        SysRoleExample sysRoleExample = new SysRoleExample();
        SysRoleExample.Criteria criteria = sysRoleExample.createCriteria();
        assert systemConfig != null;
        if (systemConfig.getSuperTubeUserName().equals(user.getUserName())) {
            // 超管查全，除了内置的
            criteria.andBuiltInRoleEqualTo(false);
        } else {
            // 判断角色
            SysAccountRoleExample accountRoleExample = new SysAccountRoleExample();
            accountRoleExample.createCriteria().andAccountIdEqualTo(user.getId());
            List<SysAccountRole> accountRoles = sysAccountRoleMapper.selectByExample(accountRoleExample);
            boolean securityAdmin = false;
            if (!accountRoles.isEmpty()) {
                for (SysAccountRole accountRole : accountRoles
                ) {
                    if (accountRole.getRoleId() == 2) {
                        securityAdmin = true;
                        break;
                    }
                }
            }
            if (securityAdmin) {
                // 系统内置的安全管理员，也可以查全部，除了内置的
                criteria.andBuiltInRoleEqualTo(false);
            } else {
                // 只能查到自己拥有的角色列表
                if (accountRoles.isEmpty()) {
                    return new ListData<>();
                }
                List<Long> ids = new ArrayList<>();
                accountRoles.forEach(sysAccountRole -> ids.add(sysAccountRole.getRoleId()));
                sysRoleExample = new SysRoleExample();
                sysRoleExample.createCriteria()
                        .andIdIn(ids)
                        .andBuiltInRoleEqualTo(false);
            }
        }
        Page<SysRole> page = PageHelper.startPage(
                NumberUtils.parseInt(pages, 1), NumberUtils.parseInt(rows, 10));
        sysRoleMapper.selectByExample(sysRoleExample);
        ListData<SysRoleVO> listData = new ListData<>(page);
        List<SysRoleVO> sysRoles = new ArrayList<>();
        if (!page.getResult().isEmpty()) {
            for (SysRole sysRole : page.getResult()
            ) {
                SysRoleVO sysRoleVO = new SysRoleVO();
                BeanUtils.copyProperties(sysRole, sysRoleVO);
                // 查询角色下的权限列表
                SysRolePermissionExample rolePermissionExample = new SysRolePermissionExample();
                rolePermissionExample.createCriteria()
                        .andRoleIdEqualTo(sysRole.getId());
                sysRoleVO.setRolePermissions(sysRolePermissionMapper.selectByExample(rolePermissionExample));
                sysRoles.add(sysRoleVO);
            }
        }
        listData.setData(sysRoles);
        return listData;
    }

    /**
     * 添加系统角色
     * 只有超管和安全管理员可以添加角色
     *
     * @param user    登陆用户
     * @param sysRole 系统角色
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSysRole(User user, SysRoleVO sysRole) {
        // 检查 EnName 重复
        if (sysRole.getEnName() == null || sysRole.getEnName().isEmpty()) {
            throw new BusinessException("EnName 不能为空");
        }
        SysRoleExample example = new SysRoleExample();
        example.createCriteria().andEnNameEqualTo(sysRole.getEnName());
        if (sysRoleMapper.selectByExample(example).isEmpty()) {
            sysRole.setId(null);
            sysRole.setBuiltInRole(false);
            SysRole sysRoleDO = new SysRole();
            BeanUtils.copyProperties(sysRole, sysRoleDO);
            sysRoleMapper.insert(sysRoleDO);
            if (!sysRole.getRolePermissions().isEmpty()) {
                // 插入权限列表
                for (SysRolePermission sysRolePermission : sysRole.getRolePermissions()
                ) {
                    sysRolePermission.setId(null);
                    sysRolePermission.setRoleId(sysRoleDO.getId());
                    sysRolePermissionMapper.insertSelective(sysRolePermission);
                }
            }
        } else {
            throw new BusinessException("EnName:[" + sysRole.getEnName() + "]已经存在，不能设置重复的。");
        }
    }

    /**
     * 修改系统角色
     * 只有超管和安全管理员可以修改角色
     *
     * @param user    登陆用户
     * @param sysRole 系统角色
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSysRole(User user, SysRoleVO sysRole) {
        if (sysRole.getId() == null) {
            throw new BusinessException("ID 不能为空");
        }
        SysRole sysRoleOld = sysRoleMapper.selectByPrimaryKey(sysRole.getId());
        if (sysRoleOld == null) {
            throw new BusinessException("未查询到该角色信息");
        }
        if (sysRoleOld.getBuiltInRole()) {
            throw new BusinessException("内置角色，系统拒绝编辑");
        }
        sysRoleOld.setZhName(sysRole.getZhName());
        if (sysRoleOld.getEnName() != null && !sysRoleOld.getEnName().equals(sysRole.getEnName())) {
            // 检查 EnName 重复
            if (sysRole.getEnName() == null || sysRole.getEnName().isEmpty()) {
                throw new BusinessException("EnName 不能为空");
            }
            SysRoleExample sysRoleExample = new SysRoleExample();
            sysRoleExample.createCriteria().andEnNameEqualTo(sysRole.getEnName());
            if (!sysRoleMapper.selectByExample(sysRoleExample).isEmpty()) {
                throw new BusinessException("EnName:[" + sysRole.getEnName() + "]已经存在，不能设置重复的。");
            }
        }
        sysRoleOld.setEnName(sysRole.getEnName());
        sysRoleMapper.updateByPrimaryKey(sysRoleOld);
        // 先删除角色下面的权限列表，再插入
        SysRolePermissionExample rolePermissionExample = new SysRolePermissionExample();
        rolePermissionExample.createCriteria().andRoleIdEqualTo(sysRoleOld.getId());
        sysRolePermissionMapper.deleteByExample(rolePermissionExample);
        // 再插入
        if (!sysRole.getRolePermissions().isEmpty()) {
            // 插入权限列表
            for (SysRolePermission sysRolePermission : sysRole.getRolePermissions()
            ) {
                sysRolePermission.setId(null);
                sysRolePermission.setRoleId(sysRoleOld.getId());
                sysRolePermissionMapper.insertSelective(sysRolePermission);
            }
        }
    }

    /**
     * 删除系统角色
     * 只有超管和安全管理员可以删除系统角色
     *
     * @param user 登陆用户
     * @param id   系统角色ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSysRole(User user, Long id) {
        if (id == null) {
            throw new BusinessException("ID 不能为空");
        }
        SysRole sysRoleOld = sysRoleMapper.selectByPrimaryKey(id);
        if (sysRoleOld == null) {
            throw new BusinessException("未查询到该角色信息");
        }
        if (sysRoleOld.getBuiltInRole()) {
            throw new BusinessException("内置角色，系统拒绝编辑");
        }
        sysRoleMapper.deleteByPrimaryKey(id);
        // 删除账户与角色关系表
        SysAccountRoleExample accountRoleExample = new SysAccountRoleExample();
        accountRoleExample.createCriteria()
                .andRoleIdEqualTo(id);
        sysAccountRoleMapper.deleteByExample(accountRoleExample);
        // 删除角色与权限关系表
        SysRolePermissionExample rolePermissionExample = new SysRolePermissionExample();
        rolePermissionExample.createCriteria()
                .andRoleIdEqualTo(id);
        sysRolePermissionMapper.deleteByExample(rolePermissionExample);
    }

    /**
     * 根据用户获取用户的角色列表
     *
     * @param user
     * @return
     */
    @Override
    public List<SysRoleVO> getRoleListByUser(User user) {
        if (user == null) {
            return new ArrayList<>();
        }
        SysAccountRoleExample accountRoleExample = new SysAccountRoleExample();
        accountRoleExample.createCriteria()
                .andAccountIdEqualTo(user.getId());
        List<SysAccountRole> sysAccountRoles = sysAccountRoleMapper.selectByExample(accountRoleExample);
        if (sysAccountRoles.isEmpty()) {
            return new ArrayList<>();
        }
        List<Long> ids = new ArrayList<>();
        sysAccountRoles.forEach(sysAccountRole -> ids.add(sysAccountRole.getRoleId()));
        SysRoleExample example = new SysRoleExample();
        example.createCriteria()
                .andIdIn(ids);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(example);
        List<SysRoleVO> sysRoleVOS = new CopyOnWriteArrayList<>();
        for (SysRole sysRole : sysRoles
        ) {
            SysRoleVO sysRoleVO = new SysRoleVO();
            BeanUtils.copyProperties(sysRole, sysRoleVO);
            // 查询角色下的权限列表
            SysRolePermissionExample rolePermissionExample = new SysRolePermissionExample();
            rolePermissionExample.createCriteria()
                    .andRoleIdEqualTo(sysRole.getId());
            sysRoleVO.setRolePermissions(sysRolePermissionMapper.selectByExample(rolePermissionExample));
            sysRoleVOS.add(sysRoleVO);
        }
        return sysRoleVOS;
    }

    /**
     * 根据API接口地址获取所需的角色列表
     *
     * @param sysApiList
     * @return
     */
    @Override
    public List<RoleDTO> getRoleDtoBySysApi(SysApiList sysApiList) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        example.createCriteria()
                .andPermissionTypeEqualTo("API")
                .andPermissionIdEqualTo(sysApiList.getId());
        List<SysRolePermission> sysRolePermissions = sysRolePermissionMapper.selectByExample(example);
        if (sysRolePermissions.isEmpty()) {
            return new ArrayList<>();
        }
        List<Long> ids = new ArrayList<>();
        sysRolePermissions.forEach(sysRolePermission -> ids.add(sysRolePermission.getRoleId()));
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria()
                .andIdIn(ids);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        if (sysRoles.isEmpty()) {
            return new ArrayList<>();
        }
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for (SysRole sysRole : sysRoles
        ) {
            RoleDTO roleDTO = new RoleDTO();
            BeanUtils.copyProperties(sysRole, roleDTO);
            roleDTOList.add(roleDTO);
        }
        return roleDTOList;
    }

    /**
     * 根据用户获取菜单树
     *
     * @param user
     * @return
     */
    @Override
    public List<MenuDataItemVo> getMenuTreeByUser(User user) {
        assert systemConfig != null;
        SysMenuExample example = new SysMenuExample();
        if (systemConfig.getSuperTubeUserName().equals(user.getUserName())) {
            // 超管获取全部菜单树
            example.createCriteria()
                    .andParentIdIsNull();
            List<MenuDataItemVo> menuDataItemVos = convert(sysMenuMapper.selectByExample(example));
            settingMenuDataItemChildren(user, menuDataItemVos, null);
            return menuDataItemVos;
        } else {
            // 不是超管，那就得看权限了
            List<SysRoleVO> sysRoles = getRoleListByUser(user);
            if (sysRoles.isEmpty()) {
                return new ArrayList<>();
            }
            List<Long> menuIds = new ArrayList<>();
            for (SysRoleVO sysRoleVO : sysRoles
            ) {
                for (SysRolePermission sysRolePermission : sysRoleVO.getRolePermissions()
                ) {
                    if ("MENU".equals(sysRolePermission.getPermissionType())) {
                        menuIds.add(sysRolePermission.getPermissionId());
                    }
                }
            }
            example.createCriteria()
                    .andParentIdIsNull()
                    .andIdIn(menuIds);
            List<MenuDataItemVo> menuDataItemVos = convert(sysMenuMapper.selectByExample(example));
            settingMenuDataItemChildren(user, menuDataItemVos, menuIds);
            return menuDataItemVos;
        }
    }

    /**
     * 获取系统菜单列表
     * 超管和安全管理员可以获取全部，用分配权限，其他人只能获取到自己拥有的菜单
     *
     * @param user  登陆用户
     * @param pages 页码
     * @param rows  每页行数
     * @return
     */
    @Override
    public ListData<SysMenu> getSysMenuList(User user, String pages, String rows) {
        SysMenuExample example = new SysMenuExample();
        assert systemConfig != null;
        if (systemConfig.getSuperTubeUserName().equals(user.getUserName())) {
            // 超管查全部
            example.createCriteria();
        } else {
            // 判断角色
            SysAccountRoleExample accountRoleExample = new SysAccountRoleExample();
            accountRoleExample.createCriteria().andAccountIdEqualTo(user.getId());
            List<SysAccountRole> accountRoles = sysAccountRoleMapper.selectByExample(accountRoleExample);
            boolean securityAdmin = false;
            if (!accountRoles.isEmpty()) {
                for (SysAccountRole accountRole : accountRoles
                ) {
                    if (accountRole.getRoleId() == 2) {
                        securityAdmin = true;
                        break;
                    }
                }
            }
            if (securityAdmin) {
                // 系统内置的安全管理员，可以查全部，用分配权限
                example.createCriteria();
            } else {
                // 只能查到自己拥有的菜单列表
                if (accountRoles.isEmpty()) {
                    return new ListData<>();
                }
                List<Long> ids = new ArrayList<>();
                accountRoles.forEach(sysAccountRole -> ids.add(sysAccountRole.getRoleId()));
                SysRolePermissionExample rolePermissionExample = new SysRolePermissionExample();
                rolePermissionExample.createCriteria()
                        .andPermissionTypeEqualTo("MENU")
                        .andRoleIdIn(ids);
                List<SysRolePermission> sysRolePermissions = sysRolePermissionMapper.selectByExample(rolePermissionExample);
                if (sysRolePermissions.isEmpty()) {
                    return new ListData<>();
                }
                List<Long> menuIds = new ArrayList<>();
                sysRolePermissions.forEach(sysRolePermission -> menuIds.add(sysRolePermission.getPermissionId()));
                example.createCriteria()
                        .andParentIdIsNull()
                        .andIdIn(menuIds);
            }
        }
        Page<SysMenu> page = PageHelper.startPage(NumberUtils.parseInt(pages, 1),
                NumberUtils.parseInt(rows, 10));
        sysMenuMapper.selectByExample(example);
        ListData<SysMenu> listData = new ListData<>(page);
        listData.setData(page.getResult());
        return listData;
    }

    /**
     * 添加系统菜单
     *
     * @param user    登陆用户
     * @param sysMenu 系统菜单
     */
    @Override
    public void addSysMenu(User user, SysMenu sysMenu) {
        sysMenu.setId(null);
        if (sysMenu.getParentId() == null || sysMenu.getParentId() == -1) {
            sysMenu.setParentId(null);
        } else {
            if (sysMenuMapper.selectByPrimaryKey(sysMenu.getParentId()) == null) {
                throw new BusinessException("ParentId 不正确");
            }
        }
        sysMenuMapper.insertSelective(sysMenu);
    }

    /**
     * 修改系统菜单
     *
     * @param user    登陆用户
     * @param sysMenu 系统菜单
     */
    @Override
    public void updateSysMenu(User user, SysMenu sysMenu) {
        SysMenu sysMenuOld = sysMenuMapper.selectByPrimaryKey(sysMenu.getId());
        if (sysMenuOld == null) {
            throw new BusinessException("根据ID未查询到菜单数据");
        }
        sysMenuOld.setMenuIcon(sysMenu.getMenuIcon());
        sysMenuOld.setMenuLink(sysMenu.getMenuLink());
        sysMenuOld.setMenuText(sysMenu.getMenuText());
        sysMenuOld.setNewWindow(sysMenu.getNewWindow());
        sysMenuOld.setOrderNumber(sysMenu.getOrderNumber());
        if (sysMenu.getParentId() != null) {
            if (sysMenu.getParentId() == -1) {
                sysMenuOld.setParentId(null);
            } else if (!sysMenu.getParentId().equals(sysMenuOld.getParentId())) {
                // 修改了 ParentId，进行校验
                if (sysMenuMapper.selectByPrimaryKey(sysMenu.getParentId()) == null) {
                    throw new BusinessException("ParentId 不正确");
                }
            }
        }
        sysMenuOld.setParentId(sysMenu.getParentId());
        sysMenuMapper.updateByPrimaryKey(sysMenuOld);
    }

    /**
     * 删除系统菜单
     * 下面的子菜单不会被删除，而是会断开树形链接
     *
     * @param user 登陆用户
     * @param id   删除的ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSysMenu(User user, Long id) {
        sysMenuMapper.deleteByPrimaryKey(id);
        // 删除与菜单关联的表
        SysRolePermissionExample example = new SysRolePermissionExample();
        example.createCriteria()
                .andPermissionTypeEqualTo("MENU")
                .andPermissionIdEqualTo(id);
        sysRolePermissionMapper.deleteByExample(example);
    }

    /**
     * 查询系统运行状态
     *
     * @return
     */
    @Override
    public SystemOperationStatusEnum querySystemOperationStatus() {
        String status = this.querySystemSetting(SYSTEM_OPERATION_STATUS_KEY)
                .orElse(SystemOperationStatusEnum.OPENED.toString());
        return SystemOperationStatusEnum.valueOf(status);
    }

    /**
     * 设置系统运行状态
     *
     * @param systemOperationStatusEnum 系统运行状态
     */
    @Override
    public void settingSystemOperationStatus(SystemOperationStatusEnum systemOperationStatusEnum) {
        this.settingSystemSetting(SYSTEM_OPERATION_STATUS_KEY, systemOperationStatusEnum.toString());
    }

    /**
     * 系统操作日志审计
     * 涉密三员要求：
     * 系统管理员：看不到另外两员的行为日志（排除另外两员的操作日志）
     * 安全保密管理员：查看用户行为日志和安全审计员的行为日志（排除系统管理员）
     * 安全审计员：查看另外两员的行为日志
     *
     * @param user          登陆的用户
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param logLevel      日志等级
     * @param systemType    系统模块
     * @param operationType 操作类型
     * @param userName      用户名
     * @param ip            IP地址
     * @param pages         页码
     * @param rows          每页容量
     * @return
     */
    @Override
    public ListData<SysLogsWithBLOBs> querySystemLog(User user, Date startTime, Date endTime, LogLevelEnum logLevel,
                                                     SystemTypeEnum systemType, OperationTypeEnum operationType,
                                                     String userName, String ip, String pages, String rows) {
        // 判断角色
        SysAccountRoleExample accountRoleExample = new SysAccountRoleExample();
        accountRoleExample.createCriteria().andAccountIdEqualTo(user.getId());
        List<SysAccountRole> accountRoles = sysAccountRoleMapper.selectByExample(accountRoleExample);
        boolean securityAdmin = false, administrator = false, safetyAuditor = false;
        if (!accountRoles.isEmpty()) {
            for (SysAccountRole accountRole : accountRoles
            ) {
                if (accountRole.getRoleId() == 1) {
                    administrator = true;
                }
                if (accountRole.getRoleId() == 2) {
                    securityAdmin = true;
                }
                if (accountRole.getRoleId() == 3) {
                    safetyAuditor = true;
                }
            }
        }
        SysLogsExample example = new SysLogsExample();
        example.setOrderByClause("log_time DESC");
        SysLogsExample.Criteria criteria = example.createCriteria();
        if (administrator) {
            // 系统管理员：看不到另外两员的行为日志（排除另外两员的操作日志）
            criteria.andUserNameNotIn(new ArrayList<String>() {
                private static final long serialVersionUID = 3556126187980141694L;

                {
                    this.add("sso");
                    this.add("saa");
                }
            });
        } else if (securityAdmin) {
            // 安全保密管理员：查看用户行为日志和安全审计员的行为日志（排除系统管理员）
            criteria.andUserNameNotIn(new ArrayList<String>() {
                private static final long serialVersionUID = 3556126187980141694L;

                {
                    this.add("sysa");
                }
            });
        }
        if (safetyAuditor) {
            // 安全审计员：查看另外两员的行为日志
            criteria.andUserNameIn(new ArrayList<String>() {
                private static final long serialVersionUID = 3556126187980141694L;

                {
                    this.add("sysa");
                    this.add("sso");
                }
            });
        } else {
            // 其他，排除内置三员的日志
            criteria.andUserNameNotIn(new ArrayList<String>() {
                private static final long serialVersionUID = 3556126187980141694L;

                {
                    this.add("sysa");
                    this.add("sso");
                    this.add("saa");
                }
            });
        }
        if (startTime != null) {
            criteria.andLogTimeGreaterThanOrEqualTo(startTime);
        }
        if (endTime != null) {
            criteria.andLogTimeLessThanOrEqualTo(endTime);
        }
        if (logLevel != null) {
            criteria.andLogLevelEqualTo(logLevel.toString());
        }
        if (systemType != null) {
            criteria.andLogModuleEqualTo(systemType.toString());
        }
        if (operationType != null) {
            criteria.andLogTypeEqualTo(operationType.toString());
        }
        if (userName != null) {
            criteria.andUserNameLike("%" + userName + "%");
        }
        if (ip != null) {
            criteria.andRequIpLike("%" + ip + "%");
        }
        Page<SysLogsWithBLOBs> page =
                PageHelper.startPage(NumberUtils.parseInt(pages, 1), NumberUtils.parseInt(rows, 10));
        sysLogsMapper.selectByExampleWithBLOBs(example);
        ListData<SysLogsWithBLOBs> sysLogsListData = new ListData<>(page);
        sysLogsListData.setData(page.getResult());
        return sysLogsListData;
    }

    /**
     * 递归查询子菜单
     *
     * @param user            当前用户
     * @param menuDataItemVos 父菜单们
     * @param menuIds         用户拥有的菜单ID
     * @return
     */
    private List<MenuDataItemVo> settingMenuDataItemChildren(User user,
                                                             List<MenuDataItemVo> menuDataItemVos,
                                                             List<Long> menuIds) {
        if (user == null || menuDataItemVos == null || menuDataItemVos.isEmpty()) {
            return null;
        }
        for (MenuDataItemVo menuDataItemVo : menuDataItemVos
        ) {
            SysMenuExample example = new SysMenuExample();
            if (systemConfig.getSuperTubeUserName().equals(user.getUserName())) {
                // 超管获取全部菜单树
                example.createCriteria()
                        .andParentIdEqualTo(menuDataItemVo.getId());
            } else {
                // 不是超管，看权限了
                example.createCriteria()
                        .andParentIdEqualTo(menuDataItemVo.getId())
                        .andIdIn(menuIds);
            }
            menuDataItemVo.setChildren(convert(sysMenuMapper.selectByExample(example)));
        }
        return menuDataItemVos;
    }

    private List<MenuDataItemVo> convert(List<SysMenu> sysMenus) {
        if (sysMenus == null || sysMenus.isEmpty()) {
            return null;
        }
        List<MenuDataItemVo> menuDataItemVos = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus
        ) {
            MenuDataItemVo menuDataItemVo = new MenuDataItemVo();
            menuDataItemVo.setId(sysMenu.getId());
            menuDataItemVo.setHideInMenu(false);
            menuDataItemVo.setHideChildrenInMenu(false);
            menuDataItemVo.setIcon(sysMenu.getMenuIcon());
            menuDataItemVo.setName(sysMenu.getMenuText());
            menuDataItemVo.setPath(sysMenu.getMenuLink());
            menuDataItemVos.add(menuDataItemVo);
        }
        return menuDataItemVos;
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
        assert systemConfig != null;
        for (int i = systemConfig.getFooterSmallMenu().size() - 1; i >= 0; i--) {
            LinkTree linkTree = LinkTree.builder()
                    .href(systemConfig.getFooterSmallMenu().get(i).split(split)[1])
                    .text(systemConfig.getFooterSmallMenu().get(i).split(split)[0])
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

    /**
     * 查询系统设置
     *
     * @param key 键值
     * @return
     */
    private Optional<String> querySystemSetting(String key) {
        SysSettingExample example = new SysSettingExample();
        example.createCriteria()
                .andSettingKeyEqualTo(key);
        List<SysSetting> sysSettings = sysSettingMapper.selectByExample(example);
        if (sysSettings.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(ListUtils.getOne(sysSettings).getSettingValue());
    }

    /**
     * 设置系统设置
     *
     * @param key   键值对
     * @param value 键值对
     */
    private void settingSystemSetting(String key, String value) {
        SysSetting sysSetting = new SysSetting();
        sysSetting.setSettingValue(value);
        if (this.querySystemSetting(key).isPresent()) {
            SysSettingExample example = new SysSettingExample();
            example.createCriteria()
                    .andSettingKeyEqualTo(key);
            sysSettingMapper.updateByExampleSelective(sysSetting, example);
        } else {
            sysSetting.setSettingKey(key);
            sysSettingMapper.insertSelective(sysSetting);
        }

    }
}
