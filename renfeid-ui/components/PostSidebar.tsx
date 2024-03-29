import Link from 'next/link'
import { QRCodeSVG } from 'qrcode.react'
import { Row, Col, Typography, Card, List, Tag } from 'antd'
import { WechatOutlined, BugOutlined } from '@ant-design/icons'
import GoogleAdsense from './GoogleAdsense'
import PostCategory = API.PostCategory
import PostTag = API.Tag
import PostVo = API.PostVo
import Comment = API.Comment
import { getSysTypeUrl } from '../utils/SystemType'

const data = [
  'Racing car sprays burn',
  'Japanese princess t',
  'Australian walks ',
  'Man charged over',
  'Los Angel',
]

const PostSidebar = (props: {
  category?: PostCategory[], tags?: PostTag[], hotPost?: PostVo[], lastComment?: Comment[], adsense?: string[]
}) => {
  return (
    <Row>
      <Col span={24}>
        <div className={'renfeid_card'}>
          <Typography.Title level={5}><WechatOutlined style={{ paddingRight: '10px' }} />微信订阅号</Typography.Title>
          <Typography.Text type="secondary" style={{ fontSize: '12px' }}>扫码关注「任霏博客」微信订阅号</Typography.Text>
          <div style={{ textAlign: 'center', marginTop: '0.5em' }}>
            <QRCodeSVG
              value="http://weixin.qq.com/r/OkSQiJLEx8_4rdbr9xEo"
              level={"M"}
              imageSettings={{
                src: "https://cdn.renfei.net/Logo/wechat_64.png",
                x: undefined,
                y: undefined,
                height: 28,
                width: 28,
                excavate: true,
              }}
            />
          </div>
        </div>
        <div className={'renfeid_card'}>
          <Typography.Title level={5}><BugOutlined style={{ paddingRight: '10px' }} />反馈与讨论</Typography.Title>
          <Typography.Text type="secondary" style={{ fontSize: '12px' }}>感谢您的关注与反馈</Typography.Text>
          <div style={{ textAlign: 'center', marginTop: '0.5em' }}>
            如果您发现了BUG、安全漏洞、或者希望讨论技术内容，请点击下方链接对我进行反馈。<br />
            <a href="https://github.com/renfei/feedback/discussions" target="_blank" rel="nofollow noopener noreferrer">
              <Tag color="processing">feedback</Tag>
            </a>
          </div>
        </div>
        <Card type="inner" title="内容分类" extra={<Link href="/posts">全部内容</Link>} className={'ant-card-renfeid'}>
          <List
            dataSource={props.category}
            renderItem={item => (
              <List.Item>
                <a href={`/posts/cat/${item.enName}`} style={{ maxWidth: '100%' }}>
                  <Typography.Text ellipsis={true}>{item.zhName}</Typography.Text>
                </a>
              </List.Item>
            )}
          />
        </Card>
        {
          props.adsense && props.adsense.length > 0 ? (
            <GoogleAdsense
              client={process.env.NEXT_PUBLIC_GOOGLE_ADSENSE}
              slot={props.adsense[0]}
            />
          ) : ''
        }
        <Card type="inner" title="内容标签" extra={<Link href="/posts">全部内容</Link>} className={'ant-card-renfeid'}>
          {
            props.tags ? (
              props.tags.map(tag => (
                <Tag key={tag.id} style={{ marginBottom: '4px' }}>
                  <a href={`/posts/tag/${tag.enName}`}>
                    {tag.zhName}
                  </a>
                </Tag>
              ))
            ) : ''
          }
        </Card>
        {
          props.adsense && props.adsense.length > 1 ? (
            <GoogleAdsense
              client={process.env.NEXT_PUBLIC_GOOGLE_ADSENSE}
              slot={props.adsense[1]}
            />
          ) : ''
        }
        <Card type="inner" title="最新留言" className={'ant-card-renfeid'}>
          <List
            dataSource={props.lastComment}
            renderItem={item => (
              <List.Item>
                <a href={`${getSysTypeUrl(item.sysType)}${item.objectId}#cmt-${item.id}`} style={{ maxWidth: '100%' }}>
                  <Typography.Text ellipsis={true}>{item.content}</Typography.Text>
                </a>
              </List.Item>
            )}
          />
        </Card>
        <Card type="inner" title="热文排行" extra={<Link href="/posts">全部内容</Link>} className={'ant-card-renfeid'}>
          <List
            dataSource={props.hotPost}
            renderItem={item => (
              <List.Item>
                <a href={`/posts/${item.id}`} style={{ maxWidth: '100%' }}>
                  <Typography.Text ellipsis={true}>{item.postTitle}</Typography.Text>
                </a>
              </List.Item>
            )}
          />
        </Card>
      </Col>
    </Row>
  )
}

export default PostSidebar