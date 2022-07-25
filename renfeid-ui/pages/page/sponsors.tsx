import Head from 'next/head'
import Layout from '../../components/layout'
import styles from '../../styles/Page.module.css'

const SponsorsPage = () => {
    return (
        <>
            <Head>
                <title>赞助 - {process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}</title>
                <meta name="description" content={`${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}的赞助`}/>
            </Head>

            <main className={styles.main}>
                <section className={"renfeid-content"}>
                    section
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