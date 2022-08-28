// @ts-ignore
/* eslint-disable */

declare namespace API {
    // 秘钥交互对象
    interface SecretKey {
        uuid: string,
        publicKey: string,
        privateKey: string,
    }

    interface SecretValue {
        secretKey: SecretKey,
        value: string,
    }

    // 加密后对象
    interface EncryptionVo {
        uuid: string,
        ciphertext: string,
    }

    // 登录请求对象
    interface SignInAo {
        userName: string,
        password: string,
        tOtp?: string,
        keyUuid: string,
        useVerCode?: boolean,
        plainPassword: string,
    }

    interface SignInVo {
        accessToken: string,
        ucScript?: string,
    }

    interface SignUpAo {
        userName: string,
        password: string,
        email: string,
        keyUuid: string,
        plainPassword: string,
        plainPassword2: string,
    }

    interface SignUpActivationAo {
        emailOrPhone: string,
        code: string,
    }

    interface APIResult<T> {
        code: number,
        message: string,
        timestamp: number,
        signature: string,
        nonce: string,
        data?: T
    }

    // 列表数据对象
    interface ListData<T> {
        pageNum: number,
        pageSize: number,
        startRow: number,
        endRow: number,
        total: number,
        pages: number,
        data: T[],
    }

    // 文章内容对象
    interface PostVo {
        id: string,
        categoryId: string,
        postAuthor: string,
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
        tags?: Tag[],
    }

    // 后台内容管理对象
    interface DashPost {
        id: string,
        categoryId: string,
        postAuthor: string,
        postDate: string,
        postStatus: string,
        postViews: number,
        commentStatus: string,
        postPassword: string,
        postModified: string,
        postModifiedUser: string,
        postParent: number,
        versionNumber: number,
        thumbsUp: number,
        thumbsDown: number,
        avgViews: number,
        avgComment: number,
        pageRank: number,
        secretLevel: string,
        isOriginal: boolean,
        featuredImage: string,
        postTitle: string,
        postKeyword: string,
        postExcerpt: string,
        postContent: string,
        sourceName: string,
        sourceUrl: string,
        authorUsername: string,
        modifiedUsername: string,
        tags?: Tag[],
    }

    interface PostCategory {
        id: string,
        enName: string,
        zhName: string,
        secretLevel: string,
    }

    // 文件上传响应对象
    interface UploadObjectVo {
        location: string,
        alt: string,
    }

    // 评论树
    interface CommentTree {
        id: string,
        addtime: string,
        isOwner: boolean,
        author: string,
        authorUrl: string,
        authorAddress: string,
        content: string,
        children?: CommentTree[]
    }

    interface Comment {
        id: string,
        sysType: string,
        objectId: string,
        authorId: string,
        addtime: string,
        isDelete: boolean,
        parentId?: string,
        isOwner: boolean,
        author: string,
        authorEmail: string,
        authorUrl?: string,
        authorIp: string,
        authorAddress: string,
        content: string,
    }

    interface ReplyCommentAo {
        content: string
    }

    interface CommentAo {
        parentId?: string,
        author: string,
        authorEmail: string,
        authorUrl?: string,
        content: string,
    }

    // 用户登录信息
    interface UserInfo {
        email?: string
        emailVerified: boolean
        firstName?: string
        id: string
        lastName?: string
        phone?: string
        phoneVerified: boolean
        registrationDate: string
        registrationIp: string
        secretLevel: string
        username: string
        uuid: string,
        u2fEnable: boolean
    }

    interface AntdSelectOption {
        label: string,
        value: string,
    }

    interface TableListParams {
        pagination?: TablePaginationConfig
        sorter?: SorterResult<any> | SorterResult<any>[]
        total?: number
        sortField?: string
        sortOrder?: string
    }

    interface RoleDetail {
        id: string,
        roleName: string,
        roleDescribe: string,
        addTime: string,
        updateTime: string,
        builtInRole: boolean,
        extendJson?: string,
    }

    interface UserDetail {
        id: string,
        uuid: string,
        username: string,
        email: string,
        emailVerified: boolean,
        phone?: string,
        phoneVerified: boolean,
        registrationDate: string,
        password: string,
        totp?: string,
        registrationIp: string,
        trialErrorTimes?: number,
        lockTime: string,
        secretLevel: string,
        builtInUser: boolean,
        passwordExpirationTime?: string,
        locked: boolean,
        enabled: boolean,
        lastName?: string,
        firstName?: string,
        keyUuid: string,
        roleDetailList?: RoleDetail[]
    }

    interface ResetPasswordAo {
        password: string,
        tOtp: string,
        keyUuid: string,
        verCode: string,
    }

    interface Authority {
        authorityType: string,
        targetId: string
    }

    interface RoleDetail {
        id: string,
        roleName: string,
        roleDescribe?: string,
        addTime: string,
        updateTime?: string,
        extendJson?: string,
        authorityList?: Authority[]
    }

    interface SystemApi {
        id: string,
        urlPath: string,
        methodName: string,
        summary: string,
        description: string,
    }

    interface MenuTree {
        id: string,
        pid?: string,
        menuName: string,
        menuHref: string,
        menuIcon?: string,
        menuTarget?: string,
        menuClass?: string,
        menuTitle?: string,
        menuOnclick?: string,
        menuOrder?: string,
        enable?: string,
        addTime?: string,
        updateTime?: string,
        menuCss?: string,
        extendJson?: string,
        child?: MenuTree[],
    }

    interface Tag {
        id: string,
        enName: string,
        zhName: string,
    }

    interface SystemLogEntity {
        id: number,
        logTime: string,
        logLevel: string,
        logModule: string,
        logType: string,
        requMethod?: string,
        requUri?: string,
        requReferrer?: string,
        userUuid?: string,
        userName?: string,
        requIp?: string,
        logDesc?: string,
        requParam?: string,
        respParam?: string,
        requAgent?: string,
    }

    interface UserSignInLog {
        logTime: string,
        userUuid: string,
        userName: string,
        requIp: string,
        requAgent: string,
        address: string,
    }

    interface TotpAo {
        pwd: string,
        totp: string,
        keyId: string,
        secretKey?: string,
    }

    interface TotpVo {
        secretKey: string,
        totpString: string,
    }

    interface UpdatePasswordAo {
        oldPwd: string,
        newPwd: string,
        keyId: string,
    }

    interface ResetPasswordAo {
        password: string,
        tOtp: string,
        keyUuid: string,
        verCode: string,
    }

    interface FindUsernameAo {
        phone: string,
        verCode: string,
    }
}