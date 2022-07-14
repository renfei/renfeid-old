import SecretKey = API.SecretKey;
import * as api from '../services/api'
import * as encryptionUtils from '../utils/encryption'
import APIResult = API.APIResult;
import {message} from 'antd';

// 获取 AES Key，秘钥交换逻辑参考：https://www.renfei.net/posts/1003346
export const getAesKey = async (): Promise<SecretKey | false> => {
    const serverSecretKeyResult: APIResult<SecretKey> = await api.requestServerSecretKey()
    if (serverSecretKeyResult.code != 200 || !serverSecretKeyResult.data) {
        message.error('获取服务器公钥失败。' + serverSecretKeyResult.message);
        return false
    }
    const clientSecretKey: SecretKey = encryptionUtils.generateRsaKey()
    // 使用服务器端公钥加密客户端公钥，注意服务器端公钥是2048，客户端公钥是512
    const clientEncryptPublicKey = encryptionUtils.rsaEncryptByPublicKey(clientSecretKey.publicKey, serverSecretKeyResult.data?.publicKey)
    if (!clientEncryptPublicKey) {
        message.error('加密客户端公钥失败');
        return false
    }
    // 将加密好的客户端公钥上报给服务器
    const settingClientSecretKeyAo: SecretKey = {
        uuid: serverSecretKeyResult.data?.uuid,
        publicKey: clientEncryptPublicKey,
        privateKey: '',
    }
    const secretKeyResult: APIResult<SecretKey> = await api.settingClientSecretKey(settingClientSecretKeyAo)
    if (secretKeyResult.code != 200 || !secretKeyResult.data) {
        message.error('上报客户端公钥失败。' + secretKeyResult.message);
        return false
    }
    // 用客户端私钥解密服务器返回的AES秘钥
    const aesKey = encryptionUtils.rsaDecryptByPrivateKey(secretKeyResult.data?.publicKey, clientSecretKey.privateKey)
    if (!aesKey) {
        message.error('解密AES秘钥失败。');
        return false
    }
    return {
        uuid: secretKeyResult.data.uuid,
        publicKey: aesKey,
        privateKey: aesKey,
    }
}