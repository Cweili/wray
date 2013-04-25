<div id="maincol">
<#list articles as article>
	<div class="postwrap">
		<div class="postmeta2">
			<div class="meta2inner">
				<div class="pyear">
				<!--#if article.hasUpdated>
				$`{article.articleUpdateDate?string("yyyy")}
				<``#else-->
				${article.createTime?string("yyyy")}
				<!--/#if-->
				</div>
				<div class="pday">
				<!--``#if article.hasUpdated>
				$`{article.articleUpdateDate?string("MM/dd")}
				<``#else-->
				${article.createTime?string("MM/dd")}
				<!--/#if-->
				</div>
			</div>
		</div>
		<h2 class="posttitle shortline">
			<a href="article/${article.permalink}">${article.title}</a>
		</h2>
		<div class="postmeta">
			<!--``#if article.articlePutTop>
				${topArticleLabel}
			<``/#if>
			<``#if article.hasUpdated>
				${updatedLabel}
			<``/#if-->
			<!--a href="author/$`{article.userId}">$`{article.userId}</a-->
			${adminNick} <a href="article/${article.permalink}">${article.createTime?string("HH:mm:ss")}</a>
			${article.hits} ${viewLabel}
		</div>
		<div class="clr16"></div>
		<div class="postcontent breakline">
			${article.content?replace('<!--more-->','... [<a href="article/'+article.permalink+'/#more">'+moreLabel+'</a>]')}
		</div>
		<div class="clr"></div>
		<div class="roubcornrcontent">
			<span class="posttags" title="${tagLabel}">
				<#list article.tag?split(",") as articleTag>
				<a href="tag/${articleTag?url('UTF-8')}">
						${articleTag}</a><#if articleTag_has_next>,</#if>
				</#list>
			</span>
			<a href="article/${article.permalink}/#comments">
				<span class="postcomments" title="${commentLabel}">
				${article.commentCount}
				</span>
			</a>
			<div class="clr"></div>
		</div>
		<div class="clr"></div>
	</div>
</#list>
<#include "pagination.ftl">
</div>
<@copyright></@copyright>