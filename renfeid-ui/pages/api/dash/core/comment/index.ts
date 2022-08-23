// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type { NextApiRequest, NextApiResponse } from 'next'
import * as Fetch from '../../../../../utils/request'
import { convertToHeaders } from "../../../../../utils/request"
import ListData = API.ListData
import Comment = API.Comment

const handler = async (req: NextApiRequest, res: NextApiResponse) => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    // 预览模式
    if (req.method === 'GET') {
      let postVos: Comment[] = []
      for (let i = 1; i < 11; i++) {
        postVos.push({
          id: i + '',
          sysType: 'POSTS',
          objectId: i + '',
          authorId: '',
          addtime: '2022-04-02 12:25:13',
          isDelete: false,
          parentId: '',
          isOwner: false,
          author: '预览模拟用户',
          authorEmail: 'preview@renfei.net',
          authorUrl: 'https://www.renfei.net',
          authorIp: '123.123.123.123',
          authorAddress: 'Beijing, Beijing, China',
          content: '这里是预览模拟评论的内容。',
        })
      }
      let listData: ListData<Comment> = {
        pageNum: 1,
        pageSize: 10,
        startRow: 0,
        endRow: 0,
        total: 10,
        pages: 1,
        data: postVos
      }

      res.status(200).json({
        code: 200,
        message: 'OK',
        timestamp: new Date().valueOf(),
        signature: '',
        nonce: '',
        data: listData
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
    const token = req.cookies['accessToken']
    if (token) {
      let url = `/_/api/core/comment?`
      if (req.method === 'GET') {
        if (req.query.haveDelete) {
          url += 'haveDelete=' + req.query.haveDelete + '&'
        }
        if (req.query.pages) {
          url += 'pages=' + req.query.pages + '&'
        }
        if (req.query.rows) {
          url += 'rows=' + req.query.rows + '&'
        }
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
}

export default handler