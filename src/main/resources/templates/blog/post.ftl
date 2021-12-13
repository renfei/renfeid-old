<#include "../layout/layout.ftl"/>
<#include "../layout/comments.ftl"/>
<#include "./sidebar.ftl"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
<@head pageView>
    <link rel="stylesheet" type="text/css" href="https://cdn.renfei.net/css/lightbox.css?v=20200506135243"/>
    <#if HighlightJS??>
        <link rel="stylesheet" href="https://cdn.renfei.net/thunder/highlight/styles/atom-one-dark.css"/>
    </#if>
</@head>
<body>
<@header pageView>

</@header>
<@compress single_line=true>
<div class="container" style="padding-top: 50px;">
    <div class="row">
        <div class="col-12">
            <small class="text-muted"
                   style="font-size: 12px;font-weight: 600;color: #6e6e73;line-height: 1.33337;letter-spacing: -.01em;">
                ${pageView.object.post.postDate?string("yyyy-MM-dd HH:mm:ss")!}
            </small>
        </div>
        <div class="col-12">
            <h1 style="font-size: 36px;line-height: 1.08349;font-weight: 600;letter-spacing: -.003em;">
                ${pageView.object.post.title!}
            </h1>
        </div>
        <div class="col-12">
            <@share socialSharing></@share>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-xl-9 col-lg-9 col-md-8">
            <div id="post-content">
                <#if pageView.object.featuredImage??>
                    <img src="${pageView.object.post.featuredImage!}" alt="${pageView.object.post.title!}" width="850px" height="400px"
                         class="img-fluid shadow mb-5 bg-white rounded"/>
                </#if>
                <#if downloadDO??>
                    <@download downloadDO></@download>
                </#if>
                <div class="renfei-content">
                    </@compress>
                    ${pageView.object.post.content!}
                    <@compress single_line=true>
                </div>
                <@share socialSharing></@share>
                <div>
                    <hr>
                    <#if pageView.object.post.isOriginal>
                        <blockquote class="blockquote"
                                    style="font-size: 12px;color: #86868b;line-height: 1.33337;font-weight: 400;">
                            商业用途请联系作者获得授权。<br>
                            版权声明：本文为博主「${pageView.object.post.sourceName!}」原创文章，遵循 <a
                                    href="http://creativecommons.org/licenses/by-nc-sa/4.0/" target="_blank"
                                    rel="nofollow noopener">CC BY-NC-SA 4.0</a> 版权协议，转载请附上原文出处链接及本声明。<br>
                            原文链接：<a href="https://www.renfei.net/posts/${pageView.object.post.id?c}">https://www.renfei.net/posts/${pageView.object.post.id?c}</a>
                        </blockquote>
                    <#else>
                        <blockquote class="blockquote"
                                    style="font-size: 12px;color: #86868b;line-height: 1.33337;font-weight: 400;">
                            商业用途请联系作者获得授权。<br>
                            版权声明：本文转载自「${pageView.object.post.sourceName!}」，版权归原所有者。<br>
                            原文链接：<a href="${pageView.object.post.sourceUrl!}" rel="nofollow noopener"
                                    target="_blank">${pageView.object.post.sourceUrl!}</a>
                        </blockquote>
                    </#if>
                </div>
            </div>
            <div class="d-none d-sm-block mb-2">
                <@adsense "1692884687" active></@adsense>
            </div>
            <div class="row py-2">
                <div class="col-12">
                    <div class="card" style="width: 100%;">
                        <div class="card-body">
                            <h5 class="card-title">相关推荐</h5>
                            <h6 class="card-subtitle mb-2 text-muted">猜你还喜欢这些内容，不妨试试阅读一下</h6>
                            <div class="row related-post-list">
                                <#if related?? && (related?size>0)>
                                    <div class="col-md-6">
                                        <ul class="list-group list-group-flush">
                                            <#list related as post>
                                                <#if post_index lt 5>
                                                    <li class="list-group-item">
                                                        <h5 class="mt-0 mb-1"
                                                            style="white-space: nowrap;overflow: hidden;">
                                                            <a href="/posts/${post.id?c}">${post.title!}</a>
                                                        </h5>
                                                    </li>
                                                </#if>
                                            </#list>
                                        </ul>
                                    </div>
                                    <div class="col-md-6">
                                        <ul class="list-group list-group-flush">
                                            <#list related as post>
                                                <#if post_index gt 4>
                                                    <li class="list-group-item">
                                                        <h5 class="mt-0 mb-1"
                                                            style="white-space: nowrap;overflow: hidden;">
                                                            <a href="/posts/${post.id?c}">${post.title!}</a>
                                                        </h5>
                                                    </li>
                                                </#if>
                                            </#list>
                                        </ul>
                                    </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <@comments commentsVO account!"null"></@comments>
        </div>
        <div class="col-xl-3 col-lg-3 col-md-4">
            <@sidebar PostSidebar></@sidebar>
        </div>
    </div>
</div>
</@compress>
<@footer pageView>
    <script type="application/ld+json">
            ${jsonld!}
    </script>
    <script type='text/javascript' charset="UTF-8" src="//cdn.renfei.net/js/lightbox.min.js?v=20200506135243"
            async></script>
<#if HighlightJS??>
    <script src="https://cdn.renfei.net/thunder/highlight/highlight.pack.js"></script>
    <script>
        $(function () {
            $("code").each(function () {
                $(this).html("<ol><li>" + $(this).html().replace(/\n/g, "\n</li><li>") + "\n</li></ol>");
            });
        });
        hljs.initHighlightingOnLoad();
    </script>
</#if>
    <script>
        $(function () {
            $(".renfei-content img").each(function () {
                $(this).attr('class', "img-fluid shadow mb-5 bg-white rounded mx-auto d-block");
                var strA = "<a class=\"lightbox\" href='" + this.src + "' style='position: initial;' data-lightbox=\"example-set\"></a>";
                $(this).wrapAll(strA);
            });
        })
    </script>
</@footer>
</body>
</html>