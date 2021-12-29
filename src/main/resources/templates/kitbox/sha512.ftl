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
                        <h5 class="card-title">SHA-512在线加密工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">SHA-512 Online Encryption</h6>
                        <div style="margin: auto;max-width: 600px;">
                            <form>
                                <label class="sr-only" for="quantityNumber" style="line-height: 40px;">字符串:</label>
                                <div class="input-group mb-2 mr-sm-2">
                                    <textarea type="text" class="form-control" id="quantityNumber" rows="4"></textarea>
                                    <div class="input-group-prepend">
                                        <div class="btn btn-primary align-middle" onclick="generate()">SHA-512加密</div>
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
                            </form>
                            <form>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">SHA-512</span>
                                    </div>
                                    <textarea id="data" rows="8" class="form-control"
                                              aria-label="With textarea"></textarea>
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
                                        <footer>SHA512算法使用的哈希值长度是512位
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
                    $("#data").val(hex_sha512(str).toLowerCase());
                }
                if ($("#MD5Options4").is(":checked")) {
                    $("#data").val(hex_sha512(str).toUpperCase());
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
            function hex_sha512(s) {
                return rstr2hex(rstr_sha512(str2rstr_utf8(s)));
            }

            function b64_sha512(s) {
                return rstr2b64(rstr_sha512(str2rstr_utf8(s)));
            }

            function any_sha512(s, e) {
                return rstr2any(rstr_sha512(str2rstr_utf8(s)), e);
            }

            function hex_hmac_sha512(k, d) {
                return rstr2hex(rstr_hmac_sha512(str2rstr_utf8(k), str2rstr_utf8(d)));
            }

            function b64_hmac_sha512(k, d) {
                return rstr2b64(rstr_hmac_sha512(str2rstr_utf8(k), str2rstr_utf8(d)));
            }

            function any_hmac_sha512(k, d, e) {
                return rstr2any(rstr_hmac_sha512(str2rstr_utf8(k), str2rstr_utf8(d)), e);
            }

            /*
             * Perform a simple self-test to see if the VM is working
             */
            function sha512_vm_test() {
                return hex_sha512("abc").toLowerCase() ==
                    "ddaf35a193617abacc417349ae20413112e6fa4e89a97ea20a9eeee64b55d39a" +
                    "2192992a274fc1a836ba3c23a3feebbd454d4423643ce80e2a9ac94fa54ca49f";
            }

            /*
             * Calculate the SHA-512 of a raw string
             */
            function rstr_sha512(s) {
                return binb2rstr(binb_sha512(rstr2binb(s), s.length * 8));
            }

            /*
             * Calculate the HMAC-SHA-512 of a key and some data (raw strings)
             */
            function rstr_hmac_sha512(key, data) {
                var bkey = rstr2binb(key);
                if (bkey.length > 32) bkey = binb_sha512(bkey, key.length * 8);

                var ipad = Array(32), opad = Array(32);
                for (var i = 0; i < 32; i++) {
                    ipad[i] = bkey[i] ^ 0x36363636;
                    opad[i] = bkey[i] ^ 0x5C5C5C5C;
                }

                var hash = binb_sha512(ipad.concat(rstr2binb(data)), 1024 + data.length * 8);
                return binb2rstr(binb_sha512(opad.concat(hash), 1024 + 512));
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
                var i, j, q, x, quotient;

                /* Convert to an array of 16-bit big-endian values, forming the dividend */
                var dividend = Array(Math.ceil(input.length / 2));
                for (i = 0; i < dividend.length; i++) {
                    dividend[i] = (input.charCodeAt(i * 2) << 8) | input.charCodeAt(i * 2 + 1);
                }

                /*
                 * Repeatedly perform a long division. The binary array forms the dividend,
                 * the length of the encoding is the divisor. Once computed, the quotient
                 * forms the dividend for the next step. All remainders are stored for later
                 * use.
                 */
                var full_length = Math.ceil(input.length * 8 /
                    (Math.log(encoding.length) / Math.log(2)));
                var remainders = Array(full_length);
                for (j = 0; j < full_length; j++) {
                    quotient = Array();
                    x = 0;
                    for (i = 0; i < dividend.length; i++) {
                        x = (x << 16) + dividend[i];
                        q = Math.floor(x / divisor);
                        x -= q * divisor;
                        if (quotient.length > 0 || q > 0)
                            quotient[quotient.length] = q;
                    }
                    remainders[j] = x;
                    dividend = quotient;
                }

                /* Convert the remainders to the output string */
                var output = "";
                for (i = remainders.length - 1; i >= 0; i--) {
                    output += encoding.charAt(remainders[i]);
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
                for (var i = 0; i < input.length; i++) {
                    output += String.fromCharCode(input.charCodeAt(i) & 0xFF,
                        (input.charCodeAt(i) >>> 8) & 0xFF);
                }
                return output;
            }

            function str2rstr_utf16be(input) {
                var output = "";
                for (var i = 0; i < input.length; i++) {
                    output += String.fromCharCode((input.charCodeAt(i) >>> 8) & 0xFF,
                        input.charCodeAt(i) & 0xFF);
                }
                return output;
            }

            /*
             * Convert a raw string to an array of big-endian words
             * Characters >255 have their high-byte silently ignored.
             */
            function rstr2binb(input) {
                var output = Array(input.length >> 2);
                for (var i = 0; i < output.length; i++) {
                    output[i] = 0;
                }
                for (var i = 0; i < input.length * 8; i += 8) {
                    output[i >> 5] |= (input.charCodeAt(i / 8) & 0xFF) << (24 - i % 32);
                }
                return output;
            }

            /*
             * Convert an array of big-endian words to a string
             */
            function binb2rstr(input) {
                var output = "";
                for (var i = 0; i < input.length * 32; i += 8) {
                    output += String.fromCharCode((input[i >> 5] >>> (24 - i % 32)) & 0xFF);
                }
                return output;
            }

            /*
             * Calculate the SHA-512 of an array of big-endian dwords, and a bit length
             */
            var sha512_k;

            function binb_sha512(x, len) {
                if (sha512_k == undefined) {

                    sha512_k = new Array(
                        new int64(0x428a2f98, -685199838), new int64(0x71374491, 0x23ef65cd),
                        new int64(-1245643825, -330482897), new int64(-373957723, -2121671748),
                        new int64(0x3956c25b, -213338824), new int64(0x59f111f1, -1241133031),
                        new int64(-1841331548, -1357295717), new int64(-1424204075, -630357736),
                        new int64(-670586216, -1560083902), new int64(0x12835b01, 0x45706fbe),
                        new int64(0x243185be, 0x4ee4b28c), new int64(0x550c7dc3, -704662302),
                        new int64(0x72be5d74, -226784913), new int64(-2132889090, 0x3b1696b1),
                        new int64(-1680079193, 0x25c71235), new int64(-1046744716, -815192428),
                        new int64(-459576895, -1628353838), new int64(-272742522, 0x384f25e3),
                        new int64(0xfc19dc6, -1953704523), new int64(0x240ca1cc, 0x77ac9c65),
                        new int64(0x2de92c6f, 0x592b0275), new int64(0x4a7484aa, 0x6ea6e483),
                        new int64(0x5cb0a9dc, -1119749164), new int64(0x76f988da, -2096016459),
                        new int64(-1740746414, -295247957), new int64(-1473132947, 0x2db43210),
                        new int64(-1341970488, -1728372417), new int64(-1084653625, -1091629340),
                        new int64(-958395405, 0x3da88fc2), new int64(-710438585, -1828018395),
                        new int64(0x6ca6351, -536640913), new int64(0x14292967, 0xa0e6e70),
                        new int64(0x27b70a85, 0x46d22ffc), new int64(0x2e1b2138, 0x5c26c926),
                        new int64(0x4d2c6dfc, 0x5ac42aed), new int64(0x53380d13, -1651133473),
                        new int64(0x650a7354, -1951439906), new int64(0x766a0abb, 0x3c77b2a8),
                        new int64(-2117940946, 0x47edaee6), new int64(-1838011259, 0x1482353b),
                        new int64(-1564481375, 0x4cf10364), new int64(-1474664885, -1136513023),
                        new int64(-1035236496, -789014639), new int64(-949202525, 0x654be30),
                        new int64(-778901479, -688958952), new int64(-694614492, 0x5565a910),
                        new int64(-200395387, 0x5771202a), new int64(0x106aa070, 0x32bbd1b8),
                        new int64(0x19a4c116, -1194143544), new int64(0x1e376c08, 0x5141ab53),
                        new int64(0x2748774c, -544281703), new int64(0x34b0bcb5, -509917016),
                        new int64(0x391c0cb3, -976659869), new int64(0x4ed8aa4a, -482243893),
                        new int64(0x5b9cca4f, 0x7763e373), new int64(0x682e6ff3, -692930397),
                        new int64(0x748f82ee, 0x5defb2fc), new int64(0x78a5636f, 0x43172f60),
                        new int64(-2067236844, -1578062990), new int64(-1933114872, 0x1a6439ec),
                        new int64(-1866530822, 0x23631e28), new int64(-1538233109, -561857047),
                        new int64(-1090935817, -1295615723), new int64(-965641998, -479046869),
                        new int64(-903397682, -366583396), new int64(-779700025, 0x21c0c207),
                        new int64(-354779690, -840897762), new int64(-176337025, -294727304),
                        new int64(0x6f067aa, 0x72176fba), new int64(0xa637dc5, -1563912026),
                        new int64(0x113f9804, -1090974290), new int64(0x1b710b35, 0x131c471b),
                        new int64(0x28db77f5, 0x23047d84), new int64(0x32caab7b, 0x40c72493),
                        new int64(0x3c9ebe0a, 0x15c9bebc), new int64(0x431d67c4, -1676669620),
                        new int64(0x4cc5d4be, -885112138), new int64(0x597f299c, -60457430),
                        new int64(0x5fcb6fab, 0x3ad6faec), new int64(0x6c44198c, 0x4a475817));
                }


                var H = new Array(
                    new int64(0x6a09e667, -205731576),
                    new int64(-1150833019, -2067093701),
                    new int64(0x3c6ef372, -23791573),
                    new int64(-1521486534, 0x5f1d36f1),
                    new int64(0x510e527f, -1377402159),
                    new int64(-1694144372, 0x2b3e6c1f),
                    new int64(0x1f83d9ab, -79577749),
                    new int64(0x5be0cd19, 0x137e2179));

                var T1 = new int64(0, 0),
                    T2 = new int64(0, 0),
                    a = new int64(0, 0),
                    b = new int64(0, 0),
                    c = new int64(0, 0),
                    d = new int64(0, 0),
                    e = new int64(0, 0),
                    f = new int64(0, 0),
                    g = new int64(0, 0),
                    h = new int64(0, 0),

                    s0 = new int64(0, 0),
                    s1 = new int64(0, 0),
                    Ch = new int64(0, 0),
                    Maj = new int64(0, 0),
                    r1 = new int64(0, 0),
                    r2 = new int64(0, 0),
                    r3 = new int64(0, 0);
                var j, i;
                var W = new Array(80);
                for (i = 0; i < 80; i++) {
                    W[i] = new int64(0, 0);
                }

                x[len >> 5] |= 0x80 << (24 - (len & 0x1f));
                x[((len + 128 >> 10) << 5) + 31] = len;

                for (i = 0; i < x.length; i += 32) {
                    int64copy(a, H[0]);
                    int64copy(b, H[1]);
                    int64copy(c, H[2]);
                    int64copy(d, H[3]);
                    int64copy(e, H[4]);
                    int64copy(f, H[5]);
                    int64copy(g, H[6]);
                    int64copy(h, H[7]);

                    for (j = 0; j < 16; j++) {
                        W[j].h = x[i + 2 * j];
                        W[j].l = x[i + 2 * j + 1];
                    }

                    for (j = 16; j < 80; j++) {
                        int64rrot(r1, W[j - 2], 19);
                        int64revrrot(r2, W[j - 2], 29);
                        int64shr(r3, W[j - 2], 6);
                        s1.l = r1.l ^ r2.l ^ r3.l;
                        s1.h = r1.h ^ r2.h ^ r3.h;
                        int64rrot(r1, W[j - 15], 1);
                        int64rrot(r2, W[j - 15], 8);
                        int64shr(r3, W[j - 15], 7);
                        s0.l = r1.l ^ r2.l ^ r3.l;
                        s0.h = r1.h ^ r2.h ^ r3.h;

                        int64add4(W[j], s1, W[j - 7], s0, W[j - 16]);
                    }

                    for (j = 0; j < 80; j++) {
                        Ch.l = (e.l & f.l) ^ (~e.l & g.l);
                        Ch.h = (e.h & f.h) ^ (~e.h & g.h);

                        int64rrot(r1, e, 14);
                        int64rrot(r2, e, 18);
                        int64revrrot(r3, e, 9);
                        s1.l = r1.l ^ r2.l ^ r3.l;
                        s1.h = r1.h ^ r2.h ^ r3.h;

                        int64rrot(r1, a, 28);
                        int64revrrot(r2, a, 2);
                        int64revrrot(r3, a, 7);
                        s0.l = r1.l ^ r2.l ^ r3.l;
                        s0.h = r1.h ^ r2.h ^ r3.h;

                        Maj.l = (a.l & b.l) ^ (a.l & c.l) ^ (b.l & c.l);
                        Maj.h = (a.h & b.h) ^ (a.h & c.h) ^ (b.h & c.h);

                        int64add5(T1, h, s1, Ch, sha512_k[j], W[j]);
                        int64add(T2, s0, Maj);

                        int64copy(h, g);
                        int64copy(g, f);
                        int64copy(f, e);
                        int64add(e, d, T1);
                        int64copy(d, c);
                        int64copy(c, b);
                        int64copy(b, a);
                        int64add(a, T1, T2);
                    }
                    int64add(H[0], H[0], a);
                    int64add(H[1], H[1], b);
                    int64add(H[2], H[2], c);
                    int64add(H[3], H[3], d);
                    int64add(H[4], H[4], e);
                    int64add(H[5], H[5], f);
                    int64add(H[6], H[6], g);
                    int64add(H[7], H[7], h);
                }

                var hash = new Array(16);
                for (i = 0; i < 8; i++) {
                    hash[2 * i] = H[i].h;
                    hash[2 * i + 1] = H[i].l;
                }
                return hash;
            }


            function int64(h, l) {
                this.h = h;
                this.l = l;

            }


            function int64copy(dst, src) {
                dst.h = src.h;
                dst.l = src.l;
            }


            function int64rrot(dst, x, shift) {
                dst.l = (x.l >>> shift) | (x.h << (32 - shift));
                dst.h = (x.h >>> shift) | (x.l << (32 - shift));
            }


            function int64revrrot(dst, x, shift) {
                dst.l = (x.h >>> shift) | (x.l << (32 - shift));
                dst.h = (x.l >>> shift) | (x.h << (32 - shift));
            }


            function int64shr(dst, x, shift) {
                dst.l = (x.l >>> shift) | (x.h << (32 - shift));
                dst.h = (x.h >>> shift);
            }


            function int64add(dst, x, y) {
                var w0 = (x.l & 0xffff) + (y.l & 0xffff);
                var w1 = (x.l >>> 16) + (y.l >>> 16) + (w0 >>> 16);
                var w2 = (x.h & 0xffff) + (y.h & 0xffff) + (w1 >>> 16);
                var w3 = (x.h >>> 16) + (y.h >>> 16) + (w2 >>> 16);
                dst.l = (w0 & 0xffff) | (w1 << 16);
                dst.h = (w2 & 0xffff) | (w3 << 16);
            }


            function int64add4(dst, a, b, c, d) {
                var w0 = (a.l & 0xffff) + (b.l & 0xffff) + (c.l & 0xffff) + (d.l & 0xffff);
                var w1 = (a.l >>> 16) + (b.l >>> 16) + (c.l >>> 16) + (d.l >>> 16) + (w0 >>> 16);
                var w2 = (a.h & 0xffff) + (b.h & 0xffff) + (c.h & 0xffff) + (d.h & 0xffff) + (w1 >>> 16);
                var w3 = (a.h >>> 16) + (b.h >>> 16) + (c.h >>> 16) + (d.h >>> 16) + (w2 >>> 16);
                dst.l = (w0 & 0xffff) | (w1 << 16);
                dst.h = (w2 & 0xffff) | (w3 << 16);
            }


            function int64add5(dst, a, b, c, d, e) {
                var w0 = (a.l & 0xffff) + (b.l & 0xffff) + (c.l & 0xffff) + (d.l & 0xffff) + (e.l & 0xffff);
                var w1 = (a.l >>> 16) + (b.l >>> 16) + (c.l >>> 16) + (d.l >>> 16) + (e.l >>> 16) + (w0 >>> 16);
                var w2 = (a.h & 0xffff) + (b.h & 0xffff) + (c.h & 0xffff) + (d.h & 0xffff) + (e.h & 0xffff) + (w1 >>> 16);
                var w3 = (a.h >>> 16) + (b.h >>> 16) + (c.h >>> 16) + (d.h >>> 16) + (e.h >>> 16) + (w2 >>> 16);
                dst.l = (w0 & 0xffff) | (w1 << 16);
                dst.h = (w2 & 0xffff) | (w3 << 16);
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>