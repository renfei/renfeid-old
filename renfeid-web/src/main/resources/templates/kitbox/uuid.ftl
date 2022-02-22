<#include "../layout/layout.ftl"/>
<#include "../layout/comments.ftl"/>
<#include "kitboxmenu.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xmlns="http://www.w3.org/1999/html" xml:lang="zh-CN" lang="zh-CN"
          dir="ltr"
          prefix="og: http://ogp.me/ns#">
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
                        <h5 class="card-title">在线批量生成 UUID/GUID 工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            UUID/GUID Online Generation Tools
                        </h6>
                        <div style="margin: auto;max-width: 600px;">
                            <form>
                                <div class="input-group mb-2 mr-sm-2">
                                    <input type="text" class="form-control" id="quantityNumber"
                                           placeholder="Generation quantity" value="10"/>
                                    <div class="input-group-prepend">
                                        <div class="btn btn-primary" onclick="generate()">Generate</div>
                                    </div>
                                </div>
                            </form>
                            <small class="text-muted">单次生成数量最大1000个。</small>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <div class="checkbox">
                                        <label>
                                            <input id="upper" type="checkbox" value="" checked="checked">
                                            upper case letters / 使用大写字母
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input id="hyphen" type="checkbox" value="" checked="checked">
                                            use hyphen / 使用连词符
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <form>
                                <div class="form-group row">
                                    <div class="col-sm-12">
                                        <textarea id="data" name="data" class="form-control" rows="15"></textarea>
                                    </div>
                                </div>
                            </form>

                            <div>
                                <blockquote style="font-size: 14px;">
                                    <p>UUID/GUID 在线批量生成接口服务已经开放。更多开放接口服务，请参考<a
                                                href="https://www.renfei.net/swagger-ui.html"
                                                target="_blank">开放接口文档</a>。
                                    </p>
                                    <p>此工具使用的接口是：https://www.renfei.net/api/open/uuid</p>
                                    <footer>接口详细参数请参考接口文档</footer>
                                </blockquote>
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
                let data = {
                    upperCase: true,
                    hyphen: true,
                    quantity: 1
                };
                if (!$("#upper").prop('checked')) {
                    data.upperCase = false;
                }
                if (!$("#hyphen").prop('checked')) {
                    data.hyphen = false;
                }
                if ($("#quantityNumber").val() !== "") {
                    data.quantity = $("#quantityNumber").val();
                } else {
                    msg("请输入生成的数量", "error");
                    return;
                }
                $("#data").val("UUID is being generated, please wait.\nUUID正在生成中，请稍后。");
                $.ajax({
                    url: '/api/uuid',
                    type: 'GET',
                    async: true,
                    data: data,
                    timeout: 60000,
                    dataType: 'json',
                    success: function (data, textStatus, jqXHR) {
                        if (data.code === 200) {
                            var str = "";
                            for (var i = 0; i < data.data.length; i++) {
                                str += data.data[i] + "\n";
                            }
                            $("#data").val(str);
                        } else {
                            $("#data").val("Error.\n" + data.message);
                            msg(data.message, "error");
                        }
                    },
                    error: function (xhr, textStatus) {
                        $("#data").val("Error.\n" + xhr.responseText);
                        msg(xhr.responseText, "error");
                    }
                })
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>