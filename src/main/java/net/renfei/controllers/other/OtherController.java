package net.renfei.controllers.other;

import net.renfei.annotation.OperationLog;
import net.renfei.controllers.BaseController;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.services.QrCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 其他杂七杂八的功能
 *
 * @author renfei
 */
@Controller
@RequestMapping("/other")
public class OtherController extends BaseController {
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
