<#include "macro-message.ftl">
<#include "macro-form.ftl">
<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>${actionName?if_exists}</h2>
		<div class="clearboth"><!-- --></div>
		<form id="editForm" action="admin-${adminAction}" method="post">
			<fieldset><legend>作者信息</legend>
				<div class="input_field">
					<label for="author">作者</label>
					<input class="bigfield" name="author" type="text" value="${comment.author?if_exists}" />
				</div>
				<div class="input_field">
					<label for="email">E-mail</label>
					<span class="field_desc">${staticServePath}article/</span>
					<input class="mediumfield" name="email" type="text" value="${comment.email?if_exists}" />
					<span class="field_desc">/</span>
				</div>
				<div class="input_field">
					<label for="link">链接</label>
					<input class="bigfield" name="link" type="text" value="${comment.link?if_exists}" />
					<span class="field_desc">使用逗号或空格分隔，每个标签不长于18字节</span>
				</div>
			</fieldset>
			<fieldset><legend>评论内容</legend>
				<div class="input_field">
					<textarea class="wysiwyg" name="content" style="width:100%;">${comment.content?if_exists}</textarea>
				</div>
			</fieldset>
			<@message type="评论">
				恭喜您，您的评论已编辑成功。
			</@message>
			<fieldset><legend>评论状态</legend>
				<div class="input_field no_margin_bottom">
					<span class="form_line"><input type="radio" name="stat" class="radio" value="2" <#if comment.stat=2>checked="checked" </#if>/>正常</span>
					<span class="form_line"><input type="radio" name="stat" class="radio" value="1" <#if comment.stat=1>checked="checked" </#if>/>屏蔽</span>
					<@submit>保存评论</@submit>
				</div>
			</fieldset>
		</form>
		<script type="text/javascript">
			$("#editForm").validate({
				rules: {
					author: {
						required:true,
						maxlength:200
					},
					email: {
						required:true,
						email:true,
						maxlength:200
					},
					link: {
						maxlength:200
					}
				}
			});
		</script>
	</div> <!-- END Content -->
</div> 	
<#include "footer.ftl">