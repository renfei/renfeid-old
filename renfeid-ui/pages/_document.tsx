import NextDocument, {Html, Head, Main, NextScript} from 'next/document'
import React from 'react'
import Script from 'next/script';

type Props = {}

class Document extends NextDocument<Props> {
    render() {
        return (
            <Html>
                <Head/>
                <body>
                <Main/>
                <NextScript/>
                <Script
                    strategy="lazyOnload"
                    src={`https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js?client=${process.env.NEXT_PUBLIC_GOOGLE_ADSENSE}`}
                    crossOrigin="anonymous"/>
                <Script
                    strategy="lazyOnload"
                    async
                    src={`https://www.googletagmanager.com/gtag/js?id=${process.env.NEXT_PUBLIC_GOOGLE_ANALYTICS}`}
                />
                </body>
            </Html>
        )
    }
}

export default Document