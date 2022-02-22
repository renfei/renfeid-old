<?xml version="1.0" encoding="UTF-8" ?>
<rss version="2.0">
    <channel>
        <title>${feed.title!}</title>
        <author>${feed.author!}</author>
        <link>${feed.link!}</link>
        <description>${feed.description!}</description>
        <language>${feed.language!}</language>

        <copyright>
            ${.now?string["yyyy"]} RENFEI.NET All rights reserved.
        </copyright>
        <#if feed.image??>
            <image>
                <url>${feed.image.url!}</url>
                <title>${feed.image.title!}</title>
                <link>${feed.image.link!}</link>
                <width>${feed.image.width!}</width>
                <height>${feed.image.height!}</height>
            </image>
        </#if>
        <#if feed.item??>
            <#list feed.item as itm>
                <item>
                    <title>${itm.title!}</title>
                    <author>${itm.author!}</author>
                    <link>${itm.link!}</link>
                    <description>${itm.description!}</description>
                    <guid>${itm.guid!}</guid>
                    <category>${itm.category!}</category>
                    <pubDate>${itm.pubDate?string["EEE, dd MMM yyyy HH:mm:ss +0800"]}</pubDate>
                </item>
            </#list>
        </#if>
    </channel>
</rss>