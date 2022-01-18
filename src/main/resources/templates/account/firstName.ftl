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
                <li class="breadcrumb-item active" aria-current="page">姓名管理</li>
            </ol>
        </nav>
    </div>
    <div class="container">
        <div class="row mt-5 mb-5">
            <div class="col-md-3">
                <h2 style="color: #666;font-weight: 300;">姓名管理</h2>
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
                                <label for="staticEmail" class="col-md-4 col-form-label">当前姓氏</label>
                                <div class="col-md-8">
                                    <input type="text" readonly class="form-control-plaintext"
                                           value="${pageView.object.lastName!'-'}">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputPassword" class="col-md-4 col-form-label">新姓氏</label>
                                <div class="col-md-8">
                                    <input tabindex="1" type="text" class="form-control" id="lastName">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputPassword" class="col-md-4 col-form-label">当前名字</label>
                                <div class="col-md-8">
                                    <input type="text" readonly class="form-control-plaintext"
                                           value="${pageView.object.firstName!'-'}">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputPassword" class="col-md-4 col-form-label">新名字</label>
                                <div class="col-md-8">
                                    <input tabindex="2" type="text" class="form-control" id="firstName">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputPassword" class="col-md-4 col-form-label"></label>
                                <div class="col-md-8">
                                    <button tabindex="3" type="button" class="btn btn-success btn-sm" onclick="save()">保存</button>
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

        function save() {
            let url = "/-/api/account/manage/firstName?lastName=" + $("#lastName").val() + "&firstName=" + $("#firstName").val();
            $.ajax({
                url: url,
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