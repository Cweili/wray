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
			<fieldset><legend>博客管理员帐户</legend>
				<div class="input_field">
					<label for="adminName">登录名</label>
					<input class="bigfield" name="adminName" type="text" value="${adminName?if_exists}" />
					<span class="field_desc">用于管理登录</span>
				</div>
				<div class="input_field">
					<label for="adminNick">显示名</label>
					<input class="bigfield" name="adminNick" type="text" value="${adminNick?if_exists}" />
					<span class="field_desc">用于博客页面显示</span>
				</div>
				<div class="input_field">
					<label for="adminPwd">旧密码</label>
					<input class="bigfield" name="adminPwd" type="password" value="" />
					<span class="field_desc">如果不修改密码, 请留空</span>
				</div>
				<div class="input_field">
					<label for="newPwd">新密码</label>
					<input class="bigfield" name="newPwd" type="password" value="" />
					<span class="field_desc">如果不修改密码, 请留空</span>
				</div>
				<div class="input_field">
					<label for="newPwd2">确认密码</label>
					<input class="bigfield" name="newPwd2" type="password" value="" />
					<span class="field_desc">再输入一遍新密码</span>
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