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
        first: string;
        last: string;
    };
    gender: string;
    email: string;
    login: {
        uuid: string;
    };
}

interface Params {
    pagination?: TablePaginationConfig;
    sorter?: SorterResult<any> | SorterResult<any>[];
    total?: number;
    sortField?: string;
    sortOrder?: string;
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
                </Space>
            )
        },
    },
];

const getRandomuserParams = (params: Params) => ({
    results: params.pagination?.pageSize,
    page: params.pagination?.current,
    ...params,
});

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
        path: '/dashboard/uaa/user',
        breadcrumbName: '用户账号管理',
    },
]

const AdvancedSearchForm = () => {
    const [expand, setExpand] = useState(false)
    const [form] = Form.useForm()

    const getFields = () => {
        const children = [];
        children.push(
            <Col span={6} key="username">
                <Form.Item
                    name="username"
                    label="用户名称"
                >
                    <Input/>
                </Form.Item>
            </Col>,
            <Col span={6} key="email">
                <Form.Item
                    name="email"
                    label="电子邮箱"
                >
                    <Input/>
                </Form.Item>
            </Col>,
            <Col span={6} key="phone">
                <Form.Item
                    name="phone"
                    label="手机号码"
                >
                    <Input/>
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
                        <Input/>
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
        return children;
    };

    const onFinish = (values: any) => {
        console.log('Received values of form: ', values);
    };

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
                                form.resetFields();
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
                                setExpand(!expand);
                            }}
                            icon={expand ? <UpOutlined/> : <DownOutlined/>}
                        >
                            {expand ? '收起' : '展开'}
                        </Button>
                    </Space>
                </Col>
            </Row>
        </Form>
    );
}

const DashboardUaaUser = () => {
    const [helpModalVisible, setHelpModalVisible] = useState(false)
    const [data, setData] = useState()
    const [loading, setLoading] = useState(false)
    const [pagination, setPagination] = useState<TablePaginationConfig>({
        current: 1,
        pageSize: 10,
    })

    useEffect(() => {
        fetchData({pagination});
    })

    const fetchData = (params: Params = {}) => {
        if (!data) {
            setLoading(true);
            fetch(`https://randomuser.me/api?${getRandomuserParams(params)}`)
                .then(res => res.json())
                .then(({results}) => {
                    setData(results);
                    setLoading(false);
                    setPagination({
                        ...params.pagination,
                        total: 200,
                        // 200 is mock data, you should read it from server
                        // total: data.totalCount,
                    });
                });
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
        });
    }

    const helpModal = () => {
        Modal.info({
            title: '帮助说明',
            width: 520,
            icon: (<QuestionCircleOutlined/>),
            content: (
                <div>
                    <p>根据系统安全要求，采用三员权限分立，流程如下：</p>
                    <ol>
                        <li>系统管理员：添加用户账户。（此时账户未启用）</li>
                        <li>安全保密管理员：对用户账户进行启用、定密级、分配角色、设置密码。</li>
                        <li>安全审计员：不参与当前业务，但可以审计以上两位管理员的操作日志。</li>
                    </ol>
                    <p style={{textIndent: '2em'}}>在此处添加用户后，需要安全保密管理员到「用户权限管理」中对用户账户进行启用激活、定密、分配角色、设置密码，账户方可生效。</p>
                    <p style={{textIndent: '2em'}}>如果前台启用自助注册功能，在用户点击邮箱验证链接后，将自动激活该用户的账户，自动设置为：非密用户、无后台角色。</p>
                </div>
            ),
            onOk() {
            },
        })
    }

    return (
        <>
            <Head>
                <title>用户账号管理 - UAA 用户账户认证管理系统 (User Account and Authentication)</title>
            </Head>

            <div style={{backgroundColor: '#fff'}}>
                <DashPageHeader
                    title="用户账号管理"
                    routes={routes}
                    subTitle="UAA 用户账户认证管理系统 (User Account and Authentication)"
                />
            </div>

            <div style={{padding: '23px'}}>
                <div style={{backgroundColor: '#fff', padding: '24px 24px 0', marginBottom: '16px'}}>
                    <AdvancedSearchForm/>
                </div>
                <div style={{backgroundColor: '#fff', padding: '24px', marginBottom: '16px'}}>
                    <Row>
                        <Col span={12}>
                            <Title level={5}>用户账号列表</Title>
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
                                    创建用户
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

DashboardUaaUser.getLayout = (page: any) => {
    return (
        <DashboardLayout>
            {page}
        </DashboardLayout>
    )
}

export default DashboardUaaUser