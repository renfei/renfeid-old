<#include "../layout/layout.ftl"/>
<#include "../layout/comments.ftl"/>
<#include "./kitboxmenu.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head pageView>
        <style>
            #xml, #data {
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
                        <h5 class="card-title">XPath在线测试工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">XPath Online Test Tools</h6>
                        <div class="row">
                            <div class="col-12">
                                <form>
                                    <div class="form-group">
                                        <label>XML报文</label>
                                        <div id="xml" class="form-control"></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <div class="input-group is-invalid">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">XPath表达式</span>
                                    </div>
                                    <input type="text" class="form-control" id="xpath"
                                           value="/bookstore/book/title[@lang='zh']">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button" onclick="test()">Test</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <form>
                                    <div class="form-group">
                                        <label>匹配结果</label>
                                        <div id="data" class="form-control"></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div>
                            <blockquote style="font-size: 14px;">
                                <p>
                                    XPath 是一门在 XML 文档中查找信息的语言。XPath 可用来在 XML 文档中对元素和属性进行遍历。
                                </p>
                                <p>
                                    XPath 是 W3C XSLT 标准的主要元素，并且 XQuery 和 XPointer 都构建于 XPath 表达之上。
                                </p>
                                <p>
                                    因此，对 XPath 的理解是很多高级 XML 应用的基础。
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
        <script src="https://cdn.jsdelivr.net/npm/ace-builds@1.4.13/src-min/ace.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/ace-builds@1.4.13/src-min/ext-language_tools.min.js"></script>
        <script>
            ace.config.set("basePath", "https://cdn.jsdelivr.net/npm/ace-builds@1.4.13/src-min");
            var xmlEditor = ace.edit('xml');
            var dataEditor = ace.edit('data');
            xmlEditor.session.setMode('ace/mode/xml');
            xmlEditor.setOptions({
                enableBasicAutocompletion: true,
                enableSnippets: true,
                enableLiveAutocompletion: true,
                fontSize: 14
            });
            dataEditor.session.setMode('ace/mode/text');
            dataEditor.setOptions({
                enableBasicAutocompletion: true,
                enableSnippets: true,
                enableLiveAutocompletion: true,
                fontSize: 14
            });
            xmlEditor.setValue("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                "\n" +
                "<bookstore>\n" +
                "\n" +
                "<book>\n" +
                "  <title lang=\"en\">Harry Potter</title>\n" +
                "  <author>J K. Rowling</author> \n" +
                "  <year>2005</year>\n" +
                "  <price>29.99</price>\n" +
                "</book>\n" +
                "\n" +
                "<book>\n" +
                "  <title lang=\"zh\">任霏博客</title>\n" +
                "  <author>任霏</author> \n" +
                "  <year>2022</year>\n" +
                "  <price>9.99</price>\n" +
                "</book>\n" +
                "\n" +
                "</bookstore>");

            function test() {
                dataEditor.setValue("");
                let code = xmlEditor.getValue();
                let pattern = $("#xpath").val();
                if (code) if (pattern) try {
                    var dom = new DOMParser;
                    xmldom = dom.parseFromString(code, "text/xml");
                    var i = xmldom.evaluate(pattern, xmldom.documentElement, null, XPathResult.ORDERED_NODE_ITETATOR_TYPE, null);
                    if (null !== i) {
                        for (var o = i.iterateNext(); o;) {
                            dataEditor.setValue(o.innerHTML);
                            o = i.iterateNext();
                        }
                    }
                } catch (pattern) {
                    dataEditor.setValue("Error:" + pattern);
                    msg("Error:" + pattern, "error");
                }
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>