import Head from 'next/head'
import Image from 'next/image'
import Link from 'next/link'
import nookies from 'nookies'
import Layout from '../components/layout'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Col, Row, Button, Typography, Divider, Card, Comment, List, Tooltip, Space } from 'antd'
import moment from 'moment'
import { QRCodeSVG } from 'qrcode.react'
import { ArrowRightOutlined, WechatOutlined, GithubOutlined } from '@ant-design/icons'
import styles from '../styles/Home.module.css'
import GoogleAdsense from '../components/GoogleAdsense'
import PostVo = API.PostVo
import APIResult = API.APIResult
import ListData = API.ListData
import SiteFriendlyLinkVo = API.SiteFriendlyLinkVo
import * as api from '../services/api'
import { convertToHeaders } from '../utils/request'
import { useState } from 'react'

const { Title, Paragraph, Text } = Typography
const { Meta } = Card

// 微博数据
const weiboData = [
    {
        author: '任霏',
        avatar: 'https://cdn.renfei.net/images/renfei_600.jpg',
        content: (
            <p>
                这是我的一篇微博客。这是我的一篇微博客。这是我的一篇微博客。这是我的一篇微博客。这是我的一篇微博客。
                这是我的一篇微博客。这是我的一篇微博客。这是我的一篇微博客。这是我的一篇微博客。
            </p>
        ),
        datetime: (
            <Tooltip title={moment().subtract(1, 'days').format('yyyy-MM-DD HH:mm:ss')}>
                <span>{moment().subtract(1, 'days').fromNow()}</span>
            </Tooltip>
        ),
    },
    {
        author: '任霏',
        avatar: 'https://cdn.renfei.net/images/renfei_600.jpg',
        content: (
            <p>
                这是我的一篇微博客。这是我的一篇微博客。这是我的一篇微博客。这是我的一篇微博客。这是我的一篇微博客。
                这是我的一篇微博客。这是我的一篇微博客。这是我的一篇微博客。
            </p>
        ),
        datetime: (
            <Tooltip title={moment().subtract(2, 'days').format('yyyy-MM-DD HH:mm:ss')}>
                <span>{moment().subtract(2, 'days').fromNow()}</span>
            </Tooltip>
        ),
    },
]

export const getServerSideProps: GetServerSideProps = async (context: any) => {
    const accessToken = nookies.get(context)['accessToken']
    const result: APIResult<ListData<PostVo>> = await api.getPosts(convertToHeaders(context.req.headers, context.req.socket.remoteAddress), undefined, 1, 11, accessToken)
    const siteFriendlyLink: APIResult<SiteFriendlyLinkVo[]> = await api.queryFriendlyLink(convertToHeaders(context.req.headers, context.req.socket.remoteAddress), accessToken)

    let postsDataTop2: PostVo[] = []
    let postsData3To5: PostVo[] = []
    let postsData6To11: PostVo[] = []
    if (result.data?.data) {
        const posts: PostVo[] = result.data?.data
        if (posts && posts.length > 0) {
            for (let i = 0; i < 2 && i < posts.length; i++) {
                postsDataTop2.push(posts[i])
            }
        }
        if (posts && posts.length > 3) {
            for (let i = 2; i < 5 && i < posts.length; i++) {
                postsData3To5.push(posts[i])
            }
        }
        if (posts && posts.length > 6) {
            for (let i = 5; i < 11 && i < posts.length; i++) {
                postsData6To11.push(posts[i])
            }
        }
    }

    return {
        props: {
            data: {
                postsDataTop2: postsDataTop2,
                postsData3To5: postsData3To5,
                postsData6To11: postsData6To11,
                siteFriendlyLink: siteFriendlyLink,
            }
        }
    }
}

const Home = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
    const [SiteFriendlyLink] = useState<SiteFriendlyLinkVo[] | undefined>(data.siteFriendlyLink.data)
    return (
        <div className={styles.container}>
            <Head>
                <title>{`首页 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
                <meta name="description" content={`${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}是任霏的个人网站与博客，一个程序员自己写的网站，不仅仅是文章内容，还包括网站程序的代码。 对新鲜事物都十分感兴趣，利用这个站点向大家分享自己的所见所得，同时这个站点也是我的实验室。`} />
                <meta name="keywords" content="任霏,博客,任霏博客,RenFei,NeilRen,技术,blog" />
                <meta name="author" content="任霏,i@renfei.net" />
                <meta name="copyright" content="CopyRight RENFEI.NET, All Rights Reserved." />
            </Head>

            {
                data.postsDataTop2.length > 0 ? (
                    <div className={styles.banner_wrapper}>
                        <div className={styles.banner}>
                            <Row>
                                <Col span={15} className={styles.banner_col}>
                                    <div className={styles.banner_img_wrapper}>
                                        <Image
                                            className={styles.banner_img}
                                            layout='fill'
                                            src={data.postsDataTop2[0].featuredImage}
                                            alt={data.postsDataTop2[0].postTitle} />
                                    </div>
                                </Col>
                                <Col span={9} className={styles.banner_col}>
                                    <div className={styles.banner_content_wrapper}>
                                        <Link href={`/posts/${data.postsDataTop2[0].id}`} style={{ display: 'block' }}>
                                            <Title
                                                level={2}
                                                className={styles.banner_content_h2}>
                                                {data.postsDataTop2[0].postTitle}
                                            </Title>
                                            <p className={styles.banner_content_p}>
                                                {data.postsDataTop2[0].postExcerpt}
                                            </p>
                                            <span
                                                className={styles.banner_content_icon}><ArrowRightOutlined /></span>
                                        </Link>
                                    </div>
                                </Col>
                            </Row>
                        </div>
                    </div>
                ) : ''
            }
            <main className={styles.main}>
                <section className={"renfeid-content"}>
                    <div className={styles.posts_top5}>
                        <Title level={3}>任霏博客</Title>
                        <Row gutter={20}>
                            <Col span={12}>
                                {
                                    data.postsDataTop2.length > 1 ? (
                                        <Link href={`/posts/${data.postsDataTop2[1].id}`} style={{ display: 'block' }}>
                                            <Card
                                                bordered={false}
                                                cover={
                                                    <Image
                                                        style={{ borderRadius: '16px 16px 16px 16px' }}
                                                        width={1280}
                                                        height={640}
                                                        alt={data.postsDataTop2[1].postTitle}
                                                        src={data.postsDataTop2[1].featuredImage} />
                                                }
                                            >
                                                <Paragraph
                                                    ellipsis={{
                                                        rows: 2,
                                                        expandable: false,
                                                    }}
                                                    className={styles.banner_content_h3}>
                                                    {data.postsDataTop2[1].postTitle}
                                                </Paragraph>
                                                <Meta
                                                    title={data.postsDataTop2[1].postExcerpt} />
                                            </Card>
                                        </Link>
                                    ) : ''
                                }
                            </Col>
                            <Col span={12}>
                                <Row>
                                    {
                                        data.postsData3To5.length > 0 ? (
                                            data.postsData3To5.map((post: PostVo) => (
                                                <Col key={post.id} span={24}>
                                                    <Link href={`/posts/${post.id}`}>
                                                        <Row>
                                                            <Col span={15}>
                                                                <Paragraph
                                                                    ellipsis={{
                                                                        rows: 3,
                                                                        expandable: false,
                                                                    }}
                                                                    className={styles.banner_content_h3}>
                                                                    {post.postTitle}
                                                                </Paragraph>
                                                            </Col>
                                                            <Col span={9}>
                                                                <Image
                                                                    className={styles.posts_img_radius}
                                                                    src={post.featuredImage}
                                                                    alt={post.postTitle}
                                                                    layout={"intrinsic"}
                                                                    width={"1280"}
                                                                    height={"640"}
                                                                />
                                                            </Col>
                                                        </Row>
                                                    </Link>
                                                    <Divider style={{ margin: '12px 0' }} />
                                                </Col>
                                            ))
                                        ) : ''
                                    }
                                </Row>
                            </Col>
                        </Row>
                    </div>
                    <Divider>更多内容</Divider>
                    <Row gutter={20}>
                        {
                            data.postsData6To11.length > 0 ? (
                                data.postsData6To11.map((post: PostVo) => (
                                    <Col key={post.id} md={8} sm={12} xs={24} className={styles.posts_more_wrapper}>
                                        <div className={styles.posts_more}>
                                            <Link href={`/posts/${post.id}`}>
                                                <Row>
                                                    <Col span={15}>
                                                        <Paragraph
                                                            ellipsis={{
                                                                rows: 2,
                                                                expandable: false,
                                                            }}
                                                            className={styles.banner_content_h4}
                                                        >
                                                            {post.postTitle}
                                                        </Paragraph>
                                                    </Col>
                                                    <Col span={9}>
                                                        <Image
                                                            className={styles.posts_img_radius}
                                                            src={post.featuredImage}
                                                            alt={post.postTitle}
                                                            layout={"intrinsic"}
                                                            width={"1280"}
                                                            height={"640"}
                                                        />
                                                    </Col>
                                                </Row>
                                            </Link>
                                        </div>
                                    </Col>
                                ))
                            ) : ''
                        }
                    </Row>
                    <div style={{ textAlign: 'center', padding: '20px 0 40px 0' }}>
                        <Link href={"/posts"}>
                            <Button style={{ padding: '0 40px' }}>浏览任霏博客</Button>
                        </Link>
                    </div>
                    <Row gutter={40}>
                        <Col sm={12} xs={24}>
                            <Divider>任霏微博</Divider>
                            <List
                                className="comment-list"
                                itemLayout="horizontal"
                                dataSource={weiboData}
                                renderItem={item => (
                                    <li>
                                        <Comment
                                            author={item.author}
                                            avatar={item.avatar}
                                            content={item.content}
                                            datetime={item.datetime}
                                        />
                                        <Divider />
                                    </li>
                                )}
                            />
                        </Col>
                        <Col sm={12} xs={24}>
                            <Divider>更多内容</Divider>
                            <GoogleAdsense
                                client={process.env.NEXT_PUBLIC_GOOGLE_ADSENSE}
                                slot={"641095"}
                            />
                            <Row gutter={20}>
                                <Col lg={12} xs={24}>
                                    <div className={'renfeid_card'}>
                                        <Title level={5}><WechatOutlined style={{ paddingRight: '10px' }} />微信订阅号</Title>
                                        <Text type="secondary" style={{ fontSize: '12px' }}>扫码关注「任霏博客」微信订阅号</Text>
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
                                </Col>
                                <Col lg={12} xs={24}>
                                    <div className={'renfeid_card'}>
                                        <Title level={5}><GithubOutlined style={{ paddingRight: '10px' }} />开源与反馈</Title>
                                        <Text type="secondary" style={{ fontSize: '12px' }}>基于开源回归开源</Text>
                                        <ul style={{ paddingLeft: '15px' }}>
                                            <li>开源仓库：<Link href="https://github.com/renfei/renfeid" target={'_blank'} rel={'nofollow noopener'}>github.com/renfei/renfeid</Link></li>
                                            <li>反馈讨论：<Link href="https://github.com/renfei/feedback/discussions" target={'_blank'} rel={'nofollow noopener'}>github.com/renfei/feedback/discussions</Link></li>
                                            <li>开发预览：<Link href="https://preview.renfei.net/" target={'_blank'} rel={'nofollow noopener'}>preview.renfei.net</Link></li>
                                            <li>更多内容：参见《<Link href="/page/about" target={'_blank'}>关于页面</Link>》</li>
                                        </ul>
                                    </div>
                                </Col>
                            </Row>
                        </Col>
                    </Row>
                </section>
                <div
                    className={"renfeid-content"}
                    style={{ fontSize: '12px', color: 'rgba(0, 0, 0, 0.6)!important', fontWeight: '100' }}
                >
                    {
                        SiteFriendlyLink && SiteFriendlyLink.length > 0 ? (
                            <div style={{ padding: '20px 0' }}>
                                友情链接：
                                <Space>
                                    {
                                        SiteFriendlyLink.map(friendly => (
                                            <a
                                                key={friendly.text}
                                                href={friendly.link}
                                                target="_blank"
                                                rel="noopener noreferrer"
                                            >
                                                {friendly.text}
                                            </a>
                                        ))
                                    }
                                </Space>
                            </div>
                        ) : ''
                    }
                </div>
            </main>
        </div>
    )
}

Home.getLayout = (page: any) => {
    return (
        <Layout>
            {page}
        </Layout>
    )
}

export default Home
