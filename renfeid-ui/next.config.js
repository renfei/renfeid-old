/** @type {import('next').NextConfig} */
const withPWA = require('next-pwa')({
    dest: 'public',
    register: true,
    skipWaiting: true,
})

const nextConfig = withPWA({
    reactStrictMode: true,
    compress: false,
    images: {
        domains: [
            'cdn.renfei.net'
        ],
    },
})

module.exports = nextConfig
