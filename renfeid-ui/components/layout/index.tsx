import useSWR from 'swr'
import { Layout, BackTop } from 'antd'
import React, { useState, useEffect } from 'react'
import Navbar from './navbar'
import SectionWrapper from "./section-wrapper"
import Footer from './footer'
import * as api from '../../services/api'
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
        return '下午好，又是奋斗的一天。'
    } else if (h < 19) {
        return '进入傍晚了，不想去散散步吗？'
    } else if (h < 21) {
        return '又到晚上黄金上网时间了，你还等什么？'
    } else if (h < 23) {
        return '夜已深，要注意休息哦。'
    }
    return '欢迎。'
}

const getMessage = () => {
    console.info('定时执行获取消息')
}


const MyLayout = ({ children }: any) => {
    const [greetings, setGreetings] = useState<string>(getGreetings())
    const [userInfo, setUserInfo] = useState<UserInfo>()
    const [intervalMessage, setIntervalMessage] = useState<boolean>(false)
    const [actuve, setActive] = useState<string | undefined>(process.env.NEXT_PUBLIC_RENFEID_ACTIVE)
    const [topBarWrapper, setTopBarWrapper] = useState<string>(
        process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview' ? ('当前是预览模式，仅支持查看，不支持其他操作。') : ('')
    )

    useEffect(() => {
        if (!userInfo) {
            api.requestCurrentUserInfo().then(res => {
                if (res.code == 200) {
                    setUserInfo(res.data)
                } else {
                    setUserInfo({
                        emailVerified: false,
                        id: '-1',
                        phoneVerified: false,
                        registrationDate: "",
                        registrationIp: "",
                        secretLevel: "",
                        username: "",
                        uuid: "",
                        u2fEnable: false,
                    })
                }
            })
        }
        if (!intervalMessage) {
            setInterval(() => {
                getMessage()
            }, 10000)
            setIntervalMessage(true)
        }
    }, [userInfo, intervalMessage])

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
        </>
    )
}

export default MyLayout
