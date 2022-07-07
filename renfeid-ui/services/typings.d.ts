// @ts-ignore
/* eslint-disable */

declare namespace API {
    // 登录请求对象
    type SignInAo = {
        username: string,
        password: string,
        redirect: string,
    }

    type APIResult<T> = {
        code: number,
        message: string,
        timestamp: number,
        signature: string,
        nonce: string,
        data: T
    }

    // 文章内容对象
    type PostVo = {
        id: number,
        categoryId: number,
        postAuthor: number,
        postDate: string,
        postStatus: string,
        postViews: number,
        commentStatus: string,
        thumbsUp: number,
        thumbsDown: number,
        avgViews: number,
        avgComment: number,
        pageRank: number,
        secretLevel: string,
        featuredImage: string,
        postTitle: string,
        postKeyword: string,
        postExcerpt: string,
        postContent: string,
        sourceName: string,
        sourceUrl: string,
        original: true,
    }
}