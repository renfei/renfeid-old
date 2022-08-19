import nookies from 'nookies'
import moment from 'moment'
import { NextSeo, ArticleJsonLd } from 'next-seo'
import React, { useState, useEffect } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Row, Col, Space, Typography, Divider } from 'antd'
import Layout from "../../components/layout"
import PostSidebar from '../../components/PostSidebar'
import PostsList from '../../components/PostList'
import * as api from '../../services/api'
import { convertToHeaders } from '../../utils/request'
import PostVo = API.PostVo
import APIResult = API.APIResult
import ListData = API.ListData
import PostCategory = API.PostCategory
import Tag = API.Tag

const datas = Array.from({ length: 23 }).map((_, i) => ({
    href: '/',
    title: `ant design part ${i}`,
    description:
        '2022-08-03 22:27:30',
    content:
        'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
}))

const IconText = ({ icon, text }: { icon: React.FC; text: string }) => (
    <Space>
        {React.createElement(icon)}
        {text}
    </Space>
)

export const getServerSideProps: GetServerSideProps = async (context: any) => {
    const accessToken = nookies.get(context)['accessToken']
    let page = 1
    if (context.query.page) {
        page = context.query.page
    }
    const allPostCategory: APIResult<ListData<PostCategory>> = await api.queryPostCategoryList(convertToHeaders(context.req.headers), undefined, 1, 2147483647, accessToken)
    const allPostTag: APIResult<Tag[]> = await api.queryAllPostTagList(convertToHeaders(context.req.headers), accessToken)
    const hotPosts: APIResult<ListData<PostVo>> = await api.getHotPosts(convertToHeaders(context.req.headers), 10, accessToken)
    const result: APIResult<ListData<PostVo>> = await api.getPosts(convertToHeaders(context.req.headers), undefined, page, 10, accessToken)
    const lastCommentResult: APIResult<Comment[]> = await api.queryLastComment(convertToHeaders(context.req.headers), 'POSTS', '10', accessToken)
    return {
        props: {
            data: {
                allPostCategory: allPostCategory.data?.data,
                allPostTag: allPostTag.data,
                listData: result.data,
                hotPosts: hotPosts.data?.data,
                lastCommentResult: lastCommentResult.data,
            }
        }
    }
}

const PostsPage = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
    let listData: ListData<PostVo> | undefined = data.listData
    let openGraphImages: any[] = []
    let jsonLdImages: string[] = []
    if (listData) {
        for (let i = 0; i < listData.data.length; i++) {
            openGraphImages.push({
                url: `${listData.data[i].featuredImage}`,
                width: 1280,
                height: 640,
                alt: `${listData.data[i].postTitle}`,
                type: 'image/jpeg',
            })
            jsonLdImages.push(listData.data[i].featuredImage)
        }
    }
    return (
        <>
            <NextSeo
                title={`博客文章 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}
                description="任霏的个人博客栏目，专注分享科技、软件、开发等技术内容，记录分享个人的实践与成长。"
                canonical={`${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}/posts`}
                openGraph={{
                    title: `博客文章 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`,
                    description: "任霏的个人博客栏目，专注分享科技、软件、开发等技术内容，记录分享个人的实践与成长。",
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
            <ArticleJsonLd
                type="Blog"
                url="https://example.com/blog"
                title={`${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}
                images={jsonLdImages}
                datePublished={moment(listData?.data[0].postDate).format()}
                authorName="任霏"
                description="任霏的个人博客栏目，专注分享科技、软件、开发等技术内容，记录分享个人的实践与成长。"
            />

            <main style={{ backgroundColor: '#ffffff' }}>
                <section className={"renfeid-content"} style={{ padding: '20px 0' }}>
                    <div style={{ padding: '0 20px' }}>
                        <Typography.Title level={3} style={{ marginBottom: '0' }}>任霏的博客</Typography.Title>
                        <Typography.Text type={'secondary'}>任霏的个人博客栏目，专注分享科技、软件、开发等技术内容，记录分享个人的实践与成长。</Typography.Text>
                    </div>
                    <Divider style={{ margin: '10px 0' }} />
                    <Row style={{ padding: '10px 0' }}>
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

PostsPage.getLayout = (page: any) => {
    return (
        <Layout>
            {page}
        </Layout>
    )
}

export default PostsPage
