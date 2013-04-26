<#include "macro-head.ftl">
<#include "macro-comments.ftl">
<!DOCTYPE html>
<html>
    <head>
        <@head title="${article.title} - ${blogTitle}">
        <meta name="keywords" content="${metaKeywords},${article.tag}" />
        <meta name="description" content="${metaDescription}" />
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
					<div id="maincol">
						<div class="postwrap">
							<div class="postmeta2">
								<div class="meta2inner">
									<div class="pyear"></div>
									<div class="pday"></div>
								</div>
							</div>
							<h2 class="posttitle">
								${article.title}
							</h2>
							<div class="postmeta">
								<a href="">${blogTitle}</a>
							</div>
							<div class="clr16"></div>
							<div class="postcontent breakline article-body">
							${article.content}
							</div>
							<div class="clr"></div>
						</div>
						<div class="clr"></div>
						<#if (article.commentStatus > 0)>
							<@comments commentList=commentList articleId=article.articleId permalink=article.permalink></@comments>
						</#if>
					</div>
					<@copyright></@copyright>
				</div>
			</div>
			<@footer></@footer>
		</div>
		<#if (article.commentStatus > 0)>
			<@comment_script></@comment_script>
		</#if>
    </body>
</html>
