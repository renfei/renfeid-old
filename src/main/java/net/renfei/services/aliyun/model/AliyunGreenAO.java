package net.renfei.services.aliyun.model;

import java.util.List;

/**
 * 阿里云绿网请求对象
 *
 * @author renfei
 */
public class AliyunGreenAO {
    private List<String> scenes;
    private List<Task> tasks;

    public static class Task {
        private String dataId;
        /**
         * 待检测的文本，长度不超过10000个字符
         */
        private String content;

        public String getDataId() {
            return dataId;
        }

        public void setDataId(String dataId) {
            this.dataId = dataId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public List<String> getScenes() {
        return scenes;
    }

    public void setScenes(List<String> scenes) {
        this.scenes = scenes;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
