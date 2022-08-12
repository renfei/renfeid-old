import Head from 'next/head'
import Link from 'next/link'
import { Col, Row, Typography, Divider, Descriptions } from 'antd'
import Layout from "../../../components/layout"

const AccountManagePage = () => {
  return (
    <>
      <Head>
        <title>管理您的账户 - {process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}</title>
        <meta name="description" content={`关于${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`} />
      </Head>

      <main style={{ background: '#ffffff' }}>
        <div style={{ background: "url('https://cdn.renfei.net/thunder/img/account_bg_blur.jpg') no-repeat", backgroundSize: 'auto', backgroundPosition: 'bottom center' }}>
          <section className={"renfeid-content"} style={{ color: '#ffffff', height: '180px' }}>
            <Row>
              <Col span={24} style={{ paddingTop: '40px' }}>
                <Typography.Title level={1} style={{ color: '#ffffff' }}>username</Typography.Title>
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
                  7A159BF2BCB94B28BD185AC868169197
                </Descriptions.Item>
                <Descriptions.Item label="用户名">username</Descriptions.Item>
                <Descriptions.Item label="电子邮箱">
                  <div>
                    xxxx@renfei.net
                  </div>
                  <div>
                    <Link href="/account/manage/email">
                      <a style={{ color: '#1890ff', fontSize: '12px' }}>修改电子邮箱地址...</a>
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="注册时间">2022-08-12 17:05:42</Descriptions.Item>
                <Descriptions.Item label="手机号码">
                  <div>
                    13001000000
                  </div>
                  <div>
                    <Link href="/account/manage/phone">
                      <a style={{ color: '#1890ff', fontSize: '12px' }}>修改手机号码...</a>
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="姓氏">
                  <div>
                    xx
                  </div>
                  <div>
                    <Link href="/account/manage/name">
                      <a style={{ color: '#1890ff', fontSize: '12px' }}>修改我的姓氏...</a>
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="名字">
                  <div>
                    xxx
                  </div>
                  <div>
                    <Link href="/account/manage/name">
                      <a style={{ color: '#1890ff', fontSize: '12px' }}>修改我的名字...</a>
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
                    <Link href="/account/manage/password">
                      <a style={{ color: '#1890ff', fontSize: '12px' }}>更改密码...</a>
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="两步认证(U2F)" span={2}>
                  <div>
                    未开启
                  </div>
                  <div>
                    <Link href="/account/manage/u2f">
                      <a style={{ color: '#1890ff', fontSize: '12px' }}>更改两步认证...</a>
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
                    <Link href="https://bbs.renfei.net/home.php?mod=spacecp&ac=usergroup">
                      <a style={{ color: '#1890ff', fontSize: '12px' }} target={'_blank'}>前往查看...</a>
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="积分">
                  <div>
                    3521
                  </div>
                  <div>
                    <Link href="https://bbs.renfei.net/home.php?mod=spacecp&ac=credit">
                      <a style={{ color: '#1890ff', fontSize: '12px' }} target={'_blank'}>前往查看...</a>
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="帖子数">
                  <div>
                    3521
                  </div>
                  <div>
                    <Link href="https://bbs.renfei.net/forum.php?mod=guide&view=my">
                      <a style={{ color: '#1890ff', fontSize: '12px' }} target={'_blank'}>前往查看...</a>
                    </Link>
                  </div>
                </Descriptions.Item>
                <Descriptions.Item label="金钱">3521</Descriptions.Item>
                <Descriptions.Item label="在线时间">
                  <div>
                    3521
                  </div>
                  <div>
                    <Link href="https://bbs.renfei.net/home.php?mod=space&uid=1&do=profile">
                      <a style={{ color: '#1890ff', fontSize: '12px' }} target={'_blank'}>前往查看...</a>
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