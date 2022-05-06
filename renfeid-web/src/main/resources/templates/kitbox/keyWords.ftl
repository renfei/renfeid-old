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
                        <h5 class="card-title">在线文章关键词提取工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">文章关键词提取工具,基于IKAnalyzer和词频实现</h6>
                        <div class="row">
                            <div class="col-12">
                                <form>
                                    <div class="form-group">
                                        <label>待提取内容</label>
                                        <textarea id="data" name="data" class="form-control" rows="8">
                                            连日来，贵阳至南宁高铁建设一线不断传来好消息：最长隧道九万大山一号隧道顺利贯通；最大新建车站南宁北站站房框架柱开始浇筑作业……

全长481公里的贵南高铁是我国西南山区第一条设计时速350公里的高速铁路，建成通车后从贵阳到南宁最快只需2小时。

2014年以来，贵州相继建成至广州、昆明、长沙、重庆的高速铁路，在黔贵大地形成以贵阳为中心的“十”字形高铁网，为快速融入国内大循环、促进产业升级打下良好基础。

作为我国唯一没有平原支撑的省份，贵州不沿边、不沿海，绵延的大山限制了人的视野，也困住了该省融入全国市场、追赶同步小康的脚步。

党的十八大以来，贵州全力突破交通瓶颈制约，2015年在西部地区率先实现县县通高速公路，目前全省公路总里程突破8000公里，位居全国第五。同时，高铁通车里程达1527公里；形成“一枢十一支”机场布局，贵阳龙洞堡机场3号航站楼正式启用，进入全国大型繁忙机场行列。

借助日益完善的交通网络，贵州稳步推进内陆开放型经济试验区建设，陆海内外联动、东西双向开放的新格局正加速形成。去年，全省实现进出口总额654.16亿元，同比增长19.7%。

4月21日，满载着60个标准集装箱、约1650吨水泥熟料的货运列车从贵阳都拉营国际陆海通物流港发车，驶向广西钦州港，标志着西部陆海新通道“贵阳都拉营—广西钦州港”专列常态化开行。目前，“湛江港—都拉国际物流港”多式联运班列、“瓮马铁路—湛江港”测试班列已经成功开车，实现了黔粤双向联通。

此外，贵州积极发挥贵安新区、贵阳高新区等对外开放平台的作用，不断完善“外部企业+贵州资源”“外部市场+贵州产品”“外部总部+贵州基地”等模式，推动更多企业在贵州布局、更多贵州产品“走出去”。 （经济日报记者 王新伟 吴秉泽）
                                        </textarea>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <div class="form-group row">
                                    <label for="countryLong"
                                           class="col-sm-3 col-form-label col-form-label-sm">词最小字数</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control form-control-sm" name="quantity"
                                               value="2" id="quantity">
                                    </div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="form-group row">
                                    <label for="countryLong"
                                           class="col-sm-3 col-form-label col-form-label-sm">提取词数</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control form-control-sm" name="number"
                                               value="10" id="number">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <button type="button" class="btn btn-primary" onclick="analyze()"
                                        style="width: 100%;margin: 10px 0;">提取关键词
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <form>
                                    <div class="form-group">
                                        <label>提取结果</label>
                                        <textarea id="result" name="ftl" class="form-control"
                                                  style="min-height: 200px;"></textarea>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div>
                            <blockquote style="font-size: 14px;">
                                <p>IK Analyzer是一个开源的，基于java语言开发的轻量级的中文分词工具包。从2006年12月推出1.0版开始，
                                    IKAnalyzer已经推出了3个大版本。最初，它是以开源项目Luence为应用主体的，结合词典分词和文法分析算法的中文分词组件。新版本的IK Analyzer
                                    3.0则发展为面向Java的公用分词组件，独立于Lucene项目，同时提供了对Lucene的默认优化实现。
                                </p>
                                <p>
                                    此处提供的分词服务仅用于简单的轻量的分词场景，如果需要更复杂的分词逻辑或者自定义词库，请自行集成开发IK Analyzer。
                                    工具代码仓库：<a href="https://github.com/renfei/ik-analyzer" target="_blank">https://github.com/renfei/ik-analyzer</a>
                                </p>
                                <p>
                                    关键词提取能力已经作为开放接口共享，此工具的接口为：https://www.renfei.net/api/keyWords 。详情请见：
                                    <a href="https://www.renfei.net/swagger-ui.html">
                                        开放接口
                                    </a>
                                </p>
                            </blockquote>
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
    <@footer pageView>
        <script>
            function analyze() {
                let data = {};
                if ($("#data").val() === "") {
                    $("#result").val("错误：待提取内容是空！");
                    $("#json").html("错误：待提取内容是空！");
                    msg("错误：待提取内容是空！", "error");
                    return;
                }
                data.word = $("#data").val();
                data.quantity = $("#quantity").val();
                data.number = $("#number").val();
                $.ajax({
                    url: '/api/keyWords',
                    type: 'POST',
                    async: true,
                    timeout: 60000,
                    dataType: 'json',
                    data: JSON.stringify(data),
                    contentType: "application/json",
                    success: function (data, textStatus, jqXHR) {
                        if (data.code === 200) {
                            let resultData = data.data;
                            let result = "";
                            for (let i = 0; i < resultData.length; i++) {
                                result += resultData[i].word + ",";
                            }
                            result = result.substr(0, result.length - 1);
                            $("#result").val(result);
                        } else {
                            $("#result").val("Error.\n" + data.message);
                            msg(data.message, "error");
                        }
                    },
                    error: function (xhr, textStatus) {
                        $("#result").val("Error.\n" + xhr.responseText);
                        msg(xhr.responseText, "error");
                    }
                });
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>