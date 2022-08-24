import Head from 'next/head'
import { Alert } from 'antd'
import Layout from '../../components/layout'
import styles from '../../styles/Page.module.css'

const SponsorsPage = () => {
    return (
        <>
            <Head>
                <title>{`赞助 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
                <meta name="description" content={`${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}的赞助`} />
            </Head>

            <main className={styles.main}>
                <section className={"renfeid-content"}>
                    <div style={{ padding: '20px 0' }}>
                        <Alert
                            message="此部分闭源"
                            description="该页面位于私有仓库闭源，在此处只做占位案例，你可以根据文件位置关系，创建自己的页面。"
                            type="info"
                            showIcon
                        />
                    </div>
                </section>
            </main>
        </>
    )
}

SponsorsPage.getLayout = (page: any) => {
    return (
        <Layout>
            {page}
        </Layout>
    )
}

export default SponsorsPage