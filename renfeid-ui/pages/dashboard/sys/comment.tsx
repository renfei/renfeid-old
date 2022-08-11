import Head from 'next/head'
import Link from 'next/link'
import nookies from 'nookies'
import React, { useEffect, useState } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Col, Row, Button, Typography, Table, Space, Form, Input, Select, DatePicker, Modal, Descriptions, message } from 'antd'
import {
  EyeOutlined,
  DeleteOutlined,
  QuestionCircleOutlined
} from '@ant-design/icons'
import type { ColumnsType, TablePaginationConfig } from 'antd/lib/table'
import DashboardLayout from "../../../components/layout/dashboard"
import DashPageHeader from "../../../components/layout/dashboard/DashPageHeader"
import * as api from "../../../services/api/dashboard/api"
import APIResult = API.APIResult
import ListData = API.ListData
import ReplyCommentAo = API.ReplyCommentAo
import { queryCommentList } from "../../../services/api/dashboard/api"
import Comment = API.Comment
import UserInfo = API.UserInfo
import CheckSignInStatus from '../../../utils/CheckSignInStatus'
import { getSysTypeUrl } from '../../../utils/SystemType'

const { Title } = Typography

const routes = [
  {
    path: '/dashboard',
    breadcrumbName: '控制面板',
  },
  {
    path: 'javascript:void(0)',
    breadcrumbName: '系统设置',
  },
  {
    path: '/dashboard/sys/comment',
    breadcrumbName: '评论管理',
  },
]

export const getServerSideProps: GetServerSideProps = async (context: any) => {
  const userInfo: UserInfo | undefined = await CheckSignInStatus(context)
  if (!userInfo) {
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
        userInfo: userInfo,
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
        <p>评论系统支持任何系统模块的评论。</p>
        <p>您可以通过删除、回复等操作来管理评论。</p>
      </div>
    ),
    onOk() {
    },
  })
}

const detailModal = (record: Comment, form: any) => {
  form.setFieldsValue({
    id: record.id,
    content: ''
  })
  Modal.info({
    width: '95%',
    icon: (<></>),
    centered: true,
    content: (
      <>
        <Descriptions title="评论详情" bordered>
          <Descriptions.Item label="系统模块">{record.sysType}</Descriptions.Item>
          <Descriptions.Item label="评论对象"><Link href={getSysTypeUrl(record.sysType) + record.objectId}><a target={'_blank'}>{getSysTypeUrl(record.sysType) + record.objectId}</a></Link></Descriptions.Item>
          <Descriptions.Item label="评论时间">{record.addtime}</Descriptions.Item>
          <Descriptions.Item label="是否删除">{record.isDelete ? '是' : '否'}</Descriptions.Item>
          <Descriptions.Item label="官方回复">{record.isOwner ? '是' : '否'}</Descriptions.Item>
          <Descriptions.Item label="作者名称">{record.author}</Descriptions.Item>
          <Descriptions.Item label="作者邮箱">{record.authorEmail}</Descriptions.Item>
          <Descriptions.Item label="作者链接" span={2}>{record.authorUrl}</Descriptions.Item>
          <Descriptions.Item label="作者IP">{record.authorIp}</Descriptions.Item>
          <Descriptions.Item label="作者地址" span={2}>{record.authorAddress}</Descriptions.Item>
          <Descriptions.Item label="评论内容" span={3}>{record.content}</Descriptions.Item>
        </Descriptions>
        <Form
          form={form}
          style={{ paddingTop: '20px' }}>
          <Form.Item
            style={{ display: 'none' }}
            name="id"
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="回复内容"
            name="content"
          >
            <Input.TextArea rows={4} />
          </Form.Item>
        </Form>
      </>
    ),
    async onOk() {
      let replyComment: ReplyCommentAo = {
        content: form.getFieldValue('content')
      }
      const result: APIResult<any> = await api.replyComment(form.getFieldValue('id'), replyComment)
      if (result.code == 200) {
        message.success('成功')
      } else {
        message.error(result.message)
      }
    },
  })
}

const DashboardCommentPosts = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  const [form] = Form.useForm()
  const [postList, setPostList] = useState<Comment[]>()
  const [inited, setInited] = useState<boolean>(false)
  const [loading, setLoading] = useState(false)
  const [pagination, setPagination] = useState<TablePaginationConfig>({
    current: 1,
    pageSize: 10,
  })

  type QueryCriteria = {
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
    let listData: APIResult<ListData<Comment>> = await queryCommentList(
      true, params.pagination.current, params.pagination.pageSize
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

  const columns: ColumnsType<Comment> = [
    {
      title: '评论时间',
      width: 165,
      dataIndex: 'addtime',
    },
    {
      title: '系统模块',
      dataIndex: 'sysType',
      width: 90,
    },
    {
      title: '评论对象',
      width: 10,
      dataIndex: 'objectId',
      render: (_: any, record: Comment) => {
        return (<Link href={getSysTypeUrl(record.sysType) + record.objectId}><a target={'_blank'}>{getSysTypeUrl(record.sysType) + record.objectId}</a></Link>)
      },
    },
    {
      title: '是否删除',
      width: 90,
      dataIndex: 'isDelete',
      render: (_: boolean, record: Comment) => {
        return (
          _ ? '是' : '否'
        )
      },
    },
    {
      title: '官方回复',
      width: 90,
      dataIndex: 'isOwner',
      render: (_: boolean, record: Comment) => {
        return (
          _ ? '是' : '否'
        )
      },
    },
    {
      title: '作者名称',
      width: 120,
      dataIndex: 'author',
    },
    {
      title: '作者邮箱',
      width: 150,
      dataIndex: 'authorEmail',
    },
    {
      title: 'IP地址',
      width: 100,
      dataIndex: 'authorIp',
    },
    {
      title: '作者地址',
      width: 180,
      dataIndex: 'authorAddress',
    },
    {
      title: '评论内容',
      dataIndex: 'content',
    },
    {
      title: '查看并回复',
      dataIndex: 'id',
      key: 'x',
      width: 60,
      fixed: 'right',
      render: (_: any, record: Comment) => {
        return (
          <Space>
            <Button type="primary" icon={<EyeOutlined />} onClick={() => { detailModal(record, form) }}>查看并回复</Button>
            <Button type="primary" danger icon={<DeleteOutlined />} onClick={async () => {
              if (confirm('确认删除吗？')) {
                // TODO
                const result: APIResult<any> = await api.deleteComment(record.id)
                if (result.code == 200) {
                  fetchData({ pagination })
                } else {
                  message.error(result.message)
                }
              }
            }}>删除</Button>
          </Space>
        )
      },
    },
  ]

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
        <title>评论管理 - 用户评论管理与回复</title>
      </Head>

      <div style={{ backgroundColor: '#fff' }}>
        <DashPageHeader
          title="评论管理"
          routes={routes}
          subTitle="用户评论管理与回复"
        />
      </div>

      <div style={{ padding: '23px' }}>
        <div style={{ backgroundColor: '#fff', padding: '24px', marginBottom: '16px' }}>
          <Row>
            <Col span={12}>
              <Title level={5}>评论管理</Title>
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

DashboardCommentPosts.getLayout = (page: any) => {
  return (
    <DashboardLayout>
      {page}
    </DashboardLayout>
  )
}

export default DashboardCommentPosts