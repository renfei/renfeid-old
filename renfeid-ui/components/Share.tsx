import { Button, Space, message } from 'antd'
import { QRCodeSVG } from 'qrcode.react'
import { CopyToClipboard } from 'react-copy-to-clipboard'
import styles from "../styles/CMS.module.css"
import {
  WechatFilled,
  QqOutlined,
  FacebookFilled,
  TwitterCircleFilled,
  WeiboCircleFilled,
  LinkedinFilled,
  LinkOutlined,
  EyeFilled,
} from '@ant-design/icons'

const share = (props: {
  url: string,
  title: string,
  desc: string,
  pics: string,
  views: number
}) => {
  return (
    <Space size={4}>
      <Button type="text" shape="circle" className={styles.wechatShareBtn} icon={<WechatFilled />}>
        <div className={styles.wechatShare}>
          <QRCodeSVG
            width={150}
            height={150}
            value={props.url}
            level={"M"}
            imageSettings={{
              src: "https://cdn.renfei.net/Logo/renfei_64.png",
              x: undefined,
              y: undefined,
              height: 34,
              width: 34,
              excavate: true,
            }}
          />
        </div>
      </Button>
      <Button type="text" shape="circle" icon={<QqOutlined />}
        onClick={() => {
          window.open(`http://connect.qq.com/widget/shareqq/index.html?url=${props.url}&title=${props.title}&source=RENFEI.NET&desc=${props.desc}&pics=${props.pics}&summary=${props.title}`, '_blank')
        }}
      />
      <Button type="text" shape="circle" icon={<FacebookFilled />}
        onClick={() => {
          window.open(`http://www.facebook.com/dialog/feed?app_id=${process.env.NEXT_PUBLIC_FACEBOOK_APP_ID}&redirect_uri=${props.url}&link=${props.url}&display=popup`, '_blank')
        }}
      />
      <Button type="text" shape="circle" icon={<TwitterCircleFilled />}
        onClick={() => {
          window.open(`https://twitter.com/intent/tweet?text=${props.title}&url=${props.url}`, '_blank')
        }}
      />
      <Button type="text" shape="circle" icon={<WeiboCircleFilled />}
        onClick={() => {
          window.open(`http://service.weibo.com/share/share.php?appkey=4264535112&url=${props.url}&title=${props.title}&pic=${props.pics}`, '_blank')
        }}
      />
      <Button type="text" shape="circle" icon={<LinkedinFilled />}
        onClick={() => {
          window.open(`https://www.linkedin.com/shareArticle?mini=true&url=${props.url}&title=${props.title}&source=RENFEI.NET`, '_blank')
        }}
      />
      <CopyToClipboard text={`${props.url}`}
        onCopy={() => { message.success('复制链接成功！') }}
      >
        <Button type="text" shape="circle" icon={<LinkOutlined />} />
      </CopyToClipboard>
      <Button type="text" shape="circle" icon={<EyeFilled />}>
        {props.views}
      </Button>
    </Space>
  )
}

export default share