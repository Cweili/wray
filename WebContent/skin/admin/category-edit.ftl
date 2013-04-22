<#include "macro-message.ftl">
<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>${actionName?if_exists}</h2>
		<form id="editForm" action="admin-${adminAction}" method="post">
			<fieldset><legend>分类信息</legend>
				<div class="input_field">
					<label for="itemName">分类名称</label>
					<input class="bigfield" name="itemName" type="text" value="${category.itemName?if_exists}" />
				</div>
				<div class="input_field">
				<label for="permalink">永久链接</label>
				<span class="field_desc">${staticServePath}category/</span>
				<input class="mediumfield" name="permalink" type="text" value="${category.permalink?if_exists}" />
				<span class="field_desc">/</span>
			</div>
				<div class="input_field">
					<label for="description">关键词</label>
					<textarea name="description">${category.description?if_exists}</textarea>
				</div>
				<div class="input_field">
					<label for="itemOrder">分类排序</label>
					<input class="smallfield" name="itemOrder" type="text" value="${category.itemOrder?if_exists}" />
				</div>
			</fieldset>
			<@message type="文章">
				恭喜您，您的分类已成功保存，您可以继续编辑，也可以 <a class="button" href="${staticServePath}category/${category.permalink?if_exists}" target="_blank">查看效果</a>。
			</@message>
			<fieldset><legend>保存分类</legend>
				<div class="input_field no_margin_bottom">
					<input class="submit" type="submit" value="保存分类" />
				</div>
			</fieldset>
		</form>
		<script type="text/javascript">
			$("#editForm").validate({
				rules: {
					itemName: {
						required:true,
						maxlength:200
					},
					permalink: {
						maxlength:150
					},
					description: {
						maxlength:512
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