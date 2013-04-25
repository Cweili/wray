<#include "macro-head.ftl">
<#include "macro-footer.ftl">
<!DOCTYPE html>
<html>
    <head>
        <@head title=" - ${blogTitle}">
        <meta name="keywords" content="${metaKeywords},"/>
        <meta name="description" content="<#list articles as article>${article.title}<#if article_has_next>,</#if></#list>"/>
        </@head>
    </head>
    <body>
        $`{topBarReplacement}
		<div id="wrapper" align="center">
			<@header></@header>
			<div id="outerwrapper">
				<div id="innerwrapper">
					<div id="rightcol">
						<#include "side.ftl">
					</div>
					<#include "article-list.ftl">
				</div>
			</div>
			<@footer></@footer>
		</div>
    </body>
</html>
