import nookies from 'nookies'
import * as Fetch from '../utils/request'
import { convertToHeaders } from "../utils/request"
import APIResult = API.APIResult
import UserInfo = API.UserInfo

// 检查登录状态
const CheckSignInStatus = async (context: any): Promise<UserInfo | null> => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    // 预览模式
    return {
      email: 'preview@renfei.net',
      emailVerified: true,
      firstName: 'fei',
      id: '1',
      lastName: 'ren',
      phone: '13001000000',
      phoneVerified: false,
      registrationDate: '2012-1-1',
      registrationIp: '123.123.123.123',
      secretLevel: '',
      username: 'preview',
      uuid: ''
    }
  } else {
    const accessToken = nookies.get(context)['accessToken']
    if (accessToken) {
      let url = `/api/auth/current/user`
      const result: APIResult<UserInfo> = await Fetch.get(url, convertToHeaders(context.req.headers), accessToken, true)
      if (result.code == 401) {
        nookies.destroy(context, 'accessToken')
        return null
      } else if (result.code == 200 && result.data) {
        return result.data
      } else {
        return null
      }
    }
    return null
  }
}

export default CheckSignInStatus