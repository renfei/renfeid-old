export const sendEmailVerCode = async (newEmail: string) => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    // 预览模式
    return {
      code: 403,
      message: '预览模式，仅支持查看，不支持数据修改。',
      timestamp: new Date().valueOf(),
      signature: '',
      nonce: ''
    }
  } else {
    const url = `/api/account/email/verCode?newEmail=${newEmail}`
    return await fetch(url, {
      method: 'POST',
      body: JSON.stringify({}),
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
}

export const updateEmail = async (newEmail: string, verCode: string) => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    // 预览模式
    return {
      code: 403,
      message: '预览模式，仅支持查看，不支持数据修改。',
      timestamp: new Date().valueOf(),
      signature: '',
      nonce: ''
    }
  } else {
    const url = `/api/account/email?newEmail=${newEmail}&verCode=${verCode}`
    return await fetch(url, {
      method: 'POST',
      body: JSON.stringify({}),
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
}

export const sendPhoneVerCode = async (newPhone: string) => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    // 预览模式
    return {
      code: 403,
      message: '预览模式，仅支持查看，不支持数据修改。',
      timestamp: new Date().valueOf(),
      signature: '',
      nonce: ''
    }
  } else {
    const url = `/api/account/phone/verCode?newPhone=${newPhone}`
    return await fetch(url, {
      method: 'POST',
      body: JSON.stringify({}),
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
}

export const updatePhone = async (newPhone: string, verCode: string) => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    // 预览模式
    return {
      code: 403,
      message: '预览模式，仅支持查看，不支持数据修改。',
      timestamp: new Date().valueOf(),
      signature: '',
      nonce: ''
    }
  } else {
    const url = `/api/account/phone?newPhone=${newPhone}&verCode=${verCode}`
    return await fetch(url, {
      method: 'POST',
      body: JSON.stringify({}),
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
}

export const updateFirstName = async (firstName: string, lastName: string) => {
  if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
    // 预览模式
    return {
      code: 403,
      message: '预览模式，仅支持查看，不支持数据修改。',
      timestamp: new Date().valueOf(),
      signature: '',
      nonce: ''
    }
  } else {
    const url = `/api/account/firstName?firstName=${firstName}&lastName=${lastName}`
    return await fetch(url, {
      method: 'POST',
      body: JSON.stringify({}),
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
}