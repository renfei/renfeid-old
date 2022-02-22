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
                        <h5 class="card-title">下划线驼峰命名互转工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            下划线(Line)与驼峰(Hump)命名风格的相互转换工具，例如：test_test/testTest的相互转换</h6>
                        <div class="row">
                            <div class="col-md-12">
                                <form>
                                    <div class="form-group">
                                        <label>待转换的内容</label>
                                        <textarea id="content" class="form-control" rows="8"></textarea>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <button type="button" class="btn btn-primary mb-3" onclick="toHump()"
                                        style="width: 100%;margin: 10px 0;">下划线转驼峰风格
                                </button>
                                <button type="button" class="btn btn-primary mb-3" onclick="toLine()"
                                        style="width: 100%;margin: 10px 0;">驼峰转下划线风格
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <form>
                                    <div class="form-group">
                                        <label>转换后结果</label>
                                        <textarea id="data" name="data" class="form-control" rows="8"></textarea>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div>
                            <blockquote style="font-size: 14px;">
                                <p>
                                    骆驼式命名法（Camel-Case）又称驼峰式命名法，是电脑程式编写时的一套命名规则（惯例）。正如它的名称CamelCase所表示的那样，是指混合使用大小写字母来构成变量和函数的名字。程序员们为了自己的代码能更容易的在同行之间交流，所以多采取统一的可读性比较好的命名方式。
                                </p>
                                <p>下划线命名法是随着C语言的出现流行起来的，在UNIX/LIUNX这样的环境，以及GNU代码中使用非常普遍。</p>
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
                $("#content").val("my_test_name\nmyTestName");
            });

            function toHump() {
                let content = $("#content").val();
                $("#data").val(content.replace(/\_(\w)/g, function (all, letter) {
                    return letter.toUpperCase();
                }));
            }

            function toLine() {
                let content = $("#content").val();
                $("#data").val(content.replace(/([A-Z])/g, "_$1").toLowerCase());
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>