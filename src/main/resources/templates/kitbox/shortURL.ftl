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
                        <h5 class="card-title">短网址在线生成工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">Online Generation Tool Of ShortURL</h6>
                        <div class="row">
                            <div class="col-md-7">
                                <div class="form-group">
                                    <label>输入原网址</label>
                                    <input id="url" name="url" class="form-control">
                                </div>
                                <div class="form-group">
                                    <button type="submit" onclick="return ok()"
                                            class="btn btn-primary btn-lg btn-block">
                                        OK
                                    </button>
                                </div>
                                <div class="form-group">
                                    <label>生成的短网址</label>
                                    <input type="text" disabled id="short" style="background-color: #fff;" name="short"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label style="display: block;">短网址二维码</label>
                                    <img id="qrcode_show" width="100%" style="display: none;"
                                         src="/other/qrcode?content=https://www.renfei.net/kitbox/qrcode"/>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="alert alert-secondary" role="alert">
                                    温馨提示：<a href="JavaScript:signInFun()">登录</a>以后可以管理您的短网址和查看统计数据。
                                </div>
                            </div>
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
            function copy() {
                window.clipboardData.setData("Text", $("#short").val());
                msg("成功复制到剪切板", "success");
                return false;
            }

            function ok() {
                var url = $("#url").val();
                if (url === "") {
                    msg("原始 Url 必须填写！", "error");
                } else {
                    $.ajax({
                        url: '/-/api/kitbox/ShortURL/do',
                        type: 'POST',
                        async: true,
                        data: {
                            url: url
                        },
                        timeout: 60000,
                        dataType: 'json',
                        success: function (data, textStatus, jqXHR) {
                            if (data.code === 200) {
                                $("#short").val(data.data.shortUrl);
                                $("#qrcode_show").attr('src', "/other/qrcode?content=" + data.data.shortUrl);
                                $("#qrcode_show").show();
                            } else {
                                msg(data.message, "error");
                            }
                        },
                        beforeSend: function (xhr) {
                            xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                        },
                        error: function (xhr, textStatus) {
                            $("#data").val("Error.\n" + xhr.responseText);
                            msg(xhr.responseText, "error");
                        }
                    });
                }
                return false;
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>