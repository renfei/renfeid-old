import Head from 'next/head'
import Link from 'next/link'
import nookies from 'nookies'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Col, Row, Typography, Divider, Descriptions } from 'antd'
import Layout from "../../../components/layout"
import CheckSignInStatus from '../../../utils/CheckSignInStatus'
import * as api from "../../../services/api"
import APIResult = API.APIResult
import ListData = API.ListData
import UserInfo = API.UserInfo
import UserSignInLog = API.UserSignInLog
import { convertToHeaders } from '../../../utils/request'

export const getServerSideProps: GetServerSideProps = async (context: any) => {
  const userInfo: UserInfo | null = await CheckSignInStatus(context)
  if (!userInfo) {
    nookies.destroy(context, 'accessToken')
    return {
      redirect: {
        destination: '/auth/signIn?redirect=' + context.req.url,
        permanent: false,
      },
    }
  }
  const accessToken = nookies.get(context)['accessToken']
  const resultUserSignInLog: APIResult<ListData<UserSignInLog>> = await api.queryCurrentUserSignInLog(convertToHeaders(context.req.headers, context.req.socket.remoteAddress), 1, 1, accessToken)
  return {
    props: {
      data: {
        userInfo: userInfo,
        userSignInLog: resultUserSignInLog.data,
      }
    }
  }
}

const AccountManagePage = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  return (
    <>
      <Head>
        <title>{`管理您的账户 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
        <meta name="description" content={`关于${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`} />
      </Head>

      <main style={{ background: '#ffffff' }}>
        <div style={{ background: "url('https://cdn.renfei.net/thunder/img/account_bg_blur.jpg') no-repeat", backgroundSize: 'auto', backgroundPosition: 'bottom center' }}>
          <section className={"renfeid-content"} style={{ color: '#ffffff', height: '180px' }}>
            <Row>
              <Col span={24} style={{ paddingTop: '40px' }}>
                <Typography.Title level={1} style={{ color: '#ffffff' }}>{data.userInfo.username}</Typography.Title>
                <Typography.Title level={5} style={{ color: '#ffffff' }}>欢迎回来！您可以在这里管理您的账户</Typography.Title>
              </Col>
            </Row>
          </section>
        </div>
        <section className={"renfeid-content"} style={{ paddingTop: '3rem!important' }}>
          <Row>
            <Col sm={6}>
              <Typography.Title level={2} style={{ color: '#666', fontWeight: '300' }}>账户</Typography.Title>
            </Col>
            <Col sm={18}>
              <Descriptions layout="vertical"
                column={2}
                labelStyle={{ color: '#1d1d1f', fontSize: '17px' }}
                contentStyle={{ display: 'block', fontSize: '14px' }}
              >
                <Descriptions.Item label="ID" span={2}>
                  {data.userInfo.uuid}
                </Descriptions.Item>
                <Descriptions.Item label="用户名">{data.userInfo.username}</Descriptions.Item>
                <Descriptions.Item label="电子邮箱">
                  <div>
                    {data.userInfo.email}
                  </div>
                  <div>
                    <Link href="/account/manage/email" style={{ color: '#1890ff', fontSize: '12px' }}>
                      修改电子邮箱地址...
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="注册时间">{data.userInfo.registrationDate}</Descriptions.Item>
                <Descriptions.Item label="手机号码">
                  <div>
                    {data.userInfo.phone}
                  </div>
                  <div>
                    <Link href="/account/manage/phone" style={{ color: '#1890ff', fontSize: '12px' }}>
                      修改手机号码...
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="姓氏">
                  <div>
                    {data.userInfo.lastName}
                  </div>
                  <div>
                    <Link href="/account/manage/name" style={{ color: '#1890ff', fontSize: '12px' }}>
                      修改我的姓氏...
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="名字">
                  <div>
                    {data.userInfo.firstName}
                  </div>
                  <div>
                    <Link href="/account/manage/name" style={{ color: '#1890ff', fontSize: '12px' }}>
                      修改我的名字...
                    </Link>
                  </div>
                </Descriptions.Item>
              </Descriptions>
            </Col>
          </Row>
          <Divider />
          <Row>
            <Col sm={6}>
              <Typography.Title level={2} style={{ color: '#666', fontWeight: '300' }}>安全</Typography.Title>
            </Col>
            <Col sm={18}>
              <Descriptions layout="vertical"
                column={2}
                labelStyle={{ color: '#1d1d1f', fontSize: '17px' }}
                contentStyle={{ display: 'block', fontSize: '14px' }}
              >
                <Descriptions.Item label="密码" span={2}>
                  <div>
                    ********
                  </div>
                  <div>
                    <Link href="/account/manage/password" style={{ color: '#1890ff', fontSize: '12px' }}>
                      更改密码...
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="两步认证(U2F)" span={2}>
                  <div>
                    {
                      data.userInfo.u2fEnable ? '已开启' : '未开启'
                    }
                  </div>
                  <div>
                    <Link href="/account/manage/u2f" style={{ color: '#1890ff', fontSize: '12px' }}>
                      更改两步认证...
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="最近登录记录" span={2}>
                  <div>
                    {
                      data.userSignInLog?.data ? (
                        <>
                          {data.userSignInLog.data[0].logTime} 在 {data.userSignInLog.data[0].address ? data.userSignInLog.data[0].address : '未知地点'} 登录系统。
                          登录地点 IP 地址：{data.userSignInLog.data[0].requIp}
                        </>
                      ) : '暂无记录'
                    }
                  </div>
                  <div>
                    <Link href="/account/manage/signInLog" style={{ color: '#1890ff', fontSize: '12px' }}>
                      查看更多登录记录...
                    </Link>
                  </div>
                </Descriptions.Item>
              </Descriptions>
            </Col>
          </Row>
          <Divider />
          <Row>
            <Col sm={6}>
              <Typography.Title level={2} style={{ color: '#666', fontWeight: '300' }}>论坛</Typography.Title>
            </Col>
            <Col sm={18}>
              <Descriptions layout="vertical"
                column={2}
                labelStyle={{ color: '#1d1d1f', fontSize: '17px' }}
                contentStyle={{ display: 'block', fontSize: '14px' }}
              >
                <Descriptions.Item label="用户组">
                  <div>
                    管理员
                  </div>
                  <div>
                    <Link href="https://bbs.renfei.net/home.php?mod=spacecp&ac=usergroup" style={{ color: '#1890ff', fontSize: '12px' }} target={'_blank'}>
                      前往查看...
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="积分">
                  <div>
                    3521
                  </div>
                  <div>
                    <Link href="https://bbs.renfei.net/home.php?mod=spacecp&ac=credit" style={{ color: '#1890ff', fontSize: '12px' }} target={'_blank'}>
                      前往查看...
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="帖子数">
                  <div>
                    3521
                  </div>
                  <div>
                    <Link href="https://bbs.renfei.net/forum.php?mod=guide&view=my" style={{ color: '#1890ff', fontSize: '12px' }} target={'_blank'}>
                      前往查看...
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="金钱">3521</Descriptions.Item>
                <Descriptions.Item label="在线时间">
                  <div>
                    3521
                  </div>
                  <div>
                    <Link href="https://bbs.renfei.net/home.php?mod=space&uid=1&do=profile" style={{ color: '#1890ff', fontSize: '12px' }} target={'_blank'}>
                      前往查看...
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="威望">3521</Descriptions.Item>
                <Descriptions.Item label="精华数">3521</Descriptions.Item>
                <Descriptions.Item label="贡献">3521</Descriptions.Item>
              </Descriptions>
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