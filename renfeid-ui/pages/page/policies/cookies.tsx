import Head from 'next/head'
import Layout from '../../../components/layout'
import styles from '../../../styles/Page.module.css'

const CookiesPoliciesPage = () => {
    return (
        <>
            <Head>
                <title>如何使用 Cookie - 隐私权和条款 - {process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}</title>
                <meta name="description" content={`${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}如何使用 Cookie。`}/>
            </Head>

            <main className={styles.main}>
                <section className={"renfeid-content"}>
                    section
                </section>
            </main>
        </>
    )
}

CookiesPoliciesPage.getLayout = (page: any) => {
    return (
        <Layout>
            {page}
        </Layout>
    )
}

export default CookiesPoliciesPage