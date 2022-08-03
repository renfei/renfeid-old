import Head from 'next/head'
import nookies from 'nookies'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Typography, Divider, Row, Col, Input, Select, Button, List } from 'antd'
import Layout from "../components/layout"

export const getServerSideProps: GetServerSideProps = async (context: any) => {
  const accessToken = nookies.get(context)['accessToken']
  const w: string = context.query.w || ""
  const type: string = context.type || ""
  return {
    props: {
      data: {
        w: w,
        type: type
      }
    }
  }
}

const SearchPage = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  return (
    <>
      <Head>
        <title>{`${data.w ? '搜索：' + data.w : '站内搜索'} - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
        <meta name="description" content={`搜索${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`} />
        <meta name="keywords" content="任霏,博客,任霏博客,RenFei,NeilRen,技术,blog" />
        <meta name="author" content="任霏,i@renfei.net" />
        <meta name="copyright" content="CopyRight RENFEI.NET, All Rights Reserved." />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main style={{ backgroundColor: '#ffffff' }}>
        <section className={"renfeid-content"} style={{ paddingTop: '20px', paddingBottom: '20px' }}>
          {
            data.w ? (<>
              <Typography.Title level={2}>搜索：{data.w}</Typography.Title>
              <Divider />
              <Row>
                <Col xs={24} sm={16} md={18} style={{ paddingRight: '20px' }}>
                  <Input.Group compact>
                    <Select
                      style={{ width: '100px' }}
                      defaultValue="ALL"
                      size="large">
                      <Select.Option value="ALL">全部</Select.Option>
                      <Select.Option value="POSTS">文章</Select.Option>
                      <Select.Option value="DISCUZ">论坛</Select.Option>
                      <Select.Option value="WEIBO">微博</Select.Option>
                      <Select.Option value="PAGES">页面</Select.Option>
                      <Select.Option value="PHOTO">相册</Select.Option>
                      <Select.Option value="VIDEO">视频</Select.Option>
                      <Select.Option value="KITBOX">工具箱</Select.Option>
                    </Select>
                    <Input.Search
                      style={{ width: 'calc(100% - 100px)' }}
                      placeholder="搜索 renfei.net"
                      defaultValue={data.w}
                      allowClear
                      enterButton={<Button type="primary" ghost>搜索</Button>}
                      size="large"
                    />
                  </Input.Group>
                  <Typography.Text type="secondary" style={{ fontSize: '12px' }}>找到 777 个结果(0.004727 seconds)</Typography.Text>
                  <List>
                    结果列表待完善，参考文章列表页。
                  </List>
                </Col>
                <Col xs={24} sm={8} md={6}>
                  占位
                </Col>
              </Row>
            </>) : (<>
              <Typography.Title level={2}>站内搜索</Typography.Title>
              <Divider />
              <Row>
                <Col xs={24} md={5}></Col>
                <Col xs={24} md={14}>
                  <Input.Group compact>
                    <Select
                      style={{ width: '100px' }}
                      defaultValue="ALL"
                      size="large">
                      <Select.Option value="ALL">全部</Select.Option>
                      <Select.Option value="POSTS">文章</Select.Option>
                      <Select.Option value="DISCUZ">论坛</Select.Option>
                      <Select.Option value="WEIBO">微博</Select.Option>
                      <Select.Option value="PAGES">页面</Select.Option>
                      <Select.Option value="PHOTO">相册</Select.Option>
                      <Select.Option value="VIDEO">视频</Select.Option>
                      <Select.Option value="KITBOX">工具箱</Select.Option>
                    </Select>
                    <Input.Search
                      style={{ width: 'calc(100% - 100px)' }}
                      placeholder="搜索 renfei.net"
                      allowClear
                      enterButton={<Button type="primary" ghost>搜索</Button>}
                      size="large"
                    />
                  </Input.Group>
                  <div style={{ border: '1px solid rgba(0,0,0,0.06)', borderRadius: '5px', padding: '20px', marginTop: '10px' }}>
                    <Typography.Title level={5} style={{ marginBottom: '0' }}>搜索热榜</Typography.Title>
                    <Typography.Text type="secondary" style={{ fontSize: '12px' }}>搜索词来自网友历史搜索自动产生，不代表本站立场。</Typography.Text>
                  </div>
                </Col>
                <Col xs={24} md={5}></Col>
              </Row>
            </>)
          }
        </section>
      </main>
    </>
  )
}

SearchPage.getLayout = (page: any) => {
  return (
    <Layout>
      {page}
    </Layout>
  )
}

export default SearchPage