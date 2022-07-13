import * as Fetch from '../../utils/request'
import SecretKey = API.SecretKey;

export const requestServerSecretKey = async () => {
    let url = `/api/auth/secretKey`
    return Fetch.get(url)
}

export const settingClientSecretKey = async (clientSecretKey: SecretKey) => {
    let url = `/api/auth/secretKey`
    return Fetch.post(url, clientSecretKey)
}
