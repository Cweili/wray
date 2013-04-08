<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>${actionName?if_exists}</h2>
		<#if err="succ">
			<div class="succes">
				<div class="succes_icon"><!-- --></div>
				<a href="#" class="close" title="关闭">x</a>
				<div class="desc">
					<span>页面保存成功!</span>
					<p>恭喜您，您的页面已成功保存，您可以继续编辑，也可以 <a class="button" href="${staticServePath}page/${permalink?if_exists}/" target="_blank">查看效果</a>。</p>
				</div>
			</div>
			<div class="clearboth"><!-- --></div>
		<#elseif err!="">
			<div class="err">
				<div class="err_icon"><!-- --></div>
				<a href="#" class="close" title="关闭">x</a>
				<div class="desc">
					<span>${err}</span>
					<p>实在抱歉，${err}, 请联系管理员。</p>
				</div>
			</div>
			<div class="clearboth"><!-- --></div>
		</#if>
		<form id="articleForm" action="admin/${adminAction}/" method="post">
			<fieldset><legend>页面信息</legend>
				<div class="input_field">
					<label for="title">页面标题</label>
					<input class="bigfield" name="title" type="text" value="${title?if_exists}" />
				</div>
				<div class="input_field">
					<label for="permalink">永久链接</label>
					<span class="field_desc">${staticServePath}page/</sapn>
					<input class="mediumfield" name="permalink" type="text" value="${permalink?if_exists}" />
					<span class="field_desc">/</span>
				</div>
			</fieldset>
			<fieldset><legend>页面正文</legend>
				<div class="input_field">
					<textarea class="wysiwyg" name="content" style="width:100%;">${content?if_exists}</textarea>
				</div>
			</fieldset>
			<fieldset><legend>保存选项</legend>
				<div class="input_field no_margin_bottom">
					<span class="form_line"><input type="checkbox" name="commentStatus" class="checkbox" value="1" <#if commentStatus=1>checked="checked" </#if>/>允许评论</span>
					<span class="form_line"><input type="radio" name="stat" class="radio" value="4" <#if stat=4>checked="checked" </#if>/>公开</span>
					<span class="form_line"><input type="radio" name="stat" class="radio" value="2" <#if stat=2>checked="checked" </#if>/>私密</span>
					<span class="form_line"><input type="radio" name="stat" class="radio" value="1" <#if stat=1>checked="checked" </#if>/>回收站</span>
					<span class="form_line"><input class="submit" type="submit" value="保存页面" /></span>
				</div>
			</fieldset>
		</form>
		<script type="text/javascript">
			$("#articleForm").validate({
				rules: {
					title: {
						required:true,
						maxlength:340
					},
					permalink: {
						maxlength:150
					}
				}
			});
		</script>
	</div> <!-- END Content -->
</div> 	
<#include "footer.ftl">