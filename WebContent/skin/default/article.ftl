<#include "macro-head.ftl">
<#--include "macro-comments.ftl"-->
<!DOCTYPE html>
<html>
    <head>
        <@head title="${article.title} - ${blogTitle}">
        <meta name="keywords" content="${article.tag}"/>
        <meta name="description" content="${article.content?html}"/>
        </@head>
		<link type="text/css" rel="stylesheet" href="include/editor/plugins/code/prettify.css" charset="utf-8" />
		<script src="include/editor/plugins/code/prettify.css" type="text/javascript"></script>
    </head>
    <body>
        $`{topBarReplacement}
		<div id="wrapper" align="center">
			<#include "header.ftl">
			<div id="outerwrapper">
				<div id="innerwrapper">
					<div id="rightcol">
						<#include "side.ftl">
					</div>
					<!---->
					<div id="maincol">
						<div class="postwrap">
							<div class="postmeta2">
								<div class="meta2inner">
									<div class="pyear">
									<#--if article.hasUpdated>
									$`{article.articleUpdateDate?string("yyyy")}
									<#else-->
									${article.createTime?string("yyyy")}
									<!--/#if-->
									</div>
									<div class="pday">
									<#--if article.hasUpdated>
									$`{article.articleUpdateDate?string("MM/dd")}
									<#else-->
									${article.createTime?string("MM/dd")}
									<!--/#if-->
									</div>
								</div>
							</div>
							<h2 class="posttitle breakline">
								<a href="article/${article.permalink}/">${article.title}</a>
							</h2>
							<div class="postmeta">
								<#--if article.articlePutTop>
									$`{topArticleLabel}
								</#if>
								<#if article.hasUpdated>
									$`{updatedLabel}
								</#if-->
								${adminNick} post on
								<#list relatedCats as cat>
									<a href="/category/${cat.permalink}/">
											${cat.itemName}</a><#if cat_has_next>,</#if>
								</#list>
								<a href="article/${article.permalink}/">${article.createTime?string("HH:mm:ss")}</a>
								${article.hits} ${viewLabel}
							</div>
							<div class="clr16"></div>
							<div class="postcontent breakline article-body">
							${article.content}
							<#--if "" != article.articleSign.signHTML?trim>
							<div class="marginTop12">
								$`{article.articleSign.signHTML}
							</div>
							</#if-->
							</div>
							<div class="clr"></div>
							<div class="roubcornrcontent">
								<span class="posttags" title="$`{tagLabel}">
									<#list article.tag?split(",") as articleTag>
									<a href="tag/${articleTag?url('UTF-8')}/">
											${articleTag}</a><#if articleTag_has_next>,</#if>
									</#list>
								</span>
								<a href="${article.permalink}#comments">
									<span class="postcomments" title="${commentLabel}">
									${article.commentCount}
									</span>
								</a>
								<div class="clr"></div>
							</div>
							<div class="clr"></div>
							<div class="nextlog shortline">
								<#--if nextArticlePermalink??>
								<a href="$`{nextArticlePermalink}">$`{nextArticle1Label}$`{nextArticleTitle}</a>
								<br/>
								</#if>
								<#if previousArticlePermalink??>
								<a href="$`{previousArticlePermalink}">$`{previousArticle1Label}$`{previousArticleTitle}</a>
								<br/>
								</#if-->
							</div>
							<div id="relevantArticles" class="article-relative"></div>
							<div id="randomArticles" class="article-relative"></div>
							<div id="externalRelevantArticles" class="article-relative"></div>
							<div class="clr"></div>
						</div>
						<div class="clr"></div>
						<#--@comments commentList=articleComments permalink=article.articlePermalink></@comments-->
					</div>
					<div class="clr"></div>
					<div class="copyr">
					&copy; ${year}&nbsp;<a href="http://${blogHost}">${blogTitle}</a>
					</div>
					<div class="clr16"></div>
					<!---->
				</div>
			</div>
			<#include "footer.ftl">
		</div>
        <!--@comment_script oId=article.oId>
        page.tips.externalRelevantArticlesDisplayCount = "$`{externalRelevantArticlesDisplayCount}";
        page.loadRandomArticles();
         page.loadRelevantArticles('$`{article.oId}', '$`{relevantArticles1Label}');
        <'#if 0 != externalRelevantArticlesDisplayCount>
        page.loadExternalRelevantArticles("<'#list article.articleTags?split(",") as articleTag>$`{articleTag}<'#if articleTag_has_next>,<'/#if><'/#list>");
        <'/#if>
        <'/@comment_script-->
    </body>
</html>