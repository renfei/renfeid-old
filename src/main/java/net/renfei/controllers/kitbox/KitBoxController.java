package net.renfei.controllers.kitbox;

import lombok.extern.slf4j.Slf4j;
import net.renfei.controllers.BaseController;
import net.renfei.domain.comment.Comment;
import net.renfei.domain.kitbox.KitBoxTypeEnum;
import net.renfei.exception.IP2LocationException;
import net.renfei.ip2location.IPResult;
import net.renfei.model.kitbox.KitboxPageView;
import net.renfei.services.IP2LocationService;
import net.renfei.services.KitBoxService;
import net.renfei.utils.IpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

import static net.renfei.services.kitbox.KitBoxServiceImpl.*;

/**
 * 工具箱栏目
 *
 * @author renfei
 */
@Slf4j
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
    public RedirectView getKitBoxDir() {
        assert SYSTEM_CONFIG != null;
        RedirectView redirectView = new RedirectView(SYSTEM_CONFIG.getSiteDomainName() + "/kitbox/");
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
        pageView.getPageHead().setTitle(KitBoxTypeEnum.NETWORK_IP.getTitle() + "开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
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
    public ModelAndView getDigTrace(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.NETWORK_DIGTRACE.getTitle() + "开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
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

    @RequestMapping("dnsqps")
    public ModelAndView getDnsQps(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.NETWORK_DNSQPSE.getTitle() + "开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
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

    @RequestMapping("whois")
    public ModelAndView getWhois(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.NETWORK_WHOIS.getTitle() + "开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
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

    @RequestMapping("getmyip")
    public ModelAndView getMyIp(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.NETWORK_GETMYIP.getTitle() + "开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
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

    @RequestMapping({"uuid", "guid", "UUID", "GUID"})
    public ModelAndView getUUID(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.DEVELOP_UUID.getTitle() + "开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
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

    @RequestMapping({"freemarkerTest", "FtlTest"})
    public ModelAndView freemarkerTest(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.DEVELOP_FREEMARKER_TEST.getTitle() + "开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("FreeMarker(FTL)在线测试工具");
        pageView.getPageHead().setKeywords("FreeMarker,ftl,在线,测试,工具");
        mv.setViewName("kitbox/freemarkerTest");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, DEVELOPMENT_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.DEVELOP_FREEMARKER_TEST);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.DEVELOP_FREEMARKER_TEST.getId());
        return mv;
    }

    @RequestMapping("strHumpLineConvert")
    public ModelAndView strHumpLineConvert(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.DEVELOP_STR_HUMP_LINE_CONVERT.getTitle() + "开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
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

    @RequestMapping("byteUnitConversion")
    public ModelAndView byteUnitConversion(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.DEVELOP_BYTE_UNIT_CONVERSION.getTitle() + "开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
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

    @RequestMapping("ueditor")
    public ModelAndView ueditor(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.DEVELOP_UEDITOR.getTitle() + "开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
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

    @RequestMapping("wordIkAnalyze")
    public ModelAndView wordIkAnalyze(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.DEVELOP_WORD_IK_ANALYZE.getTitle() + "开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
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

    @RequestMapping("portNumberList")
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
        setKitBoxMenus(mv, DEVELOPMENT_TOOL);
        return mv;
    }

    @ResponseBody
    @RequestMapping("ueditor/controller")
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
