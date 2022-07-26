import Head from 'next/head'
import React, {useEffect, useState} from 'react'
import {Col, Row, Button, Typography, Table, Space, Form, Input, Select, Modal} from 'antd'
import {
    DownOutlined,
    UpOutlined,
    PlusOutlined,
    SearchOutlined,
    ReloadOutlined,
    EditOutlined,
    DeleteOutlined,
    QuestionCircleOutlined
} from '@ant-design/icons'
import type {ColumnsType, TablePaginationConfig} from 'antd/lib/table'
import type {FilterValue, SorterResult} from 'antd/lib/table/interface'
import DashboardLayout from "../../../components/layout/dashboard"
import DashPageHeader from "../../../components/layout/dashboard/DashPageHeader"

const {Title} = Typography
const {Option} = Select

interface DataType {
    name: {
        first: string
        last: string
    }
    gender: string
    email: string
    login: {
        uuid: string
    }
}

interface Params {
    pagination?: TablePaginationConfig
    sorter?: SorterResult<any> | SorterResult<any>[]
    total?: number
    sortField?: string
    sortOrder?: string
}

const columns: ColumnsType<DataType> = [
    {
        title: 'Name',
        width: 300,
        dataIndex: 'name',
        render: name => `${name.first} ${name.last}`,
    },
    {
        title: 'Gender',
        width: 100,
        dataIndex: 'gender',
    },
    {
        title: 'Email',
        dataIndex: 'email',
    },
    {
        title: '操作',
        dataIndex: '',
        key: 'x',
        width: 100,
        fixed: 'right',
        render: () => {
            return (
                <Space>
                    <Button type="primary" icon={<EditOutlined/>}>编辑</Button>
                    <Button type="primary" icon={<DeleteOutlined/>} danger>删除</Button>
                </Space>
            )
        },
    },
]

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

const AdvancedSearchForm = () => {
    const [expand, setExpand] = useState(false)
    const [form] = Form.useForm()

    const getFields = () => {
        const children = []
        children.push(
            <Col span={6} key="username">
                <Form.Item
                    name="roleName"
                    label="角色名称"
                >
                    <Input/>
                </Form.Item>
            </Col>,
            <Col span={6}> </Col>,
            <Col span={6}> </Col>,
        )
        return children
    }

    const onFinish = (values: any) => {
        console.log('Received values of form: ', values)
    }

    return (
        <Form
            form={form}
            name="advanced_search"
            className="ant-advanced-search-form"
            onFinish={onFinish}
        >
            <Row gutter={24}>{getFields()}
                <Col span={6} style={{textAlign: 'right'}}>
                    <Space>
                        <Button
                            style={{margin: '0 8px'}}
                            onClick={() => {
                                form.resetFields()
                            }}
                            icon={<ReloadOutlined/>}
                        >
                            重置
                        </Button>
                        <Button type="primary" htmlType="submit" icon={<SearchOutlined/>}>
                            查询
                        </Button>
                        <Button
                            type="text"
                            style={{fontSize: 12}}
                            onClick={() => {
                                setExpand(!expand)
                            }}
                            icon={expand ? <UpOutlined/> : <DownOutlined/>}
                        >
                            {expand ? '收起' : '展开'}
                        </Button>
                    </Space>
                </Col>
            </Row>
        </Form>
    )
}

const DashboardUaaRole = () => {
    const [helpModalVisible, setHelpModalVisible] = useState(false)
    const [data, setData] = useState()
    const [loading, setLoading] = useState(false)
    const [pagination, setPagination] = useState<TablePaginationConfig>({
        current: 1,
        pageSize: 10,
    })

    useEffect(() => {
        fetchData({pagination})
    })

    const fetchData = (params: Params = {}) => {
        if (!data) {
            setLoading(true)
            fetch(`https://randomuser.me/api?${getRandomuserParams(params)}`)
                .then(res => res.json())
                .then(({results}) => {
                    setData(results)
                    setLoading(false)
                    setPagination({
                        ...params.pagination,
                        total: 200,
                        // 200 is mock data, you should read it from server
                        // total: data.totalCount,
                    })
                })
        }
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
            icon: (<QuestionCircleOutlined/>),
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

    return (
        <>
            <Head>
                <title>角色管理 - RBAC 基于角色的权限控制 (Role-Based Access Control)</title>
            </Head>

            <div style={{backgroundColor: '#fff'}}>
                <DashPageHeader
                    title="角色管理"
                    routes={routes}
                    subTitle="RBAC 基于角色的权限控制 (Role-Based Access Control)"
                />
            </div>

            <div style={{padding: '23px'}}>
                <div style={{backgroundColor: '#fff', padding: '24px 24px 0', marginBottom: '16px'}}>
                    <AdvancedSearchForm/>
                </div>
                <div style={{backgroundColor: '#fff', padding: '24px', marginBottom: '16px'}}>
                    <Row>
                        <Col span={12}>
                            <Title level={5}>角色列表</Title>
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
                                    创建角色
                                </Button>
                            </Space>
                        </Col>
                    </Row>
                    <Table
                        columns={columns}
                        rowKey={record => record.login.uuid}
                        dataSource={data}
                        pagination={pagination}
                        loading={loading}
                        onChange={handleTableChange}
                    />
                </div>
            </div>
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