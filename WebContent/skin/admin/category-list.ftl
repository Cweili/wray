<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>${actionName?if_exists}</h2>
		<#if (items?size > 0)>
		<form id="manageForm" action="admin-category-manage" method="post">
			<table cellspacing="0" cellpadding="0" border="0"><!-- Table -->
				<thead>
					<tr>
						<th><input type="checkbox" class="checkall" /></th>
						<th>名称</th>
						<th>日志数</th>
						<th style="width:180px;">排序</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#assign i = 0>
					<#list items as item>
					<tr<#if i = 0> class="alt"<#assign i = 1><#else><#assign i = 0></#if>>
						<td><input type="checkbox" name="id" value="${item.itemId}" /></td>
						<td><a href="admin-link-edit-${item.itemId}">${item.itemName}</a></td>
						<td>${item.count}</td>
						<td><input class="tinyfield" type="text" name="order${item.itemId}" value="${item.itemOrder}" /></td>
						<td>
							<a href="javascript:void(0);" onclick="manageSingle('${item.itemId}');">
								<img src="${staticServePath}res/image/action_<#if item.stat = 1>check<#else>delete</#if>.png"
								alt="<#if item.stat = 1>正常<#else>关闭</#if>" />
							</a>
						</td>
						<td>
							<a href="admin-category-edit-${item.itemId}">
								<img src="${staticServePath}res/image/action_edit.png" alt="编辑" />
							</a>
							<a href="category/${item.permalink}" target="_blank">
								<img src="${staticServePath}res/image/folder.png" alt="查看" />
							</a>
						</td>
					</tr>
					</#list>
					<#assign i = (12 - items?size)>
					<#if (i > 0)>
						<#list 1..i as t>
						<tr><td colspan="6" style="color:#242424">.</td></tr>
						</#list>
					</#if>
				</tbody>
			</table> <!-- END Table -->
			<fieldset>
				<div class="input_field no_margin_bottom">
					<input class="submit" type="submit" value="更新分类排序与状态" />
					<input class="submit" type="button" value="添加新的分类" onclick="location.href='admin-category-add'" />
				</div>
			</fieldset>
			<input id="manageId" name="id" type="hidden" />
		</form>
		<script type="text/javascript">
			$("#manageForm").validate({
				rules: {
					<#list items as item>
					order${item.itemId}: {
						required:true,
						digits:true,
						range:[0,99]
					},
					</#list>
				}
			});
		</script>
		<#else>
			<div class="warning">
				<div class="warn_icon"><!-- --></div>
				<div class="desc">
					<span>${actionName?if_exists}列表是空的呢</span>
					<p>赶快 <a class="button" href="admin-category-add">添加新的分类</a> 吧!</p>
				</div>
			</div>
			<div class="clearboth"><!-- --></div>
		</#if>
	</div> <!-- END Content -->
</div>
<#include "footer.ftl">