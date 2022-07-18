import Link from 'next/link'
import {Layout, Menu, Space, Dropdown, Button, Badge} from 'antd'
import {LogoutOutlined, SettingOutlined, BellOutlined, UserOutlined} from '@ant-design/icons'
import styles from '../../../styles/dashboard/Dashboard.module.css'
import DashboardLogo from './DashboardLogo'

const {Header} = Layout

const DashboardNavbar = () => {
    const userMenu = (
        <Menu
            items={[
                {
                    label: (
                        <>
                            <SettingOutlined/>
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
                            <LogoutOutlined/>
                            <Link href={"/auth/signOut"}>
                                <a>
                                    退出登录
                                </a>
                            </Link>
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
                <DashboardLogo/>
                <div style={{flex: '1 1 0%'}}></div>
                <Space size={'large'}>
                    <Dropdown overlay={noticeMenu}>
                        <Badge
                            count={1}
                            size="small"
                            offset={[0, 10]}
                        >
                            <Button
                                type="text"
                                style={{color: '#ffffff'}}
                                icon={<BellOutlined/>}
                            ></Button>
                        </Badge>
                    </Dropdown>
                    <Dropdown overlay={userMenu}>
                        <Button
                            type="text"
                            icon={<UserOutlined/>}
                            style={{color: '#ffffff'}}
                            onClick={e => e.preventDefault()}>
                            renfei
                        </Button>
                    </Dropdown>
                </Space>
            </Header>
        </>
    )
}

export default DashboardNavbar