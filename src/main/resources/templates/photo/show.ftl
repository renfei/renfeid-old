<#include "../layout/layout.ftl"/>
<#include "../layout/pagination.ftl"/>
<#include "./sidebar.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head pageView>
        <link rel="stylesheet" type="text/css" href="//cdn.renfei.net/css/lightbox.css?v=20200506135243"/>
    </@head>
    <body>
    <@header pageView>

    </@header>
    <div class="container" style="padding-top: 50px;">
        <div class="row">
            <div class="col-12">
                <div class="card mb-3">
                    <div class="row no-gutters">
                        <div class="col-md-4">
                            <img src="${pageView.object.album.featuredImage}" class="card-img" alt="${pageView.object.album.title}">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title">${pageView.object.album.title}</h5>
                                <p class="card-text"><small class="text-muted">${pageView.object.album.releaseTime?string["yyyy-MM-dd HH:mm:ss"]}</small></p>
                                <p class="card-text">${pageView.object.album.describes}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <h2 class="title text-center">Photos</h2>
        <div class="row photos-content">
            <#if pageView.object.albumImgList?? && (pageView.object.albumImgList?size > 0)>
                <#list pageView.object.albumImgList as img>
                    <div class="col-sm-3 mb-2">
                        <div class="portfolio-wrapper">
                            <div class="portfolio-single">
                                <div class="portfolio-thumb">
                                    <a href="${img.uri}" class="lightbox" style="position: initial;" data-lightbox="example-set">
                                        <img src="${img.uri}?x-oss-process=style/992.558" width="992px" height="558px" style="width: 100%;height: auto;" class="img-fluid shadow bg-white rounded mx-auto d-block" alt="">
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>
    </div>
    <@footer pageView>
        <script type='text/javascript' charset="UTF-8" src="//cdn.renfei.net/js/lightbox.min.js?v=20200506135243"
                async></script>
    </@footer>
    </body>
    </html>
</@compress>