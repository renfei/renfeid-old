import * as Fetch from '../../utils/request'
import ListData = API.ListData
import SearchItem = API.SearchItem
import HotSearch = API.HotSearch

const previewPostFeaturedImage = `https://cdn.renfei.net/upload/2022/daba120e37f64adfa6c14e6dc0f1e770.webp`
const previewPostContent = `
 <img src="${previewPostFeaturedImage}" lat="开发预览版 - Developer Preview" />
<p style="text-indent:2em">这是开发预览版，在Git仓库中提交后将自动部署最新的代码，提供最新的开发预览版本。这里仅限开源部分，私有闭源部分不提供预览版，预览版不是稳定版本，只是为了快速验证代码，任何功能都可能被移除，仅供参考。开发预览版仅支持预览，不支持任何数据新增、修改、删除操作。</p>
<p style="text-indent:2em">后台管理系统预览：<a href="/dashboard" target="_blank" rel="nofollow noopener">https://preview.renfei.net/dashboard</a></p>
<p style="text-indent:2em"></p>
<p style="text-indent:2em">代码仓库位于：<a href="https://github.com/renfei/renfeid" target="_blank" rel="nofollow noopener">https://github.com/renfei/renfeid</a></p>
<p style="text-indent:2em">如果您有反馈或意见，欢迎到社区参与反馈讨论：<a href="https://github.com/renfei/feedback/discussions" target="_blank" rel="nofollow noopener">https://github.com/renfei/feedback/discussions</a></p>
<p style="text-indent:2em"></p>
<p style="text-indent:2em">当前页面为文章内容的详情页，用于验证内容布局、引用图片、引用代码、引用视频等功能是否正常。</p>
<p style="text-indent:2em">下方将引用代码：</p>
<pre class="language-java"><code>public static void main(String[]args){
        System.out.println("Test");
}</code></pre>
<p style="text-indent:2em">下方将引用视频：</p>
<iframe src="//player.bilibili.com/player.html?aid=247083339&bvid=BV1wv411a75q&cid=309606428&page=1" style="width:100%;height:500px;" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
<p style="text-indent:2em"></p>
`

export const search = async (headers: Headers, w: string, type?: string, p?: string, token?: string) => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    let searchItem: SearchItem[] = []
    for (let i = 1; i <= 10; i++) {
      searchItem.push({
        uuid: i.toString(),
        originalId: i.toString(),
        title: `【${i}】开发预览版 - Developer Preview`,
        content: previewPostContent,
        url: `https://preview.renfei.net/posts/${i}`,
        type: 'POSTS',
        date: '2022-07-22 15:10:30',
        image: previewPostFeaturedImage
      })
    }
    let listData: ListData<SearchItem> = {
      pageNum: 1,
      pageSize: 10,
      startRow: 0,
      endRow: 0,
      total: 10,
      pages: 1,
      data: searchItem
    }

    return {
      code: 200,
      message: '0.000351',
      timestamp: new Date().valueOf(),
      signature: '',
      nonce: '',
      data: listData
    }
  } else {
    let url = `/-/api/search?w=${w}`
    if (type) {
      url += `&type=${type}`
    }
    if (p) {
      p += `&p=${p}`
    }
    return Fetch.get(url, headers, token, true)
  }
}

export const queryHotSearchList = async (headers: Headers, size?: string, token?: string) => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    let searchItem: HotSearch[] = []
    for (let i = 1; i <= 15; i++) {
      searchItem.push({
        word: `预览模式热词模拟-${i}`,
        cont: ''
      })
    }

    return {
      code: 200,
      message: 'OK',
      timestamp: new Date().valueOf(),
      signature: '',
      nonce: '',
      data: searchItem
    }
  } else {
    let url = `/-/api/search/hot`
    if (size) {
      size += `?size=${size}`
    }
    return Fetch.get(url, headers, token, true)
  }
}

export const clientQueryHotSearchList = async () => {
  return fetch('/api/search/hot?size=15', {
    method: 'GET',
  }).then((res: any) => {
    return res.json()
  }).catch((error: any) => {
    return Promise.reject(error)
  })
}
