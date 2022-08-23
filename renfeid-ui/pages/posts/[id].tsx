import Link from 'next/link'
import moment from 'moment'
import nookies from 'nookies'
import { NextSeo, ArticleJsonLd } from 'next-seo'
import React, { useState, useEffect } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import Layout from "../../components/layout"
import { Col, Row, Typography, Divider } from 'antd'
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
import Share from '../../components/Share'

const { Title, Text } = Typography


export const getServerSideProps: GetServerSideProps = async (context: any) => {
    const accessToken = nookies.get(context)['accessToken']
    const userInfo: UserInfo | null = await CheckSignInStatus(context)
    const postPassword = context.query.postPassword
    const allPostCategory: APIResult<ListData<PostCategory>> = await api.queryPostCategoryList(convertToHeaders(context.req.headers, context.req.socket.remoteAddress), undefined, 1, 2147483647, accessToken)
    const allPostTag: APIResult<Tag[]> = await api.queryAllPostTagList(convertToHeaders(context.req.headers, context.req.socket.remoteAddress), accessToken)
    const postResult: APIResult<PostVo> = await api.getPostsById(context.query.id, convertToHeaders(context.req.headers, context.req.socket.remoteAddress), postPassword, accessToken)
    const hotPosts: APIResult<ListData<PostVo>> = await api.getHotPosts(convertToHeaders(context.req.headers, context.req.socket.remoteAddress), 10, accessToken)
    const commentTreeResult: APIResult<CommentTree[]> = await api.queryCommentTree(convertToHeaders(context.req.headers, context.req.socket.remoteAddress), 'POSTS', context.query.id, accessToken)
    const lastCommentResult: APIResult<Comment[]> = await api.queryLastComment(convertToHeaders(context.req.headers, context.req.socket.remoteAddress), 'POSTS', '10', accessToken)
    const relatedPosts: APIResult<ListData<PostVo>> = await api.queryRelatedPostList(convertToHeaders(context.req.headers, context.req.socket.remoteAddress), context.query.id, 10, accessToken)
    let postRelevant5: PostVo[] = [], postRelevant10: PostVo[] = []
    if (relatedPosts.data?.data) {
        for (let i = 0; i < relatedPosts.data.data.length; i++) {
            if (i % 2 == 0) {
                postRelevant5.push(relatedPosts.data.data[i]);
            } else {
                postRelevant10.push(relatedPosts.data.data[i]);
            }
        }
    }
    if (postResult.code == 404) {
        return {
            notFound: true,
        }
    }
    return {
        props: {
            data: {
                userInfo: userInfo ? userInfo : null,
                hotPosts: hotPosts.data?.data,
                post: postResult.data,
                allPostCategory: allPostCategory.data?.data,
                allPostTag: allPostTag.data,
                commentTreeResult: commentTreeResult.data,
                lastCommentResult: lastCommentResult.data,
                postRelevant5: postRelevant5,
                postRelevant10: postRelevant10,
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
            <NextSeo
                title={`${data.post.postTitle} - 博客文章 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}
                description={data.post.postExcerpt}
                canonical={`${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}/posts/${data.post.id}`}
                openGraph={{
                    title: `${data.post.postTitle}`,
                    description: `${data.post.postExcerpt}`,
                    url: `${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}/posts/${data.post.id}`,
                    type: 'article',
                    article: {
                        publishedTime: `${moment(data.post.postDate).format()}`,
                        authors: [
                            `${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}`
                        ],
                    },
                    images: [
                        {
                            url: `${data.post.featuredImage}`,
                            width: 1280,
                            height: 640,
                            alt: `${data.post.postTitle}`,
                            type: 'image/jpeg',
                        }
                    ],
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
                url={`${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}/posts/${data.post.id}`}
                title={data.post.postTitle}
                images={[
                    `${data.post.featuredImage}`
                ]}
                datePublished={moment(data.post.postDate).format()}
                authorName={[
                    {
                        name: `${data.post.postAuthor}`,
                        url: `${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}`,
                    },
                ]}
                publisherName={process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}
                publisherLogo="https://cdn.renfei.net/Logo/RF.svg"
                description={`${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}
            />

            <main style={{ backgroundColor: '#ffffff' }}>
                <section className={"renfeid-content"}>
                    <div className={styles.container}>
                        <Text type="secondary">{data.post.postDate}</Text>
                        <Title level={1} className={styles.title}>{data.post.postTitle}</Title>
                        <Share
                            url={`${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}/posts/${data.post.id}`}
                            title={data.post.postTitle}
                            desc={data.post.postExcerpt}
                            pics={data.post.featuredImage}
                            views={data.post.postViews}
                        />
                        <Divider />
                        <Row>
                            <Col xs={24} sm={24} md={16} lg={17}>
                                <div
                                    className={styles.posts_content}
                                    dangerouslySetInnerHTML={{ __html: data.post.postContent }}></div>
                                <Share
                                    url={`${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}/posts/${data.post.id}`}
                                    title={data.post.postTitle}
                                    desc={data.post.postExcerpt}
                                    pics={data.post.featuredImage}
                                    views={data.post.postViews}
                                />
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
                                                data.postRelevant5.length > 0 ? (
                                                    data.postRelevant5.map((post: PostVo) => (
                                                        <Link key={"relevant_" + post.id}
                                                            href={'/posts/' + post.id}>
                                                            <a key={"a_" + post.id}>
                                                                <Title key={"title_" + post.id} level={5}
                                                                    style={{ marginBottom: '0' }}
                                                                    ellipsis>
                                                                    {post.postTitle}
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
                                                data.postRelevant10.length > 0 ? (
                                                    data.postRelevant10.map((post: PostVo) => (
                                                        <Link key={"relevant_" + post.id}
                                                            href={'/posts/' + post.id}>
                                                            <a key={"a_" + post.id}>
                                                                <Title key={"title_" + post.id} level={5}
                                                                    style={{ marginBottom: '0' }}
                                                                    ellipsis>
                                                                    {post.postTitle}
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
                                    hotPost={data.hotPosts}
                                    lastComment={data.lastCommentResult}
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
