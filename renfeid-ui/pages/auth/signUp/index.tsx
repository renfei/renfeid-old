import Head from 'next/head'
import Link from 'next/link'
import Image from 'next/image'
import { GetServerSideProps } from 'next'
import { parseCookies, setCookie } from 'nookies'
import { useRouter } from 'next/router'
import React, { useEffect, useState } from 'react'
import type { NextApiRequest, NextApiResponse } from 'next'
import { Button, Col, Form, Input, message, Row, Space, Typography } from 'antd'
import Layout from '../../../components/layout'
import styles from '../../../styles/auth/SignIn.module.css'
import { KeyOutlined, LockOutlined, UserOutlined, } from '@ant-design/icons'
import { encrypt } from "../../../utils/encryption"
import SignUpAo = API.SignUpAo
import * as api from "../../../services/api"
import CheckSignInStatus from '../../../utils/CheckSignInStatus'
import UserInfo = API.UserInfo
import APIResult = API.APIResult

const { Title } = Typography

let redirectPath: string = '/'

const SignUpSubmit = async (req: NextApiRequest, res: NextApiResponse, values: SignUpAo): Promise<string> => {
  if (typeof window !== 'undefined') {
    if (values.plainPassword != values.plainPassword2) {
      message.error('两次输入的密码不一致')
      return 'false'
    }
    await encrypt(values.plainPassword).then(res => {
      values.keyUuid = res?.secretKey.uuid || ''
      values.password = res?.value || ''
    })
    // 登录
    const SignUpVo: APIResult<any> = await api.signUp(values)
    if (SignUpVo.code == 402) {
      return '402'
    } else if (SignUpVo.code != 200) {
      if (SignUpVo.message == "AESKeyId不存在") {
        window.localStorage.removeItem("aesKeyJson")
        return SignUpSubmit(req, res, values)
      }
      message.error(SignUpVo.message)
      return SignUpVo.message
    } else {
      // TODO
      message.success('注册成功！')
      gotoRedirect('/auth/signIn')
    }
    gotoRedirect(redirectPath)
    return ""
  } else {
    message.error("非浏览器运行环境，程序被终止")
    return "非浏览器运行环境，程序被终止"
  }
}

const gotoRedirect = (redirect: string | string[] | undefined) => {
  if (typeof window !== 'undefined') {
    if (redirect && typeof redirect == 'string') {
      window.location.replace(redirect)
    } else {
      window.location.replace('/')
    }
  }
}

export const getServerSideProps: GetServerSideProps = async (context: any) => {
  const userInfo: UserInfo | null = await CheckSignInStatus(context)
  if (userInfo) {
    return {
      redirect: {
        destination: '/',
        permanent: false,
      },
    }
  } else {
    return {
      props: {}
    }
  }
}

const SignUpPage = (req: NextApiRequest, res: NextApiResponse) => {
  const [form] = Form.useForm()
  const router = useRouter()
  const { redirect } = router.query
  const [showMFA, setShowMFA] = useState<boolean>(false)
  const [loading, setLoading] = useState<boolean>(false)

  if (redirect) {
    redirectPath = redirect.toString()
  }

  useEffect(() => {
    if (typeof window !== 'undefined') {
      const cookies = parseCookies()
      if (cookies['accessToken']) {
        // 存在 Token，已经登录了，直接跳转
        gotoRedirect('/')
      }
    }
  })

  return (
    <>
      <div>
        <Head>
          <title>{`注册 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
          <meta name="description" content={`注册 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`} />
        </Head>

        <main style={{ backgroundColor: '#ffffff', padding: '60px 0' }}>
          <section className={"renfeid-content"}>
            <div className={styles.signInForm}>
              <Row>
                <Col md={15} sm={12} xs={24}
                  style={{ padding: '20px 20px 20px 0', display: 'flex', justifyContent: 'center' }}>
                  <Image
                    src={"/image/hire_re_gn5j.svg"}
                    alt="SignUp"
                    style={{ width: '100%', height: 'auto' }}
                    width={898.09814}
                    height={398.74219}
                  />
                </Col>
                <Col md={9} sm={12} xs={24}>
                  <Title level={3} style={{ fontWeight: '100' }}>创建您的账户</Title>
                  <p style={{ color: 'rgba(0,0,0,0.6)', fontSize: '12px' }}>
                    Join RenFei.Net
                  </p>
                  <Form
                    form={form}
                    name="SignUpForm"
                    initialValues={{ remember: true }}
                    autoComplete="off"
                    onFinish={async (values) => {
                      setLoading(true)
                      const rtn = await SignUpSubmit(req, res, values)
                      if (rtn == '402') {
                        setShowMFA(true)
                      }
                      setLoading(false)
                    }}
                  >
                    <Form.Item
                      name="userName"
                      rules={[{ required: true, message: '请输入您的账号！' }]}
                      extra={(<Typography.Text type="secondary" style={{ fontSize: '11px' }}>账号一旦注册不可修改。</Typography.Text>)}
                    >
                      <Input
                        placeholder="账号"
                        autoComplete='username'
                        prefix={<UserOutlined />}
                      />
                    </Form.Item>

                    <Form.Item
                      name="email"
                      rules={[{ required: true, message: '请输入您的电子邮箱！' }]}
                      extra={(<Typography.Text type="secondary" style={{ fontSize: '11px' }}>请输入真实的电子邮箱，注册后需要您到电子邮箱内点击激活链接。</Typography.Text>)}
                    >
                      <Input
                        placeholder="电子邮箱"
                        autoComplete='email'
                        prefix={<LockOutlined />}
                      />
                    </Form.Item>

                    <Form.Item
                      name="plainPassword"
                      rules={[{ required: true, message: '请输入您的密码！' }]}
                    >
                      <Input.Password
                        placeholder="密码"
                        autoComplete='new-password'
                        prefix={<LockOutlined />}
                      />
                    </Form.Item>

                    <Form.Item
                      name="plainPassword2"
                      rules={[{ required: true, message: '请输入您的密码！' }]}
                    >
                      <Input.Password
                        placeholder="重复密码"
                        autoComplete='new-password'
                        prefix={<LockOutlined />}
                      />
                    </Form.Item>

                    {
                      showMFA ? (
                        <Form.Item
                          name="tOtp"
                          rules={[{ required: false, message: 'Please input your username!' }]}
                        >
                          <Input
                            placeholder="MFA"
                            autoComplete='one-time-code'
                            showCount maxLength={6}
                            prefix={<KeyOutlined />}
                          />
                        </Form.Item>
                      ) : ''
                    }

                    <Form.Item shouldUpdate>
                      {() => (
                        <Space>
                          <Button
                            type="primary"
                            shape="round"
                            htmlType="submit"
                            loading={loading}
                            disabled={
                              !form.isFieldsTouched(true) ||
                              !!form.getFieldsError().filter(({ errors }) => errors.length).length
                            }
                          >
                            注册账号
                          </Button>
                          <Button shape="round" href="/auth/signIn">
                            登录现有账号
                          </Button>
                        </Space>
                      )}

                    </Form.Item>
                  </Form>
                </Col>
              </Row>
            </div>
          </section>
        </main>
      </div>
    </>
  )
}

SignUpPage.getLayout = (page: any) => {
  return (
    <Layout>
      {page}
    </Layout>
  )
}

export default SignUpPage