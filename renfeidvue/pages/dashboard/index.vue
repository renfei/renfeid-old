<template>
  <v-row>
    <v-col cols="12">
      <v-card
          flat
          outlined
      >
        <v-card-title>系统信息</v-card-title>
        <v-card-text>
          <v-row>
            <v-col cols="12" sm="6" md="4" lg="2">
              <v-card
                  flat
                  outlined
              >
                <v-card-subtitle>操作系统信息</v-card-subtitle>
                <v-card-text>
                  <v-text-field
                      label="操作系统名称"
                      v-model="data.os.name"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="操作系统架构"
                      v-model="data.os.arch"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="操作系统版本"
                      v-model="data.os.version"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="CPU核心/线程数"
                      v-model="data.os.processors"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="操作系统总物理内存"
                      v-model="data.os.totalPhysicalMemory"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="系统一分钟内平均负载"
                      v-model="data.os.systemLoadAverage"
                      dense
                      readonly
                  ></v-text-field>
                  <v-list
                      dense
                  >
                    <v-subheader class="px-0 py-0">磁盘信息</v-subheader>
                    <v-list-item three-line v-for="disk in data.os.disks" :key="disk.path" class="px-0">
                      <v-list-item-content>
                        <v-list-item-title v-text="'路径：'+disk.path"></v-list-item-title>
                        <v-list-item-subtitle>
                          总量：{{ disk.totalSpace }}
                        </v-list-item-subtitle>
                        <v-list-item-subtitle>
                          剩余：{{ disk.freeSpace }}
                        </v-list-item-subtitle>
                      </v-list-item-content>
                    </v-list-item>
                  </v-list>
                </v-card-text>
              </v-card>
              <v-card
                  flat
                  outlined
                  class="mt-4"
              >
                <v-card-subtitle>用户信息</v-card-subtitle>
                <v-card-text>
                  <v-text-field
                      label="用户名"
                      v-model="data.user.name"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="用户主目录"
                      v-model="data.user.home"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="用户当前工作目录"
                      v-model="data.user.dir"
                      dense
                      readonly
                  ></v-text-field>
                </v-card-text>
              </v-card>
            </v-col>
            <v-col cols="12" sm="6" md="4" lg="5">
              <v-card
                  flat
                  outlined
              >
                <v-card-subtitle>Java信息</v-card-subtitle>
                <v-card-text>
                  <v-text-field
                      label="Java运行时版本"
                      v-model="data.java.version"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="Java运行时供应商"
                      v-model="data.java.vendor"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="Java安装目录"
                      v-model="data.java.home"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="Java运行时规范名称"
                      v-model="data.java.specificationName"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="Java运行时规范版本"
                      v-model="data.java.specificationVersion"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="Java运行时规范供应商"
                      v-model="data.java.specificationVendor"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="Java类格式版本号"
                      v-model="data.java.classVersion"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="Java类路径"
                      v-model="data.java.classPath"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="Java引导类路径"
                      v-model="data.java.bootClassPath"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="Java运行时加载库路径"
                      v-model="data.java.library"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="Java运行时默认临时文件路径"
                      v-model="data.java.tmpdir"
                      dense
                      readonly
                  ></v-text-field>
                  <v-text-field
                      label="Java运行时扩展目录"
                      v-model="data.java.extDirs"
                      dense
                      readonly
                  ></v-text-field>
                </v-card-text>
              </v-card>
            </v-col>
            <v-col cols="12" sm="6" md="4" lg="5">
              <v-card
                  flat
                  outlined
              >
                <v-card-subtitle>JVM信息</v-card-subtitle>
                <v-card-text>
                  <v-row>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="JVM虚拟机实现名称"
                          v-model="data.jvm.name"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="JVM虚拟机实现版本"
                          v-model="data.jvm.version"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="JVM虚拟机实现供应商"
                          v-model="data.jvm.vendor"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="JVM虚拟机实现规范名称"
                          v-model="data.jvm.specificationName"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="JVM虚拟机实现规范版本"
                          v-model="data.jvm.specificationVersion"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="JVM虚拟机实现规范供应商"
                          v-model="data.jvm.specificationVendor"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="JVM虚拟机初始内存（MB）"
                          v-model="data.jvm.initMemory"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="JVM虚拟机申请内存（MB）"
                          v-model="data.jvm.committedMemory"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="JVM虚拟机使用内存（MB）"
                          v-model="data.jvm.useMemory"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="JVM虚拟机空闲内存（MB）"
                          v-model="data.jvm.freeMemory"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="JVM虚拟机最大内存（MB）"
                          v-model="data.jvm.maxMemory"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="JVM虚拟机启动时间"
                          v-model="data.jvm.startTime"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="JVM虚拟机运行时间（毫秒）"
                          v-model="data.jvm.runtime"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="已加载类总数量"
                          v-model="data.jvm.totalLoadedClassCount"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="已卸载类总数量"
                          v-model="data.jvm.unloadedClassCount"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="活动的线程总数"
                          v-model="data.jvm.thread.threadCount"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="峰值线程数"
                          v-model="data.jvm.thread.peakThreadCount"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="JVM启动以来创建和启动的线程总数"
                          v-model="data.jvm.thread.totalStartedThreadCount"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" class="py-1">
                      <v-text-field
                          label="守护线程数"
                          v-model="data.jvm.thread.daemonThreadCount"
                          dense
                          readonly
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </v-card-text>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: 'DashboardPage',
  layout: function (context) {
    return 'dashboard/default'
  },
  data: () => ({
    data: {
      user: {
        name: "string",
        home: "string",
        dir: "string",
      },
      java: {
        version: "string",
        vendor: "string",
        home: "string",
        specificationName: "string",
        specificationVersion: "string",
        specificationVendor: "string",
        classVersion: "string",
        classPath: "string",
        bootClassPath: "string",
        library: "string",
        tmpdir: "string",
        extDirs: "string",
      },
      jvm: {
        name: "string",
        version: "string",
        vendor: "string",
        specificationName: "string",
        specificationVersion: "string",
        specificationVendor: "string",
        initMemory: "string",
        committedMemory: "string",
        useMemory: "string",
        freeMemory: "string",
        maxMemory: "string",
        startTime: "2022-06-22T09:09:50.398Z",
        runtime: 0,
        totalLoadedClassCount: 0,
        loadedClassCount: 0,
        unloadedClassCount: 0,
        thread: {
          threadCount: 0,
          peakThreadCount: 0,
          totalStartedThreadCount: 0,
          daemonThreadCount: 0,
        },
      },
      os: {
        name: "string",
        arch: "string",
        version: "string",
        processors: 0,
        systemLoadAverage: 0,
        totalPhysicalMemory: "string",
        disks: [
          {
            path: "string",
            totalSpace: "string",
            freeSpace: "string",
          },
        ],
      },
    }
  }),
  mounted() {
    this.$api.getEnvironmentInfo().then(res => {
      console.warn(res)
      this.data = res.data.data
    })
  },
}
</script>
