import * as Fetch from '../../utils/request'
import CommentAo = API.CommentAo
import Comment = API.Comment

export const queryCommentTree = async (headers: Headers, systemTypeEnum: string, id: string, token?: string) => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    // 预览模式
    return {
      code: 200,
      message: 'OK',
      timestamp: new Date().valueOf(),
      signature: '',
      nonce: '',
      data: [
        {
          id: '1',
          addtime: '2022-07-08 15:32:32',
          isOwner: false,
          author: '用户1',
          authorUrl: 'https://www.renfei.net',
          authorAddress: 'Beijing, Beijing, China',
          content: '评论演示数据',
          children: [
            {
              id: '3',
              addtime: '2022-07-08 15:32:32',
              isOwner: true,
              author: '用户3',
              authorUrl: 'https://www.renfei.net',
              authorAddress: 'Beijing, Beijing, China',
              content: '评论演示数据',
              children: [
                {
                  id: '4',
                  addtime: '2022-07-08 15:32:32',
                  isOwner: false,
                  author: '用户4',
                  authorUrl: 'https://www.renfei.net',
                  authorAddress: 'Beijing, Beijing, China',
                  content: '评论演示数据',
                },
              ]
            },
            {
              id: '5',
              addtime: '2022-07-08 15:32:32',
              isOwner: false,
              author: '用户5',
              authorUrl: 'https://www.renfei.net',
              authorAddress: 'Beijing, Beijing, China',
              content: '评论演示数据',
            },
          ]
        },
        {
          id: '2',
          addtime: '2022-07-08 15:32:32',
          isOwner: false,
          author: '用户6',
          authorUrl: 'https://www.renfei.net',
          authorAddress: 'Beijing, Beijing, China',
          content: '评论演示数据',
        }
      ]
    }
  } else {
    let url = `/-/api/comment/${systemTypeEnum}/${id}`
    return Fetch.get(url, headers, token, true)
  }
}

export const queryLastComment = async (headers: Headers, systemTypeEnum: string, quantity?: string, token?: string) => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    let comments: Comment[] = []
    for (let i = 1; i <= 10; i++) {
      comments.push({
        id: i.toString(),
        sysType: systemTypeEnum,
        objectId: `${i}`,
        authorId: `${i}`,
        addtime: '2022-07-08 15:32:32',
        isDelete: false,
        isOwner: false,
        author: 'preview',
        authorEmail: 'preview@renfei.net',
        authorUrl: '',
        authorIp: '123.123.123.123',
        authorAddress: '',
        content: '评论演示数据',
      })
    }
    // 预览模式
    return {
      code: 200,
      message: 'OK',
      timestamp: new Date().valueOf(),
      signature: '',
      nonce: '',
      data: comments
    }
  } else {
    let url = `/-/api/comment/${systemTypeEnum}/last`
    if (quantity) {
      url += "?quantity=" + quantity
    }
    return Fetch.get(url, headers, token, true)
  }
}

export const submitComments = async (systemTypeEnum: string, id: string, comment: CommentAo) => {
  const url = `/api/comment/${systemTypeEnum}/${id}`
  return await fetch(url, {
    method: 'POST',
    body: JSON.stringify(comment),
    headers: {
      'content-type': 'application/json',
      'Authorization': '',
    }
  }).then((res: any) => {
    return res.json()
  }).catch((error: any) => {
    return Promise.reject(error)
  })
}
