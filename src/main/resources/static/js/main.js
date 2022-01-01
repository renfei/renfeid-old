function msg(content, type) {
    switch (type) {
        case "main":
            bs4pop.notice(content, {type: 'primary'});
            break;
        case "success":
            bs4pop.notice(content, {type: 'success'});
            break;
        case "warning":
            bs4pop.notice(content, {type: 'warning'});
            break;
        case "error":
            bs4pop.notice(content, {type: 'danger'});
            break;
        default:
            bs4pop.notice(content, {type: 'secondary'});
            break;
    }
}

function replyTo(name, id) {
    $("#replyName").val(name);
    $("#replyId").val(id);
    $("#replyTo").show();
}

function cancelReply() {
    $("#replyId").val("");
    $("#replyName").val("");
    $("#comment-content").val("");
    $("#replyTo").hide();
}

console.log("\n %c RENFEI.NET %c 你知道的太多了 %c \n", "color: #fff; background: #3274ff; padding:5px 0; border: 1px solid #3274ff;", "color: #3274ff; background: #fff; padding:5px 0; border: 1px solid #3274ff;", "");
console.log("\n %c 开发交流QQ群 %c 130832168 %c \n", "color: #fff; background: #eb0; padding:5px 0; border: 1px solid #eb0;", "color: #eb0; background: #fff; padding:5px 0; border: 1px solid #eb0;", "");

function comment(typeid, id) {
    let nickname = $("#comment-nickname").val();
    let email = $("#comment-email").val();
    let link = $("#comment-link").val();
    let content = $("#comment-content").val();
    let replyId = $("#replyId").val();
    let data = {};
    if (nickname === "") {
        msg("昵称不能为空", "error");
        return;
    } else {
        data.author = nickname;
    }
    if (email === "") {
        msg("电子邮件地址不能为空", "error");
        return;
    } else {
        data.email = email;
    }
    if (content === "") {
        msg("评论内容不能为空", "error");
        return;
    } else {
        data.content = content;
    }
    if (link !== "") {
        data.link = link;
    }
    if (replyId !== "") {
        data.reply = replyId;
    }
    $.ajax({
        url: '/_/api/foreground/comments/' + typeid + '/' + id,
        type: 'POST',
        async: true,
        data: data,
        timeout: 5000,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            if (data.code === 200) {
                cancelReply();
                msg("提交成功！\n本站有缓存策略，约2小时后可以看到您提交的评论。", "success")
            } else {
                msg("提交失败！" + data.message, "error");
            }
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
        },
        error: function (xhr, textStatus) {
            msg("Error - 错误:" + xhr.responseText, "error");
        }
    });
}

function getJiSuDownloadLink() {
    var downloadfile_jisu_btn = $("#downloadfile_jisu_btn").html();
    var downloadfile_jisu_code = $("#downloadfile_jisu_code").val();
    if (downloadfile_jisu_code === "") {
        msg("请扫描二维码关注微信公众号获得授权码", "error");
        $("#downloadfile_jisu_btn").html(downloadfile_jisu_btn);
        return;
    }
    $("#downloadfile_jisu_btn").html("正在获取请稍后");
    $.ajax({
        url: '/other/JiSuDownloadLink',
        type: 'GET',
        async: true,
        data: {
            code: downloadfile_jisu_code
        },
        timeout: 5000,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            if (data.code === 200) {
                $('#downloadfile_jisu_link').attr('href', data.data.jisulink);
                $('#downloadfile_jisu_link').html(data.data.jisulink);
                $('#downloadfile_jisu_exp').html(data.data.expires);
            } else {
                $("#downloadfile_jisu_link").html("极速下载链接获取失败！" + data.message);
                msg(data.message, "error");
            }
            $("#downloadfile_jisu_btn").html(downloadfile_jisu_btn);
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
        },
        error: function (xhr, textStatus) {
            $("#downloadfile_jisu_btn").html(downloadfile_jisu_btn);
            $("#downloadfile_jisu_link").html("极速下载链接获取失败，网络错误！");
            msg(textStatus, xhr.responseText, "error");
        }
    });
}

function setClipboardText(event, link) {
    if ((window.getSelection().getRangeAt(0) + "").length >= 64) {
        event.preventDefault();
        let node = document.createElement('div');
        node.appendChild(window.getSelection().getRangeAt(0).cloneContents());
        let htmlData = '<div>'
            + node.innerHTML
            + '<br /><br />------------<br />'
            + '商业用途请联系作者获得授权。<br />'
            + '版权声明：本文为原创文章，遵循 <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/" target="_blank" rel="nofollow noopener">CC BY-NC-SA 4.0</a> 版权协议，转载请附上原文出处链接及本声明。<br />'
            + '作者：任霏<br />'
            + '来源：<a href="'
            + link
            + '">'
            + link
            + '</a><br />'
            + '</div>';
        let textData = window.getSelection().getRangeAt(0)
            + '\n\n------------\n'
            + '商业用途请联系作者获得授权\n'
            + '版权声明：本文为原创文章，遵循 CC BY-NC-SA 4.0 版权协议，转载请附上原文出处链接及本声明。\n'
            + '作者：任霏\n'
            + '来源：'
            + link
            + '\n';
        if (event.clipboardData) {
            event.clipboardData.setData("text/html", htmlData);
            event.clipboardData.setData("text/plain", textData);
        } else if (window.clipboardData) {
            return window.clipboardData.setData("text", textData);
        }
    }
}

function setStore(name, content) {
    if (window.localStorage) {
        window.localStorage.setItem(name, content);
    } else {
        msg("此浏览器不支持「LocalStorage」技术,请更换浏览器尝试", "error");
    }
}

// 获取 localstorage 中的值,判断用户是否登录
function getStore(name) {
    if (window.localStorage) {
        return window.localStorage.getItem(name);
    } else {
        msg("此浏览器不支持「LocalStorage」技术,请更换浏览器尝试", "error");
    }
}

function removeStore(name) {
    if (window.localStorage) {
        window.localStorage.removeItem(name);
    } else {
        msg("此浏览器不支持「LocalStorage」技术,请更换浏览器尝试", "error");
    }
}

// 生成秘钥对
function generateKey() {
    let keySize = 1024;
    let crypt = new JSEncrypt({default_key_size: keySize});
    crypt.getKey();
    setStore('ClientPublicKey', crypt.getPublicKey().replace("-----BEGIN PUBLIC KEY-----\n", "").replace("\n-----END PUBLIC KEY-----", ""));
    setStore('ClientPrivateKey', crypt.getPrivateKey().replace("-----BEGIN PRIVATE KEY-----\n", "").replace("\n-----END PRIVATE KEY-----", ""));
}

// 获取客户端公钥
function getClientPublicKey() {
    let key = getStore("ClientPublicKey");
    if (key === null || key === undefined) {
        generateKey();
    }
    return getStore("ClientPublicKey");
}

// 获取AES秘钥
function getAESKey() {
    let key = getStore("aesKey");
    let keys = {};
    // 如果Store里没有那么就去申请
    if (key === null || key === undefined) {
        let ok = false;
        let reportPublicKeyVO = {};
        // 获取服务器端公钥
        $.ajax({
            url: '/auth/secretKey',
            type: 'GET',
            async: false,
            data: {},
            timeout: 20000,
            dataType: 'json',
            success: function (data, textStatus) {
                if (data.code !== 200) {
                    msg("申请加密公钥服务发生错误", "error");
                } else {
                    let serverPublicKey = data.data;
                    let serverPublicKeyId = data.message;
                    // 使用服务器端公钥加密客户端公钥，注意服务器端公钥是2048，客户端公钥是1024
                    let jse = new JSEncrypt();
                    jse.setPublicKey(serverPublicKey);
                    let clientEncryptPublicKey = jse.encrypt(getClientPublicKey());
                    reportPublicKeyVO.secretKeyId = serverPublicKeyId;
                    reportPublicKeyVO.publicKey = clientEncryptPublicKey;
                    ok = true;
                }
            },
            beforeSend: function (xhr) {
                xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
            },
            error: function (xhr, textStatus, errorThrown) {
                msg("请求发生了异常，请联系管理员", "error");
                console.log('错误', xhr.responseText);
                console.log(xhr);
                console.log(textStatus);
            }
        });
        if (ok) {
            // 将加密好的客户端公钥上报给服务器
            $.ajax({
                url: '/auth/secretKey',
                type: 'POST',
                async: false,
                data: JSON.stringify(reportPublicKeyVO),
                contentType: 'application/json;charset=UTF-8',
                timeout: 20000,
                dataType: 'json',
                success: function (serverRes, textStatus) {
                    if (serverRes.code !== 200) {
                        msg("上报公钥服务发生错误", "error");
                    } else {
                        // 用客户端私钥解密服务器返回的AES秘钥
                        let jse = new JSEncrypt();
                        jse.setPrivateKey('-----BEGIN PRIVATE KEY-----\n' +
                            getStore("ClientPrivateKey") +
                            '\n-----END PRIVATE KEY-----\n');
                        let aesKey = jse.decrypt(serverRes.data.aeskey);
                        let keys = {};
                        keys.aesKey = aesKey;
                        keys.aesKeyId = serverRes.data.keyid;
                        // 存储下来 AES 秘钥，现在这个秘钥全世界只有你知和我知，不要公开，不要在网络上明文传输
                        setStore("aesKey", aesKey);
                        setStore("aesKeyId", serverRes.data.keyid);
                        return keys;
                    }
                },
                beforeSend: function (xhr) {
                    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                },
                error: function (xhr, textStatus, errorThrown) {
                    msg("请求发生了异常，请联系管理员", "error");
                    console.log('错误', xhr.responseText);
                    console.log(xhr);
                    console.log(textStatus);
                }
            });
        }
    } else {
        keys.aesKey = getStore("aesKey");
        keys.aesKeyId = getStore("aesKeyId");
        return keys;
    }
}

//加密
function aesencrypt(word) {
    getAESKey();
    let aesKey = getAESKey().aesKey;
    let key = CryptoJS.enc.Utf8.parse(aesKey);
    let iv = CryptoJS.enc.Utf8.parse(aesKey);
    let srcs = CryptoJS.enc.Utf8.parse(word);
    let encrypted = CryptoJS.AES.encrypt(srcs, key, {
        iv: iv,
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    return CryptoJS.enc.Base64.stringify(encrypted.ciphertext);
}

function signOut() {
    let callback = window.location.href;
    window.location.href = "/auth/signOut?callback=" + callback;
}

function signInFun() {
    let callback = window.location.href;
    window.location.href = "/auth/signIn?callback=" + callback;
}

function loadJS(url, callback) {
    var script = document.createElement('script'),
        fn = callback || function () {
        };
    script.type = 'text/javascript';

    //IE
    if (script.readyState) {
        script.onreadystatechange = function () {
            if (script.readyState === 'loaded' || script.readyState === 'complete') {
                script.onreadystatechange = null;
                fn();
            }
        };
    } else {
        //其他浏览器
        script.onload = function () {
            fn();
        };
    }
    script.src = url;
    document.getElementsByTagName('head')[0].appendChild(script);

}

function modal(title, content, remote) {
    $("#g-modal #g-modal-label").html(title);
    $("#g-modal .modal-body").html(content);
    $("#g-modal").modal({
        backdrop: false,
        keyboard: false,
        show: true,
        remote: remote
    });
}

function checkAdp() {
    if (document.getElementsByTagName("ins").length > 0 &&
        window.innerWidth > 575) {
        if (typeof (window.google_jobrunner) != 'object') {
            modal("Adblock 广告屏蔽插件被启用提示", "您可能安装并启动了「Adblock」广告屏蔽插件。<br>原创内容与网站运营不易，我们恳请您关闭插件或将我们的网址加入白名单。<br>我们不会强制您关闭插件，您可以继续浏览我们的网页，但每次加载都会弹出这个提示。", "");
        }
    }
}

$(function () {
    if ($(".copyUrlBtn").length) {
        let clipboard = new ClipboardJS('.copyUrlBtn', {
            text: function () {
                return document.location.href;
            }
        });
        clipboard.on('success', function (e) {
            msg("复制链接成功！", "success");
        });
        clipboard.on('error', function (e) {
            msg("当前浏览器不支持此功能，请手动复制。", "error");
        });
    }
    var OriginTitile = document.title, titleTime;
    document.addEventListener('visibilitychange', function () {
        if (document.hidden) {
            document.title = '快回来！我想你了！he~tui~';
            clearTimeout(titleTime);
        } else {
            document.title = '哈!终于把你喊回来了!';
            titleTime = setTimeout(function () {
                document.title = OriginTitile;
            }, 1000);
        }
    });
});