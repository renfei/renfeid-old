// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type {NextApiRequest, NextApiResponse} from 'next'
import {server} from '../../../../config'
import * as Fetch from "../../../../utils/request"
import APIResult = API.APIResult
import UploadObjectVo = API.UploadObjectVo
import {convertToHeaders} from "../../../../utils/request"
import fs from 'fs'
import FormData from 'form-data'
import formidable, {File} from 'formidable'

export const config = {
    api: {
        bodyParser: false
    },
}

type ProcessedFiles = Array<[string, File]>

const handler = async (req: NextApiRequest, res: NextApiResponse) => {
    const token = req.cookies['accessToken']
    if (token) {
        let url = `${server}/_/api/core/system/upload`
        /* Get files using formidable */
        const files = await new Promise<ProcessedFiles | undefined>((resolve, reject) => {
            const form = new formidable.IncomingForm();
            const files: ProcessedFiles = [];
            form.on('file', function (field, file) {
                files.push([field, file]);
            })
            form.on('end', () => resolve(files));
            form.on('error', err => reject(err));
            form.parse(req, () => {
                //
            });
            return files
        }).catch(e => {
            console.error(e);
            res.status(500).json({
                code: 500,
                message: '内部服务器错误。',
                timestamp: new Date().valueOf(),
                signature: '',
                nonce: ''
            })
        })

        if (files?.length) {
            /* Add files to FormData */
            const formData: any = new FormData();
            for (const file of files) {
                const tempPath = file[1].filepath;
                const newPath = `${process.env.RENFEID_UPLOAD_DIR}` + file[1].originalFilename
                await fs.rename(tempPath, newPath,(err)=>{
                   if(err){
                       console.error(err)
                   }
                })
                formData.append(file[0], fs.createReadStream(newPath));
            }
            await Fetch.postForm(url, formData, convertToHeaders(formData.getHeaders()), token).then(result => {
                const apiresult: APIResult<UploadObjectVo> = result
                if (apiresult.code == 200 && apiresult.data) {
                    res.status(200).json(apiresult.data)
                } else {
                    res.status(200).json(apiresult)
                }
            })
        } else {
            console.error("未收到文件");
            res.status(400).json({
                code: 400,
                message: '未收到文件。',
                timestamp: new Date().valueOf(),
                signature: '',
                nonce: ''
            })
        }
    } else {
        res.status(401).json({
            code: 401,
            message: '需要身份验证。',
            timestamp: new Date().valueOf(),
            signature: '',
            nonce: ''
        })
    }
}

export default handler