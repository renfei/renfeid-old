import Head from 'next/head'
import React, {useEffect, useState} from 'react'
import type {DataNode} from 'antd/es/tree'
import {Col, Row, Button, Typography, Modal, Space, Form, Input, Select, Tree, Switch} from 'antd'
import {
    PlusOutlined,
    SaveOutlined,
    DeleteOutlined,
    QuestionCircleOutlined
} from '@ant-design/icons'
import DashboardLayout from "../../../components/layout/dashboard"
import DashPageHeader from "../../../components/layout/dashboard/DashPageHeader"

const {Title} = Typography
const {Option} = Select
const {TextArea} = Input

const routes = [
    {
        path: '/dashboard',
        breadcrumbName: '控制面板',
    },
    {
        path: 'javascript:void(0)',
        breadcrumbName: '用户账户与认证',
    },
    {
        path: '/dashboard/uaa/menu',
        breadcrumbName: '菜单管理',
    },
]

const treeData: DataNode[] = [
    {
        title: 'parent 1',
        key: '0-0',
        children: [
            {
                title: 'parent 1-0',
                key: '0-0-0',
                children: [
                    {title: 'leaf', key: '0-0-0-0'},
                    {
                        title: 'adsfasdf',
                        key: '0-0-0-1',
                    },
                    {title: 'leaf', key: '0-0-0-2'},
                ],
            },
            {
                title: 'parent 1-1',
                key: '0-0-1',
                children: [{title: 'leaf', key: '0-0-1-0'}],
            },
            {
                title: 'parent 1-2',
                key: '0-0-2',
                children: [
                    {title: 'leaf', key: '0-0-2-0',},
                    {
                        title: 'leaf',
                        key: '0-0-2-1',
                    },
                ],
            },
        ],
    },
    {
        title: 'parent 2',
        key: '0-1',
        children: [
            {
                title: 'parent 2-0',
                key: '0-1-0',
                children: [
                    {title: 'leaf', key: '0-1-0-0',},
                    {title: 'leaf', key: '0-1-0-1',},
                ],
            },
        ],
    },
]

const helpModal = () => {
    Modal.info({
        title: '帮助说明',
        width: 520,
        icon: (<QuestionCircleOutlined/>),
        content: (
            <div>
                <p>此处菜单为后台管理系统的菜单项，并不是前台的网站菜单。</p>
                <p>创建新菜单：</p>
                <ol>
                    <li>点击右上角的「创建菜单」按钮清空数据表单</li>
                    <li>填写必须的数据</li>
                    <li>点击右下角的保存按钮</li>
                </ol>
                <p>编辑修改菜单：</p>
                <ol>
                    <li>点击左侧菜单树中要修改的菜单，将自动填充表单数据</li>
                    <li>修改表单中数据</li>
                    <li>点击右下角的保存按钮</li>
                </ol>
                <p>删除菜单：</p>
                <ol>
                    <li>点击左侧菜单树中要修改的菜单，将自动填充表单数据</li>
                    <li>点击右下角的删除按钮</li>
                </ol>
                <p>在此处添加菜单后，需要到「角色管理」中对给角色分配菜单权限，然后将角色分配给用户才能看到新菜单。</p>
            </div>
        ),
        onOk() {
        },
    })
}

const DashboardUaaMenu = () => {
    return (
        <>
            <Head>
                <title>菜单管理 - UAA 用户账户认证管理系统 (User Account and Authentication)</title>
            </Head>

            <div style={{backgroundColor: '#fff'}}>
                <DashPageHeader
                    title="菜单管理"
                    routes={routes}
                    subTitle="UAA 用户账户认证管理系统 (User Account and Authentication)"
                />
            </div>

            <Row style={{padding: '23px'}}>
                <Col xs={12} sm={12} md={12} lg={10} xl={7} xxl={6} style={{padding: '0 12px 0 0'}}>
                    <div style={{backgroundColor: '#fff', padding: '24px'}}>
                        <Tree
                            defaultExpandAll={true}
                            showLine={true}
                            treeData={treeData}
                        />
                    </div>
                </Col>
                <Col xs={12} sm={12} md={12} lg={14} xl={17} xxl={18} style={{padding: '0 0 0 12px'}}>
                    <div style={{backgroundColor: '#fff', padding: '24px'}}>
                        <Row>
                            <Col span={12}>
                                <Title level={5}>菜单详细数据</Title>
                            </Col>
                            <Col span={12} style={{textAlign: 'right', paddingBottom: '10px'}}>
                                <Space>
                                    <Button
                                        onClick={helpModal}
                                        icon={<QuestionCircleOutlined/>}>
                                        帮助说明
                                    </Button>
                                    <Button
                                        type="primary"
                                        icon={<PlusOutlined/>}>
                                        创建菜单
                                    </Button>
                                </Space>
                            </Col>
                        </Row>
                        <Form
                            name="basic"
                            autoComplete="off"
                        >
                            <Row>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{padding: '0 5px'}}>
                                    <Form.Item
                                        label="父级菜单"
                                        name="id"
                                    >
                                        <Select defaultValue="2">
                                            <Option value="1">sdfgdfg</Option>
                                            <Option value="2">dghdsfg</Option>
                                        </Select>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{padding: '0 5px'}}>
                                    <Form.Item
                                        label="菜单名称"
                                        name="id"
                                    >
                                        <Input placeholder="菜单显示的名称"/>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{padding: '0 5px'}}>
                                    <Form.Item
                                        label="菜单标题"
                                        name="id"
                                    >
                                        <Input placeholder="鼠标悬浮在菜单上显示的内容"/>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{padding: '0 5px'}}>
                                    <Form.Item
                                        label="菜单图标"
                                        name="id"
                                    >
                                        <Input placeholder="菜单的图标"/>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{padding: '0 5px'}}>
                                    <Form.Item
                                        label="菜单目标"
                                        name="id"
                                    >
                                        <Input placeholder="菜单打开方式"/>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{padding: '0 5px'}}>
                                    <Form.Item
                                        label="样式类名"
                                        name="id"
                                    >
                                        <Input placeholder="class 样式表类名"/>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{padding: '0 5px'}}>
                                    <Form.Item
                                        label="点击事件"
                                        name="id"
                                    >
                                        <Input placeholder="onClick 点击执行的函数"/>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{padding: '0 5px'}}>
                                    <Form.Item
                                        label="菜单排序"
                                        name="id"
                                    >
                                        <Input placeholder="菜单排序基数（升序）"/>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{padding: '0 5px'}}>
                                    <Form.Item
                                        label="菜单链接"
                                        name="id"
                                    >
                                        <Input placeholder="href 目标地址"/>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{padding: '0 5px'}}>
                                    <Form.Item
                                        label="菜单样式"
                                        name="id"
                                    >
                                        <Input placeholder="css 样式代码"/>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{padding: '0 5px'}}>
                                    <Form.Item
                                        label="扩展预留"
                                        name="id"
                                    >
                                        <TextArea rows={1} placeholder="预留字段"/>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{padding: '0 5px'}}>
                                    <Form.Item
                                        label="是否启用"
                                        name="enable"
                                    >
                                        <Switch defaultChecked/>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{padding: '0 5px'}}>
                                    <Form.Item
                                        label="菜单编号"
                                        name="id"
                                    >
                                        <Input readOnly disabled placeholder="系统生成无需填写"/>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{padding: '0 5px'}}>
                                    <Form.Item
                                        label="添加时间"
                                        name="id"
                                    >
                                        <Input readOnly disabled placeholder="系统生成无需填写"/>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{padding: '0 5px'}}>
                                    <Form.Item
                                        label="修改时间"
                                        name="id"
                                    >
                                        <Input readOnly disabled placeholder="系统生成无需填写"/>
                                    </Form.Item>
                                </Col>
                            </Row>
                        </Form>
                        <Row>
                            <Col span={24} style={{textAlign: 'right', paddingTop: '10px'}}>
                                <Space>
                                    <Button
                                        danger
                                        icon={<DeleteOutlined/>}>
                                        删除
                                    </Button>
                                    <Button
                                        type="primary"
                                        icon={<SaveOutlined/>}>
                                        保存
                                    </Button>
                                </Space>
                            </Col>
                        </Row>
                    </div>
                </Col>
            </Row>
        </>
    )
}

DashboardUaaMenu.getLayout = (page: any) => {
    return (
        <DashboardLayout>
            {page}
        </DashboardLayout>
    )
}

export default DashboardUaaMenu