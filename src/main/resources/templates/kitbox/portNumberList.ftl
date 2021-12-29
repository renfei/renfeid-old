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
                        <h5 class="card-title">计算机 TCP/UDP 端口号注册列表大全</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            计算机之间依照互联网传输层TCP/IP协议的协议通信，不同的协议都对应不同的端口。并且，利用数据报文的UDP也不一定和TCP采用相同的端口号码。</h6>
                        <div class="row mb-3">
                            <div class="col-12">
                                <div class="card" style="max-width: 240px;">
                                    <div class="card-body">
                                        <h6 class="card-title">目录导航</h6>
                                        <p class="card-text"><a href="#port_1024">0到1023号端口</a></p>
                                        <p class="card-text"><a href="#port_49152">1024到49151号端口</a></p>
                                        <p class="card-text"><a href="#port_65535">49152到65535号端口</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12 mb-3">
                                <h6 id="port_1024">0到1023号端口</h6>
                                <table class="table table-sm table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th scope="col" width="15%">端口</th>
                                        <th scope="col" width="73%">描述</th>
                                        <th scope="col" width="12%">状态</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>0/TCP,UDP</td>
                                        <td>保留端口；不使用（若发送过程不准备接受回复消息，则可以作为源端口）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1/TCP,UDP</td>
                                        <td>TCPMUX（传输控制协议端口服务多路开关选择器）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5/TCP,UDP</td>
                                        <td>RJE（远程作业登录）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>7/TCP,UDP</td>
                                        <td>Echo（回显）协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>9/UDP</td>
                                        <td>DISCARD（丢弃）协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>9/TCP,UDP</td>
                                        <td>网络唤醒</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>11/TCP,UDP</td>
                                        <td>SYSTAT协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>13/TCP,UDP</td>
                                        <td>DAYTIME协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>15/TCP,UDP</td>
                                        <td>NETSTAT协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>17/TCP,UDP</td>
                                        <td>QOTD（Quote of the Day，每日引用）协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>18/TCP,UDP</td>
                                        <td>消息发送协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>19/TCP,UDP</td>
                                        <td>CHARGEN（字符发生器）协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>20/TCP,UDP</td>
                                        <td>文件传输协议 - 默认数据端口</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>21/TCP,UDP</td>
                                        <td>文件传输协议 - 控制端口</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>22/TCP,UDP</td>
                                        <td>SSH（Secure Shell） - 安全远程登录协议，用于安全文件传输（SCP、SFTP）及端口转发</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>23/TCP,UDP</td>
                                        <td>Telnet终端仿真协议 - 未加密文本通信</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>25/TCP,UDP</td>
                                        <td>SMTP（简单邮件传输协议） - 用于传递电子邮件</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>26/TCP,UDP</td>
                                        <td>RSFTP - 一个简单的类似FTP的协议</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>35/TCP,UDP</td>
                                        <td>任意的私有打印机服务端口</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>37/TCP,UDP</td>
                                        <td>TIME时间协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>39/TCP,UDP</td>
                                        <td>Resource Location Protocol（资源定位协议）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>41/TCP,UDP</td>
                                        <td>图形</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>42/TCP,UDP</td>
                                        <td>Host Name Server</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>42/TCP,UDP</td>
                                        <td>WINS（WINS主机名服务）</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>43/TCP</td>
                                        <td>WHOIS协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>49/TCP,UDP</td>
                                        <td>TACACS登录主机协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>53/TCP,UDP</td>
                                        <td>DNS（域名服务系统）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>56/TCP,UDP</td>
                                        <td>远程访问协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>57/TCP</td>
                                        <td>MTP，邮件传输协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>67/UDP</td>
                                        <td>BOOTP（BootStrap协议）服务；同时用于动态主机设置协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>68/UDP</td>
                                        <td>BOOTP客户端；同时用于动态主机设定协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>69/UDP</td>
                                        <td>小型文件传输协议（小型文件传输协议）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>70/TCP</td>
                                        <td>Gopher</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>79/TCP</td>
                                        <td>手指协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>80/TCP,UDP</td>
                                        <td>超文本传输协议（超文本传输协议）或快速UDP网络连接- 用于传输网页</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>81/TCP</td>
                                        <td>XB Browser - Tor</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>82/UDP</td>
                                        <td>XB Browser - 控制端口</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>88/TCP</td>
                                        <td>Kerberos - 认证代理</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>101/TCP</td>
                                        <td>主机名</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>102/TCP</td>
                                        <td>ISO-TSAP协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>107/TCP</td>
                                        <td>远程Telnet协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>109/TCP</td>
                                        <td>邮局协议（POP），第2版</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>110/TCP</td>
                                        <td>邮局协议，第3版 - 用于接收电子邮件</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>111/TCP,UDP</td>
                                        <td>Sun远程过程调用协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>113/TCP</td>
                                        <td>Ident - 旧的服务器身份识别系统，仍然被IRC服务器用来认证它的用户</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>115/TCP</td>
                                        <td>简单文件传输协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>117/TCP</td>
                                        <td>UNIX间复制协议（Unix to Unix Copy Protocol，UUCP）的路径确定服务</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>118/TCP,UDP</td>
                                        <td>SQL服务</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>119/TCP</td>
                                        <td>网络新闻传输协议 - 用来收取新闻组的消息</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>123/UDP</td>
                                        <td>NTP（Network Time Protocol） - 用于时间同步</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>135/TCP,UDP</td>
                                        <td>分布式运算环境（Distributed Computing Environment，DCE）终端解决方案及定位服务</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>135/TCP,UDP</td>
                                        <td>微软终端映射器（End Point Mapper，EPMAP）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>137/TCP,UDP</td>
                                        <td>NetBIOS NetBIOS 名称服务</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>138/TCP,UDP</td>
                                        <td>NetBIOS NetBIOS 数据报文服务</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>139/TCP,UDP</td>
                                        <td>NetBIOS NetBIOS 会话服务</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>143/TCP,UDP</td>
                                        <td>因特网信息访问协议（IMAP） - 用于检索电子邮件</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>152/TCP,UDP</td>
                                        <td>BFTP, 后台文件传输程序</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>153/TCP,UDP</td>
                                        <td>简单网关监控协议（Simple Gateway Monitoring Protocol，SGMP）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>156/TCP,UDP</td>
                                        <td>SQL服务</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>158/TCP,UDP</td>
                                        <td>DMSP, 分布式邮件服务协议</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>161/TCP,UDP</td>
                                        <td>简单网络管理协议（SNMP)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>162/TCP,UDP</td>
                                        <td>SNMP协议的TRAP操作</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>170/TCP</td>
                                        <td>打印服务</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>179/TCP</td>
                                        <td>边界网关协议 (BGP)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>194/TCP</td>
                                        <td>IRC（互联网中继聊天）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>201/TCP,UDP</td>
                                        <td>AppleTalk 路由维护</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>209/TCP,UDP</td>
                                        <td>Quick Mail 传输协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>213/TCP,UDP</td>
                                        <td>互联网分组交换协议（IPX）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>218/TCP,UDP</td>
                                        <td>MPP，信息发布协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>220/TCP,UDP</td>
                                        <td>因特网信息访问协议（IMAP），第3版</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>259/TCP,UDP</td>
                                        <td>ESRO, Efficient Short Remote Operations</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>264/TCP,UDP</td>
                                        <td>BGMP，边界网关多播协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>308/TCP</td>
                                        <td>Novastor 在线备份</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>311/TCP</td>
                                        <td>Apple 服务器管理员工具、工作组管理</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>318/TCP,UDP</td>
                                        <td>TSP，时间戳协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>323/TCP,UDP</td>
                                        <td>IMMP, Internet消息映射协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>383/TCP,UDP</td>
                                        <td>HP OpenView HTTPs 代理程序</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>366/TCP,UDP</td>
                                        <td>ODMR，按需邮件传递</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>369/TCP,UDP</td>
                                        <td>Rpc2 端口映射</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>371/TCP,UDP</td>
                                        <td>ClearCase 负载平衡</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>384/TCP,UDP</td>
                                        <td>一个远程网络服务器系统</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>387/TCP,UDP</td>
                                        <td>AURP，AppleTalk 升级用路由协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>389/TCP,UDP</td>
                                        <td>轻型目录访问协议 LDAP</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>401/TCP,UDP</td>
                                        <td>不间断电源（UPS）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>411/TCP</td>
                                        <td>Direct Connect Hub 端口</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>412/TCP</td>
                                        <td>Direct Connect 客户端—客户端 端口</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>427/TCP,UDP</td>
                                        <td>服务定位协议（SLP）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>443/TCP</td>
                                        <td>超文本传输安全协议或QUIC</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>444/TCP,UDP</td>
                                        <td>SNPP，简单网络分页协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>445/TCP</td>
                                        <td>Microsoft-DS (Active Directory、Windows 共享、震荡波蠕虫、Agobot、Zobotworm)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>445/UDP</td>
                                        <td>Microsoft-DS 服务器消息块（SMB）文件共享</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>464/TCP,UDP</td>
                                        <td>Kerberos 更改/设定密码</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>465/TCP</td>
                                        <td>Cisco 专用协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>465/TCP</td>
                                        <td>传输层安全性协议加密的简单邮件传输协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>475/TCP</td>
                                        <td>tcpnethaspsrv（Hasp 服务，TCP/IP 版本）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>497/TCP</td>
                                        <td>dantz 备份服务</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>500/TCP,UDP</td>
                                        <td>网络安全关系与密钥管理协议，IKE-Internet 密钥交换</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>502/TCP,UDP</td>
                                        <td>Modbus 协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>512/TCP</td>
                                        <td>exec，远程进程执行</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>512/UDP</td>
                                        <td>comsat 和 biff 命令：用于电子邮件系统</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>513/TCP</td>
                                        <td>login，登录命令</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>513/UDP</td>
                                        <td>Who命令，查看当前登录计算机的用户</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>514/TCP</td>
                                        <td>远程外壳 protocol - 用于在远程计算机上执行非交互式命令，并查看返回结果</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>514/UDP</td>
                                        <td>Syslog 协议 - 用于系统登录</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>515/TCP</td>
                                        <td>Line Printer Daemon protocol - 用于 LPD 打印机服务器</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>517/UDP</td>
                                        <td>Talk</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>518/UDP</td>
                                        <td>NTalk</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>520/TCP</td>
                                        <td>efs</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>520/UDP</td>
                                        <td>Routing - 路由信息协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>513/UDP</td>
                                        <td>路由器</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>524/TCP,UDP</td>
                                        <td>NetWare核心协议（NetWare 核心协议）用于一系列功能，例如访问NetWare主服务器资源、同步时间等</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>525/UDP</td>
                                        <td>Timed，时间服务</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>530/TCP,UDP</td>
                                        <td>远程过程调用</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>531/TCP,UDP</td>
                                        <td>AOL 即时通信软件, IRC</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>532/TCP</td>
                                        <td>netnews</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>533/UDP</td>
                                        <td>netwall，用于紧急广播</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>540/TCP</td>
                                        <td>UUCP（Unix-to-Unix 复制协议）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>542/TCP,UDP</td>
                                        <td>商业（Commerce Applications）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>543/TCP</td>
                                        <td>klogin，Kerberos登陆</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>544/TCP</td>
                                        <td>kshell，Kerberos 远程shell</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>546/TCP,UDP</td>
                                        <td>DHCPv6客户端</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>547/TCP,UDP</td>
                                        <td>DHCPv6服务器</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>548/TCP</td>
                                        <td>通过传输控制协议（TCP）的 Appletalk 文件编制协议（AFP(苹果归档协议))</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>550/UDP</td>
                                        <td>new-rwho，new-who</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>554/TCP,UDP</td>
                                        <td>即时流协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>556/TCP</td>
                                        <td>Brunhoff 的远程文件系统（RFS）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>560/UDP</td>
                                        <td>rmonitor, Remote Monitor</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>561/UDP</td>
                                        <td>monitor</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>563/TCP,UDP</td>
                                        <td>通过TLS的网络新闻传输协议（NNTPS）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>587/TCP</td>
                                        <td>邮件消息提交（简单邮件传输协议，RFC 2476）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>591/TCP</td>
                                        <td>FileMaker 6.0（及之后版本）网络共享（HTTP的替代，见80端口）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>593/TCP,UDP</td>
                                        <td>HTTP RPC Ep Map（RPC over HTTP, often used by Distributed COM services and
                                            Microsoft Exchange Server）
                                        </td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>604/TCP</td>
                                        <td>TUNNEL</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>631/TCP,UDP</td>
                                        <td>互联网打印协议</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>636/TCP,UDP</td>
                                        <td>LDAP over TLS（加密传输，也被称为LDAPS）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>639/TCP,UDP</td>
                                        <td>MSDP，组播源发现协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>646/TCP,UDP</td>
                                        <td>LDP，标签分发协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>647/TCP</td>
                                        <td>DHCP故障转移协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>648/TCP</td>
                                        <td>RRP（Registry Registrar Protocol），注册表注册协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>652/TCP</td>
                                        <td>DTCP（Dynamic Tunnel Configuration Protocol），动态主机设置协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>654/UDP</td>
                                        <td>AODV（Ad hoc On-Demand Distance Vector），无线自组网按需平面距离向量路由协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>665/TCP</td>
                                        <td>sun-dr, Remote Dynamic Reconfiguration</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>666/UDP</td>
                                        <td>毁灭战士，电脑平台上的一系列第一人称射击游戏。</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>674/TCP</td>
                                        <td>ACAP（Application Configuration Access Protocol），应用配置访问协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>691/TCP</td>
                                        <td>MS Exchange Routing</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>692/TCP</td>
                                        <td>Hyperwave-ISP</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>694/UDP</td>
                                        <td>用于带有高可用性的聚类的心跳服务</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>695/TCP</td>
                                        <td>IEEE-MMS-SSL</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>698/UDP</td>
                                        <td>OLSR（Optimized Link State Routing），优化链路状态路由协议</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>699/TCP</td>
                                        <td>Access Network</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>700/TCP</td>
                                        <td>EPP, 可扩展供应协议</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>701/TCP</td>
                                        <td>LMP,链路管理协议</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>702/TCP</td>
                                        <td>IRIS over BEEP</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>706/TCP</td>
                                        <td>SILC，Secure Internet Live Conferencing</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>711/TCP</td>
                                        <td>TDP，标签分发协议</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>712/TCP</td>
                                        <td>TBRPF，Topology Broadcast based on Reverse-Path Forwarding</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>720/TCP</td>
                                        <td>SMQP，Simple Message Queue Protocol</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>749/TCP,UDP</td>
                                        <td>kerberos-adm，Kerberos administration</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>750/UDP</td>
                                        <td>Kerberos version IV</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>782/TCP</td>
                                        <td>Conserver serial-console management server</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>829/TCP</td>
                                        <td>证书管理协议（CMP）[2]</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>860/TCP</td>
                                        <td>ISCSI，Internet小型计算机系统接口</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>873/TCP</td>
                                        <td>Rsync，文件同步协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>901/TCP</td>
                                        <td>Samba 网络管理工具（SWAT）</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>902</td>
                                        <td>VMware服务器控制台[3]</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>904</td>
                                        <td>VMware服务器替代（如果902端口已被占用）</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>911/TCP</td>
                                        <td>Network Console on Acid（NCA） - local tty redirection over OpenSSH</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>981/TCP</td>
                                        <td>Check Point Remote HTTPS management for firewall devices running embedded
                                            Checkpoint Firewall-1 software
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>989/TCP,UDP</td>
                                        <td>FTP Protocol (data) over TLS/SSL</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>990/TCP,UDP</td>
                                        <td>FTP Protocol (control) over TLS/SSL</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>991/TCP,UDP</td>
                                        <td>NAS (Netnews Admin System)</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>992/TCP,UDP</td>
                                        <td>基于TLS/SSL的Telnet协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>993/TCP</td>
                                        <td>基于 传输层安全性协议的因特网信息访问协议 (加密传输)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>995/TCP</td>
                                        <td>基于 传输层安全性协议的邮局协议 (加密传输)</td>
                                        <td>官方</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-12 mb-3 d-none d-sm-block">
                                <@adsense "9903187829" active></@adsense>
                            </div>
                            <div class="col-12 mb-3">
                                <h6 id="port_49152">1024到49151号端口</h6>
                                <table class="table table-sm table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th scope="col" width="15%">端口</th>
                                        <th scope="col" width="73%">描述</th>
                                        <th scope="col" width="12%">状态</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>1025/tcp</td>
                                        <td>NFS-or-IIS</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>1026/tcp</td>
                                        <td>通常用于微软Distributed COM服务器</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>1029/tcp</td>
                                        <td>通常用于微软Distributed COM服务器</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>1058/tcp</td>
                                        <td>nim IBM AIX</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1059/tcp</td>
                                        <td>nimreg</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1080/tcp</td>
                                        <td>SOCKS代理</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1099/tcp,udp</td>
                                        <td>Java远程方法调用 Registry</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1109/tcp</td>
                                        <td>Kerberos POP</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>1140/tcp</td>
                                        <td>AutoNOC</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1167/udp</td>
                                        <td>phone, conference calling</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>1176/tcp</td>
                                        <td>Perceptive Automation Indigo home control server</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1182/tcp,udp</td>
                                        <td>AcceleNet</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1194/udp</td>
                                        <td>OpenVPN</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1198/tcp,udp</td>
                                        <td>The cajo project Free dynamic transparent distributed computing in Java</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1200/udp</td>
                                        <td>Steam</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1214/tcp</td>
                                        <td>Kazaa</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1223/tcp,udp</td>
                                        <td>TGP: TrulyGlobal Protocol</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1241/tcp,udp</td>
                                        <td>Nessus Security Scanner</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1248/tcp</td>
                                        <td>NSClient/NSClient++/NC_Net (Nagios)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>1270/tcp,udp</td>
                                        <td>Microsoft Operations Manager 2005 agent (MOM 2005)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1311/tcp</td>
                                        <td>Dell Open Manage Https Port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>1313/tcp</td>
                                        <td>Xbiim (Canvii server) Port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>1337/tcp</td>
                                        <td>WASTE Encrypted File Sharing Program</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>1352/tcp</td>
                                        <td>IBM IBM Lotus Notes/Domino RPC</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1387/tcp,udp</td>
                                        <td>Computer Aided Design Software Inc LM (cadsi-lm )</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1414/tcp</td>
                                        <td>IBM MQSeries</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1431/tcp</td>
                                        <td>RGTP</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1433/tcp,udp</td>
                                        <td>Microsoft SQL 数据库系统</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1434/tcp,udp</td>
                                        <td>Microsoft SQL 活动监视器</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1494/tcp</td>
                                        <td>思杰系统 ICA Client</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1512/tcp,udp</td>
                                        <td>WINS</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>1521/tcp</td>
                                        <td>nCube License Manager</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1521/tcp</td>
                                        <td>Oracle数据库 default listener, in future releases official port 2483</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>1524/tcp,udp</td>
                                        <td>ingreslock, ingres</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1526/tcp</td>
                                        <td>Oracle数据库 common alternative for listener</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>1533/tcp</td>
                                        <td>IBM Lotus Sametime IM - Virtual Places Chat</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1547/tcp,udp</td>
                                        <td>Laplink</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1550</td>
                                        <td>Gadu-Gadu (Direct Client-to-Client)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>1581/udp</td>
                                        <td>MIL STD 2045-47001 VMF</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1589/udp</td>
                                        <td>Cisco VQP (VLAN Query Protocol) / VMPS</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>1627</td>
                                        <td>iSketch</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>1677/tcp</td>
                                        <td>Novell GroupWise clients in client/server access mode</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>1701/udp</td>
                                        <td>第二层隧道协议, Layer 2 Tunnelling protocol</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>1716/tcp</td>
                                        <td>美国陆军系列 MMORPG Default Game Port</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1723/tcp,udp</td>
                                        <td>Microsoft 点对点隧道协议 VPN</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1725/udp</td>
                                        <td>Valve Steam Client</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>1755/tcp,udp</td>
                                        <td>MMS (协议) (MMS, ms-streaming)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1761/tcp,udp</td>
                                        <td>cft-0</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1761/tcp</td>
                                        <td>Novell Zenworks Remote Control utility</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>1762-1768/tcp,udp</td>
                                        <td>cft-1 to cft-7</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1812/udp</td>
                                        <td>radius, 远端用户拨入验证服务 authentication protocol</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>1813/udp</td>
                                        <td>radacct, 远端用户拨入验证服务 accounting protocol</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>1863/tcp</td>
                                        <td>Windows Live Messenger, MSN</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1900/udp</td>
                                        <td>Microsoft 简单服务发现协议 Enables discovery of UPnP devices</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1935/tcp</td>
                                        <td>实时消息协议 (RTMP) raw protocol</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1970/tcp,udp</td>
                                        <td>Danware Data NetOp Remote Control</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1971/tcp,udp</td>
                                        <td>Danware Data NetOp School</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1972/tcp,udp</td>
                                        <td>InterSystems Caché</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1975-77/udp</td>
                                        <td>Cisco TCO (Documentation)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1984/tcp</td>
                                        <td>Big Brother - network monitoring tool</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1985/udp</td>
                                        <td>热备份路由器协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>1994/TCP</td>
                                        <td>STUN-SDLC protocol for tunneling</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>1998/tcp</td>
                                        <td>Cisco X.25 service (XOT)</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>2000/tcp,udp</td>
                                        <td>Cisco SCCP (Skinny)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2002/tcp</td>
                                        <td>Cisco Secure Access Control Server (ACS) for Windows</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2030</td>
                                        <td>甲骨文公司 Services for Microsoft Transaction Server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2031/tcp,udp</td>
                                        <td>mobrien-chat - Mike O'Brien November 2004
                                        </td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2049/udp</td>
                                        <td>nfs, NFS Server</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2049/udp</td>
                                        <td>shilp</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2053/tcp</td>
                                        <td>knetd, Kerberos de-multiplexor</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>2056/udp</td>
                                        <td>文明IV multiplayer</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2073/tcp,udp</td>
                                        <td>DataReel Database</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2074/tcp,udp</td>
                                        <td>Vertel VMF SA (i.e. App.. SpeakFreely)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2082/tcp</td>
                                        <td>Infowave Mobility Server</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2082/tcp</td>
                                        <td>CPanel, default port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2083/tcp</td>
                                        <td>Secure Radius Service (radsec)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2083/tcp</td>
                                        <td>CPanel default SSL port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2086/tcp</td>
                                        <td>GNUnet</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2086/tcp</td>
                                        <td>CPanel default port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2087/tcp</td>
                                        <td>CPanel default SSL port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2095/tcp</td>
                                        <td>CPanel default webmail port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2096/tcp</td>
                                        <td>CPanel default SSL webmail port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2161/tcp</td>
                                        <td>问号-APC Agent</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2181/tcp,udp</td>
                                        <td>EForward-document transport system</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2200/tcp</td>
                                        <td>Tuxanci game server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2219/tcp,udp</td>
                                        <td>NetIQ NCAP Protocol</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2220/tcp,udp</td>
                                        <td>NetIQ End2End</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2222/tcp</td>
                                        <td>DirectAdmin's default port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2222/udp</td>
                                        <td>Microsoft Office OS X antipiracy network monitor [1]</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2301/tcp</td>
                                        <td>HP System Management Redirect to port 2381</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2302/udp</td>
                                        <td>武装突袭 multiplayer (default for game)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2302/udp</td>
                                        <td>最后一战：战斗进化 multiplayer</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2303/udp</td>
                                        <td>武装突袭 multiplayer (default for server reporting) (游戏内定端口 +1)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2305/udp</td>
                                        <td>武装突袭 multiplayer (default for VoN) (游戏内定端口 +3)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2369/tcp</td>
                                        <td>Default port for BMC软件公司 CONTROL-M/Server - Configuration Agent port number
                                            - though often changed during installation
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2370/tcp</td>
                                        <td>Default port for BMC软件公司 CONTROL-M/Server - Port utilized to allow the
                                            CONTROL-M/Enterprise Manager to connect to the CONTROL-M/Server - though
                                            often changed during installation
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2381/tcp</td>
                                        <td>HP Insight Manager default port for webserver</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2404/tcp</td>
                                        <td>IEC 60870-5-104</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2427/udp</td>
                                        <td>Cisco MGCP</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2447/tcp,udp</td>
                                        <td>ovwdb - OpenView Network Node Manager (NNM) daemon</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2483/tcp,udp</td>
                                        <td>Oracle数据库 listening port for unsecure client connections to the listener,
                                            replaces port 1521
                                        </td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2484/tcp,udp</td>
                                        <td>Oracle数据库 listening port for SSL client connections to the listener</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2546/tcp,udp</td>
                                        <td>Vytal Vault - Data Protection Services</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2593/tcp,udp</td>
                                        <td>RunUO - 网络创世纪 server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2598/tcp</td>
                                        <td>new ICA - when Session Reliability is enabled, TCP port 2598 replaces port
                                            1494
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2612/tcp,udp</td>
                                        <td>QPasa from MQSoftware</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2710/tcp</td>
                                        <td>XBT Bittorrent Tracker</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2710/udp</td>
                                        <td>XBT Bittorrent Tracker experimental UDP tracker extension</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2710/tcp</td>
                                        <td>Knuddels.de</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2735/tcp,udp</td>
                                        <td>NetIQ Monitor Console</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2809/tcp</td>
                                        <td>corbaloc:iiop URL, per the CORBA 3.0.3 specification.<br>Also used by IBM
                                            IBM WebSphere Application Server Node Agent
                                        </td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2809/udp</td>
                                        <td>corbaloc:iiop URL, per the CORBA 3.0.3 specification.</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2944/udp</td>
                                        <td>Megaco Text H.248</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2945/udp</td>
                                        <td>Megaco Binary (ASN.1) H.248</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>2948/tcp,udp</td>
                                        <td>无线应用协议-push 彩信 (MMS)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2949/tcp,udp</td>
                                        <td>无线应用协议-pushsecure 彩信 (MMS)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>2967/tcp</td>
                                        <td>Symantec AntiVirus Corporate Edition</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>3000/tcp</td>
                                        <td>Miralix License server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>3000/udp</td>
                                        <td>Distributed Interactive Simulation (DIS), modifiable default port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>3000/tcp</td>
                                        <td>Puma Web Server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>3001/tcp</td>
                                        <td>Miralix Phone Monitor</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>3002/tcp</td>
                                        <td>Miralix CSTA</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>3003/tcp</td>
                                        <td>Miralix GreenBox API</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>3004/tcp</td>
                                        <td>Miralix InfoLink</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>3006/tcp</td>
                                        <td>Miralix SMS Client Connector</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>3007/tcp</td>
                                        <td>Miralix OM Server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>3025/tcp</td>
                                        <td>netpd.org</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>3050/tcp,udp</td>
                                        <td>gds_db (Interbase/Firebird)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3074/tcp,udp</td>
                                        <td>Xbox Live</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3128/tcp</td>
                                        <td>超文本传输协议 used by Web缓存s and the default port for the Squid (软件)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3260/tcp</td>
                                        <td>ISCSI target</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3268/tcp</td>
                                        <td>msft-gc, Microsoft Global Catalog (轻型目录访问协议 service which contains data from
                                            Active Directory forests)
                                        </td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3269/tcp</td>
                                        <td>msft-gc-ssl, Microsoft Global Catalog over SSL (similar to port 3268,
                                            轻型目录访问协议 over 传输层安全性协议 version)
                                        </td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3300/tcp</td>
                                        <td>TripleA game server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>3305/tcp,udp</td>
                                        <td>ODETTE-FTP</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3306/tcp,udp</td>
                                        <td>MySQL数据库系统</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3333/tcp</td>
                                        <td>Network Caller ID server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>3386/tcp,udp</td>
                                        <td>GTP' 3GPP GSM/通用移动通讯系统 CDR logging protocol</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3389/tcp</td>
                                        <td>远程桌面协议（RDP）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3396/tcp</td>
                                        <td>Novell NDPS Printer Agent</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3689/tcp</td>
                                        <td>DAAP Digital Audio Access Protocol used by 苹果公司 ITunes</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3690/tcp</td>
                                        <td>Subversion version control system</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3702/tcp,udp</td>
                                        <td>Web Services Dynamic Discovery (WS-Discovery), used by various components of
                                            Windows Vista
                                        </td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3724/tcp</td>
                                        <td>魔兽世界 Online gaming MMORPG</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3784/tcp,udp</td>
                                        <td>Ventrilo VoIP program used by Ventrilo</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3785/udp</td>
                                        <td>Ventrilo VoIP program used by Ventrilo</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3868 tcp,udp</td>
                                        <td>Diameter base protocol</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3872/tcp</td>
                                        <td>Oracle Management Remote Agent</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>3899/tcp</td>
                                        <td>Remote Administrator</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>3900/tcp</td>
                                        <td>Unidata UDT OS udt_os</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>3945/tcp</td>
                                        <td>Emcads server service port, a Giritech product used by G/On</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>4000/tcp</td>
                                        <td>暗黑破坏神II game<br>NoMachine Network Server (nxd)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>4007/tcp</td>
                                        <td>PrintBuzzer printer monitoring socket server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>4089/tcp,udp</td>
                                        <td>OpenCORE Remote Control Service</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>4093/tcp,udp</td>
                                        <td>PxPlus Client server interface ProvideX</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>4096/udp</td>
                                        <td>Bridge-Relay Element ASCOM</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>4100</td>
                                        <td>WatchGuard Authentication Applet - default port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>4111/tcp,udp</td>
                                        <td>Xgrid</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>4111/tcp</td>
                                        <td>SharePoint - 默认管理端口</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>4226/tcp,udp</td>
                                        <td>Aleph One (computer game)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>4224/tcp</td>
                                        <td>思科系统 CDP Cisco discovery Protocol</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>4569/udp</td>
                                        <td>Inter-Asterisk eXchange</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>4662/tcp</td>
                                        <td>OrbitNet Message Service</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>4662/tcp</td>
                                        <td>通常用于EMule</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>4664/tcp</td>
                                        <td>Google桌面搜索</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>4672/udp</td>
                                        <td>EMule - 常用端口</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>4894/tcp</td>
                                        <td>LysKOM Protocol A</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>4899/tcp</td>
                                        <td>Radmin 远程控制工具</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5000/tcp</td>
                                        <td>commplex-main</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5000/tcp</td>
                                        <td>UPnP - Windows network device interoperability</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5000/tcp,udp</td>
                                        <td>VTun - 虚拟专用网 软件</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5001/tcp,udp</td>
                                        <td>Iperf (Tool for measuring TCP and UDP bandwidth performance)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5001/tcp</td>
                                        <td>Slingbox及Slingplayer</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5003/tcp</td>
                                        <td>FileMaker Filemaker Pro</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5004/udp</td>
                                        <td>实时传输协议实时传输协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5005/udp</td>
                                        <td>实时传输协议实时传输协议</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5031/tcp,udp</td>
                                        <td>AVM CAPI-over-TCP (综合业务数字网 over 以太网 tunneling)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5050/tcp</td>
                                        <td>Yahoo! Messenger</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5051/tcp</td>
                                        <td>ita-agent Symantec Intruder Alert</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5060/tcp,udp</td>
                                        <td>会话发起协议 (SIP)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5061/tcp</td>
                                        <td>会话发起协议 (SIP) over 传输层安全性协议 (TLS)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5093/udp</td>
                                        <td>SPSS License Administrator (SPSS)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5104/tcp</td>
                                        <td>IBM NetCOOL / IMPACT HTTP Service</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5106/tcp</td>
                                        <td>A-Talk Common connection</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5107/tcp</td>
                                        <td>A-Talk 远程服务器连接</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5110/tcp</td>
                                        <td>ProRat Server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5121/tcp</td>
                                        <td>无冬之夜</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5176/tcp</td>
                                        <td>ConsoleWorks default UI interface</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5190/tcp</td>
                                        <td>ICQ and AIM (应用程序)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5222/tcp</td>
                                        <td>XMPP/Jabber - client connection</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5223/tcp</td>
                                        <td>XMPP/Jabber - default port for SSL Client Connection</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5269/tcp</td>
                                        <td>XMPP/Jabber - server connection</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5351/tcp,udp</td>
                                        <td>NAT端口映射协议，允许客户端在网络地址转换网关上配置传入映射</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5353/udp</td>
                                        <td>mDNS - 多播DNS</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>5402/tcp,udp</td>
                                        <td>StarBurst AutoCast MFTP</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5405/tcp,udp</td>
                                        <td>NetSupport</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5421/tcp,udp</td>
                                        <td>Net Support 2</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5432/tcp</td>
                                        <td>PostgreSQL数据库管理系统</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5445/udp</td>
                                        <td>思科系统 Vidéo VT Advantage</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5495/tcp</td>
                                        <td>Applix TM1 Admin server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5498/tcp</td>
                                        <td>Hotline tracker server connection</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5499/udp</td>
                                        <td>Hotline tracker server discovery</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5500/tcp</td>
                                        <td>VNC remote desktop protocol - for incoming listening viewer, Hotline control
                                            connection
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5501/tcp</td>
                                        <td>Hotline file transfer connection</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5517/tcp</td>
                                        <td>Setiqueue Proxy server client for SETI@home project</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5555/tcp</td>
                                        <td>Freeciv multiplay port for versions up to 2.0, 惠普 Data Protector, 会话通告协议
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5556/tcp</td>
                                        <td>Freeciv multiplay port</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5631/tcp</td>
                                        <td>赛门铁克 pcAnywhere</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5632/udp</td>
                                        <td>赛门铁克 pcAnywhere</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5666/tcp</td>
                                        <td>NRPE (Nagios)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5667/tcp</td>
                                        <td>NSCA (Nagios)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5800/tcp</td>
                                        <td>VNC remote desktop protocol - for use over 超文本传输协议</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>5814/tcp,udp</td>
                                        <td>惠普 Support Automation (HP OpenView Self-Healing Services)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>5900/tcp</td>
                                        <td>VNC remote desktop protocol (used by ARD)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6000/tcp</td>
                                        <td>X窗口系统 - used between an X client and server over the network</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6001/udp</td>
                                        <td>X窗口系统 - used between an X client and server over the network</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6005/tcp</td>
                                        <td>Default port for BMC软件公司 CONTROL-M/Server - Socket Port number used for
                                            communication between CONTROL-M processes - though often changed during
                                            installation
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6050/tcp</td>
                                        <td>Brightstor Arcserve Backup</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6051/tcp</td>
                                        <td>Brightstor Arcserve Backup</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6100/tcp</td>
                                        <td>Vizrt System</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6110/tcp,udp</td>
                                        <td>softcm HP SoftBench CM</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6111/tcp,udp</td>
                                        <td>spc HP SoftBench Sub-Process Control</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6112/tcp</td>
                                        <td>dtspcd - a network daemon that accepts requests from clients to execute
                                            commands and launch applications remotely
                                        </td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6112/tcp</td>
                                        <td>暴雪娱乐's 暴雪战网 gaming service, ArenaNet gaming service</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6129/tcp</td>
                                        <td>Dameware Remote Control</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6257/udp</td>
                                        <td>WinMX （参见6699端口）</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6346/tcp,udp</td>
                                        <td>gnutella-svc (FrostWire, LimeWire, Bearshare, etc.)</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6347/tcp,udp</td>
                                        <td>gnutella-rtr</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6379/tcp</td>
                                        <td>Redis - Redis</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6444/tcp,udp</td>
                                        <td>Oracle Grid Engine - Qmaster Service</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6445/tcp,udp</td>
                                        <td>Oracle Grid Engine - Execution Service</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6502/tcp,udp</td>
                                        <td>Danware Data NetOp Remote Control</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6522/tcp</td>
                                        <td>Gobby (and other libobby-based software)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6543/udp</td>
                                        <td>Jetnet - default port that the Paradigm Research & Development Jetnet
                                            protocol communicates on
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6566/tcp</td>
                                        <td>SANE (Scanner Access Now Easy) - SANE network scanner daemon</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6600/tcp</td>
                                        <td>Music Playing Daemon (MPD)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6619/tcp,udp</td>
                                        <td>ODETTE-FTP over TLS/SSL</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6665-6669/tcp</td>
                                        <td>IRC</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6679/tcp</td>
                                        <td>IRC SSL （安全互联网中继聊天） - 通常使用的端口</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6697/tcp</td>
                                        <td>IRC SSL （安全互联网中继聊天） - 通常使用的端口</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6699/tcp</td>
                                        <td>WinMX （参见6257端口）</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6881-6999/tcp,udp</td>
                                        <td>BitTorrent 通常使用的端口</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>6891-6900/tcp,udp</td>
                                        <td>Windows Live Messenger （文件传输）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6901/tcp,udp</td>
                                        <td>Windows Live Messenger （语音）</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6969/tcp</td>
                                        <td>acmsoda</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>6969/tcp</td>
                                        <td>BitTorrent tracker port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>7000/tcp</td>
                                        <td>Default port for Azureus's built in 超文本传输安全协议 BitTorrent tracker</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>7001/tcp</td>
                                        <td>Default port for BEA WebLogic Server's 超文本传输协议 server - though often changed
                                            during installation
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>7002/tcp</td>
                                        <td>Default port for BEA WebLogic Server's 超文本传输安全协议 server - though often
                                            changed during installation
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>7005/tcp,udp</td>
                                        <td>Default port for BMC软件公司 CONTROL-M/Server and CONTROL-M/Agent's - Agent to
                                            Server port though often changed during installation
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>7006/tcp,udp</td>
                                        <td>Default port for BMC软件公司 CONTROL-M/Server and CONTROL-M/Agent's - Server to
                                            Agent port though often changed during installation
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>7010/tcp</td>
                                        <td>Default port for Cisco AON AMC (AON Management Console) [4]</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>7025/tcp</td>
                                        <td>Zimbra - lmtp [mailbox] - local mail delivery</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>7047/tcp</td>
                                        <td>Zimbra - conversion server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>7171/tcp</td>
                                        <td>Tibia</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>7306/tcp</td>
                                        <td>Zimbra - mysql [mailbox]</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>7307/tcp</td>
                                        <td>Zimbra - mysql [logger] - logger</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>7312/udp</td>
                                        <td>Sibelius License Server port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>7670/tcp</td>
                                        <td>BrettSpielWelt BSW Boardgame Portal</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>7680/tcp</td>
                                        <td>适用于Windows 10更新的传递最优化</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>7777/tcp</td>
                                        <td>Default port used by Windows backdoor program tini.exe</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8000/tcp</td>
                                        <td>iRDMI - often mistakenly used instead of port 8080 (The Internet Assigned
                                            Numbers Authority (iana.org) officially lists this port for iRDMI protocol)
                                        </td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>8000/tcp</td>
                                        <td>Common port used for internet radio streams such as those using SHOUTcast
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8002/tcp</td>
                                        <td>Cisco Systems Unified Call Manager Intercluster Port</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>8008/tcp</td>
                                        <td>超文本传输协议 替代端口</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>8008/tcp</td>
                                        <td>IBM HTTP Server 默认管理端口</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8009/tcp</td>
                                        <td>阿帕契族 JServ 协议 v13 (ajp13) 例如：Apache mod_jk Tomcat会使用。</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8010/tcp</td>
                                        <td>XMPP/Jabber 文件传输</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8074/tcp</td>
                                        <td>Gadu-Gadu</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8080/tcp</td>
                                        <td>超文本传输协议 替代端口 （http_alt） - commonly used for 代理服务器 and caching server, or for
                                            running a web server as a non-Root user
                                        </td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>8080/tcp</td>
                                        <td>Apache Tomcat</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8086/tcp</td>
                                        <td>HELM Web Host Automation Windows Control Panel</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8086/tcp</td>
                                        <td>Kaspersky AV Control Center TCP Port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8087/tcp</td>
                                        <td>Hosting Accelerator Control Panel</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8087/udp</td>
                                        <td>Kaspersky AV Control Center UDP Port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8087/tcp</td>
                                        <td>英迈 控制面板</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8090/tcp</td>
                                        <td>Another 超文本传输协议 Alternate (http_alt_alt) - used as an alternative to port
                                            8080
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8118/tcp</td>
                                        <td>Privoxy web proxy - advertisements-filtering web proxy</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>8123/tcp</td>
                                        <td>Dynmap[5]默认网页端口号(Minecraft在线地图)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8200/tcp</td>
                                        <td>GoToMyPC</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8220/tcp</td>
                                        <td>Bloomberg</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8222</td>
                                        <td>VMware服务器管理用户界面(不安全网络界面)[6]。参见 8333端口</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8291/tcp</td>
                                        <td>Winbox - Default port on a MikroTik RouterOS for a Windows application used
                                            to administer MikroTik RouterOS
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8294/tcp</td>
                                        <td>Bloomberg</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8333</td>
                                        <td>VMware 服务器管理用户界面（安全网络界面）[7]。参见8222端口</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8400</td>
                                        <td>Commvault Unified Data Management</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>8443/tcp</td>
                                        <td>英迈 Control Panel</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8500/tcp</td>
                                        <td>Adobe ColdFusion Macromedia/Adobe ColdFusion default Webserver port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8501/udp</td>
                                        <td>毁灭公爵3D - Default Online Port</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>8767/udp</td>
                                        <td>TeamSpeak - Default UDP Port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8880</td>
                                        <td>IBM WebSphere Application Server 简单对象访问协议 Connector port</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>8881/tcp</td>
                                        <td>Atlasz Informatics Research Ltd Secure Application Server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8882/tcp</td>
                                        <td>Atlasz Informatics Research Ltd Secure Application Server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8888/tcp,udp</td>
                                        <td>NewsEDGE server</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>8888/tcp</td>
                                        <td>Sun Answerbook 网页服务器 server (deprecated by docs.sun.com)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8888/tcp</td>
                                        <td>GNUmp3d HTTP music streaming and web interface port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>8888/tcp</td>
                                        <td>英雄大作战 Network Game Server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>9000/tcp</td>
                                        <td>Buffalo LinkSystem web access</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>9000/tcp</td>
                                        <td>DBGp</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>9000/udp</td>
                                        <td>UDPCast</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>9000</td>
                                        <td>PHP - php-fpm</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>9001</td>
                                        <td>cisco-xremote router configuration</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>9001</td>
                                        <td>Tor network default port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>9001/tcp</td>
                                        <td>DBGp Proxy</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>9002</td>
                                        <td>Default ElasticSearch port</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>9009/tcp,udp</td>
                                        <td>Pichat Server - Peer to peer chat software</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>9043/tcp</td>
                                        <td>IBM WebSphere Application Server Administration Console secure port</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>9060/tcp</td>
                                        <td>IBM WebSphere Application Server Administration Console</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>9100/tcp</td>
                                        <td>Jetdirect HP Print Services</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>9110/udp</td>
                                        <td>SSMP Message protocol</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>9101</td>
                                        <td>Bacula Director</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>9102</td>
                                        <td>Bacula File Daemon</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>9103</td>
                                        <td>Bacula Storage Daemon</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>9119/TCP,UDP</td>
                                        <td>Mxit Instant Messenger</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>9535/tcp</td>
                                        <td>man, Remote Man Server</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>9535</td>
                                        <td>mngsuite - Management Suite Remote Control</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>9800/tcp,udp</td>
                                        <td>基于Web的分布式编写和版本控制 Source Port</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>9800</td>
                                        <td>WebCT e-learning portal</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>9999</td>
                                        <td>Hydranode - edonkey2000 telnet control port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>9999</td>
                                        <td>Urchin Web Analytics</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>10000</td>
                                        <td>Webmin - web based Linux admin tool</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>10000</td>
                                        <td>BackupExec</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>10008</td>
                                        <td>Octopus Multiplexer - CROMP protocol primary port, hoople.org</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>10017</td>
                                        <td>AIX,NeXT, HPUX - rexd daemon control port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>10024/tcp</td>
                                        <td>Zimbra - smtp [mta] - to amavis from postfix</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>10025/tcp</td>
                                        <td>Ximbra - smtp [mta] - back to postfix from amavis</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>10050/tcp</td>
                                        <td>Zabbix-Agent</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>10051/tcp</td>
                                        <td>Zabbix-Server</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>10113/tcp,udp</td>
                                        <td>NetIQ Endpoint</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>10114/tcp,udp</td>
                                        <td>NetIQ Qcheck</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>10115/tcp,udp</td>
                                        <td>NetIQ Endpoint</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>10116/tcp,udp</td>
                                        <td>NetIQ VoIP Assessor</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>10480</td>
                                        <td>SWAT 4 Dedicated Server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>11211</td>
                                        <td>Memcached</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>11235</td>
                                        <td>Savage:Battle for Newerth Server Hosting</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>11294</td>
                                        <td>Blood Quest Online Server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>11371</td>
                                        <td>PGP HTTP Keyserver</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>11576</td>
                                        <td>IPStor Server management communication</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>12035/udp</td>
                                        <td>《第二人生》, used for server UDP in-bound[8]</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>12345</td>
                                        <td>NetBus - remote administration tool (often 特洛伊木马 (电脑)). Also used by
                                            NetBuster. Little Fighter 2 (TCP).
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>12975/tcp</td>
                                        <td>LogMeIn Hamachi (VPN tunnel software;also port 32976)</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>13000-13050/udp</td>
                                        <td>《第二人生》, used for server UDP in-bound[9]</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>13720/tcp</td>
                                        <td>赛门铁克 NetBackup - bprd (formerly VERITAS)</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>13721/tcp</td>
                                        <td>赛门铁克 NetBackup - bpdbm (formerly VERITAS)</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>13724/tcp</td>
                                        <td>赛门铁克 Network Utility - vnet (formerly VERITAS)</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>13782/tcp</td>
                                        <td>赛门铁克 NetBackup - bpcd (formerly VERITAS)</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>13783/tcp</td>
                                        <td>赛门铁克 VOPIED protocol (formerly VERITAS)</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>14567/udp</td>
                                        <td>战地风云1942 and mods</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>15000/tcp</td>
                                        <td>Bounce (网络)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>15000/tcp</td>
                                        <td>韦诺之战</td>
                                    </tr>
                                    <tr>
                                        <td>15567/udp</td>
                                        <td>战地风云：越南 and mods</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>15345/udp</td>
                                        <td>XPilot</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>16000/tcp</td>
                                        <td>Bounce (网络)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>16080/tcp</td>
                                        <td>MacOS Server performance cache for 超文本传输协议[10]</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>16384/udp</td>
                                        <td>Iron Mountain Digital - online backup</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>16567/udp</td>
                                        <td>战地2 and mods</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>17788/udp</td>
                                        <td>PPS网络电视</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>19132/udp</td>
                                        <td>Minecraft基岩版默认服务器端口号</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>19226/tcp</td>
                                        <td>熊猫 (消歧义) AdminSecure Communication Agent</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>19638/tcp</td>
                                        <td>Ensim Control Panel</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>19813/tcp</td>
                                        <td>4D database Client Server Communication</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>20000</td>
                                        <td>Usermin - 基于网络的用户工具</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>20720/tcp</td>
                                        <td>Symantec i3 Web GUI server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>22347/tcp,udp</td>
                                        <td>WibuKey - default port for WibuKey Network Server of WIBU-SYSTEMS AG</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>22350/tcp,udp</td>
                                        <td>CodeMeter - default port for CodeMeter Server of WIBU-SYSTEMS AG</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>24554/tcp,udp</td>
                                        <td>binkp - FidoNet mail transfers over TCP/IP协议族</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>24800</td>
                                        <td>Synergy：keyboard/mouse sharing software</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>24842</td>
                                        <td>StepMania：Online：《劲爆热舞》模拟器</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>25565/tcp</td>
                                        <td>Minecraft默认服务器端口号</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>25999/tcp</td>
                                        <td>Xfire</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>26000/tcp,udp</td>
                                        <td>Id Software's 《Quake》 server,</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>26000/tcp</td>
                                        <td>CCP Games's 星战前夜 Online gaming MMORPG,</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>27000/udp</td>
                                        <td>(through 27006) Id Software's 《雷神世界》 master server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>27010/udp</td>
                                        <td>Half-Life及其修改版，如《反恐精英系列》</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>27015/udp</td>
                                        <td>Half-Life及其修改版，如《反恐精英系列》</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>27017/tcp</td>
                                        <td>MongoDB 数据库</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>27374</td>
                                        <td>Sub7's default port. Most 脚本小子s do not change the default port.</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>27500/udp</td>
                                        <td>(through 27900) Id Software's 《雷神世界》</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>27888/udp</td>
                                        <td>Kaillera server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>27900</td>
                                        <td>(through 27901) 任天堂 任天堂Wi-Fi连接</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>27901/udp</td>
                                        <td>(through 27910) Id Software's 《雷神之锤II》 master server</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>27960/udp</td>
                                        <td>(through 27969) 动视's 《Enemy Territory》 and Id Software's 《雷神之锤III竞技场》 and
                                            《Quake III》 and some ioquake3 derived games
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>28910</td>
                                        <td>任天堂 任天堂Wi-Fi连接</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>28960</td>
                                        <td>决胜时刻2 Common Call of Duty 2 port - (PC Version)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>29900</td>
                                        <td>(through 29901) 任天堂 任天堂Wi-Fi连接</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>29920</td>
                                        <td>任天堂 任天堂Wi-Fi连接</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>30000</td>
                                        <td>Pokemon Netbattle</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>30564/tcp</td>
                                        <td>Multiplicity：keyboard/mouse/clipboard sharing software</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>31337/tcp</td>
                                        <td>Back Orifice - remote administration tool（often 特洛伊木马 (电脑)）</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>31337/tcp</td>
                                        <td>xc0r3 - xc0r3 security antivir port</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>31415</td>
                                        <td>ThoughtSignal - Server Communication Service（often Informational）</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>31456-31458/tcp</td>
                                        <td>TetriNET ports (in order: IRC, game, and spectating)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>32245/tcp</td>
                                        <td>MMTSG-mutualed over MMT (encrypted transmission)</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>33434</td>
                                        <td>Traceroute</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>37777/tcp</td>
                                        <td>Digital Video Recorder hardware</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>36963</td>
                                        <td>Counter Strike 2D multiplayer port (2D clone of popular CounterStrike
                                            computer game)
                                        </td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>40000</td>
                                        <td>SafetyNET p</td>
                                        <td>官方</td>
                                    </tr>
                                    <tr>
                                        <td>43594-43595/tcp</td>
                                        <td>RuneScape</td>
                                        <td>非官方</td>
                                    </tr>
                                    <tr>
                                        <td>47808</td>
                                        <td>BACnet Building Automation and Control Networks</td>
                                        <td>官方</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-12">
                                <h6 id="port_65535">49152到65535号端口</h6>
                                <p>根据定义，该段端口属于“动态端口”范围，没有端口可以被正式地注册占用。</p>
                            </div>
                        </div>
                        <div class="d-none d-sm-block">
                            <@adsense "9903187829" active></@adsense>
                        </div>
                    </div>
                </div>
                <@comments commentList account!"null" "KITBOX" kitBoxId></@comments>
            </div>
        </div>
    </div>
    <@footer pageView></@footer>
    </body>
    </html>
</@compress>