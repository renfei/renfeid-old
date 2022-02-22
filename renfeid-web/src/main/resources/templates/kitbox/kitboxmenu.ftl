<#include "../layout/layout.ftl"/>
<#macro KitBoxMenu KitBoxMenus active>
    <div class="accordion" id="KitBoxMenus">
        <#if KitBoxMenus?? && (KitBoxMenus?size>0)>
            <#list KitBoxMenus as menu>
                <div class="card">
                    <div class="card-header p-0" id="${menu.elementId+'s'}">
                        <h2 class="mb-0">
                            <button class="btn btn-light btn-block" type="button" data-toggle="collapse"
                                    data-target="#${menu.elementId}" aria-expanded="true"
                                    aria-controls="${menu.elementId}">
                                ${menu.title!}
                            </button>
                        </h2>
                    </div>

                    <div id="${menu.elementId}" class="collapse${menu.open?string(" show","")}"
                         aria-labelledby="${menu.elementId+'s'}"
                         data-parent="#KitBoxMenus">
                        <div class="card-body">
                            <#if menu.links??>
                                <#list menu.links as link>
                                    <a class="btn btn-light btn-block" href="${link.href}"
                                       role="button">${link.text}</a>
                                </#list>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>
        </#if>
        <div class="card" style="margin-top: 10px;border-bottom: 1px solid rgba(0,0,0,.125);">
            <div class="card-body">
                <h5 class="card-title">Issue</h5>
                <h6 class="card-subtitle mb-2 text-muted">支持与报告</h6>
                <p class="card-text">如遇故障或Bug，请向我提交 Issue，感谢您的报告。</p>
                <a href="https://github.com/renfei/issues/issues" class="card-link" target="_blank"
                   rel="nofollow noopener">Issue</a>
            </div>
        </div>
        <div class="d-none d-sm-block mt-3">
            <@adsense "8357353621" active></@adsense>
        </div>
    </div>
</#macro>