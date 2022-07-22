import * as Fetch from '../../utils/request'

export const getPosts = async (headers: Headers, categoryId?: number, page?: number, rows?: number, token?: string) => {
    let url = `${process.env.RENFEID_SERVICE_API}/-/api/cms/posts?`
    if (categoryId) {
        url += 'categoryId=' + categoryId + '&'
    }
    if (page) {
        url += 'page=' + page + '&'
    }
    if (rows) {
        url += 'rows=' + rows + '&'
    }
    return Fetch.get(url, headers, token)
}

export const getPostsById = async (id: number, headers: Headers, password?: string, token?: string) => {
    let url = `${process.env.RENFEID_SERVICE_API}/-/api/cms/posts/${id}`
    if (password) {
        url += '?password=' + password
    }
    return Fetch.get(url, headers, token)
}