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
package net.renfei.pro.wechat.mp.config;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.pro.wechat.mp.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static me.chanjar.weixin.common.api.WxConsts.EventType.SUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.EventType.UNSUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType.*;
import static me.chanjar.weixin.mp.constant.WxMpEventConstants.CustomerService.*;
import static me.chanjar.weixin.mp.constant.WxMpEventConstants.POI_CHECK_NOTIFY;


/**
 * 微信公众号配置
 *
 * @author renfei
 */
@Configuration
public class WeChatMpConfig {
    private final LogHandler logHandler;
    private final MsgHandler msgHandler;
    private final NullHandler nullHandler;
    private final MenuHandler menuHandler;
    private final ScanHandler scanHandler;
    private final LinkHandler linkHandler;
    private final SystemConfig systemConfig;
    private final ImageHandler imageHandler;
    private final VideoHandler videoHandler;
    private final VoiceHandler voiceHandler;
    private final LocationHandler locationHandler;
    private final KfSessionHandler kfSessionHandler;
    private final SubscribeHandler subscribeHandler;
    private final UnsubscribeHandler unsubscribeHandler;
    private final StoreCheckNotifyHandler storeCheckNotifyHandler;

    public WeChatMpConfig(LogHandler logHandler,
                          MsgHandler msgHandler,
                          NullHandler nullHandler,
                          MenuHandler menuHandler,
                          ScanHandler scanHandler,
                          LinkHandler linkHandler,
                          SystemConfig systemConfig,
                          ImageHandler imageHandler,
                          VideoHandler videoHandler,
                          VoiceHandler voiceHandler,
                          LocationHandler locationHandler,
                          KfSessionHandler kfSessionHandler,
                          SubscribeHandler subscribeHandler,
                          UnsubscribeHandler unsubscribeHandler,
                          StoreCheckNotifyHandler storeCheckNotifyHandler) {
        this.logHandler = logHandler;
        this.msgHandler = msgHandler;
        this.nullHandler = nullHandler;
        this.menuHandler = menuHandler;
        this.scanHandler = scanHandler;
        this.linkHandler = linkHandler;
        this.systemConfig = systemConfig;
        this.imageHandler = imageHandler;
        this.videoHandler = videoHandler;
        this.voiceHandler = voiceHandler;
        this.locationHandler = locationHandler;
        this.kfSessionHandler = kfSessionHandler;
        this.subscribeHandler = subscribeHandler;
        this.unsubscribeHandler = unsubscribeHandler;
        this.storeCheckNotifyHandler = storeCheckNotifyHandler;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService service = new WxMpServiceImpl();
        WxMpDefaultConfigImpl configStorage = new WxMpDefaultConfigImpl();
        configStorage.setAppId(systemConfig.getWeChat().getMp().getAppId());
        configStorage.setSecret(systemConfig.getWeChat().getMp().getAppSecret());
        configStorage.setToken(systemConfig.getWeChat().getMp().getToken());
//        configStorage.setAesKey(systemConfig.getWeChat().getMp().getEncodingAESKey());
        service.setWxMpConfigStorage(configStorage);
        return service;
    }

    /**
     * 根据消息类型路由到不同的处理器
     *
     * @param wxMpService
     * @return
     */
    @Bean
    public WxMpMessageRouter messageRouter(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);
        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(this.logHandler).next();
        // 接收客服会话管理事件
        newRouter.rule().async(false).msgType(EVENT).event(KF_CREATE_SESSION)
                .handler(this.kfSessionHandler).end();
        newRouter.rule().async(false).msgType(EVENT).event(KF_CLOSE_SESSION)
                .handler(this.kfSessionHandler).end();
        newRouter.rule().async(false).msgType(EVENT).event(KF_SWITCH_SESSION)
                .handler(this.kfSessionHandler).end();
        // 门店审核事件
        newRouter.rule().async(false).msgType(EVENT).event(POI_CHECK_NOTIFY).handler(this.storeCheckNotifyHandler).end();
        // 自定义菜单事件
        newRouter.rule().async(false).msgType(EVENT).event(WxConsts.EventType.CLICK).handler(this.menuHandler).end();
        // 点击菜单连接事件
        newRouter.rule().async(false).msgType(EVENT).event(WxConsts.EventType.VIEW).handler(this.nullHandler).end();
        // 关注事件
        newRouter.rule().async(false).msgType(EVENT).event(SUBSCRIBE).handler(this.subscribeHandler).end();
        // 取消关注事件
        newRouter.rule().async(false).msgType(EVENT).event(UNSUBSCRIBE).handler(this.unsubscribeHandler).end();
        // 上报地理位置事件
        newRouter.rule().async(false).msgType(EVENT).event(WxConsts.EventType.LOCATION).handler(this.locationHandler).end();
        // 扫码事件
        newRouter.rule().async(false).msgType(EVENT).event(WxConsts.EventType.SCAN).handler(this.scanHandler).end();
        // 地理位置消息
        newRouter.rule().async(false).msgType(LOCATION).handler(this.locationHandler).end();
        // 图片消息
        newRouter.rule().async(false).msgType(IMAGE).handler(this.imageHandler).end();
        // 语音消息
        newRouter.rule().async(false).msgType(VOICE).handler(this.voiceHandler).end();
        // 视频消息
        newRouter.rule().async(false).msgType(VIDEO).handler(this.videoHandler).end();
        // 链接消息
        newRouter.rule().async(false).msgType(LINK).handler(this.linkHandler).end();
        // 默认
        newRouter.rule().async(false).handler(this.msgHandler).end();
        return newRouter;
    }
}
