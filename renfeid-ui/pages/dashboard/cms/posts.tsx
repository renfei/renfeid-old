import Head from 'next/head'
import React, {useEffect, useState} from 'react'
import {Col, Row, Button, Typography, Table, Space, Form, Input, Select} from 'antd'
import {
    DownOutlined,
    UpOutlined,
    PlusOutlined,
    SearchOutlined,
    ReloadOutlined,
    EditOutlined,
    DeleteOutlined
} from '@ant-design/icons'
import type {ColumnsType, TablePaginationConfig} from 'antd/lib/table'
import type {FilterValue, SorterResult} from 'antd/lib/table/interface'
import DashboardLayout from "../../../components/layout/dashboard"
import DashPageHeader from "../../../components/layout/dashboard/DashPageHeader"

const {Title} = Typography;

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
                    <Button danger icon={<DeleteOutlined/>}>删除</Button>
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
        breadcrumbName: '内容管理',
    },
    {
        path: '/dashboard/cms/posts',
        breadcrumbName: '内容列表',
    },
]

const {Option} = Select;

const AdvancedSearchForm = () => {
    const [expand, setExpand] = useState(false)
    const [form] = Form.useForm()

    const getFields = () => {
        const count = expand ? 10 : 3;
        const children = [];
        for (let i = 0; i < count; i++) {
            children.push(
                <Col span={6} key={i}>
                    <Form.Item
                        name={`field-${i}`}
                        label={`Field ${i}`}
                    >
                        {i % 3 !== 1 ? (
                            <Input placeholder="placeholder"/>
                        ) : (
                            <Select defaultValue="2">
                                <Option value="1">1</Option>
                                <Option value="2">
                                    longlonglonglonglonglonglonglonglonglonglongl
                                </Option>
                            </Select>
                        )}
                    </Form.Item>
                </Col>,
            );
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

const DashboardCmsPosts = () => {
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
    };

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
    };

    return (
        <>
            <Head>
                <title>内容列表 - CMS 内容管理系统 (Content Management System)</title>
            </Head>

            <div style={{backgroundColor: '#fff'}}>
                <DashPageHeader
                    title="内容列表"
                    routes={routes}
                    subTitle="CMS 内容管理系统 (Content Management System)"
                />
            </div>

            <div style={{padding: '23px'}}>
                <div style={{backgroundColor: '#fff', padding: '24px 24px 0', marginBottom: '16px'}}>
                    <AdvancedSearchForm/>
                </div>
                <div style={{backgroundColor: '#fff', padding: '24px', marginBottom: '16px'}}>
                    <Row>
                        <Col span={12}>
                            <Title level={5}>内容管理列表</Title>
                        </Col>
                        <Col span={12} style={{textAlign: 'right'}}>
                            <Space>
                                <Button
                                    href="/dashboard/cms/posts/new"
                                    type="primary"
                                    icon={<PlusOutlined/>}>
                                    新建
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

DashboardCmsPosts.getLayout = (page: any) => {
    return (
        <DashboardLayout>
            {page}
        </DashboardLayout>
    )
}

export default DashboardCmsPosts