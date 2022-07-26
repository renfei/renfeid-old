// 客户端请求，简单包装了Fetch，服务器端不要使用这里的

export const clientGet = async (url: string) => {
    return await fetch(url, {
        method: 'GET'
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

export const clientPost = async (url: string, data?: any) => {
    return await fetch(url, {
        method: 'POST',
        headers: {
            'content-type': 'application/json',
        },
        body: JSON.stringify(data)
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

export const clientPut = async (url: string, data?: any) => {
    return await fetch(url, {
        method: 'PUT',
        headers: {
            'content-type': 'application/json',
        },
        body: JSON.stringify(data)
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

export const clientDelete = async (url: string) => {
    return await fetch(url, {
        method: 'DELETE'
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}