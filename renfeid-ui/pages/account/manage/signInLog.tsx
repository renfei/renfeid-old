import Head from 'next/head'
import nookies from 'nookies'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Col, Row, Typography, Table, Input, Button, message } from 'antd'
import Layout from "../../../components/layout"
import CheckSignInStatus from '../../../utils/CheckSignInStatus'
import type { ColumnsType } from 'antd/es/table'
import * as api from "../../../services/api"
import APIResult = API.APIResult
import ListData = API.ListData
import UserInfo = API.UserInfo
import UserSignInLog = API.UserSignInLog
import { useState } from 'react'
import { convertToHeaders } from '../../../utils/request'

export const getServerSideProps: GetServerSideProps = async (context: any) => {
  const accessToken = nookies.get(context)['accessToken']
  const userInfo: UserInfo | null = await CheckSignInStatus(context)
  if (!accessToken || !userInfo) {
    nookies.destroy(context, 'accessToken')
    return {
      redirect: {
        destination: '/auth/signIn?redirect=' + context.req.url,
        permanent: false,
      },
    }
  }
  const resultUserSignInLog: APIResult<ListData<UserSignInLog>> = await api.queryCurrentUserSignInLog(convertToHeaders(context.req.headers, context.req.socket.remoteAddress), 1, 10, accessToken)
  return {
    props: {
      data: {
        userInfo: userInfo,
        resultUserSignInLog: resultUserSignInLog.data,
      }
    }
  }
}

const AccountManagePage = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  const [resultUserSignInLog, setResultUserSignInLog] = useState<UserSignInLog[]>(data.resultUserSignInLog.data)
  const [loading, setLoading] = useState<boolean>(false)
  const [userInfo, setUserInfo] = useState<UserInfo>(data.userInfo)

  const columns: ColumnsType<UserSignInLog> = [
    {
      title: '时间',
      dataIndex: 'logTime',
      key: 'logTime',
      width: 170,
    },
    {
      title: '地点',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: 'IP地址',
      dataIndex: 'requIp',
      key: 'requIp',
    },
  ]

  return (
    <>
      <Head>
        <title>{`系统登录记录 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
      </Head>

      <main style={{ background: '#ffffff' }}>
        <section className={"renfeid-content"} style={{ paddingTop: '3rem!important', paddingBottom: '6rem' }}>
          <Row>
            <Col xs={24} md={6}>
              <Typography.Title level={2} style={{ color: '#666', fontWeight: '300' }}>系统登录记录</Typography.Title>
              <Typography.Text type="secondary" style={{ fontSize: '12px' }}>
                您可以在这里审查您的账户登录活动信息。
              </Typography.Text>
            </Col>
            <Col xs={24} md={18}>
              <Table
                columns={columns}
                dataSource={resultUserSignInLog}
                loading={loading}
                scroll={{ x: true }}
              />
            </Col>
          </Row>
        </section>
      </main>
    </>
  )
}

AccountManagePage.getLayout = (page: any) => {
  return (
    <Layout>
      {page}
    </Layout>
  )
}

export default AccountManagePage