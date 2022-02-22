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
                        <h5 class="card-title">域名ICP备案信息查询工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            域名ICP备案信息查询工具，查询域名是否已经ICP备案，ICP备案详细信息。
                        </h6>
                        <div>
                            <form>
                                <label class="sr-only" for="iptext">IP</label>
                                <div class="input-group mb-2 mr-sm-2" aria-describedby="domainHelp">
                                    <input type="text" class="form-control" id="domain"
                                           placeholder="Domain Name" value="renfei.net"/>
                                    <div class="input-group-prepend">
                                        <div id="btn" class="btn btn-primary" onclick="query()">查询</div>
                                    </div>
                                </div>
                                <small id="domainHelp" class="form-text text-muted">仅需输入主域名，不要输入二级域名，例如：renfei.net
                                    即可。</small>
                                <div class="form-group form-check">
                                    <input type="checkbox" class="form-check-input" name="refresh" id="refresh">
                                    <label class="form-check-label" for="refresh">强制刷新数据</label>
                                </div>
                            </form>
                            <form>
                                <div class="form-group row">
                                    <label for="countryShort"
                                           class="col-sm-2 col-form-label col-form-label-sm">主办单位名称</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="unitName" value="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="countryShort"
                                           class="col-sm-2 col-form-label col-form-label-sm">主办单位性质</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="natureName" value="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="countryShort"
                                           class="col-sm-2 col-form-label col-form-label-sm">主体备案号</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="mainLicence" value="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="countryShort"
                                           class="col-sm-2 col-form-label col-form-label-sm">网站备案号</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="serviceLicence" value="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="countryShort"
                                           class="col-sm-2 col-form-label col-form-label-sm">网站前置审批项</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="contentTypeName" value="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="countryShort"
                                           class="col-sm-2 col-form-label col-form-label-sm">审核日期</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="updateRecordTime" value="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="countryShort"
                                           class="col-sm-2 col-form-label col-form-label-sm">是否限制接入</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="limitAccess" value="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="countryShort"
                                           class="col-sm-2 col-form-label col-form-label-sm">负责人姓名</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="leaderName" value="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="countryShort"
                                           class="col-sm-2 col-form-label col-form-label-sm">数据缓存时间</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="cacheTime" value="">
                                    </div>
                                </div>
                            </form>

                            <div>
                                <blockquote style="font-size: 14px;">
                                    <p>ICP备案信息查询接口服务已经开放。更多开放接口服务，请参考<a
                                                href="https://www.renfei.net/swagger-ui.html"
                                                target="_blank">开放接口文档</a>。
                                    </p>
                                    <p>此工具使用的接口是：https://www.renfei.net/api/domain/icp/{domain}</p>
                                    <p>{domain} - 更换为需要查询的域名，使用 GET 请求。</p>
                                    <p>域名必须输入顶级域名，不能是二级域名，例如：renfei.net，而不能是 bbs.renfei.net。</p>
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
            function query() {
                let domain = $("#domain").val();
                if (domain === "") {
                    msg("请填写要查询的域名", "error");
                    return;
                }
                $("#btn").html("正在查询");
                let url = "/api/domain/icp/" + domain;
                if ($('#refresh').is(':checked')) {
                    url += "?refresh=true";
                }
                $("#unitName").val("");
                $("#natureName").val("");
                $("#mainLicence").val("");
                $("#serviceLicence").val("");
                $("#updateRecordTime").val("");
                $("#limitAccess").val("");
                $("#leaderName").val("");
                $("#cacheTime").val("");
                $("#contentTypeName").val("");
                $.ajax({
                    url: url,
                    type: 'GET',
                    async: true,
                    timeout: 60000,
                    dataType: 'json',
                    success: function (data, textStatus, jqXHR) {
                        if (data.code === 200) {
                            if (data.data === undefined || data.data == null) {
                                $("#btn").html("查询");
                                msg("未查询到备案信息", "error");
                            } else {
                                $("#unitName").val(data.data.unitName);
                                $("#natureName").val(data.data.natureName);
                                $("#mainLicence").val(data.data.mainLicence);
                                $("#serviceLicence").val(data.data.serviceLicence);
                                $("#updateRecordTime").val(data.data.updateRecordTime);
                                $("#limitAccess").val(data.data.limitAccess);
                                $("#leaderName").val(data.data.leaderName);
                                $("#cacheTime").val(data.data.cacheTime);
                                $("#contentTypeName").val(data.data.contentTypeName);
                                $("#btn").html("查询");
                            }
                        } else {
                            msg(data.message, "error");
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