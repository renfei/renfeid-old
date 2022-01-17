package net.renfei.services.wechat;

import net.renfei.model.ListData;
import net.renfei.model.search.SearchItem;
import net.renfei.model.wechat.message.EventMessage;
import net.renfei.model.wechat.xmlmessage.*;
import net.renfei.repositories.WechatKeywordMapper;
import net.renfei.repositories.model.WechatKeyword;
import net.renfei.repositories.model.WechatKeywordExample;
import net.renfei.services.BaseService;
import net.renfei.services.EmailService;
import net.renfei.services.SearchService;
import net.renfei.services.WeChatService;
import net.renfei.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 微信服务
 *
 * @author renfei
 */
@Service
public class WeChatServiceImpl extends BaseService implements WeChatService {
    private static final Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);
    private static final String NOT_SUPPORTED_MSG = "【收到不支持的消息类型，暂无法显示】";
    private static final String UNABLE_PROCESS_MSG = "/::~您的消息我们已经收到，但「RENFEI.NET」暂时无法处理您的消息类型。";
    private final EmailService emailService;
    private final SearchService searchService;
    private final WechatKeywordMapper wechatKeywordMapper;

    public WeChatServiceImpl(EmailService emailService,
                             SearchService searchService,
                             WechatKeywordMapper wechatKeywordMapper) {
        this.emailService = emailService;
        this.searchService = searchService;
        this.wechatKeywordMapper = wechatKeywordMapper;
    }

    @Override
    public XMLMessage weChatMessageHandel(EventMessage eventMessage) {
        logger.info("收到微信公众号{}消息", eventMessage.getMsgType());
        switch (eventMessage.getMsgType()) {
            case "text":
                logger.info("消息内容为：{}", eventMessage.getContent());
                if (NOT_SUPPORTED_MSG.equals(eventMessage.getContent())) {
                    return new XMLTextMessage(
                            eventMessage.getFromUserName(),
                            eventMessage.getToUserName(),
                            UNABLE_PROCESS_MSG);
                }
                return processMsg(
                        eventMessage.getFromUserName(),
                        eventMessage.getToUserName(),
                        eventMessage.getContent());
            case "voice":
                logger.info("消息内容为：{}", eventMessage.getRecognition());
                if (eventMessage.getRecognition() == null) {
                    return new XMLTextMessage(
                            eventMessage.getFromUserName(),
                            eventMessage.getToUserName(),
                            UNABLE_PROCESS_MSG);
                }
                return processMsg(
                        eventMessage.getFromUserName(),
                        eventMessage.getToUserName(),
                        eventMessage.getRecognition());
            case "image":
                logger.info("图片地址为：{}", eventMessage.getPicUrl());
                return new XMLTextMessage(
                        eventMessage.getFromUserName(),
                        eventMessage.getToUserName(),
                        "/::~我们已经收到您发送的图片：" + eventMessage.getPicUrl() + "，但「RENFEI.NET」暂时还无法理解其中的含义，请您尝试发送文本消息。");
            case "location":
                logger.info("位置信息为：{}，经度：{}，纬度：{}", eventMessage.getLabel(), eventMessage.getLocation_X(), eventMessage.getLocation_Y());
                return new XMLTextMessage(
                        eventMessage.getFromUserName(),
                        eventMessage.getToUserName(),
                        "/::~我们已经收到您发送的位置信息：" + eventMessage.getLabel() + "，但「RENFEI.NET」暂时还无法理解其中的含义，请您尝试发送文本消息。");
            case "link":
                logger.info("链接地址为：{}", eventMessage.getUrl());
                return new XMLTextMessage(
                        eventMessage.getFromUserName(),
                        eventMessage.getToUserName(),
                        "/::~我们已经收到您发送的网页信息：" + eventMessage.getUrl() + "，但「RENFEI.NET」暂时还无法理解其中的含义，请您尝试发送文本消息。");
            case "event":
                logger.info("事件为：{}", eventMessage.getEvent());
                switch (eventMessage.getEvent()) {
                    case "subscribe":
                        return new XMLTextMessage(
                                eventMessage.getFromUserName(),
                                eventMessage.getToUserName(),
                                "锄禾日当午\n不如Coding苦\n对着C++\n一调一下午\n" +
                                        "\n" +
                                        "调了一下午\nBug还得补\n" +
                                        "\n" +
                                        "Bug刚补完\n结构需重组[Facepalm]\n" +
                                        "\n" +
                                        "我是任霏，感谢您关注我的公众号。");
                    case "unsubscribe":
                        return new XMLTextMessage(
                                eventMessage.getFromUserName(),
                                eventMessage.getToUserName(),
                                "后会有期。");
                    default:
                        return null;
                }
            case "video":
            case "shortvideo":
            case "music":
            case "news":
            default:
                return new XMLTextMessage(
                        eventMessage.getFromUserName(),
                        eventMessage.getToUserName(),
                        UNABLE_PROCESS_MSG);
        }
    }

    /**
     * 处理微信消息
     *
     * @param fromUserName
     * @param toUserName
     * @param content
     * @return
     */
    private XMLMessage processMsg(String fromUserName, String toUserName, String content) {
        if (content.startsWith("极速下载") ||
                content.startsWith("極速下載") ||
                content.startsWith("急速下載")) {
            return new XMLTextMessage(fromUserName, toUserName, "抱歉，极速下载功能已下线。我站将专注软件技术内容的分享。");
        }
        // 查询关键字是否命中
        WechatKeywordExample example = new WechatKeywordExample();
        example.createCriteria().andKeyWordEqualTo(content);
        WechatKeyword wechatKeyword = ListUtils.getOne(wechatKeywordMapper.selectByExample(example));
        if (wechatKeyword != null) {
            switch (wechatKeyword.getKeyType()) {
                case "image":
                    return new XMLImageMessage(fromUserName, toUserName, wechatKeyword.getMediaId());
                case "voice":
                    return new XMLVoiceMessage(fromUserName, toUserName, wechatKeyword.getMediaId());
                case "video":
                    return new XMLVideoMessage(fromUserName, toUserName, wechatKeyword.getMediaId(), wechatKeyword.getTitle(), wechatKeyword.getDescription());
                case "news":
                    XMLNewsMessage.Article article = new XMLNewsMessage.Article();
                    article.setTitle(wechatKeyword.getTitle());
                    article.setDescription(wechatKeyword.getDescription());
                    article.setPicurl(wechatKeyword.getPicUrl());
                    article.setUrl(wechatKeyword.getUrl());
                    return new XMLNewsMessage(fromUserName, toUserName, article);
                default:
                    return new XMLTextMessage(fromUserName, toUserName, wechatKeyword.getTitle());
            }
        } else {
            // 未命中关键字，走站内搜索引擎
            ListData<SearchItem> searchItemListData = searchService.search(content, "1", "1");
            if (searchItemListData.getTotal() != 0) {
                SearchItem searchItem = searchItemListData.getData().get(0);
                XMLNewsMessage.Article article = new XMLNewsMessage.Article();
                article.setTitle(searchItem.getTitle());
                article.setDescription(searchItem.getContent());
                article.setPicurl(searchItem.getImage());
                article.setUrl(searchItem.getUrl());
                return new XMLNewsMessage(fromUserName, toUserName, article);
            } else {
                // 站内搜索引擎也没搜索到，发邮件通知
                emailService.send("i@renfei.net", "任霏", "收到无法处理的微信消息", "我们收到了无法处理的微信信息，请查看：\n" + content);
                return new XMLTextMessage(fromUserName, toUserName, "/:,@!哎呀！你的问题难倒我了，我去喊任霏本人来回答吧，请稍后。[LetMeSee]");
            }
        }
    }
}
