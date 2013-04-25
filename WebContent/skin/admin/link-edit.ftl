<#include "macro-form.ftl">
<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>${actionName?if_exists}</h2>
		<form id="editForm" action="admin-${adminAction}" method="post">
			<fieldset><legend>链接信息</legend>
				<div class="input_field">
					<label for="itemName">链接名称</label>
					<input class="bigfield" name="itemName" type="text" value="${link.itemName?if_exists}" />
				</div>
				<div class="input_field">
					<label for="description">链接地址</label>
					<textarea name="description">${link.description?if_exists}</textarea>
				</div>
				<div class="input_field">
					<label for="itemOrder">链接排序</label>
					<input class="smallfield" name="itemOrder" type="text" value="${link.itemOrder?if_exists}" />
				</div>
			</fieldset>
			<@message type="链接">
				恭喜您，您的链接已成功保存。
			</@message>
			<fieldset><legend>保存链接</legend>
				<div class="input_field no_margin_bottom">
					<@submit>保存链接</@submit>
				</div>
			</fieldset>
		</form>
		<script type="text/javascript">
			$("#editForm").validate({
				rules: {
					itemName: {
						required:true,
						maxlength:150
					},
					description: {
						required:true,
						maxlength:682
					},
					itemOrder: {
						required:true,
						digits:true,
						range:[0,99]
					}
				}
			});
		</script>
	</div> <!-- END Content -->
</div> 	
<#include "footer.ftl">