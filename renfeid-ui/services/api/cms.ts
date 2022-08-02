import * as Fetch from '../../utils/request'
import PostVo = API.PostVo
import APIResult = API.APIResult
import ListData = API.ListData

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

export const getPosts = async (headers: Headers, categoryId?: string, page?: number, rows?: number, token?: string) => {
    if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
        // 预览模式
        let postVos: PostVo[] = []
        for (let i = 1; i < (rows || 11); i++) {
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
                original: true
            })
        }
        let listData: ListData<PostVo> = {
            pageNum: page || 1,
            pageSize: rows || 10,
            startRow: 0,
            endRow: 0,
            total: rows || 10,
            pages: 1,
            data: postVos
        }
        let result: APIResult<ListData<PostVo>> = {
            code: 200,
            message: 'ok',
            timestamp: new Date().valueOf(),
            signature: '',
            nonce: '',
            data: listData
        }
        return result
    } else {
        let url = `/-/api/cms/posts?`
        if (categoryId) {
            url += 'categoryId=' + categoryId + '&'
        }
        if (page) {
            url += 'page=' + page + '&'
        }
        if (rows) {
            url += 'rows=' + rows + '&'
        }
        return Fetch.get(url, headers, token, true)
    }
}

export const getPostsById = async (id: string, headers: Headers, password?: string, token?: string) => {
    if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
        // 预览模式
        const result: APIResult<PostVo> = {
            code: 200,
            message: 'ok',
            timestamp: new Date().valueOf(),
            signature: '',
            nonce: '',
            data: {
                id: id.toString(),
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
                postTitle: `开发预览版 - Developer Preview`,
                postKeyword: `postKeyword`,
                postExcerpt: previewPostExcerpt,
                postContent: previewPostContent,
                sourceName: '',
                sourceUrl: '',
                original: true
            }
        }
        return result
    } else {
        let url = `/-/api/cms/posts/${id}`
        if (password) {
            url += '?password=' + password
        }
        return Fetch.get(url, headers, token, true)
    }
}