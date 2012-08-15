<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>${actionName?if_exists}</h2>
		<#if err?default("")!="">
			<div class="err">
				<div class="err_icon"><!-- --></div>
				<a href="#" class="close" title="关闭">x</a>
				<div class="desc">
					<span>${err}</span>
					<p>实在抱歉，${err}, 请联系管理员。</p>
				</div>
			</div>
			<div class="clearboth"><!-- --></div>
			<div style="height:400px"><!-- --></div>
		<#else>
			<form id="itemForm" action="admin-${adminAction}<#if adminAction="category-edit">-${itemId?if_exists?c}</#if>.html" method="post">
				<fieldset><legend>分类信息</legend>
					<div class="input_field">
						<label for="itemName">分类名称</label>
						<input class="bigfield" name="itemName" type="text" value="${itemName?if_exists}" />
					</div>
					<div class="input_field">
					<label for="permalink">永久链接</label>
					<span class="field_desc">${staticServePath}category/</span>
					<input class="mediumfield" name="permalink" type="text" value="${permalink?if_exists}" />
					<span class="field_desc">/</span>
				</div>
					<div class="input_field">
						<label for="description">分类简介</label>
						<input class="bigfield" name="description" type="text" value="${description?if_exists}" />
					</div>
					<div class="input_field">
						<label for="itemOrder">分类排序</label>
						<input class="smallfield" name="itemOrder" type="text" value="${itemOrder?if_exists}" />
					</div>
				</fieldset>
				<fieldset><legend>保存分类</legend>
					<div class="input_field no_margin_bottom">
						<input class="submit" type="submit" value="保存链接" />
					</div>
				</fieldset>
			</form>
			<script type="text/javascript">
				$("#itemForm").validate({
					rules: {
						itemName: {
							required:true,
							maxlength:200
						},
						permalink: {
							required:true,
							maxlength:150
						},
						description: {
							required:true,
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
		</#if>
	</div> <!-- END Content -->
</div> 	
<#include "footer.ftl">