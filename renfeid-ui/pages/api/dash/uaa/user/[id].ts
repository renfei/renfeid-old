// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type { NextApiRequest, NextApiResponse } from 'next'
import * as Fetch from "../../../../../utils/request"
import { convertToHeaders } from "../../../../../utils/request"

const handler = async (req: NextApiRequest, res: NextApiResponse) => {
    const token = req.cookies['accessToken']
    if (token) {
        let url = `/_/api/uaa/user/${req.query.id}`
        if (req.method === 'PUT') {
            await Fetch.put(url, req.body, convertToHeaders(req.headers), token, true).then(result => {
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
    } else {
        res.status(401).json({
            code: 401,
            message: '需要身份验证。',
            timestamp: new Date().valueOf(),
            signature: '',
            nonce: ''
        })
    }
}

export default handler