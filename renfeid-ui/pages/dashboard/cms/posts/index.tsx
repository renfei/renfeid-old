import Head from 'next/head'
import nookies from 'nookies'
import moment from 'moment'
import React, { useEffect, useState } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Col, Row, Button, Typography, Table, Space, Form, Input, Select, DatePicker, Modal, message } from 'antd'
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
import type { ColumnsType, TablePaginationConfig } from 'antd/lib/table'
import DashboardLayout from "../../../../components/layout/dashboard"
import DashPageHeader from "../../../../components/layout/dashboard/DashPageHeader"
import * as api from "../../../../services/api/dashboard/api"
import { convertToHeaders } from "../../../../utils/request"
import AntdSelectOption = API.AntdSelectOption
import APIResult = API.APIResult
import ListData = API.ListData
import PostCategory = API.PostCategory
import { getPostStatusList, switchPostStatus } from "../../../../utils/posts"
import Params = API.TableListParams
import { queryPostList } from "../../../../services/api/dashboard/api"
import DashPost = API.DashPost
import UserInfo = API.UserInfo
import CheckSignInStatus from '../../../../utils/CheckSignInStatus'

const { Title } = Typography

const columns: ColumnsType<DashPost> = [
    {
        title: '标题',
        dataIndex: 'postTitle',
    },
    {
        title: '状态',
        dataIndex: 'postStatus',
        width: 60,
        render: post => {
            return switchPostStatus(post)
        },
    },
    {
        title: '浏览量',
        width: 80,
        dataIndex: 'postViews',
    },
    {
        title: '版本号',
        width: 75,
        dataIndex: 'versionNumber',
    },
    {
        title: '作者',
        width: 80,
        dataIndex: 'authorUsername',
    },
    {
        title: '发布时间',
        width: 165,
        dataIndex: 'postDate',
    },
    {
        title: '操作',
        dataIndex: 'id',
        key: 'x',
        width: 100,
        fixed: 'right',
        render: (_: any, record: DashPost) => {
            return (
                <Space>
                    <Button type="primary" icon={<EditOutlined />} href={`/dashboard/cms/posts/${record.id}`}>编辑</Button>
                    <Button
                        danger
                        icon={<DeleteOutlined />}
                        disabled={record.postStatus != 'PUBLISH'}
                        onClick={() => {
                            api.offlinePost(record.id).then(res => {
                                if (res.code == 200) {
                                    message.success('下线成功，请刷新查看')
                                } else {
                                    message.error(res.message)
                                }
                            })
                        }}
                    >下线</Button>
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
        breadcrumbName: '内容管理',
    },
    {
        path: '/dashboard/cms/posts',
        breadcrumbName: '内容列表',
    },
]

export const getServerSideProps: GetServerSideProps = async (context: any) => {
    const userInfo: UserInfo | null = await CheckSignInStatus(context)
    if (!userInfo) {
        return {
            redirect: {
                destination: '/auth/signIn?redirect=' + context.req.url,
                permanent: false,
            },
        }
    }
    const accessToken = nookies.get(context)['accessToken']
    let postCatOptions: AntdSelectOption[] = []
    const resultPostCategory: APIResult<ListData<PostCategory>> = await api.queryPostCategoryListUseInner(accessToken, convertToHeaders(context.req.headers, context.req.socket.remoteAddress),
        undefined, undefined, undefined, 1, 2147483647)
    if (resultPostCategory.code == 401) {
        return {
            redirect: {
                destination: '/auth/signIn?redirect=' + context.req.url,
                permanent: false,
            },
        }
    }
    if (resultPostCategory.code == 200 && resultPostCategory.data) {
        for (let i = 0; i < resultPostCategory.data.data.length; i++) {
            let option: AntdSelectOption = {
                label: resultPostCategory.data.data[i].zhName,
                value: resultPostCategory.data.data[i].id.toString(),
            }
            postCatOptions.push(option)
        }
    }

    return {
        props: {
            data: {
                userInfo: userInfo,
                postCatOptions: postCatOptions,
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
                <p>此处为CMS内容管理系统。</p>
                <p>状态：</p>
                <ol>
                    <li>发布：正在公开的内容</li>
                    <li>修订：历史版本，仅后台可见/只读</li>
                    <li>下线：内容被撤回，仅后台可见，重新编辑保存（开启审核流程进入审核，否则为发布状态）</li>
                    <li>审核：审核流程中转中，仅后台可见</li>
                    <li>删除：删除状态，仅后台可见</li>
                </ol>
                <p>版本号：</p>
                <ul>
                    <li>出于安全管理要求，操作留痕，数据每次操作、修改，版本号会自增</li>
                </ul>
                <p>发布时间：</p>
                <ul>
                    <li>您可以设置在未来的时间自动发布内容，在发布时间未到时，仅后台可见</li>
                </ul>
            </div>
        ),
        onOk() {
        },
    })
}

const DashboardCmsPosts = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
    const [postList, setPostList] = useState<DashPost[]>()
    const [inited, setInited] = useState<boolean>(false)
    const [loading, setLoading] = useState(false)
    const [pagination, setPagination] = useState<TablePaginationConfig>({
        current: 1,
        pageSize: 10,
    })

    type QueryCriteria = {
        categoryId?: number, title?: string, postStatus?: string, startDate?: string,
        endDate?: string, pagination: TablePaginationConfig
    }

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
        let listData: APIResult<ListData<DashPost>> = await queryPostList(
            params.categoryId, params.title, params.postStatus, params.startDate,
            params.endDate, params.pagination.current, params.pagination.pageSize
        )
        setPostList(listData.data?.data)
        setLoading(false)
        setPagination({
            ...params.pagination,
            total: listData.data?.total,
            // 200 is mock data, you should read it from server
            // total: data.totalCount,
        })
    }

    const AdvancedSearchForm = (props: any) => {
        const [expand, setExpand] = useState(false)
        const [form] = Form.useForm()

        const getFields = () => {
            const children = []
            children.push(
                <Col span={6} key="categoryId">
                    <Form.Item
                        name="categoryId"
                        label="内容分类"
                    >
                        <Select
                            options={props.postCatOptions}
                        >
                        </Select>
                    </Form.Item>
                </Col>,
                <Col span={6} key="title">
                    <Form.Item
                        name="title"
                        label="内容标题"
                    >
                        <Input />
                    </Form.Item>
                </Col>,
                <Col span={6} key="postStatus">
                    <Form.Item
                        name="postStatus"
                        label="内容状态"
                    >
                        <Select
                            options={getPostStatusList()}
                        >
                        </Select>
                    </Form.Item>
                </Col>,
            )
            if (expand) {
                children.push(
                    <Col span={6}></Col>,
                    <Col span={6} key="startDate">
                        <Form.Item
                            name="startDate"
                            label="起始时间"
                        >
                            <DatePicker
                                style={{ width: '100%' }}
                                showTime
                                format="yyyy-MM-DD HH:mm:ss" />
                        </Form.Item>
                    </Col>, <Col span={6} key="endDate">
                    <Form.Item
                        name="endDate"
                        label="结束时间"
                    >
                        <DatePicker
                            style={{ width: '100%' }}
                            showTime
                            format="yyyy-MM-DD HH:mm:ss" />
                    </Form.Item>
                </Col>,
                    <Col span={6}></Col>,
                )
            }
            return children
        }

        const advancedSearch = async (values: any) => {
            let params: QueryCriteria = {
                categoryId: values.categoryId,
                endDate: values.endDate ? moment(values.endDate).format('yyyy-MM-DD HH:mm:ss') : undefined,
                pagination: pagination,
                postStatus: values.postStatus,
                startDate: values.startDate ? moment(values.startDate).format('yyyy-MM-DD HH:mm:ss') : undefined,
                title: values.title
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

    return (
        <>
            <Head>
                <title>内容列表 - CMS 内容管理系统 (Content Management System)</title>
            </Head>

            <div style={{ backgroundColor: '#fff' }}>
                <DashPageHeader
                    title="内容列表"
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
                            <Title level={5}>内容管理列表</Title>
                        </Col>
                        <Col span={12} style={{ textAlign: 'right', paddingBottom: '10px' }}>
                            <Space>
                                <Button
                                    onClick={helpModal}
                                    icon={<QuestionCircleOutlined />}>
                                    帮助说明
                                </Button>
                                <Button
                                    href="/dashboard/cms/posts/new"
                                    type="primary"
                                    icon={<PlusOutlined />}>
                                    新建
                                </Button>
                            </Space>
                        </Col>
                    </Row>
                    <Table
                        columns={columns}
                        scroll={{ x: true }}
                        rowKey={record => record.id}
                        dataSource={postList}
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