<#include "../layout/layout.ftl"/>
<#include "../layout/pagination.ftl"/>
<#include "./sidebar.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head pageView>
    </@head>
    <body>
    <@header pageView>

    </@header>
    <div class="container" style="padding-top: 50px;">
        <h3>${catTitle!"全部文档"}</h3>
        <hr>
        <div class="row">
            <div class="col-xl-9 col-lg-9 col-md-8">
                <div class="d-none d-sm-block mb-3">
                    <@adsense "9200673760" active></@adsense>
                </div>
                <#if pageView.object?? && (pageView.object?size>0)>
                    <#list pageView.object as post>
                        <div class="card mb-3" style="margin-bottom: 1rem;">
                            <div class="row no-gutters">
                                <div class="col-xl-4">
                                    <img src="${post.post.featuredImage!}?x-oss-process=style/992.558"
                                         class="card-img" alt="${post.post.title}" width="992px" height="558px"
                                         style="width: 100%;height: auto;">
                                </div>
                                <div class="col-xl-8">
                                    <div class="card-body" style="padding: 0.2rem 1.25rem;">
                                        <small class="text-muted">${post.post.postDate?string("yyyy-MM-dd")!}</small>
                                        <a class="stretched-link" href="/posts/${post.post.id?c}" style="color: #000000;">
                                            <h5 class="card-title" style="font-size: 17px;">
                                                <#if (post.post.title)?length lt 30>
                                                    ${post.post.title!?html}
                                                <#else>
                                                    ${(post.post.title)?substring(0,30)?html}...
                                                </#if>
                                            </h5>
                                        </a>
                                        <p class="card-text">
                                            <#if (post.post.excerpt!)?length lt 50>
                                                ${post.post.excerpt!?html}
                                            <#else>
                                                ${(post.post.excerpt!)?substring(0,50)?html}...
                                            </#if>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>
                </#if>
                <div class="d-none d-sm-block mb-3">
                    <@adsense "7784290943" active></@adsense>
                </div>
                <@paginationLayout paginationList></@paginationLayout>
            </div>
            <div class="col-xl-3 col-lg-3 col-md-4">
                <@sidebar PostSidebar></@sidebar>
            </div>
        </div>
    </div>
    <@footer pageView>
    </@footer>
    </body>
    </html>
</@compress>