<#include "macro-head.ftl">
<!DOCTYPE html>
<html>
	<head>
		<@head title="${item.itemName} - ${blogTitle}">
		<meta name="keywords" content="${metaKeywords},${item.description?if_exists}"/>
		<meta name="description" content="<#list articles as article>${article.title}<#if article_has_next>,</#if></#list>"/>
		</@head>
	</head>
	<body>
		${topBarReplacement?if_exists}
		<div id="wrapper" align="center">
			<#include "header.ftl">
			<div id="outerwrapper">
				<div id="innerwrapper">
					<div id="rightcol">
						<#include "side.ftl">
					</div>
					<#include "article-list.ftl">
				</div>
			</div>
			<#include "footer.ftl">
		</div>
	</body>
</html>
