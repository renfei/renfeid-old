// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type { NextApiRequest, NextApiResponse } from 'next'
import * as Fetch from '../../../../utils/request'
import UserInfo = API.UserInfo
import APIResult = API.APIResult
import { convertToHeaders } from "../../../../utils/request"

const handler = async (req: NextApiRequest, res: NextApiResponse<APIResult<UserInfo>>) => {
    if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
        // 预览模式
        res.status(200).json({
            code: 200,
            message: 'OK',
            timestamp: new Date().valueOf(),
            signature: '',
            nonce: '',
            data: {
                email: 'preview@renfei.net',
                emailVerified: true,
                firstName: 'fei',
                id: '1',
                lastName: 'ren',
                phone: '13001000000',
                phoneVerified: false,
                registrationDate: '2012-1-1',
                registrationIp: '123.123.123.123',
                secretLevel: '',
                username: 'preview',
                uuid: ''
            }
        })
    } else {
        if (req.cookies['accessToken']) {
            try {
                if (req.method === 'GET') {
                    let url = `/api/auth/current/user`
                    await Fetch.get(url, convertToHeaders(req.headers), req.cookies['accessToken'], true).then(result => {
                        res.status(200).json(result)
                    })
                } else {
                    res.status(405).json({
                        code: 405,
                        message: '不支持的请求方法。',
                        timestamp: new Date().valueOf(),
                        signature: '',
                        nonce: ''
                    })
                }
            } catch (error: any) {
                if (error.code == 'ECONNREFUSED') {
                    res.status(502).json({
                        code: 502,
                        message: '服务暂时不可用，请稍后再试。',
                        timestamp: new Date().valueOf(),
                        signature: '',
                        nonce: ''
                    })
                } else {
                    res.status(500).json({
                        code: 500,
                        message: '服务暂时不可用，请稍后再试。',
                        timestamp: new Date().valueOf(),
                        signature: '',
                        nonce: ''
                    })
                }
            }
        } else {
            res.status(401).json({
                code: 401,
                message: '需要身份认证。',
                timestamp: new Date().valueOf(),
                signature: '',
                nonce: ''
            })
        }
    }
}

export default handler
