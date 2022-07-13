// @ts-ignore
/* eslint-disable */

declare namespace API {
    // 秘钥交互对象
    interface SecretKey {
        uuid: string,
        publicKey: string,
        privateKey: string,
    }

    // 加密后对象
    interface EncryptionVo{
        uuid: string,
        ciphertext: string,
    }

    // 登录请求对象
    interface SignInAo {
        username: string,
        password: string,
        plainPassword: string,
        redirect: string,
    }

    interface APIResult<T> {
        code: number,
        message: string,
        timestamp: number,
        signature: string,
        nonce: string,
        data?: T
    }

    // 文章内容对象
    interface PostVo {
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

    // 评论树
    interface CommentTree {
        id: number,
        addtime: string,
        isOwner: boolean,
        author: string,
        authorUrl: string,
        authorAddress: string,
        content: string,
        children?: CommentTree[]
    }
}