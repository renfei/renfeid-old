import type { NextApiRequest } from 'next'
import { isParameter } from './isParameter'
import { removeFileExtension } from './removeFileExtension'

export const requestSegments = (req: NextApiRequest): string[] => {
  const route = req.query.route
  const segments = (typeof route === 'string') ? [route] : route

  if (segments) {
    return segments
      .map(segment => removeFileExtension(segment))
      .filter(segment => !isParameter(segment))
  } else {
    return []
  }
}
