<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>基本设置</h2>
		<#if err="succ">
			<div class="succes">
				<div class="succes_icon"><!-- --></div>
				<a href="#" class="close" title="关闭">x</a>
				<div class="desc">
					<span>设置保存成功!</span>
					<p>恭喜您，您的设置已成功保存。</p>
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
		<form id="setupForm" action="admin-${adminAction}.html" method="post">
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
					<label for="metaKeywords">关键字</label>
					<input class="bigfield" name="metaKeywords" type="text" value="${metaKeywords?if_exists}" />
					<span class="field_desc">提供给搜索引擎索引用, 逗号分隔</span>
				</div>
				<div class="input_field">
					<label for="metaDescription">简介</label>
					<input class="bigfield" name="metaDescription" type="text" value="${metaDescription?if_exists}" />
					<span class="field_desc">通常显示在搜索引擎结果页面上</span>
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
	</div> <!-- END Content -->
</div> 	
<#include "footer.ftl">