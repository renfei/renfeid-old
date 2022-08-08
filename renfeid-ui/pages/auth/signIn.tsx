import Head from 'next/head'
import Link from 'next/link'
import Image from 'next/image'
import { GetServerSideProps } from 'next'
import { parseCookies, setCookie } from 'nookies'
import { useRouter } from 'next/router'
import React, { useEffect, useState } from 'react'
import type { NextApiRequest, NextApiResponse } from 'next'
import { Button, Col, Form, Input, message, Row, Space, Typography } from 'antd'
import Layout from '../../components/layout'
import styles from '../../styles/auth/SignIn.module.css'
import { KeyOutlined, LockOutlined, UserOutlined, } from '@ant-design/icons'
import { encrypt } from "../../utils/encryption"
import SignInAo = API.SignInAo
import * as api from "../../services/api"
import CheckSignInStatus from '../../utils/CheckSignInStatus'
import UserInfo = API.UserInfo
import SignInVo = API.SignInVo
import APIResult = API.APIResult

const { Title } = Typography

let redirectPath: string = '/'

const signInSubmit = async (req: NextApiRequest, res: NextApiResponse, values: SignInAo): Promise<string> => {
    values.useVerCode = false
    if (typeof window !== 'undefined') {
        await encrypt(values.plainPassword).then(res => {
            values.keyUuid = res?.secretKey.uuid || ''
            values.password = res?.value || ''
        })
        // 登录
        const signInVo: APIResult<SignInVo> = await api.signIn(values)
        if (signInVo.code == 402) {
            return '402'
        } else if (signInVo.code != 200) {
            if (signInVo.message == "AESKeyId不存在") {
                window.localStorage.removeItem("aesKeyJson")
                return signInSubmit(req, res, values)
            }
            message.error(signInVo.message)
            return signInVo.message
        } else if (!signInVo.data) {
            message.error('数据异常，请重试')
            return signInVo.message
        } else {
            setCookie(null, 'userName', values.userName.trim().toLowerCase(), {
                domain: '.renfei.net',
                maxAge: 8 * 60 * 60,
                path: '/',
            })
            if (signInVo.data?.ucScript) {
                // TODO 登录 Discuz!
            }
        }
        gotoRedirect(redirectPath)
        return ""
    } else {
        message.error("非浏览器运行环境，程序被终止")
        return "非浏览器运行环境，程序被终止"
    }
}

const gotoRedirect = (redirect: string | string[] | undefined) => {
    if (typeof window !== 'undefined') {
        if (redirect && typeof redirect == 'string') {
            window.location.replace(redirect)
        } else {
            window.location.replace('/')
        }
    }
}

export const getServerSideProps: GetServerSideProps = async (context: any) => {
    const userInfo: UserInfo | undefined = await CheckSignInStatus(context)
    if (userInfo) {
        if (context.query.redirect) {
            return {
                redirect: {
                    destination: context.query.redirect,
                    permanent: false,
                },
            }
        } else {
            return {
                redirect: {
                    destination: '/',
                    permanent: false,
                },
            }
        }
    } else {
        return {
            props: {}
        }
    }
}

const SignInPage = (req: NextApiRequest, res: NextApiResponse) => {
    const [form] = Form.useForm()
    const router = useRouter()
    const { redirect } = router.query
    const [showMFA, setShowMFA] = useState<boolean>(false)
    const [loading, setLoading] = useState<boolean>(false)

    if (redirect) {
        redirectPath = redirect.toString()
    }

    useEffect(() => {
        if (typeof window !== 'undefined') {
            const cookies = parseCookies()
            if (cookies['accessToken']) {
                // 存在 Token，已经登录了，直接跳转
                gotoRedirect(redirect)
            }
        }
    })

    return (
        <>
            <div>
                <Head>
                    <title>{`登录 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
                    <meta name="description" content={`登录 - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`} />
                </Head>

                <main style={{ backgroundColor: '#ffffff', padding: '60px 0' }}>
                    <section className={"renfeid-content"}>
                        <div className={styles.signInForm}>
                            <Row>
                                <Col md={15} sm={12} xs={24}
                                    style={{ padding: '20px 20px 20px 0', display: 'flex', justifyContent: 'center' }}>
                                    <Image
                                        src={"/image/hire_re_gn5j.svg"}
                                        alt="SignIn"
                                        width={898.09814}
                                        height={398.74219}
                                    />
                                </Col>
                                <Col md={9} sm={12} xs={24}>
                                    <Title level={3} style={{ fontWeight: '100' }}>欢迎回来！</Title>
                                    <p style={{ color: 'rgba(0,0,0,0.6)', fontSize: '12px' }}>
                                        无论您何时归来，我们都在这里等着您。
                                    </p>
                                    <Form
                                        form={form}
                                        name="SignInForm"
                                        initialValues={{ remember: true }}
                                        autoComplete="off"
                                        onFinish={async (values) => {
                                            setLoading(true)
                                            const rtn = await signInSubmit(req, res, values)
                                            if (rtn == '402') {
                                                setShowMFA(true)
                                            }
                                            setLoading(false)
                                        }}
                                    >
                                        <Form.Item
                                            name="userName"
                                            rules={[{ required: true, message: '请输入您的账号！' }]}
                                        >
                                            <Input
                                                placeholder="账号"
                                                autoComplete='username'
                                                prefix={<UserOutlined />}
                                            />
                                        </Form.Item>

                                        <Form.Item
                                            name="plainPassword"
                                            rules={[{ required: true, message: '请输入您的密码！' }]}
                                        >
                                            <Input.Password
                                                placeholder="密码"
                                                autoComplete='password'
                                                prefix={<LockOutlined />}
                                            />
                                        </Form.Item>

                                        {
                                            showMFA ? (
                                                <Form.Item
                                                    name="tOtp"
                                                    rules={[{ required: false, message: 'Please input your username!' }]}
                                                >
                                                    <Input
                                                        placeholder="MFA"
                                                        autoComplete='one-time-code'
                                                        showCount maxLength={6}
                                                        prefix={<KeyOutlined />}
                                                    />
                                                </Form.Item>
                                            ) : ''
                                        }

                                        <Form.Item>
                                            <Row>
                                                <Col style={{ textAlign: 'right' }}>
                                                    <Link href={"/"}>忘记密码？</Link>
                                                </Col>
                                            </Row>
                                        </Form.Item>

                                        <Form.Item shouldUpdate>
                                            {() => (
                                                <Space>
                                                    <Button
                                                        type="primary"
                                                        shape="round"
                                                        htmlType="submit"
                                                        loading={loading}
                                                        disabled={
                                                            !form.isFieldsTouched(true) ||
                                                            !!form.getFieldsError().filter(({ errors }) => errors.length).length
                                                        }
                                                    >
                                                        立即登录
                                                    </Button>
                                                    <Button shape="round" href="/auth/signUp">
                                                        注册账号
                                                    </Button>
                                                </Space>
                                            )}

                                        </Form.Item>
                                    </Form>
                                </Col>
                            </Row>
                        </div>
                    </section>
                </main>
            </div>
        </>
    )
}

SignInPage.getLayout = (page: any) => {
    return (
        <Layout>
            {page}
        </Layout>
    )
}

export default SignInPage