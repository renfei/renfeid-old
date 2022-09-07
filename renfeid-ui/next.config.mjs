/** @type {import('next').NextConfig} */
const nextConfig = {
    reactStrictMode: true,
    compress: false,
    images: {
        domains: [
            'cdn.renfei.net'
        ],
    },
}

export default nextConfig
