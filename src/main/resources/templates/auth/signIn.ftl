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
                <h1 style="font-size: 24px;font-weight: 300;">Sign in to RenFei.net</h1>
            </div>
            <div id="errorMsg" class="alert alert-danger alert-dismissible fade show" role="alert"
                 style="display: none;">
                <span id="errorMsgContent"></span>
            </div>
            <div class="border rounded mt-3 p-4" style="background-color: #f6f8fa;">
                <div class="form-group">
                    <label for="username" style="font-size: 14px;">用户名</label>
                    <input type="text" class="form-control form-control-sm" id="username" tabindex="1"
                           aria-describedby="usernameHelp" autocomplete="off">
                    <small id="usernameHelp" class="form-text text-muted">您可以使用用户名、邮箱地址、手机号码</small>
                </div>
                <div class="form-group" id="sendVerCodeBtn" style="display: none;">
                    <input type="button" name="commit" value="发送验证码" tabindex="2" id="sendCode"
                           onclick="sendCodeOnclick()"
                           class="btn btn-primary btn-block btn-sm">
                </div>
                <div class="form-group">
                    <label for="password" style="width:100%;font-size: 14px;"><span id="pwdLable">密码</span><a href="#"
                                                                                                              class="float-right">忘记密码？</a></label>
                    <input type="password" class="form-control form-control-sm" id="password" tabindex="3"
                           aria-describedby="passwordHelp" autocomplete="off">
                    <small id="passwordHelp" class="form-text text-muted">您可以使用您的<a
                                href="JavaScript:usePwdSignIn()">密码</a>或者<a
                                href="JavaScript:useVerCodeSignIn()">动态验证码</a>登录</small>
                </div>
                <div class="form-group" style="display: none;" id="u2fInput">
                    <label for="tOtp" style="font-size: 14px;">两步认证（U2F）</label>
                    <input type="text" class="form-control form-control-sm" id="tOtp" autocomplete="off"
                           onkeyup="this.value=this.value.replace(/\D/g,'')" tabindex="4">
                </div>
                <input id="signInBtn" type="button" name="commit" value="登 录" tabindex="5"
                       class="btn btn-success btn-block"
                       onclick="signIn()">
            </div>
            <div class="border rounded mt-3 p-4" style="font-size: 14px;">
                没有账户？<a href="/auth/signUp">立即创建</a>一个。
            </div>
        </form>
    </div>
    <script src="https://cdn.renfei.net/thunder/js/jsencrypt.js" type="text/javascript"></script>
    <script src="https://cdn.renfei.net/thunder/js/crypto-js/3.1.2/core.js" type="text/javascript"></script>
    <script src="https://cdn.renfei.net/thunder/js/crypto-js/3.1.2/cipher-core.js" type="text/javascript"></script>
    <script src="https://cdn.renfei.net/thunder/js/crypto-js/3.1.2/enc-base64.js" type="text/javascript"></script>
    <script src="https://cdn.renfei.net/thunder/js/crypto-js/3.1.2/mode-ecb.js" type="text/javascript"></script>
    <script src="https://cdn.renfei.net/thunder/js/crypto-js/3.1.2/aes.js" type="text/javascript"></script>
    <div id="jScriptBox"></div>
    <@footer pageView></@footer>
    <script>
        <#if pageView.object??>
        let signed = "1";
        let ucScript = "${pageView.object.ucScript}";
        <#else >
        let signed = "0";
        let ucScript = "";
        </#if>
        <#if callback??>
        let oauthCallback = "${callback?html}";
        <#else>
        let oauthCallback = "";
        </#if>
        let signInVo = {};
        let useVerCode = false;

        $(function () {
            if (signed === "1") {
                if (ucScript !== "") {
                    let ucScripts = ucScript.split("|");
                    if (ucScripts.length === 2) {
                        loadJS(ucScripts[0], function () {
                            loadJS(ucScripts[1], function () {
                                if (oauthCallback === "") {
                                    window.location.href = "/";
                                } else {
                                    window.location.href = oauthCallback;
                                }
                            });
                        });
                    } else if (ucScripts.length === 1) {
                        loadJS(ucScripts[0], function () {
                            if (oauthCallback === "") {
                                window.location.href = "/";
                            } else {
                                window.location.href = oauthCallback;
                            }
                        });
                    } else {
                        if (oauthCallback === "") {
                            window.location.href = "/";
                        } else {
                            window.location.href = oauthCallback;
                        }
                    }
                } else {
                    if (oauthCallback === "") {
                        window.location.href = "/";
                    } else {
                        window.location.href = oauthCallback;
                    }
                }
            }
        });

        function errorMsg(msg) {
            $("#errorMsg #errorMsgContent").html(msg);
            $("#errorMsg").show();
        }

        function usePwdSignIn() {
            useVerCode = false;
            $("#sendVerCodeBtn").hide();
            $("#pwdLable").html("密码");
        }

        function useVerCodeSignIn() {
            useVerCode = true;
            $("#sendVerCodeBtn").show();
            $("#pwdLable").html("验证码");
        }

        let t = 60;
        let interSendCodePending;

        function sendCodePending() {
            t--;
            $("#sendCode").attr('disabled', true);
            $("#sendCode").val("发送验证码 (" + t + ")");
            if (t <= 0) {
                clearInterval(interSendCodePending);
                $("#sendCode").val("发送验证码");
                $("#sendCode").attr('disabled', false);
            }
        }

        function setSendCodePending() {
            t = 60;
            interSendCodePending = setInterval("sendCodePending()", 1000);
        }

        function sendCodeOnclick() {
            $("#sendCode").attr('disabled', true);
            setSendCodePending();
        }

        function signIn() {
            signInVo.userName = $("#username").val();
            signInVo.password = aesencrypt($("#password").val());
            signInVo.keyUuid = getStore("aesKeyId");
            signInVo.tOtp = $("#tOtp").val();
            signInVo.useVerCode = useVerCode;
            if (signInVo.userName === "") {
                errorMsg("请填写用户名，您可以使用用户名、邮箱地址、手机号码。");
                return;
            }
            if (signInVo.password === "") {
                if (signInVo.useVerCode) {
                    errorMsg("请填写验证码。");
                } else {
                    errorMsg("请填写密码。");
                }
                return;
            }
            $("#signInBtn").attr('disabled', true);
            $("#signInBtn").val('登录中...');
            grecaptcha.ready(function () {
                grecaptcha.execute('${ReCAPTCHA_Client_Key}', {action: 'signIn'}).then(function (token) {
                    signInVo.reCAPTCHAToken = token;
                    $.ajax({
                        url: '/-/api/auth/signIn',
                        type: 'POST',
                        async: true,
                        data: JSON.stringify(signInVo),
                        timeout: 20000,
                        dataType: 'JSON',
                        contentType: "application/json;charset=utf-8",
                        beforeSend: function (xhr) {
                        },
                        success: function (data, textStatus, jqXHR) {
                            $("#signInBtn").val('登 录');
                            $("#signInBtn").attr('disabled', false);
                            if (data.code === 200) {
                                if (data.data !== "") {
                                    let datas = data.data.split("|");
                                    if (datas.length === 2) {
                                        loadJS(datas[0], function () {
                                            loadJS(datas[1], function () {
                                                if (oauthCallback === "") {
                                                    window.location.href = "/";
                                                } else {
                                                    window.location.href = oauthCallback;
                                                }
                                            });
                                        });
                                    } else if (datas.length === 1) {
                                        loadJS(datas[0], function () {
                                            if (oauthCallback === "") {
                                                window.location.href = "/";
                                            } else {
                                                window.location.href = oauthCallback;
                                            }
                                        });
                                    } else {
                                        if (oauthCallback === "") {
                                            window.location.href = "/";
                                        } else {
                                            window.location.href = oauthCallback;
                                        }
                                    }
                                } else {
                                    if (oauthCallback === "") {
                                        window.location.href = "/";
                                    } else {
                                        window.location.href = oauthCallback;
                                    }
                                }
                            } else if (data.code === 402) {
                                $("#u2fInput").show();
                                errorMsg("账户开启了两步认证（U2F），请输入U2F验证码");
                                $("#signInBtn").val('登 录');
                                $("#signInBtn").attr('disabled', false);
                                return false;
                            } else {
                                if ("AESKeyId不存在" === data.message) {
                                    removeStore("ClientPublicKey");
                                    removeStore("ClientPrivateKey");
                                    removeStore("aesKey");
                                    removeStore("aesKeyId");
                                    return signIn();
                                } else {
                                    errorMsg(data.message);
                                    $("#signInBtn").val('登 录');
                                    $("#signInBtn").attr('disabled', false);
                                    return false;
                                }
                            }
                        },
                        error: function (xhr, textStatus) {
                            $("#signInBtn").val('登 录');
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