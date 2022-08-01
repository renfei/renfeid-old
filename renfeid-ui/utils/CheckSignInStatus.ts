import nookies from 'nookies'
import * as Fetch from '../utils/request'
import { convertToHeaders } from "../utils/request"
import APIResult = API.APIResult
import UserInfo = API.UserInfo

// 检查登录状态
const CheckSignInStatus = async (context: any): Promise<UserInfo | undefined> => {
  const accessToken = nookies.get(context)['accessToken']
  if (accessToken) {
    let url = `/api/auth/current/user`
    const result: APIResult<UserInfo> = await Fetch.get(url, convertToHeaders(context.req.headers), accessToken, true)
    if (result.code == 401) {
      nookies.destroy(context, 'accessToken')
      return undefined
    } else if (result.code == 200) {
      return result.data
    }
  }
  return undefined
}

export default CheckSignInStatus