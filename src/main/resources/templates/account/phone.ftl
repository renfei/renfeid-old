<#include "../layout/layout.ftl"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
<@head pageView>
    <style>
        .account_info_label {
            padding: 0;
            margin: 0;
            border: 0;
            height: auto;
        }
    </style>
</@head>
<body>
<@header pageView></@header>
<@compress single_line=true>
    <div class="container">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb" style="background-color: #ffffff;">
                <li class="breadcrumb-item"><a href="/">首页</a></li>
                <li class="breadcrumb-item"><a href="/account/manage">账户管理</a></li>
                <li class="breadcrumb-item active" aria-current="page">手机号码</li>
            </ol>
        </nav>
    </div>
    <div class="container">
        <div class="row mt-5 mb-5">
            <div class="col-md-3">
                <h2 style="color: #666;font-weight: 300;">手机号码</h2>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-9">
                        <div id="errorMsg" class="alert alert-danger alert-dismissible fade show" role="alert"
                             style="display: none;">
                            <span id="errorMsgContent"></span>
                        </div>
                        <form>
                            <div class="form-group row">
                                <label for="staticEmail" class="col-md-4 col-form-label">手机号码</label>
                                <div class="col-md-8">
                                    <input type="text" readonly class="form-control-plaintext" id="staticEmail"
                                           value="${pageView.object.phone!'-'}">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputPassword" class="col-md-4 col-form-label">新手机号码</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="newPhone">
                                    <small class="form-text text-muted">点击下方发送验证码，我们会给您的新手机号码发送一条短信。</small>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputPassword" class="col-md-4 col-form-label"></label>
                                <div class="col-md-8">
                                    <button type="button" id="sendCode" class="btn btn-primary btn-sm btn-block"
                                            onclick="sendCodeOnclick()">发送验证码
                                    </button>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputPassword" class="col-md-4 col-form-label">验证码</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="vcode">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputPassword" class="col-md-4 col-form-label"></label>
                                <div class="col-md-8">
                                    <button type="button" class="btn btn-success btn-sm" onclick="save()">保存</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
    </div>
</@compress>
<@footer pageView>
    <script>
        function errorMsg(msg) {
            $("#errorMsg #errorMsgContent").html(msg);
            $("#errorMsg").show();
        }

        let t = 60;
        let interSendCodePending;

        function sendCodePending() {
            t--;
            $("#sendCode").attr('disabled', true);
            $("#sendCode").html("发送验证码 (" + t + ")");
            if (t <= 0) {
                clearInterval(interSendCodePending);
                $("#sendCode").html("发送验证码");
                $("#sendCode").attr('disabled', false);
            }
        }

        function setSendCodePending() {
            t = 60;
            interSendCodePending = setInterval("sendCodePending()", 1000);
        }

        function sendCodeOnclick() {
            if ($("#newPhone").val() === "") {
                errorMsg("请先填写新的手机号码");
                return;
            }
            $("#sendCode").attr('disabled', true);
            setSendCodePending();
            $.ajax({
                url: '/-/api/account/manage/phone/verCode?newPhone=' + $("#newPhone").val(),
                type: 'POST',
                async: true,
                timeout: 20000,
                dataType: 'JSON',
                contentType: "application/json;charset=utf-8",
                success: function (data, textStatus, jqXHR) {
                    if (data.code !== 200) {
                        errorMsg(data.message);
                    }
                },
                error: function (xhr, textStatus) {
                    t = -1
                },
                beforeSend: function (xhr) {
                    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                }
            });
        }

        function save() {
            if ($("#newPhone").val() === "") {
                errorMsg("请先填写新的手机号码");
                return;
            }
            if ($("#vcode").val() === "") {
                errorMsg("请填写验证码");
                return;
            }
            $.ajax({
                url: '/-/api/account/manage/phone?newPhone=' + $("#newPhone").val() + "&verCode=" + $("#vcode").val(),
                type: 'POST',
                async: true,
                timeout: 20000,
                dataType: 'JSON',
                contentType: "application/json;charset=utf-8",
                success: function (data, textStatus, jqXHR) {
                    if (data.code === 200) {
                        window.location.href = "/account/manage";
                    } else {
                        errorMsg(data.message);
                    }
                },
                error: function (xhr, textStatus) {
                },
                beforeSend: function (xhr) {
                    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                }
            });
        }
    </script>
</@footer>
</body>
</html>