import Head from 'next/head'
import Layout from '../../components/layout'
import styles from '../../styles/Page.module.css'

const AboutPage = () => {
    return (
        <>
            <Head>
                <title>关于任霏 - {process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}</title>
                <meta name="description" content={`关于${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}/>
            </Head>

            <main className={styles.main}>
                <section className={"renfeid-content"}>
                    section
                </section>
            </main>
        </>
    )
}

AboutPage.getLayout = (page: any) => {
    return (
        <Layout>
            {page}
        </Layout>
    )
}

export default AboutPage