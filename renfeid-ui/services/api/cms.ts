import * as Fetch from '../../utils/request'

export const getPosts = async (headers: Headers, categoryId?: number, page?: number, rows?: number, token?: string) => {
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

export const getPostsById = async (id: number, headers: Headers, password?: string, token?: string) => {
    let url = `/-/api/cms/posts/${id}`
    if (password) {
        url += '?password=' + password
    }
    return Fetch.get(url, headers, token, true)
}