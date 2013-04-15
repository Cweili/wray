<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>${actionName?if_exists}</h2>
		<#if (comments?size > 0)>
		<form id="deleteForm" action="admin-comment-block" method="post">
			<table cellspacing="0" cellpadding="0" border="0"><!-- Table -->
				<thead>
					<tr>
						<th><input type="checkbox" class="checkall" /></th>
						<th>作者</th>
						<th>时间</th>
						<th>E-mail</th>
						<th>链接</th>
						<th>内容</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#assign i = 0>
					<#list comments as comment>
					<tr<#if i = 0> class="alt"<#assign i = 1><#else><#assign i = 0></#if>>
						<td><input type="checkbox" name="id" value="${comment.commentId}" /></td>
						<td>${comment.author}</td>
						<td><a href="admin-comment-edit-${comment.commentId}">${comment.postDate?string("yyyy-MM-dd HH:mm:ss")}</a></td>
						<td>${comment.email}</td>
						<td>${comment.link}</td>
						<td><a href="admin-comment-edit-${comment.commentId}">${comment.content}</a></td>
						<td><#if comment.stat = 2>正常<#else>屏蔽</#if></td>
						<td>
							<a href="admin-comment-edit-${comment.commentId}">
								<img src="${staticServePath}include/image/action_edit.png" alt="编辑" />
							</a>
							<a href="javascript:void(0)" onclick="deleteSingle('${comment.commentId}');">
								<img src="${staticServePath}include/image/action_delete.png" alt="删除" />
							</a>
						</td>
					</tr>
					</#list>
					<#assign i = (adminListSize - comments?size)>
					<#if (i > 0)>
						<#list 1..i as t>
						<tr><td colspan="8" style="color:#242424">.</td></tr>
						</#list>
					</#if>
				</tbody>
			</table> <!-- END Table -->
			<fieldset>
				<div class="input_field no_margin_bottom">
					<input class="submit" type="submit" value="屏蔽选中评论" />
				</div>
			</fieldset>
			<input id="deleteId" name="id" type="hidden" />
			<input name="page" value="${paginationCurrentPageNum}" type="hidden" />
		</form>
		<#include "pagination.ftl">
		<#else>
			<div class="warning">
				<div class="warn_icon"><!-- --></div>
				<div class="desc">
					<span>${actionName?if_exists}列表是空的呢</span>
				</div>
			</div>
			<div class="clearboth"><!-- --></div>
		</#if>
	</div> <!-- END Content -->
</div>
<#include "footer.ftl">