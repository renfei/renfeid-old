import useSWR from 'swr'
import { Layout, BackTop } from 'antd'
import React, { useState } from 'react'
import Navbar from './navbar'
import SectionWrapper from "./section-wrapper"
import Footer from './footer'
import Script from 'next/script'
import UserInfo = API.UserInfo

const { Content } = Layout

const getGreetings = () => {
    let h = new Date().getHours()
    if (h < 4) {
        return '深夜了，注意身体哦...'
    } else if (h < 7) {
        return '清晨好，起得真早啊...'
    } else if (h < 11) {
        return '上午好，今天真是美好的一天。'
    } else if (h < 12) {
        return '午饭时间，别太为难自己的肚子哦！'
    } else if (h < 12) {
        return '诶嘿嘿，是时候午休咯！'
    } else if (h < 18) {
        return '进入傍晚了，不想去散散步吗？'
    } else if (h < 19) {
        return '我大概在吃晚饭了，你呢？'
    } else if (h < 21) {
        return '又到晚上黄金上网时间了，你还等什么？'
    } else if (h < 23) {
        return '夜已深，要注意休息哦。'
    }
    return '欢迎。'
}


const MyLayout = ({ children }: any) => {
    const [greetings, setGreetings] = useState<string>(getGreetings())
    const [actuve, setActive] = useState<string | undefined>(process.env.NEXT_PUBLIC_RENFEID_ACTIVE)
    const [topBarWrapper, setTopBarWrapper] = useState<string>(
        process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview' ? ('当前是预览模式，仅支持查看，不支持其他操作。') : ('')
    )
    const { data, error } = useSWR('/api/auth/current/user', (...args) => fetch(...args).then((res) => res.json()))
    let userInfo: UserInfo = {
        emailVerified: false,
        id: '-1',
        phoneVerified: false,
        registrationDate: "",
        registrationIp: "",
        secretLevel: "",
        username: "",
        uuid: ""
    }
    if (data && data.code && data.code == 200 && data.data) {
        userInfo = data.data
    }
    return (
        <>
            <Layout>
                <Navbar>
                    {topBarWrapper}
                </Navbar>
                <SectionWrapper
                    userInfo={userInfo}
                >
                    {greetings}
                </SectionWrapper>
                <Content>{children}</Content>
                <Footer />
            </Layout>
            <BackTop />
            <Script id="google-analytics" strategy="lazyOnload">
                {`
                    window.dataLayer = window.dataLayer || []
                    function gtag(){dataLayer.push(arguments)}
                    gtag('js', new Date())
                    gtag('config', '${process.env.NEXT_PUBLIC_GOOGLE_ANALYTICS}', {
                      page_path: window.location.pathname,
                    })
                `}
            </Script>
            <Script id="baidu-tongji" strategy="lazyOnload">
                {`
                    var _hmt = _hmt || []
                    (function() {
                      var hm = document.createElement("script")
                      hm.src = "https://hm.baidu.com/hm.js?${process.env.NEXT_PUBLIC_BAIDU_TONGJI}"
                      var s = document.getElementsByTagName("script")[0] 
                      s.parentNode.insertBefore(hm, s)
                    })()
                `}
            </Script>
        </>
    )
}

export default MyLayout
