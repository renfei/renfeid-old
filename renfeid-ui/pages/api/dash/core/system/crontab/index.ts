// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type { NextApiRequest, NextApiResponse } from 'next'
import * as Fetch from '../../../../../../utils/request'
import { convertToHeaders } from "../../../../../../utils/request"
import CronJobVo = API.CronJobVo

const handler = async (req: NextApiRequest, res: NextApiResponse) => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    // 预览模式
    if (req.method === 'GET') {
      let cronJobs: CronJobVo[] = []
      for (let i = 1; i < 10; i++) {
        cronJobs.push({
          jobName: `演示定时任务-${i}`,
          groupName: '演示定时任务组-A',
          className: 'net.renfei.common.core.jobs.ExampleJob',
          cronExpression: '0 5 3 ? * * *',
          state: 'NORMAL',
          nextFireTime: '',
          param: {
            name: 'zhangsan',
            age: 12
          },
        })
      }
      res.status(200).json({
        code: 200,
        message: 'OK',
        timestamp: new Date().valueOf(),
        signature: '',
        nonce: '',
        data: cronJobs
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
      let url = `/_/api/core/system/crontab`
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
}

export default handler