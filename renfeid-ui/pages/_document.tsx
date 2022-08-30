import NextDocument, { Html, Head, Main, NextScript } from 'next/document'
import { FrownOutlined } from '@ant-design/icons'
import React from 'react'
import Script from 'next/script'

type Props = {}

class Document extends NextDocument<Props> {
    render() {
        return (
            <Html lang="zh">
                <Head>
                    <meta charSet="utf-8" />
                    <meta httpEquiv="X-UA-Compatible" content="IE=edge" />
                    <meta
                        name="viewport"
                        content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"
                    />
                    <link rel="manifest" href="/manifest.json" />
                    <link
                        href="/icons/favicon-16x16.png"
                        rel="icon"
                        type="image/png"
                        sizes="16x16"
                    />
                    <link
                        href="/icons/favicon-32x32.png"
                        rel="icon"
                        type="image/png"
                        sizes="32x32"
                    />
                    <link rel="apple-touch-icon" href="https://cdn.renfei.net/Logo/logo_192.png"></link>
                    <meta name="theme-color" content="#303030" />
                </Head>
                <body>
                    <Main />
                    <NextScript />
                    <Script
                        strategy="lazyOnload"
                        src={`https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js?client=${process.env.NEXT_PUBLIC_GOOGLE_ADSENSE}`}
                        crossOrigin="anonymous" />
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