<#include "macro-message.ftl">
<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>基本设置</h2>
		<@message type="设置">
			恭喜您，您的设置已成功保存。
		</@message>
		<form id="editForm" action="admin-${adminAction}" method="post">
			<fieldset><legend>博客基本信息</legend>
				<div class="input_field">
					<label for="blogTitle">博客名称</label>
					<input class="bigfield" name="blogTitle" type="text" value="${blogTitle?if_exists}" />
				</div>
				<div class="input_field">
					<label for="blogSubtitle">副标题</label>
					<input class="bigfield" name="blogSubtitle" type="text" value="${blogSubtitle?if_exists}" />
				</div>
				<div class="input_field">
					<label for="blogHost">网站URL</label>
					<input class="bigfield" name="blogSubtitle" type="text" value="${blogHost?if_exists}" />
					<span class="field_desc">用于页脚版权链接</span>
				</div>
				<div class="input_field">
					<label for="metaKeywords">关键词</label>
					<textarea name="metaKeywords">${metaKeywords?if_exists}</textarea>
					<span class="field_desc">提供给搜索引擎索引用, 逗号分隔</span>
				</div>
				<div class="input_field">
					<label for="metaDescription">简介</label>
					<textarea name="metaDescription">${metaDescription?if_exists}</textarea>
					<span class="field_desc">通常显示在搜索引擎结果页面上</span>
				</div>
			</fieldset>
			<fieldset><legend>公告栏</legend>
				<div class="input_field">
					<textarea class="wysiwyg" name="noticeBoard" style="width:100%;height:100px;">${noticeBoard?if_exists}</textarea>
				</div>
			</fieldset>
			<fieldset><legend>页面附加HTML</legend>
				<div class="input_field">
					<label for="attachHeader">头部附加HTML</label>
					<textarea name="attachHeader">${attachHeader?if_exists}</textarea>
					<span class="field_desc">在页面头部添加的HTML内容, 通常为一些js插件代码</span>
				</div>
				<div class="input_field">
					<label for="attachFooter">尾部附加HTML</label>
					<textarea name="attachFooter">${attachFooter?if_exists}</textarea>
					<span class="field_desc">在页面尾部添加的HTML内容, 通常为一些js插件代码</span>
				</div>
				<div class="input_field">
					<label for="attachStat">统计代码</label>
					<textarea name="attachStat">${attachStat?if_exists}</textarea>
					<span class="field_desc">可以在页面尾部添加统计代码</span>
				</div>
			</fieldset>
			<fieldset><legend>保存设置</legend>
				<div class="input_field no_margin_bottom">
					<span class="form_line"><input class="submit" type="submit" value="保存设置" /></span>
				</div>
			</fieldset>
		</form>
		<script type="text/javascript">
			$("#editForm").validate({
				rules: {
					blogTitle: {
						required:true,
						maxlength:512
					},
					blogSubtitle: {
						maxlength:512
					},
					blogHost: {
						maxlength:512
					},
					metaKeywords: {
						maxlength:512
					},
					metaDescription: {
						maxlength:512
					},
					noticeBoard: {
						maxlength:512
					},
					attachHeader: {
						maxlength:512
					},
					attachFooter: {
						maxlength:512
					},
					attachStat: {
						maxlength:512
					}
				}
			});
		</script>
	</div> <!-- END Content -->
</div> 	
<#include "footer.ftl">