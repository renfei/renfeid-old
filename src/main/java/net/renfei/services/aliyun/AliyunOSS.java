package net.renfei.services.aliyun;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import net.renfei.config.SystemConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * 阿里云对象存储
 *
 * @author renfei
 */
@Service
public class AliyunOSS extends AbstractAliyunService {
    protected AliyunOSS(SystemConfig systemConfig) {
        super(systemConfig);
    }

    public String upload(String path, MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空!");
        }
        // 文件名
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 新文件名
        fileName = UUID.randomUUID().toString().replace("-", "") + suffixName;
        uploadFile(file.getInputStream(), path + fileName);
        return systemConfig.getStaticDomain() + "/" + path + fileName;
    }

    /**
     * 获取签名URL进行临时授权
     *
     * @param bucketName 储存桶
     * @param objectName 对象地址
     * @param expiration 授权过期时间
     * @return 签名URL
     */
    public String getPreSignedUrl(String bucketName, String objectName, Date expiration) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder()
                .build(systemConfig.getAliyun().getOss().getEndpoint(), systemConfig.getAliyun().getAccessKeyId(), systemConfig.getAliyun().getAccessKeySecret());
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url.getPath() + "?" + url.getQuery();
    }

    /**
     * 获取限速下载的签名URL
     *
     * @param bucketName 储存桶
     * @param objectName 对象地址
     * @param expiration 授权过期时间
     * @param speed      限速（单位KB/s）
     * @return 签名URL
     */
    public String getTrafficLimitUrl(String bucketName, String objectName, Date expiration, int speed) {
        // 限速 （X） KB/s。
        int limitSpeed = speed * 1024 * 8;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder()
                .build(systemConfig.getAliyun().getOss().getEndpoint(), systemConfig.getAliyun().getAccessKeyId(), systemConfig.getAliyun().getAccessKeySecret());
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        request.setExpiration(expiration);
        request.setTrafficLimit(limitSpeed);
        URL url = ossClient.generatePresignedUrl(request);
        ossClient.shutdown();
        return url.getPath() + "?" + url.getQuery();
    }

    private void uploadFile(InputStream inputStream, String objectName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder()
                .build(systemConfig.getAliyun().getOss().getEndpoint(), systemConfig.getAliyun().getAccessKeyId(), systemConfig.getAliyun().getAccessKeySecret());
        ossClient.putObject(systemConfig.getAliyun().getOss().getBucketName(), objectName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
