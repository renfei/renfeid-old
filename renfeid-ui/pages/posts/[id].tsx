import Head from 'next/head'
import Link from 'next/link'
import nookies from 'nookies'
import React, { useState, useEffect } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import Layout from "../../components/layout"
import { Col, Row, Button, Typography, Divider, Space } from 'antd'
import {
    WechatFilled,
    QqOutlined,
    FacebookFilled,
    TwitterCircleFilled,
    WeiboCircleFilled,
    LinkedinFilled,
    LinkOutlined,
    EyeFilled
} from '@ant-design/icons'
import * as api from '../../services/api'
import styles from "../../styles/CMS.module.css"
import GoogleAdsense from "../../components/GoogleAdsense"
import Comments from '../../components/Comments'
import hljs from 'highlight.js'
import PostVo = API.PostVo
import APIResult = API.APIResult
import CommentTree = API.CommentTree
import ListData = API.ListData
import PostCategory = API.PostCategory
import Tag = API.Tag
import UserInfo = API.UserInfo
import 'highlight.js/styles/intellij-light.css'
import { convertToHeaders } from '../../utils/request'
import PostSidebar from '../../components/PostSidebar'
import CheckSignInStatus from '../../utils/CheckSignInStatus'

const { Title, Text } = Typography

const postRelevant5 = [
    {
        id: 1,
        title: '标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题'
    },
    {
        id: 2,
        title: '标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题'
    },
    {
        id: 3,
        title: '标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题'
    },
    {
        id: 4,
        title: '标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题'
    },
    {
        id: 5,
        title: '标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题'
    },
]

const postRelevant10 = [
    {
        id: 6,
        title: '标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题'
    },
    {
        id: 7,
        title: '标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题'
    },
    {
        id: 8,
        title: '标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题'
    },
    {
        id: 9,
        title: '标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题'
    },
    {
        id: 10,
        title: '标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题'
    },
]

export const getServerSideProps: GetServerSideProps = async (context: any) => {
    const accessToken = nookies.get(context)['accessToken']
    const userInfo: UserInfo | undefined = await CheckSignInStatus(context)
    const postPassword = context.query.postPassword
    const allPostCategory: APIResult<ListData<PostCategory>> = await api.queryPostCategoryList(convertToHeaders(context.req.headers), undefined, 1, 9007199254740991, accessToken)
    const allPostTag: APIResult<Tag[]> = await api.queryAllPostTagList(convertToHeaders(context.req.headers), accessToken)
    let postResult: APIResult<PostVo> = await api.getPostsById(context.query.id, convertToHeaders(context.req.headers), postPassword, accessToken)
    let commentTreeResult: APIResult<CommentTree[]> = await api.queryCommentTree(convertToHeaders(context.req.headers), 'POSTS', context.query.id, accessToken)
    if (postResult.code == 404) {
        return {
            notFound: true,
        }
    }
    return {
        props: {
            data: {
                userInfo: userInfo,
                post: postResult.data,
                allPostCategory: allPostCategory.data?.data,
                allPostTag: allPostTag.data,
                commentTreeResult: commentTreeResult.data,
            }
        }
    }
}

const PostPage = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {

    useEffect(() => {
        hljs.initHighlighting();
        let hljsElements = document.getElementsByClassName('hljs')
        for (let i = 0; i < hljsElements.length; i++) {
            hljsElements[i].innerHTML = "<ol><li>" + hljsElements[i].innerHTML.replace(/\n/g, "\n</li><li>") + "\n</li></ol>";
        }
    }, [])

    return (
        <>
            <Head>
                <title>{`${data.post.postTitle} - 博客文章 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
                <meta name="keyword" content={data.post.postKeyword} />
                <meta name="description" content={data.post.postExcerpt} />
            </Head>

            <main style={{ backgroundColor: '#ffffff' }}>
                <section className={"renfeid-content"}>
                    <div className={styles.container}>
                        <Text type="secondary">{data.post.postDate}</Text>
                        <Title level={1} className={styles.title}>{data.post.postTitle}</Title>
                        <Space size={4}>
                            <Button type="text" shape="circle" icon={<WechatFilled />} />
                            <Button type="text" shape="circle" icon={<QqOutlined />} />
                            <Button type="text" shape="circle" icon={<FacebookFilled />} />
                            <Button type="text" shape="circle" icon={<TwitterCircleFilled />} />
                            <Button type="text" shape="circle" icon={<WeiboCircleFilled />} />
                            <Button type="text" shape="circle" icon={<LinkedinFilled />} />
                            <Button type="text" shape="circle" icon={<LinkOutlined />} />
                            <Button type="text" shape="circle" icon={<EyeFilled />}>
                                {data.post.postViews}
                            </Button>
                        </Space>
                        <Divider />
                        <Row>
                            <Col xs={24} sm={24} md={16} lg={17}>
                                <div
                                    className={styles.posts_content}
                                    dangerouslySetInnerHTML={{ __html: data.post.postContent }}></div>
                                <Space size={4}>
                                    <Button type="text" shape="circle" icon={<WechatFilled />} />
                                    <Button type="text" shape="circle" icon={<QqOutlined />} />
                                    <Button type="text" shape="circle" icon={<FacebookFilled />} />
                                    <Button type="text" shape="circle" icon={<TwitterCircleFilled />} />
                                    <Button type="text" shape="circle" icon={<WeiboCircleFilled />} />
                                    <Button type="text" shape="circle" icon={<LinkedinFilled />} />
                                    <Button type="text" shape="circle" icon={<LinkOutlined />} />
                                    <Button type="text" shape="circle" icon={<EyeFilled />}>
                                        {data.post.postViews}
                                    </Button>
                                </Space>
                                <Divider />
                                <div className={styles.posts_content_copyright}>
                                    {
                                        data.post.original ? (
                                            <>
                                                <p>商业用途请联系作者获得授权。</p>
                                                <p>版权声明：本文为博主「任霏」原创文章，遵循 <a
                                                    href="http://creativecommons.org/licenses/by-nc-sa/4.0/"
                                                    target="_blank" rel="noreferrer noopener nofollow">CC BY-NC-SA
                                                    4.0</a> 版权协议，转载请附上原文出处链接及本声明。</p>
                                                <p>原文链接：<Link
                                                    href={'https://www.renfei.net/posts/' + data.post.id}>
                                                    <a>
                                                        https://www.renfei.net/posts/{data.post.id}
                                                    </a>
                                                </Link>
                                                </p>
                                            </>
                                        ) : (
                                            <>
                                                <p>商业用途请联系作者获得授权。</p>
                                                <p>版权声明：本文转载自「{data.post.sourceName}」，版权归原所有者。</p>
                                                <p>原文链接：<Link
                                                    href={data.post.sourceUrl}
                                                >
                                                    <a target="_blank" rel="noreferrer noopener nofollow">
                                                        {data.post.sourceUrl}
                                                    </a>
                                                </Link>
                                                </p>
                                            </>
                                        )
                                    }
                                </div>
                                <GoogleAdsense
                                    client={process.env.NEXT_PUBLIC_GOOGLE_ADSENSE}
                                    slot={"641095"}
                                />
                                <div className={"renfeid_card " + styles.posts_relevant}>
                                    <Title level={4} style={{ marginBottom: '0' }}>相关推荐</Title>
                                    <Text type="secondary">猜你还喜欢这些内容，不妨试试阅读一下</Text>
                                    <Row style={{ paddingTop: '10px' }}>
                                        <Col xs={24} sm={24} md={12} lg={12} style={{ padding: '0 5px' }}>
                                            {
                                                postRelevant5.length > 0 ? (
                                                    postRelevant5.map(post => (
                                                        <Link key={"relevant_" + post.id}
                                                            href={'/posts/' + post.id}>
                                                            <a key={"a_" + post.id}>
                                                                <Title key={"title_" + post.id} level={5}
                                                                    style={{ marginBottom: '0' }}
                                                                    ellipsis>
                                                                    {post.title}
                                                                </Title>
                                                                <Divider key={"divider_" + post.id}
                                                                    style={{ margin: '5px 0' }} />
                                                            </a>
                                                        </Link>
                                                    ))
                                                ) : ''
                                            }
                                        </Col>
                                        <Col xs={24} sm={24} md={12} lg={12} style={{ padding: '0 5px' }}>
                                            {
                                                postRelevant10.length > 0 ? (
                                                    postRelevant10.map(post => (
                                                        <Link key={"relevant_" + post.id}
                                                            href={'/posts/' + post.id}>
                                                            <a key={"a_" + post.id}>
                                                                <Title key={"title_" + post.id} level={5}
                                                                    style={{ marginBottom: '0' }}
                                                                    ellipsis>
                                                                    {post.title}
                                                                </Title>
                                                                <Divider key={"divider_" + post.id}
                                                                    style={{ margin: '5px 0' }} />
                                                            </a>
                                                        </Link>
                                                    ))
                                                ) : ''
                                            }
                                        </Col>
                                    </Row>
                                </div>
                                <Comments
                                    systemTypeEnum='POSTS'
                                    objectId={data.post.id}
                                    data={data.commentTreeResult}
                                    userInfo={data.userInfo} />
                            </Col>
                            <Col xs={24} sm={24} md={8} lg={7}>
                                <PostSidebar
                                    category={data.allPostCategory}
                                    tags={data.allPostTag}
                                    adsense={[]}
                                />
                            </Col>
                        </Row>
                    </div>
                </section>
            </main>
        </>
    )
}

PostPage.getLayout = (page: any) => {
    return (
        <Layout>
            {page}
        </Layout>
    )
}

export default PostPage
