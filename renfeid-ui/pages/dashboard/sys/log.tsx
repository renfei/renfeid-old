import Head from 'next/head'
import nookies from 'nookies'
import moment from 'moment'
import React, { useEffect, useState } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Col, Row, Button, Typography, Table, Space, Form, Input, Select, DatePicker, Modal, Descriptions } from 'antd'
import {
    DownOutlined,
    UpOutlined,
    SearchOutlined,
    ReloadOutlined,
    EyeOutlined,
    QuestionCircleOutlined
} from '@ant-design/icons'
import type { ColumnsType, TablePaginationConfig } from 'antd/lib/table'
import DashboardLayout from "../../../components/layout/dashboard"
import DashPageHeader from "../../../components/layout/dashboard/DashPageHeader"
import * as api from "../../../services/api/dashboard/api"
import { convertToHeaders } from "../../../utils/request"
import AntdSelectOption = API.AntdSelectOption
import APIResult = API.APIResult
import ListData = API.ListData
import PostCategory = API.PostCategory
import { getPostStatusList, switchPostStatus } from "../../../utils/posts"
import Params = API.TableListParams
import { querySystemLog } from "../../../services/api/dashboard/api"
import SystemLogEntity = API.SystemLogEntity
import UserInfo = API.UserInfo
import CheckSignInStatus from '../../../utils/CheckSignInStatus'

const { Title } = Typography

const columns: ColumnsType<SystemLogEntity> = [
    {
        title: '时间',
        width: 165,
        dataIndex: 'logTime',
    },
    {
        title: '等级',
        dataIndex: 'logLevel',
        width: 60,
        render: post => {
            return switchLogLevel(post)
        },
    },
    {
        title: '系统模块',
        width: 80,
        dataIndex: 'logModule',
    },
    {
        title: '操作类型',
        width: 75,
        dataIndex: 'logType',
        render: post => {
            return switchLogType(post)
        },
    },
    {
        title: '请求方法',
        width: 90,
        dataIndex: 'requMethod',
    },
    {
        title: '请求地址',
        width: 165,
        dataIndex: 'requUri',
    },
    {
        title: '操作用户',
        width: 100,
        dataIndex: 'userName',
    },
    {
        title: 'IP地址',
        width: 100,
        dataIndex: 'requIp',
    },
    {
        title: '操作描述',
        dataIndex: 'logDesc',
    },
    {
        title: '操作',
        dataIndex: 'id',
        key: 'x',
        width: 60,
        fixed: 'right',
        render: (_: any, record: SystemLogEntity) => {
            return (
                <Space>
                    <Button type="primary" icon={<EyeOutlined />} onClick={() => { detailModal(record) }}>查看详情</Button>
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

const switchLogType = (logType: string) => {
    switch (logType) {
        case 'CREATE':
            return '创建'
        case 'RETRIEVE':
            return '查询'
        case 'UPDATE':
            return '修改'
        case 'DELETE':
            return '删除'
        case 'SIGNIN':
            return '登录'
        case 'SIGNUP':
            return '注册'
        case 'SIGNOUT':
            return '登出'
        default:
            return logType
    }
}

const switchLogLevel = (logLevel: string) => {
    switch (logLevel) {
        case 'DEBUG':
            return '调试'
        case 'INFO':
            return '一般'
        case 'WARN':
            return '警告'
        case 'ERROR':
            return '错误'
        case 'FATAL':
            return '致命'
        default:
            return logLevel
    }
}

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
        path: '/dashboard/sys/log',
        breadcrumbName: '审计日志',
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
    const resultPostCategory: APIResult<ListData<PostCategory>> = await api.queryPostCategoryListUseInner(accessToken, convertToHeaders(context.req.headers),
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
                <p>系统审计日志记录了用户的操作记录、系统事件等日志信息。</p>
                <p>三员：</p>
                <ol>
                    <li>超级管理员：如果未禁用超管，超管可查看全部日志。</li>
                    <li>系统管理员：可查看除安全保密管理员、安全审计管理员之外的日志。</li>
                    <li>安全保密管理员：可查看除系统管理员之外的日志</li>
                    <li>安全审计管理员：仅能进行查询分析系统管理员和安全管理员的操作日志。</li>
                    <li>其他角色：可查看除三员以外的日志。</li>
                </ol>
                <p>日志数据量较多，查询可能较慢，请耐心等待。</p>
            </div>
        ),
        onOk() {
        },
    })
}

const detailModal = (record: SystemLogEntity) => {
    Modal.info({
        width: '95%',
        icon: (<></>),
        centered: true,
        content: (
            <Descriptions title="日志详情" bordered>
                <Descriptions.Item label="记录时间">{record.logTime}</Descriptions.Item>
                <Descriptions.Item label="事件等级">{switchLogLevel(record.logLevel)}</Descriptions.Item>
                <Descriptions.Item label="系统模块">{record.logModule}</Descriptions.Item>
                <Descriptions.Item label="事件类型">{switchLogType(record.logType)}</Descriptions.Item>
                <Descriptions.Item label="操作用户">{record.userName}</Descriptions.Item>
                <Descriptions.Item label="IP地址">{record.requIp}</Descriptions.Item>
                <Descriptions.Item label="请求方法">{record.requMethod}</Descriptions.Item>
                <Descriptions.Item label="请求地址" span={2}>{record.requUri}</Descriptions.Item>
                <Descriptions.Item label="来源地址" span={3}>{record.requReferrer}</Descriptions.Item>
                <Descriptions.Item label="事件描述" span={3}>{record.logDesc}</Descriptions.Item>
                <Descriptions.Item label="浏览器" span={3}>{record.requAgent}</Descriptions.Item>
                <Descriptions.Item label="请求参数" span={3}>{record.requParam}</Descriptions.Item>
                <Descriptions.Item label="响应内容" span={3}>{record.respParam}</Descriptions.Item>
            </Descriptions>
        ),
        onOk() {
        },
    })
}

const DashboardCmsPosts = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
    const [postList, setPostList] = useState<SystemLogEntity[]>()
    const [inited, setInited] = useState<boolean>(false)
    const [loading, setLoading] = useState(false)
    const [pagination, setPagination] = useState<TablePaginationConfig>({
        current: 1,
        pageSize: 10,
    })

    type QueryCriteria = {
        startDate?: string, endDate?: string, logLevel?: string,
        systemType?: string, operationType?: string,
        reqUri?: string, username?: string, reqIp?: string,
        pages?: number, rows?: number, pagination: TablePaginationConfig
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
        let listData: APIResult<ListData<SystemLogEntity>> = await querySystemLog(
            params.startDate, params.endDate, params.logLevel, params.systemType,
            params.operationType, params.reqUri, params.username, params.reqIp,
            params.pagination.current, params.pagination.pageSize
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
                </Col>,
                <Col span={6} key="endDate">
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
                <Col span={6} key="logLevel">
                    <Form.Item
                        name="logLevel"
                        label="事件等级"
                    >
                        <Select>
                            <Select.Option value="DEBUG">调试</Select.Option>
                            <Select.Option value="INFO">一般</Select.Option>
                            <Select.Option value="WARN">警告</Select.Option>
                            <Select.Option value="ERROR">错误</Select.Option>
                            <Select.Option value="FATAL">致命</Select.Option>
                        </Select>
                    </Form.Item>
                </Col>,
            )
            if (expand) {
                children.push(
                    <Col span={6}></Col>,
                    <Col span={6} key="username">
                        <Form.Item
                            name="username"
                            label="操作用户"
                        >
                            <Input />
                        </Form.Item>
                    </Col>,
                    <Col span={6} key="reqIp">
                        <Form.Item
                            name="reqIp"
                            label="IP地址"
                        >
                            <Input />
                        </Form.Item>
                    </Col>,
                    <Col span={6} key="reqUri">
                        <Form.Item
                            name="reqUri"
                            label="请求地址"
                        >
                            <Input />
                        </Form.Item>
                    </Col>,
                )
            }
            return children
        }

        const advancedSearch = async (values: any) => {
            let params: QueryCriteria = {
                startDate: values.startDate,
                endDate: values.endDate,
                logLevel: values.logLevel,
                systemType: values.systemType,
                operationType: values.operationType,
                reqUri: values.reqUri,
                username: values.username,
                reqIp: values.reqIp,
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
                <title>系统审计日志 - 用户操作记录与系统事件记录</title>
            </Head>

            <div style={{ backgroundColor: '#fff' }}>
                <DashPageHeader
                    title="系统审计日志"
                    routes={routes}
                    subTitle="用户操作记录与系统事件记录"
                />
            </div>

            <div style={{ padding: '23px' }}>
                <div style={{ backgroundColor: '#fff', padding: '24px 24px 0', marginBottom: '16px' }}>
                    <AdvancedSearchForm postCatOptions={data.postCatOptions} />
                </div>
                <div style={{ backgroundColor: '#fff', padding: '24px', marginBottom: '16px' }}>
                    <Row>
                        <Col span={12}>
                            <Title level={5}>审计日志</Title>
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