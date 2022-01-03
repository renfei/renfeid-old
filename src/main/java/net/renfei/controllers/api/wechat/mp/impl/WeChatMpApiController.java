package net.renfei.controllers.api.wechat.mp.impl;

import jdk.nashorn.internal.ir.annotations.Ignore;
import net.renfei.controllers.BaseController;
import net.renfei.controllers.api.wechat.mp.WeChatMpApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信公众号API
 *
 * @author renfei
 */
@Ignore
@RestController
@RequestMapping("/api/wechat/mp")
public class WeChatMpApiController extends BaseController implements WeChatMpApi {
}
