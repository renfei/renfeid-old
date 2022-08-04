import Head from 'next/head'
import nookies from 'nookies'
import React, { useEffect, useState } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Result, Typography, Descriptions, Tabs } from 'antd'
import DashboardLayout from "../../../components/layout/dashboard"
import DashPageHeader from "../../../components/layout/dashboard/DashPageHeader"
import * as api from "../../../services/api/dashboard/api"
import { convertToHeaders } from "../../../utils/request"
import APIResult = API.APIResult
import EnvironmentInfo = Environment.EnvironmentInfo

const { Text } = Typography
const { TabPane } = Tabs

const routes = [
  {
    path: '/dashboard',
    breadcrumbName: '控制面板',
  },
  {
    path: 'javascript:void(0)',
    breadcrumbName: '系统设置',
  },
  {
    path: '/dashboard/sys/environment',
    breadcrumbName: '运行环境信息',
  },
]

export const getServerSideProps: GetServerSideProps = async (context: any) => {
  const accessToken = nookies.get(context)['accessToken']
  if (!accessToken) {
    return {
      redirect: {
        destination: '/auth/signIn?redirect=' + context.req.url,
        permanent: false,
      },
    }
  }

  let environmentInfo: EnvironmentInfo | undefined = undefined
  const resultEnvironmentInfo: APIResult<EnvironmentInfo> = await api.getEnvironmentInfo(accessToken, convertToHeaders(context.req.headers))
  if (resultEnvironmentInfo.code == 401) {
    return {
      redirect: {
        destination: '/auth/signIn?redirect=' + context.req.url,
        permanent: false,
      },
    }
  }
  if (resultEnvironmentInfo.code == 200) {
    environmentInfo = resultEnvironmentInfo.data
  }
  return {
    props: {
      data: {
        environmentInfo: environmentInfo,
        serverMessage: resultEnvironmentInfo.message
      }
    }
  }
}

const DashboardSysEnvironment = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  const [environmentInfo, setEnvironmentInfo] = useState<EnvironmentInfo | undefined>(data.environmentInfo)
  const [serverMessage, setServerMessage] = useState<string>(data.serverMessage)
  const [browserName, setBrowserName] = useState<string>()


  useEffect(() => {
    setBrowserName(getBrowser())
  }, [browserName])

  const getBrowser = () => {
    let userAgent = window.navigator.userAgent;
    let browserName;
    if (userAgent.indexOf("Firefox") > -1) {
      browserName = "Mozilla Firefox";
    } else if (userAgent.indexOf("Opera") > -1 || userAgent.indexOf("OPR") > -1) {
      browserName = "Opera";
    } else if (userAgent.indexOf("Trident") > -1) {
      browserName = "Microsoft Internet Explorer";
    } else if (userAgent.indexOf("Edge") > -1) {
      browserName = "Microsoft Edge";
    } else if (userAgent.indexOf("Chrome") > -1) {
      browserName = "Google Chrome or Chromium";
    } else if (userAgent.indexOf("Safari") > -1) {
      browserName = "Apple Safari";
    } else {
      browserName = "unknown";
    }
    return browserName
  }

  return (
    <>
      <Head>
        <title>运行环境信息 - 系统设置</title>
      </Head>

      <div style={{ backgroundColor: '#fff' }}>
        <DashPageHeader
          title="运行环境信息"
          routes={routes}
          subTitle="系统运行环境信息"
        />
      </div>

      <div style={{ padding: '23px' }}>
        <div style={{ backgroundColor: '#fff', padding: '24px', marginBottom: '16px' }}>
          说明：如果系统架构为分布式部署，此处展示的仅为当前请求命中的节点数据，并不是整个集群的数据。此处数据仅为程序自动感知得来，受限于系统权限，数据可能并不准确，仅供参考。
        </div>
        <div style={{ backgroundColor: '#fff', padding: '24px', marginBottom: '16px' }}>
          <Tabs type="card">
            <TabPane tab="后端运行环境" key="1" style={{ padding: '0 10px' }}>
              {
                environmentInfo ? (
                  <Tabs defaultActiveKey="1">
                    <TabPane tab="JVM信息" key="jvm">
                      <Descriptions bordered>
                        <Descriptions.Item label="实现名称">{environmentInfo.jvm.name}</Descriptions.Item>
                        <Descriptions.Item label="实现版本">{environmentInfo.jvm.version}</Descriptions.Item>
                        <Descriptions.Item label="实现供应商">{environmentInfo.jvm.vendor}</Descriptions.Item>
                        <Descriptions.Item label="实现规范名称">{environmentInfo.jvm.specificationName}</Descriptions.Item>
                        <Descriptions.Item label="实现规范版本">{environmentInfo.jvm.specificationVersion}</Descriptions.Item>
                        <Descriptions.Item label="实现规供应商">{environmentInfo.jvm.specificationVendor}</Descriptions.Item>
                        <Descriptions.Item label="初始内存空间">{environmentInfo.jvm.initMemory}</Descriptions.Item>
                        <Descriptions.Item label="已申请的内存空间">{environmentInfo.jvm.committedMemory}</Descriptions.Item>
                        <Descriptions.Item label="已使用内存空间">{environmentInfo.jvm.useMemory}</Descriptions.Item>
                        <Descriptions.Item label="空闲内存空间">{environmentInfo.jvm.freeMemory}</Descriptions.Item>
                        <Descriptions.Item label="可申请最大内存空间">{environmentInfo.jvm.maxMemory}</Descriptions.Item>
                        <Descriptions.Item label="启动时间">{environmentInfo.jvm.startTime}</Descriptions.Item>
                        <Descriptions.Item label="运行时间（毫秒）">{environmentInfo.jvm.runtime}</Descriptions.Item>
                        <Descriptions.Item label="已加载类总数量">{environmentInfo.jvm.totalLoadedClassCount}</Descriptions.Item>
                        <Descriptions.Item label="已卸载类总数量">{environmentInfo.jvm.unloadedClassCount}</Descriptions.Item>
                        <Descriptions.Item label="JVM启动参数">
                          <Text
                            style={{ width: 200 }}
                            ellipsis={{ tooltip: environmentInfo.jvm.inputArguments }}
                          >
                            {environmentInfo.jvm.inputArguments}
                          </Text>
                        </Descriptions.Item>
                        <Descriptions.Item label="活动的线程总数">{environmentInfo.jvm.thread.threadCount}</Descriptions.Item>
                        <Descriptions.Item label="峰值线程数">{environmentInfo.jvm.thread.peakThreadCount}</Descriptions.Item>
                        <Descriptions.Item label="JVM启动以来创建和启动的线程总数">{environmentInfo.jvm.thread.totalStartedThreadCount}</Descriptions.Item>
                        <Descriptions.Item label="守护线程数">{environmentInfo.jvm.thread.daemonThreadCount}</Descriptions.Item>
                      </Descriptions>
                    </TabPane>
                    <TabPane tab="Java信息" key="java">
                      <Descriptions bordered>
                        <Descriptions.Item label="运行时版本">{environmentInfo.java.version}</Descriptions.Item>
                        <Descriptions.Item label="运行时供应商">{environmentInfo.java.vendor}</Descriptions.Item>
                        <Descriptions.Item label="安装目录">{environmentInfo.java.home}</Descriptions.Item>
                        <Descriptions.Item label="运行时规范名称">{environmentInfo.java.specificationName}</Descriptions.Item>
                        <Descriptions.Item label="运行时规范版本">{environmentInfo.java.specificationVersion}</Descriptions.Item>
                        <Descriptions.Item label="运行时规范供应商">{environmentInfo.java.specificationVendor}</Descriptions.Item>
                        <Descriptions.Item label="类格式版本号">{environmentInfo.java.classVersion}</Descriptions.Item>
                        <Descriptions.Item label="Java类路径">
                          <Text
                            style={{ width: 200 }}
                            ellipsis={{ tooltip: environmentInfo.java.classPath }}
                          >
                            {environmentInfo.java.classPath}
                          </Text>
                        </Descriptions.Item>
                        <Descriptions.Item label="引导类路径">{environmentInfo.java.bootClassPath}</Descriptions.Item>
                        <Descriptions.Item label="运行时加载库路径">
                          <Text
                            style={{ width: 200 }}
                            ellipsis={{ tooltip: environmentInfo.java.library }}
                          >
                            {environmentInfo.java.library}
                          </Text>
                        </Descriptions.Item>
                        <Descriptions.Item label="默认临时文件路径">
                          <Text
                            style={{ width: 200 }}
                            ellipsis={{ tooltip: environmentInfo.java.tmpdir }}
                          >
                            {environmentInfo.java.tmpdir}
                          </Text>
                        </Descriptions.Item>
                        <Descriptions.Item label="运行时扩展目录">{environmentInfo.java.extDirs}</Descriptions.Item>
                      </Descriptions>
                    </TabPane>
                    <TabPane tab="操作系统" key="os">
                      <Descriptions bordered>
                        <Descriptions.Item label="操作系统">{environmentInfo.os.name}</Descriptions.Item>
                        <Descriptions.Item label="系统架构">{environmentInfo.os.arch}</Descriptions.Item>
                        <Descriptions.Item label="系统版本">{environmentInfo.os.version}</Descriptions.Item>
                        <Descriptions.Item label="CPU核心/线程数">{environmentInfo.os.processors}</Descriptions.Item>
                        <Descriptions.Item label="一分钟内平均负载">{environmentInfo.os.systemLoadAverage}</Descriptions.Item>
                        <Descriptions.Item label="总物理内存">{environmentInfo.os.totalPhysicalMemory}</Descriptions.Item>
                        {
                          environmentInfo.os.disks.map(element => (
                            <>
                              <Descriptions.Item label="磁盘路径/总容量/剩余容量" span={2}>{element.path}；{element.totalSpace}；{element.freeSpace}</Descriptions.Item>
                            </>
                          ))
                        }
                      </Descriptions>
                    </TabPane>
                    <TabPane tab="用户信息" key="user">
                      <Descriptions bordered>
                        <Descriptions.Item label="用户名">{environmentInfo.user.name}</Descriptions.Item>
                        <Descriptions.Item label="用户目录">{environmentInfo.user.home}</Descriptions.Item>
                        <Descriptions.Item label="工作目录">{environmentInfo.user.dir}</Descriptions.Item>
                      </Descriptions>
                    </TabPane>
                  </Tabs>
                ) : (
                  <Result
                    status="error"
                    title="错误"
                    subTitle={serverMessage}
                  />
                )
              }
            </TabPane>
            <TabPane tab="前端运行环境" key="2">
              暂无
            </TabPane>
            <TabPane tab="客户端运行环境" key="3">
              <Descriptions bordered>
                <Descriptions.Item label="浏览器类型">{browserName}</Descriptions.Item>
                {
                  (typeof window !== 'undefined') ? (
                    <>
                      <Descriptions.Item label="启用Cookie">{window.navigator.cookieEnabled ? '启用' : '禁用'}</Descriptions.Item>
                      <Descriptions.Item label="禁止跟踪">{window.navigator.doNotTrack}</Descriptions.Item>
                      <Descriptions.Item label="处理器数量">{window.navigator.hardwareConcurrency}</Descriptions.Item>
                      <Descriptions.Item label="语言">{window.navigator.language}</Descriptions.Item>
                      <Descriptions.Item label="多点触控">{window.navigator.maxTouchPoints}</Descriptions.Item>
                      <Descriptions.Item label="是否在线">{window.navigator.onLine ? '是' : '否'}</Descriptions.Item>
                      <Descriptions.Item label="PDF预览支持">{window.navigator.pdfViewerEnabled ? '是' : '否'}</Descriptions.Item>
                      <Descriptions.Item label="设备供应商">{window.navigator.vendor}</Descriptions.Item>
                    </>
                  ) : ''
                }

              </Descriptions>
            </TabPane>
          </Tabs>
        </div>
      </div>
    </>
  )
}

DashboardSysEnvironment.getLayout = (page: any) => {
  return (
    <DashboardLayout>
      {page}
    </DashboardLayout>
  )
}

export default DashboardSysEnvironment