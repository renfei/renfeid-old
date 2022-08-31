// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type { NextApiRequest, NextApiResponse } from 'next'
import * as Fetch from "../../../utils/request"
import { convertToHeaders } from "../../../utils/request"
import HotSearch = API.HotSearch

const handler = async (req: NextApiRequest, res: NextApiResponse) => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    // 预览模式
    let searchItem: HotSearch[] = []
    for (let i = 1; i <= 15; i++) {
      searchItem.push({
        word: `预览模式热词模拟-${i}`,
        cont: ''
      })
    }
    res.status(403).json({
      code: 200,
      message: 'OK',
      timestamp: new Date().valueOf(),
      signature: '',
      nonce: '',
      data: searchItem
    })
  } else {
    const token = req.cookies['accessToken']
    let url = `/-/api/search/hot?size=${req.query.size}`
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
  }
}

export default handler