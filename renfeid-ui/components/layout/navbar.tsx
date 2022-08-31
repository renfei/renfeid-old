import Link from 'next/link'
import Image from 'next/image'
import { Affix, List, Form, Input, Typography } from 'antd'
import React, { useState } from 'react'
import TopBarWrapper from "./top-bar-wrapper"
import { SearchOutlined, UnorderedListOutlined, CloseOutlined } from '@ant-design/icons'

const menuList = [
    {
        text: '首页',
        href: '/',
        target: '_self',
    },
    {
        text: '文章',
        href: '/posts',
        target: '_self',
    },
    {
        text: '微博',
        href: '/weibo',
        target: '_self',
    },
    {
        text: '相册',
        href: '/photo',
        target: '_self',
    },
    {
        text: '论坛',
        href: 'https://bbs.renfei.net',
        target: '_blank',
    },
    {
        text: '工具箱',
        href: '/kitbox',
        target: '_self',
    },
    {
        text: '在线文档',
        href: '/docs',
        target: '_self',
    },
    {
        text: '更多',
        href: '/more',
        target: '_self',
    },
]

const Navbar = ({ children, ...props }: any) => {
    const [searchForm] = Form.useForm();
    const [showMobileMenu, setShowMobileMenu] = useState<boolean>(false)
    const [showSearch, setShowSearch] = useState<boolean>(false)

    const clickMobileMenu = () => {
        if (showSearch) {
            clickSearch()
        }
        setShowMobileMenu(!showMobileMenu)
        const wrapper: any = document.querySelector(".nav-menu-mobile-list")
        if (wrapper) {
            if (!showMobileMenu) {
                wrapper.style.height = "100%"
            } else {
                wrapper.style.height = 0
            }
        }
    }

    const clickSearch = () => {
        if (showMobileMenu) {
            clickMobileMenu()
        }
        setShowSearch(!showSearch)
        const wrapper: any = document.querySelector(".nav-menu-search")
        if (wrapper) {
            if (!showSearch) {
                wrapper.style.height = "100%"
            } else {
                wrapper.style.height = 0
            }
        }
    }

    const menuElementClick = () => {
        if (showMobileMenu) {
            clickMobileMenu()
        }
        if (showSearch) {
            clickSearch()
        }
    }

    const menuElement: any[] = []
    menuList.forEach(element => {
        menuElement.push(
            <li key={element.href}>
                <Link href={element.href}>
                    <a target={element.target}>{element.text}</a>
                </Link>
            </li>
        )
    })

    return (
        <>
            <TopBarWrapper>
                {children}
            </TopBarWrapper>
            <Affix offsetTop={0}>
                <div>
                    <nav className={"renfeid-nav"}>
                        <div className={"renfeid-content"}>
                            <ul className={"nav-menu-mobile"}>
                                <li onClick={clickMobileMenu} style={{ cursor: 'pointer' }}>
                                    {
                                        showMobileMenu ? (<CloseOutlined />) : (<UnorderedListOutlined />)
                                    }
                                </li>
                                <li>
                                    <Link href={"/"}>
                                        <a>
                                            <Image
                                                className={"nav-logo"}
                                                src="https://cdn.renfei.net/Logo/RF_white.svg"
                                                alt={"任霏博客"}
                                                height={30}
                                                width={30}
                                            />
                                        </a>
                                    </Link>
                                </li>
                                <li onClick={clickSearch} style={{ cursor: 'pointer' }}><SearchOutlined /></li>
                            </ul>
                            <ul className={"nav-menu"}>
                                <li>
                                    <Link href={"/"}>
                                        <a>
                                            <Image
                                                className={"nav-logo"}
                                                src="https://cdn.renfei.net/Logo/RF_white.svg"
                                                alt={"任霏博客"}
                                                height={30}
                                                width={30}
                                            />
                                        </a>
                                    </Link>
                                </li>
                                {menuElement}
                                <li onClick={clickSearch} style={{ cursor: 'pointer' }}>
                                    <SearchOutlined />
                                </li>
                            </ul>
                        </div>
                    </nav>
                    <nav className={"renfeid-nav nav-menu-mobile-list"} style={{
                        height: '0',
                        width: '100%',
                        transition: 'height 0.5s ease',
                        overflow: 'hidden',
                        position: 'fixed',
                        zIndex: '999999',
                    }}>
                        <div className={"renfeid-content nav-menu-list-content"}>
                            <List
                                dataSource={menuList}
                                renderItem={item => (
                                    <List.Item style={{ color: 'rgba(255, 255, 255, 0.9)', borderBottom: '1px solid rgba(255, 255, 255, 0.5)' }}>
                                        <Link href={item.href}>
                                            <a target={item.target} onClick={menuElementClick}>{item.text}</a>
                                        </Link>
                                    </List.Item>
                                )}
                            />
                        </div>
                    </nav>
                    <nav className={"renfeid-nav nav-menu-search"} style={{
                        height: '0',
                        width: '100%',
                        transition: 'height 0.5s ease',
                        overflow: 'hidden',
                        position: 'fixed',
                        zIndex: '999999',
                    }}>
                        <div className={"renfeid-content nav-menu-search-content"} style={{ maxWidth: '680px' }}>
                            <Form
                                form={searchForm}
                            >
                                <Form.Item                            >
                                    <Input
                                        placeholder="搜索 renfei.net"
                                        prefix={<SearchOutlined className="site-form-item-icon" />}
                                        suffix={
                                            <CloseOutlined onClick={() => {
                                                if (showSearch) {
                                                    clickSearch()
                                                }
                                            }} />
                                        }
                                        onPressEnter={(event) => {
                                            if (event && event.target) {
                                                window.location.href = `/search?w=${(event.target as HTMLInputElement).value}`
                                            }
                                        }}
                                    />
                                </Form.Item>
                            </Form>
                            <Typography.Title level={5} style={{ color: 'rgba(255, 255, 255, 0.95)' }}>搜索热词</Typography.Title>
                            <List
                                dataSource={props.hotSearch}
                                renderItem={(item: string) => (
                                    <List.Item style={{ color: 'rgba(255, 255, 255, 0.9)', borderBottom: '1px solid rgba(255, 255, 255, 0.5)' }}>
                                        <Link href={`/search?w=${item}`}>
                                            <a target={'_blank'}>{item}</a>
                                        </Link>
                                    </List.Item>
                                )}
                            />
                        </div>
                    </nav>
                </div>
            </Affix>
        </>
    )
}

export default Navbar
