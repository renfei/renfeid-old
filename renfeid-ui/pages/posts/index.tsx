import Head from 'next/head'
import Link from 'next/link'
import Image from 'next/image'
import nookies from 'nookies'
import React, { useState, useEffect } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Row, Col, List, Space } from 'antd'
import { LikeOutlined, MessageOutlined, EyeFilled } from '@ant-design/icons'
import Layout from "../../components/layout"
import PostSidebar from '../../components/PostSidebar'
import PostsList from '../../components/PostList'
import * as api from '../../services/api'
import { convertToHeaders } from '../../utils/request'
import PostVo = API.PostVo
import APIResult = API.APIResult
import ListData = API.ListData

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
    const result: APIResult<ListData<PostVo>> = await api.getPosts(convertToHeaders(context.req.headers), undefined, 1, 10, accessToken)
    return {
        props: {
            data: {
                listData: result.data
            }
        }
    }
}

const PostsPage = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
    let listData: ListData<PostVo> | undefined = data.listData
    return (
        <>
            <Head>
                <title>{`博客文章 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
                <meta name="keyword" content="任霏,renfei,博客,blog,任霏博客,renfei blog,内容" />
                <meta name="description" content="任霏的个人博客栏目，专注分享科技、软件、开发等技术内容，记录分享个人的实践与成长。" />
                <meta name="author" content="任霏,i@renfei.net" />
                <meta name="copyright" content="CopyRight RENFEI.NET, All Rights Reserved." />
            </Head>

            <main style={{ backgroundColor: '#ffffff' }}>
                <section className={"renfeid-content"}>
                    <Row style={{ padding: '20px 0' }}>
                        <Col xs={24} sm={24} md={16} lg={17}>
                            {
                                listData ? (
                                    <PostsList posts={listData} />
                                ) : ''
                            }
                        </Col>
                        <Col xs={24} sm={24} md={8} lg={7}>
                            <PostSidebar />
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
