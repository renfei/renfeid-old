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
                        <h5 class="card-title">域名QPS压力测试工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            警告：域名解析QPS压力测试工具，仅限测试域名DNS抗压能力，请勿用于发动DNS攻击
                        </h6>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="input-group mb-3">
                                            <div class="input-group-append">
                                                <span class="input-group-text">域名</span>
                                            </div>
                                            <input type="text" class="form-control" id="domain" value="google.com">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12">
                                        <div class="input-group mb-3">
                                            <div class="input-group-append">
                                                <span class="input-group-text">TPS</span>
                                            </div>
                                            <input type="text" class="form-control" id="tps" value="250"
                                                   oninput="value=value.replace(/[^\d]/g,'')">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="input-group mb-3">
                                            <div class="input-group-append">
                                                <span class="input-group-text">请求次数</span>
                                            </div>
                                            <input type="text" class="form-control" id="count" value="1000"
                                                   oninput="value=value.replace(/[^\d]/g,'')">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12">
                                        <div class="input-group mb-3">
                                            <div class="input-group-append">
                                                <span class="input-group-text">执行次数</span>
                                            </div>
                                            <input type="text" class="form-control" id="excount" readonly value="0">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <button type="button" class="btn btn-primary" onclick="start()" id="startBtn"
                                        style="width: 100%;margin: 10px 0;">开 始
                                </button>
                            </div>
                        </div>
                        <div>
                            <blockquote style="font-size: 14px;">
                                <p>
                                    Chrome浏览器最大TPS为「250」，如果需要更大的量，可以找更多小伙伴从全国不通地区同时执行测试。
                                </p>
                                <p style="color: red;">
                                    警告：域名解析QPS压力测试工具，仅限测试自己的域名DNS抗压能力，请勿用于发动DNS攻击。自建或免费的DNS服务可能会被打死。
                                </p>
                                <p>
                                    执行效果如下：
                                </p>
                                <a href="https://cdn.renfei.net/upload/image/2021/20210106161114.png"
                                   target="_blank"><img
                                            src="https://cdn.renfei.net/upload/image/2021/20210106161114.png"
                                            alt="域名DNS解析QPS压力测试" class="img-fluid shadow mb-5 bg-white rounded"/></a>
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
            let COUNT = 0,
                STARTTIME = (new Date).getTime(),
                DOMAIN = "google.com",
                MAX_COUNT = 5e4,
                TPS = 100,
                TIMERID = 0,
                STARTED = false;

            function makeid_low(t) {
                for (var e = "",
                         n = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789",
                         o = 0; t > o; o++) e += n.charAt(Math.floor(Math.random() * n.length));
                return e
            }

            function makeid_new(t) {
                var e = new Uint8Array((t || 40) / 2);
                return window.crypto.getRandomValues(e),
                    text = [].map.call(e,
                        function (t) {
                            return t.toString(16)
                        }).join(""),
                    text
            }

            function get(t) {
                $.ajax({
                    url: t,
                    dataType: "script",
                    timeout: .01,
                    cache: false,
                    complete: function () {
                        COUNT += 1
                    }
                })
            }

            function rSendDo() {
                if (MAX_COUNT > 0 && COUNT <= MAX_COUNT) {
                    get("https://" + makeid(Math.floor(64 * Math.random() + 1)) + "." + DOMAIN)
                }
            }

            function rSend(t) {
                TIMERID = setInterval("rSendDo()", t)
            }

            makeid = makeid_new;
            try {
                test_msg = makeid(5);
            } catch (err) {
                makeid = makeid_low;
            }
            let updateId;

            function updateCounter() {
                $("#excount").val(COUNT.toString());
            }

            function start() {
                if (STARTED) {
                    clearInterval(TIMERID);
                    clearInterval(updateId);
                    $("#startBtn").html("开 始");
                    STARTED = false;
                } else {
                    if ($("#domain").val() === "") {
                        $("#domain").val("google.com");
                    }
                    DOMAIN = $("#domain").val();
                    if ($("#count").val() === "") {
                        $("#count").val("1000");
                    }
                    MAX_COUNT = parseInt($("#count").val());
                    if ($("#tps").val() === "") {
                        $("#tps").val("250");
                    }
                    TPS = parseInt($("#tps").val());
                    rSend(1000 * (1 / TPS));
                    updateId = setInterval("updateCounter()", 1E3);
                    $("#startBtn").html("停 止");
                    STARTED = true;
                }
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>