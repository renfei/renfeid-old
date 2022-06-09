/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.common.core.entity;

import lombok.Getter;
import lombok.ToString;

import java.util.Properties;

/**
 * 环境信息
 *
 * @author renfei
 */
@ToString
public class EnvironmentInfo {
    private final static Properties PROPERTIES = System.getProperties();
    @Getter
    private final User user;
    @Getter
    private final OS oS;
    @Getter
    private final Java java;
    @Getter
    private final JVM jvm;

    public EnvironmentInfo() {
        user = new User();
        oS = new OS();
        java = new Java();
        jvm = new JVM();
    }

    @ToString
    public static class User {
        @Getter
        private final String name;
        @Getter
        private final String home;
        @Getter
        private final String dir;

        User() {
            name = PROPERTIES.getProperty("user.name");
            home = PROPERTIES.getProperty("user.home");
            dir = PROPERTIES.getProperty("user.dir");
        }
    }

    @ToString
    public static class OS {
        @Getter
        private final String name;
        @Getter
        private final String arch;
        @Getter
        private final String version;

        OS() {
            name = PROPERTIES.getProperty("os.name");
            arch = PROPERTIES.getProperty("os.arch");
            version = PROPERTIES.getProperty("os.version");
        }
    }

    @ToString
    public static class Java {
        @Getter
        private final String version;
        @Getter
        private final String vendor;
        @Getter
        private final String home;
        @Getter
        private final String specificationName;
        @Getter
        private final String specificationVersion;
        @Getter
        private final String specificationVendor;
        @Getter
        private final String classVersion;
        @Getter
        private final String classPath;
        @Getter
        private final String library;
        @Getter
        private final String tmpdir;
        @Getter
        private final String extDirs;

        Java() {
            version = PROPERTIES.getProperty("java.version");
            vendor = PROPERTIES.getProperty("java.vendor");
            home = PROPERTIES.getProperty("java.home");
            specificationName = PROPERTIES.getProperty("java.specification.name");
            specificationVersion = PROPERTIES.getProperty("java.specification.version");
            specificationVendor = PROPERTIES.getProperty("java.specification.vender");
            classVersion = PROPERTIES.getProperty("java.class.version");
            classPath = PROPERTIES.getProperty("java.class.path");
            library = PROPERTIES.getProperty("java.library.path");
            tmpdir = PROPERTIES.getProperty("java.io.tmpdir");
            extDirs = PROPERTIES.getProperty("java.ext.dirs");
        }
    }

    @ToString
    public static class JVM {
        private final String name;
        @Getter
        private final String version;
        @Getter
        private final String vendor;
        @Getter
        private final String specificationName;
        @Getter
        private final String specificationVersion;
        @Getter
        private final String specificationVendor;

        JVM() {
            name = PROPERTIES.getProperty("java.vm.name");
            version = PROPERTIES.getProperty("java.vm.version");
            vendor = PROPERTIES.getProperty("java.vm.vendor");
            specificationName = PROPERTIES.getProperty("java.vm.specification.name");
            specificationVersion = PROPERTIES.getProperty("java.vm.specification.version");
            specificationVendor = PROPERTIES.getProperty("java.vm.specification.vendor");
        }
    }
}
