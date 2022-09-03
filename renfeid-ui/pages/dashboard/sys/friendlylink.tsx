import Head from 'next/head'
import React, { useEffect, useState } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Col, Row, Button, Typography, Table, Space, Form, Input, Select, DatePicker, Modal, Descriptions, message, InputNumber } from 'antd'
import {
  DeleteOutlined,
  RedoOutlined,
  PlusOutlined,
  EditOutlined,
  QuestionCircleOutlined
} from '@ant-design/icons'
import type { ColumnsType, TablePaginationConfig } from 'antd/lib/table'
import DashboardLayout from "../../../components/layout/dashboard"
import DashPageHeader from "../../../components/layout/dashboard/DashPageHeader"
import * as api from "../../../services/api/dashboard/api"
import APIResult = API.APIResult
import UserInfo = API.UserInfo
import CoreSiteFriendlyLink = API.CoreSiteFriendlyLink
import CheckSignInStatus from '../../../utils/CheckSignInStatus'

const { Title } = Typography

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
    path: '/dashboard/sys/friendlylink',
    breadcrumbName: '友情链接管理',
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
        <p>友情链接可管理站点页面底部的友情链接列表。</p>
        <p>详细说明：</p>
        <ol>
          <li>站点名称：友情链接显示的文字。</li>
          <li>站点链接：友情链接的跳转地址。</li>
          <li>审核通过：支持友情链接自助申请，只有审核通过的才会显示。</li>
          <li>交换类型：对等交换为首页友情链接交换；交叉交换为只在特定友情链接页面才显示。</li>
          <li>对方链接位置：交叉交换时记录对方的友情链接展示地址。</li>
          <li>联系人姓名：对方的联系人。</li>
          <li>联系人邮箱：对方的联系邮箱。</li>
          <li>联系人QQ：对方的QQ号码。</li>
          <li>备注：可以填写你想记录的任何内容。</li>
          <li>排序：排序为升序，数字越小越靠前显示。</li>
        </ol>
        <p>如果您不明白其中的含义，请咨询软件厂商服务人员。</p>
      </div>
    ),
    onOk() {
    },
  })
}

const DashboardSystemFriendlyLink = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  const [postList, setPostList] = useState<CoreSiteFriendlyLink[]>()
  const [inited, setInited] = useState<boolean>(false)
  const [loading, setLoading] = useState(false)
  const [modelForm] = Form.useForm()
  const [visibleModel, setVisibleModel] = useState(false)
  const [modelLoading, setModelLoading] = useState(false)

  useEffect(() => {
    fetchData()
  })

  const fetchData = async () => {
    if (!inited) {
      await queryData()
      setInited(true)
    }
  }

  const queryData = async () => {
    setLoading(true)
    let listData: APIResult<CoreSiteFriendlyLink[]> = await api.queryAllFriendlyLink()
    setPostList(listData.data)
    setLoading(false)
  }

  const columns: ColumnsType<CoreSiteFriendlyLink> = [
    {
      title: '站点名称',
      width: 200,
      dataIndex: 'sitename',
    },
    {
      title: '站点链接',
      dataIndex: 'sitelink',
    },
    {
      title: '审核',
      dataIndex: 'auditPass',
      width: 60,
      render: auditPass => {
        return auditPass ? '通过' : '拒绝'
      },
    },
    {
      title: '交换类型',
      width: 100,
      dataIndex: 'linkType',
      render: linkType => {
        return linkType == 1 ? '对等交换' : '交叉交换'
      },
    },
    {
      title: '排序',
      width: 60,
      dataIndex: 'orderId',
    },
    {
      title: '联系人',
      width: 100,
      dataIndex: 'contactName',
    },
    {
      title: '联系邮箱',
      width: 180,
      dataIndex: 'contactEmail',
    },
    {
      title: '联系QQ',
      width: 120,
      dataIndex: 'contactQq',
    },
    {
      title: '备注',
      width: 180,
      dataIndex: 'remarks',
    },
    {
      title: '操作',
      dataIndex: 'id',
      key: 'x',
      width: 60,
      fixed: 'right',
      render: (_: any, record: CoreSiteFriendlyLink) => {
        return (
          <Space>
            <Button icon={<EditOutlined />} onClick={async () => {
              modelForm.setFieldsValue({
                sitename: record.sitename,
                sitelink: record.sitelink,
                contactName: record.contactName,
                remarks: record.remarks,
                id: record.id,
                isDelete: record.isDelete,
                addtime: record.addtime,
                auditPass: record.auditPass + '',
                linkType: record.linkType + '',
                contactEmail: record.contactEmail,
                contactQq: record.contactQq,
                orderId: record.orderId,
              })
              setVisibleModel(true)
            }} >编辑</Button>
            <Button type="primary" icon={<DeleteOutlined />} onClick={async () => {
              const res: APIResult<any> = await api.deleteFriendlyLink(record.id)
              if (res.code == 200) {
                queryData()
              } else {
                message.error(res.message)
              }
            }} danger>删除</Button>
          </Space>
        )
      },
    },
  ]

  const handleOk = async () => {
    setModelLoading(true)
    if (!modelForm.getFieldValue('sitename')) {
      message.error('请填站点名称')
      setModelLoading(false)
      return
    }
    if (!modelForm.getFieldValue('sitelink')) {
      message.error('请填站点链接')
      setModelLoading(false)
      return
    }
    const friendlyLink: CoreSiteFriendlyLink = {
      sitename: modelForm.getFieldValue('sitename'),
      sitelink: modelForm.getFieldValue('sitelink'),
      contactName: modelForm.getFieldValue('contactName'),
      remarks: modelForm.getFieldValue('remarks'),
      id: modelForm.getFieldValue('id'),
      isDelete: modelForm.getFieldValue('isDelete'),
      addtime: modelForm.getFieldValue('addtime'),
      auditPass: modelForm.getFieldValue('auditPass'),
      linkType: modelForm.getFieldValue('linkType'),
      contactEmail: modelForm.getFieldValue('contactEmail'),
      contactQq: modelForm.getFieldValue('contactQq'),
      orderId: modelForm.getFieldValue('orderId'),
    }
    let res: APIResult<any>
    if (friendlyLink.id == '-1') {
      res = await api.createFriendlyLink(friendlyLink)
    } else {
      res = await api.updateFriendlyLink(friendlyLink.id, friendlyLink)
    }
    if (res.code == 200) {
      queryData()
      setVisibleModel(false)
    } else {
      message.error(res.message)
    }
    setModelLoading(false)
  }

  const handleCancel = () => {
    setVisibleModel(false)
    setModelLoading(false)
  }

  return (
    <>
      <Head>
        <title>友情链接管理 - 网站友情链接管理</title>
      </Head>

      <div style={{ backgroundColor: '#fff' }}>
        <DashPageHeader
          title="友情链接管理"
          routes={routes}
          subTitle="网站友情链接管理"
        />
      </div>

      <div style={{ padding: '23px' }}>
        <div style={{ backgroundColor: '#fff', padding: '24px', marginBottom: '16px' }}>
          <Row>
            <Col span={12}>
              <Title level={5}>友情链接</Title>
            </Col>
            <Col span={12} style={{ textAlign: 'right', paddingBottom: '10px' }}>
              <Space>
                <Button
                  onClick={helpModal}
                  icon={<QuestionCircleOutlined />}>
                  帮助说明
                </Button>
                <Button
                  icon={<RedoOutlined />}
                  onClick={() => { queryData() }}
                >
                  刷新
                </Button>
                <Button
                  type="primary"
                  icon={<PlusOutlined />}
                  onClick={() => {
                    modelForm.setFieldsValue({
                      sitename: '',
                      sitelink: '',
                      contactName: '',
                      remarks: '',
                      id: '-1',
                      isDelete: 'false',
                      addtime: "2022-09-03 18:42:29",
                      auditPass: 'true',
                      linkType: '1',
                      contactEmail: '',
                      contactQq: '',
                      orderId: 0,
                    })
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
            scroll={{ x: true }}
            rowKey={record => record.sitename}
            dataSource={postList}
            loading={loading}
          />
        </div>
      </div>
      <Modal
        title="友情链接"
        visible={visibleModel}
        confirmLoading={modelLoading}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <Form
          form={modelForm}
          layout={'vertical'}
        >
          <Form.Item name="sitename" label="站点名称：">
            <Input />
          </Form.Item>
          <Form.Item name="sitelink" label="站点链接：">
            <Input />
          </Form.Item>
          <Form.Item name="orderId" label="排序：">
            <InputNumber min={0} />
          </Form.Item>
          <Form.Item name="auditPass" label="审核：">
            <Select>
              <Select.Option value="true">通过</Select.Option>
              <Select.Option value="false">拒绝</Select.Option>
            </Select>
          </Form.Item>
          <Form.Item name="linkType" label="交换类型：">
            <Select>
              <Select.Option value="1">对等交换</Select.Option>
              <Select.Option value="2">交叉交换</Select.Option>
            </Select>
          </Form.Item>
          <Form.Item name="inSiteLink" label="对方链接位置：">
            <Input />
          </Form.Item>
          <Form.Item name="contactName" label="联系人：">
            <Input />
          </Form.Item>
          <Form.Item name="contactEmail" label="联系邮箱：">
            <Input />
          </Form.Item>
          <Form.Item name="contactQq" label="联系QQ：">
            <Input />
          </Form.Item>
          <Form.Item name="remarks" label="备注：">
            <Input.TextArea rows={3} />
          </Form.Item>
        </Form>
      </Modal>
    </>
  )
}

DashboardSystemFriendlyLink.getLayout = (page: any) => {
  return (
    <DashboardLayout>
      {page}
    </DashboardLayout>
  )
}

export default DashboardSystemFriendlyLink