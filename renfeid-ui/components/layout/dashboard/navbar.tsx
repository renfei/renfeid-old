import { useRouter } from 'next/router'
import React, { useState, useEffect } from 'react'
import { Layout, Menu, Space, Dropdown, Button, Badge } from 'antd'
import { LogoutOutlined, SettingOutlined, BellOutlined, UserOutlined } from '@ant-design/icons'
import styles from '../../../styles/dashboard/Dashboard.module.css'
import DashboardLogo from './DashboardLogo'
import * as api from '../../../services/api'

const { Header } = Layout

const getMessage = () => {
    console.info('定时执行获取消息')
}

const DashboardNavbar = () => {
    const router = useRouter()
    const [userName, setUserName] = useState<string>('')
    const [intervalMessage, setIntervalMessage] = useState<boolean>(false)

    useEffect(() => {
        api.requestCurrentUserInfo().then(res => {
            if (res.code == 401) {
                router.push('/auth/signIn?redirect=' + router.asPath)
            } else if (res.code == 200 && res.data) {
                setUserName(res.data.username)
            }
        })
        if (!intervalMessage) {
            setInterval(() => {
                getMessage()
            }, 10000)
            setIntervalMessage(true)
        }
    }, [intervalMessage, router])

    const userMenu = (
        <Menu
            items={[
                {
                    label: (
                        <>
                            <SettingOutlined />
                            <a href="">
                                个人设置
                            </a>
                        </>
                    ),
                    key: '0',
                },
                {
                    type: 'divider',
                },
                {
                    label: (
                        <>
                            <LogoutOutlined />
                            <a href={"/auth/signOut"}>
                                退出登录
                            </a>
                        </>
                    ),
                    key: '100',
                },
            ]}
        />
    )

    const noticeMenu = (
        <Menu
            items={[
                {
                    label: (
                        <
                            >
                            <a href="">
                                未读消息
                            </a>
                        </>
                    ),
                    key: '0',
                },
                {
                    label: (
                        <>
                            <a href="">
                                全部消息
                            </a>
                        </>
                    ),
                    key: '100',
                },
            ]}
        />
    )

    return (
        <>
            <Header className={"header " + styles.dashboard_header}>
                <DashboardLogo />
                <div style={{ flex: '1 1 0%' }}></div>
                <Space size={'large'}>
                    <Dropdown overlay={noticeMenu}>
                        <Badge
                            count={1}
                            size="small"
                            offset={[0, 10]}
                        >
                            <Button
                                type="text"
                                style={{ color: '#ffffff' }}
                                icon={<BellOutlined />}
                            ></Button>
                        </Badge>
                    </Dropdown>
                    <Dropdown overlay={userMenu}>
                        <Button
                            type="text"
                            icon={<UserOutlined />}
                            style={{ color: '#ffffff' }}
                            onClick={e => e.preventDefault()}>
                            {userName}
                        </Button>
                    </Dropdown>
                </Space>
            </Header>
        </>
    )
}

export default DashboardNavbar