import * as Fetch from '../../utils/request'
import SecretKey = API.SecretKey;
import SignInAo = API.SignInAo;

export const requestServerSecretKey = async () => {
    let url = `/api/auth/secretKey`
    return Fetch.get(url)
}

export const settingClientSecretKey = async (clientSecretKey: SecretKey) => {
    let url = `/api/auth/secretKey`
    return Fetch.post(url, clientSecretKey)
}

export const signIn = async (signInAo: SignInAo) => {
    let url = `/api/auth/signIn`
    return Fetch.post(url, signInAo)
}

export const requestCurrentUserInfo = async () => {
    let url = `/api/auth/current/user`
    return Fetch.get(url)
}
