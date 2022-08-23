// 服务端请求封装，增加 Authorization 请求头
import http from 'http'

const request = async (url: string, config: any, isServer?: boolean) => {
    let requesrUrl;
    if (isServer) {
        requesrUrl = `${process.env.RENFEID_SERVICE_API}${url}`
    } else {
        requesrUrl = `${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}${url}`
    }
    try {
        const res = await fetch(requesrUrl, config)
        return res.json()
    } catch (error) {
        return await Promise.reject(error)
    }
}

const addAuthorizationHeader = (headers: Headers, token?: string) => {
    if (token) {
        if (headers.has('Authorization')) {
            headers.set('Authorization', 'Bearer ' + token)
        } else {
            headers.append('Authorization', 'Bearer ' + token)
        }
    }
    return headers
}

// GET请求
export const get = (url: string, headers: Headers, token?: string, isServer?: boolean) => {
    let header: Headers = addAuthorizationHeader(headers, token)
    const config = {
        method: 'GET',
        headers: header,
    }
    return request(url, config, isServer)
}

// POST请求
export const post = (url: string, data: any, headers: Headers, token?: string, isServer?: boolean) => {
    let header: Headers = addAuthorizationHeader(headers, token)
    const config = {
        method: 'POST',
        headers: header,
        body: '',
    }
    if (data) {
        config.body = JSON.stringify(data)
    }
    return request(url, config, isServer)
}

// PUT请求
export const put = (url: string, data: any, headers: Headers, token?: string, isServer?: boolean) => {
    let header: Headers = addAuthorizationHeader(headers, token)
    const config = {
        method: 'PUT',
        headers: header,
        body: '',
    }
    if (data) {
        config.body = JSON.stringify(data)
    }
    return request(url, config, isServer)
}

// DELETE请求
export const delet = (url: string, headers: Headers, token?: string, isServer?: boolean) => {
    let header: Headers = addAuthorizationHeader(headers, token)
    const config = {
        method: 'DELETE',
        headers: header,
    }
    return request(url, config, isServer)
}

// POST提交Form表单，例如上传文件
export const postForm = (url: string, data: FormData, headers: Headers, token: string, isServer?: boolean) => {
    let header: Headers = addAuthorizationHeader(headers, token)
    const config = {
        method: 'POST',
        headers: header,
        body: data,
    }
    return request(url, config, isServer)
}

export const convertToHeaders = (incomingHttpHeaders: http.IncomingHttpHeaders, remoteAddress?: string): Headers => {
    let headers: Headers = new Headers()
    if (incomingHttpHeaders['accept']) {
        headers.append('accept', incomingHttpHeaders['accept'])
    }
    if (incomingHttpHeaders['accept-language']) {
        headers.append('accept-language', incomingHttpHeaders['accept-language'])
    }
    if (incomingHttpHeaders['accept-patch']) {
        headers.append('accept-patch', incomingHttpHeaders['accept-patch'])
    }
    if (incomingHttpHeaders['accept-ranges']) {
        headers.append('accept-ranges', incomingHttpHeaders['accept-ranges'])
    }
    if (incomingHttpHeaders['access-control-allow-credentials']) {
        headers.append('access-control-allow-credentials', incomingHttpHeaders['access-control-allow-credentials'])
    }
    if (incomingHttpHeaders['access-control-allow-headers']) {
        headers.append('access-control-allow-headers', incomingHttpHeaders['access-control-allow-headers'])
    }
    if (incomingHttpHeaders['access-control-allow-methods']) {
        headers.append('access-control-allow-methods', incomingHttpHeaders['access-control-allow-methods'])
    }
    if (incomingHttpHeaders['access-control-allow-origin']) {
        headers.append('access-control-allow-origin', incomingHttpHeaders['access-control-allow-origin'])
    }
    if (incomingHttpHeaders['access-control-expose-headers']) {
        headers.append('access-control-expose-headers', incomingHttpHeaders['access-control-expose-headers'])
    }
    if (incomingHttpHeaders['access-control-max-age']) {
        headers.append('access-control-max-age', incomingHttpHeaders['access-control-max-age'])
    }
    if (incomingHttpHeaders['access-control-request-headers']) {
        headers.append('access-control-request-headers', incomingHttpHeaders['access-control-request-headers'])
    }
    if (incomingHttpHeaders['access-control-request-method']) {
        headers.append('access-control-request-method', incomingHttpHeaders['access-control-request-method'])
    }
    if (incomingHttpHeaders['age']) {
        headers.append('age', incomingHttpHeaders['age'])
    }
    if (incomingHttpHeaders['allow']) {
        headers.append('allow', incomingHttpHeaders['allow'])
    }
    if (incomingHttpHeaders['alt-svc']) {
        headers.append('alt-svc', incomingHttpHeaders['alt-svc'])
    }
    if (incomingHttpHeaders['authorization']) {
        headers.append('authorization', incomingHttpHeaders['authorization'])
    }
    if (incomingHttpHeaders['cache-control']) {
        headers.append('cache-control', incomingHttpHeaders['cache-control'])
    }
    if (incomingHttpHeaders['connection']) {
        headers.append('connection', incomingHttpHeaders['connection'])
    }
    if (incomingHttpHeaders['content-disposition']) {
        headers.append('content-disposition', incomingHttpHeaders['content-disposition'])
    }
    if (incomingHttpHeaders['content-encoding']) {
        headers.append('content-encoding', incomingHttpHeaders['content-encoding'])
    }
    if (incomingHttpHeaders['content-language']) {
        headers.append('content-language', incomingHttpHeaders['content-language'])
    }
    if (incomingHttpHeaders['content-length']) {
        headers.append('content-length', incomingHttpHeaders['content-length'])
    }
    if (incomingHttpHeaders['content-location']) {
        headers.append('content-location', incomingHttpHeaders['content-location'])
    }
    if (incomingHttpHeaders['content-range']) {
        headers.append('content-range', incomingHttpHeaders['content-range'])
    }
    if (incomingHttpHeaders['content-type']) {
        headers.append('content-type', incomingHttpHeaders['content-type'])
    }
    if (incomingHttpHeaders['cookie']) {
        headers.append('cookie', incomingHttpHeaders['cookie'])
    }
    if (incomingHttpHeaders['date']) {
        headers.append('date', incomingHttpHeaders['date'])
    }
    if (incomingHttpHeaders['expect']) {
        headers.append('expect', incomingHttpHeaders['expect'])
    }
    if (incomingHttpHeaders['expires']) {
        headers.append('expires', incomingHttpHeaders['expires'])
    }
    if (incomingHttpHeaders['forwarded']) {
        headers.append('forwarded', incomingHttpHeaders['forwarded'])
    }
    if (incomingHttpHeaders['from']) {
        headers.append('from', incomingHttpHeaders['from'])
    }
    if (incomingHttpHeaders['host']) {
        headers.append('host', incomingHttpHeaders['host'])
    }
    if (incomingHttpHeaders['if-match']) {
        headers.append('if-match', incomingHttpHeaders['if-match'])
    }
    if (incomingHttpHeaders['if-modified-since']) {
        headers.append('if-modified-since', incomingHttpHeaders['if-modified-since'])
    }
    if (incomingHttpHeaders['if-none-match']) {
        headers.append('if-none-match', incomingHttpHeaders['if-none-match'])
    }
    if (incomingHttpHeaders['if-unmodified-since']) {
        headers.append('if-unmodified-since', incomingHttpHeaders['if-unmodified-since'])
    }
    if (incomingHttpHeaders['last-modified']) {
        headers.append('last-modified', incomingHttpHeaders['last-modified'])
    }
    if (incomingHttpHeaders['location']) {
        headers.append('location', incomingHttpHeaders['location'])
    }
    if (incomingHttpHeaders['origin']) {
        headers.append('origin', incomingHttpHeaders['origin'])
    }
    if (incomingHttpHeaders['pragma']) {
        headers.append('pragma', incomingHttpHeaders['pragma'])
    }
    if (incomingHttpHeaders['proxy-authenticate']) {
        headers.append('proxy-authenticate', incomingHttpHeaders['proxy-authenticate'])
    }
    if (incomingHttpHeaders['proxy-authorization']) {
        headers.append('proxy-authorization', incomingHttpHeaders['proxy-authorization'])
    }
    if (incomingHttpHeaders['public-key-pins']) {
        headers.append('public-key-pins', incomingHttpHeaders['public-key-pins'])
    }
    if (incomingHttpHeaders['range']) {
        headers.append('range', incomingHttpHeaders['range'])
    }
    if (incomingHttpHeaders['referer']) {
        headers.append('referer', incomingHttpHeaders['referer'])
    }
    if (incomingHttpHeaders['retry-after']) {
        headers.append('retry-after', incomingHttpHeaders['retry-after'])
    }
    if (incomingHttpHeaders['sec-websocket-accept']) {
        headers.append('sec-websocket-accept', incomingHttpHeaders['sec-websocket-accept'])
    }
    if (incomingHttpHeaders['sec-websocket-extensions']) {
        headers.append('sec-websocket-extensions', incomingHttpHeaders['sec-websocket-extensions'])
    }
    if (incomingHttpHeaders['sec-websocket-key']) {
        headers.append('sec-websocket-key', incomingHttpHeaders['sec-websocket-key'])
    }
    if (incomingHttpHeaders['sec-websocket-protocol']) {
        headers.append('sec-websocket-protocol', incomingHttpHeaders['sec-websocket-protocol'])
    }
    if (incomingHttpHeaders['sec-websocket-version']) {
        headers.append('sec-websocket-version', incomingHttpHeaders['sec-websocket-version'])
    }
    if (incomingHttpHeaders['strict-transport-security']) {
        headers.append('strict-transport-security', incomingHttpHeaders['strict-transport-security'])
    }
    if (incomingHttpHeaders['tk']) {
        headers.append('tk', incomingHttpHeaders['tk'])
    }
    if (incomingHttpHeaders['trailer']) {
        headers.append('trailer', incomingHttpHeaders['trailer'])
    }
    if (incomingHttpHeaders['transfer-encoding']) {
        headers.append('transfer-encoding', incomingHttpHeaders['transfer-encoding'])
    }
    if (incomingHttpHeaders['upgrade']) {
        headers.append('upgrade', incomingHttpHeaders['upgrade'])
    }
    if (incomingHttpHeaders['user-agent']) {
        headers.append('user-agent', incomingHttpHeaders['user-agent'])
    }
    if (incomingHttpHeaders['vary']) {
        headers.append('vary', incomingHttpHeaders['vary'])
    }
    if (incomingHttpHeaders['via']) {
        headers.append('via', incomingHttpHeaders['via'])
    }
    if (incomingHttpHeaders['warning']) {
        headers.append('warning', incomingHttpHeaders['warning'])
    }
    if (incomingHttpHeaders['www-authenticate']) {
        headers.append('www-authenticate', incomingHttpHeaders['www-authenticate'])
    }
    // IP
    if (incomingHttpHeaders['x-forwarded-for']) {
        headers.append('x-forwarded-for', incomingHttpHeaders['x-forwarded-for'].toString())
    } else if (incomingHttpHeaders['Proxy-Client-IP']) {
        headers.append('Proxy-Client-IP', incomingHttpHeaders['Proxy-Client-IP'].toString())
    } else if (incomingHttpHeaders['WL-Proxy-Client-IP']) {
        headers.append('WL-Proxy-Client-IP', incomingHttpHeaders['WL-Proxy-Client-IP'].toString())
    } else if (incomingHttpHeaders['HTTP_CLIENT_IP']) {
        headers.append('HTTP_CLIENT_IP', incomingHttpHeaders['HTTP_CLIENT_IP'].toString())
    } else if (incomingHttpHeaders['HTTP_X_FORWARDED_FOR']) {
        headers.append('HTTP_X_FORWARDED_FOR', incomingHttpHeaders['HTTP_X_FORWARDED_FOR'].toString())
    } else if (incomingHttpHeaders['X-Real-IP']) {
        headers.append('X-Real-IP', incomingHttpHeaders['X-Real-IP'].toString())
    } else if (incomingHttpHeaders['Cf-Connecting-IP']) {
        headers.append('Cf-Connecting-IP', incomingHttpHeaders['Cf-Connecting-IP'].toString())
    } else if (incomingHttpHeaders['AWS-APIGateway-sourceIp']) {
        headers.append('AWS-APIGateway-sourceIp', incomingHttpHeaders['AWS-APIGateway-sourceIp'].toString())
    } else if (remoteAddress) {
        if (remoteAddress.startsWith('::ffff:')) {
            remoteAddress = remoteAddress.replace('::ffff:', '')
        }
        headers.append('x-forwarded-for', remoteAddress)
    }
    return headers
}