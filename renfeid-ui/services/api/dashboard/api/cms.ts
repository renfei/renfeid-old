import * as Fetch from '../../../../utils/request'
import DashPost = API.DashPost;
import APIResult = API.APIResult;

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
        url += 'page=' + page + '&'
    }
    if (rows) {
        url += 'rows=' + rows + '&'
    }
    return await fetch(url, {
        method: 'GET',
    }).then((res: any) => {
        return res.json();
    }).catch((error: any) => {
        return Promise.reject(error);
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
        return res.json();
    }).catch((error: any) => {
        return Promise.reject(error);
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
        return res.json();
    }).catch((error: any) => {
        return Promise.reject(error);
    })
}

export const queryPostById = async (token: string, headers: Headers, id: string) => {
    const url = `${process.env.RENFEID_SERVICE_API}/_/api/cms/posts/` + id
    return Fetch.get(url, headers, token)
}

export const queryPostCategoryList = async (token: string, headers: Headers, enName?: string, zhName?: string,
                                            secretLevel?: string, pages?: string, rows?: string) => {
    let url = `${process.env.RENFEID_SERVICE_API}/_/api/cms/posts/category?`
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