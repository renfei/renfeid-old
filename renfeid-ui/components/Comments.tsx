import { Typography, Button, Comment, Form, Input, List, Divider } from 'antd'
import Link from 'next/link'
import React, { useState } from 'react'
import { CheckCircleFilled } from '@ant-design/icons'
import CommentTree = API.CommentTree

const { TextArea } = Input
const { Title, Text } = Typography

interface EditorProps {
    onChange: (e: React.ChangeEvent<HTMLTextAreaElement>) => void
    onSubmit: () => void
    submitting: boolean
    value: string
}

const CommentGroup = ({ data }: { data: CommentTree[] }) => {
    return (
        <>
            {
                data.length > 0 ? (
                    data.map(comment => (
                        <>
                            <Comment
                                className={"renfeid-comment"}
                                key={`comment-${comment.id}`}
                                actions={[
                                    <span key={`comment-nested-datetime-${comment.id}`}>{comment.addtime}</span>,
                                    <span key={`comment-nested-author-address-${comment.id}`}>{comment.authorAddress}</span>,
                                    <a key={`comment-nested-reply-to-${comment.id}`} href="#CommentEditor">回复</a>
                                ]}
                                author={(
                                    <Link key={`comment-author-url-${comment.id}`}
                                        href={comment.authorUrl ? comment.authorUrl : 'javascript:void(0)'}>
                                        <a id={`cmt-${comment.id}`} target="_blank" key={`comment-author-url-a-${comment.id}`}>{comment.author}</a>
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
                        </>
                    ))
                ) : ''
            }
        </>
    )
}

const Comments = ({ data }: { data?: CommentTree[] }) => {
    const [comments, setComments] = useState<CommentTree[]>(data ? data : [])
    const [submitting, setSubmitting] = useState(false)
    const [value, setValue] = useState('')
    const [form] = Form.useForm()

    const handleSubmit = () => {
        if (!value) return

        setSubmitting(true)

        setTimeout(() => {
            setSubmitting(false)
            setValue('')
            setComments([
                ...comments,
                {
                    id: '1',
                    addtime: '2022-07-21 12:35:23',
                    isOwner: false,
                    author: 'Han Solo',
                    authorUrl: 'Han Solo',
                    authorAddress: 'Beijing, Beijing, China',
                    content: `${value}`,
                },
            ])
        }, 1000)
    }

    const handleChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
        setValue(e.target.value)
    }

    const Editor = ({ onChange, onSubmit, submitting, value }: EditorProps) => (
        <Form
            id="CommentEditor"
        >
            <Form.Item
                label="昵称"
                name="name"
            >
                <Input placeholder="必填：怎么称呼您？" />
            </Form.Item>
            <Input type="hidden" />
            <Form.Item
                label="电邮"
                name="email"
            >
                <Input placeholder="必填：请填写真实电子邮箱，如有回复将通知您，邮箱不会被公开。" />
            </Form.Item>
            <Form.Item
                label="链接"
                name="link"
            >
                <Input placeholder="可选：您可以填写您的主页地址，方便网友找到您。" />
            </Form.Item>
            <Form.Item label="内容">
                <TextArea
                    rows={4}
                    onChange={onChange}
                    placeholder="本站有缓存策略，时间约2小时后能看到您的评论。本站使用自动审核机制，如果您的内容包含广告/谩骂/恐怖/暴力/涉政等不和谐内容将无法展示！"
                    value={value} />
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
                            onChange={handleChange}
                            onSubmit={handleSubmit}
                            submitting={submitting}
                            value={value}
                        />
                    }
                />
            </div>
        </>
    )
}

export default Comments