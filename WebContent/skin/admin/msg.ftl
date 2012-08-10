<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>${actionName?if_exists}</h2>
		<#if err="succ">
			<div class="succes">
				<div class="succes_icon"><!-- --></div>
				<div class="desc">
					<span>${msg?if_exists}</span>
					<p>${succ?if_exists}</p>
				</div>
			</div>
			<div class="clearboth"><!-- --></div>
		<#elseif err!="">
			<div class="err">
				<div class="err_icon"><!-- --></div>
				<div class="desc">
					<span>${msg?if_exists}</span>
					<p>实在抱歉，${err}，请联系管理员。</p>
				</div>
			</div>
			<div class="clearboth"><!-- --></div>
		</#if>
		<div style="height:400px"><!-- --></div>
	</div> <!-- END Content -->
	<#if redirect?default('') != ''>
		<script type="text/javascript">
			setTimeout('window.location.href="${staticServePath}${redirect?default('')}.html"', 3000);
		</script>
	</#if>
</div> 	
<#include "footer.ftl">