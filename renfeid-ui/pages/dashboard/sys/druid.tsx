import Head from 'next/head'
import nookies from 'nookies'
import React, { useEffect } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Typography, Tabs } from 'antd'
import DashboardLayout from "../../../components/layout/dashboard"
import DashPageHeader from "../../../components/layout/dashboard/DashPageHeader"

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
    path: '/dashboard/sys/druid',
    breadcrumbName: '数据库连接池',
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

  return {
    props: {
    }
  }
}

const DashboardSysDruid = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  useEffect(() => {
    let apiContent = document.getElementById('apiContent')
    if (apiContent) {
      apiContent.setAttribute('height', (window.innerHeight - 152) + "px")
      apiContent.setAttribute('width', (window.innerWidth - 200) + "px")
    }
  })

  return (
    <>
      <Head>
        <title>数据库连接池 - 系统设置</title>
      </Head>

      <div style={{ backgroundColor: '#fff' }}>
        <DashPageHeader
          title="数据库连接池"
          routes={routes}
          subTitle="系统数据库连接池"
        />
      </div>

      {/* 此处需要配置 Nginx 反代后端的 /druid 页面 */}
      <embed id="apiContent" src='/druid' />
    </>
  )
}

DashboardSysDruid.getLayout = (page: any) => {
  return (
    <DashboardLayout>
      {page}
    </DashboardLayout>
  )
}

export default DashboardSysDruid