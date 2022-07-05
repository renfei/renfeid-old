import 'antd/dist/antd.css'
import '../styles/globals.css'
import {ReactNode} from 'react';
import {NextPage} from "next";
import Head from 'next/head'
import type {AppProps} from 'next/app'

type Page<P = {}> = NextPage<P> & {
    getLayout?: (page: ReactNode) => ReactNode;
};

type Props = AppProps & {
    Component: Page;
};

const App = ({Component, pageProps}: Props) => {
    const getLayout = Component.getLayout ?? ((page: ReactNode) => page)
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
