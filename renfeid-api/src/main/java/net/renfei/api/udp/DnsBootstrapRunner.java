package net.renfei.api.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.dns.DatagramDnsQueryDecoder;
import io.netty.handler.codec.dns.DatagramDnsResponseEncoder;
import net.renfei.api.udp.handler.DnsHandler;
import net.renfei.config.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * DNS 解析服务(监听53端口)
 *
 * @author renfei
 */
@Component
public class DnsBootstrapRunner
        implements ApplicationRunner, ApplicationListener<ContextClosedEvent>, ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(DnsBootstrapRunner.class);
    /**
     * +---------------------------------------------------------+
     * | 非 root 用户无法使用 1024 以下的端口，所以此处不直接使用 53 端口
     * | java.net.SocketException: Permission denied
     * | 使用在宿主机上端口转发的方式将 53 端口转发到 9553 上
     * | public static final int DNS_PORT = 53;
     * +---------------------------------------------------------+
     */
    public static final int DNS_PORT = 9553;
    private ApplicationContext applicationContext;
    private Channel serverChannel;

    @Override
    public void run(ApplicationArguments args) {
        SystemConfig systemConfig = applicationContext.getBean(SystemConfig.class);
        // 测试会一直运行不停止
        if (!"ci".equals(systemConfig.getActive())) {
            final NioEventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(group).channel(NioDatagramChannel.class)
                        .handler(new ChannelInitializer<NioDatagramChannel>() {
                            @Override
                            protected void initChannel(NioDatagramChannel nioDatagramChannel) {
                                nioDatagramChannel.pipeline().addLast(new DatagramDnsQueryDecoder());
                                nioDatagramChannel.pipeline().addLast(new DatagramDnsResponseEncoder());
                                nioDatagramChannel.pipeline().addLast(applicationContext.getBean(DnsHandler.class));
                            }
                        }).option(ChannelOption.SO_BROADCAST, true);

                ChannelFuture future = bootstrap.bind(DNS_PORT).sync();
                logger.info("DnsNettyBootstrapRunner is running. Port={}.", DNS_PORT);
                this.serverChannel = future.channel();
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            } finally {
                group.shutdownGracefully();
            }
        }
    }

    /**
     * 通过 ApplicationContextAware 获取到 ApplicationContext
     * 这样方便我们拿到各种 Bean
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        if (this.serverChannel != null) {
            this.serverChannel.close();
        }
        logger.info("DnsNettyBootstrapRunner is stopped.");
    }
}
