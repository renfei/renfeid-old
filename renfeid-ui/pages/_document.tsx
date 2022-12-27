import NextDocument, { Html, Head, Main, NextScript } from 'next/document'
import { FrownOutlined } from '@ant-design/icons'
import React from 'react'
import Script from 'next/script'

type Props = {}

const getAnalyticsTag = () => {
    return {
        __html: `window.dataLayer = window.dataLayer || [];function gtag(){dataLayer.push(arguments)}gtag('js', new Date());gtag('config', '${process.env.NEXT_PUBLIC_GOOGLE_ANALYTICS}', {page_path: window.location.pathname,});`,
    }
}

class Document extends NextDocument<Props> {
    render() {
        return (
            <Html lang="zh-CN">
                <Head>
                    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                    <meta http-equiv="Content-Language" content="zh-CN" />
                    <meta name="viewport" content="width=device-width, initial-scale=1" />
                    <meta name="renderer" content="webkit" />
                    <meta http-equiv="X-UA-Compatible" content="edge" />
                    <link rel="icon" href="/favicon.ico" />
                    <link rel="dns-prefetch" href="https://cdn.renfei.net"></link>
                    <link rel="dns-prefetch" href="https://cloudflareinsights.com"></link>
                    <link rel="dns-prefetch" href="https://hm.baidu.com"></link>
                    <link rel="dns-prefetch" href="https://www.google-analytics.com"></link>
                    <link rel="dns-prefetch" href="https://adservice.google.com"></link>
                    <link rel="dns-prefetch" href="https://googleads.g.doubleclick.net"></link>
                    <link rel="dns-prefetch" href="https://pagead2.googlesyndication.com"></link>
                    <link rel="dns-prefetch" href="https://fundingchoicesmessages.google.com"></link>
                    <script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js" crossOrigin="anonymous"></script>
                    <script dangerouslySetInnerHTML={getAnalyticsTag()} />
                </Head>
                <body>
                    <Main />
                    <NextScript />
                    <Script
                        strategy="lazyOnload"
                        async
                        src={`https://www.googletagmanager.com/gtag/js?id=${process.env.NEXT_PUBLIC_GOOGLE_ANALYTICS}`}
                    />
                    <Script
                        strategy="lazyOnload"
                        defer
                        src="https://static.cloudflareinsights.com/beacon.min.js"
                        data-cf-beacon={`{"token": "${process.env.NEXT_PUBLIC_CLOUDFLARE_ANALYTICS}"}`}
                    />
                </body>
            </Html>
        )
    }
}

export default Document