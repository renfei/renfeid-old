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
                        <h5 class="card-title" style="margin-top: 10px;">CliEnv - Client Environment - 客户端环境信息探针获取工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            此工具利用浏览器API获取客户端运行环境信息，包括操作系统与浏览器版本等信息。
                        </h6>
                        <img src="https://cdn.renfei.net/upload/2022/b79f4df3c065497787a4c9421b4ffae9.png" style="width: 100%;"
                             alt="CliEnv - Client Environment - 客户端环境信息探针获取工具">
                        <div style="font-size: 14px;">
                            <p>&nbsp;</p>
                            <p style="text-indent: 2em;">
                                在某项目中，互联网用户的环境千奇百怪，经常有人上报无法打开页面、无法登陆等问题，但反馈给开发者的技术信息非常少，很难定位问题，最终只能依靠远程协助来解决问题。
                            </p>
                            <p style="text-indent: 2em;">
                                C端用户并不懂技术，无法协助完成技术诊断，你问他操作系统版本、用的什么浏览器可能都回答不上来，所以需要一个工具可视化的显示客户端的所处环境。
                            </p>
                            <p style="text-indent: 2em;">
                                客户端环境探针工具来了，只需让用户访问 <a href="http://CliEnv.com" target="_blank">http://CliEnv.com</a>，
                                打不开的话可以直接访问境内站：<a href="https://clienv.renfei.net" target="_blank">https://clienv.renfei.net</a>


                            </p>
                            <p style="text-indent: 2em;">
                                名字也好记：Client Environment，客户端环境，各取 3 个字母组合成 CliEnv
                            </p>
                            <p style="text-indent: 2em;">
                                这样只需要客户把截图发过来，那么操作系统版本、浏览器版本等信息都有了，方便创造模拟环境进行进一步诊断。
                            </p>
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