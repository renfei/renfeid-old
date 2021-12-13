<#macro sidebar sidebarVO>
    <div class="card" style="margin-bottom: 1rem;border-color: #fe7941;">
        <div class="card-body" style="padding-top: 0;">
            <h5 class="card-title"
                style="font-size: 18px;background-color: #fe7941;color: #ffffff;padding: 5px;text-align: center;">优惠 -
                上云前必看</h5>
            <h6 class="card-subtitle mb-2 text-muted">阿里云精选优惠低至0.55折</h6>
            <a href="/go/aliyunYouHui"
               target="_blank" style="text-decoration: none !important">
                <div style="font-size: 12px;color: #999999;">
                    <h5 style="color: #fe7941">1核2G=87元/年</h5>
                    <span style="color: #fe7941;font-weight: 800;">新用户：</span>低至0.55折， 1核2G轻量服务器1年87元。
                    <br/><span style="color: #fe7941;font-weight: 800;">老用户：</span>低至3.5折，2核8G服务器1年2825元。
                </div>
            </a>
        </div>
    </div>
    <div class="card" style="margin-bottom: 1rem;">
        <div class="card-body">
            <h5 class="card-title" style="font-size: 18px;">关注任霏博客</h5>
            <h6 class="card-subtitle mb-2 text-muted">扫码关注「任霏博客」微信订阅号</h6>
            <div class="text-center">
                <img src="https://cdn.renfei.net/images/WechatQR.png" class="img-fluid" width="500px" height="500px"
                     style="max-width: 140px;height:auto;margin: auto;"/>
            </div>
            <div style="font-size: 12px;" class="mt-2">
                微博：<a href="https://weibo.com/5214619228" target="_blank" rel="nofollow noopener">任霏博客网</a><br/>
                Twitter：<a href="https://twitter.com/renfeii" target="_blank" rel="nofollow noopener">@renfeii</a><br/>
                Facebook:<a href="https://www.facebook.com/renfeii" target="_blank" rel="nofollow noopener">任霏</a>
            </div>
        </div>
    </div>
    <#if sidebarVO??>
        <#if sidebarVO.postSidebars?? && (sidebarVO.postSidebars?size > 0)>
            <#list sidebarVO.postSidebars as postSidebar>
                <#if postSidebar.title == "标签云">
                    <div class="list-group mb-2">
                        <a class="list-group-item list-group-item-action active">
                            ${postSidebar.title!}
                        </a>
                        <#if postSidebar.link?? && (postSidebar.link?size > 0)>
                            <div class="row">
                                <div class="col-12">
                                    <div class="border p-2">
                                        <#list postSidebar.link as link>
                                            <a href="${link.href!}"
                                               class="badge badge-pill badge-secondary">${link.text!?html}</a>
                                        </#list>
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </div>
                <#else>
                    <div class="list-group mb-2">
                        <a class="list-group-item list-group-item-action active">
                            ${postSidebar.title!}
                        </a>
                        <#if postSidebar.link?? && (postSidebar.link?size > 0)>
                            <#list postSidebar.link as link>
                                <a href="${link.href!}"
                                   class="list-group-item list-group-item-action">${link.text!?html}</a>
                            </#list>
                        </#if>
                    </div>
                </#if>
                <#if postSidebar_index ==0 || postSidebar_index == 1>
                    <div class="d-none d-sm-block mb-2">
                        <@adsense "4527572214" active></@adsense>
                    </div>
                </#if>
            </#list>
        </#if>
    </#if>
    <#nested>
</#macro>