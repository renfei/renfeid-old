import type { NextApiRequest, NextApiResponse } from 'next'
import { serviceName } from './serviceName'
interface Service {
  (req: NextApiRequest, res: NextApiResponse): any
}

export const findService = async (req: NextApiRequest): Promise<Service | undefined> => {
  try {
    return (await import(`../services/mock/${serviceName(req)}`)).default
  } catch {
    return undefined
  }
}
