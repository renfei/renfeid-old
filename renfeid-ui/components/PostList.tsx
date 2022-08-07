import React from 'react'
import Image from 'next/image'
import { Row, Col, List, Space } from 'antd'
import { LikeOutlined, MessageOutlined, EyeFilled } from '@ant-design/icons'
import PostVo = API.PostVo
import ListData = API.ListData

const IconText = ({ icon, text }: { icon: React.FC; text: string }) => (
  <Space>
    {React.createElement(icon)}
    {text}
  </Space>
)

const PostList = (props: { posts: ListData<PostVo> }) => {
  if (props.posts) {
    return (
      <List
        itemLayout="vertical"
        size="large"
        pagination={{
          onChange: page => {
            console.log(page);
          },
          pageSize: 10,
          total: props.posts.total
        }}
        dataSource={props.posts.data}
        renderItem={item => (
          <List.Item
            key={item.id}
            actions={[
              <IconText icon={EyeFilled} text={item.postViews.toString()} key="list-vertical-star-o" />,
              <IconText icon={LikeOutlined} text="0" key="list-vertical-like-o" />,
              <IconText icon={MessageOutlined} text="0" key="list-vertical-message" />,
            ]}
            extra={
              <Image
                width={272}
                height={136}
                style={{ borderRadius: '12px' }}
                src={item.featuredImage}
                alt={item.postTitle}
              />
            }
          >
            <List.Item.Meta
              title={<a href={`/posts/${item.id}`}>{item.postTitle}</a>}
              description={item.postDate}
            />
            {item.postExcerpt}
          </List.Item>
        )}
      />
    )
  } else {
    return (<></>)
  }
}

export default PostList