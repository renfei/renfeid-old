<#include "../layout/layout.ftl"/>
<#include "../layout/comments.ftl"/>
<#include "./kitboxmenu.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head pageView>
        <script type="text/javascript" charset="utf-8"
                src="https://cdn.renfei.net/font/ueditor/ueditor.config.js"></script>
        <script type="text/javascript" charset="utf-8"
                src="https://cdn.renfei.net/font/ueditor/ueditor.all.min.js"></script>
        <script type="text/javascript" charset="utf-8"
                src="https://cdn.renfei.net/font/ueditor/lang/zh-cn/zh-cn.js"></script>
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
                        <h5 class="card-title">UEditor</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            UEditor是由百度web前端研发部开发所见即所得富文本web编辑器，具有轻量，可定制，注重用户体验等特点，开源基于MIT协议，允许自由使用和修改代码。</h6>
                        <p class="card-text">
                            任霏注：由于UEditor已经常年失修，截止本工具部署时间2020年10月，最后一次版本发布还是2016年8月，官网的在线Demo也下线了，而有的时候需要用到在线的编辑器，我就将UEditor部署在我的工具箱中了。</p>
                        <script id="editor" class="mb-2" type="text/plain" style="width:100%;height:500px;"></script>
                        <div id="btns">
                            <div>
                                <button class="btn btn-outline-primary btn-sm mb-2" onclick="getAllHtml()">获得整个html的内容
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onclick="getContent()">获得内容</button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onclick="setContent()">写入内容</button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onclick="setContent(true)">追加内容
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onclick="getContentTxt()">获得纯文本
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onclick="getPlainTxt()">获得带格式的纯文本
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onclick="hasContent()">判断是否有内容
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onclick="setFocus()">使编辑器获得焦点
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onmousedown="isFocus(event)">
                                    编辑器是否获得焦点
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onmousedown="setblur(event)">
                                    编辑器失去焦点
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onclick="getText()">获得当前选中的文本
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onclick="insertHtml()">插入给定的内容
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2" id="enable" onclick="setEnabled()">
                                    可以编辑
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onclick="setDisabled()">不可编辑
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2"
                                        onclick=" UE.getEditor('editor').setHide()">隐藏编辑器
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2"
                                        onclick=" UE.getEditor('editor').setShow()">显示编辑器
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2"
                                        onclick=" UE.getEditor('editor').setHeight(300)">设置高度为300默认关闭了自动长高
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onclick="getLocalData()">获取草稿箱内容
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onclick="clearLocalData()">清空草稿箱
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onclick="createEditor()">创建编辑器
                                </button>
                                <button class="btn btn-outline-primary btn-sm mb-2" onclick="deleteEditor()">删除编辑器
                                </button>
                            </div>
                        </div>
                        <textarea class="form-control" id="ue_content" readonly rows="5"></textarea>
                    </div>
                </div>
                <@comments commentList account!"null" "KITBOX" kitBoxId></@comments>
            </div>
        </div>
    </div>
    <@footer pageView>
        <script type="text/javascript">
            window.UEDITOR_HOME_URL = "/";
            var ue = UE.getEditor('editor');

            function isFocus(e) {
                alert(UE.getEditor('editor').isFocus());
                UE.dom.domUtils.preventDefault(e)
            }

            function setblur(e) {
                UE.getEditor('editor').blur();
                UE.dom.domUtils.preventDefault(e)
            }

            function insertHtml() {
                var value = prompt('插入html代码', '');
                UE.getEditor('editor').execCommand('insertHtml', value)
            }

            function createEditor() {
                enableBtn();
                UE.getEditor('editor');
            }

            function getAllHtml() {
                $("#ue_content").val(UE.getEditor('editor').getAllHtml());
            }

            function getContent() {
                $("#ue_content").val(UE.getEditor('editor').getContent());
            }

            function getPlainTxt() {
                $("#ue_content").val(UE.getEditor('editor').getPlainTxt());
            }

            function setContent(isAppendTo) {
                UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
            }

            function setDisabled() {
                UE.getEditor('editor').setDisabled('fullscreen');
                disableBtn("enable");
            }

            function setEnabled() {
                UE.getEditor('editor').setEnabled();
                enableBtn();
            }

            function getText() {
                var range = UE.getEditor('editor').selection.getRange();
                range.select();
                var txt = UE.getEditor('editor').selection.getText();
                $("#ue_content").val(txt);
            }

            function getContentTxt() {
                $("#ue_content").val(UE.getEditor('editor').getContentTxt());
            }

            function hasContent() {
                $("#ue_content").val(UE.getEditor('editor').hasContents());
            }

            function setFocus() {
                UE.getEditor('editor').focus();
            }

            function deleteEditor() {
                disableBtn();
                UE.getEditor('editor').destroy();
            }

            function disableBtn(str) {
                var div = document.getElementById('btns');
                var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
                for (var i = 0, btn; btn = btns[i++];) {
                    if (btn.id == str) {
                        UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
                    } else {
                        btn.setAttribute("disabled", "true");
                    }
                }
            }

            function enableBtn() {
                var div = document.getElementById('btns');
                var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
                for (var i = 0, btn; btn = btns[i++];) {
                    UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
                }
            }

            function getLocalData() {
                $("#ue_content").val(UE.getEditor('editor').execCommand("getlocaldata"));
            }

            function clearLocalData() {
                UE.getEditor('editor').execCommand("clearlocaldata");
                alert("已清空草稿箱")
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>