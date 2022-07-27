import * as Fetch from '../../../../utils/request'

export const uploadFile = async (token: string, headers: Headers, file: any) => {
    const url = `/_/api/core/system/upload`
    const formData = new FormData()
    formData.append('file', file)

    return await Fetch.postForm(url, formData, headers, token, true)
        .then((res: any) => {
            return res.json()
        }).catch((error: any) => {
            return Promise.reject(error)
        })
}