import { Typography, Button, Comment, Form, Input, Divider, message } from 'antd'
import Link from 'next/link'
import moment from 'moment'
import React, { useState } from 'react'
import { CheckCircleFilled } from '@ant-design/icons'
import CommentTree = API.CommentTree
import UserInfo = API.UserInfo
import APIResult = API.APIResult
import CommentAo = API.CommentAo
import * as api from '../services/api'

const { TextArea } = Input
const { Title, Text } = Typography

interface EditorProps {
    onSubmit: () => void
    submitting: boolean
}

const Comments = (props: { systemTypeEnum: string, objectId: string, data?: CommentTree[], userInfo?: UserInfo }) => {
    const [comments, setComments] = useState<CommentTree[]>(props.data ? props.data : [])
    const [submitting, setSubmitting] = useState<boolean>(false)
    const [parentId, setParentId] = useState<string>('')
    const [parentName, setParentName] = useState<string>('')
    const [form] = Form.useForm()

    const CommentGroup = ({ data }: { data: CommentTree[] }) => {
        return (
            <>
                {
                    data.length > 0 ? (
                        data.map(comment => (
                            <Comment
                                className={"renfeid-comment"}
                                key={`comment-${comment.id}`}
                                actions={[
                                    <span key={`comment-nested-datetime-${comment.id}`}>{comment.addtime}</span>,
                                    <span key={`comment-nested-author-address-${comment.id}`}>{comment.authorAddress}</span>,
                                    <a key={`comment-nested-reply-to-${comment.id}`}
                                        href="#CommentEditor"
                                        onClick={() => {
                                            setParentId(`${comment.id}`)
                                            setParentName(`${comment.author}`)
                                        }}>回复</a>
                                ]}
                                author={(
                                    <Link key={`comment-author-url-${comment.id}`}
                                        href={comment.authorUrl ? comment.authorUrl : 'javascript:void(0)'}>
                                        <a id={`cmt-${comment.id}`} target="_blank" rel="nofollow noopener" key={`comment-author-url-a-${comment.id}`}>{comment.author}</a>
                                    </Link>
                                )}
                                content={comment.content}
                                datetime={
                                    comment.isOwner ? (
                                        <span style={{ color: '#ffb032' }}>
                                            <CheckCircleFilled /><span style={{ padding: '0 0 0 5px' }}>站点官方</span>
                                        </span>
                                    ) : ''
                                }
                            >
                                {
                                    comment.children && comment.children.length > 0 ? (
                                        <CommentGroup key={`comment-children-${comment.id}`} data={comment.children} />
                                    ) : ''
                                }
                            </Comment>
                        ))
                    ) : ''
                }
            </>
        )
    }

    if (props.userInfo) {
        form.setFieldsValue({
            name: props.userInfo.username,
            email: props.userInfo.email
        })
    }


    const handleSubmit = async () => {
        if (!form.getFieldValue('name')) {
            message.error('请填写昵称')
            return
        }
        if (!form.getFieldValue('email')) {
            message.error('请填写电邮')
            return
        }
        if (!form.getFieldValue('content')) {
            message.error('请填写内容')
            return
        }
        setSubmitting(true)
        if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
            setTimeout(() => {
                setSubmitting(false)
                setComments([
                    ...comments,
                    {
                        id: '1',
                        addtime: moment(new Date()).format('yyyy-MM-DD HH:mm:ss'),
                        isOwner: false,
                        author: form.getFieldValue('name'),
                        authorUrl: form.getFieldValue('link'),
                        authorAddress: 'Beijing, Beijing, China',
                        content: form.getFieldValue('content'),
                    },
                ])
            }, 1000)
        } else {
            const comment: CommentAo = {
                parentId: parentId,
                author: form.getFieldValue('name'),
                authorEmail: form.getFieldValue('email'),
                authorUrl: form.getFieldValue('link'),
                content: form.getFieldValue('content')
            }
            const result: APIResult<any> = await api.submitComments(props.systemTypeEnum, props.objectId, comment)
            if (result.code == 200) {
                message.success('提交成功！')
                form.setFieldValue('content', '')
                setParentId('')
            } else {
                message.error(result.message)
            }
            setSubmitting(false)
        }
    }

    const Editor = ({ onSubmit, submitting }: EditorProps) => (
        <Form
            form={form}
            id="CommentEditor"
        >
            <Form.Item
                name="parentId"
                style={{ display: 'none' }}
            >
                <Input />
            </Form.Item>
            <Form.Item
                label="昵称"
                name="name"
            >
                <Input readOnly={props.userInfo != undefined} disabled={props.userInfo != undefined} placeholder="必填：怎么称呼您？" />
            </Form.Item>
            <Input type="hidden" />
            <Form.Item
                label="电邮"
                name="email"
            >
                <Input readOnly={props.userInfo != undefined} disabled={props.userInfo != undefined} placeholder="必填：请填写真实电子邮箱，如有回复将通知您，邮箱不会被公开。" />
            </Form.Item>
            <Form.Item
                label="链接"
                name="link"
            >
                <Input placeholder="可选：您可以填写您的主页地址，方便网友找到您。" />
            </Form.Item>
            <Form.Item
                label="回复给"
                name="parentName"
                style={
                    parentId ? {} : { display: 'none' }
                }
            >
                <Input.Group compact>
                    <Form.Item noStyle>
                        <Input readOnly disabled style={{ width: '50%' }} value={parentName} />
                    </Form.Item>
                    <Form.Item noStyle>
                        <Button onClick={() => {
                            setParentId('')
                        }}>取消</Button>
                    </Form.Item>
                </Input.Group>
            </Form.Item>
            <Form.Item
                label="内容"
                name="content">
                <TextArea
                    rows={4}
                    placeholder="本站有缓存策略，时间约2小时后能看到您的评论。本站使用自动审核机制，如果您的内容包含广告/谩骂/恐怖/暴力/涉政等不和谐内容将无法展示！"
                />
            </Form.Item>
            <Form.Item
                label="注意">
                <Text type="secondary"
                    style={{ fontSize: '12px' }}>本站有缓存策略，时间约2小时后能看到您的评论。本站使用自动审核机制，如果您的内容包含广告/谩骂/恐怖/暴力/涉政等不和谐内容将无法展示！
                </Text>
            </Form.Item>
            <Form.Item>
                <Button htmlType="submit" loading={submitting} onClick={onSubmit} type="primary">
                    提交
                </Button>
            </Form.Item>
        </Form>
    )

    return (
        <>
            <div className={"renfeid_card"} id="Comments">
                <Title level={4} style={{ marginBottom: '0' }}>评论与留言</Title>
                <Text type="secondary" style={{ fontSize: '12px' }}>以下内容均由网友提交发布，版权与真实性无法查证，请自行辨别。</Text>
                {comments.length > 0 && <CommentGroup key="MainCommentGroup" data={comments} />}
                <Divider />
                <Comment
                    content={
                        <Editor
                            onSubmit={handleSubmit}
                            submitting={submitting}
                        />
                    }
                />
            </div>
        </>
    )
}

export default Comments