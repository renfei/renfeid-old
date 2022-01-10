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
            <h1 style="font-size: 24px;font-weight: 300;">您已安全登出我们的系统。</h1>
            <h4 style="font-size: 14px;">我们恭候您下次归来。</h4>
        </div>
        <div class="border rounded mt-3 p-4" style="font-size: 14px;">
            我们将在（<span id="countdown">10</span>）秒后为您跳转到：<br/><a href="${pageView.object}">${pageView.object}</a>。
        </div>
    </div>
    <@footer pageView></@footer>
    <script>
        let t = 10;
        function fun() {
            t--;
            $("#countdown").html(t);
            if(t <= 0) {
                clearInterval(inter);
                window.location.href='${pageView.object}';
            }
        }
        var inter = setInterval("fun()", 1000);
    </script>
    ${script}
    </body>
    </html>
</@compress>