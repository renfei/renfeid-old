import Head from 'next/head'
import nookies from 'nookies'
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
  const allPostCategory: APIResult<ListData<PostCategory>> = await api.queryPostCategoryList(convertToHeaders(context.req.headers), undefined, 1, 9007199254740991, accessToken)
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
  return (
    <>
      <Head>
        <title>{`分类：${data.category.zhName} - 博客文章分类 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
        <meta name="keyword" content={`${data.category.zhName},博客,blog,开发,技术,posts`} />
        <meta name="description" content={`博客文章分类：${data.category.zhName}。共同类型的文章在这里聚合等待您的查阅。`} />
        <meta name="author" content="任霏,i@renfei.net" />
        <meta name="copyright" content="CopyRight RENFEI.NET, All Rights Reserved." />
      </Head>

      <main style={{ backgroundColor: '#ffffff' }}>
        <section className={"renfeid-content"}>
          <Row style={{ padding: '20px 0' }}>
            <Typography.Title level={1} className={styles.title}>内容分类：{data.category.zhName}</Typography.Title>
            <Divider />
            <Col xs={24} sm={24} md={16} lg={17}>
              {
                listData ? (
                  <PostsList posts={listData} />
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