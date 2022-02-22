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
                        <h5 class="card-title">SHA-1在线加密工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">SHA-1 Online Encryption</h6>
                        <div style="margin: auto;max-width: 600px;">
                            <form>
                                <label class="sr-only" for="quantityNumber" style="line-height: 40px;">字符串:</label>
                                <div class="input-group mb-2 mr-sm-2">
                                    <textarea type="text" class="form-control" id="quantityNumber" rows="4"></textarea>
                                    <div class="input-group-prepend">
                                        <div class="btn btn-primary align-middle" onclick="generate()">SHA-1加密</div>
                                    </div>
                                </div>
                            </form>
                            <form>
                                <div class="form-group">
                                    <div class="col-md-offset-2 col-md-10">
                                        <label class="radio-inline">
                                            <input type="radio" name="MD5Options" id="MD5Options3" value="1" checked>
                                            小写字母
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="MD5Options" id="MD5Options4" value="2"> 大写字母
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="quantity" class="col-md-2 col-form-label"
                                           style="line-height: 40px;">SHA-1:</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" id="data" value="">
                                    </div>
                                </div>
                            </form>
                            <div>
                                <div class="col-sm-12">
                                    <blockquote style="font-size: 14px;">
                                        <p>SHA（Secure Hash Algorithm）
                                            中文翻译为“安全散列算法”，是美国国家安全局（NSA）设计，美国国家标准与技术研究院（NIST）发布的一系列密码散列函数。 正式名称为 SHA
                                            的家族第一个成员发布于1993年。然而现在的人们给它取了一个非正式的名称 SHA-0 以避免与它的后继者混淆。两年之后，第一个 SHA 的后继者
                                            SHA-1 发布了。 另外还有四种变体，曾经发布以提升输出的范围和变更一些细微设计: SHA-224, SHA-256, SHA-384 和
                                            SHA-512（这些有时候也被称做 SHA-2）。</p>
                                        <footer>SHA-1 散列函数加密算法输出的散列值为40位十六进制数字串，可用于验证信息的一致性，防止被篡改。本页面的 SHA-1
                                            在线加密工具可对字符串进行 SHA-1 加密，并可转换散列值中字母的大小写。
                                        </footer>
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
            function generate() {
                var str = $("#quantityNumber").val();
                if ($("#MD5Options3").is(":checked")) {
                    $("#data").val(hex_sha1(str).toLowerCase());
                }
                if ($("#MD5Options4").is(":checked")) {
                    $("#data").val(hex_sha1(str).toUpperCase());
                }
            }

            /*
         * Configurable variables. You may need to tweak these to be compatible with
         * the server-side, but the defaults work in most cases.
         */
            var hexcase = 0;  /* hex output format. 0 - lowercase; 1 - uppercase        */
            var b64pad = ""; /* base-64 pad character. "=" for strict RFC compliance   */

            /*
             * These are the functions you'll usually want to call
             * They take string arguments and return either hex or base-64 encoded strings
             */
            function hex_sha1(s) {
                return rstr2hex(rstr_sha1(str2rstr_utf8(s)));
            }

            function b64_sha1(s) {
                return rstr2b64(rstr_sha1(str2rstr_utf8(s)));
            }

            function any_sha1(s, e) {
                return rstr2any(rstr_sha1(str2rstr_utf8(s)), e);
            }

            function hex_hmac_sha1(k, d) {
                return rstr2hex(rstr_hmac_sha1(str2rstr_utf8(k), str2rstr_utf8(d)));
            }

            function b64_hmac_sha1(k, d) {
                return rstr2b64(rstr_hmac_sha1(str2rstr_utf8(k), str2rstr_utf8(d)));
            }

            function any_hmac_sha1(k, d, e) {
                return rstr2any(rstr_hmac_sha1(str2rstr_utf8(k), str2rstr_utf8(d)), e);
            }

            /*
             * Perform a simple self-test to see if the VM is working
             */
            function sha1_vm_test() {
                return hex_sha1("abc").toLowerCase() == "a9993e364706816aba3e25717850c26c9cd0d89d";
            }

            /*
             * Calculate the SHA1 of a raw string
             */
            function rstr_sha1(s) {
                return binb2rstr(binb_sha1(rstr2binb(s), s.length * 8));
            }

            /*
             * Calculate the HMAC-SHA1 of a key and some data (raw strings)
             */
            function rstr_hmac_sha1(key, data) {
                var bkey = rstr2binb(key);
                if (bkey.length > 16) bkey = binb_sha1(bkey, key.length * 8);

                var ipad = Array(16), opad = Array(16);
                for (var i = 0; i < 16; i++) {
                    ipad[i] = bkey[i] ^ 0x36363636;
                    opad[i] = bkey[i] ^ 0x5C5C5C5C;
                }

                var hash = binb_sha1(ipad.concat(rstr2binb(data)), 512 + data.length * 8);
                return binb2rstr(binb_sha1(opad.concat(hash), 512 + 160));
            }

            /*
             * Convert a raw string to a hex string
             */
            function rstr2hex(input) {
                try {
                    hexcase
                } catch (e) {
                    hexcase = 0;
                }
                var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
                var output = "";
                var x;
                for (var i = 0; i < input.length; i++) {
                    x = input.charCodeAt(i);
                    output += hex_tab.charAt((x >>> 4) & 0x0F)
                        + hex_tab.charAt(x & 0x0F);
                }
                return output;
            }

            /*
             * Convert a raw string to a base-64 string
             */
            function rstr2b64(input) {
                try {
                    b64pad
                } catch (e) {
                    b64pad = '';
                }
                var tab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
                var output = "";
                var len = input.length;
                for (var i = 0; i < len; i += 3) {
                    var triplet = (input.charCodeAt(i) << 16)
                        | (i + 1 < len ? input.charCodeAt(i + 1) << 8 : 0)
                        | (i + 2 < len ? input.charCodeAt(i + 2) : 0);
                    for (var j = 0; j < 4; j++) {
                        if (i * 8 + j * 6 > input.length * 8) output += b64pad;
                        else output += tab.charAt((triplet >>> 6 * (3 - j)) & 0x3F);
                    }
                }
                return output;
            }

            /*
             * Convert a raw string to an arbitrary string encoding
             */
            function rstr2any(input, encoding) {
                var divisor = encoding.length;
                var remainders = Array();
                var i, q, x, quotient;

                /* Convert to an array of 16-bit big-endian values, forming the dividend */
                var dividend = Array(Math.ceil(input.length / 2));
                for (i = 0; i < dividend.length; i++) {
                    dividend[i] = (input.charCodeAt(i * 2) << 8) | input.charCodeAt(i * 2 + 1);
                }

                /*
                 * Repeatedly perform a long division. The binary array forms the dividend,
                 * the length of the encoding is the divisor. Once computed, the quotient
                 * forms the dividend for the next step. We stop when the dividend is zero.
                 * All remainders are stored for later use.
                 */
                while (dividend.length > 0) {
                    quotient = Array();
                    x = 0;
                    for (i = 0; i < dividend.length; i++) {
                        x = (x << 16) + dividend[i];
                        q = Math.floor(x / divisor);
                        x -= q * divisor;
                        if (quotient.length > 0 || q > 0)
                            quotient[quotient.length] = q;
                    }
                    remainders[remainders.length] = x;
                    dividend = quotient;
                }

                /* Convert the remainders to the output string */
                var output = "";
                for (i = remainders.length - 1; i >= 0; i--) {
                    output += encoding.charAt(remainders[i]);
                }
                /* Append leading zero equivalents */
                var full_length = Math.ceil(input.length * 8 /
                    (Math.log(encoding.length) / Math.log(2)));
                for (i = output.length; i < full_length; i++) {
                    output = encoding[0] + output;
                }
                return output;
            }

            /*
             * Encode a string as utf-8.
             * For efficiency, this assumes the input is valid utf-16.
             */
            function str2rstr_utf8(input) {
                var output = "";
                var i = -1;
                var x, y;

                while (++i < input.length) {
                    /* Decode utf-16 surrogate pairs */
                    x = input.charCodeAt(i);
                    y = i + 1 < input.length ? input.charCodeAt(i + 1) : 0;
                    if (0xD800 <= x && x <= 0xDBFF && 0xDC00 <= y && y <= 0xDFFF) {
                        x = 0x10000 + ((x & 0x03FF) << 10) + (y & 0x03FF);
                        i++;
                    }

                    /* Encode output as utf-8 */
                    if (x <= 0x7F)
                        output += String.fromCharCode(x);
                    else if (x <= 0x7FF)
                        output += String.fromCharCode(0xC0 | ((x >>> 6) & 0x1F),
                            0x80 | (x & 0x3F));
                    else if (x <= 0xFFFF)
                        output += String.fromCharCode(0xE0 | ((x >>> 12) & 0x0F),
                            0x80 | ((x >>> 6) & 0x3F),
                            0x80 | (x & 0x3F));
                    else if (x <= 0x1FFFFF)
                        output += String.fromCharCode(0xF0 | ((x >>> 18) & 0x07),
                            0x80 | ((x >>> 12) & 0x3F),
                            0x80 | ((x >>> 6) & 0x3F),
                            0x80 | (x & 0x3F));
                }
                return output;
            }

            /*
             * Encode a string as utf-16
             */
            function str2rstr_utf16le(input) {
                var output = "";
                for (var i = 0; i < input.length; i++)
                    output += String.fromCharCode(input.charCodeAt(i) & 0xFF,
                        (input.charCodeAt(i) >>> 8) & 0xFF);
                return output;
            }

            function str2rstr_utf16be(input) {
                var output = "";
                for (var i = 0; i < input.length; i++)
                    output += String.fromCharCode((input.charCodeAt(i) >>> 8) & 0xFF,
                        input.charCodeAt(i) & 0xFF);
                return output;
            }

            /*
             * Convert a raw string to an array of big-endian words
             * Characters >255 have their high-byte silently ignored.
             */
            function rstr2binb(input) {
                var output = Array(input.length >> 2);
                for (var i = 0; i < output.length; i++)
                    output[i] = 0;
                for (var i = 0; i < input.length * 8; i += 8)
                    output[i >> 5] |= (input.charCodeAt(i / 8) & 0xFF) << (24 - i % 32);
                return output;
            }

            /*
             * Convert an array of big-endian words to a string
             */
            function binb2rstr(input) {
                var output = "";
                for (var i = 0; i < input.length * 32; i += 8)
                    output += String.fromCharCode((input[i >> 5] >>> (24 - i % 32)) & 0xFF);
                return output;
            }

            /*
             * Calculate the SHA-1 of an array of big-endian words, and a bit length
             */
            function binb_sha1(x, len) {
                /* append padding */
                x[len >> 5] |= 0x80 << (24 - len % 32);
                x[((len + 64 >> 9) << 4) + 15] = len;

                var w = Array(80);
                var a = 1732584193;
                var b = -271733879;
                var c = -1732584194;
                var d = 271733878;
                var e = -1009589776;

                for (var i = 0; i < x.length; i += 16) {
                    var olda = a;
                    var oldb = b;
                    var oldc = c;
                    var oldd = d;
                    var olde = e;

                    for (var j = 0; j < 80; j++) {
                        if (j < 16) w[j] = x[i + j];
                        else w[j] = bit_rol(w[j - 3] ^ w[j - 8] ^ w[j - 14] ^ w[j - 16], 1);
                        var t = safe_add(safe_add(bit_rol(a, 5), sha1_ft(j, b, c, d)),
                            safe_add(safe_add(e, w[j]), sha1_kt(j)));
                        e = d;
                        d = c;
                        c = bit_rol(b, 30);
                        b = a;
                        a = t;
                    }

                    a = safe_add(a, olda);
                    b = safe_add(b, oldb);
                    c = safe_add(c, oldc);
                    d = safe_add(d, oldd);
                    e = safe_add(e, olde);
                }
                return Array(a, b, c, d, e);

            }

            /*
             * Perform the appropriate triplet combination function for the current
             * iteration
             */
            function sha1_ft(t, b, c, d) {
                if (t < 20) return (b & c) | ((~b) & d);
                if (t < 40) return b ^ c ^ d;
                if (t < 60) return (b & c) | (b & d) | (c & d);
                return b ^ c ^ d;
            }

            /*
             * Determine the appropriate additive constant for the current iteration
             */
            function sha1_kt(t) {
                return (t < 20) ? 1518500249 : (t < 40) ? 1859775393 :
                    (t < 60) ? -1894007588 : -899497514;
            }

            /*
             * Add integers, wrapping at 2^32. This uses 16-bit operations internally
             * to work around bugs in some JS interpreters.
             */
            function safe_add(x, y) {
                var lsw = (x & 0xFFFF) + (y & 0xFFFF);
                var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
                return (msw << 16) | (lsw & 0xFFFF);
            }

            /*
             * Bitwise rotate a 32-bit number to the left.
             */
            function bit_rol(num, cnt) {
                return (num << cnt) | (num >>> (32 - cnt));
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>