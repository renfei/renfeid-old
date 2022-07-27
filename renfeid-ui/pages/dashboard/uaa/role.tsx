import Head from 'next/head'
import moment from 'moment'
import React, { useEffect, useState } from 'react'
import { Col, Row, Button, Typography, Table, Space, Form, Input, Modal, Tag, message } from 'antd'
import {
    DeleteOutlined,
    PlusOutlined,
    SearchOutlined,
    ReloadOutlined,
    EditOutlined,
    QuestionCircleOutlined
} from '@ant-design/icons'
import type { ColumnsType, TablePaginationConfig } from 'antd/lib/table'
import type { FilterValue, SorterResult } from 'antd/lib/table/interface'
import DashboardLayout from "../../../components/layout/dashboard"
import DashPageHeader from "../../../components/layout/dashboard/DashPageHeader"
import * as api from "../../../services/api/dashboard/api"
import APIResult = API.APIResult
import ListData = API.ListData
import RoleDetail = API.RoleDetail
import SecretValue = API.SecretValue
import { switchSecretLevelText } from '../../../utils/security'
import { encrypt } from '../../../utils/encryption'

const { Title } = Typography
const { TextArea } = Input

interface Params {
    pagination?: TablePaginationConfig
    sorter?: SorterResult<any> | SorterResult<any>[]
    total?: number
    sortField?: string
    sortOrder?: string
}

type QueryCriteria = {
    roleName?: string, pagination: TablePaginationConfig
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
        path: '/dashboard/uaa/role',
        breadcrumbName: '角色管理',
    },
]

const DashboardUaaRole = () => {
    const [inited, setInited] = useState<boolean>(false)
    const [roleList, setRoleList] = useState<RoleDetail[]>()
    const [loading, setLoading] = useState(false)
    const [visibleModel, setVisibleModel] = useState(false)
    const [modelLoading, setModelLoading] = useState(false)
    const [modelForm] = Form.useForm()
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
        let listData: APIResult<ListData<RoleDetail>> = await api.queryRoleList(
            params.roleName, params.pagination.current, params.pagination.pageSize
        )
        console.info(listData)
        if (listData.data?.data) {
            setRoleList(listData.data?.data)
        } else {
            setRoleList([])
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
            roleName: values.roleName,
            pagination: pagination,
        }
        await queryData(params)
    }

    const AdvancedSearchForm = () => {
        const [form] = Form.useForm()

        const getFields = () => {
            const children = []
            children.push(
                <Col span={6} key="roleName">
                    <Form.Item
                        name="roleName"
                        label="角色名称"
                    >
                        <Input />
                    </Form.Item>
                </Col>,
                <Col span={6}> </Col>,
                <Col span={6}> </Col>,
            )
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
                    <p>RBAC 基于角色的权限控制 (Role-Based Access Control)</p>
                    <ol>
                        <li>将资源权限赋予给角色</li>
                        <li>将角色赋予给用户</li>
                        <li>一个用户可以拥有多个角色</li>
                    </ol>
                </div>
            ),
            onOk() {
            },
        })
    }

    const columns: ColumnsType<RoleDetail> = [
        {
            title: '角色名称',
            width: 150,
            dataIndex: 'roleName',
        },
        {
            title: '角色描述',
            dataIndex: 'roleDescribe',
        },
        {
            title: '添加时间',
            width: 170,
            dataIndex: 'addTime',
        },
        {
            title: '操作',
            dataIndex: '',
            key: 'x',
            width: 100,
            fixed: 'right',
            render: (_: any, record: RoleDetail) => {
                return (
                    <Space>
                        <Button
                            type="primary"
                            icon={<EditOutlined />}
                            onClick={() => {
                                modelForm.setFieldsValue({
                                    id: record.id,
                                    roleName: record.roleName,
                                    roleDescribe: record.roleDescribe,
                                    extendJson: record.extendJson,
                                })
                                setVisibleModel(true)
                            }}
                        >编辑</Button>
                        <Button
                            danger
                            icon={<DeleteOutlined />}
                            onClick={() => {
                                if (confirm('确认删除吗？')) {
                                    api.deleteRole(record.id).then(res => {
                                        if (res.code == 200) {
                                            queryData({ pagination })
                                        } else {
                                            message.error(res.message)
                                        }
                                    })
                                }
                            }}
                        >删除</Button>
                    </Space>
                )
            },
        },
    ]

    const handleOk = async () => {
        setModelLoading(true)
        if (!modelForm.getFieldValue('roleName')) {
            message.error('请填写角色名称')
            setModelLoading(false)
            return
        }
        const roleDetail: RoleDetail = {
            id: modelForm.getFieldValue('id'),
            roleName: modelForm.getFieldValue('roleName'),
            roleDescribe: modelForm.getFieldValue('roleDescribe'),
            extendJson: modelForm.getFieldValue('extendJson'),
            addTime: '',
            builtInRole: false
        }
        if (!modelForm.getFieldValue('id')) {
            // 新增
            let apiresult: APIResult<any> = await api.createRole(roleDetail)
            if (apiresult.code != 200) {
                message.error(apiresult.message)
            } else {
                setVisibleModel(false)
            }
        } else {
            // 修改
            let apiresult: APIResult<any> = await api.updateRole(roleDetail)
            if (apiresult.code != 200) {
                message.error(apiresult.message)
            } else {
                setVisibleModel(false)
            }
        }
        setModelLoading(false)
        queryData({ pagination })
    }

    const handleCancel = () => {
        setVisibleModel(false)
        setModelLoading(false)
    }

    return (
        <>
            <Head>
                <title>角色管理 - UAA 用户账户认证管理系统 (User Account and Authentication)</title>
            </Head>

            <div style={{ backgroundColor: '#fff' }}>
                <DashPageHeader
                    title="角色管理"
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
                                <Button
                                    type="primary"
                                    icon={<PlusOutlined />}
                                    onClick={() => {
                                        modelForm.setFieldsValue({
                                            id: undefined,
                                            roleName: '',
                                            roleDescribe: '',
                                            extendJson: '',
                                        })
                                        setVisibleModel(true)
                                    }}
                                >
                                    创建角色
                                </Button>
                            </Space>
                        </Col>
                    </Row>
                    <Table
                        columns={columns}
                        scroll={{ x: true }}
                        rowKey={record => record.id}
                        dataSource={roleList}
                        pagination={pagination}
                        loading={loading}
                        onChange={handleTableChange}
                    />
                </div>
            </div>
            <Modal
                title="角色信息"
                visible={visibleModel}
                confirmLoading={modelLoading}
                onOk={handleOk}
                onCancel={handleCancel}
            >
                <Form
                    form={modelForm}
                >
                    <Form.Item name="id" label="ID" style={{ display: 'none' }}>
                        <Input />
                    </Form.Item>
                    <Form.Item name="roleName" label="角色名称">
                        <Input />
                    </Form.Item>
                    <Form.Item name="roleDescribe" label="角色描述">
                        <Input />
                    </Form.Item>
                    <Form.Item name="extendJson" label="扩展信息">
                        <TextArea rows={4} />
                    </Form.Item>
                </Form>
            </Modal>
        </>
    )
}

DashboardUaaRole.getLayout = (page: any) => {
    return (
        <DashboardLayout>
            {page}
        </DashboardLayout>
    )
}

export default DashboardUaaRole