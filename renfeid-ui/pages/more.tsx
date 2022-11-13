import Head from 'next/head'
import Image from 'next/image'
import Layout from '../components/layout'
import { Row, Col, Avatar, Card } from 'antd'

const { Meta } = Card

const MetaPageColStyle = {
  padding: '10px'
}

const MorePage = () => {
  return (
    <>
      <Head>
        <title>{`更多信息 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
        <meta name="description" content={`关于${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`} />
      </Head>

      <main style={{ backgroundColor: '#ffffff' }}>
        <section className={"renfeid-content"} style={{ paddingTop: '20px', paddingBottom: '20px' }}>
          <Row style={{ padding: '20px 0' }}>
            <Col xs={24} sm={12} md={8} lg={6} style={MetaPageColStyle}>
              <a href={'/page/about'}>
                <Card
                  style={{ width: '100%' }}
                  cover={
                    <Image
                      width={658}
                      height={400}
                      alt={`关于${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}
                      src="https://cdn.renfei.net/renfeid/img/aboutimg.webp"
                    />
                  }
                >
                  <Meta
                    title={`关于${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}
                    description={`关于${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}更多信息，联系方式与我的历史等信息。`}
                  />
                </Card>
              </a>
            </Col>
            <Col xs={24} sm={12} md={8} lg={6} style={MetaPageColStyle}>
              <Card
                style={{ width: '100%' }}
                cover={
                  <Image
                    width={658}
                    height={400}
                    alt="example"
                    src="https://cdn.renfei.net/renfeid/img/aboutimg.webp"
                  />
                }
              >
                <Meta
                  title="占位"
                  description="你可以替换成你的卡片。"
                />
              </Card>
            </Col>
            <Col xs={24} sm={12} md={8} lg={6} style={MetaPageColStyle}>
              <Card
                style={{ width: '100%' }}
                cover={
                  <Image
                    width={658}
                    height={400}
                    alt="example"
                    src="https://cdn.renfei.net/renfeid/img/aboutimg.webp"
                  />
                }
              >
                <Meta
                  title="占位"
                  description="你可以替换成你的卡片。"
                />
              </Card>
            </Col>
            <Col xs={24} sm={12} md={8} lg={6} style={MetaPageColStyle}>
              <Card
                style={{ width: '100%' }}
                cover={
                  <Image
                    width={658}
                    height={400}
                    alt="example"
                    src="https://cdn.renfei.net/renfeid/img/aboutimg.webp"
                  />
                }
              >
                <Meta
                  title="占位"
                  description="你可以替换成你的卡片。"
                />
              </Card>
            </Col>
            <Col xs={24} sm={12} md={8} lg={6} style={MetaPageColStyle}>
              <Card
                style={{ width: '100%' }}
                cover={
                  <Image
                    width={658}
                    height={400}
                    alt="example"
                    src="https://cdn.renfei.net/renfeid/img/aboutimg.webp"
                  />
                }
              >
                <Meta
                  title="占位"
                  description="你可以替换成你的卡片。"
                />
              </Card>
            </Col>
            <Col xs={24} sm={12} md={8} lg={6} style={MetaPageColStyle}>
              <Card
                style={{ width: '100%' }}
                cover={
                  <Image
                    width={658}
                    height={400}
                    alt="example"
                    src="https://cdn.renfei.net/renfeid/img/aboutimg.webp"
                  />
                }
              >
                <Meta
                  title="占位"
                  description="你可以替换成你的卡片。"
                />
              </Card>
            </Col>
          </Row>
        </section>
      </main>
    </>
  )
}

MorePage.getLayout = (page: any) => {
  return (
    <Layout>
      {page}
    </Layout>
  )
}

export default MorePage