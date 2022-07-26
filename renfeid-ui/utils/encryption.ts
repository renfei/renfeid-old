import NodeRSA from 'node-rsa'
import * as CryptoJS from 'crypto-js'
import SecretKey = API.SecretKey

// RSA 公钥加密
export const rsaEncryptByPublicKey = (plaintext: string, publicKey: string): string | false => {
    plaintext = encodeURIComponent(plaintext)
    const key = new NodeRSA(publicKey, "pkcs8-public")
    key.setOptions({encryptionScheme: 'pkcs1'})
    return key.encrypt(plaintext, "base64")
}

// RSA 私钥解密
export const rsaDecryptByPrivateKey = (ciphertext: string, privateKey: string): string | false => {
    const key = new NodeRSA(privateKey, "pkcs8-private")
    key.setOptions({encryptionScheme: 'pkcs1'})
    return key.decrypt(ciphertext, "utf8")
}

// 生成RSA秘钥对
export const generateRsaKey = (): SecretKey => {
    const crypt = new NodeRSA({b: 512})
    return {
        uuid: '',
        publicKey: crypt.exportKey("pkcs8-public-pem"),
        privateKey: crypt.exportKey("pkcs8-private-pem"),
    }
}

// AES 加密 AES/CBC/PKCS7Padding
export const aesEncrypt = (plaintext: string, aesKey: string): string => {
    const key = CryptoJS.enc.Utf8.parse(aesKey)
    const iv = CryptoJS.enc.Utf8.parse(aesKey)
    const srcs = CryptoJS.enc.Utf8.parse(plaintext)
    return CryptoJS.AES.encrypt(srcs, key, {
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
    }).toString()
}

// AES 解密 AES/CBC/PKCS7Padding
export const aesDecrypt = (ciphertext: string, aesKey: string): string => {
    const key = CryptoJS.enc.Utf8.parse(aesKey)//Latin1 w8m31+Yy/Nw6thPsMpO5fg==
    const decrypt = CryptoJS.AES.decrypt(ciphertext, key, {mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7})
    return CryptoJS.enc.Utf8.stringify(decrypt).toString()
}
