import * as Fetch from '../../../../utils/request'
import DashPost = API.DashPost
import APIResult = API.APIResult
import PostCategory = API.PostCategory

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

export const queryPostList = async (categoryId?: number, title?: string, postStatus?: string,
    startDate?: string, endDate?: string, page?: number, rows?: number) => {
    let url = `/api/dash/cms/posts?`
    if (categoryId) {
        url += 'categoryId=' + categoryId + '&'
    }
    if (title) {
        url += 'title=' + title + '&'
    }
    if (postStatus) {
        url += 'postStatus=' + postStatus + '&'
    }
    if (startDate) {
        url += 'startDate=' + startDate + '&'
    }
    if (endDate) {
        url += 'endDate=' + endDate + '&'
    }
    if (page) {
        url += 'pages=' + page + '&'
    }
    if (rows) {
        url += 'rows=' + rows + '&'
    }
    return await fetch(url, {
        method: 'GET',
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 创建内容
export const createPost = async (post: DashPost): Promise<APIResult<any>> => {
    const url = `/api/dash/cms/posts`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(post),
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

export const updatePost = async (post: DashPost): Promise<APIResult<any>> => {
    const url = `/api/dash/cms/posts/` + post.id
    return await fetch(url, {
        method: 'PUT',
        body: JSON.stringify(post),
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

export const queryPostById = async (token: string, headers: Headers, id: string) => {
    if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
        return {
            code: 200,
            message: 'OK',
            timestamp: new Date().valueOf(),
            signature: '',
            nonce: '',
            data: {
                id: id,
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
                isOriginal: true,
                postPassword: '',
                postModified: '',
                postModifiedUser: '',
                postParent: 0,
                versionNumber: 1,
                authorUsername: '',
                modifiedUsername: ''
            }
        }
    } else {
        const url = `/_/api/cms/posts/` + id
        return Fetch.get(url, headers, token, true)
    }
}

export const queryPostArchivalListById = async (token: string, headers: Headers, id: string) => {
    if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
        let postVos: DashPost[] = []
        for (let i = 1; i < 5; i++) {
            postVos.push({
                id: id,
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
                versionNumber: i,
                authorUsername: '',
                modifiedUsername: ''
            })
        }
        return {
            code: 200,
            message: 'OK',
            timestamp: new Date().valueOf(),
            signature: '',
            nonce: '',
            data: postVos
        }
    } else {
        const url = `/_/api/cms/posts/archival/` + id
        return Fetch.get(url, headers, token, true)
    }
}

export const queryPostArchivalById = async (token: string, headers: Headers, id: string, archivalId: string) => {
    const url = `/_/api/cms/posts/archival/${id}/${archivalId}`
    return Fetch.get(url, headers, token, true)
}

export const offlinePost = async (id: string) => {
    const url = `/api/dash/cms/posts/${id}/offline`
    return await fetch(url, {
        method: 'PUT',
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

export const queryPostCategoryListUseInner = async (token: string, headers: Headers, enName?: string, zhName?: string,
    secretLevel?: string, pages?: number, rows?: number) => {
    if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
        // 预览模式
        return {
            code: 200,
            message: 'OK',
            timestamp: new Date().valueOf(),
            signature: '',
            nonce: '',
            data: {
                pageNum: 1,
                pageSize: 10,
                startRow: 1,
                endRow: 1,
                total: 1,
                pages: 1,
                data: [
                    {
                        id: '1',
                        enName: 'default',
                        zhName: '默认分类',
                        secretLevel: 'UNCLASSIFIED',
                    }
                ]
            }
        }
    } else {
        let url = `/_/api/cms/posts/category?`
        if (enName) {
            url += 'enName=' + enName + '&'
        }
        if (zhName) {
            url += 'zhName=' + zhName + '&'
        }
        if (secretLevel) {
            url += 'secretLevel=' + secretLevel + '&'
        }
        if (pages) {
            url += 'pages=' + pages + '&'
        }
        if (rows) {
            url += 'rows=' + rows + '&'
        }
        return Fetch.get(url, headers, token, true)
    }
}

export const queryPostCategoryList = async (token: string, headers: Headers, enName?: string, zhName?: string,
    secretLevel?: string, pages?: number, rows?: number) => {
    let url = `/api/dash/cms/posts/category?`
    if (enName) {
        url += 'enName=' + enName + '&'
    }
    if (zhName) {
        url += 'zhName=' + zhName + '&'
    }
    if (secretLevel) {
        url += 'secretLevel=' + secretLevel + '&'
    }
    if (pages) {
        url += 'pages=' + pages + '&'
    }
    if (rows) {
        url += 'rows=' + rows + '&'
    }
    return Fetch.get(url, headers, token)
}

export const createPostCategory = async (postCategory: PostCategory): Promise<APIResult<any>> => {
    const url = `/api/dash/cms/posts/category`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(postCategory),
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

export const updatePostCategory = async (postCategory: PostCategory): Promise<APIResult<any>> => {
    const url = `/api/dash/cms/posts/category/` + postCategory.id
    return await fetch(url, {
        method: 'PUT',
        body: JSON.stringify(postCategory),
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

export const deletePostCategory = async (id: string): Promise<APIResult<any>> => {
    const url = `/api/dash/cms/posts/category/` + id
    return await fetch(url, {
        method: 'DELETE',
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

export const queryAllTag = async (token: string, headers: Headers) => {
    if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
        return {
            code: 200,
            message: 'OK',
            timestamp: new Date().valueOf(),
            signature: '',
            nonce: '',
            data: [{
                id: '1',
                enName: 'test',
                zhName: '测试',
            }, {
                id: '2',
                enName: 'tag',
                zhName: '标签',
            }]
        }
    } else {
        const url = `/-/api/cms/tag`
        return Fetch.get(url, headers, token, true)
    }
}