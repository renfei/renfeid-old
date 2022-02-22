<#include "../layout/layout.ftl"/>
<#include "../layout/pagination.ftl"/>
<#include "sidebar.ftl"/>
<@compress single_line=true>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
<@head pageView>
</@head>
<body>
<@header pageView>

</@header>
<div class="container" style="padding-top: 50px;">
    <h3>任霏的微博客</h3>
    <hr>
    <div class="row">
        <div class="col-md-9 col-sm-7">
            <div class="row">
                <#if pageView.object.data?? && (pageView.object.data?size > 0)>
                    <#list pageView.object.data as weibo>
                        <div class="col-12">
                            <div class="card mb-3">
                                <div class="row no-gutters">
                                    <div class="col-xs-3 col-sm-2 col-md-1 p-2">
                                        <img src="https://cdn.renfei.net/images/renfei_600.jpg"
                                             style="border-radius: 50%;width: 100%;" alt="任霏">
                                    </div>
                                    <div class="col-xs-9 col-sm-10 col-md-11">
                                        <div class="card-body">
                                            <h5 class="card-title mb-0" style="font-size: 18px;">任霏</h5>
                                            <p class="card-text"><small
                                                        class="text-muted">${weibo.releaseTime?string["yyyy-MM-dd HH:mm:ss"]}</small>
                                            </p>
                                            <p class="card-text"><a class="stretched-link" href="/weibo/${weibo.id?c}"
                                                                    style="color: #000000;">${weibo.content!}</a></p>
                                            <#if weibo.image??>
                                                <div class="post-thumb weibo_radius">
                                                    <img src="${weibo.image}"
                                                         class="img-fluid shadow bg-white rounded mx-auto d-block">
                                                </div>
                                            </#if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>
                </#if>
            </div>
            <div class="clear"></div>
            <@paginationLayout paginationList></@paginationLayout>
        </div>
        <div class="col-md-3 col-sm-5">
            <@sidebar></@sidebar>
        </div>
    </div>
</div>
<@footer pageView>
</@footer>
</body>
</html>
</@compress>