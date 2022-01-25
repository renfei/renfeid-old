package net.renfei.controllers.other;

import net.renfei.annotation.OperationLog;
import net.renfei.controllers.BaseController;
import net.renfei.model.HomePageView;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.services.QrCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 其他杂七杂八的功能
 *
 * @author renfei
 */
@Controller
@RequestMapping("/other")
public class OtherController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(OtherController.class);

    /**
     * 链接重定向（注意地址需要Base64编码以后传入）
     *
     * @param url URL需要Base64编码
     * @param mv
     * @return
     */
    @RequestMapping("urlredirect")
    public ModelAndView urlredirect(String url, ModelAndView mv) {
        if (ObjectUtils.isEmpty(url) || url.isEmpty()) {
            return new ModelAndView("redirect:/");
        }
        assert systemConfig != null;
        HomePageView<String> pageView = buildPageView(HomePageView.class, null);
        try {
            byte[] asBytes = Base64.getDecoder().decode(url);
            mv.addObject("siteName", systemConfig.getSiteName());
            String uri = new String(asBytes, StandardCharsets.UTF_8);
            if (!uri.startsWith("http://") && !uri.startsWith("https://")) {
                uri = "http://" + uri;
            }
            try{
                URL urlObj = new URL(uri);
                String host = urlObj.getHost();
                if (host.endsWith(systemConfig.getBaseDomainName())) {
                    return new ModelAndView("redirect:" + uri);
                }
            }catch (Exception exception){
            }
            mv.addObject("url", uri);
            mv.addObject("pageView", pageView);
            mv.setViewName("other/urlredirect");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("redirect:/");
        }
        return mv;
    }

    @GetMapping("qrcode")
    @OperationLog(module = SystemTypeEnum.API, desc = "访问二维码生成接口")
    public void qrcode(@RequestParam("content") String content,
                       @RequestParam(value = "size", required = false) String size,
                       HttpServletResponse response) throws Exception {
        ServletOutputStream stream = null;
        QrCodeService qrCodeService;
        if (!ObjectUtils.isEmpty(size)) {
            int qrCodeSize;
            try {
                qrCodeSize = Integer.parseInt(size);
                qrCodeService = new QrCodeService(qrCodeSize);
            } catch (NumberFormatException nfe) {
                qrCodeService = new QrCodeService();
            }
        } else {
            qrCodeService = new QrCodeService();
        }
        try {
            response.setHeader("Content-Type", "image/jpg");
            stream = response.getOutputStream();
            //使用工具类生成二维码
            qrCodeService.encode(content, stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
}
