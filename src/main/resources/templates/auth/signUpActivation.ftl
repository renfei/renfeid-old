<#include "../layout/layout.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head pageView></@head>
    <body>
    <@header pageView></@header>
    <div id="form">
        <div class="pb-4" style="margin-top: 80px;">
            <div class="container text-center">
                <div>
                    <i class="fa fa-clipboard-check" aria-hidden="true" style="font-size: 60px;color: #6F6F6F;"></i>
                </div>
            </div>
        </div>
        <div style="width: 340px; margin: auto;margin-bottom: 230px;" class="px-3">
            <div class="p-0"
                 style="margin-bottom: 15px;text-align: center;text-shadow: none;background-color: initial;border: 0;">
                <h1 style="font-size: 24px;font-weight: 300;">现在开始激活您的账户</h1>
                <h4 style="font-size: 14px;">请输入您注册时填写的邮箱或手机号、验证码</h4>
            </div>
            <div id="errorMsg" class="alert alert-danger alert-dismissible fade show" role="alert"
                 style="display: none;">
                <span id="errorMsgContent">You should check in on some of those fields below.</span>
            </div>
            <div class="border rounded mt-3 p-4" style="background-color: #f6f8fa;">
                <div class="form-group">
                    <label for="emailOrPhone" style="font-size: 14px;">邮箱或手机号</label>
                    <input type="text" class="form-control form-control-sm" id="emailOrPhone" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="code" style="width:100%;font-size: 14px;">验证码</label>
                    <input type="text" class="form-control form-control-sm" id="code" aria-describedby="codelHelp"
                           autocomplete="off" value="${pageView.object!?html}">
                </div>
                <input id="activationBtn" type="button" name="commit" value="激 活" tabindex="3"
                       class="btn btn-primary btn-block"
                       onclick="activation()">
            </div>
        </div>
    </div>
    <div id="success" style="display: none;">
        <div class="pb-4" style="margin-top: 80px;">
            <div class="container text-center">
                <div>
                    <i class="fa fa-check-circle" aria-hidden="true" style="font-size: 60px;color: #28a745;"></i>
                </div>
            </div>
        </div>
        <div style="width: 340px; margin: auto;margin-bottom: 230px;" class="px-3">
            <div class="p-0"
                 style="margin-bottom: 15px;text-align: center;text-shadow: none;background-color: initial;border: 0;">
                <h1 style="font-size: 24px;font-weight: 300;">您的账户已经激活。</h1>
                <h4 style="font-size: 14px;">接下来您可以登录您的账户了。</h4>
            </div>
            <div class="border rounded mt-3 p-4" style="font-size: 14px;">
                恭喜您已经激活了您的账户，接下来您可以<a href="/auth/signIn">登录</a>您的账户开始享受我们提供的服务了。
            </div>
        </div>
    </div>
    <div id="fail" style="display: none;">
        <div class="pb-4" style="margin-top: 80px;">
            <div class="container text-center">
                <div>
                    <i class="fa fa-times-circle" aria-hidden="true" style="font-size: 60px;color: red;"></i>
                </div>
            </div>
        </div>
        <div style="width: 340px; margin: auto;margin-bottom: 230px;" class="px-3">
            <div class="p-0"
                 style="margin-bottom: 15px;text-align: center;text-shadow: none;background-color: initial;border: 0;">
                <h1 style="font-size: 24px;font-weight: 300;">未能激活您的账户。</h1>
                <h4 style="font-size: 14px;">验证码错误或者已经过期。</h4>
            </div>
            <div class="border rounded mt-3 p-4" style="font-size: 14px;">
                很遗憾，我们未能正确的识别您的账户，这可能是由于验证码错误或者已经过期，您可以尝试<a href="/auth/signIn">登录</a>，我们将为您重新发送激活邮件。
            </div>
        </div>
    </div>

    <@footer pageView>
        <script>
            function errorMsg(msg) {
                $("#errorMsg #errorMsgContent").html(msg);
                $("#errorMsg").show();
            }

            function activation() {
                if ($("#emailOrPhone").val() === "") {
                    errorMsg("邮箱或手机号不能为空");
                    return;
                }
                if ($("#code").val() === "") {
                    errorMsg("验证码不能为空");
                    return;
                }
                let activationVo = {};
                activationVo.emailOrPhone = $("#emailOrPhone").val();
                activationVo.code = $("#code").val();
                $("#activationBtn").attr('disabled', true);
                $("#activationBtn").val('激活中...');
                $.ajax({
                    url: '/-/api/auth/signUp/activation',
                    type: 'POST',
                    async: true,
                    data: JSON.stringify(activationVo),
                    timeout: 5000,
                    dataType: 'JSON',
                    contentType: "application/json;charset=utf-8",
                    success: function (data, textStatus, jqXHR) {
                        if (data.code === 200) {
                            $("#form").hide();
                            $("#success").show();
                        } else {
                            $("#form").hide();
                            $("#fail").show();
                        }
                    },
                    error: function (xhr, textStatus) {
                        $("#signInBtn").val('创 建');
                        $("#signInBtn").attr('disabled', false);
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
</@compress>