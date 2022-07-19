import Head from 'next/head'
import React, {useRef} from 'react'
import {Editor} from '@tinymce/tinymce-react'
import {Button, Form, Input, Row, Col, Select, DatePicker, Upload, Descriptions, Collapse, Table} from 'antd'
import DashboardLayout from "../../../../components/layout/dashboard"
import DashPageHeader from "../../../../components/layout/dashboard/DashPageHeader"

const {Option} = Select
const {TextArea} = Input
const {Panel} = Collapse
const {Column} = Table

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
    {
        path: '/dashboard/cms/posts/new',
        breadcrumbName: '内容编辑',
    },
]

interface DataType {
    key: React.Key
    firstName: string
    lastName: string
    age: number
    address: string
}

const historyData: DataType[] = [
    {
        key: '1',
        firstName: 'John',
        lastName: 'Brown',
        age: 32,
        address: 'New York No. 1 Lake Park',
    },
    {
        key: '2',
        firstName: 'Jim',
        lastName: 'Green',
        age: 42,
        address: 'London No. 1 Lake Park',
    },
]

const keyWordChildren: React.ReactNode[] = []
for (let i = 10; i < 36; i++) {
    keyWordChildren.push(<Option key={i.toString(36) + i}>{i.toString(36) + i}</Option>)
}

const DashboardCmsPostsNew = () => {
    const [form] = Form.useForm();
    const editorRef = useRef<any>(null);
    const log = () => {
        if (editorRef.current) {
            console.log(editorRef.current.getContent());
        }
    };
    return (
        <>
            <Head>
                <title>内容编辑 - CMS 内容管理系统 (Content Management System)</title>
            </Head>

            <div style={{backgroundColor: '#fff'}}>
                <DashPageHeader
                    title="内容编辑"
                    routes={routes}
                    subTitle="CMS 内容管理系统 (Content Management System)"
                />
            </div>

            <div style={{padding: '23px'}}>
                <div style={{backgroundColor: '#fff', padding: '24px 24px 12px', marginBottom: '16px'}}>
                    <Descriptions title="内容元信息">
                        <Descriptions.Item label="内容状态">Zhou</Descriptions.Item>
                        <Descriptions.Item label="内容浏览量">1810000000</Descriptions.Item>
                        <Descriptions.Item label="内容作者">Hangzhou, Zhejiang</Descriptions.Item>
                        <Descriptions.Item label="最后修改时间">Hangzhou, Zhejiang</Descriptions.Item>
                        <Descriptions.Item label="最后修改人">empty</Descriptions.Item>
                        <Descriptions.Item label="当前版本号">1</Descriptions.Item>
                        <Descriptions.Item label="内容点赞量">1</Descriptions.Item>
                        <Descriptions.Item label="内容点踩量">1</Descriptions.Item>
                    </Descriptions>
                    <Collapse accordion>
                        <Panel header="历史版本" key="1">
                            <Table dataSource={historyData} pagination={false}>
                                <Column title="内容标题" dataIndex="firstName" key="firstName"/>
                                <Column title="版本号" dataIndex="lastName" key="lastName"/>
                                <Column title="修改时间" dataIndex="age" key="age"/>
                                <Column title="修改人" dataIndex="address" key="address"/>
                                <Column
                                    title="操作"
                                    key="action"
                                    render={(_: any, record: DataType) => (
                                        <>
                                            <a>查看快照</a>
                                        </>
                                    )}
                                />
                            </Table>
                        </Panel>
                    </Collapse>
                </div>
                <div style={{backgroundColor: '#fff', padding: '24px 24px 0', marginBottom: '16px'}}>
                    <Form
                        form={form}
                        layout="horizontal"
                    >
                        <Row>
                            <Col span={24}>
                                <Form.Item label="内容标题">
                                    <Input/>
                                </Form.Item>
                            </Col>
                            <Col span={24}>
                                <Form.Item label="特色图像">
                                    <Upload
                                        action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                                        listType="picture"
                                        maxCount={1}
                                    >
                                        <Button>点击上传</Button>
                                    </Upload>
                                </Form.Item>
                            </Col>
                        </Row>
                        <Row>
                            <Col xs={24} sm={24} md={24} lg={9} xl={7} xxl={5}>
                                <Form.Item label="内容分类">
                                    <Select>
                                        <Option value="male">male</Option>
                                        <Option value="female">female</Option>
                                        <Option value="other">other</Option>
                                    </Select>
                                </Form.Item>
                            </Col>
                            <Col xs={24} sm={1} md={1} lg={1} xl={1} xxl={1}> </Col>
                            <Col xs={24} sm={24} md={24} lg={14} xl={16} xxl={18}>
                                <Form.Item label="关键词组">
                                    <Input.Group compact>
                                        <Select mode="tags" style={{width: 'calc(100% - 129px)'}}>
                                            {keyWordChildren}
                                        </Select>
                                        <Button>从正文自动提取</Button>
                                    </Input.Group>
                                </Form.Item>
                            </Col>
                        </Row>
                        <Row>
                            <Col xs={24} sm={24} md={24} lg={9} xl={7} xxl={5}>
                                <Form.Item label="保密等级">
                                    <Select defaultValue="UNCLASSIFIED">
                                        <Option value="UNCLASSIFIED">非密</Option>
                                        <Option value="INTERNAL">内部</Option>
                                        <Option value="SECRET">秘密</Option>
                                        <Option value="CONFIDENTIAL">机密</Option>
                                    </Select>
                                </Form.Item>
                            </Col>
                            <Col xs={24} sm={1} md={1} lg={1} xl={1} xxl={1}> </Col>
                            <Col xs={24} sm={24} md={24} lg={9} xl={7} xxl={5}>
                                <Form.Item label="评论状态">
                                    <Select defaultValue="OPENED">
                                        <Option value="OPENED">允许评论</Option>
                                        <Option value="CLOSED">禁止评论</Option>
                                    </Select>
                                </Form.Item>
                            </Col>
                            <Col xs={24} sm={1} md={1} lg={24} xl={1} xxl={1}> </Col>
                            <Col xs={24} sm={24} md={24} lg={9} xl={8} xxl={12}>
                                <Form.Item label="密码保护">
                                    <Input/>
                                </Form.Item>
                            </Col>
                        </Row>
                        <Row>
                            <Col xs={24} sm={24} md={24} lg={9} xl={7} xxl={5}>
                                <Form.Item label="发布时间">
                                    <DatePicker
                                        style={{width: '100%'}}
                                        showTime
                                        format="YYYY-MM-DD HH:mm:ss"/>
                                </Form.Item>
                            </Col>
                            <Col xs={24} sm={1} md={1} lg={1} xl={1} xxl={1}> </Col>
                            <Col xs={24} sm={24} md={24} lg={14} xl={16} xxl={18}>
                                <Form.Item label="内容摘要">
                                    <TextArea rows={2}/>
                                </Form.Item>
                            </Col>
                        </Row>
                        <Row>
                            <Col xs={24} sm={24} md={24} lg={9} xl={7} xxl={5}>
                                <Form.Item label="是否原创">
                                    <Select defaultValue="true">
                                        <Option value="true">原创</Option>
                                        <Option value="false">转载</Option>
                                    </Select>
                                </Form.Item>
                            </Col>
                            <Col xs={24} sm={1} md={1} lg={1} xl={1} xxl={1}> </Col>
                            <Col xs={24} sm={24} md={24} lg={14} xl={7} xxl={5}>
                                <Form.Item label="原文作者">
                                    <Input/>
                                </Form.Item>
                            </Col>
                            <Col xs={24} sm={1} md={1} lg={24} xl={1} xxl={1}> </Col>
                            <Col xs={24} sm={24} md={24} lg={9} xl={8} xxl={12}>
                                <Form.Item label="原文链接">
                                    <Input/>
                                </Form.Item>
                            </Col>
                        </Row>
                    </Form>
                </div>
                <div style={{backgroundColor: '#fff', padding: '24px', marginBottom: '16px'}}>
                    <Editor
                        apiKey='your-api-key'
                        onInit={(evt, editor) => {
                            if (editorRef.current) {
                                editorRef.current = editor
                            }

                        }}
                        initialValue="<p>This is the initial content of the editor.</p>"
                        init={{
                            height: 650,
                            min_height: 400,
                            language: 'zh_CN',
                            menubar: true,
                            toolbar_mode: 'wrap',
                            automatic_uploads: false,
                            fontsize_formats: '12px 14px 16px 18px 24px 36px 48px 56px 72px',
                            plugins: [
                                'preview', 'importcss',
                                'searchreplace', 'autolink', 'autosave', 'save', 'directionality',
                                'visualblocks', 'visualchars', 'fullscreen', 'image',
                                'link', 'media', 'template', 'codesample', 'table',
                                'charmap', 'pagebreak', 'nonbreaking', 'anchor',
                                'insertdatetime', 'advlist', 'lists', 'wordcount',
                                'help', 'charmap', 'mentions', 'quickbars', 'emoticons'
                            ],
                            toolbar: 'undo redo | bold italic underline strikethrough | '
                                + 'fontfamily fontsize blocks | alignleft aligncenter '
                                + 'alignright alignjustify | outdent indent |  numlist '
                                + 'bullist | forecolor backcolor removeformat | pagebreak'
                                + ' | charmap emoticons | fullscreen  preview save print | '
                                + 'image media link anchor codesample | ltr rtl',
                        }}
                    />
                    <div style={{paddingTop: '24px', textAlign: 'right'}}>
                        <Button type="primary" onClick={log}>保存</Button>
                    </div>
                </div>
            </div>
        </>
    )
}

DashboardCmsPostsNew.getLayout = (page: any) => {
    return (
        <DashboardLayout>
            {page}
        </DashboardLayout>
    )
}

export default DashboardCmsPostsNew