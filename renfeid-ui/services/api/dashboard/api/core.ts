export const uploadFile = async (token: string, file: any) => {
    const url = `${process.env.RENFEID_SERVICE_API}/_/api/core/system/upload`
    const formData = new FormData()
    formData.append('file', file)
    return await fetch(url, {
        method: 'POST',
        body: formData
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}