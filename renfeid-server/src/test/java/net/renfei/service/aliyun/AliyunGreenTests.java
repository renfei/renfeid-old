package net.renfei.service.aliyun;

import net.renfei.ApplicationTests;
import net.renfei.services.aliyun.AliyunGreen;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 阿里云服务测试
 *
 * @author renfei
 */
public class AliyunGreenTests extends ApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(AliyunGreenTests.class);
    @Autowired
    private AliyunGreen aliyunGreen;

    /**
     * 阿里云文本扫描测试
     */
    @Test
    public void textScanTest() {
        logger.info("测试涉政内容：反党反政府");
        aliyunGreen.textScan("反党反政府");
        logger.info("测试谩骂内容：操你妈个逼的大傻逼");
        aliyunGreen.textScan("操你妈个逼的大傻逼");
        logger.info("测试正常内容：今天天气非常好");
        aliyunGreen.textScan("今天天气非常好");
    }
}
