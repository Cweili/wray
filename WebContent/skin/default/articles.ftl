<#include "macro-head.ftl">
<#include "macro-footer.ftl">
<!DOCTYPE html>
<html>
	<head>
		<@head title="${title?if_exists} - ${blogTitle}">
		<meta name="keywords" content="${metaKeywords?html},${title?if_exists}"/>
		<meta name="description" content="<#list articles as article>${article.title?html}<#if article_has_next>,</#if></#list>"/>
		</@head>
	</head>
	<body>
		<div id="wrapper" align="center">
			<@header></@header>
			<div id="outerwrapper">
				<div id="innerwrapper">
					<div id="rightcol">
						<#include "side.ftl">
					</div>
					<div class="postwrap">
						<h1>${title?if_exists}</h1>
					</div>
					<#include "article-list.ftl">
				</div>
			</div>
			<@footer></@footer>
		</div>
	</body>
</html>
