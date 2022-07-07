import Head from 'next/head'
import {InferGetServerSidePropsType} from 'next'
import Layout from "../../components/layout";
import PostVo = API.PostVo;
import APIResult = API.APIResult;
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
} from '@ant-design/icons';
import * as api from '../../services/api'
import styles from "../../styles/CMS.module.css";
import GoogleAdsense from "../../components/GoogleAdsense";

const {Title, Text} = Typography;

export const getServerSideProps = async (context: any) => {
    let data: APIResult<PostVo>
    if (process.env.RENFEID_ENV == 'development') {
        // 开发环境不请求接口，直接返回假数据，方便写 UI
        data = {
            code: 200,
            message: "string",
            timestamp: 123,
            signature: "string",
            nonce: "string",
            data: {
                id: 123,
                categoryId: 123,
                postAuthor: 123,
                postDate: "2022-05-21 12:32:16",
                postStatus: "string",
                postViews: 123,
                commentStatus: "string",
                thumbsUp: 1,
                thumbsDown: 1,
                avgViews: 1,
                avgComment: 1,
                pageRank: 1,
                secretLevel: "string",
                featuredImage: "string",
                postTitle: "这是一篇用于开发调试的文章内容标题",
                postKeyword: "开发,调试,文章,内容",
                postExcerpt: "这里是用于开发调试的文章摘要字段，一般不会超过100字。",
                postContent: "string",
                sourceName: "string",
                sourceUrl: "string",
                original: true,
            }
        }
    } else {
        data = await api.getPostsById(context.query.id)
    }
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
            <div>
                <Head>
                    <title>{data.data.postTitle}</title>
                    <meta name="description" content={data.data.postKeyword}/>
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
                                    <div>
                                        正文
                                    </div>
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
                                    <GoogleAdsense
                                        client={"ca-pub-885975646380"}
                                        slot={"641095"}
                                    />
                                </Col>
                                <Col xs={24} sm={24} md={8} lg={7}>123</Col>
                            </Row>
                        </div>
                    </section>
                </main>
            </div>
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
