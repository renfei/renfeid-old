import { GetServerSideProps } from 'next'
import React from 'react'

const Feed: React.FC = () => null

export const getServerSideProps: GetServerSideProps = async ({ res }) => {
  if (res) {
    res.setHeader('Content-Type', 'text/xml')
    res.write(`<?xml version="1.0" encoding="UTF-8"?>
    <rss version="2.0">
      <channel>
        <title>${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}</title>
        <author>i@renfei.net (RenFei)</author>
        <link>${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}</link>
        <description>任霏博客是任霏的个人网站与博客，一个程序员自己写的网站，不仅仅是文章内容，还包括网站程序的代码。 对新鲜事物都十分感兴趣，利用这个站点向大家分享自己的所见所得，同时这个站点也是我的实验室。</description>
        <language>zh-CN</language>

        <copyright>
            ${new Date().getFullYear()} RENFEI.NET All rights reserved.
        </copyright>
            <image>
                <url>https://cdn.renfei.net/Logo/RF_white.svg</url>
                <title>${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}</title>
                <link>${process.env.NEXT_PUBLIC_RENFEID_SITE_DOMAIN}</link>
                <width>32</width>
                <height>32</height>
            </image>
      </channel>
    </rss>`)
    res.end()
  }
  return {
    props: {},
  }
}

export default Feed