import Link from 'next/link'
import { useRouter } from 'next/router'
import { Divider, Dropdown, Button, Menu } from 'antd'
import { LogoutOutlined, SettingOutlined, LockOutlined, UserOutlined } from '@ant-design/icons'
import UserInfo = API.UserInfo

const SectionWrapper = ({ children, ...props }: any) => {
    const userInfo: UserInfo = props.userInfo
    const router = useRouter()
    let path = router.asPath
    if (path.startsWith('/auth/signIn')) {
        path = ''
    } else {
        path = '?redirect=' + router.asPath
    }

    const userMenu = (
        <Menu
            items={[
                {
                    label: (
                        <>
                            <SettingOutlined />
                            <Link href={"/account/manage"}>
                                个人设置
                            </Link>
                        </>
                    ),
                    key: 'manage',
                },
                {
                    label: (
                        <>
                            <LockOutlined />
                            <Link href={"/account/manage/password"}>
                                修改密码
                            </Link>
                        </>
                    ),
                    key: 'password',
                },
                {
                    type: 'divider',
                },
                {
                    label: (
                        <>
                            <LogoutOutlined />
                            <Link href={"/auth/signOut"}>
                                退出登录
                            </Link>
                        </>
                    ),
                    key: 'signOut',
                },
            ]}
        />
    )

    return (
        <>
            {
                children && (
                    <div className={"section-wrapper"}>
                        <div className={"renfeid-content"}>
                            <div style={{ float: 'left' }}>
                                {children}
                            </div>
                            <div style={{ float: 'right' }}>
                                {
                                    userInfo && userInfo.username ? (
                                        <>
                                            <Dropdown overlay={userMenu}>
                                                <Button
                                                    type="text"
                                                    icon={<UserOutlined />}
                                                    onClick={e => e.preventDefault()}>
                                                    {userInfo.username}
                                                </Button>
                                            </Dropdown>
                                            <Divider type={'vertical'} />
                                            <Link
                                                href={'/auth/signOut'}
                                            >
                                                登出
                                            </Link>
                                        </>
                                    ) : (
                                        <>
                                            <Link
                                                href={'/auth/signIn' + path}
                                            >
                                                登录
                                            </Link>
                                            <Divider type={'vertical'} />
                                            <Link
                                                href={'/auth/signUp'}
                                            >
                                                注册
                                            </Link>
                                        </>
                                    )
                                }
                            </div>
                        </div>
                    </div>
                )
            }
        </>
    )
}

export default SectionWrapper
