<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>${actionName?if_exists}</h2>
		<#if (links?size > 0)>
		<script type="text/javascript">
			function deleteSingle(id) {
				$("#deleteId").val(id);
				$("#deleteForm").submit();
			};
		</script>
		<form id="manageForm" action="admin-link-manage.html" method="post">
			<table cellspacing="0" cellpadding="0" border="0"><!-- Table -->
				<thead>
					<tr>
						<th><input type="checkbox" class="checkall" /></th>
						<th>名称</th>
						<th>日志数</th>
						<th style="width:180px;">排序</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#assign i = 0>
					<#list links as link>
					<tr<#if i = 0> class="alt"<#assign i = 1><#else><#assign i = 0></#if>>
						<td><input type="checkbox" name="id" value="${link.itemId?c}" /></td>
						<td><a href="admin-link-edit-${link.itemId?c}.html">${link.itemName}</a></td>
						<td>${link.count}</td>
						<td><input class="tinyfield" type="text" name="order${link.itemId?c}" value="${link.itemOrder}" /></td>
						<td>
							<a href="admin-link-edit-${link.itemId?c}.html"><img src="${staticServePath}${skinDir}assets/action_edit.png" alt="编辑" /></a>
							<a href="javascript:void(0)" onclick="deleteSingle(${link.itemId?c});"><img src="${staticServePath}${skinDir}assets/action_delete.png" alt="删除" /></a>
							<a href="${link.description}" target="_blank"><img src="${staticServePath}${skinDir}assets/folder.png" alt="查看" /></a>
						</td>
					</tr>
					</#list>
					<#assign i = (12 - links?size)>
					<#if (i > 0)>
						<#list 1..i as t>
						<tr><td colspan="5" style="color:#242424">.</td></tr>
						</#list>
					</#if>
				</tbody>
			</table> <!-- END Table -->
			<fieldset>
				<div class="input_field no_margin_bottom">
					<input class="submit" type="submit" value="更新排序并删除选中分类" />
					<input class="submit" type="button" value="添加新的分类" onclick="location.href='admin-link-add.html'" />
				</div>
			</fieldset>
			<input id="deleteId" name="id" type="hidden" />
		</form>
		<script type="text/javascript">
			$("#manageForm").validate({
				rules: {
					<#list links as link>
					order${link.itemId?c}: {
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
					<p>赶快 <a class="button" href="admin-link-add.html">添加新的分类</a> 吧!</p>
				</div>
			</div>
			<div class="clearboth"><!-- --></div>
		</#if>
	</div> <!-- END Content -->
</div>
<#include "footer.ftl">