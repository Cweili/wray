<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>${actionName?if_exists}</h2>
		<#if (uploads?size > 0)>
		<script type="text/javascript">
			function deleteSingle(id) {
				$("#deleteId").val(id);
				$("#deleteForm").submit();
			};
		</script>
		<form id="deleteForm" action="admin/upload-delete/" method="post">
			<table cellspacing="0" cellpadding="0" border="0"><!-- Table -->
				<thead>
					<tr>
						<th><input type="checkbox" class="checkall" /></th>
						<th>文件名</th>
						<th>时间</th>
						<th>大小</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#assign i = 0>
					<#list uploads as upload>
					<tr<#if i = 0> class="alt"<#assign i = 1><#else><#assign i = 0></#if>>
						<td><input type="checkbox" name="id" value="${upload.uploadId}" /></td>
						<td><a href="${staticServePath}upload/${upload.uploadId}/${upload.filename}">${upload.filename}</a></td>
						<td>${upload.uploadDate?string("yyyy-MM-dd HH:mm:ss")}</td>
						<td>${(upload.length / 1024)?int} KB</td>
						<td>
							<a href="javascript:void(0)" onclick="deleteSingle('${upload.uploadId}');"><img src="${staticServePath}include/image/action_delete.png" alt="删除" /></a>
							<a href="${staticServePath}upload/${upload.uploadId}/${upload.filename}" target="_blank"><img src="${staticServePath}include/image/folder.png" alt="打开" /></a>
						</td>
					</tr>
					</#list>
					<#assign i = (12 - uploads?size)>
					<#if (i > 0)>
						<#list 1..i as t>
						<tr><td colspan="5" style="color:#242424">.</td></tr>
						</#list>
					</#if>
				</tbody>
			</table> <!-- END Table -->
			<fieldset>
				<div class="input_field no_margin_bottom">
					<input class="submit" type="submit" value="删除选中附件" />
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