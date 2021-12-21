<#include "layout/layout.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head pageView>
    </@head>
    <body>
    <@header pageView>

    </@header>
    <div class="index-container">
        <div class="container d-none d-sm-block" style="padding-top: 30px;">
            <@adsense "1847785593" active></@adsense>
        </div>
        <div class="container" style="padding-top: 20px;">
            <h3>任霏的最新文章</h3>
            <div class="row">
                <div class="col-sm-12 col-card">
                    <#if pageView.object.data?? && (pageView.object.data?size > 0)>
                        <#list pageView.object.data as post>
                            <#if post_index == 0>
                                <div class="card">
                                    <div class="row no-gutters">
                                        <div class="col-md-8">
                                            <img src="${post.post.featuredImage!}?x-oss-process=style/992.558"
                                                 class="card-img" alt="${post.post.title}" width="992px" height="558px"
                                                 style="width: 100%;height: auto;">
                                        </div>
                                        <div class="col-md-4">
                                            <div class="card-body">
                                                <a class="stretched-link" href="/posts/${post.post.id?c}"
                                                   style="color: #000000;">
                                                    <h5 class="card-title">
                                                        <#if (post.post.title)?length lt 25>
                                                            ${post.post.title!}
                                                        <#else>
                                                            ${(post.post.title)?substring(0,25)}...
                                                        </#if>
                                                    </h5>
                                                </a>
                                                <p class="card-text">
                                                    <#if (post.post.excerpt)?length lt 50>
                                                        ${post.post.excerpt!}
                                                    <#else>
                                                        ${(post.post.excerpt)?substring(0,50)}...
                                                    </#if>
                                                </p>
                                                <p class="card-text"><small
                                                            class="text-muted">${post.post.postDate?string("yyyy-MM-dd")!}</small>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </#if>
                        </#list>
                    </#if>
                </div>
            </div>
            <div class="row">
                <#if pageView.object.data?? && (pageView.object.data?size > 0)>
                    <#list pageView.object.data as post>
                        <#if post_index gt 0 && post_index lt 3>
                            <div class="col-sm-6 col-card">
                                <div class="card">
                                    <img src="${post.post.featuredImage!}?x-oss-process=style/992.558"
                                         class="card-img" alt="${post.title}" width="992px" height="558px"
                                         style="width: 100%;height: auto;">
                                    <div class="card-body">
                                        <a class="stretched-link" href="/posts/${post.post.id?c}"
                                           style="color: #000000;">
                                            <h5 class="card-title">
                                                <#if (post.post.title)?length lt 31>
                                                    ${post.post.title!}
                                                <#else>
                                                    ${(post.post.title)?substring(0,30)}...
                                                </#if>
                                            </h5>
                                        </a>
                                        <p class="card-text">
                                            <#if (post.post.excerpt)?length lt 50>
                                                ${post.post.excerpt!}
                                            <#else>
                                                ${(post.post.excerpt)?substring(0,50)}...
                                            </#if>
                                        </p>
                                        <p class="card-text"><small
                                                    class="text-muted">${post.post.postDate?string("yyyy-MM-dd")!}</small>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="row">
                <#if pageView.object.data?? && (pageView.object.data?size > 0)>
                    <#list pageView.object.data as post>
                        <#if post_index gt 2 && post_index lt 9>
                            <div class="col-md-4 col-sm-6 col-card">
                                <div class="card">
                                    <img src="${post.post.featuredImage!}?x-oss-process=style/992.558"
                                         class="card-img" alt="${post.post.title}" width="992px" height="558px"
                                         style="width: 100%;height: auto;">
                                    <div class="card-body">
                                        <a class="stretched-link" href="/posts/${post.post.id?c}"
                                           style="color: #000000;">
                                            <h5 class="card-title">
                                                <#if (post.post.title)?length lt 25>
                                                    ${post.post.title!}
                                                <#else>
                                                    ${(post.post.title)?substring(0,25)}...
                                                </#if>
                                            </h5>
                                        </a>
                                        <p class="card-text">
                                            <#if (post.post.excerpt)?length lt 45>
                                                ${post.post.excerpt!}
                                            <#else>
                                                ${(post.post.excerpt)?substring(0,45)}...
                                            </#if>
                                        </p>
                                        <p class="card-text"><small
                                                    class="text-muted">${post.post.postDate?string("yyyy-MM-dd")!}</small>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </#list>
                </#if>
            </div>
        </div>
    </div>
    <div class="index-container2">
        <div class="container more" style="padding-top: 50px;">
            <h3>任霏的更多文章</h3>
            <hr>
            <div class="row">
                <#if pageView.object.data?? && (pageView.object.data?size > 0)>
                    <#list pageView.object.data as post>
                        <#if post_index gt 8 && post_index lt 15>
                            <div class="col-md-6">
                                <div class="card ">
                                    <div class="row no-gutters">
                                        <div class="col-3 col-md-5 col-lg-3">
                                            <img src="${post.post.featuredImage!}?x-oss-process=style/200_200"
                                                 class="card-img" alt="${post.post.title}" width="200px" height="200px"
                                                 style="width: 100%;height: auto;">
                                        </div>
                                        <div class="col-9 col-md-7 col-lg-9">
                                            <div class="card-body">
                                                <a class="stretched-link" href="/posts/${post.post.id?c}"
                                                   style="color: #000000;">
                                                    <h5 class="card-title" style="font-size: 18px;">
                                                        <#if (post.post.title)?length lt 25>
                                                            ${post.post.title!}
                                                        <#else>
                                                            ${(post.post.title)?substring(0,25)}...
                                                        </#if>
                                                    </h5>
                                                </a>
                                                <p class="card-text"><small
                                                            class="text-muted">${post.post.postDate?string("yyyy-MM-dd")!}</small>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <hr>
            <div class="text-center">
                <a href="/posts" class="btn btn-link">查看更多文档 >></a>
            </div>
        </div>
    </div>
    <@footer pageView>
        <script type="application/ld+json">
            {
                "@context": "http://schema.org/",
                "@graph": [
                    {
                        "@type": "Organization",
                        "logo": "https://cdn.renfei.net/Logo/logo_112.png",
                        "url": "https://www.renfei.net"
                    },
                    {
                        "@type": "WebSite",
                        "url": "https://www.renfei.net",
                        "potentialAction": {
                            "@type": "SearchAction",
                            "target": "https://www.renfei.net/search?type=all&w={search_term_string}",
                            "query-input": "required name=search_term_string"
                        }
                    },
                    {
                        "@type": "Person",
                        "name": "任霏",
                        "url": "https://www.renfei.net",
                        "sameAs": [
                            "https://github.com/renfei",
                            "https://gitlab.com/renfei",
                            "https://www.facebook.com/renfeii",
                            "https://twitter.com/renfeii",
                            "https://www.youtube.com/channel/UCPsjiVvFMS7piLgC-RHBWxg"
                        ]
                    }
                ]
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>