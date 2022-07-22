import Head from 'next/head'
import nookies from 'nookies'
import moment from 'moment'
import React, {useRef, useEffect} from 'react'
import {GetServerSideProps, InferGetServerSidePropsType} from 'next'
import {Editor} from '@tinymce/tinymce-react'
import {
    Button,
    Form,
    Input,
    Row,
    Col,
    Select,
    DatePicker,
    Upload,
    Descriptions,
    Collapse,
    Table,
    Result,
    message
} from 'antd'
import DashboardLayout from "../../../../components/layout/dashboard"
import DashPageHeader from "../../../../components/layout/dashboard/DashPageHeader"
import * as api from '../../../../services/api/dashboard/api'
import APIResult = API.APIResult
import DashPost = API.DashPost
import AntdSelectOption = API.AntdSelectOption
import PostCategory = API.PostCategory
import ListData = API.ListData;
import {updatePost} from "../../../../services/api/dashboard/api";
import {convertToHeaders} from "../../../../utils/request";

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
    let post: DashPost = {
        id: '-1',
        categoryId: '-1',
        postAuthor: '-1',
        postDate: '',
        postStatus: 'PUBLISH',
        postViews: 0,
        commentStatus: '',
        postPassword: '',
        postModified: '',
        postModifiedUser: '-1',
        postParent: -1,
        versionNumber: 1,
        thumbsUp: 0,
        thumbsDown: 0,
        avgViews: 0,
        avgComment: 0,
        pageRank: 0,
        secretLevel: '',
        isOriginal: true,
        featuredImage: '',
        postTitle: '',
        postKeyword: '',
        postExcerpt: '',
        postContent: '',
        sourceName: '',
        sourceUrl: '',
        authorUsername: '',
        modifiedUsername: '',
    }
    if (context.params.id && !isNaN(parseInt(context.params.id.toString()))) {
        const result: APIResult<DashPost> = await api.queryPostById(accessToken, context.req.headers, context.params.id.toString())
        console.info(result)
        if (result.code == 401) {
            return {
                redirect: {
                    destination: '/auth/signIn?redirect=' + context.req.url,
                    permanent: false,
                },
            }
        }
        if (result.code == 200 && result.data) {
            post = result.data
        } else {
            post.id = (0 - result.code).toString()
            post.postTitle = result.message
        }
    }

    let postCatOptions: AntdSelectOption[] = []
    const resultPostCategory: APIResult<ListData<PostCategory>> = await api.queryPostCategoryList(accessToken, convertToHeaders(context.req.headers),
        undefined, undefined, undefined, "1", "2147483647")
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
                post: post,
                postCatOptions: postCatOptions
            }
        }
    }
}

const switchPostStatus = (postStatus: string) => {
    switch (postStatus) {
        case 'PUBLISH':
            return '发布'
        case 'REVISION':
            return '修订'
        case 'DELETED':
            return '删除'
        case 'OFFLINE':
            return '下线'
        case 'REVIEW':
            return '审核'
        default:
            return postStatus
    }
}

const checkPostContent = (values: any) => {
    if (!values.categoryId) {
        message.error('请选择内容分类')
        return false
    }
    if (!values.postDate) {
        message.error('请选择内容发布时间')
        return false
    }
    if (!values.commentStatus) {
        message.error('请选择内容评论状态')
        return false
    }
    if (!values.secretLevel) {
        message.error('请选择内容保密等级')
        return false
    }
    if (values.isOriginal == undefined) {
        message.error('请选择内容是否原创')
        return false
    } else if (values.isOriginal == 'false') {
        if (!values.sourceName || !values.sourceUrl) {
            message.error('非原创内容，请填写原文作者和原文链接')
            return false
        }
    }
    if (!values.featuredImageUpload) {
        message.error('请上传特色图像')
        return false
    }
    if (!values.postTitle) {
        message.error('请填写内容标题')
        return false
    }
    if (!values.postExcerpt) {
        message.error('请填写内容摘要')
        return false
    }
    if (!values.postContent) {
        message.error('请填写内容')
        return false
    }
    return true
}

const submitForm = (values: any) => {
    if (!checkPostContent(values)) {
        return false
    }
    let dashPost: DashPost = {
        id: values.id,
        categoryId: values.categoryId,
        postAuthor: '0',
        postDate: values.postDate.format('YYYY-MM-DD HH:mm:ss'),
        postStatus: 'PUBLISH',
        postViews: 0,
        commentStatus: values.commentStatus,
        postPassword: values.postPassword,
        postModified: '',
        postModifiedUser: '0',
        postParent: 0,
        versionNumber: 1,
        thumbsUp: 0,
        thumbsDown: 0,
        avgViews: 0,
        avgComment: 0,
        pageRank: 0,
        secretLevel: values.secretLevel,
        isOriginal: values.isOriginal,
        featuredImage: values.featuredImageUpload.file.response.location,
        postTitle: values.postTitle,
        postKeyword: '',
        postExcerpt: values.postExcerpt,
        postContent: values.postContent,
        sourceName: values.sourceName,
        sourceUrl: values.sourceUrl,
        authorUsername: '',
        modifiedUsername: '',
    }
    if (dashPost.id == '-1') {
        // 创建文章
        api.createPost(dashPost).then(res => {
            if (res.code == 200) {
                if (typeof window !== 'undefined') {
                    window.location.replace('/dashboard/cms/posts')
                }
            } else {
                message.error(res.message)
            }
        })
    } else if (parseInt(dashPost.id) > 0) {
        // 编辑文章
        api.updatePost(dashPost).then(res => {
            if (res.code == 200) {
                if (typeof window !== 'undefined') {
                    window.location.replace('/dashboard/cms/posts')
                }
            } else {
                message.error(res.message)
            }
        })
    } else {
        message.error('意外的文章状态，请联系软件厂商')
        return false
    }
}

const DashboardCmsPostEdit = ({data}: InferGetServerSidePropsType<typeof getServerSideProps>) => {
    const [form] = Form.useForm();
    const editorRef = useRef<any>(null);

    if (data.post.id > 0) {
        form.setFieldsValue({id: data.post.id})
        form.setFieldsValue({categoryId: data.post.categoryId})
        form.setFieldsValue({postAuthor: data.post.postAuthor})
        form.setFieldsValue({postDate: moment(data.post.postDate)})
        form.setFieldsValue({postStatus: data.post.postStatus})
        form.setFieldsValue({postViews: data.post.postViews})
        form.setFieldsValue({commentStatus: data.post.commentStatus})
        form.setFieldsValue({postPassword: data.post.postPassword})
        form.setFieldsValue({postModified: data.post.postModified})
        form.setFieldsValue({postModifiedUser: data.post.postModifiedUser})
        form.setFieldsValue({postParent: data.post.postParent})
        form.setFieldsValue({versionNumber: data.post.versionNumber})
        form.setFieldsValue({thumbsUp: data.post.thumbsUp})
        form.setFieldsValue({thumbsDown: data.post.thumbsDown})
        form.setFieldsValue({avgViews: data.post.avgViews})
        form.setFieldsValue({avgComment: data.post.avgComment})
        form.setFieldsValue({pageRank: data.post.pageRank})
        form.setFieldsValue({secretLevel: data.post.secretLevel})
        form.setFieldsValue({isOriginal: data.post.isOriginal})
        form.setFieldsValue({featuredImage: data.post.featuredImage})
        form.setFieldsValue({postTitle: data.post.postTitle})
        form.setFieldsValue({postKeyword: data.post.postKeyword})
        form.setFieldsValue({postExcerpt: data.post.postExcerpt})
        form.setFieldsValue({postContent: data.post.postContent})
        form.setFieldsValue({sourceName: data.post.sourceName})
        form.setFieldsValue({sourceUrl: data.post.sourceUrl})
    }

    useEffect(() => {
        form.setFieldsValue({secretLevel: 'UNCLASSIFIED'})
        form.setFieldsValue({commentStatus: 'OPENED'})
        form.setFieldsValue({isOriginal: 'true'})
    })

    const submit = () => {
        form.setFieldsValue({postContent: editorRef.current.getContent()})
        return true
    }

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
                {
                    data.post.id < -1 ? (
                        <Result
                            status="error"
                            title={0 - data.post.id}
                            subTitle={data.post.postTitle}
                        >
                        </Result>
                    ) : (
                        <>
                            {
                                data.post.id > 0 ? (
                                    <div style={{
                                        backgroundColor: '#fff',
                                        padding: '24px 24px 12px',
                                        marginBottom: '16px'
                                    }}>
                                        <Descriptions title="内容元信息">
                                            <Descriptions.Item
                                                label="内容状态">{switchPostStatus(data.post.postStatus)}</Descriptions.Item>
                                            <Descriptions.Item label="内容浏览量">{data.post.postViews}</Descriptions.Item>
                                            <Descriptions.Item
                                                label="内容作者">{data.post.authorUsername}</Descriptions.Item>
                                            <Descriptions.Item
                                                label="最后修改时间">{data.post.postModified}</Descriptions.Item>
                                            <Descriptions.Item
                                                label="最后修改人">{data.post.modifiedUsername}</Descriptions.Item>
                                            <Descriptions.Item
                                                label="当前版本号">{data.post.versionNumber}</Descriptions.Item>
                                            <Descriptions.Item label="内容点赞量">{data.post.thumbsUp}</Descriptions.Item>
                                            <Descriptions.Item label="内容点踩量">{data.post.thumbsDown}</Descriptions.Item>
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
                                ) : ''
                            }
                            <div style={{backgroundColor: '#fff', padding: '24px 24px 0', marginBottom: '16px'}}>
                                <Form
                                    form={form}
                                    onFinish={submitForm}
                                    layout="horizontal"
                                >
                                    <Row>
                                        <Col span={24}>
                                            <Form.Item name="id" style={{display: 'none'}}>
                                                <Input value={data.post.id}/>
                                            </Form.Item>
                                            <Form.Item label="内容标题" name="postTitle">
                                                <Input value={data.post.postTitle}/>
                                            </Form.Item>
                                        </Col>
                                        <Col span={24}>
                                            <Form.Item label="特色图像" name="featuredImageUpload">
                                                <Upload
                                                    action="/api/core/system/upload"
                                                    accept="image/*"
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
                                            <Form.Item label="内容分类" name="categoryId">
                                                <Select
                                                    options={data.postCatOptions}
                                                >
                                                </Select>
                                            </Form.Item>
                                        </Col>
                                        <Col xs={24} sm={1} md={1} lg={1} xl={1} xxl={1}> </Col>
                                        <Col xs={24} sm={24} md={24} lg={14} xl={16} xxl={18}>
                                            <Form.Item label="关键词组" name="postKeyword">
                                                <Input.Group compact>
                                                    <Select mode="tags" style={{width: 'calc(100% - 129px)'}}>
                                                    </Select>
                                                    <Button>从正文自动提取</Button>
                                                </Input.Group>
                                            </Form.Item>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={24} sm={24} md={24} lg={9} xl={7} xxl={5}>
                                            <Form.Item label="保密等级" name="secretLevel">
                                                <Select>
                                                    <Option value="UNCLASSIFIED">非密</Option>
                                                    <Option value="INTERNAL">内部</Option>
                                                    <Option value="SECRET">秘密</Option>
                                                    <Option value="CONFIDENTIAL">机密</Option>
                                                </Select>
                                            </Form.Item>
                                        </Col>
                                        <Col xs={24} sm={1} md={1} lg={1} xl={1} xxl={1}> </Col>
                                        <Col xs={24} sm={24} md={24} lg={9} xl={7} xxl={5}>
                                            <Form.Item label="评论状态" name="commentStatus">
                                                <Select>
                                                    <Option value="OPENED">允许评论</Option>
                                                    <Option value="CLOSED">禁止评论</Option>
                                                </Select>
                                            </Form.Item>
                                        </Col>
                                        <Col xs={24} sm={1} md={1} lg={24} xl={1} xxl={1}> </Col>
                                        <Col xs={24} sm={24} md={24} lg={9} xl={8} xxl={12}>
                                            <Form.Item label="密码保护" name="postPassword">
                                                <Input/>
                                            </Form.Item>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={24} sm={24} md={24} lg={9} xl={7} xxl={5}>
                                            <Form.Item label="发布时间" name="postDate">
                                                <DatePicker
                                                    style={{width: '100%'}}
                                                    showTime
                                                    format="YYYY-MM-DD HH:mm:ss"/>
                                            </Form.Item>
                                        </Col>
                                        <Col xs={24} sm={1} md={1} lg={1} xl={1} xxl={1}> </Col>
                                        <Col xs={24} sm={24} md={24} lg={14} xl={16} xxl={18}>
                                            <Form.Item label="内容摘要" name="postExcerpt">
                                                <TextArea rows={2}/>
                                            </Form.Item>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={24} sm={24} md={24} lg={9} xl={7} xxl={5}>
                                            <Form.Item label="是否原创" name="isOriginal">
                                                <Select>
                                                    <Option value="true">原创</Option>
                                                    <Option value="false">转载</Option>
                                                </Select>
                                            </Form.Item>
                                        </Col>
                                        <Col xs={24} sm={1} md={1} lg={1} xl={1} xxl={1}> </Col>
                                        <Col xs={24} sm={24} md={24} lg={14} xl={7} xxl={5}>
                                            <Form.Item label="原文作者" name="sourceName">
                                                <Input/>
                                            </Form.Item>
                                        </Col>
                                        <Col xs={24} sm={1} md={1} lg={24} xl={1} xxl={1}> </Col>
                                        <Col xs={24} sm={24} md={24} lg={9} xl={8} xxl={12}>
                                            <Form.Item label="原文链接" name="sourceUrl">
                                                <Input/>
                                            </Form.Item>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span={24}>
                                            <Form.Item name="postContent" style={{display: 'none'}}>
                                                <Input/>
                                            </Form.Item>
                                            <Editor
                                                apiKey={process.env.NEXT_PUBLIC_TINYMCE_API_TOKEN}
                                                onInit={(evt, editor) => {
                                                    editorRef.current = editor
                                                }}
                                                initialValue={data.post.postContent}
                                                init={{
                                                    height: 650,
                                                    min_height: 400,
                                                    language: 'zh_CN',
                                                    menubar: true,
                                                    file_picker_types: 'image',
                                                    toolbar_mode: 'wrap',
                                                    automatic_uploads: true,
                                                    images_upload_url: '/api/core/system/upload',
                                                    fontsize_formats: '12px 14px 16px 18px 24px 36px 48px 56px 72px',
                                                    plugins: [
                                                        'preview', 'importcss',
                                                        'searchreplace', 'autolink', 'autosave', 'save', 'directionality',
                                                        'visualblocks', 'visualchars', 'fullscreen', 'image',
                                                        'link', 'media', 'template', 'code', 'codesample', 'table',
                                                        'charmap', 'pagebreak', 'nonbreaking', 'anchor',
                                                        'insertdatetime', 'advlist', 'lists', 'wordcount',
                                                        'help', 'charmap', 'quickbars', 'emoticons', 'autoresize'
                                                    ],
                                                    toolbar: 'undo redo | bold italic underline strikethrough | '
                                                        + 'fontfamily fontsize blocks | alignleft aligncenter '
                                                        + 'alignright alignjustify | outdent indent |  numlist '
                                                        + 'bullist | forecolor backcolor removeformat | pagebreak'
                                                        + ' | charmap emoticons | '
                                                        + 'image media link anchor codesample | ltr rtl | '
                                                        + 'insertdatetime nonbreaking searchreplace | '
                                                        + 'table tabledelete | tableprops tablerowprops tablecellprops | '
                                                        + 'tableinsertrowbefore tableinsertrowafter tabledeleterow | '
                                                        + 'tableinsertcolbefore tableinsertcolafter tabledeletecol | '
                                                        + 'visualblocks visualchars wordcount | fullscreen  preview save print code',
                                                }}
                                            />
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span={24} style={{padding: '24px 0', textAlign: 'right'}}>
                                            <Button type="primary" onClick={submit} htmlType="submit">保存</Button>
                                        </Col>
                                    </Row>
                                </Form>
                            </div>
                        </>
                    )
                }
            </div>
        </>
    )
}

DashboardCmsPostEdit.getLayout = (page: any) => {
    return (
        <DashboardLayout>
            {page}
        </DashboardLayout>
    )
}

export default DashboardCmsPostEdit