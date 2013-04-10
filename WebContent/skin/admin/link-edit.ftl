<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>${actionName?if_exists}</h2>
		<div class="succes hide">
			<div class="succes_icon"><!-- --></div>
			<a href="#" class="close" title="关闭">x</a>
			<div class="desc">
				<span>链接保存成功!</span>
				<p>恭喜您，您的链接已成功保存。</p>
			</div>
		</div>
		<div class="clearboth"><!-- --></div>
		<div class="err hide">
			<div class="err_icon"><!-- --></div>
			<a href="#" class="close" title="关闭">x</a>
			<div class="desc">
				<span>链接保存失败</span>
				<p>实在抱歉，数据库更新失败, 请联系管理员。</p>
			</div>
		</div>
		<form id="editForm" action="admin-${adminAction}" method="post">
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