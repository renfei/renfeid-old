<#include "../layout/layout.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head pageView></@head>
    <body>
    <@header pageView></@header>
    <div class="pt-5 pb-4">
        <div class="container text-center">
            <a href="/">
                <img src="https://cdn.renfei.net/Logo/RF.svg" width="48" height="48">
            </a>
        </div>
    </div>
    <div style="width: 340px; margin: auto;" class="px-3">
        <form>
            <div class="p-0"
                 style="margin-bottom: 15px;text-align: center;text-shadow: none;background-color: initial;border: 0;">
                <h4 style="font-size: 14px;font-weight: 300;color: #6e6a73;">Join RenFei.Net</h4>
                <h1 style="font-size: 24px;font-weight: 300;">创建您的账户</h1>
            </div>
            <div id="errorMsg" class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;">
                <span id="errorMsgContent">You should check in on some of those fields below.</span>
            </div>
            <div class="border rounded mt-3 p-4" style="background-color: #f6f8fa;">
                <div class="form-group">
                    <label for="username" style="font-size: 14px;">用户名</label>
                    <input type="text" class="form-control form-control-sm" id="username" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="email" style="width:100%;font-size: 14px;">电子邮箱</label>
                    <input type="email" class="form-control form-control-sm" id="email" aria-describedby="emailHelp" autocomplete="off">
                    <small id="emailHelp" class="form-text text-muted">请输入真实的电子邮箱，注册后需要您到电子邮箱内点击激活链接。</small>
                </div>
                <div class="form-group">
                    <label for="password" style="width:100%;font-size: 14px;">密码</label>
                    <input type="password" class="form-control form-control-sm" id="password" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="password2" style="width:100%;font-size: 14px;">再次输入密码</label>
                    <input type="password" class="form-control form-control-sm" id="password2" autocomplete="off">
                </div>
                <input id="signUpBtn" type="button" name="commit" value="创 建" tabindex="3"
                       class="btn btn-primary btn-block"
                       onclick="signUp()">
            </div>
            <div class="border rounded mt-3 p-4" style="font-size: 14px;">
                已经拥有账户？<a href="/auth/signIn">立即登录</a>。
            </div>
        </form>
    </div>
    <script src="https://cdn.renfei.net/thunder/js/jsencrypt.js" type="text/javascript"></script>
    <script src="https://cdn.renfei.net/thunder/js/crypto-js/3.1.2/core.js" type="text/javascript"></script>
    <script src="https://cdn.renfei.net/thunder/js/crypto-js/3.1.2/cipher-core.js" type="text/javascript"></script>
    <script src="https://cdn.renfei.net/thunder/js/crypto-js/3.1.2/enc-base64.js" type="text/javascript"></script>
    <script src="https://cdn.renfei.net/thunder/js/crypto-js/3.1.2/mode-ecb.js" type="text/javascript"></script>
    <script src="https://cdn.renfei.net/thunder/js/crypto-js/3.1.2/aes.js" type="text/javascript"></script>
    <@footer pageView></@footer>
    <script src="https://www.recaptcha.net/recaptcha/api.js?render=${ReCAPTCHA_Client_Key}"></script>
    <script>
        let signUpVo = {};
        let useVerCode = false;

        function errorMsg(msg) {
            $("#errorMsg #errorMsgContent").html(msg);
            $("#errorMsg").show();
        }

        function signUp() {
            if($("#username").val()===""){
                errorMsg("用户名不能为空");
                return;
            }
            if($("#email").val()===""){
                errorMsg("电子邮箱不能为空");
                return;
            }
            if($("#password").val()===""){
                errorMsg("密码不能为空");
                return;
            }
            if($("#password").val()!==$("#password2").val()){
                errorMsg("两次密码输入不一致");
                return;
            }
            signUpVo.userName = $("#username").val();
            signUpVo.email = $("#email").val();
            signUpVo.password = aesencrypt($("#password").val());
            signUpVo.keyUuid = getStore("aesKeyId");
            $("#signUpBtn").attr('disabled', true);
            $("#signUpBtn").val('创建中...');
            grecaptcha.ready(function () {
                grecaptcha.execute('${ReCAPTCHA_Client_Key}', {action: 'signUp'}).then(function (token) {
                    signUpVo.reCAPTCHAToken = token;
                    $.ajax({
                        url: '/auth/signUp',
                        type: 'POST',
                        async: true,
                        data: JSON.stringify(signUpVo),
                        timeout: 5000,
                        dataType: 'JSON',
                        contentType: "application/json;charset=utf-8",
                        beforeSend: function (xhr) {
                        },
                        success: function (data, textStatus, jqXHR) {
                            $("#signUpBtn").val('创 建');
                            $("#signUpBtn").attr('disabled', false);
                            if (data.code === 200) {
                                window.location.href = '/auth/signUp/success';
                            } else {
                                errorMsg(data.message);
                                return false;
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
                });
            });
        }
    </script>
    </body>
    </html>
</@compress>