// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type {NextApiRequest, NextApiResponse} from 'next'
import {server} from '../../../../config'
import * as Fetch from "../../../../utils/request"
import APIResult = API.APIResult
import UploadObjectVo = API.UploadObjectVo
import {convertToHeaders} from "../../../../utils/request";

const handler = async (req: NextApiRequest, res: NextApiResponse) => {
    const token = req.cookies['accessToken']
    if (token) {
        let url = `${server}/_/api/core/system/upload`
        await Fetch.postForm(url, req.body, convertToHeaders(req.headers), token).then(result => {
            const apiresult: APIResult<UploadObjectVo> = result
            if (apiresult.code == 200 && apiresult.data) {
                res.status(200).json(apiresult.data)
            } else {
                res.status(200).json(apiresult)
            }
        })
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