import * as Fetch from '../../utils/request'
import APIResult = API.APIResult
import SiteFriendlyLinkVo = API.SiteFriendlyLinkVo


export const queryFriendlyLink = async (headers: Headers, token?: string) => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    // 预览模式
    let siteFriendlyLinkVos: SiteFriendlyLinkVo[] = [
      {
        text: '任霏博客',
        link: 'https://www.renfei.net',
      },
    ]
    let result: APIResult<SiteFriendlyLinkVo[]> = {
      code: 200,
      message: 'ok',
      timestamp: new Date().valueOf(),
      signature: '',
      nonce: '',
      data: siteFriendlyLinkVos
    }
    return result
  } else {
    let url = `/-/api/core/friendlylink`
    return Fetch.get(url, headers, token, true)
  }
}
