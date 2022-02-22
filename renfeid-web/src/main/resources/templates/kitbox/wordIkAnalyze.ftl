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
                        <h5 class="card-title">在线分词工具与API接口(IK Analyze)</h5>
                        <h6 class="card-subtitle mb-2 text-muted">基于IKAnalyzer同时提供了对Lucene的默认优化实现</h6>
                        <div class="row">
                            <div class="col-12">
                                <form>
                                    <div class="form-group">
                                        <label>待分词内容</label>
                                        <textarea id="data" name="data" class="form-control" rows="8">
                                            书籍是人类进步的阶梯。
                                        </textarea>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <button type="button" class="btn btn-primary" onclick="analyze()"
                                        style="width: 100%;margin: 10px 0;">分词
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <form>
                                    <div class="form-group">
                                        <label>分词结果</label>
                                        <textarea id="result" name="ftl" class="form-control"
                                                  style="min-height: 200px;"></textarea>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-6">
                                <form>
                                    <div class="form-group">
                                        <label>分词结果</label>
                                        <div id="json" class="form-control"
                                             style="min-height: 200px;overflow: auto;"></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div>
                            <blockquote style="font-size: 14px;">
                                <p>IK Analyzer是一个开源的，基于java语言开发的轻量级的中文分词工具包。从2006年12月推出1.0版开始，
                                    IKAnalyzer已经推出了3个大版本。最初，它是以开源项目Luence为应用主体的，结合词典分词和文法分析算法的中文分词组件。新版本的IK Analyzer
                                    3.0则发展为面向Java的公用分词组件，独立于Lucene项目，同时提供了对Lucene的默认优化实现。
                                </p>
                                <p>
                                    此处提供的分词服务仅用于简单的轻量的分词场景，如果需要更复杂的分词逻辑或者自定义词库，请自行集成开发IK Analyzer。
                                </p>
                                <p>
                                    分词能力已经作为开放接口共享，此工具的接口为：https://www.renfei.net/api/wordIkAnalyze 。详情请见：
                                    <a href="https://www.renfei.net/swagger-ui.html">
                                        开放接口
                                    </a>
                                </p>
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
            function analyze() {
                let data = $("#data").val();
                if (data === "") {
                    $("#result").val("错误：待分词内容是空！");
                    $("#json").html("错误：待分词内容是空！");
                    msg("错误：待分词内容是空！", "error");
                    return;
                }
                $.ajax({
                    url: '/api/wordIkAnalyze',
                    type: 'POST',
                    async: true,
                    timeout: 60000,
                    dataType: 'json',
                    data: JSON.stringify(data),
                    contentType: "application/json",
                    success: function (data, textStatus, jqXHR) {
                        if (data.code === 200) {
                            let resultData = data.data;
                            let result = "";
                            let html = "";
                            for (let i = 0; i < resultData.length; i++) {
                                result += resultData[i].word + ",";
                                html += "<h6>" + resultData[i].word + " <span class=\"badge badge-secondary\">" + resultData[i].type + "</span></h6>";
                            }
                            result = result.substr(0, result.length - 1);
                            $("#result").val(result);
                            $("#json").html(html);
                        } else {
                            $("#result").val("Error.\n" + data.message);
                            msg(data.message, "error");
                        }
                    },
                    error: function (xhr, textStatus) {
                        $("#result").val("Error.\n" + xhr.responseText);
                        msg(xhr.responseText, "error");
                    }
                });
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>