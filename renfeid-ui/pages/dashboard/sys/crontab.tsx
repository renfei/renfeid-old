import Head from 'next/head'
import nookies from 'nookies'
import React, { useEffect, useState } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Col, Row, Button, Typography, Table, Space, Form, Input, Select, DatePicker, Modal, Descriptions, message } from 'antd'
import {
  CheckOutlined,
  PauseOutlined,
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
import CronJobVo = API.CronJobVo
import UserInfo = API.UserInfo
import CheckSignInStatus from '../../../utils/CheckSignInStatus'

const { Title } = Typography

const switchState = (state: string) => {
  switch (state) {
    case 'NONE':
      return '无'
    case 'NORMAL':
      return '正常'
    case 'PAUSED':
      return '暂停'
    case 'COMPLETE':
      return '完成'
    case 'ERROR':
      return '错误'
    case 'BLOCKED':
      return '阻塞'
    default:
      return state
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
    path: '/dashboard/sys/crontab',
    breadcrumbName: '定时任务',
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
        <p>系统定时任务的配置由专业的运维人员进行配置，普通用户禁止操作。</p>
        <p>详细说明：</p>
        <ol>
          <li>任务名称：任务名不可重复。</li>
          <li>任务组名：group 是用于分类的，相当于一个命名空间。</li>
          <li>任务实现类名：实现了org.quartz.Job接口的类名，例如：net.renfei.common.core.jobs.ExampleJob</li>
          <li>Cron表达式：设置具体执行时间或执行周期，Cron表达式是一个字符串。</li>
        </ol>
        <p>如果您不明白其中的含义，请咨询软件厂商服务人员。</p>
      </div>
    ),
    onOk() {
    },
  })
}

const DashboardSystemCrontab = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  const [postList, setPostList] = useState<CronJobVo[]>()
  const [inited, setInited] = useState<boolean>(false)
  const [loading, setLoading] = useState(false)
  const [modelForm] = Form.useForm()
  const [detailModalForm] = Form.useForm()
  const [visibleModel, setVisibleModel] = useState(false)
  const [detailModalVisibleModel, setDetailModalVisibleModel] = useState(false)
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
    let listData: APIResult<CronJobVo[]> = await api.queryJobList()
    setPostList(listData.data)
    setLoading(false)
  }

  const columns: ColumnsType<CronJobVo> = [
    {
      title: '任务名称',
      width: 200,
      dataIndex: 'jobName',
    },
    {
      title: '状态',
      dataIndex: 'state',
      width: 60,
      render: post => {
        return switchState(post)
      },
    },
    {
      title: '任务组名',
      width: 180,
      dataIndex: 'groupName',
    },
    {
      title: '定时表达式',
      width: 190,
      dataIndex: 'cronExpression',
    },
    {
      title: '任务实现类名',
      dataIndex: 'className',
    },
    {
      title: '下一次执行时间',
      width: 180,
      dataIndex: 'nextFireTime',
    },
    {
      title: '操作',
      dataIndex: 'id',
      key: 'x',
      width: 60,
      fixed: 'right',
      render: (_: any, record: CronJobVo) => {
        return (
          <Space>

            {
              record.state == 'PAUSED' ? (
                <Button icon={<CheckOutlined />} onClick={async () => {
                  const res: APIResult<any> = await api.resumeJob(record.jobName, record.groupName)
                  if (res.code == 200) {
                    queryData()
                  } else {
                    message.error(res.message)
                  }
                }}>恢复</Button>
              ) : record.state == 'NORMAL' ? (
                <Button icon={<PauseOutlined />} onClick={async () => {
                  const res: APIResult<any> = await api.pauseJob(record.jobName, record.groupName)
                  if (res.code == 200) {
                    queryData()
                  } else {
                    message.error(res.message)
                  }
                }} danger>暂停</Button>
              ) : ''
            }
            <Button type="primary" icon={<EditOutlined />} onClick={() => {
              detailModalForm.setFieldsValue({
                jobName: record.jobName,
                groupName: record.groupName,
                cronExpression: record.cronExpression,
              })
              setDetailModalVisibleModel(true)
            }}>重排</Button>
            <Button type="primary" icon={<DeleteOutlined />} onClick={async () => {
              const res: APIResult<any> = await api.deleteJob(record.jobName, record.groupName)
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
    if (!modelForm.getFieldValue('jobGroup')) {
      message.error('请填写任务组名')
      setModelLoading(false)
      return
    }
    if (!modelForm.getFieldValue('jobName')) {
      message.error('请填写任务名称')
      setModelLoading(false)
      return
    }
    // 新增
    const res: APIResult<any> = await api.createJob(modelForm.getFieldValue('jobName'), modelForm.getFieldValue('jobGroup'), {
      className: modelForm.getFieldValue('className'),
      cronExpression: modelForm.getFieldValue('cronExpression'),
      param: modelForm.getFieldValue('param') ? modelForm.getFieldValue('param') : {}
    })
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

  const rescheduleJob = async () => {
    if (!detailModalForm.getFieldValue('cronExpression')) {
      message.error('请填写Cron表达式')
      return
    }
    const res: APIResult<any> = await api.rescheduleJob(detailModalForm.getFieldValue('jobName'),
      detailModalForm.getFieldValue('groupName'), detailModalForm.getFieldValue('cronExpression'))
    if (res.code == 200) {
      queryData()
      setDetailModalVisibleModel(false)
    } else {
      message.error(res.message)
    }
  }

  return (
    <>
      <Head>
        <title>系统定时任务 - 分布式定时任务</title>
      </Head>

      <div style={{ backgroundColor: '#fff' }}>
        <DashPageHeader
          title="系统定时任务"
          routes={routes}
          subTitle="分布式定时任务"
        />
      </div>

      <div style={{ padding: '23px' }}>
        <div style={{ backgroundColor: '#fff', padding: '24px', marginBottom: '16px' }}>
          <Row>
            <Col span={12}>
              <Title level={5}>系统定时任务</Title>
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
                      jobGroup: '',
                      jobName: '',
                      className: '',
                      cronExpression: '',
                      param: '',
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
            rowKey={record => record.jobName}
            dataSource={postList}
            loading={loading}
          />
        </div>
      </div>
      <Modal
        title="系统定时任务"
        visible={visibleModel}
        confirmLoading={modelLoading}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <Form
          form={modelForm}
          layout={'vertical'}
        >
          <Form.Item name="jobGroup" label="任务组名：">
            <Input />
          </Form.Item>
          <Form.Item name="jobName" label="任务名称：">
            <Input />
          </Form.Item>
          <Form.Item name="className" label="实现类名：">
            <Input placeholder='例如：net.renfei.common.core.jobs.ExampleJob' />
          </Form.Item>
          <Form.Item name="cronExpression" label="Cron表达式：">
            <Input placeholder='例如：0 0 3 1/1 * ?' />
          </Form.Item>
          <Form.Item name="param" label="初始化参数：">
            <Input.TextArea rows={3} placeholder='例如：{"name":"zhangsan","age":"18"}' />
          </Form.Item>
        </Form>
      </Modal>
      <Modal
        title="任务重排"
        visible={detailModalVisibleModel}
        onOk={rescheduleJob}
        onCancel={() => {
          setDetailModalVisibleModel(false)
        }}
      >
        <Form
          form={detailModalForm}
        >
          <Form.Item label="Cron 表达式" name="cronExpression">
            <Input />
          </Form.Item>
        </Form>
      </Modal>
    </>
  )
}

DashboardSystemCrontab.getLayout = (page: any) => {
  return (
    <DashboardLayout>
      {page}
    </DashboardLayout>
  )
}

export default DashboardSystemCrontab