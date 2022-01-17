package net.renfei.controllers.kitbox;

import net.renfei.annotation.OperationLog;
import net.renfei.controllers.BaseController;
import net.renfei.domain.comment.Comment;
import net.renfei.domain.kitbox.KitBoxTypeEnum;
import net.renfei.exception.IP2LocationException;
import net.renfei.ip2location.IPResult;
import net.renfei.model.APIResult;
import net.renfei.model.StateCodeEnum;
import net.renfei.model.kitbox.KitboxPageView;
import net.renfei.model.kitbox.PlistVO;
import net.renfei.model.kitbox.ShortUrlVO;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.repositories.model.KitboxShortUrl;
import net.renfei.services.IP2LocationService;
import net.renfei.services.KitBoxService;
import net.renfei.utils.IpUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static net.renfei.services.kitbox.KitBoxServiceImpl.*;

/**
 * 工具箱栏目
 *
 * @author renfei
 */
@Controller
@RequestMapping("/kitbox")
public class KitBoxController extends BaseController {
    private final KitBoxService kitBoxService;
    private final IP2LocationService ip2LocationService;

    public KitBoxController(KitBoxService kitBoxService, IP2LocationService ip2LocationService) {
        this.kitBoxService = kitBoxService;
        this.ip2LocationService = ip2LocationService;
    }

    @RequestMapping("")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问开发者工具箱首页")
    public ModelAndView kitbox(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<Object> pageView = buildPageView(KitboxPageView.class, null);
        pageView.getPageHead().setTitle("开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("免费的开发者与站长工具箱小工具，包含网络工具、加解密工具、测试工具等，工欲善其事，必先利其器。");
        pageView.getPageHead().setKeywords("开发,开发者,工具,工具箱");
        mv.addObject("KitBoxMenus", kitBoxService.getKitBoxMenus());
        mv.addObject("pageView", pageView);
        mv.setViewName("kitbox/index");
        return mv;
    }

    /**
     * 博客列表错误地址重定向
     *
     * @return
     */
    @RequestMapping({
            "index.html", "index.htm", "index.xhtml", "index.shtml",
            "index.php", "index.asp", "index.aspx", "index.do",
            "index.jsp", "index.dll", "index.php3", "index.pl",
            "index.cgi",
            "default.html", "default.htm", "default.xhtml", "default.shtml",
            "default.php", "default.asp", "default.aspx", "default.do",
            "default.jsp", "default.dll", "default.php3", "default.pl",
            "default.cgi"
    })
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问开发者工具箱首页Index.html")
    public RedirectView getKitBoxDir() {
        assert SYSTEM_CONFIG != null;
        RedirectView redirectView = new RedirectView(SYSTEM_CONFIG.getSiteDomainName() + "/kitbox");
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }

    /**
     * IP地址信息查询工具
     *
     * @param mv
     * @return
     */
    @RequestMapping("ip")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问IP地址信息查询工具")
    public ModelAndView ip(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        String ip = IpUtils.getIpAddress(request);
        IPResult ipInfoDTO = null;
        try {
            ipInfoDTO = ip2LocationService.ipQuery(ip);
        } catch (IP2LocationException e) {
            e.printStackTrace();
        }
        mv.addObject("myip", ip);
        KitboxPageView<IPResult> pageView = buildPageView(KitboxPageView.class, ipInfoDTO);
        pageView.getPageHead().setTitle(KitBoxTypeEnum.NETWORK_IP.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("免费的开发者与站长工具箱小工具，包含网络工具、加解密工具、测试工具等，工欲善其事，必先利其器。");
        pageView.getPageHead().setKeywords("IP,地址,信息,查询,工具,地理,位置");
        mv.setViewName("kitbox/ipinfo");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, NETWORK_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.NETWORK_IP);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.NETWORK_IP.getId());
        return mv;
    }

    /**
     * Dig+trace命令检测DNS状态工具
     *
     * @param mv
     * @return
     */
    @RequestMapping("digtrace")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问Dig+trace命令检测DNS状态工具")
    public ModelAndView getDigTrace(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.NETWORK_DIGTRACE.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("域名解析信息查询工具，开放服务接口实现dig+trace域名解析过程查询。");
        pageView.getPageHead().setKeywords("域名,解析,信息,查询,工具,Dig,trace,DNS");
        mv.setViewName("kitbox/digtrace");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, NETWORK_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.NETWORK_DIGTRACE);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.NETWORK_DIGTRACE.getId());
        return mv;
    }

    /**
     * 域名解析QPS压力测试工具
     *
     * @param mv
     * @return
     */
    @RequestMapping("dnsqps")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问域名解析QPS压力测试工具")
    public ModelAndView getDnsQps(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.NETWORK_DNSQPSE.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("域名解析QPS压力测试工具，仅限测试域名DNS抗压能力，请勿用于发动DNS攻击");
        pageView.getPageHead().setKeywords("域名,解析,QPS,压力,测试,工具,DNS");
        mv.setViewName("kitbox/dnsqps");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, NETWORK_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.NETWORK_DNSQPSE);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.NETWORK_DNSQPSE.getId());
        return mv;
    }

    /**
     * 域名Whois信息查询工具
     *
     * @param mv
     * @return
     */
    @RequestMapping("whois")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问域名Whois信息查询工具")
    public ModelAndView getWhois(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.NETWORK_WHOIS.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("域名Whois信息查询工具，查询域名是否已经被注册，以及注册域名的详细信息的数据库（如域名所有人、域名注册商、域名注册日期和过期日期等）。通过域名Whois服务器查询，可以查询域名归属者联系方式，以及注册和到期时间。");
        pageView.getPageHead().setKeywords("域名,whois,信息,查询,工具");
        mv.setViewName("kitbox/whois");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, NETWORK_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.NETWORK_WHOIS);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.NETWORK_WHOIS.getId());
        return mv;
    }

    /**
     * 公网IP获取工具
     *
     * @param mv
     * @return
     */
    @RequestMapping("getmyip")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问公网IP获取工具")
    public ModelAndView getMyIp(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.NETWORK_GETMYIP.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("公网IP获取工具支持Linux、Windows、API");
        pageView.getPageHead().setKeywords("IP,公网,出口,地址,工具");
        mv.setViewName("kitbox/getmyip");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, NETWORK_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.NETWORK_GETMYIP);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.NETWORK_GETMYIP.getId());
        return mv;
    }

    /**
     * 客户端环境获取工具
     *
     * @param mv
     * @return
     */
    @RequestMapping("clienv")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问客户端环境获取工具")
    public ModelAndView getCliEnvPage(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.NETWORK_CLIENV.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("客户端环境获取工具，获取操作系统与浏览器版本环境信息");
        pageView.getPageHead().setKeywords("客户端,环境,系统,浏览器,版本,探针,获取client,env,environment");
        mv.setViewName("kitbox/clienv");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, NETWORK_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.NETWORK_CLIENV);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.NETWORK_CLIENV.getId());
        return mv;
    }

    /**
     * 在线批量生成 UUID/GUID 工具
     *
     * @param mv
     * @return
     */
    @RequestMapping({"uuid", "guid", "UUID", "GUID"})
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问在线批量生成 UUID/GUID 工具")
    public ModelAndView getUUID(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.DEVELOP_UUID.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("在线批量生成 UUID/GUID 工具");
        pageView.getPageHead().setKeywords("UUID,GUID,在线,批量,生成,工具");
        mv.setViewName("kitbox/uuid");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, DEVELOPMENT_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.DEVELOP_UUID);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.DEVELOP_UUID.getId());
        return mv;
    }

    /**
     * FreeMarker(FTL)在线测试工具
     *
     * @param mv
     * @return
     */
    @RequestMapping({"freemarkerTest", "FtlTest"})
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问FreeMarker(FTL)在线测试工具")
    public ModelAndView freemarkerTest(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.DEVELOP_FREEMARKER_TEST.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("FreeMarker(FTL)在线测试工具");
        pageView.getPageHead().setKeywords("FreeMarker,ftl,在线,测试,工具");
        mv.setViewName("kitbox/freemarkerTest");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, DEVELOPMENT_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.DEVELOP_FREEMARKER_TEST);
        mv.addObject("version", freemarker.template.Configuration.getVersion());
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.DEVELOP_FREEMARKER_TEST.getId());
        return mv;
    }

    /**
     * 下划线(Line)与驼峰(Hump)命名风格的相互转换工具
     *
     * @param mv
     * @return
     */
    @RequestMapping("strHumpLineConvert")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问下划线(Line)与驼峰(Hump)命名风格的相互转换工具")
    public ModelAndView strHumpLineConvert(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.DEVELOP_STR_HUMP_LINE_CONVERT.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("下划线(Line)与驼峰(Hump)命名风格的相互转换工具，例如：test_test/testTest的相互转换");
        pageView.getPageHead().setKeywords("驼峰,下划线,命名,风格,转换,互转,在线,工具,Hump,line");
        mv.setViewName("kitbox/strHumpLineConvert");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, DEVELOPMENT_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.DEVELOP_STR_HUMP_LINE_CONVERT);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.DEVELOP_STR_HUMP_LINE_CONVERT.getId());
        return mv;
    }

    /**
     * 计算机字节(Byte)单位之间的转换换算工具
     *
     * @param mv
     * @return
     */
    @RequestMapping("byteUnitConversion")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问计算机字节(Byte)单位之间的转换换算工具")
    public ModelAndView byteUnitConversion(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.DEVELOP_BYTE_UNIT_CONVERSION.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("计算机字节(Byte)单位之间的转换换算工具：bit、Byte、KB、MB、GB、TB、PB、EB之间的转换计算工具");
        pageView.getPageHead().setKeywords("字节,单位,比特,转换,换算,工具,bit,Byte,KB,MB,GB,TB,PB,EB");
        mv.setViewName("kitbox/byteUnitConversion");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, DEVELOPMENT_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.DEVELOP_BYTE_UNIT_CONVERSION);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.DEVELOP_BYTE_UNIT_CONVERSION.getId());
        return mv;
    }

    /**
     * UEditor工具
     *
     * @param mv
     * @return
     */
    @RequestMapping("ueditor")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问UEditor工具")
    public ModelAndView ueditor(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.DEVELOP_UEDITOR.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("UEditor是由百度web前端研发部开发所见即所得富文本web编辑器，具有轻量，可定制，注重用户体验等特点，开源基于MIT协议，允许自由使用和修改代码。");
        pageView.getPageHead().setKeywords("ueditor,百度,在线,测试,demo,富文本,编辑器");
        mv.setViewName("kitbox/ueditor");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, DEVELOPMENT_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.DEVELOP_UEDITOR);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.DEVELOP_UEDITOR.getId());
        return mv;
    }

    /**
     * 在线分词工具
     *
     * @param mv
     * @return
     */
    @RequestMapping("wordIkAnalyze")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问在线分词工具")
    public ModelAndView wordIkAnalyze(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.DEVELOP_WORD_IK_ANALYZE.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("在线分词工具与API接口(IK Analyze)，提供免费的在线文字分词与切词API接口服务，基于IKAnalyzer同时提供了对Lucene的默认优化实现。");
        pageView.getPageHead().setKeywords("分词,切词,中文,ik,analyze,api,接口,在线");
        mv.setViewName("kitbox/wordIkAnalyze");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, DEVELOPMENT_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.DEVELOP_WORD_IK_ANALYZE);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.DEVELOP_WORD_IK_ANALYZE.getId());
        return mv;
    }

    /**
     * 计算机 TCP/UDP 端口号注册列表大全
     *
     * @param mv
     * @return
     */
    @RequestMapping("portNumberList")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问计算机 TCP/UDP 端口号注册列表大全工具")
    public ModelAndView portNumberList(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle("计算机 TCP/UDP 端口号注册列表大全 - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("计算机之间依照互联网传输层TCP/IP协议的协议通信，不同的协议都对应不同的端口。并且，利用数据报文的UDP也不一定和TCP采用相同的端口号码。");
        pageView.getPageHead().setKeywords("tcp,udp,port,端口,计算机,注册,列表,大全");
        mv.setViewName("kitbox/portNumberList");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, DEVELOPMENT_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.DEVELOP_PORT_NUMBER_LIST);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.DEVELOP_PORT_NUMBER_LIST.getId());
        return mv;
    }

    /**
     * 随机密码生成工具
     *
     * @param mv
     * @return
     */
    @RequestMapping("randomPassword")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问随机密码生成工具")
    public ModelAndView randomPassword(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.ENCRYPTION_RANDOM_PASSWORD.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("用户可根据自身需要选择生成密码所包含的字符以及密码长度，随机密码一键生成，简单易用，生成安全、随机的密码以保证网络账号的安全。");
        pageView.getPageHead().setKeywords("随机,密码,生成");
        mv.setViewName("kitbox/randomPassword");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, ENCRYPTION_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.ENCRYPTION_RANDOM_PASSWORD);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.ENCRYPTION_RANDOM_PASSWORD.getId());
        return mv;
    }

    /**
     * MD5在线加密工具
     *
     * @param mv
     * @return
     */
    @RequestMapping("md5")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问MD5在线加密工具")
    public ModelAndView md5Tools(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.ENCRYPTION_MD5.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("MD5在线加密工具，对字符串进行MD5计算得出MD5加密字符串");
        pageView.getPageHead().setKeywords("MD5,在线,加密,解密,字符串");
        mv.setViewName("kitbox/md5");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, ENCRYPTION_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.ENCRYPTION_MD5);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.ENCRYPTION_MD5.getId());
        return mv;
    }

    /**
     * SHA-1 散列函数加密算法
     *
     * @param mv
     * @return
     */
    @RequestMapping("sha1")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问SHA-1散列函数加密算法工具")
    public ModelAndView sha1Tools(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.ENCRYPTION_SHA1.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("SHA-1 散列函数加密算法输出的散列值为40位十六进制数字串，可用于验证信息的一致性，防止被篡改。本页面的 SHA-1 在线加密工具可对字符串进行 SHA-1 加密，并可转换散列值中字母的大小写。");
        pageView.getPageHead().setKeywords("SHA-1,在线,加密,解密,字符串");
        mv.setViewName("kitbox/sha1");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, ENCRYPTION_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.ENCRYPTION_SHA1);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.ENCRYPTION_SHA1.getId());
        return mv;
    }

    /**
     * 访问SHA-256散列函数加密算法工具
     *
     * @param mv
     * @return
     */
    @RequestMapping("sha256")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问SHA-256散列函数加密算法工具")
    public ModelAndView sha256Tools(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.ENCRYPTION_SHA256.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("SHA-256 散列函数加密算法输出的散列值可用于验证信息的一致性，防止被篡改。本页面的 SHA-256 在线加密工具可对字符串进行 SHA-256 加密，并可转换散列值中字母的大小写。");
        pageView.getPageHead().setKeywords("SHA-256,在线,加密,解密,字符串");
        mv.setViewName("kitbox/sha256");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, ENCRYPTION_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.ENCRYPTION_SHA256);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.ENCRYPTION_SHA256.getId());
        return mv;
    }

    /**
     * 访问SHA-512散列函数加密算法工具
     *
     * @param mv
     * @return
     */
    @RequestMapping("sha512")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问SHA-512散列函数加密算法工具")
    public ModelAndView sha512Tools(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.ENCRYPTION_SHA512.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("SHA-512 散列函数加密算法输出的散列值可用于验证信息的一致性，防止被篡改。本页面的 SHA-256 在线加密工具可对字符串进行 SHA-256 加密，并可转换散列值中字母的大小写。");
        pageView.getPageHead().setKeywords("SHA-256,在线,加密,解密,字符串");
        mv.setViewName("kitbox/sha512");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, ENCRYPTION_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.ENCRYPTION_SHA512);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.ENCRYPTION_SHA512.getId());
        return mv;
    }

    /**
     * URL16进制加密
     *
     * @param mv
     * @return
     */
    @RequestMapping("url16")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问URL16进制加密")
    public ModelAndView url16Tools(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.ENCRYPTION_URL16.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("URL编码形式表示的ASCII字符(十六进制格式)。把URL网址转换成16进制代码形式,加密后可直接复制到地址栏访问。");
        pageView.getPageHead().setKeywords("URL,网址,加密,16进制,hex");
        mv.setViewName("kitbox/url16");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, ENCRYPTION_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.ENCRYPTION_URL16);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.ENCRYPTION_URL16.getId());
        return mv;
    }

    /**
     * 二维码在线生成工具
     *
     * @param mv
     * @return
     */
    @RequestMapping({"qrcode", "QRCode"})
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问二维码在线生成工具")
    public ModelAndView qrCode(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.OTHER_QRCODE.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("二维码在线生成工具");
        pageView.getPageHead().setKeywords("二维码,qrcode,在线,生成,工具");
        mv.setViewName("kitbox/qrcode");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, OTHER_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.OTHER_QRCODE);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.OTHER_QRCODE.getId());
        return mv;
    }

    /**
     * 苹果 iOS Plist 文件在线生成制作工具
     *
     * @param mv
     * @return
     */
    @GetMapping("plist")
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问苹果iOSPlist文件在线生成制作工具")
    public ModelAndView plist(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle("苹果 iOS Plist 文件在线生成制作工具 - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("苹果 iOS Plist 文件在线生成制作工具，在服务器上部署 Plist 文件，用户即可通过自己的服务器下载 IPA 安装文件");
        pageView.getPageHead().setKeywords("苹果,iOS,Plist,文件,在线,生成,制作,工具");
        mv.setViewName("kitbox/plist");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, DEVELOPMENT_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.DEVELOP_PLIST);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.DEVELOP_PLIST.getId());
        return mv;
    }

    /**
     * 苹果iOSPlist文件在线生成制作接口
     *
     * @param mv
     * @param response
     * @param plistVO
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping("plist")
    @OperationLog(module = SystemTypeEnum.API, desc = "访问苹果iOSPlist文件在线生成制作接口")
    public ModelAndView plistDo(ModelAndView mv, HttpServletResponse response, PlistVO plistVO) throws UnsupportedEncodingException {
        response.setHeader("content-type", "application/octet-stream;charset=UTF-8");
        response.setHeader("content-disposition", "attachment; filename=" + URLEncoder.encode(plistVO.getAppname(), "UTF-8") + ".plist");
        response.setContentType("application/octet-stream");
        mv.addObject("plistVO", plistVO);
        mv.setViewName("kitbox/plist-ftl");
        return mv;
    }

    /**
     * 短网址在线生成工具
     *
     * @param mv
     * @return
     */
    @RequestMapping({"ShortUrl", "ShortURL"})
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问短网址在线生成工具")
    public ModelAndView shortUrl(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.OTHER_SHORT_URL.getTitle() + " - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("短网址在线生成工具");
        pageView.getPageHead().setKeywords("短网址,Short,Url,生成,工具");
        mv.setViewName("kitbox/shortURL");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, OTHER_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.OTHER_SHORT_URL);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.OTHER_SHORT_URL.getId());
        return mv;
    }

    /**
     * Indexing - 站长推送工具
     *
     * @param mv
     * @return
     */
    @RequestMapping({"indexing", "Indexing"})
    @OperationLog(module = SystemTypeEnum.KITBOX, desc = "访问Indexing站长推送工具")
    public ModelAndView indexing(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle("Indexing - 站长推送工具 - 开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("Indexing - 百度/必应/谷歌-站长推送工具");
        pageView.getPageHead().setKeywords("百度必应,bing,谷歌,google,推送,工具,站长");
        mv.setViewName("kitbox/indexing");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, OTHER_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.OTHER_INDEXING);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.OTHER_INDEXING.getId());
        return mv;
    }

    @ResponseBody
    @RequestMapping("ShortURL/do/{url}")
    public APIResult getShortUrl(@PathVariable("url") String url) {
        if (ObjectUtils.isEmpty(url)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("Url不合法")
                    .build();
        }
        // TODO 记录日志方便统计
        KitboxShortUrl shortUrl = kitBoxService.getShortUrl(url);
        if (shortUrl != null) {
            ShortUrlVO shortUrlVO = new ShortUrlVO();
            BeanUtils.copyProperties(shortUrl, shortUrlVO);
            shortUrlVO.setShortUrl(shortUrl.getShortUrl());
            return new APIResult(shortUrlVO);
        } else {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("短网址不存在")
                    .build();
        }
    }

    /**
     * ueditor配置接口
     *
     * @param action
     * @return
     */
    @ResponseBody
    @RequestMapping("ueditor/controller")
    @OperationLog(module = SystemTypeEnum.API, desc = "访问ueditor配置接口")
    public String ueditorConfig(@RequestParam("action") String action) {
        if ("config".equals(action)) {
            return "{" +
                    "    \"imageActionName\": \"uploadimage\", " +
                    "    \"imageFieldName\": \"upfile\", " +
                    "    \"imageMaxSize\": 2048000, " +
                    "    \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], " +
                    "    \"imageCompressEnable\": true, " +
                    "    \"imageCompressBorder\": 1600, " +
                    "    \"imageInsertAlign\": \"none\", " +
                    "    \"imageUrlPrefix\": \"\", " +
                    "    \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"scrawlActionName\": \"uploadscrawl\", " +
                    "    \"scrawlFieldName\": \"upfile\", " +
                    "    \"scrawlPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"scrawlMaxSize\": 2048000, " +
                    "    \"scrawlUrlPrefix\": \"\", " +
                    "    \"scrawlInsertAlign\": \"none\"," +
                    "    \"snapscreenActionName\": \"uploadimage\", " +
                    "    \"snapscreenPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"snapscreenUrlPrefix\": \"\", " +
                    "    \"snapscreenInsertAlign\": \"none\", " +
                    "    \"catcherLocalDomain\": [\"127.0.0.1\", \"localhost\", \"img.baidu.com\"]," +
                    "    \"catcherActionName\": \"catchimage\", " +
                    "    \"catcherFieldName\": \"source\", " +
                    "    \"catcherPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"catcherUrlPrefix\": \"\", " +
                    "    \"catcherMaxSize\": 2048000, " +
                    "    \"catcherAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], " +
                    "    \"videoActionName\": \"uploadvideo\", " +
                    "    \"videoFieldName\": \"upfile\", " +
                    "    \"videoPathFormat\": \"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"videoUrlPrefix\": \"\", " +
                    "    \"videoMaxSize\": 102400000, " +
                    "    \"videoAllowFiles\": [" +
                    "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\"," +
                    "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"], " +
                    "    \"fileActionName\": \"uploadfile\", " +
                    "    \"fileFieldName\": \"upfile\", " +
                    "    \"filePathFormat\": \"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"fileUrlPrefix\": \"\", " +
                    "    \"fileMaxSize\": 51200000, " +
                    "    \"fileAllowFiles\": [" +
                    "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"," +
                    "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\"," +
                    "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"," +
                    "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\"," +
                    "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"" +
                    "    ], " +
                    "    \"imageManagerActionName\": \"listimage\", " +
                    "    \"imageManagerListPath\": \"/ueditor/jsp/upload/image/\", " +
                    "    \"imageManagerListSize\": 20, " +
                    "    \"imageManagerUrlPrefix\": \"\", " +
                    "    \"imageManagerInsertAlign\": \"none\", " +
                    "    \"imageManagerAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], " +
                    "    \"fileManagerActionName\": \"listfile\", " +
                    "    \"fileManagerListPath\": \"/ueditor/jsp/upload/file/\", " +
                    "    \"fileManagerUrlPrefix\": \"\", " +
                    "    \"fileManagerListSize\": 20, " +
                    "    \"fileManagerAllowFiles\": [" +
                    "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"," +
                    "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\"," +
                    "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"," +
                    "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\"," +
                    "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"" +
                    "    ] " +
                    "}";
        }
        return "Unsupported action. -By RenFei.Net";
    }

    private void setKitBoxMenus(ModelAndView mv, String key) {
        mv.addObject("KitBoxMenus", kitBoxService.getKitBoxMenus(key));
    }
}
