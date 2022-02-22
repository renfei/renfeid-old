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
                <li class="breadcrumb-item active" aria-current="page">两步认证(U2F)</li>
            </ol>
        </nav>
    </div>
    <div class="container">
        <div class="row mt-5 mb-5">
            <div class="col-md-3">
                <h2 style="color: #666;font-weight: 300;">两步认证(U2F)</h2>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-9">
                        <div id="errorMsg" class="alert alert-danger alert-dismissible fade show" role="alert"
                             style="display: none;">
                            <span id="errorMsgContent"></span>
                        </div>
                        ${pageView.object}
                        <#if pageView.object.totp??>
                            <form>
                                <div class="form-group row">
                                    <label for="staticEmail" class="col-md-4 col-form-label">当前状态</label>
                                    <div class="col-md-8">
                                        <input type="text" readonly class="form-control-plaintext" value="已开启">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputPassword" class="col-md-4 col-form-label">密码</label>
                                    <div class="col-md-8">
                                        <input type="password" class="form-control" id="pwd">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputPassword" class="col-md-4 col-form-label">两步认证码</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="totp">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputPassword" class="col-md-4 col-form-label"></label>
                                    <div class="col-md-8">
                                        <button type="button" class="btn btn-danger btn-sm btn-block" onclick="closeU2F()">
                                            关闭两步认证(U2F)
                                        </button>
                                    </div>
                                </div>
                            </form>
                        <#else>
                            <form>
                                <div class="form-group row">
                                    <label for="staticEmail" class="col-md-4 col-form-label">当前状态</label>
                                    <div class="col-md-8">
                                        <input type="text" readonly class="form-control-plaintext" value="未开启">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputPassword" class="col-md-4 col-form-label">二维码</label>
                                    <div class="col-md-8">
                                        <img src="/other/qrcode?content=${totpString}" style="width: 100%;"/>
                                        <small class="form-text text-muted">请使用 Google Authenticator、Authy或1Password
                                            扫描二维码添加动态验证码。</small>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputPassword" class="col-md-4 col-form-label">秘钥</label>
                                    <div class="col-md-8">
                                        <small class="form-text text-muted">${secretKey}</small>
                                        <small class="form-text text-muted">当无法扫描时，请复制上方的秘钥，进行手动添加。</small>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputPassword" class="col-md-4 col-form-label">密码</label>
                                    <div class="col-md-8">
                                        <input type="password" class="form-control" id="pwd">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputPassword" class="col-md-4 col-form-label">两步认证码</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="totp">
                                        <small class="form-text text-muted">请扫描二维码或手动添加秘钥，然后将生成的动态验证码填入上方两步认证码</small>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputPassword" class="col-md-4 col-form-label"></label>
                                    <div class="col-md-8">
                                        <button type="button" class="btn btn-success btn-sm btn-block" onclick="save()">
                                            开启两步认证(U2F)
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </#if>
                    </div>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
    </div>
</@compress>
<script src="https://cdn.renfei.net/thunder/js/jsencrypt.js" type="text/javascript"></script>
<script src="https://cdn.renfei.net/thunder/js/crypto-js/3.1.2/core.js" type="text/javascript"></script>
<script src="https://cdn.renfei.net/thunder/js/crypto-js/3.1.2/cipher-core.js" type="text/javascript"></script>
<script src="https://cdn.renfei.net/thunder/js/crypto-js/3.1.2/enc-base64.js" type="text/javascript"></script>
<script src="https://cdn.renfei.net/thunder/js/crypto-js/3.1.2/mode-ecb.js" type="text/javascript"></script>
<script src="https://cdn.renfei.net/thunder/js/crypto-js/3.1.2/aes.js" type="text/javascript"></script>
<@footer pageView>
    <script>
        let secretKey = '${secretKey!}';
        let saveU2FVO = {};

        function errorMsg(msg) {
            $("#errorMsg #errorMsgContent").html(msg);
            $("#errorMsg").show();
        }

        function closeU2F() {
            if ($("#pwd").val() === "") {
                errorMsg("请填写您的密码");
                return;
            }
            if ($("#totp").val() === "") {
                errorMsg("请填写两步认证码");
                return;
            }
            saveU2FVO.totp = $("#totp").val();
            saveU2FVO.pwd = aesencrypt($("#pwd").val());
            saveU2FVO.keyId = getStore("aesKeyId");
            $.ajax({
                url: '/-/api/account/manage/u2f',
                type: 'DELETE',
                async: true,
                timeout: 20000,
                dataType: 'JSON',
                data: JSON.stringify(saveU2FVO),
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
                    let csrfHeader = $("meta[name='_csrf_header']").attr("content");
                    let csrf = $("meta[name='_csrf']").attr("content");
                    if (csrfHeader !== '') {
                        xhr.setRequestHeader(csrfHeader, csrf);
                    }
                }
            });
        }

        function save() {
            if ($("#pwd").val() === "") {
                errorMsg("请填写您的密码");
                return;
            }
            if ($("#totp").val() === "") {
                errorMsg("请扫描二维码或添加认证秘钥，然后填写两步认证码");
                return;
            }
            saveU2FVO.totp = $("#totp").val();
            saveU2FVO.pwd = aesencrypt($("#pwd").val());
            saveU2FVO.secretKey = aesencrypt(secretKey);
            saveU2FVO.keyId = getStore("aesKeyId");
            $.ajax({
                url: '/-/api/account/manage/u2f',
                type: 'POST',
                async: true,
                timeout: 20000,
                dataType: 'JSON',
                data: JSON.stringify(saveU2FVO),
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
                    let csrfHeader = $("meta[name='_csrf_header']").attr("content");
                    let csrf = $("meta[name='_csrf']").attr("content");
                    if (csrfHeader !== '') {
                        xhr.setRequestHeader(csrfHeader, csrf);
                    }
                }
            });
        }
    </script>
</@footer>
</body>
</html>