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
                        <h5 class="card-title">苹果 iOS Plist 文件在线生成制作工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            苹果 iOS Plist 文件在线生成制作工具，在服务器上部署 Plist 文件，用户即可通过自己的服务器下载 IPA 安装文件</h6>
                        <div style="margin: auto;max-width: 600px;">
                            <form method="post" action="/kitbox/plist">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <div class="form-group row">
                                    <label for="countryShort"
                                           class="col-sm-3 col-form-label col-form-label-sm">APP名称</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control form-control-sm" name="appname"
                                               id="appname">
                                        <small class="form-text text-muted">例如：任霏博客</small>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="countryLong"
                                           class="col-sm-3 col-form-label col-form-label-sm">版本号</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control form-control-sm" name="version"
                                               id="version">
                                        <small class="form-text text-muted">例如：1.0.2</small>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="countryLong"
                                           class="col-sm-3 col-form-label col-form-label-sm">包名（Bundle ID）</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control form-control-sm" name="bundleid"
                                               id="bundleid">
                                        <small class="form-text text-muted">例如：net.renfei.app</small>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="region"
                                           class="col-sm-3 col-form-label col-form-label-sm">IPA下载地址</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control form-control-sm" name="ipa" id="ipa">
                                        <small class="form-text text-muted">例如：https://cdn.renfei.net/app/renfei.ipa</small>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="city" class="col-sm-3 col-form-label col-form-label-sm">ICON链接地址</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control form-control-sm" name="icon" id="icon">
                                        <small class="form-text text-muted">例如：https://cdn.renfei.net/app/icon.png</small>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12">
                                        <button type="submit" class="btn btn-primary" onclick="return submit_plist()"
                                                style="width: 100%;margin: 10px 0;">
                                            生成
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <div>
                                <blockquote style="font-size: 14px;">
                                    <p>
                                        什么是Plist文件？通过Plist文件实现itms-services协议在线安装IPA，在iOS7以后，plist文件必须部署到HTTPS服务器上，才能下载IPA。</p>
                                    <p>使用方式可以参考：<a href="https://www.renfei.net/posts/1003441" target="_blank">企业版 iOS
                                            IPA 安装包分发与 plist 文件生成教程 通过 itms-services 协议在线安装 IPA</a></p>
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
            function submit_plist() {
                let appname = $("#appname").val();
                let version = $("#version").val();
                let bundleid = $("#bundleid").val();
                let ipa = $("#ipa").val();
                let icon = $("#icon").val();
                if (appname.length === 0) {
                    msg("APP名称不能为空", "error");
                    return false;
                }
                if (version.length === 0) {
                    msg("版本号不能为空", "error");
                    return false;
                }
                if (bundleid.length === 0) {
                    msg("包名（Bundle ID）不能为空", "error");
                    return false;
                }
                if (ipa.length === 0) {
                    msg("IPA下载地址不能为空", "error");
                    return false;
                }
                if (icon.length === 0) {
                    msg("ICON链接地址不能为空", "error");
                    return false;
                }
                if (!ipa.startsWith("https://")) {
                    msg("IPA下载地址必须是https://开头的", "error");
                    return false;
                }
                if (!icon.startsWith("http")) {
                    msg("ICON链接地址必须是http://或者https://开头的", "error");
                    return false;
                }
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>