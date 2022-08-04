import Link from 'next/link'
import { QRCodeCanvas } from 'qrcode.react'
import { Row, Col, Typography, Card, List, Tag } from 'antd'
import { WechatOutlined, BugOutlined } from '@ant-design/icons'
import GoogleAdsense from './GoogleAdsense'

const data = [
  'Racing car sprays burn',
  'Japanese princess t',
  'Australian walks ',
  'Man charged over',
  'Los Angel',
]

const PostSidebar = ({ props }: any) => {
  return (
    <Row style={{ padding: '10px 0 20px 10px' }}>
      <Col span={24}>
        <div className={'renfeid-card'}>
          <Typography.Title level={5}><WechatOutlined style={{ paddingRight: '10px' }} />微信订阅号</Typography.Title>
          <Typography.Text type="secondary" style={{ fontSize: '12px' }}>扫码关注「任霏博客」微信订阅号</Typography.Text>
          <div style={{ textAlign: 'center', marginTop: '0.5em' }}>
            <QRCodeCanvas
              value="http://weixin.qq.com/r/OkSQiJLEx8_4rdbr9xEo"
            />
          </div>
        </div>
        <div className={'renfeid-card'}>
          <Typography.Title level={5}><BugOutlined style={{ paddingRight: '10px' }} />反馈与讨论</Typography.Title>
          <Typography.Text type="secondary" style={{ fontSize: '12px' }}>感谢您的关注与反馈</Typography.Text>
          <div style={{ textAlign: 'center', marginTop: '0.5em' }}>
            如果您发现了BUG、安全漏洞、或者希望讨论技术内容，请点击下方链接对我进行反馈。<br />
            <Link href="https://github.com/renfei/feedback/discussions">
              <a target="_blank" rel="nofollow noopener">
                <Tag color="processing">feedback</Tag>
              </a>
            </Link>
          </div>
        </div>
        <Card type="inner" title="内容分类" extra={<a href="/posts">全部内容</a>} className={'ant-card-renfeid'}>
          <List
            dataSource={data}
            renderItem={item => (
              <List.Item>
                <Link href={`/posts/${item}`}>
                  <a>{item}</a>
                </Link>
              </List.Item>
            )}
          />
        </Card>
        <GoogleAdsense
          client={process.env.NEXT_PUBLIC_GOOGLE_ADSENSE}
          slot={"641095"}
        />
        <Card type="inner" title="内容标签" extra={<a href="/posts">全部内容</a>} className={'ant-card-renfeid'}>
          <Tag>
            <a href="https://github.com/ant-design/ant-design/issues/1862">Link</a>
          </Tag>
          <Tag>
            <a href="https://github.com/ant-design/ant-design/issues/1862">Link</a>
          </Tag>
          <Tag>
            <a href="https://github.com/ant-design/ant-design/issues/1862">Link</a>
          </Tag>
          <Tag>
            <a href="https://github.com/ant-design/ant-design/issues/1862">Link</a>
          </Tag>
          <Tag>
            <a href="https://github.com/ant-design/ant-design/issues/1862">Link</a>
          </Tag>
        </Card>
        <GoogleAdsense
          client={process.env.NEXT_PUBLIC_GOOGLE_ADSENSE}
          slot={"641096"}
        />
        <Card type="inner" title="最新留言" className={'ant-card-renfeid'}>
          <List
            dataSource={data}
            renderItem={item => (
              <List.Item>
                <Link href={`/posts/${item}`}>
                  <a>{item}</a>
                </Link>
              </List.Item>
            )}
          />
        </Card>
        <Card type="inner" title="热文排行" extra={<a href="/posts">全部内容</a>} className={'ant-card-renfeid'}>
          <List
            dataSource={data}
            renderItem={item => (
              <List.Item>
                <Link href={`/posts/${item}`}>
                  <a>{item}</a>
                </Link>
              </List.Item>
            )}
          />
        </Card>
      </Col>
    </Row>
  )
}

export default PostSidebar