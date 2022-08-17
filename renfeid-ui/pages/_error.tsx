import Head from 'next/head'
import Link from 'next/link'
import { CloseCircleOutlined } from '@ant-design/icons'
import { Button, Result, Typography } from 'antd'
import Layout from "../components/layout"
import styles from "../styles/Home.module.css"

const { Paragraph, Text } = Typography

const ErrorPage = ({ err }: any) => {
    // @ts-ignore
    return (
        <>
            <div className={styles.container}>
                <Head>
                    <title>500 - 服务器错误</title>
                    <meta name="description" content="服务器错误" />
                    <link rel="icon" href="/favicon.ico" />
                </Head>

                <div style={{ backgroundColor: '#ffffff' }}>
                    <div className={"renfeid-content"}>
                        <Result
                            status="error"
                            title="我们遇到了一些问题"
                            subTitle="抱歉，我们现在遇到了一些问题无法提供服务，您可以重新尝试一下。"
                            extra={[
                                <Button type="primary" key="console" href="/">
                                    回到首页
                                </Button>,
                                <Button key="buy" onClick={() => {
                                    location.reload()
                                }}>刷新页面</Button>,
                            ]}
                        >
                            <div className="desc">
                                <Paragraph>
                                    <Text
                                        strong
                                        style={{
                                            fontSize: 16,
                                        }}
                                    >
                                        关于这个问题现在能提供有限的帮助信息：
                                    </Text>
                                </Paragraph>
                                {
                                    err ? (
                                        err.code == 'ECONNREFUSED' ? (
                                            <>
                                                <Paragraph>
                                                    <CloseCircleOutlined className="site-result-demo-error-icon" />
                                                    经过检测，故障原因为：后端服务拒绝了我们的连接，我们暂时无法为您修复，请过段时间再次尝试。
                                                </Paragraph>
                                                <Paragraph>
                                                    <CloseCircleOutlined className="site-result-demo-error-icon" />
                                                    向我们提交故障报告：
                                                    <Link href="https://github.com/renfei/feedback/discussions"><a target='_blank'
                                                        rel='nofollow noopener noreferrer'>https://github.com/renfei/feedback/discussions</a></Link>，
                                                    或者邮件联系我们：<Link href="mailto:i@renfei.net" target={'_blank'}
                                                        rel="nofollow noopener noreferrer"><a>i@renfei.net</a></Link>
                                                </Paragraph>
                                            </>
                                        ) : (
                                            <>
                                                <Paragraph>
                                                    <CloseCircleOutlined className="site-result-demo-error-icon" />
                                                    程序遇到不可预知的错误，暂时无法提供服务，我们会积极抢修。
                                                </Paragraph>
                                                <Paragraph>
                                                    <CloseCircleOutlined className="site-result-demo-error-icon" />
                                                    后端服务可能过载或无法响应我们的请求，请稍后再试。
                                                </Paragraph>
                                                <Paragraph>
                                                    <CloseCircleOutlined className="site-result-demo-error-icon" />
                                                    向我们提交故障报告：
                                                    <Link href="https://github.com/renfei/feedback/discussions"><a target='_blank'
                                                        rel='nofollow noopener noreferrer'>https://github.com/renfei/feedback/discussions</a></Link>，
                                                    或者邮件联系我们：<Link href="mailto:i@renfei.net"><a target='_blank'
                                                        rel='nofollow noopener noreferrer'>i@renfei.net</a></Link>
                                                </Paragraph>
                                            </>
                                        )
                                    ) : (
                                        <>
                                            <Paragraph>
                                                <CloseCircleOutlined className="site-result-demo-error-icon" />
                                                请尝试强制刷新：如果你是 Windows 系统用户，请按 Ctrl+F5 组合键强制刷新重试。
                                            </Paragraph>
                                            <Paragraph>
                                                <CloseCircleOutlined className="site-result-demo-error-icon" />
                                                请尝试强制刷新：如果你是 MacOS 系统用户，请按 Command+Shift+R 组合键强制刷新重试。
                                            </Paragraph>
                                            <Paragraph>
                                                <CloseCircleOutlined className="site-result-demo-error-icon" />
                                                请尝试清除浏览器记录或更换浏览器重试。
                                            </Paragraph>
                                            <Paragraph>
                                                <CloseCircleOutlined className="site-result-demo-error-icon" />
                                                向我们提交故障报告：
                                                <Link href="https://github.com/renfei/feedback/discussions" ><a target='_blank'
                                                    rel='nofollow noopener noreferrer'>https://github.com/renfei/feedback/discussions</a></Link>，
                                                或者邮件联系我们：<Link href="mailto:i@renfei.net"><a target='_blank'
                                                    rel='nofollow noopener noreferrer'>i@renfei.net</a></Link>
                                            </Paragraph>
                                        </>
                                    )
                                }
                            </div>
                        </Result>
                    </div>
                </div>
            </div>
        </>
    )
}

ErrorPage.getInitialProps = ({ res, err }: any) => {
    return { err }
}

ErrorPage.getLayout = (page: any) => {
    return (
        <Layout>
            {page}
        </Layout>
    )
}

export default ErrorPage