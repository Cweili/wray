<#include "macro-head.ftl">
<#include "macro-footer.ftl">
<!DOCTYPE html>
<html>
	<head>
		<@head title="${allTagsLabel} - ${blogTitle}">
		<meta name="keywords" content="${metaKeywords},${allTagsLabel}"/>
		<meta name="description" content="<#list tags as tag>${tag.itemName}<#if tag_has_next>,</#if></#list>"/>
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
					<div id="maincol">
						<div class="postwrap">
							<div class="postmeta2">
								<div class="meta2inner">
									<div class="pyear"></div>
									<div class="pday"></div>
								</div>
							</div>
							<h2 class="posttitle">
								${allTagsLabel}
							</h2>
							<div class="postmeta">
								<a href="">${blogTitle}</a>
							</div>
							<div class="clr16"></div>
							<div class="postcontent">
							<#list tags as tag>
								<a href="tag/${tag.itemName?url('UTF-8')}" title="${tag.itemName}" class="tag">
									${tag.itemName}(<b>${tag.count}</b>)
								</a>
							</#list>
							</div>
							<div class="clr"></div>
						</div>
					</div>
					<@copyright></@copyright>
				</div>
			</div>
			<@footer></@footer>
		</div>
	</body>
</html>