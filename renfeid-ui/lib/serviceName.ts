import type { NextApiRequest } from 'next'
import { requestSegments } from './requestSegments'

export const serviceName = (req: NextApiRequest) => {
  const segments = requestSegments(req)
  return segments.join('/')
}
