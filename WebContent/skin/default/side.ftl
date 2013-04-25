<div id="searchbox">
	<form method="get" id="searchform" action="search">
		<input type="text" value="Search keywords" name="s" id="searchbox" onfocus="this.value=''" class="searchinput"/>
		<input type="submit" class="submitbutton" value="Search" />
	</form> 
	<div class="clr"></div>
</div>
<#if noticeBoard?has_content>
	<div class="widget-cat">
		<h4>${noticeBoardLabel}</h4>
		<ul id="bloggerinfo">
		${noticeBoard}
		</ul>
	</div>
	<div class="clr"></div>
</#if>
<#if (recentComments?size > 0)>
	<div class="widget-cat">
		<h4>${recentCommentsLabel}</h4>
		<ul id="newcomment">
		<#list recentComments as comment>
			<li>
				${comment.author}<br>
				<a class="breakline" href="comment-${comment.commentId}">
					${comment.content}
				</a>
			</li>
		</#list>
		</ul>
	</div>
	<div class="clr"></div>
</#if>
<#if (mostUsedTags?size > 0)>
	<div class="widget-cat">
		<h4><span>${popTagsLabel}</span></h4>
		<div id="tagcloud">
			<#list mostUsedTags as tag>
				<a href="tag/${tag.itemName?url('UTF-8')}" title="${tag.itemName}(${tag.count})">
				${tag.itemName}</a>
			</#list>
		 </div>
	</div>
	<div class="clr"></div>
</#if>
<#if (mostCommentArticles?size > 0)>
	<div class="widget-cat">
		<h4>${mostCommentArticlesLabel}</h4>
		<ul>
		<#list mostCommentArticles as article>
			<li class="shortline">
				<sup>[${article.commentCount}]</sup>
				<a title="${article.title}" href="article/${article.permalink?url('UTF-8')}">
					${article.title}
				</a>
			</li>
		</#list>
		</ul>
	</div>
	<div class="clr"></div>
</#if>
<#if (mostViewCountArticles?size > 0)>
	<div class="widget-cat">
		<h4>${mostViewCountArticlesLabel}</h4>
		<ul>
		<#list mostViewCountArticles as article>
			<li class="shortline">
				<sup>[${article.hits}]</sup>
				<a title="${article.title}" href="article/${article.permalink?url('UTF-8')}">
					${article.title}
				</a>
			</li>
		</#list>
		</ul>
	</div>
	<div class="clr"></div>
</#if>
<#if (links?size > 0)>
	<div class="widget">
		<h4>${linkLabel}</h4>
		<ul>
		<#list links as link>
			<li><a href="${link.description}" title="${link.itemName}" target="_blank">
			${link.itemName}</a></li>
		</#list>
		</ul>
	</div>
	<div class="clr"></div>
</#if>
<``#if 0 != archiveDates?size>
	<div class="widget">
		<h4>${archiveLabel}</h4>
		<ul>
		<#--
		<``#list archiveDates as archiveDate>
			<``#if "en" == localeString?substring(0, 2)>
			<li><a href="/archives/$`{archiveDate.archiveDateYear}/$`{archiveDate.archiveDateMonth}"
			   title="$`{archiveDate.monthName} $`{archiveDate.archiveDateYear}($`{archiveDate.archiveDatePublishedArticleCount})">
				$`{archiveDate.monthName} $`{archiveDate.archiveDateYear}</a>($`{archiveDate.archiveDatePublishedArticleCount})</li>
			<``#else>
			<li><a href="/archives/$`{archiveDate.archiveDateYear}/$`{archiveDate.archiveDateMonth}"
			   title="$`{archiveDate.archiveDateYear} $`{yearLabel} $`{archiveDate.archiveDateMonth} $`{monthLabel}($`{archiveDate.archiveDatePublishedArticleCount})">
				$`{archiveDate.archiveDateYear} $`{yearLabel} $`{archiveDate.archiveDateMonth} $`{monthLabel}</a>($`{archiveDate.archiveDatePublishedArticleCount})</li>
			<``/#if>
		<``/#list>
		-->
		</ul>
	</div>
	<div class="clr"></div>
<``/#if>