package net.renfei.api.udp.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.dns.*;
import net.renfei.repositories.KitboxDneyesRecordLogMapper;
import net.renfei.repositories.model.KitboxDneyesRecordLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * DNS 解析处理器
 *
 * @author renfei
 */
@Component
public class DnsHandler extends SimpleChannelInboundHandler<DatagramDnsQuery> {
    private static final Logger logger = LoggerFactory.getLogger(DnsHandler.class);
    private static final String DOT = ".";
    private static final String DOMAIN = "dneyes.net";
    private static final String DOMAIN_WWW = "www.dneyes.net";
    private static final String GITHUB_PAGES_CNAME = "renfei.github.io";
    private static final String DNS_NS_RECORD_1 = "dneyes-ns1.renfei.net";
    private static final String DNS_NS_RECORD_2 = "dneyes-ns2.renfei.net";
    private static final long DNS_NS_TTL = 72 * 60 * 60;
    @Autowired
    private KitboxDneyesRecordLogMapper kitboxDneyesRecordLogMapper;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramDnsQuery query) {

        String clientIp = query.sender().getAddress().getHostAddress(),
                hostName = query.sender().getHostString();
        // 假数据，域名和ip的对应关系应该放到数据库中
        DatagramDnsResponse response = new DatagramDnsResponse(query.recipient(), query.sender(), query.id());
        try {
            DefaultDnsQuestion dnsQuestion = query.recordAt(DnsSection.QUESTION);
            response.addRecord(DnsSection.QUESTION, dnsQuestion);
            String domainName = dnsQuestion.name().substring(0, dnsQuestion.name().length() - 1).toLowerCase();
            ByteBuf buf = Unpooled.wrappedBuffer(new byte[]{127, 0, 0, 1});
            DefaultDnsRawRecord queryAnswer;
            if (DOMAIN.equals(domainName) && dnsQuestion.type().equals(DnsRecordType.NS)) {
                // 查询 NS 记录
                buf = Unpooled.buffer();
                this.encodeName(DNS_NS_RECORD_1, buf);
                queryAnswer = new DefaultDnsRawRecord(dnsQuestion.name(), DnsRecordType.NS, DNS_NS_TTL, buf);
                response.addRecord(DnsSection.ANSWER, queryAnswer);
                buf = Unpooled.buffer();
                this.encodeName(DNS_NS_RECORD_2, buf);
                queryAnswer = new DefaultDnsRawRecord(dnsQuestion.name(), DnsRecordType.NS, DNS_NS_TTL, buf);
            } else if (DOMAIN.equals(domainName) || DOMAIN_WWW.equals(domainName)) {
                // 直接解析网址，CNAME 到 Github Pages
                buf = Unpooled.buffer();
                this.encodeName(GITHUB_PAGES_CNAME, buf);
                queryAnswer = new DefaultDnsRawRecord(dnsQuestion.name(), DnsRecordType.CNAME, 1, buf);
            } else if (domainName.endsWith(DOT + DOMAIN)) {
                // 只处理 .dneyes.net 的域名解析请求
                KitboxDneyesRecordLog recordLog = new KitboxDneyesRecordLog();
                recordLog.setClientIp(clientIp);
                recordLog.setLogTime(new Date());
                recordLog.setClientHostName(hostName);
                recordLog.setSubDomain(domainName);
                kitboxDneyesRecordLogMapper.insertSelective(recordLog);
                queryAnswer = new DefaultDnsRawRecord(dnsQuestion.name(), DnsRecordType.A, 1, buf);
            } else {
                // 其他非法域名不做处理，只打印日志
                logger.info("域名查询请求：{}，客户端IP：{}，客户端主机名：{}，丢弃处理。", domainName, clientIp, hostName);
                queryAnswer = new DefaultDnsRawRecord(dnsQuestion.name(), DnsRecordType.A, 1, buf);
            }
            response.addRecord(DnsSection.ANSWER, queryAnswer);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            ctx.writeAndFlush(response);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error(cause.getMessage(), cause);
    }

    private void encodeName(String name, ByteBuf out) {
        DefaultDnsRecordEncoderTrampoline.INSTANCE.encodeName(name, out);
    }

    private static class DefaultDnsRecordEncoderTrampoline extends DefaultDnsRecordEncoder {

        private static final DefaultDnsRecordEncoderTrampoline INSTANCE =
                new DefaultDnsRecordEncoderTrampoline();

        @Override
        protected void encodeName(String name, ByteBuf buf) {
            try {
                super.encodeName(name, buf);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
