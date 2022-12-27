import 'antd/dist/antd.css'
import '../styles/globals.css'
import { LogoJsonLd, SiteLinksSearchBoxJsonLd, SocialProfileJsonLd } from 'next-seo'
import React, { ReactNode, useEffect, useState } from 'react'
import { NextPage } from "next"
import type { AppProps } from 'next/app'

type Page<P = {}> = NextPage<P> & {
    getLayout?: (page: ReactNode) => ReactNode
}

type Props = AppProps & {
    Component: Page
}

const getAnalyticsTag = () => {
    return {
        __html: `
        window.dataLayer = window.dataLayer || [];
        function gtag(){dataLayer.push(arguments)}
        gtag('js', new Date());
        gtag('config', '${process.env.NEXT_PUBLIC_GOOGLE_ANALYTICS}', {
          page_path: window.location.pathname,
        });
        `,
    }
}

const App = ({ Component, pageProps }: Props) => {
    const getLayout = Component.getLayout ?? ((page: ReactNode) => page)
    const [consoleLog, setConsoleLog] = useState<number>(0)
    useEffect(() => {
        if (typeof window !== 'undefined') {
            if (consoleLog == 0) {
                console.log("\n %c RENFEI.NET %c 这里你都能找到？你知道的太多了！ %c \n\n", "color: #fff; background: #3274ff; padding:5px 0; border: 1px solid #3274ff;", "color: #3274ff; background: #fff; padding:5px 0; border: 1px solid #3274ff;", "");
                console.log("\n %c 代码已开源： %c github.com/renfei/renfeid %c \n\n", "color: #fff; background: #2da44e; padding:5px 0; border: 1px solid #2da44e;", "color: #2da44e; background: #fff; padding:5px 0; border: 1px solid #2da44e;", "");
                setConsoleLog(1)
            }
        }
        var ads = document.getElementsByClassName("adsbygoogle").length;
        for (var i = 0; i < ads; i++) {
            try {
                // @ts-ignore
                (adsbygoogle = window.adsbygoogle || []).push({});
            } catch (e) { }
        }
    }, [consoleLog])
    return getLayout(
        <>
            <LogoJsonLd
                keyOverride="site-logo"
                logo="https://cdn.renfei.net/Logo/RF.svg"
                url={`${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}`}
            />
            <SiteLinksSearchBoxJsonLd
                keyOverride="site-search"
                url={`${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}`}
                potentialActions={[
                    {
                        target: `${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}/search?w=`,
                        queryInput: 'search_term_string',
                    },
                ]}
            />
            <SocialProfileJsonLd
                keyOverride="site-social"
                type="Person"
                name="任霏"
                url={`${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}`}
                sameAs={[
                    'https://www.facebook.com/renfeii',
                    'https://twitter.com/renfeii',
                ]}
            />
            <Component {...pageProps} />
            <script dangerouslySetInnerHTML={getAnalyticsTag()} />
        </>
    )
}

export default App
