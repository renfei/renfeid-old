package net.renfei.services.aliyun.model;

import lombok.Data;

import java.util.List;

/**
 * 阿里云绿网返回对象
 *
 * @author renfei
 */
@Data
public class AliyunGreenVO {
    private Integer code;
    private String msg;
    private String requestId;
    private List<Data> data;

    @lombok.Data
    public static class Data {
        private Integer code;
        private String content;
        private String dataId;
        private String msg;
        private List<Result> results;

        @lombok.Data
        public static class Result {
            private List<Detail> details;
            private String label;
            private Double rate;
            private String scene;
            private String suggestion;

            @lombok.Data
            private static class Detail {
                private String label;
            }
        }
    }
}
