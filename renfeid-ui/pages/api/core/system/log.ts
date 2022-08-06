// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type { NextApiRequest, NextApiResponse } from 'next'
import * as Fetch from '../../../../utils/request'
import { convertToHeaders } from "../../../../utils/request"
import ListData = API.ListData
import SystemLogEntity = API.SystemLogEntity

const handler = async (req: NextApiRequest, res: NextApiResponse) => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    // 预览模式
    if (req.method === 'GET') {
      let postVos: SystemLogEntity[] = []
      for (let i = 1; i < 10; i++) {
        postVos.push({
          id: i,
          logTime: '2022-08-02 12:32:45',
          logLevel: 'INFO',
          logModule: 'SYSTEM',
          logType: 'RETRIEVE',
          requMethod: 'GET',
          requUri: '/_/api/core/system/log',
          requReferrer: 'https://preview.renfei.net/dashboard/sys/log',
          userUuid: '7A159BF2BCB94B28BD185AC868169197',
          userName: 'preview',
          requIp: '123.123.123.123',
          logDesc: '查看系统审计日志',
          requParam: '{"rows":"100"}',
          respParam: '{"code":200,"message":"成功","timestamp":1658462049,"signature":"658d54a51953ee4a8d48dcc872a7196039d91046","nonce":"1aMEKSDXoq0YVnqf","data":{"pageNum":1,"pageSize":100,"startRow":0,"endRow":100,"total":1,"pages":1,"data":[{"id":1,"enName":"default","zhName":"默认分类","secretLevel":"UNCLASSIFIED"}]}}',
          requAgent: 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36',
        })
      }
      let listData: ListData<SystemLogEntity> = {
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
      let url = `/_/api/core/system/log?`
      if (req.method === 'GET') {
        if (req.query.startDate) {
          url += 'startDate=' + req.query.startDate + '&'
        }
        if (req.query.endDate) {
          url += 'endDate=' + req.query.endDate + '&'
        }
        if (req.query.logLevel) {
          url += 'logLevel=' + req.query.logLevel + '&'
        }
        if (req.query.systemType) {
          url += 'systemType=' + req.query.systemType + '&'
        }
        if (req.query.operationType) {
          url += 'operationType=' + req.query.operationType + '&'
        }
        if (req.query.reqUri) {
          url += 'reqUri=' + req.query.reqUri + '&'
        }
        if (req.query.username) {
          url += 'username=' + req.query.username + '&'
        }
        if (req.query.reqIp) {
          url += 'reqIp=' + req.query.reqIp + '&'
        }
        if (req.query.pages) {
          url += 'pages=' + req.query.pages + '&'
        }
        if (req.query.rows) {
          url += 'rows=' + req.query.rows + '&'
        }
        await Fetch.get(url, convertToHeaders(req.headers), token, true).then(result => {
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