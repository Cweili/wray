<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN" xml:lang="zh-CN">
<head>
	<title>${blogTitle?if_exists} 管理面板 - Wray Admin Panel</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="${staticServePath}" />
	<link rel="stylesheet" href="${staticServePath}include/css/admin-style.css" type="text/css" charset="utf-8" />
	<script src="${staticServePath}include/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="${staticServePath}include/js/jquery-ui-1.10.2.min.js" type="text/javascript"></script>
	<script src="${staticServePath}include/js/jquery.validate.min.js" type="text/javascript"></script>
	<#--
	<script src="${staticServePath}include/js/jquery.wysiwyg.js" type="text/javascript"></script>
	<script src="${staticServePath}include/js/wysiwyg.image.js" type="text/javascript"></script>
	<script src="${staticServePath}include/js/wysiwyg.link.js" type="text/javascript"></script>
	<script src="${staticServePath}include/js/wysiwyg.table.js" type="text/javascript"></script>
	-->
	<script src="${staticServePath}include/editor/kindeditor-min.js" type="text/javascript"></script>
	<script src="${staticServePath}include/editor/zh_CN.js" type="text/javascript"></script>
	<#--<script src="${staticServePath}include/editor/plugins/code/prettify.js"></script>-->
	<script src="${staticServePath}include/js/admin-functions.js" type="text/javascript"></script>

</head>
<body>
<div id="container"> <!-- Container -->
	<div id="header"> <!-- Header -->
		<div id="title">
			Wray Admin Panel
			<span>${blogTitle?if_exists} 管理面板</span>
		</div>
		<div class="logged">
			<p style="height:40px"><!-- --></p>
			<p style="font-size:16px">
				欢迎您, ${adminNick}! | <a href="${staticServePath}" target="_blank">博客首页</a> | <a href="admin-logout-${authority?if_exists}" >退出登录</a>
			</p>
		</div>
	</div>
	<#include "sidenav.ftl">