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
                newWindow: false,
            },
            {
                title: 'Github',
                link: 'https://github.com/renfei',
                newWindow: false,
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

const MyFooter = () => {
    return (
        <>
            <Footer>
                <div className={"renfeid-content"}>
                    <Row>
                        {
                            footMenuList.length > 0 ? (
                                footMenuList.map(lists => (
                                    <Col key={lists.title} sm={6} xs={24}>
                                        <Title level={5}>{lists.title}</Title>
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
                                            rel={link.nofollow ? 'noreferrer nofollow noopener' : ''}
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
