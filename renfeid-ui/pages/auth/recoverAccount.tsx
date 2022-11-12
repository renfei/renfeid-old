import Head from 'next/head'
import Image from 'next/image'
import React, { useEffect, useState } from 'react'
import type { NextApiRequest, NextApiResponse } from 'next'
import { Button, Col, Form, Input, message, Row, Space, Typography, Radio } from 'antd'
import type { RadioChangeEvent } from 'antd'
import Layout from '../../components/layout'
import styles from '../../styles/auth/SignIn.module.css'
import { encrypt } from "../../utils/encryption"
import SignInAo = API.SignInAo
import * as api from "../../services/api"
import SignInVo = API.SignInVo
import APIResult = API.APIResult
import FindUsernameAo = API.FindUsernameAo
import ResetPasswordAo = API.ResetPasswordAo

const { Title } = Typography

let redirectPath: string = '/'

const signInSubmit = async (req: NextApiRequest, res: NextApiResponse, values: SignInAo): Promise<string> => {
  values.useVerCode = false
  if (typeof window !== 'undefined') {
    await encrypt(values.plainPassword).then(res => {
      values.keyUuid = res?.secretKey.uuid || ''
      values.password = res?.value || ''
    })
    // 登录
    const signInVo: APIResult<SignInVo> = await api.signIn(values)
    if (signInVo.code == 402) {
      return '402'
    } else if (signInVo.code != 200) {
      if (signInVo.message == "AESKeyId不存在") {
        window.localStorage.removeItem("aesKeyJson")
        return signInSubmit(req, res, values)
      }
      message.error(signInVo.message)
      return signInVo.message
    } else if (!signInVo.data) {
      message.error('数据异常，请重试')
      return signInVo.message
    } else {
      if (signInVo.data?.ucScript) {
        // TODO 登录 Discuz!
      }
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

const SignInPage = (req: NextApiRequest, res: NextApiResponse) => {
  const [form] = Form.useForm()
  const [findUsernameByPhoneForm] = Form.useForm()
  const [resetPasswordForm] = Form.useForm()
  const [type, setType] = useState(1);
  const [loading, setLoading] = useState<boolean>(false)
  const [stage, setStage] = useState<string>('start')
  const [emailOrPhone, setEmailOrPhone] = useState<string>('')

  const findAccount = async () => {
    if (!form.getFieldValue('userName')) {
      message.error('请输入您注册时预留的邮箱或手机号')
      return
    }
    setEmailOrPhone(form.getFieldValue('userName'))
    setLoading(true)
    if (type == 1) {
      // 忘记密码
      const res: APIResult<any> = await api.sendFindPasswordVerCode(form.getFieldValue('userName'))
      if (res.code == 200) {
        setStage('resetPassword')
      } else {
        message.error(res.message)
      }
    } else {
      // 忘记用户名
      const res: APIResult<any> = await api.sendFindUsernameVerCode(form.getFieldValue('userName'))
      if (res.code == 200) {
        const username: string = form.getFieldValue('userName')
        if (username.indexOf('@') > 0) {
          setStage('findUsernameByEmail')
        } else {
          setStage('findUsernameByPhone')
        }
      } else {
        message.error(res.message)
      }
    }
    setLoading(false)
  }

  const findUserByPhone = async () => {
    if (!findUsernameByPhoneForm.getFieldValue('verCode')) {
      message.error('请输入短信验证码')
      return
    }
    setLoading(true)
    const findUsername: FindUsernameAo = {
      phone: emailOrPhone,
      verCode: findUsernameByPhoneForm.getFieldValue('verCode')
    }
    const res: APIResult<string> = await api.findUsernameByVerCode(findUsername)
    if (res.code == 200) {
      findUsernameByPhoneForm.setFieldValue('username', res.data)
      message.success('找回成功')
    } else {
      message.error(res.message)
    }
    setLoading(false)
  }

  const resetPassword = async () => {
    if (!resetPasswordForm.getFieldValue('verCode')) {
      message.error('请输入短信验证码')
      return
    }
    if (!resetPasswordForm.getFieldValue('newPwd')) {
      message.error('请输入新密码')
      return
    }
    if (resetPasswordForm.getFieldValue('newPwd') != resetPasswordForm.getFieldValue('newPwd2')) {
      message.error('两次密码输入不一致，请重试')
      return
    }
    setLoading(true)
    let resetPassword: ResetPasswordAo = {
      password: '',
      tOtp: emailOrPhone,
      keyUuid: '',
      verCode: resetPasswordForm.getFieldValue('verCode')
    }
    await encrypt(resetPasswordForm.getFieldValue('newPwd')).then(res => {
      resetPassword.keyUuid = res?.secretKey.uuid || ''
      resetPassword.password = res?.value || ''
    })
    const res: APIResult<string> = await api.resetPasswordByVerCode(resetPassword)
    if (res.code == 200) {
      resetPasswordForm.setFieldValue('verCode', '')
      resetPasswordForm.setFieldValue('newPwd', '')
      resetPasswordForm.setFieldValue('newPwd2', '')
      message.success('密码重置成功')
    } else {
      message.error(res.message)
    }
    setLoading(false)
  }

  return (
    <>
      <div>
        <Head>
          <title>{`找回您的账号 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
          <meta name="description" content={`找回您的账号 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`} />
        </Head>

        <main style={{ backgroundColor: '#ffffff', padding: '60px 0' }}>
          <section className={"renfeid-content"}>
            <div className={styles.signInForm}>
              <Row>
                <Col md={15} sm={12} xs={24}
                  style={{ padding: '20px 20px 20px 0', display: 'flex', justifyContent: 'center' }}>
                  <Image
                    src={"/image/people_search_re_5rre.svg"}
                    style={{ width: '100%', height: 'auto' }}
                    alt="SignIn"
                    width={898.09814}
                    height={398.74219}
                  />
                </Col>
                <Col md={9} sm={12} xs={24}>
                  <Title level={3} style={{ fontWeight: '100' }}>找回您的账号</Title>
                  <p style={{ color: 'rgba(0,0,0,0.6)', fontSize: '12px' }}>
                    我们会尽力为您找回您的账户。
                  </p>
                  {
                    stage == 'start' ? (
                      <Form
                        form={form}
                        name="SignInForm"
                        initialValues={{ remember: true }}
                        autoComplete="off"
                      >
                        <Form.Item>
                          <Radio.Group onChange={(e: RadioChangeEvent) => {
                            setType(e.target.value)
                          }} value={type}>
                            <Radio value={1}>我忘记了密码</Radio>
                            <Radio value={2}>我忘记了用户名</Radio>
                          </Radio.Group>
                        </Form.Item>
                        <Form.Item
                          name="userName"
                          rules={[{ required: true, message: '请输入您注册时预留的邮箱或手机号' }]}
                        >
                          <Input
                            placeholder="您注册时预留的邮箱或手机号"
                          />
                        </Form.Item>
                        <Form.Item>
                          <Typography.Text type={'secondary'} style={{ fontSize: '12px' }}>
                            如果您填写对了您的邮箱或手机号，您将收到一封邮件或短信的验证码，通过验证码可以找回您的账户密码或用户名。
                          </Typography.Text>
                        </Form.Item>
                        <Form.Item shouldUpdate>
                          {() => (
                            <Space>
                              <Button
                                type="primary"
                                shape="round"
                                htmlType="submit"
                                loading={loading}
                                onClick={findAccount}
                                disabled={
                                  !form.isFieldsTouched(true) ||
                                  !!form.getFieldsError().filter(({ errors }) => errors.length).length
                                }
                              >
                                找回账号
                              </Button>
                              <Button shape="round" href="/auth/signUp">
                                注册账号
                              </Button>
                            </Space>
                          )}
                        </Form.Item>
                      </Form>
                    ) : ''
                  }
                  {
                    stage == 'resetPassword' ? (
                      <Form
                        form={resetPasswordForm}
                        layout={'vertical'}
                        initialValues={{ remember: true }}
                        autoComplete="off"
                      >
                        <Form.Item label="邮箱或手机号：">
                          <Input
                            readOnly
                            value={emailOrPhone}
                          />
                        </Form.Item>
                        <Form.Item
                          label="验证码："
                          name="verCode"
                          rules={[{ required: true, message: '请输入验证码' }]}
                        >
                          <Input />
                        </Form.Item>
                        <Form.Item
                          label="新密码："
                          name="newPwd"
                          rules={[{ required: true, message: '请输入新密码' }]}
                        >
                          <Input.Password
                            placeholder="新密码"
                            autoComplete='new-password'
                          />
                        </Form.Item>
                        <Form.Item
                          label="重复新密码："
                          name="newPwd2"
                          rules={[{ required: true, message: '请再次输入新密码' }]}
                        >
                          <Input.Password
                            placeholder="重复新密码"
                            autoComplete='new-password'
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
                                disabled={
                                  !resetPasswordForm.isFieldsTouched(true) ||
                                  !!resetPasswordForm.getFieldsError().filter(({ errors }) => errors.length).length
                                }
                                onClick={resetPassword}
                              >
                                重置密码
                              </Button>
                              <Button shape="round" href="/auth/signUp">
                                注册账号
                              </Button>
                            </Space>
                          )}
                        </Form.Item>
                      </Form>
                    ) : ''
                  }
                  {
                    stage == 'findUsernameByEmail' ? (
                      <Form>
                        <Form.Item>
                          <Typography.Text>
                            如果您填写对了您的邮箱，我们会将您的用户名发送到您的邮箱中，请查收邮件。
                          </Typography.Text>
                        </Form.Item>
                        <Form.Item shouldUpdate>
                          {() => (
                            <Space>
                              <Button
                                type="primary"
                                shape="round"
                                href='/auth/signIn'
                              >
                                去登录
                              </Button>
                              <Button shape="round" href="/auth/signUp">
                                注册账号
                              </Button>
                            </Space>
                          )}
                        </Form.Item>
                      </Form>
                    ) : ''
                  }
                  {
                    stage == 'findUsernameByPhone' ? (
                      <Form
                        form={findUsernameByPhoneForm}
                        layout={'vertical'}
                        name="SignInForm"
                        initialValues={{ remember: true }}
                        autoComplete="off"
                      >
                        <Form.Item label="手机号：">
                          <Input
                            readOnly
                            value={emailOrPhone}
                          />
                        </Form.Item>
                        <Form.Item
                          label="短信验证码："
                          name="verCode"
                          rules={[{ required: true, message: '请输入您注册时预留的邮箱或手机号' }]}
                        >
                          <Input />
                        </Form.Item>
                        <Form.Item label="您的用户名：" name="username">
                          <Input readOnly disabled />
                        </Form.Item>
                        <Form.Item shouldUpdate>
                          {() => (
                            <Space>
                              <Button
                                type="primary"
                                shape="round"
                                htmlType="submit"
                                loading={loading}
                                onClick={findUserByPhone}
                              >
                                找回用户名
                              </Button>
                              <Button shape="round" href="/auth/signUp">
                                注册账号
                              </Button>
                            </Space>
                          )}
                        </Form.Item>
                      </Form>
                    ) : ''
                  }
                </Col>
              </Row>
            </div>
          </section>
        </main>
      </div>
    </>
  )
}

SignInPage.getLayout = (page: any) => {
  return (
    <Layout>
      {page}
    </Layout>
  )
}

export default SignInPage