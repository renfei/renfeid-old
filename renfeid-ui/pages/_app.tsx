import 'antd/dist/antd.css'
import '../styles/globals.css'
import React, {ReactNode, useEffect, useState} from 'react'
import {NextPage} from "next"
import Head from 'next/head'
import type {AppProps} from 'next/app'

type Page<P = {}> = NextPage<P> & {
    getLayout?: (page: ReactNode) => ReactNode
}

type Props = AppProps & {
    Component: Page
}

const App = ({Component, pageProps}: Props) => {
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
    }, [consoleLog])
    return getLayout(
        <>
            <Head>
                <meta
                    name="viewport"
                    content="width=device-width, initial-scale=1, shrink-to-fit=no"
                />
            </Head>
            <Component {...pageProps} />
        </>
    )
}

export default App
