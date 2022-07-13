import type {NextApiRequest, NextApiResponse} from 'next'
import * as Fetch from "../../../utils/request";
import APIResult = API.APIResult;
import SecretKey = API.SecretKey;

const handler = (req: NextApiRequest, res: NextApiResponse) => {
    if (req.method === 'GET') {
        // Process a GET request
        let url = `${process.env.RENFEID_SERVICE_API}/api/auth/secretKey`
        Fetch.get(url).then(result => {
            const serverSecretKeyResult: APIResult<SecretKey> = result
            res.status(200).json(serverSecretKeyResult)
        })
    } else if (req.method === 'POST') {
        // Process a POST request
        let url = `${process.env.RENFEID_SERVICE_API}/api/auth/secretKey`
        Fetch.post(url, req.body).then(result => {
            const serverSecretKeyResult: APIResult<SecretKey> = result
            res.status(200).json(serverSecretKeyResult)
        })
    } else if (req.method === 'PUT') {
        // Process a PUT request
    } else if (req.method === 'DELETE') {
        // Process a DELETE request
    } else {
        // Handle any other HTTP method
    }
}

export default handler