<#include "../layout/layout.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head pageView></@head>
    <body>
    <@header pageView></@header>
    <main class="site-main" id="site-main">
        <section class="post-feed">
            <div class="container">
                <div style="max-width: 500px;margin: 20px auto;">
                    <p style="font-size: 22px;color: #d68300;line-height: 12px;text-indent: initial;">您将要访问(You will visit)：</p>
                    <p>${url!?html}</p>
                    <p>该网站不属于<span>${siteName}</span>，我们无法确认该网页是否安全，它可能包含未知的安全隐患。</p>
                    <p>The site is not a <span>${siteName}</span>, we can not confirm whether the page is secure,
                        it may contain unknown security risks.</p>
                    <a class="btn" href="${url!?html}" rel="nofollow noopener"
                       style="color:#ffffff;background-color: #238aca;border: 1px solid #238aca;background-image: none;">继续(Continue)</a>
                    <span class="btn" onclick="closePage()">关闭(Close)</span>
                </div>
            </div>
        </section>
    </main>
    <@footer pageView>
        <script>
            function closePage() {
                window.opener = null;
                window.open('', '_self');
                window.close();
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>