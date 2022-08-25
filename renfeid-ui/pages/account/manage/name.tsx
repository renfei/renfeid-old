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
  const [loading, setLoading] = useState<boolean>(false)
  const [userInfo, setUserInfo] = useState<UserInfo>(data.userInfo)

  const save = async () => {
    setLoading(true)
    const res: APIResult<any> = await api.updateFirstName(form.getFieldValue('firstName'), form.getFieldValue('lastName'))
    if (res.code == 200) {
      message.success("修改成功！")
      userInfo.firstName = form.getFieldValue('firstName')
      userInfo.lastName = form.getFieldValue('lastName')
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
        <title>{`管理您的姓名称呼 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
      </Head>

      <main style={{ background: '#ffffff' }}>
        <section className={"renfeid-content"} style={{ paddingTop: '3rem!important', paddingBottom: '6rem' }}>
          <Row>
            <Col xs={24} md={6}>
              <Typography.Title level={2} style={{ color: '#666', fontWeight: '300' }}>姓名管理</Typography.Title>
            </Col>
            <Col xs={24} md={12}>
              <Form
                labelCol={{ span: 5, offset: 0 }}
                form={form}>
                <Form.Item label="当前姓氏" >
                  {
                    userInfo.lastName ? userInfo.lastName : '<-- 未填写 -->'
                  }
                </Form.Item>
                <Form.Item label="新的姓氏" name="lastName">
                  <Input
                    placeholder="新的姓氏(Last Name)"
                  />
                </Form.Item>
                <Form.Item label="当前名字" >
                  {
                    userInfo.firstName ? userInfo.firstName : '<-- 未填写 -->'
                  }
                </Form.Item>
                <Form.Item label="新的名字" name="firstName">
                  <Input
                    placeholder="新的名字(First Name)"
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