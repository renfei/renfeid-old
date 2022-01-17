<#include "../layout/layout.ftl"/>
<#include "../layout/comments.ftl"/>
<#include "./kitboxmenu.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head pageView>
    </@head>
    <body>
    <@header pageView>

    </@header>
    <div class="container" style="padding-top: 50px;">
        <div class="row">
            <div class="col-sm-3 col-md-3">
                <@KitBoxMenu KitBoxMenus active></@KitBoxMenu>
            </div>
            <div class="col-sm-9 col-md-9">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">二维码在线生成工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">Online Generation Tool Of QRCode</h6>
                        <div class="row">
                            <div class="col-md-7">
                                <div class="form-group">
                                    <label>输入内容</label>
                                    <textarea id="qrtext" name="qrtext" class="form-control" rows="8">https://www.renfei.net/kitbox/qrcode</textarea>
                                    <label>生成图片的链接</label>
                                    <input onclick="qrlinkonclick()" type="text" disabled id="qrlink" name="qrtext"
                                           class="form-control"
                                           value="https://www.renfei.net/other/qrcode?content=https://www.renfei.net/kitbox/qrcode"></input>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label style="display: block;">实时生成结果</label>
                                    <img id="qrcode_show" width="100%"
                                         src="/other/qrcode?content=https://www.renfei.net/kitbox/qrcode"/>
                                </div>
                            </div>
                        </div>
                        <div>
                            <blockquote style="font-size: 14px;">
                                <p>如果您想在您的网页上动态的生成二维码，您可以这样在您网页上声明一个图片：<br>
                                <pre><code class="html">&lt;img src=&quot;https://www.renfei.net/other/qrcode?content=youtext&quot; /&gt;</code></pre>
                                <p>
                                其中「content=」后面跟的就是二维码的内容。
                                </p>
                                <footer>推荐内容控制在150字内，过多的内容可能会模糊不清。</footer>
                            </blockquote>
                        </div>
                        <div class="d-none d-sm-block">
                            <@adsense "9903187829" active></@adsense>
                        </div>
                    </div>
                </div>
                <@comments commentList account!"null" "KITBOX" kitBoxId></@comments>
            </div>
        </div>
    </div>
    <@footer pageView>
        <script type="text/javascript">
            function qrlinkonclick() {
                window.open($("#qrlink").val());
            }

            $('#qrtext').bind('input propertychange', function () {
                var redeemCode_text = $("#qrtext").val();
                if (redeemCode_text === "") {
                    $("#qrcode_show").attr('src', "/other/qrcode?content=https://www.renfei.net/kitbox/qrcode");
                    $("#qrlink").val("https://www.renfei.net/other/qrcode?content=https://www.renfei.net/kitbox/qrcode");
                } else {
                    $("#qrcode_show").attr('src', "/other/qrcode?content=" + redeemCode_text);
                    $("#qrlink").val("https://www.renfei.net/other/qrcode?content=" + redeemCode_text);
                }
            });
        </script>
    </@footer>
    </body>
    </html>
</@compress>