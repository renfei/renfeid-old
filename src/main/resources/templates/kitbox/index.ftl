<#include "../layout/layout.ftl"/>
<#include "./kitboxmenu.ftl"/>
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
                <div class="col-md-4 developer-toolkit-banner-left"></div>
                <div class="col-md-8 text-center align-self-center">
                    <h1>开发者工具箱</h1>
                    <h3 style="color: #6c757d!important;">Developer Toolkit</h3>
                    <p>子曰："工欲善其事，必先利其器。"</p>
                    <p>利用工具优化自己的学习工作效率，将时间留给更美好的事物。</p>
                </div>
            </div>
        </div>
    </div>
    <div class="container" style="padding-top: 50px;">
        <#if KitBoxMenus?? && (KitBoxMenus?size > 0)>
            <#list KitBoxMenus as menu>
                <h4>${menu.title}</h4>
                <div class="row mb-5">
                    <#if menu.links?? && (menu.links?size > 0)>
                        <#list menu.links as link>
                            <div class="col-md-3 mb-2">
                                <div class="card">
                                    <div class="card-body">
                                        <a class="stretched-link" href="${link.href}" style="color: #000000;">
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