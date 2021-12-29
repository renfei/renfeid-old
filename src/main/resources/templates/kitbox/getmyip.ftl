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
                        <h5 class="card-title">公网IP获取工具支持Linux、Windows、API</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            http://ip.renfei.net 支持IPv6和IPv4的地址，IPv6优先。<br/>http://ipv4.renfei.net 仅支持IPv4。</h6>
                        <div style="margin: auto;max-width: 600px;">
                            <form>
                                <label class="sr-only" for="iptext">IP</label>
                                <div class="input-group mb-2 mr-sm-2">
                                    <input type="text" class="form-control" value="ip.renfei.net" readonly/>
                                    <div class="input-group-prepend">
                                        <div class="btn btn-primary" onclick="generate()">Query</div>
                                    </div>
                                </div>
                            </form>
                            <form>
                                <div class="form-group row">
                                    <div class="col-sm-12">
                                        <textarea id="data" name="data" class="form-control" rows="8"></textarea>
                                    </div>
                                </div>
                            </form>
                            <form>
                                <label class="sr-only" for="iptext">IP</label>
                                <div class="input-group mb-2 mr-sm-2">
                                    <input type="text" class="form-control" value="ipv4.renfei.net" readonly/>
                                    <div class="input-group-prepend">
                                        <div class="btn btn-primary" onclick="generatev4()">Query</div>
                                    </div>
                                </div>
                            </form>
                            <form>
                                <div class="form-group row">
                                    <div class="col-sm-12">
                                        <textarea id="datav4" name="data" class="form-control" rows="8"></textarea>
                                    </div>
                                </div>
                            </form>
                            <div>
                                <blockquote style="font-size: 14px;">
                                    <p>Windows下的用户可以使用浏览器直接访问链接：<a href="http://ip.renfei.net" target="_blank">http://ip.renfei.net</a>
                                        查看自己的互联网公网出口IP地址。</p>
                                    <p>如果只想查询IPv4地址可以使用：<a href="http://ipv4.renfei.net" target="_blank">http://ipv4.renfei.net</a>
                                        查看自己的互联网公网出口IP地址。</p>
                                    <p>
                                    <h3>Linux命令行用户获取互联网公网出口IP地址</h3>Linux用户没有图形界面，例如在服务器上，可以使用 curl
                                    命令查看自己的互联网公网出口IP地址：</p>
                                    <pre><code class="bash">curl&nbsp;ip.renfei.net</code></pre>
                                    <img src="//cdn.renfei.net/upload/image/2020/20200104134319.png" alt="获取互联网公网出口IP地址"
                                         class="img-fluid shadow bg-white rounded mx-auto d-block"/>
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
            $(function () {
                generate();
                generatev4();
            });

            function generate() {
                $.ajax({
                    url: 'https://ip.renfei.net',
                    type: 'GET',
                    async: true,
                    timeout: 60000,
                    dataType: 'json',
                    success: function (data, textStatus, jqXHR) {
                        $("#data").val(data.clientIP);
                    },
                    error: function (xhr, textStatus) {
                        $("#data").val("Error.\n" + xhr.responseText);
                        msg(xhr.responseText, "error");
                    }
                });
            }

            function generatev4() {
                $.ajax({
                    url: 'https://ipv4.renfei.net',
                    type: 'GET',
                    async: true,
                    timeout: 60000,
                    dataType: 'json',
                    success: function (data, textStatus, jqXHR) {
                        $("#datav4").val(data.clientIP);
                    },
                    error: function (xhr, textStatus) {
                        $("#datav4").val("Error.\n" + xhr.responseText);
                        msg(xhr.responseText, "error");
                    }
                });
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>