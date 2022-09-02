import Head from 'next/head'
import Link from 'next/link'
import Image from 'next/image'
import { useRouter } from 'next/router'
import React, { useEffect, useState } from 'react'
import type { NextApiRequest, NextApiResponse } from 'next'
import { Button, Col, Form, Input, message, Row, Space, Typography } from 'antd'
import { CheckSquareOutlined, UserOutlined, } from '@ant-design/icons'
import Layout from '../../../components/layout'
import styles from '../../../styles/auth/SignIn.module.css'
import APIResult = API.APIResult
import SignUpActivationAo = API.SignUpActivationAo
import * as api from '../../../services/api'

const { Title } = Typography

const SignUpActivation = async (req: NextApiRequest, res: NextApiResponse, values: SignUpActivationAo): Promise<string> => {
  if (typeof window !== 'undefined') {
    // 登录
    const result: APIResult<any> = await api.signUpActivation(values)
    if (result.code != 200) {
      message.error(result.message)
    } else {
      message.success('激活成功，正在跳转登录页面')
      window.location.replace('/auth/signIn')
    }
    return ""
  } else {
    message.error("非浏览器运行环境，程序被终止")
    return "非浏览器运行环境，程序被终止"
  }
}

const SignUpActivationPage = (req: NextApiRequest, res: NextApiResponse) => {
  const [form] = Form.useForm()
  const router = useRouter()
  const { code } = router.query
  const [loading, setLoading] = useState<boolean>(false)

  useEffect(() => {
    if (code) {
      form.setFieldValue('code', code)
    }
  })

  return (
    <>
      <Head>
        <title>{`激活您的账号 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
        <meta name="description" content={`激活您的账号 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`} />
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
                  width={898.09814}
                  height={398.74219}
                />
              </Col>
              <Col md={9} sm={12} xs={24}>
                <Title level={3} style={{ fontWeight: '100' }}>现在开始激活您的账号</Title>
                <p style={{ color: 'rgba(0,0,0,0.6)', fontSize: '12px' }}>
                  请输入您注册时填写的邮箱或手机号、验证码
                </p>
                <Form
                  form={form}
                  name="SignUpForm"
                  initialValues={{ remember: true }}
                  autoComplete="off"
                  onFinish={async (values) => {
                    setLoading(true)
                    if (!values.emailOrPhone || !values.code) {
                      message.error('请填写账号与验证码')
                      setLoading(false)
                      return false
                    }
                    const rtn = await SignUpActivation(req, res, values)
                    // TODO
                    setLoading(false)
                  }}
                >
                  <Form.Item
                    name="emailOrPhone"
                    rules={[{ required: true, message: '请输入您注册时填写的邮箱或手机号！' }]}
                  >
                    <Input
                      placeholder="注册时填写的邮箱或手机号"
                      prefix={<UserOutlined />}
                    />
                  </Form.Item>

                  <Form.Item
                    name="code"
                    rules={[{ required: true, message: '请输入您的电子邮箱！' }]}
                  >
                    <Input
                      placeholder="验证码"
                      prefix={<CheckSquareOutlined />}
                    />
                  </Form.Item>

                  <Form.Item shouldUpdate>
                    {() => (
                      <Space>
                        <Button
                          type="primary"
                          shape="round"
                          htmlType="submit"
                          loading={loading}
                        >
                          激活账号
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
    </>
  )
}

SignUpActivationPage.getLayout = (page: any) => {
  return (
    <Layout>
      {page}
    </Layout>
  )
}

export default SignUpActivationPage