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
                        <h5 class="card-title">字节(Byte)单位转换换算工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            计算机字节(Byte)单位之间的转换换算工具：bit、Byte、KB、MB、GB、TB、PB、EB之间的转换计算工具</h6>
                        <div class="row">
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" aria-describedby="basic-addon1" id="bitTxt"
                                           oninput="value=value.replace(/[^\d]/g,'')">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon1">比特(bit)</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" aria-describedby="basic-addon2"
                                           id="bytesTxt" oninput="value=value.replace(/[^\d]/g,'')">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon2">字节(Byte)</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" aria-describedby="basic-addon3" id="kbTxt"
                                           oninput="value=value.replace(/[^\d]/g,'')">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon3">千字节(KB)</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" aria-describedby="basic-addon4" id="mbTxt"
                                           oninput="value=value.replace(/[^\d]/g,'')">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon4">兆字节(MB)</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" aria-describedby="basic-addon5" id="gbTxt"
                                           oninput="value=value.replace(/[^\d]/g,'')">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon5">吉字节(GB)</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" aria-describedby="basic-addon6" id="tbTxt"
                                           oninput="value=value.replace(/[^\d]/g,'')">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon6">太字节(TB)</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" aria-describedby="basic-addon7" id="pbTxt"
                                           oninput="value=value.replace(/[^\d]/g,'')">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon7">拍字节(PB)</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" aria-describedby="basic-addon8" id="ebTxt"
                                           oninput="value=value.replace(/[^\d]/g,'')">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon8">艾字节(EB)</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" aria-describedby="basic-addon9" id="zbTxt"
                                           oninput="value=value.replace(/[^\d]/g,'')">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon9">泽字节(ZB)</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" aria-describedby="basic-addon10" id="ybTxt"
                                           oninput="value=value.replace(/[^\d]/g,'')">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon10">尧字节(YB)</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" aria-describedby="basic-addon11" id="bbTxt"
                                           oninput="value=value.replace(/[^\d]/g,'')">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon11">珀字节(BB)</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" aria-describedby="basic-addon12" id="nbTxt"
                                           oninput="value=value.replace(/[^\d]/g,'')">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon12">诺字节(NB)</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" aria-describedby="basic-addon13" id="dbTxt"
                                           oninput="value=value.replace(/[^\d]/g,'')">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon13">刀字节(DB)</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" aria-describedby="basic-addon14" id="cbTxt"
                                           oninput="value=value.replace(/[^\d]/g,'')">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon14">馈字节(CB)</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" aria-describedby="basic-addon15" id="xbTxt"
                                           oninput="value=value.replace(/[^\d]/g,'')">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon15">？字节(XB)</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <blockquote style="font-size: 14px;">
                                <p>
                                    计算机存储单位一般用字节(Byte)、千字节(KB)、兆字节(MB)、吉字节(GB)、太字节(TB)、拍字节(PB)、艾字节(EB)、泽字节(ZB，又称皆字节)、尧字节(YB，又称佑字节)表示。</p>
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
            function setSize(bit, b, kb, mb, gb, tb, pb, eb, zb, yb, bb, nb, db, cb, xb) {
                $("#bitTxt").val(bit);
                $("#bytesTxt").val(b);
                $("#kbTxt").val(kb);
                $("#mbTxt").val(mb);
                $("#gbTxt").val(gb);
                $("#tbTxt").val(tb);
                $("#pbTxt").val(pb);
                $("#ebTxt").val(eb);
                $("#zbTxt").val(zb);
                $("#ybTxt").val(yb);
                $("#bbTxt").val(bb);
                $("#nbTxt").val(nb);
                $("#dbTxt").val(db);
                $("#cbTxt").val(cb);
                $("#xbTxt").val(xb);
            }

            $("#bitTxt").keyup(function () {
                var e = $(this).val();
                if ("" !== e) {
                    e = parseFloat(e);
                    var t = e
                        , n = e / 8
                        , i = e / 8 / 1024
                        , o = e / 8 / 1024 / 1024
                        , r = e / 8 / 1024 / 1024 / 1024
                        , a = e / 8 / 1024 / 1024 / 1024 / 1024
                        , pb = e / 8 / 1024 / 1024 / 1024 / 1024 / 1024
                        , eb = e / 8 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , zb = e / 8 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , yb = e / 8 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , bb = e / 8 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , nb = e / 8 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , db = e / 8 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , cb = e / 8 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        ,
                        xb = e / 8 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024;
                    setSize(t, n, i, o, r, a, pb, eb, zb, yb, bb, nb, db, cb, xb);
                }
            });
            $("#bytesTxt").keyup(function () {
                var e = $(this).val();
                if ("" !== e) {
                    e = parseFloat(e);
                    var t = 8 * e
                        , n = e
                        , i = e / 1024
                        , o = e / 1024 / 1024
                        , r = e / 1024 / 1024 / 1024
                        , a = e / 1024 / 1024 / 1024 / 1024
                        , pb = e / 1024 / 1024 / 1024 / 1024 / 1024
                        , eb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , zb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , yb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , bb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , nb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , db = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , cb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        ,
                        xb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024;
                    setSize(t, n, i, o, r, a, pb, eb, zb, yb, bb, nb, db, cb, xb);
                }
            });
            $("#kbTxt").keyup(function () {
                var e = $(this).val();
                if ("" !== e) {
                    e = parseFloat(e);
                    var t = 1024 * 8 * e
                        , n = 1024 * e
                        , i = e
                        , o = e / 1024
                        , r = e / 1024 / 1024
                        , a = e / 1024 / 1024 / 1024
                        , pb = e / 1024 / 1024 / 1024 / 1024
                        , eb = e / 1024 / 1024 / 1024 / 1024 / 1024
                        , zb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , yb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , bb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , nb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , db = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , cb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        ,
                        xb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024;
                    setSize(t, n, i, o, r, a, pb, eb, zb, yb, bb, nb, db, cb, xb);
                }
            });
            $("#mbTxt").keyup(function () {
                var e = $(this).val();
                if ("" !== e) {
                    e = parseFloat(e);
                    var t = 1024 * 1024 * 8 * e
                        , n = 1024 * 1024 * e
                        , i = 1024 * e
                        , o = e
                        , r = e / 1024
                        , a = e / 1024 / 1024
                        , pb = e / 1024 / 1024 / 1024
                        , eb = e / 1024 / 1024 / 1024 / 1024
                        , zb = e / 1024 / 1024 / 1024 / 1024 / 1024
                        , yb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , bb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , nb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , db = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , cb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , xb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024;
                    setSize(t, n, i, o, r, a, pb, eb, zb, yb, bb, nb, db, cb, xb);
                }
            });
            $("#gbTxt").keyup(function () {
                var e = $(this).val();
                if ("" !== e) {
                    e = parseFloat(e);
                    var t = 1024 * 1024 * 1024 * 8 * e
                        , n = 1024 * 1024 * 1024 * e
                        , i = 1024 * 1024 * e
                        , o = 1024 * e
                        , r = e
                        , a = e / 1024
                        , pb = e / 1024 / 1024
                        , eb = e / 1024 / 1024 / 1024
                        , zb = e / 1024 / 1024 / 1024 / 1024
                        , yb = e / 1024 / 1024 / 1024 / 1024 / 1024
                        , bb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , nb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , db = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , cb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , xb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024;
                    setSize(t, n, i, o, r, a, pb, eb, zb, yb, bb, nb, db, cb, xb);
                }
            });
            $("#tbTxt").keyup(function () {
                var e = $(this).val();
                if ("" !== e) {
                    e = parseFloat(e);
                    var t = 1024 * 1024 * 1024 * 1024 * 8 * e
                        , n = 1024 * 1024 * 1024 * 1024 * e
                        , i = 1024 * 1024 * 1024 * e
                        , o = 1024 * 1024 * e
                        , r = 1024 * e
                        , a = e
                        , pb = e / 1024
                        , eb = e / 1024 / 1024
                        , zb = e / 1024 / 1024 / 1024
                        , yb = e / 1024 / 1024 / 1024 / 1024
                        , bb = e / 1024 / 1024 / 1024 / 1024 / 1024
                        , nb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , db = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , cb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , xb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024;
                    setSize(t, n, i, o, r, a, pb, eb, zb, yb, bb, nb, db, cb, xb);
                }
            });
            $("#pbTxt").keyup(function () {
                var e = $(this).val();
                if ("" !== e) {
                    e = parseFloat(e);
                    var t = 1024 * 1024 * 1024 * 1024 * 1024 * 8 * e
                        , n = 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , i = 1024 * 1024 * 1024 * 1024 * e
                        , o = 1024 * 1024 * 1024 * e
                        , r = 1024 * 1024 * e
                        , a = 1024 * e
                        , pb = e
                        , eb = e / 1024
                        , zb = e / 1024 / 1024
                        , yb = e / 1024 / 1024 / 1024
                        , bb = e / 1024 / 1024 / 1024 / 1024
                        , nb = e / 1024 / 1024 / 1024 / 1024 / 1024
                        , db = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , cb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , xb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024;
                    setSize(t, n, i, o, r, a, pb, eb, zb, yb, bb, nb, db, cb, xb);
                }
            });
            $("#ebTxt").keyup(function () {
                var e = $(this).val();
                if ("" !== e) {
                    e = parseFloat(e);
                    var t = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 8 * e
                        , n = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , i = 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , o = 1024 * 1024 * 1024 * 1024 * e
                        , r = 1024 * 1024 * 1024 * e
                        , a = 1024 * 1024 * e
                        , pb = 1024 * e
                        , eb = e
                        , zb = e / 1024
                        , yb = e / 1024 / 1024
                        , bb = e / 1024 / 1024 / 1024
                        , nb = e / 1024 / 1024 / 1024 / 1024
                        , db = e / 1024 / 1024 / 1024 / 1024 / 1024
                        , cb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024
                        , xb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024 / 1024;
                    setSize(t, n, i, o, r, a, pb, eb, zb, yb, bb, nb, db, cb, xb);
                }
            });
            $("#zbTxt").keyup(function () {
                var e = $(this).val();
                if ("" !== e) {
                    e = parseFloat(e);
                    var t = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 8 * e
                        , n = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , i = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , o = 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , r = 1024 * 1024 * 1024 * 1024 * e
                        , a = 1024 * 1024 * 1024 * e
                        , pb = 1024 * 1024 * e
                        , eb = 1024 * e
                        , zb = e
                        , yb = e / 1024
                        , bb = e / 1024 / 1024
                        , nb = e / 1024 / 1024 / 1024
                        , db = e / 1024 / 1024 / 1024 / 1024
                        , cb = e / 1024 / 1024 / 1024 / 1024 / 1024
                        , xb = e / 1024 / 1024 / 1024 / 1024 / 1024 / 1024;
                    setSize(t, n, i, o, r, a, pb, eb, zb, yb, bb, nb, db, cb, xb);
                }
            });
            $("#ybTxt").keyup(function () {
                var e = $(this).val();
                if ("" !== e) {
                    e = parseFloat(e);
                    var t = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 8 * e
                        , n = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , i = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , o = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , r = 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , a = 1024 * 1024 * 1024 * 1024 * e
                        , pb = 1024 * 1024 * 1024 * e
                        , eb = 1024 * 1024 * e
                        , zb = 1024 * e
                        , yb = e
                        , bb = e / 1024
                        , nb = e / 1024 / 1024
                        , db = e / 1024 / 1024 / 1024
                        , cb = e / 1024 / 1024 / 1024 / 1024
                        , xb = e / 1024 / 1024 / 1024 / 1024 / 1024;
                    setSize(t, n, i, o, r, a, pb, eb, zb, yb, bb, nb, db, cb, xb);
                }
            });
            $("#bbTxt").keyup(function () {
                var e = $(this).val();
                if ("" !== e) {
                    e = parseFloat(e);
                    var t = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 8 * e
                        , n = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , i = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , o = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , r = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , a = 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , pb = 1024 * 1024 * 1024 * 1024 * e
                        , eb = 1024 * 1024 * 1024 * e
                        , zb = 1024 * 1024 * e
                        , yb = 1024 * e
                        , bb = e
                        , nb = e / 1024
                        , db = e / 1024 / 1024
                        , cb = e / 1024 / 1024 / 1024
                        , xb = e / 1024 / 1024 / 1024 / 1024;
                    setSize(t, n, i, o, r, a, pb, eb, zb, yb, bb, nb, db, cb, xb);
                }
            });
            $("#nbTxt").keyup(function () {
                var e = $(this).val();
                if ("" !== e) {
                    e = parseFloat(e);
                    var t = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 8 * e
                        , n = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , i = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , o = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , r = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , a = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , pb = 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , eb = 1024 * 1024 * 1024 * 1024 * e
                        , zb = 1024 * 1024 * 1024 * e
                        , yb = 1024 * 1024 * e
                        , bb = 1024 * e
                        , nb = e
                        , db = e / 1024
                        , cb = e / 1024 / 1024
                        , xb = e / 1024 / 1024 / 1024;
                    setSize(t, n, i, o, r, a, pb, eb, zb, yb, bb, nb, db, cb, xb);
                }
            });
            $("#dbTxt").keyup(function () {
                var e = $(this).val();
                if ("" !== e) {
                    e = parseFloat(e);
                    var t = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 8 * e
                        , n = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , i = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , o = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , r = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , a = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , pb = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , eb = 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , zb = 1024 * 1024 * 1024 * 1024 * e
                        , yb = 1024 * 1024 * 1024 * e
                        , bb = 1024 * 1024 * e
                        , nb = 1024 * e
                        , db = e
                        , cb = e / 1024
                        , xb = e / 1024 / 1024;
                    setSize(t, n, i, o, r, a, pb, eb, zb, yb, bb, nb, db, cb, xb);
                }
            });
            $("#cbTxt").keyup(function () {
                var e = $(this).val();
                if ("" !== e) {
                    e = parseFloat(e);
                    var t = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 8 * e
                        , n = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , i = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , o = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , r = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , a = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , pb = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , eb = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , zb = 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , yb = 1024 * 1024 * 1024 * 1024 * e
                        , bb = 1024 * 1024 * 1024 * e
                        , nb = 1024 * 1024 * e
                        , db = 1024 * e
                        , cb = e
                        , xb = e / 1024;
                    setSize(t, n, i, o, r, a, pb, eb, zb, yb, bb, nb, db, cb, xb);
                }
            });
            $("#xbTxt").keyup(function () {
                var e = $(this).val();
                if ("" !== e) {
                    e = parseFloat(e);
                    var t = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 8 * e
                        ,
                        n = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , i = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , o = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , r = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , a = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , pb = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , eb = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , zb = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , yb = 1024 * 1024 * 1024 * 1024 * 1024 * e
                        , bb = 1024 * 1024 * 1024 * 1024 * e
                        , nb = 1024 * 1024 * 1024 * e
                        , db = 1024 * 1024 * e
                        , cb = 1024 * e
                        , xb = e;
                    setSize(t, n, i, o, r, a, pb, eb, zb, yb, bb, nb, db, cb, xb);
                }
            });
            $(function () {
                $("#kbTxt").val("1");
                $("#kbTxt").keyup();
            });
        </script>
    </@footer>
    </body>
    </html>
</@compress>