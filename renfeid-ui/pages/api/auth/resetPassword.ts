// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type { NextApiRequest, NextApiResponse } from 'next'
import * as Fetch from '../../../utils/request'
import SecretKey = API.SecretKey
import APIResult = API.APIResult
import { convertToHeaders } from "../../../utils/request"

const handler = async (req: NextApiRequest, res: NextApiResponse<APIResult<SecretKey>>) => {
  try {
    if (req.method === 'PUT') {
      let url = `/api/auth/resetPassword`
      await Fetch.put(url, req.body, convertToHeaders(req.headers, req.socket.remoteAddress), req.cookies['accessToken'], true).then(result => {
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
