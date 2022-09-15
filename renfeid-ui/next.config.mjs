/** @type {import('next').NextConfig} */
const nextConfig = {
    output: 'standalone',
    reactStrictMode: true,
    compress: false,
    images: {
        domains: [
            'cdn.renfei.net'
        ],
    },
}

export default nextConfig
