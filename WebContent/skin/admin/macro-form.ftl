<#macro submit>
	<span class="form_line"><input class="submit" type="submit" value="<#nested>" /></span>
	<span class="form_line loading"></span>
</#macro>

<#macro message type>
	<div class="warning hide">
		<div class="warn_icon"><!-- --></div>
		<a href="#" class="close" title="关闭">x</a>
		<div class="desc">
			<span>${type}正在保存...</span>
			<p>${type}正在保存，请稍候...</p>
		</div>
	</div>
	<div class="clearboth"><!-- --></div>
	<div class="succes hide">
		<div class="succes_icon"><!-- --></div>
		<a href="#" class="close" title="关闭">x</a>
		<div class="desc">
			<span>${type}保存成功!</span>
			<p><#nested></p>
		</div>
	</div>
	<div class="clearboth"><!-- --></div>
	<div class="err hide">
		<div class="err_icon"><!-- --></div>
		<a href="#" class="close" title="关闭">x</a>
		<div class="desc">
			<span>${type}保存失败</span>
			<p>实在抱歉，${type}保存失败, 请联系管理员。</p>
		</div>
	</div>
</#macro>