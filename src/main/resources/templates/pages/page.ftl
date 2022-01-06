<#include "../layout/layout.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head pageView></@head>
    <body>
    <@header pageView></@header>
    <div class="container" style="padding-top: 50px;">
        <div class="row">
            <div class="col-12">
                <small class="text-muted"
                       style="font-size: 12px;font-weight: 600;color: #6e6e73;line-height: 1.33337;letter-spacing: -.01em;">
                    ${pageView.object.page.pageDate?string("yyyy-MM-dd HH:mm:ss")!}
                </small>
            </div>
            <div class="col-12">
                <h1 style="font-size: 36px;line-height: 1.08349;font-weight: 600;letter-spacing: -.003em;">
                    ${pageView.object.page.pageTitle!}
                </h1>
            </div>
            <div class="col-12">
                <@share socialSharing></@share>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-12">
                ${pageView.object.page.pageContent}
            </div>
            <div class="col-12">
                <@share socialSharing></@share>
            </div>
        </div>
    </div>
    <@footer pageView></@footer>
    </body>
    </html>
</@compress>