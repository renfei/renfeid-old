import {Layout, Divider, Space, Col, Row, Button, Typography, Card, Comment, List, Tooltip} from 'antd';
import React from 'react';

const {Title} = Typography;
const {Footer} = Layout;
const footMenuList = [
    {
        title: '社会化互动',
        links: [
            {
                title: '开发者交流QQ群',
                link: 'https://shang.qq.com/wpa/qunwpa?idkey=bfbde7e5dec79fd3cdb23c7cf590ca698e3da8b48a71369139ed6aa52f9a7513',
                newWindow: true,
                nofollow: true,
            },
            {
                title: 'Github',
                link: 'https://github.com/renfei',
                newWindow: true,
                nofollow: true,
            },
            {
                title: 'Gitlab',
                link: 'https://gitlab.com/renfei',
                newWindow: true,
                nofollow: true,
            },
            {
                title: 'Gitlab-JH',
                link: 'https://jihulab.com/renfei',
                newWindow: true,
                nofollow: true,
            },
        ]
    },
    {
        title: '公益与捐赠',
        links: [
            {
                title: 'Github Sponsors',
                link: 'https://github.com/renfei?tab=sponsoring',
                newWindow: true,
                nofollow: true,
            },
            {
                title: '中国小动物保护协会',
                link: 'http://www.csapa.org',
                newWindow: true,
                nofollow: true,
            },
            {
                title: '联合国儿童基金会',
                link: 'https://www.unicef.org/zh',
                newWindow: true,
                nofollow: true,
            },
        ]
    },
    {
        title: '推荐厂商',
        links: [
            {
                title: '阿里云计算',
                link: 'https://promotion.aliyun.com/ntms/yunparter/invite.html?userCode=mqe0f1u0',
                newWindow: true,
                nofollow: true,
            },
            {
                title: '腾讯云计算',
                link: 'https://cloud.tencent.com/redirect.php?redirect=1005&cps_key=1e6697c30d61f2919ab51bce34ffd8dc',
                newWindow: true,
                nofollow: true,
            },
            {
                title: '东路互联虚拟主机',
                link: 'https://www.donglu.net/user/aff.php?aff=114',
                newWindow: true,
                nofollow: true,
            },
            {
                title: '硅云-云计算',
                link: 'https://www.vpsor.cn/aff?affid=58227',
                newWindow: true,
                nofollow: true,
            },
        ]
    },
    {
        title: '其他内容',
        links: [
            {
                title: '关于任霏博客',
                link: '/about',
                newWindow: false,
                nofollow: false,
            },
            {
                title: '隐私与 Cookie',
                link: '/page/1',
                newWindow: false,
                nofollow: false,
            },
            {
                title: '使用条款',
                link: '/page/2',
                newWindow: false,
                nofollow: false,
            },
        ]
    },
]

const footLinks = [
    {
        title: '冀公网安备 13070902000275号',
        link: 'http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=13070902000275',
        newWindow: true,
        nofollow: true,
    },
    {
        title: '冀ICP备12003293号-9',
        link: 'https://beian.miit.gov.cn',
        newWindow: true,
        nofollow: true,
    },
    {
        title: '任霏',
        link: 'https://www.renfei.net',
        newWindow: false,
        nofollow: false,
    },
    {
        title: '使用条款',
        link: '/page/2',
        newWindow: false,
        nofollow: false,
    },
    {
        title: '隐私与 Cookie',
        link: '/page/1',
        newWindow: false,
        nofollow: false,
    },
    {
        title: 'Status',
        link: 'https://status.renfei.net',
        newWindow: false,
        nofollow: false,
    },
]

const MyFooter = (props: any) => {
    return (
        <>
            <Footer style={{padding: '60px 0 40px 0'}}>
                <div className={"renfeid-content"}>
                    <Row>
                        {
                            footMenuList.length > 0 ? (
                                footMenuList.map(lists => (
                                    <Col key={lists.title} sm={6} xs={24} className={"footer-menu"}>
                                        <Title level={5} className={"footer-menu-title"}>{lists.title}</Title>
                                        <ul>
                                            {
                                                lists.links.length > 0 ? (
                                                    lists.links.map(link => (
                                                        <li key={link.title}>
                                                            <a
                                                                href={link.link}
                                                                target={link.newWindow ? '_blank' : '_self'}
                                                                rel={link.nofollow ? 'nofollow noopener' : ''}
                                                            >
                                                                {link.title}
                                                            </a>
                                                        </li>
                                                    ))
                                                ) : ''
                                            }
                                        </ul>
                                    </Col>
                                ))
                            ) : ''
                        }
                    </Row>
                    <div style={{textAlign: 'right', fontSize: '11px', color: '#6c757d!important'}}>
                        <Space>
                            {
                                footLinks.length > 0 ? (
                                    footLinks.map(link => (
                                        <a
                                            key={link.title}
                                            href={link.link}
                                            target={link.newWindow ? '_blank' : '_self'}
                                            rel={link.nofollow ? 'nofollow noopener' : ''}
                                        >
                                            {link.title}
                                        </a>
                                    ))
                                ) : ''
                            }
                        </Space>
                    </div>
                    <Divider style={{margin: '7px 0'}}/>
                    <div style={{fontSize: '11px', color: '#6c757d!important'}}>
                        <div style={{float: 'left'}}>
                            Copyright © {new Date().getFullYear()} RENFEI.NET All rights reserved.
                        </div>
                        <div style={{float: 'right'}}>
                        </div>
                    </div>
                </div>
            </Footer>
        </>
    )
}

export default MyFooter
