package net.renfei.service.aliyun;

import lombok.extern.slf4j.Slf4j;
import net.renfei.ApplicationTests;
import net.renfei.services.aliyun.AliyunGreen;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 阿里云服务测试
 *
 * @author renfei
 */
@Slf4j
public class AliyunGreenTests extends ApplicationTests {
    @Autowired
    private AliyunGreen aliyunGreen;

    /**
     * 阿里云文本扫描测试
     */
    @Test
    public void textScanTest() {
        log.info("测试涉政内容：反党反政府");
        boolean result = aliyunGreen.textScan("反党反政府");
        assert !result;
        log.info("测试谩骂内容：操你妈个逼的大傻逼");
        result = aliyunGreen.textScan("操你妈个逼的大傻逼");
        assert !result;
        log.info("测试正常内容：今天天气非常好");
        result = aliyunGreen.textScan("今天天气非常好");
        assert result;
    }
}
