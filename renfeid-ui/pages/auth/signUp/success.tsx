import Head from 'next/head'
import Link from 'next/link'
import { Button, Result, Typography } from 'antd'
import Layout from '../../../components/layout'

const { Paragraph, Text } = Typography

const SignUpSuccessPage = () => {
  return (
    <>
      <Head>
        <title>{`您已成功创建了账户 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
        <meta name="description" content={`您已成功创建了账户 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`} />
      </Head>

      <main style={{ backgroundColor: '#ffffff', padding: '60px 0' }}>
        <section className={"renfeid-content"}>
          <Result
            status="success"
            title="您已成功创建了账户。"
            subTitle="接下来您还需要一些验证工作。"
            extra={[
              <Button type="primary" key="console" href='/auth/signUp/activation'>
                去激活账户
              </Button>
            ]}
          >
            <div className="desc">
              <Paragraph>
                <Text
                  strong
                  style={{
                    fontSize: 16,
                  }}
                >
                  激活您的账户：
                </Text>
              </Paragraph>
              <Paragraph>
                为了确保账户的真实有效，我们向您注册时所填写的邮箱发送了一封激活确认邮件或给您的手机发送了一条短信， 请您到您的邮箱中点击激活链接来启用您的账户。
              </Paragraph>
              <Paragraph>
                如果是手机短信注册，可访问 <Link href="/auth/signUp/activation">激活页面</Link> 输入验证码激活您的账户。
              </Paragraph>
            </div>
          </Result>
        </section>
      </main>
    </>
  )
}

SignUpSuccessPage.getLayout = (page: any) => {
  return (
    <Layout>
      {page}
    </Layout>
  )
}

export default SignUpSuccessPage