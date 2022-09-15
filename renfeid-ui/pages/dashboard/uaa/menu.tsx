import Head from 'next/head'
import nookies from 'nookies'
import React, { useEffect, useState } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import type { DataNode } from 'antd/es/tree'
import { Col, Row, Button, Typography, Modal, Space, Form, Input, Select, Tree, Switch, message, Dropdown } from 'antd'
import {
    PlusOutlined,
    SaveOutlined,
    DeleteOutlined,
    QuestionCircleOutlined
} from '@ant-design/icons'
import * as api from "../../../services/api/dashboard/api"
import APIResult = API.APIResult
import MenuTree = API.MenuTree
import AntdSelectOption = API.AntdSelectOption
import { convertToHeaders } from '../../../utils/request'
import DashboardLayout from "../../../components/layout/dashboard"
import DashPageHeader from "../../../components/layout/dashboard/DashPageHeader"

const { Title } = Typography
const { Option } = Select
const { TextArea } = Input

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

const helpModal = () => {
    Modal.info({
        title: '帮助说明',
        width: 520,
        icon: (<QuestionCircleOutlined />),
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
                <p>权限标识：工程师开发的每个接口都有个权限标识，用于识别每个接口，请联系工程师获取对应接口的权限表达式。</p>
                <p>在此处添加菜单后，需要到「角色管理」中对给角色分配菜单权限，然后将角色分配给用户才能看到新菜单。</p>
            </div>
        ),
        onOk() {
        },
    })
}

export const getServerSideProps: GetServerSideProps = async (context: any) => {
    const accessToken = nookies.get(context)['accessToken']
    if (!accessToken) {
        return {
            redirect: {
                destination: '/auth/signIn?redirect=' + context.req.url,
                permanent: false,
            },
        }
    }
    let menuTreeList: MenuTree[] = []
    const resultMenuTree: APIResult<MenuTree[]> = await api.queryAllMenuTreeInner(accessToken, convertToHeaders(context.req.headers, context.req.socket.remoteAddress))
    if (resultMenuTree.code == 401) {
        return {
            redirect: {
                destination: '/auth/signIn?redirect=' + context.req.url,
                permanent: false,
            },
        }
    }
    if (resultMenuTree.code == 200 && resultMenuTree.data) {
        menuTreeList = resultMenuTree.data
    }
    return {
        props: {
            data: {
                menuTreeList: menuTreeList
            }
        }
    }
}

const DashboardUaaMenu = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
    const [form] = Form.useForm()
    const [menuTreeList, setMenuTreeList] = useState<MenuTree[]>(data.menuTreeList)
    const [menuDataNode, setTreeDataNode] = useState<DataNode[]>(convertToDataNode(data.menuTreeList))
    const [currentId, setCurrentId] = useState<string>()
    const [pid, setPid] = useState<string>('-1')
    const [pidName, setPidName] = useState<string>("顶级菜单")
    const [enable, setEnable] = useState<boolean>(true)

    const onSelectTree = (selectedKeys: any[], e: any) => {
        if (selectedKeys.length > 0) {
            setCurrentId(selectedKeys[0])
            let slectMenuTree: MenuTree | undefined = findMenuData(selectedKeys[0], menuTreeList)
            if (slectMenuTree) {
                let pidMenu: MenuTree | undefined = findMenuData(slectMenuTree.pid + "", menuTreeList)
                form.setFieldsValue({
                    id: slectMenuTree.id,
                    menuName: slectMenuTree.menuName,
                    menuType: slectMenuTree.menuType,
                    permissionExpr: slectMenuTree.permissionExpr,
                    menuHref: slectMenuTree.menuHref,
                    menuIcon: slectMenuTree.menuIcon,
                    menuTarget: slectMenuTree.menuTarget,
                    menuClass: slectMenuTree.menuClass,
                    menuTitle: slectMenuTree.menuTitle,
                    menuOnclick: slectMenuTree.menuOnclick,
                    menuOrder: slectMenuTree.menuOrder,
                    addTime: slectMenuTree.addTime,
                    updateTime: slectMenuTree.updateTime,
                    menuCss: slectMenuTree.menuCss,
                    extendJson: slectMenuTree.extendJson,
                })
                setPid(slectMenuTree.pid + "")
                if (pidMenu) {
                    setPidName(pidMenu.menuName)
                } else {
                    setPidName("顶级菜单")
                }
                setEnable(slectMenuTree.enable + "" == "true")
            } else {
                message.error(`遇到错误，未找到该菜单:${selectedKeys[0]}`)
            }
        }
    }

    const onSelectPid = (selectedKeys: any[], e: any) => {
        if (selectedKeys.length > 0) {
            setCurrentId(selectedKeys[0])
            let slectMenuTree: MenuTree | undefined = findMenuData(selectedKeys[0], menuTreeList)
            if (slectMenuTree) {
                setPid(slectMenuTree.id)
                setPidName(slectMenuTree.menuName)
            } else {
                message.error(`遇到错误，未找到该菜单:${selectedKeys[0]}`)
            }
        }
    }

    const reloadTree = async () => {
        setPid("-1")
        setPidName("顶级菜单")
        const resultMenuTree: APIResult<MenuTree[]> = await api.queryAllMenuTree()
        if (resultMenuTree.code == 200 && resultMenuTree.data) {
            form.resetFields()
            setMenuTreeList(resultMenuTree.data)
            setTreeDataNode(convertToDataNode(resultMenuTree.data))
        } else {
            message.error(resultMenuTree.message)
        }
    }

    const save = async (values: any) => {
        if (!values.menuName) {
            message.error('请填写菜单名称')
            return false
        }
        if (!values.menuHref) {
            message.error('请填写菜单链接')
            return false
        }
        if (values.id && values.id == values.pid) {
            message.error('不能选择当前菜单自己为父级菜单')
            return false
        }
        if (values.pid == '-1') {
            values.pid = undefined
        }
        let menu: MenuTree = {
            id: values.id,
            menuName: values.menuName,
            menuType: values.menuType,
            permissionExpr: values.permissionExpr,
            menuHref: values.menuHref,
            pid: pid,
            menuIcon: values.menuIcon,
            menuTarget: values.menuTarget,
            menuClass: values.menuClass,
            menuTitle: values.menuTitle,
            menuOnclick: values.menuOnclick,
            menuOrder: values.menuOrder,
            enable: enable + "",
            addTime: values.addTime,
            updateTime: values.updateTime,
            menuCss: values.menuCss,
            extendJson: values.extendJson,
        }
        if (values.id) {
            let apiresult: APIResult<any> = await api.updateMenu(menu)
            if (apiresult.code != 200) {
                message.error(apiresult.message)
            } else {
                reloadTree()
            }
        } else {
            let apiresult: APIResult<any> = await api.createMenu(menu)
            if (apiresult.code != 200) {
                message.error(apiresult.message)
            } else {
                reloadTree()
            }
        }
    }

    return (
        <>
            <Head>
                <title>菜单管理 - UAA 用户账户认证管理系统 (User Account and Authentication)</title>
            </Head>

            <div style={{ backgroundColor: '#fff' }}>
                <DashPageHeader
                    title="菜单管理"
                    routes={routes}
                    subTitle="UAA 用户账户认证管理系统 (User Account and Authentication)"
                />
            </div>

            <Row style={{ padding: '23px' }}>
                <Col xs={12} sm={12} md={12} lg={10} xl={7} xxl={6} style={{ padding: '0 12px 0 0' }}>
                    <div style={{ backgroundColor: '#fff', padding: '24px' }}>
                        <Tree
                            defaultExpandAll={true}
                            showLine={{ showLeafIcon: false }}
                            showIcon={false}
                            treeData={menuDataNode}
                            onSelect={onSelectTree}
                        />
                    </div>
                </Col>
                <Col xs={12} sm={12} md={12} lg={14} xl={17} xxl={18} style={{ padding: '0 0 0 12px' }}>
                    <div style={{ backgroundColor: '#fff', padding: '24px' }}>
                        <Row>
                            <Col span={12}>
                                <Title level={5}>菜单详细数据</Title>
                            </Col>
                            <Col span={12} style={{ textAlign: 'right', paddingBottom: '10px' }}>
                                <Space>
                                    <Button
                                        onClick={helpModal}
                                        icon={<QuestionCircleOutlined />}>
                                        帮助说明
                                    </Button>
                                    <Button
                                        onClick={() => {
                                            form.resetFields()
                                            setPid("-1")
                                            setPidName("顶级菜单")
                                        }}
                                        type="primary"
                                        icon={<PlusOutlined />}>
                                        创建菜单
                                    </Button>
                                </Space>
                            </Col>
                        </Row>
                        <Form
                            form={form}
                            onFinish={save}
                            name="basic"
                            autoComplete="off"
                        >
                            <Row>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="父级菜单"
                                        name="pid"
                                    >
                                        <Dropdown
                                            overlay={(
                                                <Tree
                                                    defaultExpandAll={true}
                                                    showLine={{ showLeafIcon: false }}
                                                    treeData={menuDataNode}
                                                    onSelect={onSelectPid}
                                                />
                                            )}
                                        >
                                            <a>
                                                {`${pidName}`}
                                            </a>
                                        </Dropdown>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="菜单名称"
                                        name="menuName"
                                    >
                                        <Input placeholder="菜单显示的名称" />
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="权限类型"
                                        name="menuType"
                                    >
                                        <Select defaultValue={"MENU"}>
                                            <Select.Option value="MENU">菜单</Select.Option>
                                            <Select.Option value="BUTTON">按钮</Select.Option>
                                            <Select.Option value="API">接口</Select.Option>
                                        </Select>
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="权限标识"
                                        name="permissionExpr"
                                    >
                                        <Input placeholder="权限表达式请咨询工程师" />
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="菜单标题"
                                        name="menuTitle"
                                    >
                                        <Input placeholder="鼠标悬浮在菜单上显示的内容" />
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="菜单图标"
                                        name="menuIcon"
                                    >
                                        <Input placeholder="菜单的图标" />
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="菜单目标"
                                        name="menuTarget"
                                    >
                                        <Input placeholder="菜单打开方式" />
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="样式类名"
                                        name="menuClass"
                                    >
                                        <Input placeholder="class 样式表类名" />
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="点击事件"
                                        name="menuOnclick"
                                    >
                                        <Input placeholder="onClick 点击执行的函数" />
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="菜单排序"
                                        name="menuOrder"
                                    >
                                        <Input placeholder="菜单排序基数（升序）" />
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="菜单链接"
                                        name="menuHref"
                                    >
                                        <Input placeholder="href 目标地址" />
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="菜单样式"
                                        name="menuCss"
                                    >
                                        <Input placeholder="css 样式代码" />
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="扩展预留"
                                        name="extendJson"
                                    >
                                        <TextArea rows={1} placeholder="预留字段" />
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="是否启用"
                                    >
                                        <Switch checked={enable} onChange={(checked: boolean, event: any) => {
                                            setEnable(checked)
                                        }} />
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="菜单编号"
                                        name="id"
                                    >
                                        <Input readOnly disabled placeholder="系统生成无需填写" />
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="添加时间"
                                        name="addTime"
                                    >
                                        <Input readOnly disabled placeholder="系统生成无需填写" />
                                    </Form.Item>
                                </Col>
                                <Col xs={24} sm={24} md={24} lg={12} xl={8} xxl={6} style={{ padding: '0 5px' }}>
                                    <Form.Item
                                        label="修改时间"
                                        name="updateTime"
                                    >
                                        <Input readOnly disabled placeholder="系统生成无需填写" />
                                    </Form.Item>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={24} style={{ textAlign: 'right', paddingTop: '10px' }}>
                                    <Space>
                                        <Button
                                            danger
                                            disabled={currentId == undefined}
                                            onClick={async () => {
                                                if (currentId) {
                                                    if (confirm('确认删除吗')) {
                                                        let apiresult: APIResult<any> = await api.deleteMenu(currentId)
                                                        if (apiresult.code != 200) {
                                                            message.error(apiresult.message)
                                                        } else {
                                                            reloadTree()
                                                        }
                                                    }
                                                } else {
                                                    message.error('请先点击左侧树中要编辑的菜单')
                                                }
                                            }}
                                            icon={<DeleteOutlined />}>
                                            删除
                                        </Button>
                                        <Button
                                            type="primary"
                                            htmlType="submit"
                                            icon={<SaveOutlined />}>
                                            保存
                                        </Button>
                                    </Space>
                                </Col>
                            </Row>
                        </Form>
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

// 递归转换树
const convertToDataNode = (menuTreeList: MenuTree[]): DataNode[] => {
    if (menuTreeList) {
        let dataNodeList: DataNode[] = []
        menuTreeList.forEach(element => {
            let childrenList: DataNode[] | undefined = undefined
            if (element.child) {
                childrenList = convertToDataNode(element.child)
            }
            let dataNode: DataNode = {
                key: element.id,
                title: element.menuName,
                children: childrenList
            }
            dataNodeList.push(dataNode)
        })
        return dataNodeList
    }
    return []
}

// 递归转换列表
const convertToList = (menuTreeList: MenuTree[]): MenuTree[] => {
    if (menuTreeList) {
        let dataNodeList: MenuTree[] = []
        menuTreeList.forEach(element => {
            dataNodeList.push(element)
            if (element.child) {
                convertToList(element.child).forEach(child => {
                    dataNodeList.push(child)
                })
            }
        })
        return dataNodeList
    }
    return []
}

// 递归树查找数据
const findMenuData = (id: string, menuTreeList: MenuTree[] | undefined): MenuTree | undefined => {
    if (menuTreeList) {
        for (let i = 0; i < menuTreeList.length; i++) {
            if (menuTreeList[i].id == id) {
                return menuTreeList[i]
            } else if (menuTreeList[i].child) {
                let child: MenuTree | undefined = findMenuData(id, menuTreeList[i].child)
                if (child) {
                    return child
                }
            }
        }
    }
    return undefined
}

export default DashboardUaaMenu