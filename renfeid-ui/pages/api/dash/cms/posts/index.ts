// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type { NextApiRequest, NextApiResponse } from 'next'
import { getPosts } from '../../../../../services/api'
import * as Fetch from '../../../../../utils/request'
import { convertToHeaders } from "../../../../../utils/request"
import ListData = API.ListData
import DashPost = API.DashPost

const previewPostFeaturedImage = `https://cdn.renfei.net/upload/2022/daba120e37f64adfa6c14e6dc0f1e770.webp`
const previewPostExcerpt = `这是开发预览版，在Git仓库中提交后将自动部署最新的代码，提供最新的开发预览版本。这里仅限开源部分，私有闭源部分不提供预览版，预览版不是稳定版本，只是为了快速验证代码，任何功能都可能被移除，仅供参考。开发预览版仅支持预览，不支持任何数据新增、修改、删除操作。`
const previewPostContent = `
 <img src="${previewPostFeaturedImage}" lat="开发预览版 - Developer Preview" />
<p style="text-indent:2em">这是开发预览版，在Git仓库中提交后将自动部署最新的代码，提供最新的开发预览版本。这里仅限开源部分，私有闭源部分不提供预览版，预览版不是稳定版本，只是为了快速验证代码，任何功能都可能被移除，仅供参考。开发预览版仅支持预览，不支持任何数据新增、修改、删除操作。</p>
<p style="text-indent:2em">后台管理系统预览：<a href="/dashboard" target="_blank" rel="nofollow noopener">https://preview.renfei.net/dashboard</a></p>
<p style="text-indent:2em"></p>
<p style="text-indent:2em">代码仓库位于：<a href="https://github.com/renfei/renfeid" target="_blank" rel="nofollow noopener">https://github.com/renfei/renfeid</a></p>
<p style="text-indent:2em">如果您有反馈或意见，欢迎到社区参与反馈讨论：<a href="https://github.com/renfei/feedback/discussions" target="_blank" rel="nofollow noopener">https://github.com/renfei/feedback/discussions</a></p>
<p style="text-indent:2em"></p>
<p style="text-indent:2em">当前页面为文章内容的详情页，用于验证内容布局、引用图片、引用代码、引用视频等功能是否正常。</p>
<p style="text-indent:2em">下方将引用代码：</p>
<pre class="language-java"><code>public static void main(String[]args){
        System.out.println("Test");
}</code></pre>
<p style="text-indent:2em">下方将引用视频：</p>
<iframe src="//player.bilibili.com/player.html?aid=247083339&bvid=BV1wv411a75q&cid=309606428&page=1" style="width:100%;height:500px;" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
<p style="text-indent:2em"></p>
`

const handler = async (req: NextApiRequest, res: NextApiResponse) => {
    if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
        // 预览模式
        if (req.method === 'GET') {
            let postVos: DashPost[] = []
            for (let i = 1; i < 10; i++) {
                postVos.push({
                    id: i.toString(),
                    categoryId: '1',
                    postAuthor: '1',
                    postDate: '2022-07-22 15:10:30',
                    postStatus: 'PUBLISH',
                    postViews: 1668,
                    commentStatus: 'OPENED',
                    thumbsUp: 0,
                    thumbsDown: 0,
                    avgViews: 0,
                    avgComment: 0,
                    pageRank: 0,
                    secretLevel: 'UNCLASSIFIED',
                    featuredImage: previewPostFeaturedImage,
                    postTitle: `【${i}】开发预览版 - Developer Preview`,
                    postKeyword: `postKeyword[${i}]`,
                    postExcerpt: previewPostExcerpt,
                    postContent: previewPostContent,
                    sourceName: '',
                    sourceUrl: '',
                    isOriginal: true,
                    postPassword: '',
                    postModified: '',
                    postModifiedUser: '',
                    postParent: 0,
                    versionNumber: 1,
                    authorUsername: '',
                    modifiedUsername: ''
                })
            }
            let listData: ListData<DashPost> = {
                pageNum: 1,
                pageSize: 10,
                startRow: 0,
                endRow: 0,
                total: 10,
                pages: 1,
                data: postVos
            }

            res.status(200).json({
                code: 200,
                message: 'OK',
                timestamp: new Date().valueOf(),
                signature: '',
                nonce: '',
                data: listData
            })
        } else {
            res.status(403).json({
                code: 403,
                message: '预览模式，仅支持查看，不支持数据修改。',
                timestamp: new Date().valueOf(),
                signature: '',
                nonce: ''
            })
        }
    } else {
        const token = req.cookies['accessToken']
        if (token) {
            let url = `/_/api/cms/posts?`
            if (req.method === 'GET') {
                if (req.query.categoryId) {
                    url += 'categoryId=' + req.query.categoryId + '&'
                }
                if (req.query.title) {
                    url += 'title=' + req.query.title + '&'
                }
                if (req.query.postStatus) {
                    url += 'postStatus=' + req.query.postStatus + '&'
                }
                if (req.query.startDate) {
                    url += 'startDate=' + req.query.startDate + '&'
                }
                if (req.query.endDate) {
                    url += 'endDate=' + req.query.endDate + '&'
                }
                if (req.query.pages) {
                    url += 'pages=' + req.query.pages + '&'
                }
                if (req.query.rows) {
                    url += 'rows=' + req.query.rows + '&'
                }
                await Fetch.get(url, convertToHeaders(req.headers), token, true).then(result => {
                    res.status(200).json(result)
                })
            } else if (req.method === 'POST') {
                await Fetch.post(url, req.body, convertToHeaders(req.headers), token, true).then(result => {
                    res.status(200).json(result)
                })
            } else {
                res.status(405).json({
                    code: 405,
                    message: '不支持的请求方法。',
                    timestamp: new Date().valueOf(),
                    signature: '',
                    nonce: ''
                })
            }
        } else {
            res.status(401).json({
                code: 401,
                message: '需要身份验证。',
                timestamp: new Date().valueOf(),
                signature: '',
                nonce: ''
            })
        }
    }
}

export default handler