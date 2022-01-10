<#include "../layout/layout.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head pageView></@head>
    <body>
    <@header pageView></@header>
    <div class="pb-4" style="margin-top: 80px;">
        <div class="container text-center">
            <div>
                <i class="fa fa-check-circle" aria-hidden="true" style="font-size: 60px;color: #28a745;"></i>
            </div>
        </div>
    </div>
    <div style="width: 340px; margin: auto;margin-bottom: 230px;" class="px-3">
        <div class="p-0"
             style="margin-bottom: 15px;text-align: center;text-shadow: none;background-color: initial;border: 0;">
            <h1 style="font-size: 24px;font-weight: 300;">您已成功创建了账户。</h1>
            <h4 style="font-size: 14px;">接下来您还需要一些验证工作。</h4>
        </div>
        <div class="border rounded mt-3 p-4" style="font-size: 14px;">
            为了确保账户的真实有效，我们向您注册时所填写的邮箱发送了一封激活确认邮件，请您到您的邮箱中点击激活链接来启用您的账户。
        </div>
    </div>
    <@footer pageView></@footer>
    </body>
    </html>
</@compress>