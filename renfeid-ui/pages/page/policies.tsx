import Head from 'next/head'
import Layout from '../../components/layout'
import styles from '../../styles/Page.module.css'

const PoliciesPage = () => {
    return (
        <>
            <Head>
                <title>隐私权和条款政策 - {process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}</title>
                <meta name="description" content={`${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}的隐私权和条款政策`}/>
            </Head>

            <main className={styles.main}>
                <section className={"renfeid-content"}>
                    section
                </section>
            </main>
        </>
    )
}

PoliciesPage.getLayout = (page: any) => {
    return (
        <Layout>
            {page}
        </Layout>
    )
}

export default PoliciesPage