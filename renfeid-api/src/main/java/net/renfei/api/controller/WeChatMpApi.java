package net.renfei.api.controller;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信公众号API对接
 *
 * @author renfei
 */
@Ignore
@RequestMapping("/api/wechat/mp")
public interface WeChatMpApi {
    @RequestMapping("")
    void doPost(HttpServletResponse response) throws IOException;
}
