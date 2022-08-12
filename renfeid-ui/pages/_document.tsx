import NextDocument, { Html, Head, Main, NextScript } from 'next/document'
import { FrownOutlined } from '@ant-design/icons'
import React from 'react'
import Script from 'next/script'

type Props = {}

class Document extends NextDocument<Props> {
    render() {
        return (
            <Html>
                <Head />
                <body>
                    <div id="noscript-waring" style={{ border: '5px solid #ff0000', padding: '20px', backgroundColor: '#ffff00', color: '#ff0000' }}>
                        <FrownOutlined />您的浏览器未启用 JavaScript 脚本功能，我站将无法正常显示和提供正常的功能，请先启用 JavaScript 脚本功能。
                    </div>
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