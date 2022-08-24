import Head from 'next/head'
import nookies from 'nookies'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Col, Row, Typography, Form, Input, Button, message } from 'antd'
import Layout from "../../../components/layout"
import CheckSignInStatus from '../../../utils/CheckSignInStatus'
import * as api from "../../../services/api"
import { encrypt } from "../../../utils/encryption"
import APIResult = API.APIResult
import UserInfo = API.UserInfo
import UpdatePasswordAo = API.UpdatePasswordAo
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
  const [loading, setLoading] = useState<boolean>(false)
  const [userInfo, setUserInfo] = useState<UserInfo>(data.userInfo)

  const save = async () => {
    setLoading(true)
    if (!form.getFieldValue('oldPwd')) {
      message.error('请输入当前密码')
      setLoading(false)
      return
    }
    if (!form.getFieldValue('newPwd')) {
      message.error('请输入新密码')
      setLoading(false)
      return
    }
    if (form.getFieldValue('newPwd') != form.getFieldValue('newPwds')) {
      message.error('新密码两次密码输入不一致')
      setLoading(false)
      return
    }
    let updatePassword: UpdatePasswordAo = {
      oldPwd: '',
      newPwd: '',
      keyId: ''
    }
    await encrypt(form.getFieldValue('oldPwd')).then(res => {
      updatePassword.keyId = res?.secretKey.uuid || ''
      updatePassword.oldPwd = res?.value || ''
    })
    await encrypt(form.getFieldValue('newPwd')).then(res => {
      updatePassword.keyId = res?.secretKey.uuid || ''
      updatePassword.newPwd = res?.value || ''
    })
    let res: APIResult<any> = await api.updatePassword(updatePassword)
    if (res.code == 200) {
      form.resetFields()
      message.success('修改成功！')
    } else {
      message.error(res.message)
    }
    setLoading(false)
  }

  return (
    <>
      <Head>
        <title>{`管理您的密码 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
      </Head>

      <main style={{ background: '#ffffff' }}>
        <section className={"renfeid-content"} style={{ paddingTop: '3rem!important', paddingBottom: '6rem' }}>
          <Row>
            <Col xs={24} md={6}>
              <Typography.Title level={2} style={{ color: '#666', fontWeight: '300' }}>密码管理</Typography.Title>
            </Col>
            <Col xs={24} md={12}>
              <Form
                labelCol={{ span: 5, offset: 0 }}
                form={form}>
                <Form.Item label="当前密码" name="oldPwd">
                  <Input.Password
                    placeholder="当前密码"
                    autoComplete='current-password'
                  />
                </Form.Item>
                <Form.Item label="新的密码" name="newPwd">
                  <Input.Password
                    placeholder="新的密码"
                    autoComplete='new-password'
                  />
                </Form.Item>
                <Form.Item label="重复密码" name="newPwds">
                  <Input.Password
                    placeholder="重复新密码"
                    autoComplete='new-password'
                  />
                </Form.Item>
                <Form.Item label=" " colon={false}>
                  <Button type={'primary'} block loading={loading} onClick={save}>保存新密码</Button>
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