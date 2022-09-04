import Head from 'next/head'
import nookies from 'nookies'
import React, { useEffect, useState } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Col, Row, Button, Typography, Alert, Space, Form, Input, Select, DatePicker, Modal, Descriptions, message } from 'antd'
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
import SystemSettingVo = API.SystemSettingVo
import UserInfo = API.UserInfo
import CheckSignInStatus from '../../../utils/CheckSignInStatus'
import { convertToHeaders } from '../../../utils/request'

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
    path: '/dashboard/sys/setting',
    breadcrumbName: '系统设置',
  },
]

export const getServerSideProps: GetServerSideProps = async (context: any) => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    // 预览模式
    let values: string[] = []
    values.push('OPENED')
    return {
      props: {
        data: {
          userInfo: null,
          SYSTEM_RUNNING_STATUS: {
            code: 200,
            message: 'OK',
            timestamp: new Date().valueOf(),
            signature: '',
            nonce: '',
            data: {
              setting: 'SYSTEM_RUNNING_STATUS',
              values: values
            }
          },
          GLOBAL_COMMENT_STATUS: {
            code: 200,
            message: 'OK',
            timestamp: new Date().valueOf(),
            signature: '',
            nonce: '',
            data: {
              setting: 'GLOBAL_COMMENT_STATUS',
              values: values
            }
          },
        }
      }
    }
  } else {
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
    const SYSTEM_RUNNING_STATUS: APIResult<SystemSettingVo> = await api.querySystemSetting('SYSTEM_RUNNING_STATUS', accessToken, convertToHeaders(context.req.headers, context.req.socket.remoteAddress))
    const GLOBAL_COMMENT_STATUS: APIResult<SystemSettingVo> = await api.querySystemSetting('GLOBAL_COMMENT_STATUS', accessToken, convertToHeaders(context.req.headers, context.req.socket.remoteAddress))
    return {
      props: {
        data: {
          userInfo: userInfo,
          SYSTEM_RUNNING_STATUS: SYSTEM_RUNNING_STATUS.data ? SYSTEM_RUNNING_STATUS.data : null,
          GLOBAL_COMMENT_STATUS: GLOBAL_COMMENT_STATUS.data ? GLOBAL_COMMENT_STATUS.data : null,
        }
      }
    }
  }
}

const DashboardSystemCrontab = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  const [SYSTEM_RUNNING_STATUS_LOADING, setSYSTEM_RUNNING_STATUS_LOADING] = useState<boolean>(false)
  const [GLOBAL_COMMENT_STATUS_LOADING, setGLOBAL_COMMENT_STATUS_LOADING] = useState<boolean>(false)
  const [SYSTEM_RUNNING_STATUS, setSYSTEM_RUNNING_STATUS] = useState<SystemSettingVo | null>(data.SYSTEM_RUNNING_STATUS)
  const [GLOBAL_COMMENT_STATUS, setGLOBAL_COMMENT_STATUS] = useState<SystemSettingVo | null>(data.GLOBAL_COMMENT_STATUS)
  const [modelForm] = Form.useForm()

  modelForm.setFieldsValue({
    SYSTEM_RUNNING_STATUS: SYSTEM_RUNNING_STATUS ? SYSTEM_RUNNING_STATUS.values[0] : '',
    GLOBAL_COMMENT_STATUS: GLOBAL_COMMENT_STATUS ? GLOBAL_COMMENT_STATUS.values[0] : '',
  })

  return (
    <>
      <Head>
        <title>系统设置 - 系统全局设置</title>
      </Head>

      <div style={{ backgroundColor: '#fff' }}>
        <DashPageHeader
          title="系统设置"
          routes={routes}
          subTitle="系统全局设置"
        />
      </div>



      <div style={{ padding: '23px' }}>
        <Alert message="注意：此处设置为全局设置，请谨慎操作！；系统运行状态一旦设置为只读或关闭，此功能将无法修改，必须由运维工程师修改数据库状态！"
          type="warning" showIcon />
        <div style={{ backgroundColor: '#fff', padding: '24px', margin: '16px 0' }}>
          <Form
            form={modelForm}
          >
            <Row gutter={24}>
              <Col xs={24} sm={12} md={8} lg={6} xl={4} xxl={4}>
                <Form.Item
                  label="运行状态"
                  name="SYSTEM_RUNNING_STATUS"
                  help="逻辑关闭系统"
                  tooltip={(
                    <>
                      <ol>
                        <li>开放：完全开放状态</li>
                        <li>只读：只允许读取请求</li>
                        <li>关闭：拒绝任何外部请求</li>
                      </ol>
                      <p>软关闭系统，通过软件逻辑关闭系统，系统还在运行，只是拒绝处理外部的请求。</p>
                      <p>警告：一旦改为只读或关闭，开启只能去数据库中修改状态！</p>
                    </>
                  )}
                >
                  <Select
                    loading={SYSTEM_RUNNING_STATUS_LOADING}
                    onChange={(value, option) => {
                      setSYSTEM_RUNNING_STATUS_LOADING(true)
                      let values: string[] = []
                      values.push(value)
                      const SystemSettingVo: SystemSettingVo = {
                        setting: 'SYSTEM_RUNNING_STATUS',
                        values: values
                      }
                      api.updateSystemSetting("SYSTEM_RUNNING_STATUS", SystemSettingVo)
                        .then((result: APIResult<string>) => {
                          if (result.code == 200) {
                            setSYSTEM_RUNNING_STATUS({
                              setting: 'SYSTEM_RUNNING_STATUS',
                              values: values
                            })
                            modelForm.setFieldValue("SYSTEM_RUNNING_STATUS", value)
                            message.success('修改成功')
                          } else {
                            message.error(result.message)
                          }
                          setSYSTEM_RUNNING_STATUS_LOADING(false)
                        }).catch((error: any) => {
                          message.error('请求发生错误：' + error)
                          setSYSTEM_RUNNING_STATUS_LOADING(false)
                        })
                    }}
                  >
                    <Select.Option value="OPENED">开放</Select.Option>
                    <Select.Option value="READONLY">只读</Select.Option>
                    <Select.Option value="CLOSED">关闭</Select.Option>
                  </Select>
                </Form.Item>
              </Col>
              <Col xs={24} sm={12} md={8} lg={6} xl={4} xxl={4}>
                <Form.Item
                  label="评论状态"
                  name="GLOBAL_COMMENT_STATUS"
                  help="全局控制评论功能"
                  tooltip={(
                    <>
                      <ol>
                        <li>开放：允许评论</li>
                        <li>关闭：拒绝评论</li>
                      </ol>
                      <p>快速关闭或开启全站评论功能。</p>
                    </>
                  )}
                >
                  <Select
                    loading={GLOBAL_COMMENT_STATUS_LOADING}
                    onChange={(value, option) => {
                      setGLOBAL_COMMENT_STATUS_LOADING(true)
                      let values: string[] = []
                      values.push(value)
                      const SystemSettingVo: SystemSettingVo = {
                        setting: 'GLOBAL_COMMENT_STATUS',
                        values: values
                      }
                      api.updateSystemSetting("GLOBAL_COMMENT_STATUS", SystemSettingVo)
                        .then((result: APIResult<string>) => {
                          if (result.code == 200) {
                            setGLOBAL_COMMENT_STATUS({
                              setting: 'GLOBAL_COMMENT_STATUS',
                              values: values
                            })
                            modelForm.setFieldValue("GLOBAL_COMMENT_STATUS", value)
                            message.success('修改成功')
                          } else {
                            message.error(result.message)
                          }
                          setGLOBAL_COMMENT_STATUS_LOADING(false)
                        }).catch((error: any) => {
                          message.error('请求发生错误：' + error)
                          setGLOBAL_COMMENT_STATUS_LOADING(false)
                        })
                    }}
                  >
                    <Select.Option value="OPENED">开放</Select.Option>
                    <Select.Option value="CLOSED">关闭</Select.Option>
                  </Select>
                </Form.Item>
              </Col>
            </Row>
          </Form>
        </div>
      </div>
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