package net.renfei.service.aliyun;

import net.renfei.ApplicationTests;
import net.renfei.services.aliyun.AliyunOSS;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 阿里云对象存储单元测试
 *
 * @author renfei
 */
public class AliyunOSSTests extends ApplicationTests {
    @Autowired
    private AliyunOSS aliyunOSS;

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
        aliyunOSS.upload("", multipartFile);
    }
}
