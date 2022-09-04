import Head from 'next/head'
import nookies from 'nookies'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Col, Row, Typography, Form, Input, Button, message } from 'antd'
import Layout from "../../../components/layout"
import CheckSignInStatus from '../../../utils/CheckSignInStatus'
import * as api from "../../../services/api"
import APIResult = API.APIResult
import UserInfo = API.UserInfo
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
  return {
    props: {
      data: {
        userInfo: userInfo,
      }
    }
  }
}

const AccountManagePage = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  const [form] = Form.useForm()
  const [sendLoading, setSendLoading] = useState<boolean>(false)
  const [loading, setLoading] = useState<boolean>(false)
  const [userInfo, setUserInfo] = useState<UserInfo>(data.userInfo)

  const sendPhoneVerCode = async () => {
    setSendLoading(true)
    if (!form.getFieldValue('newPhone')) {
      message.error('请先填写新的手机号码')
      setSendLoading(false)
      return
    }
    const res: APIResult<any> = await api.sendPhoneVerCode(form.getFieldValue('newPhone'))
    if (res.code == 200) {
      message.success("短信发送成功！")
    } else {
      message.error(res.message)
    }
    setSendLoading(false)
  }

  const save = async () => {
    setLoading(true)
    if (!form.getFieldValue('newPhone')) {
      message.error('请先填写新的手机号码')
      setLoading(false)
      return
    }
    if (!form.getFieldValue('vcode')) {
      message.error('请填写验证码')
      setLoading(false)
      return
    }
    const res: APIResult<any> = await api.updatePhone(form.getFieldValue('newPhone'), form.getFieldValue('vcode'))
    if (res.code == 200) {
      message.success("修改成功！")
      userInfo.phone = form.getFieldValue('newPhone')
      setUserInfo(userInfo)
    } else {
      message.error(res.message)
    }
    setLoading(false)
  }

  return (
    <>
      <Head>
        <title>{`管理您的手机号码 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
      </Head>

      <main style={{ background: '#ffffff' }}>
        <section className={"renfeid-content"} style={{ paddingTop: '3rem!important', paddingBottom: '6rem' }}>
          <Row>
            <Col xs={24} md={6}>
              <Typography.Title level={2} style={{ color: '#666', fontWeight: '300' }}>手机号码</Typography.Title>
            </Col>
            <Col xs={24} md={12}>
              <Form
                labelCol={{ span: 5, offset: 0 }}
                form={form}>
                <Form.Item label="当前手机" >
                  {
                    userInfo.phone ? userInfo.phone : '暂无'
                  }
                </Form.Item>
                <Form.Item label="新的手机" name="newPhone" help="点击下方发送验证码，我们会给您的新手机号码发送一条短信。">
                  <Input
                    placeholder="新的手机号码"
                  />
                </Form.Item>
                <Form.Item label=" " colon={false}>
                  <Button type={'primary'} block loading={sendLoading} onClick={sendPhoneVerCode}>发送验证码</Button>
                </Form.Item>
                <Form.Item label="验证码" name="vcode" tooltip="我们会给您的新手机号码发送一条验证码短信，请到手机上查收验证码短信。">
                  <Input
                    placeholder="请到手机短信中查收验证码"
                  />
                </Form.Item>
                <Form.Item label=" " colon={false}>
                  <Button type={'primary'} block loading={loading} onClick={save}>保存</Button>
                </Form.Item>
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