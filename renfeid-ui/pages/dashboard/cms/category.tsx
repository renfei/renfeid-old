import Head from 'next/head'
import nookies from 'nookies'
import moment from 'moment'
import React, { useEffect, useState } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Col, Row, Button, Typography, Table, Space, Form, Input, Select, Modal, message } from 'antd'
import {
    PlusOutlined,
    SearchOutlined,
    ReloadOutlined,
    EditOutlined,
    DeleteOutlined,
    QuestionCircleOutlined
} from '@ant-design/icons'
import type { ColumnsType, TablePaginationConfig } from 'antd/lib/table'
import DashboardLayout from "../../../components/layout/dashboard"
import DashPageHeader from "../../../components/layout/dashboard/DashPageHeader"
import * as api from "../../../services/api/dashboard/api"
import APIResult = API.APIResult
import ListData = API.ListData
import PostCategory = API.PostCategory
import { switchSecretLevelText } from "../../../utils/security"
import Params = API.TableListParams

const { Option } = Select
const { Title } = Typography

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
        breadcrumbName: '内容管理',
    },
    {
        path: '/dashboard/cms/category',
        breadcrumbName: '分类列表',
    },
]

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
    return {
        props: {
            data: {
                accessToken: accessToken,
            }
        }
    }
}

const helpModal = () => {
    Modal.info({
        title: '帮助说明',
        width: 520,
        icon: (<QuestionCircleOutlined />),
        content: (
            <div>
                <p>此处为CMS内容分类管理。</p>
                <p>英文名称不可重复。</p>
                <p>保密等级会限制分类下的内容的保密等级。</p>
            </div>
        ),
        onOk() {
        },
    })
}

const DashboardCmsCategory = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
    const [postCategoryList, setPostCategoryList] = useState<PostCategory[]>()
    const [visibleModel, setVisibleModel] = useState(false)
    const [modelLoading, setModelLoading] = useState(false)
    const [modelForm] = Form.useForm()
    const [loading, setLoading] = useState(false)
    const [pagination, setPagination] = useState<TablePaginationConfig>({
        current: 1,
        pageSize: 10,
    })

    type QueryCriteria = {
        enName?: string, zhName?: string, secretLevel?: string, pagination: TablePaginationConfig
    }

    useEffect(() => {
        fetchData({ pagination })
    })

    const fetchData = async (params: QueryCriteria) => {
        if (!postCategoryList) {
            await queryData(params)
        }
    }

    const queryData = async (params: QueryCriteria) => {
        setLoading(true)
        let listData: APIResult<ListData<PostCategory>> = await api.queryPostCategoryList(data.accessToken, new Headers(),
            params.enName, params.zhName, params.secretLevel, params.pagination.current, params.pagination.pageSize
        )
        setPostCategoryList(listData.data?.data)
        setLoading(false)
        setPagination({
            ...params.pagination,
            total: listData.data?.total,
            // 200 is mock data, you should read it from server
            // total: data.totalCount,
        })
    }

    const columns: ColumnsType<PostCategory> = [
        {
            title: '英文名称',
            dataIndex: 'enName',
        },
        {
            title: '中文名称',
            dataIndex: 'zhName',
            width: 160,
        },
        {
            title: '保密等级',
            width: 100,
            dataIndex: 'secretLevel',
            render: data => {
                return switchSecretLevelText(data)
            },
        },
        {
            title: '操作',
            dataIndex: 'id',
            key: 'x',
            width: 100,
            fixed: 'right',
            render: (_: any, record: PostCategory) => {
                return (
                    <Space>
                        <Button
                            type="primary"
                            icon={<EditOutlined />}
                            onClick={() => {
                                modelForm.setFieldsValue({ id: record.id })
                                modelForm.setFieldsValue({ enName: record.enName })
                                modelForm.setFieldsValue({ zhName: record.zhName })
                                modelForm.setFieldsValue({ secretLevel: record.secretLevel })
                                setVisibleModel(true)
                            }}
                        >编辑</Button>
                        <Button
                            danger
                            icon={<DeleteOutlined />}
                            onClick={() => {
                                if (confirm('确认删除吗？')) {
                                    api.deletePostCategory(record.id).then(res => {
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

    const AdvancedSearchForm = (props: any) => {
        const [form] = Form.useForm()

        const getFields = () => {
            const children = []
            children.push(
                <Col span={6} key="secretLevel">
                    <Form.Item
                        name="secretLevel"
                        label="保密等级"
                    >
                        <Select>
                            <Option value="UNCLASSIFIED">非密</Option>
                            <Option value="INTERNAL">内部</Option>
                            <Option value="SECRET">秘密</Option>
                            <Option value="CONFIDENTIAL">机密</Option>
                        </Select>
                    </Form.Item>
                </Col>,
                <Col span={6} key="enName">
                    <Form.Item
                        name="enName"
                        label="英文名称"
                    >
                        <Input />
                    </Form.Item>
                </Col>,
                <Col span={6} key="zhName">
                    <Form.Item
                        name="zhName"
                        label="中文名称"
                    >
                        <Input />
                    </Form.Item>
                </Col>,
            )
            return children
        }

        const advancedSearch = async (values: any) => {
            console.info(values)
            let params: QueryCriteria = {
                enName: values.enName,
                zhName: values.zhName,
                secretLevel: values.secretLevel,
                pagination: pagination,
            }
            await queryData(params)
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

    const handleOk = async () => {
        setModelLoading(true)
        if (!modelForm.getFieldValue('enName')) {
            message.error('请填写英文名称')
            setModelLoading(false)
            return
        }
        if (!modelForm.getFieldValue('zhName')) {
            message.error('请填写中文名称')
            setModelLoading(false)
            return
        }
        if (!modelForm.getFieldValue('secretLevel')) {
            message.error('请填选择保密等级')
            setModelLoading(false)
            return
        }
        const postCategory: PostCategory = {
            id: modelForm.getFieldValue('id'),
            enName: modelForm.getFieldValue('enName'),
            zhName: modelForm.getFieldValue('zhName'),
            secretLevel: modelForm.getFieldValue('secretLevel'),
        }
        if (!modelForm.getFieldValue('id')) {
            // 新增
            let apiresult: APIResult<any> = await api.createPostCategory(postCategory)
            if (apiresult.code != 200) {
                message.error(apiresult.message)
            } else {
                setVisibleModel(false)
            }
        } else {
            // 修改
            let apiresult: APIResult<any> = await api.updatePostCategory(postCategory)
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
                <title>内容分类 - CMS 内容管理系统 (Content Management System)</title>
            </Head>

            <div style={{ backgroundColor: '#fff' }}>
                <DashPageHeader
                    title="内容分类"
                    routes={routes}
                    subTitle="CMS 内容管理系统 (Content Management System)"
                />
            </div>

            <div style={{ padding: '23px' }}>
                <div style={{ backgroundColor: '#fff', padding: '24px 24px 0', marginBottom: '16px' }}>
                    <AdvancedSearchForm postCatOptions={data.postCatOptions} />
                </div>
                <div style={{ backgroundColor: '#fff', padding: '24px', marginBottom: '16px' }}>
                    <Row>
                        <Col span={12}>
                            <Title level={5}>内容分类列表</Title>
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
                                        modelForm.setFieldsValue({ id: undefined })
                                        modelForm.setFieldsValue({ enName: undefined })
                                        modelForm.setFieldsValue({ zhName: undefined })
                                        modelForm.setFieldsValue({ secretLevel: undefined })
                                        setVisibleModel(true)
                                    }}
                                >
                                    新建
                                </Button>
                            </Space>
                        </Col>
                    </Row>
                    <Table
                        columns={columns}
                        rowKey={record => record.id}
                        dataSource={postCategoryList}
                        pagination={pagination}
                        loading={loading}
                        onChange={handleTableChange}
                    />
                    <Modal
                        title="内容分类编辑"
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
                            <Form.Item name="enName" label="英文名称">
                                <Input />
                            </Form.Item>
                            <Form.Item name="zhName" label="中文名称">
                                <Input />
                            </Form.Item>
                            <Form.Item name="secretLevel" label="保密等级">
                                <Select>
                                    <Option value="UNCLASSIFIED">非密</Option>
                                    <Option value="INTERNAL">内部</Option>
                                    <Option value="SECRET">秘密</Option>
                                    <Option value="CONFIDENTIAL">机密</Option>
                                </Select>
                            </Form.Item>
                        </Form>
                    </Modal>
                </div>
            </div>
        </>
    )
}

DashboardCmsCategory.getLayout = (page: any) => {
    return (
        <DashboardLayout>
            {page}
        </DashboardLayout>
    )
}

export default DashboardCmsCategory