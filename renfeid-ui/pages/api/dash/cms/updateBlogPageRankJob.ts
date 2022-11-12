// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type { NextApiRequest, NextApiResponse } from 'next'
import { getPosts } from '../../../../../services/api'
import * as Fetch from '../../../../../utils/request'
import { convertToHeaders } from "../../../../../utils/request"
import ListData = API.ListData
import DashPost = API.DashPost

const handler = async (req: NextApiRequest, res: NextApiResponse) => {
    const token = req.cookies['accessToken']
        if (token) {
            let url = `/_/api/cms/updateBlogPageRankJob`
            if (req.method === 'GET') {
                await Fetch.get(url, convertToHeaders(req.headers, req.socket.remoteAddress), token, true).then(result => {
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