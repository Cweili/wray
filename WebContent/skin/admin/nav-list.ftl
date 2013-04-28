<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>${actionName?if_exists}</h2>
		<form id="manageForm" action="admin-nav-manage" method="post">
			<fieldset><legend>导航设置</legend>
				<div class="input_field">
					<label for="navigatorSwitch">自定义导航</label>
					<span class="form_line">
						<input type="radio" name="navigatorSwitch" class="radio" value="1" <#if navigatorSwitch="1">checked="checked" </#if>/>
						打开
					</span>
					<span class="form_line">
						<input type="radio" name="navigatorSwitch" class="radio" value="0" <#if navigatorSwitch="0">checked="checked" </#if>/>
						关闭
					</span>
				</div>
			</fieldset>
			<#if (items?size > 0)>
				<table cellspacing="0" cellpadding="0" border="0"><!-- Table -->
					<thead>
						<tr>
							<th><input type="checkbox" class="checkall" /></th>
							<th>名称</th>
							<th>地址</th>
							<th style="width:180px;">排序</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<#assign i = 0>
						<#list items as item>
						<tr<#if i = 0> class="alt"<#assign i = 1><#else><#assign i = 0></#if>>
							<td><input type="checkbox" name="id" value="${item.itemId}" /></td>
							<td><a href="admin-link-edit-${item.itemId}">${item.itemName}</a></td>
							<td>${item.description}</td>
							<td><input class="tinyfield" type="text" name="order${item.itemId}" value="${item.itemOrder}" /></td>
							<td>
								<a href="admin-link-edit-${item.itemId}">
									<img src="${staticServePath}res/image/action_edit.png" alt="编辑" />
								</a>
								<a href="javascript:void(0);" onclick="manageSingle('${item.itemId}');">
									<img src="${staticServePath}res/image/action_delete.png" alt="删除" />
								</a>
								<a href="${item.description}" target="_blank">
									<img src="${staticServePath}res/image/folder.png" alt="查看" />
								</a>
							</td>
						</tr>
						</#list>
						<#assign i = (12 - items?size)>
						<#if (i > 0)>
							<#list 1..i as t>
							<tr><td colspan="5" style="color:#242424">.</td></tr>
							</#list>
						</#if>
					</tbody>
				</table> <!-- END Table -->
			<#else>
				<div class="warning">
					<div class="warn_icon"><!-- --></div>
					<div class="desc">
						<span>${actionName?if_exists}列表是空的呢</span>
						<p>赶快 <a class="button" href="admin-nav-add">添加新的导航</a> 吧!</p>
					</div>
				</div>
				<div class="clearboth"><!-- --></div>
			</#if>
			<fieldset>
				<div class="input_field no_margin_bottom">
					<input class="submit" type="submit" value="保存导航设置" />
					<input class="submit" type="button" value="添加新的导航" onclick="location.href='admin-nav-add'" />
				</div>
			</fieldset>
			<input id="manageId" name="id" type="hidden" />
		</form>
		<script type="text/javascript">
			$("#manageForm").validate({
				rules: {
					<#list items as item>
					order${item.itemId}: {
						required:true,
						digits:true,
						range:[0,99]
					},
					</#list>
				}
			});
		</script>
	</div> <!-- END Content -->
</div>
<#include "footer.ftl">