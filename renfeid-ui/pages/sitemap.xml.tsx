import { GetServerSideProps } from 'next'
import React from 'react'
import nookies from 'nookies'
import * as Fetch from '../utils/request'
import { convertToHeaders } from '../utils/request'
import APIResult = API.APIResult
import SiteMapXml = API.SiteMapXml

const Sitemap: React.FC = () => null

export const getServerSideProps: GetServerSideProps = async ({ req, res }) => {
  const resp: APIResult<SiteMapXml[]> = await Fetch.get(
    '/-/api/sitemap',
    convertToHeaders(req.headers, req.socket.remoteAddress),
    undefined,
    true
  )
  let xml = ''
  if (resp.code == 200 && resp.data) {
    for (let i = 0; i < resp.data.length; i++) {
      xml += `  <url>
    <loc>${resp.data[i].loc}</loc>
    <changefreq>${resp.data[i].changefreqEnum}</changefreq>
    <priority>${resp.data[i].priority}</priority>
    <lastmod>${resp.data[i].lastmod}</lastmod>
  </url>`
    }
  }
  if (res) {
    res.setHeader('Content-Type', 'text/xml')
    res.write(`<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="/sitemap.xsl"?>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
${xml}
</urlset>`)
    res.end()
  }
  return {
    props: {},
  }
}

export default Sitemap