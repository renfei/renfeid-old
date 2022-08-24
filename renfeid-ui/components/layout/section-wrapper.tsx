import Link from 'next/link'
import { useRouter } from 'next/router'
import { Divider } from 'antd'
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
                                            <Link
                                                href={'/account/manage'}
                                            >
                                                <a>
                                                    {userInfo.username}
                                                </a>
                                            </Link>
                                            <Divider type={'vertical'} />
                                            <Link
                                                href={'/auth/signOut'}
                                            >
                                                <a>
                                                    登出
                                                </a>
                                            </Link>
                                        </>
                                    ) : (
                                        <>
                                            <Link
                                                href={'/auth/signIn' + path}
                                            >
                                                <a>
                                                    登录
                                                </a>
                                            </Link>
                                            <Divider type={'vertical'} />
                                            <Link
                                                href={'/auth/signUp'}
                                            >
                                                <a>
                                                    注册
                                                </a>
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
