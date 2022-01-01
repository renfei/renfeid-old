<#include "../layout/layout.ftl"/>
<#include "../layout/comments.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
<@head pageView></@head>
<body>
<@header pageView></@header>
    <div class="about-banner">
        <div class="container">
            <div class="row">
                <div class="col-12 about-img"
                     style="background-image: url('/img/fixing_bugs.svg');background-position:right center;margin-top: 10px;">
                    <div class="about-title d-none d-sm-block">
                        <h1 style="font-size: 40px;line-height: 1.05;font-weight: 500;letter-spacing: .008em;">
                            Error 404 (Not Found)!!
                        </h1>
                        <h3 style="font-size: 32px;line-height: 1.09375;font-weight: 300;letter-spacing: .011em;">
                            您访问的内容不存在！!
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container" style="padding-top: 50px;">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="d-sm-none mt-5">
                        <h1 style="font-size: 40px;line-height: 1.05;font-weight: 500;letter-spacing: .008em;">
                            Error 404 (Not Found)!!
                        </h1>
                        <h3 style="font-size: 32px;line-height: 1.09375;font-weight: 300;letter-spacing: .011em;">
                            您访问的内容不存在！
                        </h3>
                    </div>
                </div>
            </div>
            <div class="row mt-5" style="min-height: 400px;">
                <div class="col-md-2">
                </div>
                <div class="col-md-8">
                    <form action="/search" method="get">
                        <div class="input-group input-group-lg mb-3">
                            <div class="input-group-prepend">
                                <select class="form-control form-control-lg" name="type"
                                        style="border-top-right-radius: 0;border-bottom-right-radius: 0;">
                                    <option selected value="all">全部</option>
                                    <option value="post">文章</option>
                                    <option value="bbs">论坛</option>
                                    <option value="weibo">微博</option>
                                    <option value="page">页面</option>
                                    <option value="photo">相册</option>
                                    <option value="video">视频</option>
                                    <option value="kitbox">工具箱</option>
                                </select>
                            </div>
                            <input type="text" class="form-control" placeholder="搜索一下，发现更多" name="w"
                                   aria-label="搜索一下，发现更多" aria-describedby="button-search">
                            <div class="input-group-append">
                                <button class="btn btn-outline-primary" type="submit" id="button-search">搜索</button>
                            </div>
                        </div>
                    </form>
                    <h5 class="text-center">您访问的内容已经无法找到，不妨尝试一下搜索功能。</h5>
                </div>
                <div class="col-md-2">
                </div>
            </div>
        </div>
    </div>
    <@footer pageView></@footer>
    </body>
    </html>
</@compress>