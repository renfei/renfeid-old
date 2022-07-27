import Head from 'next/head'
import moment from 'moment'
import React, { useEffect, useState } from 'react'
import { Col, Row, Button, Typography, Table, Space, Form, Input, Select, Modal, Tag, message } from 'antd'
import {
    DownOutlined,
    UpOutlined,
    ContactsOutlined,
    SearchOutlined,
    ReloadOutlined,
    SafetyOutlined,
    PoweroffOutlined,
    UnlockOutlined,
    KeyOutlined,
    QuestionCircleOutlined
} from '@ant-design/icons'
import type { ColumnsType, TablePaginationConfig } from 'antd/lib/table'
import type { FilterValue, SorterResult } from 'antd/lib/table/interface'
import DashboardLayout from "../../../components/layout/dashboard"
import DashPageHeader from "../../../components/layout/dashboard/DashPageHeader"
import * as api from "../../../services/api/dashboard/api"
import APIResult = API.APIResult
import ListData = API.ListData
import UserDetail = API.UserDetail
import SecretValue = API.SecretValue
import ResetPasswordAo = API.ResetPasswordAo
import { switchSecretLevelText } from '../../../utils/security'
import { encrypt } from '../../../utils/encryption'

const { Title } = Typography
const { Option } = Select

interface Params {
    pagination?: TablePaginationConfig
    sorter?: SorterResult<any> | SorterResult<any>[]
    total?: number
    sortField?: string
    sortOrder?: string
}

type QueryCriteria = {
    username?: string, email?: string, phone?: string, ip?: string,
    secretLevel?: string, locked?: boolean, enabled?: boolean, pagination: TablePaginationConfig
}

const getRandomuserParams = (params: Params) => ({
    results: params.pagination?.pageSize,
    page: params.pagination?.current,
    ...params,
})

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
        path: '/dashboard/uaa/perm',
        breadcrumbName: '用户权限管理',
    },
]

const DashboardUaaPerm = () => {
    const [inited, setInited] = useState<boolean>(false)
    const [userList, setUserList] = useState<UserDetail[]>()
    const [loading, setLoading] = useState(false)
    const [visibleSecretLevelModel, setVisibleSecretLevelModel] = useState(false)
    const [secretLevelModelLoading, setSecretLevelModelLoading] = useState(false)
    const [modelSecretLevelForm] = Form.useForm()
    const [visibleResetPasswordModel, setVisibleResetPasswordModel] = useState(false)
    const [resetPasswordModelLoading, setResetPasswordModelLoading] = useState(false)
    const [modelResetPasswordForm] = Form.useForm()
    const [pagination, setPagination] = useState<TablePaginationConfig>({
        current: 1,
        pageSize: 10,
    })

    useEffect(() => {
        fetchData({ pagination })
    })

    const fetchData = async (params: QueryCriteria) => {
        if (!inited) {
            await queryData(params)
            setInited(true)
        }
    }

    const queryData = async (params: QueryCriteria) => {
        setLoading(true)
        let listData: APIResult<ListData<UserDetail>> = await api.queryUserList(
            params.username, params.email, params.phone, params.ip, params.secretLevel,
            params.locked, params.enabled, params.pagination.current, params.pagination.pageSize
        )
        if (listData.data?.data) {
            setUserList(listData.data?.data)
        } else {
            setUserList([])
        }

        setLoading(false)
        setPagination({
            ...params.pagination,
            total: listData.data?.total,
            // 200 is mock data, you should read it from server
            // total: data.totalCount,
        })
    }

    const advancedSearch = async (values: any) => {
        let params: QueryCriteria = {
            username: values.username,
            email: values.email,
            phone: values.phone,
            ip: values.ip,
            secretLevel: values.secretLevel,
            locked: values.locked,
            enabled: values.enabled,
            pagination: pagination,
        }
        await queryData(params)
    }

    const AdvancedSearchForm = () => {
        const [expand, setExpand] = useState(false)
        const [form] = Form.useForm()

        const getFields = () => {
            const children = []
            children.push(
                <Col span={6} key="username">
                    <Form.Item
                        name="username"
                        label="用户名称"
                    >
                        <Input />
                    </Form.Item>
                </Col>,
                <Col span={6} key="email">
                    <Form.Item
                        name="email"
                        label="电子邮箱"
                    >
                        <Input />
                    </Form.Item>
                </Col>,
                <Col span={6} key="phone">
                    <Form.Item
                        name="phone"
                        label="手机号码"
                    >
                        <Input />
                    </Form.Item>
                </Col>,
            )
            if (expand) {
                children.push(
                    <Col span={6} key="ip">
                        <Form.Item
                            name="ip"
                            label="注册IP地址"
                        >
                            <Input />
                        </Form.Item>
                    </Col>,
                    <Col span={6} key="secretLevel">
                        <Form.Item
                            name="secretLevel"
                            label="保密等级"
                        >
                            <Select defaultValue="UNCLASSIFIED">
                                <Option value="UNCLASSIFIED">非密</Option>
                                <Option value="INTERNAL">内部</Option>
                                <Option value="SECRET">秘密</Option>
                                <Option value="CONFIDENTIAL">机密</Option>
                            </Select>
                        </Form.Item>
                    </Col>,
                    <Col span={6} key="locked">
                        <Form.Item
                            name="locked"
                            label="是否锁定"
                        >
                            <Select defaultValue="false">
                                <Option value="false">否</Option>
                                <Option value="true">是</Option>
                            </Select>
                        </Form.Item>
                    </Col>,
                    <Col span={6} key="enabled">
                        <Form.Item
                            name="enabled"
                            label="是否启用"
                        >
                            <Select defaultValue="false">
                                <Option value="false">否</Option>
                                <Option value="true">是</Option>
                            </Select>
                        </Form.Item>
                    </Col>,
                )
            }
            return children
        }

        return (
            <Form
                form={form}
                name="advanced_search"
                className="ant-advanced-search-form"
                onFinish={advancedSearch}
            >
                <Row gutter={24}>{getFields()}
                    <Col span={6} style={{ textAlign: 'right' }}>
                        <Space>
                            <Button
                                style={{ margin: '0 8px' }}
                                onClick={() => {
                                    form.resetFields()
                                }}
                                icon={<ReloadOutlined />}
                            >
                                重置
                            </Button>
                            <Button type="primary" htmlType="submit" icon={<SearchOutlined />}>
                                查询
                            </Button>
                            <Button
                                type="text"
                                style={{ fontSize: 12 }}
                                onClick={() => {
                                    setExpand(!expand)
                                }}
                                icon={expand ? <UpOutlined /> : <DownOutlined />}
                            >
                                {expand ? '收起' : '展开'}
                            </Button>
                        </Space>
                    </Col>
                </Row>
            </Form>
        )
    }

    const handleTableChange = (
        newPagination: any,
        filters: any,
        sorter: any,
    ) => {
        fetchData({
            sortField: sorter.field as string,
            sortOrder: sorter.order as string,
            pagination: newPagination,
            ...filters,
        })
    }

    const helpModal = () => {
        Modal.info({
            title: '帮助说明',
            width: 520,
            icon: (<QuestionCircleOutlined />),
            content: (
                <div>
                    <p>根据系统安全要求，采用三员权限分立，流程如下：</p>
                    <ol>
                        <li>系统管理员：添加用户账户。（此时账户未启用）</li>
                        <li>安全保密管理员：对用户账户进行启用、定密级、分配角色、设置密码。</li>
                        <li>安全审计员：不参与当前业务，但可以审计以上两位管理员的操作日志。</li>
                    </ol>
                    <p style={{ textIndent: '2em' }}>安全保密管理员，在此处可对用户：启用/禁用、锁定/解锁、定密、分配角色、设置密码等操作。</p>
                    <p style={{ textIndent: '2em' }}>如果前台启用自助注册功能，在用户点击邮箱验证链接后，将自动激活该用户的账户，自动设置为：非密用户、无后台角色。</p>
                    <p>操作解释：</p>
                    <ul>
                        <li>角色：给用户分配角色，用户将拥有角色的权限</li>
                        <li>定密：确定用户的保密等级</li>
                        <li>启用/禁用：用户账户的启用和禁用（禁用以后禁止登录系统，已经登录的账号将被注销登录状态）</li>
                        <li>锁定/解锁：用户因密码错误或敏感操作被自动锁定，可立即解锁（锁定以后禁止登录系统，但已经登录的不受影响可继续使用账户）</li>
                    </ul>
                </div>
            ),
            onOk() {
            },
        })
    }

    const columns: ColumnsType<UserDetail> = [
        {
            title: '用户名',
            dataIndex: 'username',
        },
        {
            title: '邮箱',
            width: 150,
            dataIndex: 'email',
        },
        {
            title: '手机号',
            width: 150,
            dataIndex: 'phone',
        },
        {
            title: '注册时间',
            width: 170,
            dataIndex: 'registrationDate',
        },
        {
            title: '锁定时间',
            width: 170,
            dataIndex: 'lockTime',
        },
        {
            title: '保密等级',
            width: 100,
            dataIndex: 'secretLevel',
            render: secretLevel => switchSecretLevelText(secretLevel),
        },
        {
            title: '锁定状态',
            width: 90,
            dataIndex: 'locked',
            render: locked => {
                if (locked) {
                    return (<Tag color="error">锁定</Tag>)
                } else {
                    return (<Tag color="success">解锁</Tag>)
                }
            },
        },
        {
            title: '启用状态',
            width: 90,
            dataIndex: 'enabled',
            render: enabled => {
                if (enabled) {
                    return (<Tag color="success">启用</Tag>)
                } else {
                    return (<Tag color="error">禁用</Tag>)
                }
            },
        },
        {
            title: '操作',
            dataIndex: '',
            key: 'x',
            width: 100,
            fixed: 'right',
            render: (_: any, record: UserDetail) => {
                return (
                    <Space>
                        <Button type="primary" icon={<ContactsOutlined />}>角色</Button>
                        <Button
                            type="primary"
                            icon={<SafetyOutlined />}
                            onClick={() => {
                                modelSecretLevelForm.setFieldsValue({
                                    id: record.id,
                                    secretLevel: 'UNCLASSIFIED'
                                })
                                setVisibleSecretLevelModel(true)
                            }}
                        >定密</Button>
                        {
                            record.enabled ?
                                (<Button
                                    type="primary"
                                    icon={<PoweroffOutlined />}
                                    danger
                                    onClick={() => {
                                        api.enableUser(record.id, false).then(res => {
                                            if (res.code = 200) {
                                                queryData({ pagination })
                                            } else {
                                                message.error(res.message)
                                            }
                                        })
                                    }}
                                >禁用</Button>)
                                : (<Button
                                    type="primary"
                                    icon={<PoweroffOutlined />}
                                    onClick={() => {
                                        api.enableUser(record.id, true).then(res => {
                                            if (res.code = 200) {
                                                queryData({ pagination })
                                            } else {
                                                message.error(res.message)
                                            }
                                        })
                                    }}
                                >启用</Button>)
                        }
                        {/* {
                            record.locked ?
                                (<Button type="primary" icon={<UnlockOutlined />}>解锁</Button>)
                                : (<Button type="primary" icon={<UnlockOutlined />} danger>锁定</Button>)
                        } */}
                        <Button
                            type="primary"
                            icon={<KeyOutlined />}
                            onClick={() => {
                                modelResetPasswordForm.setFieldsValue({
                                    id: record.id,
                                    password: ''
                                })
                                setVisibleResetPasswordModel(true)
                            }}
                        >密码</Button>
                    </Space>
                )
            },
        },
    ]

    const handleSecretLevelModelOk = async () => {
        setSecretLevelModelLoading(true)
        if (!modelSecretLevelForm.getFieldValue('secretLevel')) {
            message.error('请选择保密等级')
            setSecretLevelModelLoading(false)
            return
        }
        let apiresult: APIResult<any> = await api.determineUserSecretLevel(modelSecretLevelForm.getFieldValue('id'),
            modelSecretLevelForm.getFieldValue('secretLevel'))
        if (apiresult.code != 200) {
            message.error(apiresult.message)
            setSecretLevelModelLoading(false)
            return
        } else {
            setVisibleSecretLevelModel(false)
        }
        setSecretLevelModelLoading(false)
        queryData({ pagination })
    }

    const handleSecretLevelModelCancel = () => {
        setVisibleSecretLevelModel(false)
        setSecretLevelModelLoading(false)
    }

    const handleResetPasswordModelOk = async () => {
        setResetPasswordModelLoading(true)
        if (!modelResetPasswordForm.getFieldValue('password')) {
            message.error('请填写密码')
            setResetPasswordModelLoading(false)
            return
        }
        // 加密密码
        let secretValue: SecretValue | undefined = await encrypt(modelResetPasswordForm.getFieldValue('password'))
        if (!secretValue) {
            message.error('密码加密失败，请重试')
            setResetPasswordModelLoading(false)
            return
        }
        let resetPasswordAo: ResetPasswordAo = {
            password: secretValue.value,
            tOtp: '',
            keyUuid: secretValue.secretKey.uuid,
            verCode: ''
        }
        let apiresult: APIResult<any> = await api.resetPassword(modelResetPasswordForm.getFieldValue('id'), resetPasswordAo)
        if (apiresult.code != 200) {
            message.error(apiresult.message)
            setResetPasswordModelLoading(false)
            return
        } else {
            setVisibleResetPasswordModel(false)
        }
        setResetPasswordModelLoading(false)
        queryData({ pagination })
    }

    const handleResetPasswordModelCancel = () => {
        setVisibleResetPasswordModel(false)
        setResetPasswordModelLoading(false)
    }

    return (
        <>
            <Head>
                <title>用户权限管理 - UAA 用户账户认证管理系统 (User Account and Authentication)</title>
            </Head>

            <div style={{ backgroundColor: '#fff' }}>
                <DashPageHeader
                    title="用户权限管理"
                    routes={routes}
                    subTitle="UAA 用户账户认证管理系统 (User Account and Authentication)"
                />
            </div>

            <div style={{ padding: '23px' }}>
                <div style={{ backgroundColor: '#fff', padding: '24px 24px 0', marginBottom: '16px' }}>
                    <AdvancedSearchForm />
                </div>
                <div style={{ backgroundColor: '#fff', padding: '24px', marginBottom: '16px' }}>
                    <Row>
                        <Col span={12}>
                            <Title level={5}>用户账号列表</Title>
                        </Col>
                        <Col span={12} style={{ textAlign: 'right', paddingBottom: '10px' }}>
                            <Space>
                                <Button
                                    onClick={helpModal}
                                    icon={<QuestionCircleOutlined />}>
                                    帮助说明
                                </Button>
                            </Space>
                        </Col>
                    </Row>
                    <Table
                        columns={columns}
                        scroll={{ x: true }}
                        rowKey={record => record.id}
                        dataSource={userList}
                        pagination={pagination}
                        loading={loading}
                        onChange={handleTableChange}
                    />
                </div>
            </div>
            <Modal
                title="保密等级"
                visible={visibleSecretLevelModel}
                confirmLoading={secretLevelModelLoading}
                onOk={handleSecretLevelModelOk}
                onCancel={handleSecretLevelModelCancel}
            >
                <Form
                    form={modelSecretLevelForm}
                >
                    <Form.Item name="id" label="ID" style={{ display: 'none' }}>
                        <Input />
                    </Form.Item>
                    <Form.Item name="secretLevel">
                        <Select defaultValue="UNCLASSIFIED">
                            <Option value="UNCLASSIFIED">非密</Option>
                            <Option value="INTERNAL">内部</Option>
                            <Option value="SECRET">秘密</Option>
                            <Option value="CONFIDENTIAL">机密</Option>
                        </Select>
                    </Form.Item>
                </Form>
            </Modal>
            <Modal
                title="密码重置"
                visible={visibleResetPasswordModel}
                confirmLoading={resetPasswordModelLoading}
                onOk={handleResetPasswordModelOk}
                onCancel={handleResetPasswordModelCancel}
            >
                <Form
                    form={modelResetPasswordForm}
                >
                    <Form.Item name="id" label="ID" style={{ display: 'none' }}>
                        <Input />
                    </Form.Item>
                    <Form.Item name="password">
                        <Input />
                    </Form.Item>
                </Form>
            </Modal>
        </>
    )
}

DashboardUaaPerm.getLayout = (page: any) => {
    return (
        <DashboardLayout>
            {page}
        </DashboardLayout>
    )
}

export default DashboardUaaPerm