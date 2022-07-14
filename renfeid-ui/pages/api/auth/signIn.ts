// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type {NextApiRequest, NextApiResponse} from 'next'
import * as Fetch from '../../../utils/request'
import {setCookie} from '../../../utils/cookies'
import SecretKey = API.SecretKey;
import APIResult = API.APIResult;

const handler = async (req: NextApiRequest, res: NextApiResponse<APIResult<SecretKey>>) => {
    try {
        if (req.method === 'POST') {
            let url = `${process.env.NEXT_PUBLIC_RENFEID_SERVICE_API}/api/auth/signIn`
            await Fetch.post(url, req.body, req.cookies['accessToken']).then(result => {
                if (result.code == 200) {
                    setCookie(res, 'accessToken', result.data.accessToken, {
                        domain: 'renfei.net',
                        expires: new Date(Date.now() + (8 * 60 * 60 * 1000)),
                        httpOnly: true,
                        path: '/',
                    })
                }
                res.status(200).json(result)
            })
        } else {
            res.status(200).json({
                code: 405,
                message: '不支持的请求方法。',
                timestamp: new Date().valueOf(),
                signature: '',
                nonce: ''
            })
        }
    } catch (error: any) {
        console.error(error)
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
}

export default handler
