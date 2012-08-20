<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>${actionName?if_exists}</h2>
		<#if (articles?size > 0)>
		<script type="text/javascript">
			function deleteSingle(id) {
				$("#deleteId").val(id);
				$("#manageForm").submit();
			};
		</script>
		<form id="manageForm" action="admin-page-manage-${adminAction?replace('page-','')}.html?page=${paginationCurrentPageNum}" method="post">
			<table cellspacing="0" cellpadding="0" border="0"><!-- Table -->
				<thead>
					<tr>
						<th><input type="checkbox" class="checkall" /></th>
						<th>标题</th>
						<th>时间</th>
						<th>回复</th>
						<th style="width:180px;">排序</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#assign i = 0>
					<#list articles as article>
					<tr<#if i = 0> class="alt"<#assign i = 1><#else><#assign i = 0></#if>>
						<td><input type="checkbox" name="id" value="${article.articleId?c}" /></td>
						<td><a href="admin-page-edit-${article.articleId?c}.html">${article.title}</a></td>
						<td>${article.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
						<td>${article.commentCount}</td>
						<td><input class="tinyfield" type="text" name="order${article.articleId?c}" value="${article.hits}" /></td>
						<td>
							<a href="admin-page-edit-${article.articleId?c}.html"><img src="${staticServePath}include/image/action_edit.png" alt="编辑" /></a>
							<a href="javascript:void(0)" onclick="deleteSingle(${article.articleId?c});"><img src="${staticServePath}include/image/action_delete.png" alt="删除" /></a>
							<a href="page/${article.permalink}/" target="_blank"><img src="${staticServePath}include/image/folder.png" alt="查看" /></a>
						</td>
					</tr>
					</#list>
					<#assign i = (adminListSize - articles?size)>
					<#if (i > 0)>
						<#list 1..i as t>
						<tr><td colspan="6" style="color:#242424">.</td></tr>
						</#list>
					</#if>
				</tbody>
			</table> <!-- END Table -->
			<fieldset>
				<div class="input_field no_margin_bottom">
					<input class="submit" type="submit" value="更新排序并删除选中页面" />
					<input class="submit" type="button" value="添加新的页面" onclick="location.href='admin-page-add.html'" />
				</div>
			</fieldset>
			<input id="deleteId" name="id" type="hidden" />
		</form>
		<script type="text/javascript">
			$("#manageForm").validate({
				rules: {
					<#list articles as article>
					order${article.articleId?c}: {
						required:true,
						digits:true,
						range:[0,99]
					},
					</#list>
				}
			});
		</script>
		<#include "pagination.ftl">
		<#else>
			<div class="warning">
				<div class="warn_icon"><!-- --></div>
				<div class="desc">
					<span>${actionName?if_exists}列表是空的呢</span>
					<p>赶快 <a class="button" href="admin-page-add.html">添加新的页面</a> 吧!</p>
				</div>
			</div>
			<div class="clearboth"><!-- --></div>
		</#if>
	</div> <!-- END Content -->
</div>
<#include "footer.ftl">