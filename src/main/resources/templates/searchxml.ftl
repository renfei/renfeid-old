<?xml version="1.0" encoding="UTF-8"?>
<OpenSearchDescription xmlns="http://a9.com/-/spec/opensearch/1.1/">
    <InputEncoding>utf-8</InputEncoding>
    <ShortName>${siteName}</ShortName>
    <Description>搜索${siteName}，发现更多</Description>
    <Url type="text/html" method="get" template="${domain}/search?type=all&amp;w={searchTerms}"/>
</OpenSearchDescription>