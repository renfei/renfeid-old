import * as Fetch from '../../../../utils/request'
import ReplyCommentAo = API.ReplyCommentAo

export const uploadFile = async (token: string, headers: Headers, file: any) => {
    const url = `/_/api/core/system/upload`
    const formData = new FormData()
    formData.append('file', file)

    return await Fetch.postForm(url, formData, headers, token, true)
        .then((res: any) => {
            return res.json()
        }).catch((error: any) => {
            return Promise.reject(error)
        })
}

export const getEnvironmentInfo = async (token: string, headers: Headers) => {
    const url = `/_/api/core/system/environment`
    return Fetch.get(url, headers, token, true)
}

export const querySystemLog = async (startDate?: string, endDate?: string,
    logLevel?: string, systemType?: string, operationType?: string, reqUri?: string,
    username?: string, reqIp?: string, page?: number, rows?: number) => {
    let url = `/api/dash/core/system/log?`
    if (startDate) {
        url += 'startDate=' + startDate + '&'
    }
    if (endDate) {
        url += 'endDate=' + endDate + '&'
    }
    if (logLevel) {
        url += 'logLevel=' + logLevel + '&'
    }
    if (systemType) {
        url += 'systemType=' + systemType + '&'
    }
    if (operationType) {
        url += 'operationType=' + operationType + '&'
    }
    if (reqUri) {
        url += 'reqUri=' + reqUri + '&'
    }
    if (username) {
        url += 'username=' + username + '&'
    }
    if (reqIp) {
        url += 'reqIp=' + reqIp + '&'
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

export const queryCommentList = async (haveDelete?: boolean, page?: number, rows?: number) => {
    let url = `/api/dash/core/comment?`
    if (haveDelete) {
        url += 'haveDelete=' + haveDelete + '&'
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

export const replyComment = async (id: string, replyComment: ReplyCommentAo) => {
    let url = `/api/dash/core/comment/${id}/reply`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(replyComment),
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

export const deleteComment = async (id: string) => {
    let url = `/api/dash/core/comment/${id}`
    return await fetch(url, {
        method: 'DELETE'
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}