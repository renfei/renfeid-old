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
                        <h5 class="card-title">随机密码生成工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            可根据自身需要选择生成密码所包含的字符以及密码长度，随机密码一键生成，简单易用，生成安全、随机的密码以保证网络账号的安全。</h6>
                        <div style="margin: auto;max-width: 600px;">
                            <form>
                                <div class="input-group mb-3">
                                    <input class="form-control form-control-lg" type="text" id="result"/>
                                    <div class="input-group-prepend">
                                        <div class="btn btn-primary align-middle copyPaswd"
                                             data-clipboard-target="#result">复制
                                        </div>
                                    </div>
                                </div>
                                <div class="btn-light btn-block pl-3 pr-3 mb-3">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">密码长度</label>
                                        <div class="input-group range__slider">
                                            <input type="range" class="form-control" min="4" max="64" value="8"
                                                   id="slider">
                                            <div class="input-group-prepend" style="width: 45px;">
                                                <input class="form-control" type="text" id="sliderText" value="8"
                                                       onkeyup="this.value=this.value.replace(/\D/g,'')"/>
                                            </div>
                                        </div>
                                        <small id="emailHelp" class="form-text text-muted">密码长度支持4～64位</small>
                                    </div>
                                </div>
                                <div class="btn-light btn-block p-3 mb-3">
                                    <div class="custom-control custom-switch">
                                        <input type="checkbox" checked class="custom-control-input" id="uppercase">
                                        <label class="custom-control-label" for="uppercase">包含大写字母(ABCDE..)</label>
                                    </div>
                                </div>
                                <div class="btn-light btn-block p-3 mb-3">
                                    <div class="custom-control custom-switch">
                                        <input type="checkbox" checked class="custom-control-input" id="lowercase">
                                        <label class="custom-control-label" for="lowercase">包含小写(abcde..)</label>
                                    </div>
                                </div>
                                <div class="btn-light btn-block p-3 mb-3">
                                    <div class="custom-control custom-switch">
                                        <input type="checkbox" checked class="custom-control-input" id="number">
                                        <label class="custom-control-label" for="number">包含数字(12345..)</label>
                                    </div>
                                </div>
                                <div class="btn-light btn-block p-3 mb-3">
                                    <div class="custom-control custom-switch">
                                        <input type="checkbox" class="custom-control-input" id="symbol">
                                        <label class="custom-control-label" for="symbol">包含符号(~!@#$..)</label>
                                    </div>
                                </div>
                                <button type="button" class="btn btn-primary btn-block" id="generate"
                                        onclick="onGeneratePassword()">生成密码
                                </button>
                            </form>
                            <div class="row mt-3">
                                <div class="col-md-12">
                                    <blockquote style="font-size: 14px;">
                                        <p>建议：采用八位以上的密码，包含：特殊符号、大小写字母和数字，越复杂越好。</p>
                                        <p>密码暴力破解时间表（使用一台普通双核家用计算机，暴力破解密码）：</p>
                                        <ul>
                                            <li>简单六位纯数字密码，瞬间破解；八位纯数字密码，约三百分钟；十位纯数字密码，约150天可破解。</li>
                                            <li>普通大小写字母，约半小时可破解；八位约60天可破解</li>
                                            <li>数字＋大小写字母，约2小时可破解；八位约250天可破解</li>
                                            <li>数字＋大小写字母＋标点，六位约24小时可破解；八位约20年方可破解。</li>
                                        </ul>
                                    </blockquote>
                                </div>
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
            const resultEl = document.getElementById("result");
            const lengthEl = document.getElementById("slider");
            const uppercaseEl = document.getElementById("uppercase");
            const lowercaseEl = document.getElementById("lowercase");
            const numberEl = document.getElementById("number");
            const symbolEl = document.getElementById("symbol");
            const generateBtn = document.getElementById("generate");
            $(document).ready(function () {
                $("#slider").bind("input", function () {
                    if ($("#sliderText").val() !== $("#slider").val()) {
                        $("#sliderText").val($("#slider").val());
                    }
                });
                $("#sliderText").bind("input", function () {
                    if ($("#sliderText").val() === "") {
                        $("#sliderText").val(4);
                    }
                    if ($("#slider").val() !== $("#sliderText").val()) {
                        let int = parseInt($("#sliderText").val());
                        if (int < 4) {
                            $("#sliderText").val(4);
                        } else if (int > 64) {
                            $("#sliderText").val(64);
                        }
                        $("#slider").val($("#sliderText").val());
                    }
                });
                let clipboard = new ClipboardJS('.copyPaswd', {
                    text: function () {
                        return $("#result").val();
                    }
                });
                clipboard.on('success', function (e) {
                    msg("复制成功！", "success");
                });
                clipboard.on('error', function (e) {
                    msg("当前浏览器不支持此功能，请手动复制。", "error");
                });
            });
            const randomFunc = {
                lower: getRandomLower,
                upper: getRandomUpper,
                number: getRandomNumber,
                symbol: getRandomSymbol,
            };

            function getRandomLower() {
                return String.fromCharCode(Math.floor(Math.random() * 26) + 97);
            }

            function getRandomUpper() {
                return String.fromCharCode(Math.floor(Math.random() * 26) + 65);
            }

            function getRandomNumber() {
                return String.fromCharCode(Math.floor(Math.random() * 10) + 48);
            }

            function getRandomSymbol() {
                const symbols = '~!@#$%^&*()_+{}":?><;.,';
                return symbols[Math.floor(Math.random() * symbols.length)];
            }

            function onGeneratePassword() {
                const length = +lengthEl.value;
                const hasLower = lowercaseEl.checked;
                const hasUpper = uppercaseEl.checked;
                const hasNumber = numberEl.checked;
                const hasSymbol = symbolEl.checked;
                $("#result").val(generatePassword(length, hasLower, hasUpper, hasNumber, hasSymbol));
            }

            function generatePassword(length, lower, upper, number, symbol) {
                let generatedPassword = "";
                const typesCount = lower + upper + number + symbol;
                const typesArr = [{lower}, {upper}, {number}, {symbol}].filter(item => Object.values(item)[0]);
                if (typesCount === 0) {
                    return "";
                }
                for (let i = 0; i < length; i++) {
                    typesArr.forEach(type => {
                        const funcName = Object.keys(type)[0];
                        generatedPassword += randomFunc[funcName]();
                    });
                }
                return generatedPassword.slice(0, length);
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>