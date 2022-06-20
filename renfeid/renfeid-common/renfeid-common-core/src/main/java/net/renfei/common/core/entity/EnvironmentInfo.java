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

import com.sun.management.OperatingSystemMXBean;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import java.io.File;
import java.io.Serializable;
import java.lang.management.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 环境信息
 *
 * @author renfei
 */
@ToString
@Schema(title = "环境信息")
public class EnvironmentInfo implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    private final static int BYTE_TO_MB = 1024 * 1024;
    private final static Properties PROPERTIES = System.getProperties();
    private final static OperatingSystemMXBean OPERATING_SYSTEM_MX_BEAN =
            (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    private final static ThreadMXBean THREAD_MX_BEAN = ManagementFactory.getThreadMXBean();
    private final static RuntimeMXBean RUNTIME_MX_BEAN = ManagementFactory.getRuntimeMXBean();
    private final static ClassLoadingMXBean CLASS_LOAD = ManagementFactory.getClassLoadingMXBean();
    private final static MemoryUsage MEMORY_USAGE = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
    @Getter
    @Schema(description = "用户信息")
    private final User user;
    @Getter
    @Schema(description = "操作系统信息")
    private final OS oS;
    @Getter
    @Schema(description = "Java信息")
    private final Java java;
    @Getter
    @Schema(description = "JVM信息")
    private final JVM jvm;

    public EnvironmentInfo() {
        user = new User();
        oS = new OS();
        java = new Java();
        jvm = new JVM();
    }

    @ToString
    @Schema(title = "用户信息")
    public static class User {
        @Getter
        @Schema(description = "用户名")
        private final String name;
        @Getter
        @Schema(description = "用户主目录")
        private final String home;
        @Getter
        @Schema(description = "用户当前工作目录")
        private final String dir;

        User() {
            name = PROPERTIES.getProperty("user.name");
            home = PROPERTIES.getProperty("user.home");
            dir = PROPERTIES.getProperty("user.dir");
        }
    }

    @ToString
    @Schema(title = "操作系统信息")
    public static class OS {
        @Getter
        @Schema(description = "操作系统名称")
        private final String name;
        @Getter
        @Schema(description = "操作系统架构")
        private final String arch;
        @Getter
        @Schema(description = "操作系统版本")
        private final String version;
        @Getter
        @Schema(description = "CPU核心/线程数")
        private final int processors;
        @Getter
        @Schema(description = "系统一分钟内平均负载")
        private final double systemLoadAverage;
        @Getter
        @Schema(description = "操作系统总物理内存")
        private final String totalPhysicalMemory;
        @Getter
        @Schema(description = "操作系统磁盘容量")
        private final List<Disk> disks;

        public static class Disk {
            @Getter
            @Schema(description = "磁盘路径")
            private final String path;
            @Getter
            @Schema(description = "磁盘总容量（MB）")
            private final String totalSpace;
            @Getter
            @Schema(description = "磁盘总容量（MB）")
            private final String freeSpace;

            Disk(String path, String totalSpace, String freeSpace) {
                this.path = path;
                this.totalSpace = totalSpace;
                this.freeSpace = freeSpace;
            }
        }

        OS() {
            name = OPERATING_SYSTEM_MX_BEAN.getName();
            arch = OPERATING_SYSTEM_MX_BEAN.getArch();
            version = OPERATING_SYSTEM_MX_BEAN.getVersion();
            processors = OPERATING_SYSTEM_MX_BEAN.getAvailableProcessors();
            systemLoadAverage = OPERATING_SYSTEM_MX_BEAN.getSystemLoadAverage();
            totalPhysicalMemory = (OPERATING_SYSTEM_MX_BEAN.getTotalPhysicalMemorySize() / BYTE_TO_MB) + " MB";
            File[] disks = File.listRoots();
            List<Disk> diskList = new ArrayList<>();
            for (File file : disks
            ) {
                diskList.add(new Disk(file.getPath(),
                        (file.getTotalSpace() / BYTE_TO_MB) + " MB",
                        (file.getFreeSpace() / BYTE_TO_MB) + " MB"));
            }
            this.disks = diskList;
        }
    }

    @ToString
    @Schema(title = "Java信息")
    public static class Java {
        @Getter
        @Schema(description = "Java运行时版本")
        private final String version;
        @Getter
        @Schema(description = "Java运行时供应商")
        private final String vendor;
        @Getter
        @Schema(description = "Java安装目录")
        private final String home;
        @Getter
        @Schema(description = "Java运行时规范名称")
        private final String specificationName;
        @Getter
        @Schema(description = "Java运行时规范版本")
        private final String specificationVersion;
        @Getter
        @Schema(description = "Java运行时规范供应商")
        private final String specificationVendor;
        @Getter
        @Schema(description = "Java类格式版本号")
        private final String classVersion;
        @Getter
        @Schema(description = "Java类路径")
        private final String classPath;
        @Getter
        @Schema(description = "Java引导类路径")
        private final String bootClassPath;
        @Getter
        @Schema(description = "Java运行时加载库路径")
        private final String library;
        @Getter
        @Schema(description = "Java运行时默认临时文件路径")
        private final String tmpdir;
        @Getter
        @Schema(description = "Java运行时扩展目录")
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
            bootClassPath = PROPERTIES.getProperty("sun.boot.class.path");
            library = PROPERTIES.getProperty("java.library.path");
            tmpdir = PROPERTIES.getProperty("java.io.tmpdir");
            extDirs = PROPERTIES.getProperty("java.ext.dirs");
        }
    }

    @ToString
    @Schema(title = "JVM信息")
    public static class JVM {
        @Getter
        @Schema(description = "JVM虚拟机实现名称")
        private final String name;
        @Getter
        @Schema(description = "JVM虚拟机实现版本")
        private final String version;
        @Getter
        @Schema(description = "JVM虚拟机实现供应商")
        private final String vendor;
        @Getter
        @Schema(description = "JVM虚拟机实现规范名称")
        private final String specificationName;
        @Getter
        @Schema(description = "JVM虚拟机实现规范版本")
        private final String specificationVersion;
        @Getter
        @Schema(description = "JVM虚拟机实现规供应商")
        private final String specificationVendor;
        @Getter
        @Schema(description = "JVM虚拟机初始内存空间（MB）")
        private final String initMemory;
        @Getter
        @Schema(description = "JVM虚拟机已申请的内存空间（MB）")
        private final String committedMemory;
        @Getter
        @Schema(description = "JVM虚拟机已使用内存空间（MB）")
        private final String useMemory;
        @Getter
        @Schema(description = "JVM虚拟机空闲内存空间（MB）")
        private final String freeMemory;
        @Getter
        @Schema(description = "JVM虚拟机可申请最大内存空间（MB）")
        private final String maxMemory;
        @Getter
        @Schema(description = "JVM虚拟机启动时间")
        private final Date startTime;
        @Getter
        @Schema(description = "JVM虚拟机运行时间（毫秒）")
        private final long runtime;
        @Getter
        @Schema(description = "已加载类总数量")
        private final long totalLoadedClassCount;
        @Getter
        @Schema(description = "当前加载类数量")
        private final long loadedClassCount;
        @Getter
        @Schema(description = "已卸载类总数量")
        private final long unloadedClassCount;
        @Getter
        @Schema(description = "JVM启动参数")
        private final List<String> inputArguments;
        @Getter
        @Schema(description = "线程信息")
        private final Thread thread;

        @Schema(title = "线程信息")
        public static class Thread {
            @Getter
            @Schema(description = "活动的线程总数")
            private final int threadCount;
            @Getter
            @Schema(description = "峰值线程数")
            private final int peakThreadCount;
            @Getter
            @Schema(description = "JVM启动以来创建和启动的线程总数")
            private final long totalStartedThreadCount;
            @Getter
            @Schema(description = "守护线程数")
            private final int daemonThreadCount;
            @Getter
            @Schema(description = "死锁线程列表")
            private final ThreadInfo[] deadlockInfos;

            Thread() {
                threadCount = THREAD_MX_BEAN.getThreadCount();
                peakThreadCount = THREAD_MX_BEAN.getPeakThreadCount();
                totalStartedThreadCount = THREAD_MX_BEAN.getTotalStartedThreadCount();
                daemonThreadCount = THREAD_MX_BEAN.getDaemonThreadCount();
                //查找死锁线程id
                long[] deadlockedIds = THREAD_MX_BEAN.findDeadlockedThreads();
                if (deadlockedIds != null && deadlockedIds.length > 0) {
                    deadlockInfos = THREAD_MX_BEAN.getThreadInfo(deadlockedIds);
                } else {
                    deadlockInfos = null;
                }
            }
        }

        JVM() {
            name = PROPERTIES.getProperty("java.vm.name");
            version = PROPERTIES.getProperty("java.vm.version");
            vendor = PROPERTIES.getProperty("java.vm.vendor");
            specificationName = PROPERTIES.getProperty("java.vm.specification.name");
            specificationVersion = PROPERTIES.getProperty("java.vm.specification.version");
            specificationVendor = PROPERTIES.getProperty("java.vm.specification.vendor");
            initMemory = (MEMORY_USAGE.getInit() / BYTE_TO_MB) + " MB";
            committedMemory = (MEMORY_USAGE.getCommitted() / BYTE_TO_MB) + " MB";
            useMemory = (MEMORY_USAGE.getUsed() / BYTE_TO_MB) + " MB";
            maxMemory = (MEMORY_USAGE.getMax() / BYTE_TO_MB) + " MB";
            freeMemory = ((MEMORY_USAGE.getCommitted() - MEMORY_USAGE.getUsed()) / BYTE_TO_MB) + " MB";
            startTime = new Date(RUNTIME_MX_BEAN.getStartTime());
            runtime = RUNTIME_MX_BEAN.getUptime();
            totalLoadedClassCount = CLASS_LOAD.getTotalLoadedClassCount();
            loadedClassCount = CLASS_LOAD.getLoadedClassCount();
            unloadedClassCount = CLASS_LOAD.getUnloadedClassCount();
            inputArguments = RUNTIME_MX_BEAN.getInputArguments();
            thread = new Thread();
        }
    }
}
