// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type {NextApiRequest, NextApiResponse} from 'next'
import {server} from '../../../../config'
import * as Fetch from '../../../../utils/request'
import {convertToHeaders} from "../../../../utils/request";

const handler = async (req: NextApiRequest, res: NextApiResponse) => {
    const token = req.cookies['accessToken']
    if (token) {
        let url = `${server}/_/api/cms/posts?`
        if (req.method === 'GET') {
            if (req.query.categoryId) {
                url += 'categoryId=' + req.query.categoryId + '&'
            }
            if (req.query.title) {
                url += 'title=' + req.query.title + '&'
            }
            if (req.query.postStatus) {
                url += 'postStatus=' + req.query.postStatus + '&'
            }
            if (req.query.startDate) {
                url += 'startDate=' + req.query.startDate + '&'
            }
            if (req.query.endDate) {
                url += 'endDate=' + req.query.endDate + '&'
            }
            if (req.query.pages) {
                url += 'pages=' + req.query.pages + '&'
            }
            if (req.query.rows) {
                url += 'rows=' + req.query.rows + '&'
            }
            await Fetch.get(url, convertToHeaders(req.headers), token).then(result => {
                res.status(200).json(result)
            })
        } else if (req.method === 'POST') {
            await Fetch.post(url, req.body, convertToHeaders(req.headers), token).then(result => {
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