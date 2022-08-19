import nookies from 'nookies'
import { NextSeo } from 'next-seo'
import { Row, Col, Typography, Divider } from 'antd'
import React, { useState, useEffect } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import Layout from "../../../components/layout"
import * as api from '../../../services/api'
import { convertToHeaders } from '../../../utils/request'
import PostVo = API.PostVo
import APIResult = API.APIResult
import ListData = API.ListData
import PostCategory = API.PostCategory
import Tag = API.Tag
import PostsList from '../../../components/PostList'
import PostSidebar from '../../../components/PostSidebar'
import styles from "../../../styles/CMS.module.css"

export const getServerSideProps: GetServerSideProps = async (context: any) => {
  const accessToken = nookies.get(context)['accessToken']
  const tagName = context.query.tagName
  if (!tagName) {
    return {
      notFound: true,
    }
  }
  const allPostCategory: APIResult<ListData<PostCategory>> = await api.queryPostCategoryList(convertToHeaders(context.req.headers), undefined, 1, 2147483647, accessToken)
  const allPostTag: APIResult<Tag[]> = await api.queryAllPostTagList(convertToHeaders(context.req.headers), accessToken)
  let tag: Tag = {
    id: '',
    enName: '',
    zhName: ''
  }
  if (allPostTag.data && allPostTag.data.length > 0) {
    allPostTag.data.forEach(element => {
      if (element.enName == tagName) {
        tag = element
      }
    })
  } else {
    return {
      notFound: true,
    }
  }
  if (tag.id == '') {
    return {
      notFound: true,
    }
  }
  let page = 1
  if (context.query.page) {
    page = context.query.page
  }
  const postResult: APIResult<ListData<PostVo>> = await api.getPostsByTag(convertToHeaders(context.req.headers), tagName, page, 10, accessToken)
  const hotPosts: APIResult<ListData<PostVo>> = await api.getHotPosts(convertToHeaders(context.req.headers), 10, accessToken)
  const lastCommentResult: APIResult<Comment[]> = await api.queryLastComment(convertToHeaders(context.req.headers), 'POSTS', '10', accessToken)
  return {
    props: {
      data: {
        allPostCategory: allPostCategory.data?.data,
        allPostTag: allPostTag.data,
        tag: tag,
        listData: postResult.data,
        hotPosts: hotPosts.data?.data,
        lastCommentResult: lastCommentResult.data,
      }
    }
  }
}

const PostTagPage = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  let listData: ListData<PostVo> | undefined = data.listData
  let openGraphImages: any[] = []
  if (listData) {
    for (let i = 0; i < listData.data.length; i++) {
      openGraphImages.push({
        url: `${listData.data[i].featuredImage}`,
        width: 1280,
        height: 640,
        alt: `${listData.data[i].postTitle}`,
        type: 'image/jpeg',
      })
    }
  }
  return (
    <>
      <NextSeo
        title={`标签：${data.tag.zhName} - 博客文章标签分类 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}
        description={`博客文章标签分类：${data.tag.zhName}。共同类型的文章在这里聚合等待您的查阅。`}
        canonical={`${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}/posts`}
        openGraph={{
          title: `标签：${data.tag.zhName} - 博客文章标签分类 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`,
          description: `博客文章标签分类：${data.tag.zhName}。共同类型的文章在这里聚合等待您的查阅。`,
          url: `${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}/posts`,
          type: 'website',
          images: openGraphImages,
          site_name: `${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`,
        }}
        twitter={{
          handle: '@renfeii',
          site: '@renfeii',
          cardType: 'summary_large_image',
        }}
        facebook={{
          appId: `${process.env.NEXT_PUBLIC_FACEBOOK_APP_ID}`
        }}
      />

      <main style={{ backgroundColor: '#ffffff' }}>
        <section className={"renfeid-content"}>
          <Row style={{ padding: '20px 0' }}>
            <Typography.Title level={1} className={styles.title}>标签分类：{data.tag.zhName}</Typography.Title>
            <Divider />
            <Col xs={24} sm={24} md={16} lg={17}>
              {
                listData ? (
                  <PostsList posts={listData} path={`/posts/tag/${data.tag.enName}`} />
                ) : ''
              }
            </Col>
            <Col xs={24} sm={24} md={8} lg={7}>
              <PostSidebar
                category={data.allPostCategory}
                tags={data.allPostTag}
                hotPost={data.hotPosts}
                lastComment={data.lastCommentResult}
                adsense={[]}
              />
            </Col>
          </Row>
        </section>
      </main>
    </>
  )
}

PostTagPage.getLayout = (page: any) => {
  return (
    <Layout>
      {page}
    </Layout>
  )
}

export default PostTagPage