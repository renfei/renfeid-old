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
  const categoryName = context.query.categoryName
  if (!categoryName) {
    return {
      notFound: true,
    }
  }
  const allPostCategory: APIResult<ListData<PostCategory>> = await api.queryPostCategoryList(convertToHeaders(context.req.headers), undefined, 1, 2147483647, accessToken)
  const allPostTag: APIResult<Tag[]> = await api.queryAllPostTagList(convertToHeaders(context.req.headers), accessToken)
  const result: APIResult<ListData<PostCategory>> = await api.queryPostCategoryList(convertToHeaders(context.req.headers), categoryName, 1, 1, accessToken)
  if (!result.data || !result.data.data || result.data.data.length == 0) {
    return {
      notFound: true,
    }
  }
  const postCategory: PostCategory = result.data.data[0]
  let page = 1
  if (context.query.page) {
    page = context.query.page
  }
  const postResult: APIResult<ListData<PostVo>> = await api.getPosts(convertToHeaders(context.req.headers), postCategory.id, page, 10, accessToken)
  const hotPosts: APIResult<ListData<PostVo>> = await api.getHotPosts(convertToHeaders(context.req.headers), 10, accessToken)
  const lastCommentResult: APIResult<Comment[]> = await api.queryLastComment(convertToHeaders(context.req.headers), 'POSTS', '10', accessToken)
  return {
    props: {
      data: {
        allPostCategory: allPostCategory.data?.data,
        allPostTag: allPostTag.data,
        category: postCategory,
        listData: postResult.data,
        hotPosts: hotPosts.data?.data,
        lastCommentResult: lastCommentResult.data,
      }
    }
  }
}

const PostCategoryPage = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
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
        title={`分类：${data.category.zhName} - 博客文章分类 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}
        description={`博客文章分类${data.category.zhName}。共同类型的文章在这里聚合等待您的查阅。`}
        canonical={`${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}/posts`}
        openGraph={{
          title: `分类：${data.category.zhName} - 博客文章分类 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`,
          description: `博客文章分类${data.category.zhName}。共同类型的文章在这里聚合等待您的查阅。`,
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
        <section className={"renfeid-content"} style={{ padding: '20px 0' }}>
          <div style={{ padding: '0 20px' }}>
            <Typography.Title level={3} style={{ marginBottom: '0' }} className={styles.title}>内容分类：{data.category.zhName}</Typography.Title>
          </div>
          <Divider style={{ margin: '10px 0' }} />
          <Row style={{ padding: '10px 0' }} gutter={20}>
            <Col xs={24} sm={24} md={16} lg={17}>
              {
                listData ? (
                  <PostsList posts={listData} path={`/posts/cat/${data.category.enName}`} />
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

PostCategoryPage.getLayout = (page: any) => {
  return (
    <Layout>
      {page}
    </Layout>
  )
}

export default PostCategoryPage