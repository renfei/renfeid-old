const dev = process.env.NODE_ENV !== 'production'

export const server = dev ? 'http://localhost:9595' : 'http://renfeid:9595'