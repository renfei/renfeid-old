<#include "../layout/layout.ftl"/>
<#include "../kitbox/kitboxmenu.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head pageView>
    </@head>
    <body>
    <@header pageView>

    </@header>
    <div class="developer-toolkit-banner">
        <div class="container">
            <div class="row">
                <div class="col-md-4 online-document-banner-left"></div>
                <div class="col-md-8 text-center align-self-center">
                    <h1>在线文档</h1>
                    <h3 style="color: #6c757d!important;">Online Documents</h3>
                    <p>提供在线开发文档与手册服务</p>
                    <p>中文文档基于机器翻译，请结合原版阅读</p>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <small class="text-muted">注：中文文档基于机器翻译，请结合原版阅读。由于文档数量庞大，即使是机器翻译工作量也十分缓慢，更多文档正在翻译中。如果没有你需要的文档可以联系我添加。</small>
    </div>
    <div class="container" style="padding-top: 50px;">
        <#if pageView.object?? && (pageView.object?size > 0)>
            <#list pageView.object as menu>
                <h4>${menu.title}</h4>
                <div class="row mb-5">
                    <#if menu.links?? && (menu.links?size > 0)>
                        <#list menu.links as link>
                            <div class="col-md-3 mb-2">
                                <div class="card">
                                    <div class="card-body">
                                        <a class="stretched-link" href="${link.href}" style="color: #000000;"
                                           target="_blank">
                                            <h6>${link.text}</h6>
                                        </a>
                                        <div style="font-size:12px;color: #6c757d!important;">
                                            ${link.rel!}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </#if>
                </div>
            </#list>
        </#if>
    </div>
    <@footer pageView>
    </@footer>
    </body>
    </html>
</@compress>