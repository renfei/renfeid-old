<#include "../layout/layout.ftl"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
<@head pageView>
    <link rel="stylesheet" type="text/css" href="https://cdn.renfei.net/css/lightbox.css?v=20200506135243"/>
    <#if HighlightJS??>
        <link rel="stylesheet" href="https://cdn.renfei.net/thunder/highlight/styles/atom-one-dark.css"/>
    </#if>
</@head>
<body>
<@header pageView>

</@header>
<@footer pageView>
    <script type="application/ld+json">
            ${jsonld!}
    </script>
    <script type='text/javascript' charset="UTF-8" src="//cdn.renfei.net/js/lightbox.min.js?v=20200506135243"
            async></script>
<#if HighlightJS??>
    <script src="https://cdn.renfei.net/thunder/highlight/highlight.pack.js"></script>
    <script>
        $(function () {
            $("code").each(function () {
                $(this).html("<ol><li>" + $(this).html().replace(/\n/g, "\n</li><li>") + "\n</li></ol>");
            });
        });
        hljs.initHighlightingOnLoad();
    </script>
</#if>
    <script>
        $(function () {
            $(".renfei-content img").each(function () {
                $(this).attr('class', "img-fluid shadow mb-5 bg-white rounded mx-auto d-block");
                var strA = "<a class=\"lightbox\" href='" + this.src + "' style='position: initial;' data-lightbox=\"example-set\"></a>";
                $(this).wrapAll(strA);
            });
        })
    </script>
</@footer>
</body>
</html>