<#include "../layout/layout.ftl"/>
<#include "../layout/comments.ftl"/>
<#include "./kitboxmenu.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head pageView>
        <style>
            #ftl, #json, #data {
                height: 360px;
                width: 100%;
            }
        </style>
    </@head>
    <body>
    <@header pageView>

    </@header>
    <div id="container" class="container" style="padding-top: 50px;">
        <div class="row">
            <div id="KitBoxMenus" class="col-sm-3 col-md-3">
                <@KitBoxMenu KitBoxMenus active></@KitBoxMenu>
            </div>
            <div id="ftlContainer" class="col-sm-9 col-md-9">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">FreeMarker(FTL)在线测试工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">FreeMarker(FTL) Online Test Tools <span
                                    class="badge badge-secondary">Powered By FreeMarker ${version}</span></h6>
                        <div class="row">
                            <div class="col-12" style="text-align:right;">
                                <button type="button" class="btn btn-primary btn-sm" onclick="container_fluid()">宽屏
                                </button>
                            </div>
                            <div class="col-md-6">
                                <form>
                                    <div class="form-group">
                                        <label>FreeMarker (ftl) Code</label>
                                        <div id="ftl" class="form-control"></div>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-6">
                                <form>
                                    <div class="form-group">
                                        <label>JavaBean (JSON) Code</label>
                                        <div id="json" class="form-control"></div>
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
                                        <div id="data" class="form-control"></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div>
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
        <script src="https://cdn.jsdelivr.net/npm/ace-builds@1.4.13/src-min/ace.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/ace-builds@1.4.13/src-min/ext-language_tools.min.js"></script>
        <script>
            ace.config.set("basePath", "https://cdn.jsdelivr.net/npm/ace-builds@1.4.13/src-min");
            var ftlEditor = ace.edit('ftl');
            var jsonEditor = ace.edit('json');
            var dataEditor = ace.edit('data');
            ftlEditor.session.setMode('ace/mode/ftl');
            ftlEditor.setOptions({
                enableBasicAutocompletion: true,
                enableSnippets: true,
                enableLiveAutocompletion: true,
                fontSize: 14
            });
            jsonEditor.session.setMode('ace/mode/json');
            jsonEditor.setOptions({
                enableBasicAutocompletion: true,
                enableSnippets: true,
                enableLiveAutocompletion: true,
                fontSize: 14
            });
            dataEditor.setOptions({
                enableBasicAutocompletion: true,
                enableSnippets: true,
                enableLiveAutocompletion: true,
                fontSize: 14
            });
            ftlEditor.setValue("<html>\n\t<body>\n\t\t ${'$'}{demo.text}\n\t</body>\n</html>");
            jsonEditor.setValue("{\n\t\"demo\":{\n\t\t\"text\":\"Hello World\"\n\t}\n}");

            function test() {
                let ftl = ftlEditor.getValue();
                let json = jsonEditor.getValue();
                if (ftl === "") {
                    dataEditor.setValue("Error: FreeMarker Code is empty!\n错误：FreeMarker Code是空！");
                    msg("Error: FreeMarker Code is empty!\n错误：FreeMarker Code是空！", "error");
                    return;
                }
                if (json === "") {
                    dataEditor.setValue("Error: JavaBean Code is empty!\n错误：JavaBean Code是空！");
                    msg("Error: JavaBean Code is empty!\n错误：JavaBean Code是空！", "error");
                    return;
                }
                let data = {};
                data.ftl = ftl;
                data.beanJson = json;
                $.ajax({
                    url: '/api/freemarker/test',
                    type: 'POST',
                    async: true,
                    data: JSON.stringify(data),
                    contentType: 'application/json;charset=UTF-8',
                    timeout: 60000,
                    dataType: 'json',
                    success: function (data, textStatus, jqXHR) {
                        if (data.code === 200) {
                            dataEditor.setValue(data.data);
                        } else {
                            dataEditor.setValue("Error.\n" + data.message);
                            msg(data.message, "error");
                        }
                    },
                    error: function (xhr, textStatus) {
                        dataEditor.setValue("Error.\n" + xhr.responseText);
                        msg(xhr.responseText, "error");
                    }
                });
            }

            function container_fluid() {
                if ($("#container").hasClass("container-fluid")) {
                    $("#container").attr("class", "container");
                    $("#KitBoxMenus").show();
                    $("#ftlContainer").attr("class", "col-sm-9 col-md-9");
                } else {
                    $("#container").attr("class", "container-fluid");
                    $("#KitBoxMenus").hide();
                    $("#ftlContainer").attr("class", "col-12");
                }
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>