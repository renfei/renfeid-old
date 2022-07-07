import Head from 'next/head'
import {Button, Result} from 'antd';
import Layout from "../components/layout";
import styles from "../styles/Home.module.css";

const Error404Page = () => {
    return (
        <>
            <div className={styles.container}>
                <Head>
                    <title>404 - 页面没有找到</title>
                    <meta name="description" content="页面没有找到"/>
                    <link rel="icon" href="/favicon.ico"/>
                </Head>

                <div style={{backgroundColor: '#ffffff'}}>
                    <Result
                        status="404"
                        title="404"
                        subTitle="抱歉，您请求的资源没有找到"
                        extra={<Button type="primary" href="/">回到首页</Button>}
                    />
                </div>
            </div>
        </>
    )
}

Error404Page.getLayout = (page: any) => {
    return (
        <Layout>
            {page}
        </Layout>
    )
}

export default Error404Page