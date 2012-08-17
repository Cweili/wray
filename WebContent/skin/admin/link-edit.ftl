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
			<form id="itemForm" action="admin-${adminAction}<#if adminAction="link-edit">-${itemId?if_exists?c}</#if>.html" method="post">
				<fieldset><legend>链接信息</legend>
					<div class="input_field">
						<label for="itemName">链接名称</label>
						<input class="bigfield" name="itemName" type="text" value="${itemName?if_exists}" />
					</div>
					<div class="input_field">
						<label for="description">链接地址</label>
						<textarea name="description">${description?if_exists}</textarea>
					</div>
					<div class="input_field">
						<label for="itemOrder">链接排序</label>
						<input class="smallfield" name="itemOrder" type="text" value="${itemOrder?if_exists}" />
					</div>
				</fieldset>
				<fieldset><legend>保存链接</legend>
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
		</#if>
	</div> <!-- END Content -->
</div> 	
<#include "footer.ftl">