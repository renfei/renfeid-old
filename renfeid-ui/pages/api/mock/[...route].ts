import type { NextApiRequest, NextApiResponse } from 'next'
import { findService, serviceName } from '../../../lib'
import APIResult = API.APIResult

// Mock API 处理，模拟的地方在 ~/service/mock/**
const handler = async (req: NextApiRequest, res: NextApiResponse) => {
  const service = await findService(req)
  let result: APIResult<any> = {
    code: 200,
    message: '',
    timestamp: new Date().valueOf(),
    signature: '',
    nonce: '',
  }

  if (!service) {
    result = {
      code: 501,
      message: 'the requested handler was not found',
      timestamp: new Date().valueOf(),
      signature: '',
      nonce: '',
      data: serviceName(req)
    }
    res
      .status(501)
      .json(result)

    return
  }

  service(req, res)
}

export default handler