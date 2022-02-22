<#include "../layout/layout.ftl"/>
<#include "../layout/comments.ftl"/>
<#include "kitboxmenu.ftl"/>
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
                        <h5 class="card-title">URL网址16进制加密工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">URL hex encryption tool</h6>
                        <div style="margin: auto;max-width: 600px;">
                            <form>
                                <div class="form-group row">
                                    <label for="staticEmail" class="col-sm-2 col-form-label">URL:</label>
                                    <div class="col-sm-10">
                                        <div class="input-group mb-2 mr-sm-2">
                                            <input type="text" class="form-control" value="http://www.renfei.net/"
                                                   id="quantityNumber">
                                            <div class="input-group-prepend">
                                                <div class="btn btn-primary" onclick="generate()">16进制加密</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputPassword" class="col-sm-2 col-form-label">URL-16:</label>
                                    <div class="col-sm-10">
                                        <textarea type="text" class="form-control" id="data" rows="4"></textarea>
                                    </div>
                                </div>
                            </form>
                            <div>
                                <div class="col-sm-12">
                                    <blockquote style="font-size: 14px;">
                                        <p>把URL网址转换成16进制代码形式,加密后可直接复制到地址栏访问</p>
                                        <footer>URL编码形式表示的ASCII字符(十六进制格式)
                                        </footer>
                                    </blockquote>
                                </div>
                            </div>
                            <div class="d-none d-sm-block">
                                <@adsense "9903187829" active></@adsense>
                            </div>
                        </div>
                    </div>
                </div>
                <@comments commentList account!"null" "KITBOX" kitBoxId></@comments>
            </div>
        </div>
    </div>
    <@footer pageView>
        <script>
            function generate() {
                var str = $("#quantityNumber").val();
                var str2 = '';
                var str3 = str.substring(0, 7);
                if (str3 == 'http://') {
                    str2 = 'http://';
                    str = str.substring(7, str.length);
                }
                if (str3 == 'https://') {
                    str2 = 'https://';
                    str = str.substring(8, str.length);
                }
                for (var i = 0; i < str.length; i++) {
                    if (str.charCodeAt(i) == '47') str2 += '/';
                    else if (str.charCodeAt(i) == '63') str2 += '?';
                    else if (str.charCodeAt(i) == '38') str2 += '&';
                    else if (str.charCodeAt(i) == '61') str2 += '=';
                    else if (str.charCodeAt(i) == '58') str2 += ':';
                    else str2 += '%' + str.charCodeAt(i).toString(16);
                }
                $("#data").val(str2);
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>