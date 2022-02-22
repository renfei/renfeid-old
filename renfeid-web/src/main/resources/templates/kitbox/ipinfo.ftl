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
                        <h5 class="card-title">IP地址信息查询工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            IP地址信息查询工具，开放服务接口实现IP信息查询，查询IP地址所属地理位置</h6>
                        <div style="margin: auto;max-width: 600px;">
                            <form>
                                <label class="sr-only" for="iptext">IP</label>
                                <div class="input-group mb-2 mr-sm-2">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">IP:</div>
                                    </div>
                                    <input type="text" class="form-control" value="${myip!''}"
                                           id="iptext" placeholder="IP">
                                    <div class="input-group-prepend">
                                        <div class="btn btn-primary" onclick="query()">Query</div>
                                    </div>
                                </div>
                            </form>
                            <form>
                                <div class="form-group row">
                                    <label for="countryShort"
                                           class="col-sm-2 col-form-label col-form-label-sm">国家代码</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="countryShort"
                                               value="${pageView.object.countryShort!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="countryLong"
                                           class="col-sm-2 col-form-label col-form-label-sm">国家</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="countryLong"
                                               value="${pageView.object.countryLong!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="region" class="col-sm-2 col-form-label col-form-label-sm">省份</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="region"
                                               value="${pageView.object.region!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="city" class="col-sm-2 col-form-label col-form-label-sm">城市</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="city"
                                               value="${pageView.object.city!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="isp" class="col-sm-2 col-form-label col-form-label-sm">ISP</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="isp"
                                               value="${pageView.object.isp!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="zipcode" class="col-sm-2 col-form-label col-form-label-sm">邮编</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="zipcode"
                                               value="${pageView.object.zipCode!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="latitude" class="col-sm-2 col-form-label col-form-label-sm">纬度</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="latitude"
                                               value="${pageView.object.latitude!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="longitude" class="col-sm-2 col-form-label col-form-label-sm">经度</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="longitude"
                                               value="${pageView.object.longitude!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="timezone" class="col-sm-2 col-form-label col-form-label-sm">时区</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="timezone"
                                               value="${pageView.object.timeZone!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="domain" class="col-sm-2 col-form-label col-form-label-sm">domain</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="domain"
                                               value="${pageView.object.domain!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="iddcode"
                                           class="col-sm-2 col-form-label col-form-label-sm">iddcode</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="iddcode"
                                               value="${pageView.object.getIDDCode()!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="areacode"
                                           class="col-sm-2 col-form-label col-form-label-sm">areaCode</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="areacode"
                                               value="${pageView.object.areaCode!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="weatherstationcode" class="col-sm-2 col-form-label col-form-label-sm">weatherStationCode</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="weatherstationcode"
                                               value="${pageView.object.weatherStationCode!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="weatherstationname" class="col-sm-2 col-form-label col-form-label-sm">weatherStationName</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="weatherstationname"
                                               value="${pageView.object.weatherStationName!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="mcc" class="col-sm-2 col-form-label col-form-label-sm">mcc</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="mcc"
                                               value="${pageView.object.getMCC()!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="mnc" class="col-sm-2 col-form-label col-form-label-sm">mnc</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="mnc"
                                               value="${pageView.object.getMNC()!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="mobilebrand" class="col-sm-2 col-form-label col-form-label-sm">mobileBrand</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="mobilebrand"
                                               value="${pageView.object.mobileBrand!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="elevation"
                                           class="col-sm-2 col-form-label col-form-label-sm">elevation</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="elevation"
                                               value="${pageView.object.elevation!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="usagetype"
                                           class="col-sm-2 col-form-label col-form-label-sm">usageType</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="usagetype"
                                               value="${pageView.object.usageType!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="status" class="col-sm-2 col-form-label col-form-label-sm">status</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="status"
                                               value="${pageView.object.status!'...'}">
                                    </div>
                                </div>
                            </form>
                            <div>
                                <blockquote style="font-size: 14px;">
                                    <p>IP查询接口服务已经开放。更多开放接口服务，请参考<a
                                                href="https://www.renfei.net/swagger-ui.html"
                                                target="_blank">开放接口文档</a>。
                                    </p>
                                    <p>此工具使用的接口是：https://www.renfei.net/api/ip/{ip}</p>
                                    <footer>{ip} - 更换为需要查询的IP地址，使用 GET 请求。</footer>
                                </blockquote>
                            </div>
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
            function query() {
                var ip = $("#iptext").val();
                if (ip === '') {
                    msg("请填写要查询的IP地址", "error");
                    return;
                }
                $.ajax({
                    url: '/api/ip/' + ip,
                    type: 'GET',
                    async: true,
                    timeout: 10000,
                    dataType: 'json',
                    success: function (data, textStatus, jqXHR) {
                        if (data.code === 200) {
                            $("#countryShort").val(data.data.countryShort);
                            $("#countryLong").val(data.data.countryLong);
                            $("#region").val(data.data.region);
                            $("#city").val(data.data.city);
                            $("#isp").val(data.data.isp);
                            $("#latitude").val(data.data.latitude);
                            $("#longitude").val(data.data.longitude);
                            $("#timezone").val(data.data.timeZone);
                            $("#domain").val(data.data.domain);
                            $("#zipcode").val(data.data.zipCode);
                            $("#netspeed").val(data.data.netspeed);
                            $("#iddcode").val(data.data.iddcode);
                            $("#areacode").val(data.data.areaCode);
                            $("#weatherstationcode").val(data.data.weatherStationCode);
                            $("#weatherstationname").val(data.data.weatherStationName);
                            $("#mcc").val(data.data.mcc);
                            $("#mnc").val(data.data.mnc);
                            $("#mobilebrand").val(data.data.mobileBrand);
                            $("#elevation").val(data.data.elevation);
                            $("#usagetype").val(data.data.usageType);
                        } else {
                            msg(data.message, "error");
                        }
                    },
                    error: function (xhr, textStatus) {
                        msg(xhr.responseText, "error");
                    }
                })
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>