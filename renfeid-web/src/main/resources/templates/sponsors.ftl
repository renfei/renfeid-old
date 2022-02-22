<#include "layout/layout.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head pageView>
    </@head>
    <body>
    <canvas id="canvas" style="position: absolute;z-index: 999999;display: none;"></canvas>
    <@header pageView>

    </@header>
    <div class="sponsors-banner">
        <div class="container">
            <div class="row">
                <div class="col-md-4 sponsors-banner-left"></div>
                <div class="col-md-8 text-center align-self-center">
                    <h1>赞助</h1>
                    <h3 style="color: #6c757d!important;">Sponsors</h3>
                    <p>赞助您所依赖的项目成为您的供应链</p>
                    <p>成为赞助商将获得额外的付费支持和最新的软件更新</p>
                </div>
            </div>
        </div>
    </div>
    <div class="container" style="padding-top: 50px;">
        <h4>赞助方式</h4>
        <div class="row mb-5">
            <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6 mb-2">
                <div class="card" style="min-height: 320px;">
                    <img src="https://cdn.renfei.net/thunder/img/sponsors-wechat.jpg" class="card-img-top" alt="微信支付">
                    <div class="card-body" style="background-color: #22ab39;color: #FFFFFF;">
                        <h5 class="card-title">微信支付</h5>
                        <h6 class="card-subtitle mb-2 text-muted" style="color: #c5c5c5!important;">WeChat Pay</h6>
                        <p class="card-text">使用微信扫描二维码</p>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6 mb-2">
                <div class="card" style="min-height: 320px;">
                    <img src="https://cdn.renfei.net/thunder/img/sponsors-alipay.jpg" class="card-img-top" alt="支付宝支付">
                    <div class="card-body" style="background-color: #1678ff;color: #FFFFFF;">
                        <h5 class="card-title">支付宝</h5>
                        <h6 class="card-subtitle mb-2 text-muted" style="color: #c5c5c5!important;">Alipay</h6>
                        <p class="card-text">使用支付宝扫描二维码</p>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6 mb-2">
                <div class="card" style="min-height: 320px;">
                    <img src="https://cdn.renfei.net/thunder/img/sponsors-unionpay.jpg" class="card-img-top" alt="银联支付">
                    <div class="card-body" style="background-color: #DE3232;color: #FFFFFF;">
                        <h5 class="card-title">银联支付</h5>
                        <h6 class="card-subtitle mb-2 text-muted" style="color: #c5c5c5!important;">UnionPay</h6>
                        <p class="card-text">使用手机网银APP扫描二维码</p>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6 mb-2">
                <div class="card" style="min-height: 320px;">
                    <img src="https://cdn.renfei.net/thunder/img/sponsors-bitcoin.jpg" class="card-img-top" alt="比特币支付">
                    <div class="card-body" style="background-color: #F7931A;color: #FFFFFF;">
                        <h5 class="card-title">比特币</h5>
                        <h6 class="card-subtitle mb-2 text-muted" style="color: #c5c5c5!important;">Bitcoin</h6>
                        <p class="card-text">
                            <a href="bitcoin:3K82R127gUSmuE8182ChvutvH75sx7XnGt" target="_blank">3K82R127gUSmuE8182ChvutvH75sx7XnGt</a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6 mb-2">
                <div class="card" style="min-height: 320px;">
                    <img src="https://cdn.renfei.net/thunder/img/sponsors-server.jpg" class="card-img-top" alt="服务器">
                    <div class="card-body">
                        <h5 class="card-title">服务器赞助</h5>
                        <h6 class="card-subtitle mb-2 text-muted" style="color: #c5c5c5!important;">Server</h6>
                        <p class="card-text">
                            如果您有多余的服务器资源也可以赞助给所需的项目
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <h4>赞助商列表</h4>
        <div class="row mb-5">
            <div class="col-12">
                暂无。
            </div>
        </div>
    </div>
    <@footer pageView>
        <script>
            var canvas1, ctx, W, H;
            if (screen.width >= 988) var mp = 150; else mp = 75;
            var deactivationTimerHandler, reactivationTimerHandler, animationHandler, particles = [], angle = 0,
                tiltAngle = 0, confettiActive = !0, animationComplete = !0, particleColors = {
                    colorOptions: ["DodgerBlue", "OliveDrab", "Gold", "pink", "SlateBlue", "lightblue", "Violet", "PaleGreen", "SteelBlue", "SandyBrown", "Chocolate", "Crimson"],
                    colorIndex: 0,
                    colorIncrementer: 0,
                    colorThreshold: 10,
                    getColor: function () {
                        return this.colorIncrementer >= 10 && (this.colorIncrementer = 0, this.colorIndex++, this.colorIndex >= this.colorOptions.length && (this.colorIndex = 0)), this.colorIncrementer++, this.colorOptions[this.colorIndex]
                    }
                };

            function confettiParticle(t) {
                this.x = Math.random() * W, this.y = Math.random() * H - H, this.r = RandomFromTo(10, 30), this.d = Math.random() * mp + 10, this.color = t, this.tilt = Math.floor(10 * Math.random()) - 10, this.tiltAngleIncremental = .07 * Math.random() + .05, this.tiltAngle = 0, this.draw = function () {
                    return ctx.beginPath(), ctx.lineWidth = this.r / 2, ctx.strokeStyle = this.color, ctx.moveTo(this.x + this.tilt + this.r / 4, this.y), ctx.lineTo(this.x + this.tilt, this.y + this.tilt + this.r / 4), ctx.stroke()
                }
            }

            function InitializeButton() {
                $("#stopButton").click(DeactivateConfetti), $("#startButton").click(RestartConfetti)
            }

            function SetGlobals() {
                canvas1 = document.getElementById("canvas"), ctx = canvas1.getContext("2d"), W = window.innerWidth, H = document.body.scrollHeight, canvas1.width = W, canvas1.height = H
            }

            function InitializeConfetti() {
                particles = [], animationComplete = !1;
                for (var t = 0; t < mp; t++) {
                    var i = particleColors.getColor();
                    particles.push(new confettiParticle(i))
                }
                StartConfetti()
            }

            function Draw() {
                ctx.clearRect(0, 0, W, H);
                for (var t, i = [], n = 0; n < mp; n++) t = n, i.push(particles[t].draw());
                return Update(), i
            }

            function RandomFromTo(t, i) {
                return Math.floor(Math.random() * (i - t + 1) + t)
            }

            function Update() {
                var t, i = 0;
                angle += .01, tiltAngle += .1;
                for (var n = 0; n < mp; n++) {
                    if (t = particles[n], animationComplete) return;
                    !confettiActive && t.y < -15 ? t.y = H + 100 : (stepParticle(t, n), t.y <= H && i++, CheckForReposition(t, n))
                }
                0 === i && StopConfetti()
            }

            function CheckForReposition(t, i) {
                (t.x > W + 20 || t.x < -20 || t.y > H) && confettiActive && (i % 5 > 0 || i % 2 == 0 ? repositionParticle(t, Math.random() * W, -10, Math.floor(10 * Math.random()) - 10) : Math.sin(angle) > 0 ? repositionParticle(t, -5, Math.random() * H, Math.floor(10 * Math.random()) - 10) : repositionParticle(t, W + 5, Math.random() * H, Math.floor(10 * Math.random()) - 10))
            }

            function stepParticle(t, i) {
                t.tiltAngle += t.tiltAngleIncremental, t.y += (Math.cos(angle + t.d) + 3 + t.r / 2) / 2, t.x += Math.sin(angle), t.tilt = 15 * Math.sin(t.tiltAngle - i / 3)
            }

            function repositionParticle(t, i, n, e) {
                t.x = i, t.y = n, t.tilt = e
            }

            function StartConfetti() {
                W = window.innerWidth, H = document.body.scrollHeight, canvas1.width = W, canvas1.height = H, function t() {
                    return animationComplete ? null : (animationHandler = requestAnimFrame(t), Draw())
                }()
            }

            function ClearTimers() {
                clearTimeout(reactivationTimerHandler), clearTimeout(animationHandler)
            }

            function DeactivateConfetti() {
                confettiActive = !1, ClearTimers()
            }

            function StopConfetti() {
                animationComplete = !0, null != ctx && ctx.clearRect(0, 0, W, H)
            }

            function RestartConfetti() {
                ClearTimers(), StopConfetti(), reactivationTimerHandler = setTimeout(function () {
                    confettiActive = !0, animationComplete = !1, InitializeConfetti()
                }, 100)
            }

            $(document).ready(function () {
                SetGlobals(), InitializeButton(), InitializeConfetti(), $(window).resize(function () {
                    W = window.innerWidth, H = document.body.scrollHeight, canvas1.width = W, canvas1.height = H
                })
            }), window.requestAnimFrame = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || function (t) {
                return window.setTimeout(t, 1e3 / 60)
            };

            function hideCanvas() {
                StopConfetti();
                $("#canvas").hide();
            }

            $(function () {
                $("#canvas").show();
                setTimeout("hideCanvas()", 3000);
            });
        </script>
    </@footer>
    </body>
    </html>
</@compress>