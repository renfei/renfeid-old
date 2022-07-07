const request = (url: string, config: any) => {
    return fetch(url, config)
        .then((res: any) => {
            if (!res.ok) {
                // 服务器异常返回
                throw Error('接口请求异常');
            }
            return res.json();
        })
        .catch((error: any) => {
            return Promise.reject(error);
        });
};

// GET请求
export const get = (url: string, token?: string) => {
    const config = {
        method: 'GET',
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        },
    }
    if (token) {
        config.headers.Authorization = 'Bearer ' + token
    }
    return request(url, config);
};

// POST请求
export const post = (url: string, data: any, token?: string) => {
    const config = {
        method: 'POST',
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        },
        body: '',
    }
    if (token) {
        config.headers.Authorization = 'Bearer ' + token
    }
    if (data !== null) {
        config.body = JSON.stringify(data)
    }
    return request(url, config);
};

// PUT请求
export const put = (url: string, data: any, token?: string) => {
    const config = {
        method: 'PUT',
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        },
        body: '',
    }
    if (token) {
        config.headers.Authorization = 'Bearer ' + token
    }
    if (data !== null) {
        config.body = JSON.stringify(data)
    }
    return request(url, config);
};

// DELETE请求
export const delet = (url: string, token?: string) => {
    const config = {
        method: 'DELETE',
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        },
    }
    if (token) {
        config.headers.Authorization = 'Bearer ' + token
    }
    return request(url, config);
};