import Head from 'next/head'
import nookies from 'nookies'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Col, Row, Typography, Form, Input, Button, message } from 'antd'
import Layout from "../../../components/layout"
import CheckSignInStatus from '../../../utils/CheckSignInStatus'
import * as api from "../../../services/api"
import { QRCodeSVG } from 'qrcode.react'
import { encrypt } from "../../../utils/encryption"
import APIResult = API.APIResult
import UserInfo = API.UserInfo
import TotpAo = API.TotpAo
import TotpVo = API.TotpVo
import { convertToHeaders } from '../../../utils/request'
import { useState } from 'react'

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
  const totpResult: APIResult<TotpVo> = await api.generateU2FSecretKey(convertToHeaders(context.req.headers, context.req.socket.remoteAddress), accessToken)
  return {
    props: {
      data: {
        userInfo: userInfo,
        totpResult: totpResult.data,
      }
    }
  }
}

const AccountManagePage = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  const [form] = Form.useForm()
  const [loading, setLoading] = useState<boolean>(false)
  const [userInfo, setUserInfo] = useState<UserInfo>(data.userInfo)
  const [totp] = useState<TotpVo>(data.totpResult)

  const open = async () => {
    setLoading(true)
    if (!form.getFieldValue('password')) {
      message.error('请输入账号的密码')
      setLoading(false)
      return
    }
    if (!form.getFieldValue('totp')) {
      message.error('请输入两步认证码')
      setLoading(false)
      return
    }
    let totpAo: TotpAo = {
      pwd: '',
      totp: '',
      keyId: '',
      secretKey: ''
    }
    await encrypt(form.getFieldValue('password')).then(res => {
      totpAo.keyId = res?.secretKey.uuid || ''
      totpAo.pwd = res?.value || ''
    })
    await encrypt(totp.secretKey).then(res => {
      totpAo.keyId = res?.secretKey.uuid || ''
      totpAo.secretKey = res?.value || ''
    })
    totpAo.totp = form.getFieldValue('totp')
    let res: APIResult<any> = await api.openU2f(totpAo)
    if (res.code == 200) {
      userInfo.u2fEnable = true
      setUserInfo(userInfo)
      form.resetFields()
    } else {
      message.error(res.message)
    }
    setLoading(false)
  }

  const close = async () => {
    setLoading(true)
    if (!form.getFieldValue('password')) {
      message.error('请输入账号的密码')
      setLoading(false)
      return
    }
    if (!form.getFieldValue('totp')) {
      message.error('请输入两步认证码')
      setLoading(false)
      return
    }
    let totpAo: TotpAo = {
      pwd: '',
      totp: '',
      keyId: '',
    }
    await encrypt(form.getFieldValue('password')).then(res => {
      totpAo.keyId = res?.secretKey.uuid || ''
      totpAo.pwd = res?.value || ''
    })
    totpAo.totp = form.getFieldValue('totp')
    let res: APIResult<any> = await api.closeU2f(totpAo)
    if (res.code == 200) {
      userInfo.u2fEnable = false
      setUserInfo(userInfo)
      form.resetFields()
    } else {
      message.error(res.message)
    }
    setLoading(false)
  }

  return (
    <>
      <Head>
        <title>{`管理您的两步认证（U2F） - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
      </Head>

      <main style={{ background: '#ffffff' }}>
        <section className={"renfeid-content"} style={{ paddingTop: '3rem!important' }}>
          <Row>
            <Col xs={24} md={6}>
              <Typography.Title level={2} style={{ color: '#666', fontWeight: '300' }}>两步认证(U2F)</Typography.Title>
            </Col>
            <Col xs={24} md={12}>
              <Form
                labelCol={{ span: 5, offset: 0 }}
                form={form}>
                {
                  userInfo.u2fEnable ? (
                    <>
                      <Form.Item label="当前状态">
                        已开启
                      </Form.Item>
                      <Form.Item label="密码" name="password">
                        <Input.Password
                          placeholder="密码"
                          autoComplete='password'
                        />
                      </Form.Item>
                      <Form.Item label="两步认证码" name="totp">
                        <Input
                          autoComplete='one-time-code'
                          showCount maxLength={6}
                        />
                      </Form.Item>
                      <Form.Item label=" " colon={false}>
                        <Button type={'primary'} block danger loading={loading} onClick={close}>关闭两步认证(U2F)</Button>
                      </Form.Item>
                    </>
                  ) : (
                    <>
                      <Form.Item label="当前状态">
                        未开启
                      </Form.Item>
                      <Form.Item
                        label="二维码"
                        help="请使用 Google Authenticator、Authy或1Password 扫描二维码添加动态验证码"
                        tooltip="使用 Google Authenticator、Authy或1Password 等支持TOTP的APP扫描二维码添加动态验证码"
                      >
                        <QRCodeSVG
                          value={totp.totpString}
                        />
                      </Form.Item>
                      <Form.Item
                        label="秘钥"
                        tooltip="当无法扫描时，请复制TOTP秘钥，进行手动添加。"
                      >
                        {totp.secretKey}
                      </Form.Item>
                      <Form.Item label="密码" name="password">
                        <Input.Password
                          placeholder="密码"
                          autoComplete='password'
                        />
                      </Form.Item>
                      <Form.Item label="两步认证码" name="totp">
                        <Input
                          placeholder="请扫描二维码添加秘钥，将生成的动态验证码填入"
                          autoComplete='one-time-code'
                          showCount maxLength={6}
                        />
                      </Form.Item>
                      <Form.Item label=" " colon={false}>
                        <Typography.Text type="secondary" style={{ fontSize: '12px' }}>
                          注意：启用两步验证可使您的账户非常安全，但丢失两步认证以后，如果您没有其他证据证明您的身份（例如绑定手机号）将永久丢失账户。
                        </Typography.Text>
                      </Form.Item>
                      <Form.Item label=" " colon={false}>
                        <Button type={'primary'} block loading={loading} onClick={open}>开启两步认证(U2F)</Button>
                      </Form.Item>
                    </>
                  )
                }
              </Form>
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