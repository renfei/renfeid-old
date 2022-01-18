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
                        <h5 class="card-title">FreeMarker(FTL)在线测试工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">FreeMarker(FTL) Online Test Tools <span class="badge badge-secondary">Powered By FreeMarker ${version}</span></h6>
                        <div class="row">
                            <div class="col-md-6">
                                <form>
                                    <div class="form-group">
                                        <label>FreeMarker (ftl) Code</label>
                                        <textarea id="ftl" name="ftl" class="form-control" rows="8"></textarea>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-6">
                                <form>
                                    <div class="form-group">
                                        <label>JavaBean (JSON) Code</label>
                                        <textarea id="json" name="json" class="form-control" rows="8"></textarea>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <button type="button" class="btn btn-primary" onclick="test()"
                                        style="width: 100%;margin: 10px 0;">Test
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <form>
                                    <div class="form-group">
                                        <label>Result</label>
                                        <textarea id="data" name="data" class="form-control" rows="8"></textarea>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div>
                            <div class="alert alert-secondary" role="alert">
                                我站已启用 WAF 防火墙，可能存在误拦截的情况，如有误拦截请联系我或提交
                                <a href="https://github.com/renfei/issues/issues" target="_blank" rel="nofollow noopener">Issue</a>。
                            </div>
                            <blockquote style="font-size: 14px;">
                                <p>在「FreeMarker (ftl) Code」中填写FreeMarker代码；在「JavaBean (JSON)
                                    Code」中填写入参对象的JSON字符串；「Result」将返回FreeMarker引擎的运行结果。
                                </p>
                                <p>
                                    当前基于 FreeMarker ${version} 驱动运行测试。
                                </p>
                                <footer>只支持自带函数方法，不支持自定义函数方法</footer>
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
        <script>
            $(function () {
                $("#ftl").val("<html>\n\t<body>\n\t\t ${'$'}{demo.text}\n\t</body>\n</html>");
                $("#json").val("{\n\t\"demo\":{\n\t\t\"text\":\"Hello World\"\n\t}\n}");
            });

            function test() {
                let ftl = $("#ftl").val();
                let json = $("#json").val();
                if (ftl === "") {
                    $("#data").val("Error: FreeMarker Code is empty!\n错误：FreeMarker Code是空！");
                    msg("Error: FreeMarker Code is empty!\n错误：FreeMarker Code是空！", "error");
                    return;
                }
                if (json === "") {
                    $("#data").val("Error: JavaBean Code is empty!\n错误：JavaBean Code是空！");
                    msg("Error: JavaBean Code is empty!\n错误：JavaBean Code是空！", "error");
                    return;
                }
                $.ajax({
                    url: '/api/freemarker/test',
                    type: 'POST',
                    async: true,
                    data: {
                        ftl: ftl,
                        beanJson: json
                    },
                    timeout: 60000,
                    dataType: 'json',
                    success: function (data, textStatus, jqXHR) {
                        if (data.code === 200) {
                            $("#data").val(data.data);
                        } else {
                            $("#data").val("Error.\n" + data.message);
                            msg(data.message, "error");
                        }
                    },
                    error: function (xhr, textStatus) {
                        $("#data").val("Error.\n" + xhr.responseText);
                        msg(xhr.responseText, "error");
                    }
                });
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>