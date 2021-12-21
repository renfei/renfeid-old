<@compress single_line=true>
<?xml version="1.0" encoding="utf-8"?>
<?xml-stylesheet type="text/xsl" href="https://www.renfei.net/sitemap.xsl"?>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
    <#if data??>
        <#list data as info>
            <url>
                <loc>${info.loc}</loc>
                <changefreq>${info.changefreq}</changefreq>
                <priority>${info.priority}</priority>
                <lastmod>${info.lastmod}</lastmod>
            </url>
        </#list>
    </#if>
</urlset>
</@compress>