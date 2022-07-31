import Head from 'next/head'
import Image from 'next/image'
import Link from 'next/link'
import nookies from 'nookies'
import Layout from '../components/layout'
import { Col, Row, Button, Typography, Divider, Card, Comment, List, Tooltip, Space } from 'antd'
import moment from 'moment'
import { ArrowRightOutlined } from '@ant-design/icons'
import styles from '../styles/Home.module.css'
import GoogleAdsense from '../components/GoogleAdsense'

const { Title, Paragraph } = Typography
const { Meta } = Card

// 文章 1到2
const postsDataTop2 = [
    {
        id: 123,
        postTitle: '关于我在极狐GitLab造机器人这件事儿我觉得很酷',
        postExcerpt: '我在参与极狐GitLab创作营 JIHULAB 101活动的时候，发现极狐GitLab官方启用了一个机器人，会在issue哪里进行服务，我就突发奇想，很多地方都有自动回复的客服机器人，那在极狐GitLab能不能造个机器人玩？',
        featuredImage: 'https://cdn.renfei.net/upload/2022/2af9436a41c945e58b98d5ac09835179.webp',
    },
    {
        id: 124,
        postTitle: '如何参与极狐GitLab开源项目成为贡献者',
        postExcerpt: '嗨，小伙伴，你是否也希望参与到极狐GitLab开源项目的建设，成为贡献者？但作为新手似乎无从下手？其实，每个人都可以参与到极狐GitLab开源项目中成为贡献者，无论你是否是技术人员。',
        featuredImage: 'https://cdn.renfei.net/upload/2022/9b69f9f788e74d30a845a3f85f0a2797.webp',
    },
]

// 文章 3到5
const postsData3To5 = [
    {
        id: 125,
        postTitle: '关于 Cloudflare R2 Storage 的使用体验测评和我的观点',
        postExcerpt: '',
        featuredImage: 'https://cdn.renfei.net/upload/2022/35ac66d2e9214facbbcc02643692fffb.jpg',
    },
    {
        id: 126,
        postTitle: '西部数据（WD40NMZW） 4TB Elements（2060-800041-003）移动硬盘拆解记录',
        postExcerpt: '',
        featuredImage: 'https://cdn.renfei.net/upload/2022/45723ed1db3b4a7196db995340a1a9ec.jpg',
    },
    {
        id: 127,
        postTitle: '获取公网IP服务「ip.renfei.net」升级，支持根据请求头 Accept 响应不同格式数据',
        postExcerpt: '',
        featuredImage: 'https://cdn.renfei.net/upload/2022/108b72905f1a4cf59b9bf3431c78e480.jpg',
    },
]

// 文章 6到11
const postsData6To11 = [
    {
        id: 128,
        postTitle: '关于 Cloudflare R2 Storage 的使用体验测评和我的观点',
        postExcerpt: '',
        featuredImage: 'https://cdn.renfei.net/upload/2022/35ac66d2e9214facbbcc02643692fffb.jpg',
    },
    {
        id: 129,
        postTitle: '西部数据（WD40NMZW） 4TB Elements（2060-800041-003）移动硬盘拆解记录',
        postExcerpt: '',
        featuredImage: 'https://cdn.renfei.net/upload/2022/45723ed1db3b4a7196db995340a1a9ec.jpg',
    },
    {
        id: 130,
        postTitle: '获取公网IP服务「ip.renfei.net」升级，支持根据请求头 Accept 响应不同格式数据',
        postExcerpt: '',
        featuredImage: 'https://cdn.renfei.net/upload/2022/108b72905f1a4cf59b9bf3431c78e480.jpg',
    },
    {
        id: 131,
        postTitle: '西部数据（WD40NMZW） 4TB Elements（2060-800041-003）移动硬盘拆解记录',
        postExcerpt: '',
        featuredImage: 'https://cdn.renfei.net/upload/2022/45723ed1db3b4a7196db995340a1a9ec.jpg',
    },
    {
        id: 132,
        postTitle: '关于 Cloudflare R2 Storage 的使用体验测评和我的观点',
        postExcerpt: '',
        featuredImage: 'https://cdn.renfei.net/upload/2022/35ac66d2e9214facbbcc02643692fffb.jpg',
    },
    {
        id: 133,
        postTitle: '获取公网IP服务「ip.renfei.net」升级，支持根据请求头 Accept 响应不同格式数据',
        postExcerpt: '',
        featuredImage: 'https://cdn.renfei.net/upload/2022/108b72905f1a4cf59b9bf3431c78e480.jpg',
    },
]

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

const friendlyLinks = [
    {
        title: '任霏博客',
        link: 'https://www.renfei.net',
    },
]

const Home = () => {
    return (
        <div className={styles.container}>
            <Head>
                <title>首页 - {process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}</title>
                <meta name="description" content={`${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}是任霏的个人网站与博客，一个程序员自己写的网站，不仅仅是文章内容，还包括网站程序的代码。 对新鲜事物都十分感兴趣，利用这个站点向大家分享自己的所见所得，同时这个站点也是我的实验室。`} />
                <meta name="keywords" content="任霏,博客,任霏博客,RenFei,NeilRen,技术,blog" />
                <meta name="author" content="任霏,i@renfei.net" />
                <meta name="copyright" content="CopyRight RENFEI.NET, All Rights Reserved." />
                <link rel="icon" href="/favicon.ico" />
            </Head>

            {
                postsDataTop2.length > 0 ? (
                    <div className={styles.banner_wrapper}>
                        <div className={styles.banner}>
                            <Row>
                                <Col span={15} className={styles.banner_col}>
                                    <div className={styles.banner_img_wrapper}>
                                        <Image
                                            className={styles.banner_img}
                                            layout='fill'
                                            src={postsDataTop2[0].featuredImage}
                                            alt={postsDataTop2[0].postTitle} />
                                    </div>
                                </Col>
                                <Col span={9} className={styles.banner_col}>
                                    <div className={styles.banner_content_wrapper}>
                                        <Link href={`/posts/${postsDataTop2[0].id}`} style={{ display: 'block' }}>
                                            <a>
                                                <Title
                                                    level={2}
                                                    className={styles.banner_content_h2}>
                                                    {postsDataTop2[0].postTitle}
                                                </Title>
                                                <p className={styles.banner_content_p}>
                                                    {postsDataTop2[0].postExcerpt}
                                                </p>
                                                <span
                                                    className={styles.banner_content_icon}><ArrowRightOutlined /></span>
                                            </a>
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
                        <Row>
                            <Col span={12} className={styles.posts_top5_left}>
                                {
                                    postsDataTop2.length > 1 ? (
                                        <Link href={`/posts/${postsDataTop2[1].id}`} style={{ display: 'block' }}>
                                            <a>
                                                <Card
                                                    bordered={false}
                                                    cover={
                                                        <Image
                                                            style={{ borderRadius: '16px 16px 16px 16px' }}
                                                            width={1280}
                                                            height={640}
                                                            alt={postsDataTop2[1].postTitle}
                                                            src={postsDataTop2[1].featuredImage} />
                                                    }
                                                >
                                                    <Paragraph
                                                        ellipsis={{
                                                            rows: 2,
                                                            expandable: false,
                                                        }}
                                                        className={styles.banner_content_h3}>
                                                        {postsDataTop2[1].postTitle}
                                                    </Paragraph>
                                                    <Meta
                                                        title={postsDataTop2[1].postExcerpt} />
                                                </Card>
                                            </a>
                                        </Link>
                                    ) : ''
                                }
                            </Col>
                            <Col span={12} className={styles.posts_top5_right}>
                                <Row>
                                    {
                                        postsData3To5.length > 0 ? (
                                            postsData3To5.map(post => (
                                                <Col key={post.id} span={24}>
                                                    <Link href={`/posts/${post.id}`}>
                                                        <a>
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
                                                        </a>
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
                    <Row>
                        {
                            postsData6To11.length > 0 ? (
                                postsData6To11.map(post => (
                                    <Col key={post.id} md={8} sm={12} xs={24} className={styles.posts_more_wrapper}>
                                        <div className={styles.posts_more}>
                                            <Link href={"/"}>
                                                <a>
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
                                                </a>
                                            </Link>
                                        </div>
                                    </Col>
                                ))
                            ) : ''
                        }
                    </Row>
                    <div style={{ textAlign: 'center', padding: '20px 0 40px 0' }}>
                        <Link href={"/posts"}>
                            <a>
                                <Button style={{ padding: '0 40px' }}>浏览任霏博客</Button>
                            </a>
                        </Link>
                    </div>
                    <Row>
                        <Col sm={12} xs={24} style={{ padding: '0 20px 0 0' }}>
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
                        <Col sm={12} xs={24} style={{ padding: '0 0 0 20px' }}>
                            <Divider>更多内容</Divider>
                            <GoogleAdsense
                                client={"ca-pub-885975646380"}
                                slot={"641095"}
                            />
                        </Col>
                    </Row>
                </section>
                <div
                    className={"renfeid-content"}
                    style={{ fontSize: '12px', color: 'rgba(0, 0, 0, 0.6)!important', fontWeight: '100' }}
                >
                    {
                        friendlyLinks.length > 0 ? (
                            <div style={{ padding: '20px 0' }}>
                                友情链接：
                                <Space>
                                    {
                                        friendlyLinks.map(friendly => (
                                            <a
                                                key={friendly.title}
                                                href={friendly.link}
                                                target="_blank"
                                                rel="noopener noreferrer"
                                            >
                                                {friendly.title}
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
