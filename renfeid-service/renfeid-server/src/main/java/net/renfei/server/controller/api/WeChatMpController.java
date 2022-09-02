/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.server.controller.api;

import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.pro.wechat.mp.service.WeChatMessageService;
import net.renfei.server.controller.AbstractController;
import org.springframework.web.bind.annotation.*;

/**
 * 微信公众号接口
 *
 * @author renfei
 */
@RestController
public class WeChatMpController extends AbstractController {
    private final WeChatMessageService weChatMessageService;

    public WeChatMpController(WeChatMessageService weChatMessageService) {
        this.weChatMessageService = weChatMessageService;
    }

    /**
     * 微信公众号认证接口
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echoStr
     * @return
     */
    @GetMapping("/api/wechat/mp")
    public String auth(@RequestParam(name = "signature", required = false) String signature,
                       @RequestParam(name = "timestamp", required = false) String timestamp,
                       @RequestParam(name = "nonce", required = false) String nonce,
                       @RequestParam(name = "echostr", required = false) String echoStr) {
        return weChatMessageService.auth(signature, timestamp, nonce, echoStr);
    }

    /**
     * 微信订阅号消息处理接口
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param openid
     * @param encType
     * @param msgSignature
     * @param requestBody
     * @return
     */
    @PostMapping("/api/wechat/mp")
    @OperationLog(module = SystemTypeEnum.WECHAT_MP,desc = "微信消息")
    public String post(@RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam("openid") String openid,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature,
                       @RequestBody String requestBody) {
        return weChatMessageService.post(requestBody, signature, timestamp, nonce, openid, encType, msgSignature);
    }
}
