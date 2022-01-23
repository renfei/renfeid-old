package net.renfei.apisync.model;

import java.util.Map;

/**
 * @author renfei
 */
public class ApiDom {
    private Map<String, Path> paths;

    public static class Path {
        private Map<String, PathInfo> post;
        private Map<String, PathInfo> get;
        private Map<String, PathInfo> put;
        private Map<String, PathInfo> delete;

        public static class PathInfo {
            private String summary;
            private String description;

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            @Override
            public String toString() {
                return "PathInfo{" +
                        "summary='" + summary + '\'' +
                        ", description='" + description + '\'' +
                        '}';
            }
        }

        public Map<String, PathInfo> getPost() {
            return post;
        }

        public void setPost(Map<String, PathInfo> post) {
            this.post = post;
        }

        public Map<String, PathInfo> getGet() {
            return get;
        }

        public void setGet(Map<String, PathInfo> get) {
            this.get = get;
        }

        public Map<String, PathInfo> getPut() {
            return put;
        }

        public void setPut(Map<String, PathInfo> put) {
            this.put = put;
        }

        public Map<String, PathInfo> getDelete() {
            return delete;
        }

        public void setDelete(Map<String, PathInfo> delete) {
            this.delete = delete;
        }

        @Override
        public String toString() {
            return "Path{" +
                    "post=" + post +
                    ", get=" + get +
                    ", put=" + put +
                    ", delete=" + delete +
                    '}';
        }
    }

    public Map<String, Path> getPaths() {
        return paths;
    }

    public void setPaths(Map<String, Path> paths) {
        this.paths = paths;
    }

    @Override
    public String toString() {
        return "ApiDom{" +
                "paths=" + paths +
                '}';
    }
}
