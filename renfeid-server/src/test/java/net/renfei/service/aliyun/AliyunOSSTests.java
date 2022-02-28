package net.renfei.service.aliyun;

import net.renfei.ApplicationTests;
import net.renfei.config.SystemConfig;
import net.renfei.services.aliyun.AliyunOSS;
import net.renfei.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * 阿里云对象存储单元测试
 *
 * @author renfei
 */
public class AliyunOSSTests extends ApplicationTests {
    @Autowired
    private AliyunOSS aliyunOSS;
    @Autowired
    private SystemConfig systemConfig;

    @Test
    public void upload() throws Exception {
        File file = new File("/tmp/UnitTestFileName.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return "UnitTestFileName.txt";
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(file);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        String url = aliyunOSS.upload("", multipartFile);
        String objectName = url.split("/")[3];
        System.out.println("upload url:" + url);
        Date expiration = DateUtils.nextHours(1);
        String bucketName = systemConfig.getAliyun().getOss().getBucketName();
        System.out.println("getPreSignedUrl: " + aliyunOSS.getPreSignedUrl(bucketName, objectName, expiration));
        System.out.println("getTrafficLimitUrl: " + aliyunOSS.getTrafficLimitUrl(bucketName, objectName, expiration, 1));
    }
}
