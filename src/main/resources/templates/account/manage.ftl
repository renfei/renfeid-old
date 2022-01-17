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
    <div style="background: url('https://cdn.renfei.net/thunder/img/account_bg_blur.jpg') no-repeat;background-size: auto;background-position: bottom center;">
        <div class="container" style="height: 180px;">
            <div class="row">
                <div class="col-12" style="padding-top: 40px;">
                    <h1 style="color: #ffffff;font-size: 40px">${pageView.object.userName?html}</h1>
                    <small class="text-muted"
                           style="color: #ffffff!important;font-size: 21px;">欢迎回来！您可以在这里管理您的账户</small>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row mt-5">
            <div class="col-sm-3">
                <h2 style="color: #666;font-weight: 300;">账户</h2>
            </div>
            <div class="col-sm-9">
                <div class="form-group">
                    <label>ID: ${pageView.object.uuid!'-'}</label>
                    <small class="form-text text-muted"></small>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>用户名</label>
                            <label class="form-control account_info_label">${pageView.object.userName!'-'}</label>
                        </div>
                        <div class="form-group">
                            <label>注册时间</label>
                            <label class="form-control account_info_label">${pageView.object.registrationDate!?string("yyyy-MM-dd hh:mm:ss")}</label>
                        </div>
                        <div class="form-group">
                            <label>姓氏</label>
                            <label class="form-control account_info_label">${pageView.object.lastName!'-'}</label>
                            <small class="form-text text-muted"><a href="/account/manage/firstName">修改我的姓氏...</a></small>
                        </div>
                        <div class="form-group">
                            <label>名字</label>
                            <label class="form-control account_info_label">${pageView.object.firstName!'-'}</label>
                            <small class="form-text text-muted"><a href="/account/manage/lastName">修改我的名字...</a></small>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>电子邮箱</label>
                            <label class="form-control account_info_label">${pageView.object.email!'-'}</label>
                            <small class="form-text text-muted"><a href="/account/manage/email">修改电子邮箱地址...</a></small>
                        </div>
                        <div class="form-group">
                            <label>手机号码</label>
                            <label class="form-control account_info_label">${pageView.object.phone!'-'}</label>
                            <small class="form-text text-muted"><a href="/account/manage/phone">修改手机号码...</a></small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <div class="row mt-4">
            <div class="col-sm-3">
                <h2 style="color: #666;font-weight: 300;">安全</h2>
            </div>
            <div class="col-sm-9">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>密码</label>
                            <label class="form-control account_info_label">******</label>
                            <small class="form-text text-muted"><a href="/account/manage/password">更改密码...</a></small>
                        </div>
                        <div class="form-group">
                            <label>两步认证(U2F)</label>
                            <label class="form-control account_info_label">${(pageView.object.totp??)?string('已开启','未开启')}</label>
                            <small class="form-text text-muted"><a href="/account/manage/u2f">更改两步认证...</a></small>
                        </div>
                    </div>
                    <div class="col-md-6">
                    </div>
                </div>
            </div>
        </div>
        <#if pageView.discuzInfo??>
            <hr>
            <div class="row mt-4">
                <div class="col-sm-3">
                    <h2 style="color: #666;font-weight: 300;">论坛</h2>
                </div>
                <div class="col-sm-9">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>用户组</label>
                                <label class="form-control account_info_label">${pageView.discuzInfo.userGroup!'-'}</label>
                                <small class="form-text text-muted"><a
                                            href="https://bbs.renfei.net/home.php?mod=spacecp&ac=usergroup"
                                            target="_blank">前往查看...</a></small>
                            </div>
                            <div class="form-group">
                                <label>帖子数</label>
                                <label class="form-control account_info_label">${pageView.discuzInfo.postsCount!?c}</label>
                                <small class="form-text text-muted"><a
                                            href="https://bbs.renfei.net/forum.php?mod=guide&view=my" target="_blank">前往查看...</a></small>
                            </div>
                            <div class="form-group">
                                <label>在线时间</label>
                                <label class="form-control account_info_label">${pageView.discuzInfo.oltime!?c} 小时</label>
                                <small class="form-text text-muted"><a
                                            href="https://bbs.renfei.net/home.php?mod=space&uid=1&do=profile"
                                            target="_blank">前往查看...</a></small>
                            </div>
                            <div class="form-group">
                                <label>精华数</label>
                                <label class="form-control account_info_label">${pageView.discuzInfo.essenceCount!?c}</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>积分</label>
                                <label class="form-control account_info_label">${pageView.discuzInfo.points!?c}</label>
                                <small class="form-text text-muted"><a
                                            href="https://bbs.renfei.net/home.php?mod=spacecp&ac=credit"
                                            target="_blank">前往查看...</a></small>
                            </div>
                            <div class="form-group">
                                <label>金钱</label>
                                <label class="form-control account_info_label">${pageView.discuzInfo.money!?c}</label>
                            </div>
                            <div class="form-group">
                                <label>威望</label>
                                <label class="form-control account_info_label">${pageView.discuzInfo.prestige!?c}</label>
                            </div>
                            <div class="form-group">
                                <label>贡献</label>
                                <label class="form-control account_info_label">${pageView.discuzInfo.contribution!?c}</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </#if>
    </div>
</@compress>
<@footer pageView></@footer>
</body>
</html>