namespace Environment {
  interface EnvironmentInfo {
    user: User,
    os: OS,
    java: Java,
    jvm: JVM,
  }

  interface User {
    name: string,
    home: string,
    dir: string,
  }

  interface OS {
    name: string,
    arch: string,
    version: string,
    processors: string,
    systemLoadAverage: string,
    totalPhysicalMemory: string,
    disks: Disk[],
  }

  interface Java {
    version: string,
    vendor: string,
    home: string,
    specificationName: string,
    specificationVersion: string,
    specificationVendor: string,
    classVersion: string,
    classPath: string,
    bootClassPath: string,
    library: string,
    tmpdir: string,
    extDirs: string,
  }

  interface JVM {
    name: string,
    version: string,
    vendor: string,
    specificationName: string,
    specificationVersion: string,
    specificationVendor: string,
    initMemory: string,
    committedMemory: string,
    useMemory: string,
    freeMemory: string,
    maxMemory: string,
    startTime: string,
    runtime: string,
    totalLoadedClassCount: string,
    loadedClassCount: string,
    unloadedClassCount: string,
    inputArguments: string[],
    thread: Thread,
  }

  interface Disk {
    path: string,
    totalSpace: string,
    freeSpace: string,
  }

  interface Thread {
    threadCount: number,
    peakThreadCount: number,
    totalStartedThreadCount: string,
    daemonThreadCount: number,
  }
}