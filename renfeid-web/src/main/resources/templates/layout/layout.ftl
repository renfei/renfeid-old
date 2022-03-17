<#macro head pageView>
    <@compress single_line=false>
        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <title>${pageView.pageHead.title!?html}</title>
            <#if pageView.pageHead.description?? && ((pageView.pageHead.description)?length lt 150)>
                <meta name="description" content="${pageView.pageHead.description!}">
            <#elseif pageView.pageHead.description??>
                <meta name="description" content="${(pageView.pageHead.description)?substring(0,150)}...">
            </#if>
            <meta name="keywords" content="${pageView.pageHead.keywords!}">
            <meta name="author" content="${pageView.pageHead.author!}"/>
            <meta name="copyright" content="${pageView.pageHead.copyright!}"/>
            <meta http-equiv="Content-Language" content="zh-CN"/>
            <meta name="renderer" content="webkit">
            <meta name="theme-color" content="#333333"/>
            <meta name="_csrf" content="${(_csrf.token)!}"/>
            <meta name="_csrf_header" content="${(_csrf.headerName)!}"/>
            <#list pageView.pageHead.dnsPrefetch as dns>
                <link rel="dns-prefetch" href="${dns!}">
            </#list>
            <#if pageView.pageHead.ogProtocol??>
                <meta property="og:title" content="${pageView.pageHead.ogProtocol.title!}"/>
                <meta property="og:description" content="${pageView.pageHead.ogProtocol.description!}"/>
                <meta property="og:type" content="article"/>
                <meta property="og:image" content="${pageView.pageHead.ogProtocol.image!}"/>
                <meta property="og:url" content="${pageView.pageHead.ogProtocol.url!}"/>
                <meta property="og:release_date"
                      content="${pageView.pageHead.ogProtocol.releaseDate?string("yyyy-MM-dd'T'HH:mm:ssZ")}"/>
                <meta property="og:author" content="${pageView.pageHead.ogProtocol.author!}"/>
                <meta property="og:locale" content="${pageView.pageHead.ogProtocol.locale!}"/>
                <meta property="og:site_name" content="${pageView.pageHead.ogProtocol.siteName!}"/>
                <meta name="twitter:card" content="summary_large_image"/>
                <meta name="twitter:site" content="@renfeii"/>
                <meta name="twitter:creator" content="@renfeii"/>
                <meta name="twitter:title" content="${pageView.pageHead.ogProtocol.title!}">
                <meta name="twitter:description" content="${pageView.pageHead.ogProtocol.description!}">
                <meta name="twitter:domain" content="www.renfei.net">
                <meta name="twitter:image:src" content="${pageView.pageHead.ogProtocol.image!}">
                <meta name="og:image:alt" content="RenFei 任霏">
            </#if>
            <meta property="fb:app_id" content="${pageView.pageHead.fbAppId!}"/>
            <meta property="fb:pages" content="${pageView.pageHead.fbPages!}"/>
            <link id="favicon" rel="shortcut icon" href="${pageView.pageHead.favicon!}" type="image/x-icon"/>
            <link rel="apple-touch-icon" href="${pageView.pageHead.appleTouchIcon!}">
            <link rel="search" type="application/opensearchdescription+xml"
                  href="https://www.renfei.net/search/search.xml" title="任霏博客"/>
            <#list pageView.pageHead.css! as css>
                <link href="${css}" rel="stylesheet" type="text/css"/>
            </#list>
            <style>
                ${pageView.pageHead.cssText!}
            </style>
            <script>
                ${pageView.pageHead.jsText!}
            </script>
            <#nested>
        </head>
    </@compress>
</#macro>
<#macro header pageView>
    <@compress single_line=false>
        <nav class="navbar navbar-expand-lg navbar-dark nav">
            <div class="container-xl">
                <a class="navbar-brand site-logo-name" href="/">
                    <img class="align-middle" src="https://cdn.renfei.net/Logo/RF_white.svg" height="25" alt="任霏博客"/>
                    <h1 class="align-middle"
                        style="font-size: 1.25rem;font-weight: 100;display: initial;">任霏博客</h1>
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample07XL"
                        aria-controls="navbarsExample07XL" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarsExample07XL">
                    <ul class="navbar-nav mr-auto">
                        <#if pageView.pageHeader.menus??>
                            <#list pageView.pageHeader.menus as menu>
                                <#if menu.subLink?? && (menu.subLink?size > 0)>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="${menu.href!}"
                                           target="${menu.target!}"
                                           rel="${menu.rel!}" style="${menu.style!}" id="dropdown07XL"
                                           data-toggle="dropdown"
                                           aria-expanded="false">${menu.text!}</a>
                                        <div class="dropdown-menu" aria-labelledby="dropdown07XL">
                                            <#list menu.subLink as submenu>
                                                <a class="dropdown-item" href="${submenu.href!}"
                                                   target="${submenu.target!}"
                                                   rel="${submenu.rel!}" style="${submenu.style!}">
                                                    ${submenu.text!}
                                                </a>
                                            </#list>
                                        </div>
                                    </li>
                                <#else>
                                    <li class="nav-item">
                                        <a class="nav-link" href="${menu.href!}" target="${menu.target!}"
                                           rel="${menu.rel!}"
                                           style="${menu.style!}">
                                            ${menu.text!}
                                        </a>
                                    </li>
                                </#if>
                            </#list>
                        </#if>
                    </ul>
                    <form action="/search" method="get" class="form-inline my-2 my-lg-0">
                        <div class="input-group" style="width: 100%;">
                            <div class="input-group-prepend">
                                <select class="form-control form-control-sm btn-dark" name="type"
                                        style="border-top-right-radius: 0;border-bottom-right-radius: 0;">
                                    <option selected value="all">全部</option>
                                    <option value="POSTS">文章</option>
                                    <option value="DISCUZ">论坛</option>
                                    <option value="WEIBO">微博</option>
                                    <option value="PAGES">页面</option>
                                    <option value="PHOTO">相册</option>
                                    <option value="VIDEO">视频</option>
                                    <option value="KITBOX">工具箱</option>
                                </select>
                            </div>
                            <input type="text" class="form-control form-control-sm btn-dark" placeholder="搜索一下，发现更多"
                                   name="w"
                                   aria-label="搜索一下，发现更多" aria-describedby="button-search">
                            <div class="input-group-append">
                                <button class="btn btn-dark btn-sm" type="submit" id="button-search">搜索</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </nav>
        <#if pageView.pageHeader.notice??>
            <div class="header_notice">
                ${pageView.pageHeader.notice!}
            </div>
        </#if>
        <#if pageView.pageHeader.user??>
            <nav class="navbar navbar-expand-lg navbar-light bg-light nav" style="z-index: 1;">
                <div class="container-xl">
                    <div class="navbar-collapse"
                         style="display: -ms-flexbox!important;display: flex!important;-ms-flex-preferred-size: auto;flex-basis: auto;">
                        <ul class="navbar-nav ml-auto" style="-ms-flex-direction: row;flex-direction: row;">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="" style=""
                                   data-toggle="dropdown"
                                   style="font-size: 14px;padding-right: .5rem;padding-left: .5rem;"
                                   aria-expanded="false">${pageView.pageHeader.user.userName!?html}</a>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" href="/account/manage" style="">
                                        管理账户
                                    </a>
                                    <a class="dropdown-item" href="JavaScript:signOut()" style="">
                                        登 出
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        <#else>
            <nav class="navbar navbar-expand-lg navbar-light bg-light nav" style="z-index: 1;">
                <div class="container-xl">
                    <div class="navbar-collapse"
                         style="display: -ms-flexbox!important;display: flex!important;-ms-flex-preferred-size: auto;flex-basis: auto;">
                        <ul class="navbar-nav ml-auto" style="-ms-flex-direction: row;flex-direction: row;">
                            <li class="nav-item">
                                <a class="nav-link" href="JavaScript:signInFun()"
                                   style="font-size: 14px;padding-right: .5rem;padding-left: .5rem;">登录</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/auth/signUp"
                                   style="font-size: 14px;padding-right: .5rem;padding-left: .5rem;">注册</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </#if>
        <#nested>
        <input type="hidden" id="_paste" value="">
    </@compress>
</#macro>
<#macro footer pageView>
    <@compress single_line=false>
        <div class="footer">
            <div class="footer_image" style="background: url('/img/footer_image.webp') 50% center no-repeat;"></div>
            <div class="container">
                <div class="row">
                    <#list pageView.pageFooter.footerMenuLinks! as footerMenus>
                        <div class="col-lg-3 col-md-6 col-12 footerMenus">
                            <h3>${footerMenus.title!}</h3>
                            <ul>
                                <#list footerMenus.links! as link>
                                    <li>
                                        <a href="${link.href!}" target="${link.target!}" rel="nofollow noopener"
                                           style="${link.style!}">
                                            ${link.text!}
                                        </a>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </#list>
                </div>
                <#if pageView.pageFooter.showFriendlyLink??>
                    <div class="row" style="margin-top: 20px;">
                        <div class="col-12 text-left">
                            <ul class="footer-menu-ul">
                                <li class="float-left">
                                    <a href="javascipt:void(0)">
                                        <small class="text-muted small">友情链接：</small>
                                    </a>
                                </li>
                                <#list pageView.pageFooter.friendlyLink! as friendlyLink>
                                    <li class="float-left">
                                        <a href="${friendlyLink.href!}" target="${friendlyLink.target!}"
                                           rel="noopener" style="${friendlyLink.style!}">
                                            <small class="text-muted small">${friendlyLink.text!}</small>
                                        </a>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </#if>
                <div class="row" style="margin-top: 10px;">
                    <div class="col-12 text-right">
                        <ul class="footer-menu-ul">
                            <#list pageView.pageFooter.smallMenu! as smallMenu>
                                <li class="float-right">
                                    <a href="${smallMenu.href!}" target="${smallMenu.target!}" rel="nofollow noopener"
                                       style="${smallMenu.style!}">
                                        <small class="text-muted small">${smallMenu.text!}</small>
                                    </a>
                                </li>
                            </#list>
                        </ul>
                    </div>
                </div>
                <hr style="margin: 7px 0;">
                <div class="row">
                    <div class="col-12">
                        <small class="text-muted small float-left" style="color: #86868b;">Copyright
                            © ${.now?string["yyyy"]} RENFEI.NET All rights
                            reserved.</small>
                        <ul class="float-right footer-menu-ul">
                            <#if pageView.pageFooter.performanceExecCount??>
                                <li class="float-right">
                                    <small class="text-muted small float-right" style="color: #86868b;">
                                        calls ${pageView.pageFooter.performanceExecCount} methods.</small>
                                </li>
                            </#if>
                            <#if pageView.pageFooter.performanceExecTime??>
                                <li class="float-right">
                                    <small class="text-muted small float-right" style="color: #86868b;">
                                        Processed in ${pageView.pageFooter.performanceExecTime} second(s),</small>
                                </li>
                            </#if>
                            <li class="float-right">
                                <small class="text-muted small float-right"
                                       style="color: #86868b;">GMT+8, ${.now?string["yyyy-MM-dd HH:mm:ss"]},</small>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <ul class="float-right footer-menu-ul">
                            <#if pageView.pageFooter.buildTime??>
                                <li class="float-right">
                                    <small class="text-muted small float-right" style="color: #86868b;">
                                        Build: ${pageView.pageFooter.buildTime!}</small>
                                </li>
                            </#if>
                            <#if pageView.pageFooter.version??>
                                <li class="float-right">
                                    <small class="text-muted small float-right" style="color: #86868b;">
                                        Ver: ${pageView.pageFooter.version!}</small>
                                </li>
                            </#if>
                        </ul>
                    </div>
                </div>
                <div class="row" style="height: 20px;"></div>
            </div>
        </div>
        <div id="g-modal" class="modal fade" style="z-index: 999999999;background-color: rgba(0,0,1,.5);"
             data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="g-modal-label"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="g-modal-label">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        ...
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </div>
        </div>
        <#list pageView.pageFooter.jss! as js>
            <script src="${js}" type='text/javascript' charset="UTF-8"></script>
        </#list>
        <script>
            $(function () {
                if ($(".header_notice").length > 0) {
                    $(".header_notice").toggleClass('open');
                }
            });
        </script>
        <script>
            ${pageView.pageFooter.jsText!}
        </script>
        <#nested>
    </@compress>
</#macro>
<#macro share sharevo>
    <@compress single_line=true>
        <#if sharevo??>
            <div class="row">
                <div class="col-12">
                    <div class="btn-group" role="group" aria-label="Third group">
                        <button id="wechat-share-box" type="button" class="btn btn-sm btn-outline-dark"
                                style="border: 0;">
                            <i class="fab fa-weixin"></i>
                            <div class="wechat-share">
                                <img src="/other/qrcode?content=${sharevo.url}" width="150">
                            </div>
                        </button>
                    </div>
                    <div class="btn-group" role="group" aria-label="Third group">
                        <a href="http://connect.qq.com/widget/shareqq/index.html?url=${sharevo.url}&title=${sharevo.title}&source=RENFEI.NET&desc=${sharevo.describes}&pics=${sharevo.pics}&summary=${sharevo.title}"
                           target="_blank" rel="nofollow noopener" class="btn btn-sm btn-outline-dark"
                           style="border: 0;">
                            <i class="fab fa-qq"></i>
                        </a>
                    </div>
                    <div class="btn-group" role="group" aria-label="Third group">
                        <a href="http://www.facebook.com/dialog/feed?app_id=${sharevo.fb_appid}&redirect_uri=${sharevo.url}&link=${sharevo.url}&display=popup"
                           target="_blank" rel="nofollow noopener" class="btn btn-sm btn-outline-dark"
                           style="border: 0;">
                            <i class="fab fa-facebook-square"></i>
                        </a>
                    </div>
                    <div class="btn-group" role="group" aria-label="Third group">
                        <a href="https://twitter.com/intent/tweet?text=${sharevo.title}&url=${sharevo.url}"
                           target="_blank"
                           rel="nofollow noopener" class="btn btn-sm btn-outline-dark" style="border: 0;">
                            <i class="fab fa-twitter"></i>
                        </a>
                    </div>
                    <div class="btn-group" role="group" aria-label="Third group">
                        <a href="http://service.weibo.com/share/share.php?appkey=4264535112&url=${sharevo.url}&title=${sharevo.title}&pic=${sharevo.pics}"
                           target="_blank" rel="nofollow noopener" class="btn btn-sm btn-outline-dark"
                           style="border: 0;">
                            <i class="fab fa-weibo"></i>
                        </a>
                    </div>
                    <div class="btn-group" role="group" aria-label="Third group">
                        <a href="https://www.linkedin.com/shareArticle?mini=true&url=${sharevo.url}&title=${sharevo.title}&source=RENFEI.NET"
                           target="_blank" rel="nofollow noopener" class="btn btn-sm btn-outline-dark"
                           style="border: 0;">
                            <i class="fab fa-linkedin"></i>
                        </a>
                    </div>
                    <div class="btn-group" role="group" aria-label="Third group">
                        <button type="button" class="copyUrlBtn btn btn-sm btn-outline-dark" style="border: 0;">
                            <i class="fas fa-link"></i>
                        </button>
                    </div>
                    <#if sharevo.views??>
                        <div class="btn-group" role="group" aria-label="Third group">
                            <button type="button" class="btn btn-sm btn-outline-dark" style="border: 0;">
                                <i class="fas fa-eye"></i> ${sharevo.views}
                            </button>
                        </div>
                    </#if>
                    <#nested>
                </div>
            </div>
        </#if>
    </@compress>
</#macro>
<#macro adsense slot active>
    <@compress single_line=true>
        <#if active=="prod">
            <ins class="adsbygoogle"
                 style="display:block"
                 data-ad-client="ca-pub-8859756463807757"
                 data-ad-slot="${slot}"
                 data-ad-format="auto"
                 data-full-width-responsive="true"></ins>
            <script>
                (adsbygoogle = window.adsbygoogle || []).push({});
            </script>
        </#if>
    </@compress>
</#macro>