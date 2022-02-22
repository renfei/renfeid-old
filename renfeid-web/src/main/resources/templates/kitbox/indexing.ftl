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
                        <h5 class="card-title" style="margin-top: 10px;">Indexing - 百度/必应/谷歌-站长推送工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            此工具利用「百度-必应-谷歌」站长工具或开放平台接口即时推送网站更新给搜索引擎，加快蜘蛛程序爬取与更新。
                        </h6>
                        <img src="https://cdn.renfei.net/upload/image/2021/indexing_tools.gif" style="width: 100%;"
                             alt="Indexing - 百度/必应/谷歌-站长推送工具">
                        <div style="font-size: 14px;">
                            <p style="text-indent: 2em;">
                                此工具代码作者已经使用多年，同时作者正在探索 Swing/AWT 编程，此工具作为作者 Swing/AWT
                                编程处女作，顺便将技术能力通过可视化界面分享出来，让不懂编程的站长也可以使用 API 接口的便利。
                            </p>
                            <h6>安全声明</h6>
                            <p style="text-indent: 2em;">作为软件开发工程师的我，深知安全的重要性，因为此工具运行时需要站长提供 API 的 Token
                                令牌，这就相当于密码授权。</p>
                            <p style="text-indent: 2em;">为了证明此工具不会抓取上传站长的 Token
                                令牌，所以开源公布出来，欢迎监督。（PS:本来想闭源使用混淆编译发布，防止被仿冒）</p>
                            <p style="text-indent: 2em;">因为开源以后任何人可以利用源码修改制作小版本，站长们请认准 renfei.net 官网。如果发现仿冒请向我举报。</p>
                            <h6>使用帮助</h6>
                            <p style="text-indent: 2em;">本工具基于 Java8 制作，如果您拥有 Java8 或更高的 JDK/JRE 环境，可以直接下载 Jar
                                包文件，使用如下命令即可启动：</p>
                            <p style="text-indent: 2em;">java -jar Indexing.jar</p>
                            <p style="text-indent: 2em;">如果您不确定自己的环境是否拥有 JDK/JRE，我还提供了环境打包版本，由于操作系统不同请下载对应的版本，执行其中的
                                start 脚本。</p>
                            <h6>其他说明</h6>
                            <ol>
                                <li>百度Token获取地址： <a href="https://ziyuan.baidu.com/linksubmit/index" target="_blank"
                                                    rel="nofollow noopener">https://ziyuan.baidu.com/linksubmit/index</a>
                                </li>
                                <li>必应Token获取地址：<a
                                            href="https://docs.microsoft.com/en-us/bingwebmaster/getting-access#using-api-key"
                                            target="_blank" rel="nofollow noopener">https://docs.microsoft.com/en-us/bingwebmaster/getting-access#using-api-key</a>
                                </li>
                                <li>谷歌JSON私钥获取：<a href="https://www.renfei.net/posts/1003342" target="_blank">https://www.renfei.net/posts/1003342</a>
                                </li>
                                <li>谷歌上报需要本地是翻墙状态，否则网络不通</li>
                                <li>各个平台的接口提交配额与本工具无关，是各个平台分配给你的；例如百度快速收录是百度站长工具给予的权限，与是否使用本工具无关</li>
                                <li>本工具不会收集上报用户的Token，本工具代码已开源，欢迎监督，如遇仿制程序上报Token请联系 i@renfei.net</li>
                            </ol>
                            <h6>下载列表</h6>
                            <ul>
                                <li>Github发布与下载：<a href="https://github.com/renfei/Indexing/releases" target="_blank"
                                                   rel="nofollow noopener">https://github.com/renfei/Indexing/releases</a>
                                </li>
                                <li>Gitee发布与下载：<a href="https://gitee.com/rnf/Indexing/releases" target="_blank"
                                                  rel="nofollow noopener">https://gitee.com/rnf/Indexing/releases</a>
                                </li>
                            </ul>
                            <h6>开源地址</h6>
                            <ul>
                                <li>Github：<a href="https://github.com/renfei/Indexing" target="_blank"
                                              rel="nofollow noopener">https://github.com/renfei/Indexing</a></li>
                                <li>Gitee：<a href="https://gitee.com/rnf/Indexing" target="_blank"
                                             rel="nofollow noopener">https://gitee.com/rnf/Indexing</a></li>
                                <li>Gitlab：<a href="https://gitlab.com/renfei/Indexing" target="_blank"
                                              rel="nofollow noopener">https://gitlab.com/renfei/Indexing</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <@comments commentList account!"null" "KITBOX" kitBoxId></@comments>
            </div>
        </div>
    </div>
    <@footer pageView>
    </@footer>
    </body>
    </html>
</@compress>