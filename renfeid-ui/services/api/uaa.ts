import * as Fetch from '../../utils/request'
import SecretKey = API.SecretKey
import SignInAo = API.SignInAo
import SignUpAo = API.SignUpAo
import SignUpActivationAo = API.SignUpActivationAo
import APIResult = API.APIResult
import ListData = API.ListData
import UserSignInLog = API.UserSignInLog
import TotpVo = API.TotpVo
import TotpAo = API.TotpAo
import UpdatePasswordAo = API.UpdatePasswordAo
import ResetPasswordAo = API.ResetPasswordAo
import FindUsernameAo = API.FindUsernameAo

export const requestServerSecretKey = async () => {
    let url = `/api/auth/secretKey`
    return await fetch(url, {
        method: 'GET',
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

export const settingClientSecretKey = async (clientSecretKey: SecretKey) => {
    let url = `/api/auth/secretKey`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(clientSecretKey),
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

export const signIn = async (signInAo: SignInAo) => {
    let url = `/api/auth/signIn`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(signInAo),
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

export const signUp = async (signUpAo: SignUpAo) => {
    let url = `/api/auth/signUp`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(signUpAo),
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

export const signUpActivation = async (signUpActivationAo: SignUpActivationAo) => {
    let url = `/api/auth/signUp/activation`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(signUpActivationAo),
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

export const signOut = async () => {
    let url = `/api/auth/signOut`
    return await fetch(url, {
        method: 'DELETE',
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

export const requestCurrentUserInfo = async () => {
    let url = `/api/auth/current/user`
    return await fetch(url, {
        method: 'GET',
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

export const queryCurrentUserSignInLog = async (headers: Headers, page: number, rows: number, token: string) => {
    if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
        // 预览模式
        let result: APIResult<ListData<UserSignInLog>> = {
            code: 200,
            message: 'ok',
            timestamp: new Date().valueOf(),
            signature: '',
            nonce: '',
            data: {
                pageNum: 1,
                pageSize: 1,
                startRow: 1,
                endRow: 1,
                total: 1,
                pages: 1,
                data: [
                    {
                        logTime: '2022-08-08 10:18:51',
                        userUuid: '7A159BF2BCB94B28BD185AC868169197',
                        userName: 'preview',
                        requIp: '123.123.123.123',
                        requAgent: '',
                        address: 'Beijing, Beijing',
                    }
                ],
            }
        }
        return result
    } else {
        let url = `/api/auth/current/signin/log?pages=${page}&rows=${rows}`
        return Fetch.get(url, headers, token, true)
    }
}

export const generateU2FSecretKey = async (headers: Headers, token: string) => {
    if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
        // 预览模式
        let result: APIResult<TotpVo> = {
            code: 200,
            message: 'ok',
            timestamp: new Date().valueOf(),
            signature: '',
            nonce: '',
            data: {
                secretKey: '',
                totpString: '',
            }
        }
        return result
    } else {
        let url = `/api/auth/current/u2f/secret`
        return Fetch.get(url, headers, token, true)
    }
}

export const openU2f = async (totp: TotpAo) => {
    let url = `/api/auth/current/u2f`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(totp),
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

export const closeU2f = async (totp: TotpAo) => {
    let url = `/api/auth/current/u2f`
    return await fetch(url, {
        method: 'PUT',
        body: JSON.stringify(totp),
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

export const updatePassword = async (updatePassword: UpdatePasswordAo) => {
    let url = `/api/auth/current/password`
    return await fetch(url, {
        method: 'PUT',
        body: JSON.stringify(updatePassword),
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

export const sendFindPasswordVerCode = async (account: String) => {
    let url = `/api/auth/findPassword/${account}`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(""),
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

export const sendFindUsernameVerCode = async (account: String) => {
    let url = `/api/auth/findUsername/${account}`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(""),
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

export const resetPasswordByVerCode = async (resetPassword: ResetPasswordAo) => {
    let url = `/api/auth/resetPassword`
    return await fetch(url, {
        method: 'PUT',
        body: JSON.stringify(resetPassword),
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

export const findUsernameByVerCode = async (findUsername: FindUsernameAo) => {
    let url = `/api/auth/findUsername`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(findUsername),
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
