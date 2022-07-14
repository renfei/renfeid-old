// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type {NextApiRequest, NextApiResponse} from 'next'
import * as Fetch from '../../../../utils/request'
import SecretKey = API.SecretKey;
import APIResult = API.APIResult;

const handler = async (req: NextApiRequest, res: NextApiResponse<APIResult<SecretKey>>) => {
    if (req.cookies['accessToken']) {
        try {
            if (req.method === 'GET') {
                let url = `${process.env.NEXT_PUBLIC_RENFEID_SERVICE_API}/api/auth/signIn`
                await Fetch.get(url, req.cookies['accessToken']).then(result => {
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

export default handler
