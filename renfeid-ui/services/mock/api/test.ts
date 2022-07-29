import { NextApiRequest, NextApiResponse } from "next"
import APIResult = API.APIResult

const handler = (req: NextApiRequest, res: NextApiResponse) => {
  let result: APIResult<any> = {
    code: 200,
    message: '这是 Mock API Test',
    timestamp: new Date().valueOf(),
    signature: '',
    nonce: '',
  }
  res
    .status(200)
    .json(result)
}

export default handler