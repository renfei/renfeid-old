import Head from 'next/head'
import Link from 'next/link'
import nookies from 'nookies'
import {GetServerSideProps, InferGetServerSidePropsType} from 'next'
import Layout from "../../components/layout"
import PostVo = API.PostVo
import APIResult = API.APIResult
import {Col, Row, Button, Typography, Divider, Card, Comment, List, Tooltip, Space} from 'antd'
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
import CommentTree = API.CommentTree

const {Title, Text} = Typography

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

const CommentList: CommentTree[] = [
    {
        id: '1',
        addtime: '2022-07-08 15:32:32',
        isOwner: false,
        author: '用户1',
        authorUrl: 'https://www.renfei.net',
        authorAddress: 'Beijing, Beijing, China',
        content: '评论演示数据',
        children: [
            {
                id: '3',
                addtime: '2022-07-08 15:32:32',
                isOwner: true,
                author: '用户3',
                authorUrl: 'https://www.renfei.net',
                authorAddress: 'Beijing, Beijing, China',
                content: '评论演示数据',
                children: [
                    {
                        id: '4',
                        addtime: '2022-07-08 15:32:32',
                        isOwner: false,
                        author: '用户4',
                        authorUrl: 'https://www.renfei.net',
                        authorAddress: 'Beijing, Beijing, China',
                        content: '评论演示数据',
                    },
                ]
            },
            {
                id: '5',
                addtime: '2022-07-08 15:32:32',
                isOwner: false,
                author: '用户5',
                authorUrl: 'https://www.renfei.net',
                authorAddress: 'Beijing, Beijing, China',
                content: '评论演示数据',
            },
        ]
    },
    {
        id: '2',
        addtime: '2022-07-08 15:32:32',
        isOwner: false,
        author: '用户6',
        authorUrl: 'https://www.renfei.net',
        authorAddress: 'Beijing, Beijing, China',
        content: '评论演示数据',
    }
]

export const getServerSideProps: GetServerSideProps = async (context: any) => {
    const accessToken = nookies.get(context)['accessToken']
    const postPassword = context.req.query?.postPassword
    let data: APIResult<PostVo> = await api.getPostsById(context.query.id, context.req.headers, postPassword, accessToken)
    if (data.code == 404) {
        return {
            notFound: true,
        }
    }
    return {
        props: {
            data,
        }
    }
}

const PostPage = ({data}: InferGetServerSidePropsType<typeof getServerSideProps>) => {
    return (
        <>
            <Head>
                <title>{data.data.postTitle}</title>
                <meta name="keyword" content={data.data.postKeyword}/>
                <meta name="description" content={data.data.postExcerpt}/>
                <link rel="icon" href="/favicon.ico"/>
            </Head>

            <main style={{backgroundColor: '#ffffff'}}>
                <section className={"renfeid-content"}>
                    <div className={styles.container}>
                        <Text type="secondary">{data.data.postDate}</Text>
                        <Title level={1} className={styles.title}>{data.data.postTitle}</Title>
                        <Space size={4}>
                            <Button type="text" shape="circle" icon={<WechatFilled/>}/>
                            <Button type="text" shape="circle" icon={<QqOutlined/>}/>
                            <Button type="text" shape="circle" icon={<FacebookFilled/>}/>
                            <Button type="text" shape="circle" icon={<TwitterCircleFilled/>}/>
                            <Button type="text" shape="circle" icon={<WeiboCircleFilled/>}/>
                            <Button type="text" shape="circle" icon={<LinkedinFilled/>}/>
                            <Button type="text" shape="circle" icon={<LinkOutlined/>}/>
                            <Button type="text" shape="circle" icon={<EyeFilled/>}>
                                {data.data.postViews}
                            </Button>
                        </Space>
                        <Divider/>
                        <Row>
                            <Col xs={24} sm={24} md={16} lg={17}>
                                <div
                                    className={styles.posts_content}
                                    dangerouslySetInnerHTML={{__html: data.data.postContent}}></div>
                                <Space size={4}>
                                    <Button type="text" shape="circle" icon={<WechatFilled/>}/>
                                    <Button type="text" shape="circle" icon={<QqOutlined/>}/>
                                    <Button type="text" shape="circle" icon={<FacebookFilled/>}/>
                                    <Button type="text" shape="circle" icon={<TwitterCircleFilled/>}/>
                                    <Button type="text" shape="circle" icon={<WeiboCircleFilled/>}/>
                                    <Button type="text" shape="circle" icon={<LinkedinFilled/>}/>
                                    <Button type="text" shape="circle" icon={<LinkOutlined/>}/>
                                    <Button type="text" shape="circle" icon={<EyeFilled/>}>
                                        {data.data.postViews}
                                    </Button>
                                </Space>
                                <Divider/>
                                <div className={styles.posts_content_copyright}>
                                    {
                                        data.data.original ? (
                                            <>
                                                <p>商业用途请联系作者获得授权。</p>
                                                <p>版权声明：本文为博主「任霏」原创文章，遵循 <a
                                                    href="http://creativecommons.org/licenses/by-nc-sa/4.0/"
                                                    target="_blank" rel="noreferrer noopener nofollow">CC BY-NC-SA
                                                    4.0</a> 版权协议，转载请附上原文出处链接及本声明。</p>
                                                <p>原文链接：<Link
                                                    href={'https://www.renfei.net/posts/' + data.data.id}>
                                                    <a>
                                                        https://www.renfei.net/posts/{data.data.id}
                                                    </a>
                                                </Link>
                                                </p>
                                            </>
                                        ) : (
                                            <>
                                                <p>商业用途请联系作者获得授权。</p>
                                                <p>版权声明：本文转载自「{data.data.sourceName}」，版权归原所有者。</p>
                                                <p>原文链接：<Link
                                                    href={data.data.sourceUrl}
                                                >
                                                    <a target="_blank" rel="noreferrer noopener nofollow">
                                                        {data.data.sourceUrl}
                                                    </a>
                                                </Link>
                                                </p>
                                            </>
                                        )
                                    }
                                </div>
                                <GoogleAdsense
                                    client={"ca-pub-885975646380"}
                                    slot={"641095"}
                                />
                                <div className={"renfeid_card " + styles.posts_relevant}>
                                    <Title level={4} style={{marginBottom: '0'}}>相关推荐</Title>
                                    <Text type="secondary">猜你还喜欢这些内容，不妨试试阅读一下</Text>
                                    <Row style={{paddingTop: '10px'}}>
                                        <Col xs={24} sm={24} md={12} lg={12} style={{padding: '0 5px'}}>
                                            {
                                                postRelevant5.length > 0 ? (
                                                    postRelevant5.map(post => (
                                                        <Link key={"relevant_" + post.id}
                                                              href={'/posts/' + post.id}>
                                                            <a key={"a_" + post.id}>
                                                                <Title key={"title_" + post.id} level={5}
                                                                       style={{marginBottom: '0'}}
                                                                       ellipsis>
                                                                    {post.title}
                                                                </Title>
                                                                <Divider key={"divider_" + post.id}
                                                                         style={{margin: '5px 0'}}/>
                                                            </a>
                                                        </Link>
                                                    ))
                                                ) : ''
                                            }
                                        </Col>
                                        <Col xs={24} sm={24} md={12} lg={12} style={{padding: '0 5px'}}>
                                            {
                                                postRelevant10.length > 0 ? (
                                                    postRelevant10.map(post => (
                                                        <Link key={"relevant_" + post.id}
                                                              href={'/posts/' + post.id}>
                                                            <a key={"a_" + post.id}>
                                                                <Title key={"title_" + post.id} level={5}
                                                                       style={{marginBottom: '0'}}
                                                                       ellipsis>
                                                                    {post.title}
                                                                </Title>
                                                                <Divider key={"divider_" + post.id}
                                                                         style={{margin: '5px 0'}}/>
                                                            </a>
                                                        </Link>
                                                    ))
                                                ) : ''
                                            }
                                        </Col>
                                    </Row>
                                </div>
                                <Comments data={CommentList}/>
                            </Col>
                            <Col xs={24} sm={24} md={8} lg={7}>123</Col>
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
