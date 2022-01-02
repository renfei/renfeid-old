package net.renfei.services.aliyun.model;

import lombok.Data;

import java.util.List;

/**
 * 阿里云绿网请求对象
 *
 * @author renfei
 */
@Data
public class AliyunGreenAO {
    private List<String> scenes;
    private List<Task> tasks;

    @Data
    public static class Task {
        private String dataId;
        /**
         * 待检测的文本，长度不超过10000个字符
         */
        private String content;
    }
}
