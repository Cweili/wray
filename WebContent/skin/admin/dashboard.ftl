<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<ul class="modals">
			<li>
				<a href="admin-article-add">
					<img src="res/image/pencil_48.png" alt="创建新文章" />
					<div>创建新文章</div>
				</a>
			</li>
			<li>
				<a href="admin-page-add">
					<img src="res/image/page_48.png" alt="创建新页面" />
					<span>创建新页面</span>
				</a>
			</li>
			<li>
				<a href="admin-comment">
					<img src="res/image/letter_48.png" alt="管理评论" />
					<span>管理评论</span>
				</a>
			</li>
			<li>
				<a href="admin-upload">
					<img src="res/image/save_48.png" alt="管理附件" />
					<span>管理附件</span>
				</a>
			</li>
			<li>
				<a href="admin-setup-basic">
					<img src="res/image/gear_48.png" alt="博客设置" />
					<span>博客设置</span>
				</a>
			</li>
		</ul>
		<fieldset><legend>博客统计</legend>
			<div class="input_field">
				<p>
					<strong><a href="admin-article-pub">已发布文章</a></strong>
					<em><a href="admin-article-pub">${articlePublishCount} 篇</a></em>
					<strong><a href="admin-page-pub">已发布页面</a></strong>
					<em><a href="admin-page-pub">${pagePublishCount} 个</a></em>
				</p>
				<p>
					<strong><a href="admin-article-draft">文章草稿</a></strong>
					<em><a href="admin-article-draft">${articleDraftCount} 篇</a></em>
					<strong><a href="admin-page-private">私密页面</a></strong>
					<em><a href="admin-page-private">${pagePrivateCount} 个</a></em>
				</p>
				<p>
					<strong><a href="admin-article-recycle">文章回收站</a></strong>
					<em><a href="admin-article-recycle">${articleRecycleCount} 篇</a></em>
					<strong><a href="admin-comment">评论总数</a></strong>
					<em><a href="admin-comment">${commentCount} 条</a></em>
				</p>
			</div>
		</fieldset>
		<#if (recentComments?size > 0)>
			<h2>最新评论</h2>
			<table cellspacing="0" cellpadding="0" border="0"><!-- Table -->
				<thead>
					<tr>
						<th>作者</th>
						<th>时间</th>
						<th>E-mail</th>
						<th>内容</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#assign i = 0>
					<#list recentComments as comment>
					<tr<#if i = 0> class="alt"<#assign i = 1><#else><#assign i = 0></#if>>
						<td>
							<#if comment.link?has_content>
								<a href="${comment.link}" target="_blank">${comment.author}</a>
							<#else>
								${comment.author}
							</#if>
						</td>
						<td><a href="admin-comment-edit-${comment.commentId}">${comment.postDate?string("yyyy-MM-dd HH:mm:ss")}</a></td>
						<td>${comment.email}</td>
						<td><a href="admin-comment-edit-${comment.commentId}">${comment.content}</a></td>
						<td><#if comment.stat = 2>正常<#else>屏蔽</#if></td>
						<td>
							<a href="admin-comment-edit-${comment.commentId}">
								<img src="${staticServePath}res/image/action_edit.png" alt="编辑" />
							</a>
							<a href="comment-${comment.commentId}" target="_blank">
								<img src="${staticServePath}res/image/folder.png" alt="查看" />
							</a>
						</td>
					</tr>
					</#list>
				</tbody>
			</table> <!-- END Table -->
		</#if>
		<#if (articles?size > 0)>
			<h2>最近文章草稿</h2>
			<table cellspacing="0" cellpadding="0" border="0"><!-- Table -->
				<thead>
					<tr>
						<th>标题</th>
						<th>时间</th>
						<th>标签</th>
						<th>点击</th>
						<th>回复</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#assign i = 0>
					<#list articles as article>
					<tr<#if i = 0> class="alt"<#assign i = 1><#else><#assign i = 0></#if>>
						<td><a href="admin-article-edit-${article.articleId}">${article.title}</a></td>
						<td>${article.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
						<td>${article.tag}</td>
						<td>${article.hit}</td>
						<td>${article.commentCount}</td>
						<td>
							<a href="admin-article-edit-${article.articleId}">
								<img src="${staticServePath}res/image/action_edit.png" alt="编辑" />
							</a>
							<a href="article/${article.permalink}" target="_blank">
								<img src="${staticServePath}res/image/folder.png" alt="查看" />
							</a>
						</td>
					</tr>
					</#list>
				</tbody>
			</table> <!-- END Table -->
		</#if>
	</div> <!-- END Content -->
</div> 	
<#include "footer.ftl">