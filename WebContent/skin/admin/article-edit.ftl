<#include "macro-message.ftl">
<#include "macro-form.ftl">
<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>${actionName?if_exists}</h2>
		<div class="clearboth"><!-- --></div>
		<form id="editForm" action="admin-${adminAction}" method="post">
			<fieldset><legend>文章信息</legend>
				<div class="input_field">
					<label for="title">文章标题</label>
					<input class="bigfield" name="title" type="text" value="${article.title?if_exists}" />
				</div>
				<div class="input_field">
					<label for="permalink">永久链接</label>
					<span class="field_desc">${staticServePath}article/</span>
					<input class="mediumfield" name="permalink" type="text" value="${article.permalink?if_exists}" />
					<span class="field_desc">/</span>
				</div>
				<div class="input_field">
					<label for="tag">文章标签</label>
					<input class="bigfield" name="tag" type="text" value="${article.tag?if_exists}" />
					<span class="field_desc">使用逗号或空格分隔，每个标签不长于18字节</span>
				</div>
				<div class="input_field">
					<label for="permalink">文章分类</label>
					<span class="category">
						<#list categories as category>
							<input type="checkbox" name="category" value="${category.itemId}" <#if category.stat=2>checked="checked" </#if>/>
							${category.itemName} &nbsp;
						</#list>
					</span>
				</div>
			</fieldset>
			<fieldset><legend>文章正文</legend>
				<div class="input_field">
					<textarea class="wysiwyg" name="content" style="width:100%;">${article.content?if_exists}</textarea>
				</div>
			</fieldset>
			<@message type="文章">
				恭喜您，您的文章已成功保存，您可以继续编辑，也可以 <a class="button" href="${staticServePath}article/${article.permalink?if_exists}" target="_blank">查看效果</a>。
			</@message>
			<fieldset><legend>发布选项</legend>
				<div class="input_field no_margin_bottom">
					<span class="form_line"><input type="checkbox" name="commentStatus" class="checkbox" value="1" <#if article.commentStatus=1>checked="checked" </#if>/>允许评论</span>
					<span class="form_line"><input type="radio" name="stat" class="radio" value="4" <#if article.stat=4>checked="checked" </#if>/>已发布</span>
					<span class="form_line"><input type="radio" name="stat" class="radio" value="2" <#if article.stat=2>checked="checked" </#if>/>草稿</span>
					<span class="form_line"><input type="radio" name="stat" class="radio" value="1" <#if article.stat=1>checked="checked" </#if>/>回收站</span>
					<@submit>保存文章</@submit>
				</div>
			</fieldset>
		</form>
		<script type="text/javascript">
			$("#editForm").validate({
				rules: {
					title: {
						required:true,
						maxlength:340
					},
					permalink: {
						maxlength:150
					},
					tag: {
						maxlength:150
					}
				}
			});
		</script>
	</div> <!-- END Content -->
</div> 	
<#include "footer.ftl">