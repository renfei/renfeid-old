import Head from 'next/head'
import Link from 'next/link'
import nookies from 'nookies'
import React, { useState, useEffect } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import Layout from "../../components/layout"

export const getServerSideProps: GetServerSideProps = async (context: any) => {
    const accessToken = nookies.get(context)['accessToken']
    return {
        props: {
            data: {}
        }
    }
}

const PostsPage = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
    return (
        <>
            <Head>
                <title>{`博客文章 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
                <meta name="keyword" content="任霏,renfei,博客,blog,任霏博客,renfei blog,内容" />
                <meta name="description" content="任霏的个人博客栏目，专注分享科技、软件、开发等技术内容，记录分享个人的实践与成长。" />
                <meta name="author" content="任霏,i@renfei.net" />
                <meta name="copyright" content="CopyRight RENFEI.NET, All Rights Reserved." />
                <meta http-equiv="Content-Language" content="zh-CN" />
            </Head>

            <main style={{ backgroundColor: '#ffffff' }}>
                <section className={"renfeid-content"}>
                    x
                </section>
            </main>
        </>
    )
}

PostsPage.getLayout = (page: any) => {
    return (
        <Layout>
            {page}
        </Layout>
    )
}

export default PostsPage
