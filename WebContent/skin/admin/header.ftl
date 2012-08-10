<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN" xml:lang="zh-CN">
<head>
	<title>Wray Admin Panel</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--base href="${staticServePath}" /-->
	<style type="text/css" media="all">
		@import url('${staticServePath}${skinDir}css/style.css');
		<#--@import url('${staticServePath}${skinDir}css/jquery.wysiwyg.css');-->
		<#--@import url('${staticServePath}include/editor/plugins/code/prettify.css');-->
		img {behavior:url('js/iepngfix.htc') !important;}
		.ke-icon-insertmore {background-image: url(default.png);background-position: 0px -1024px;width: 16px;height: 16px;}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
	<script src="${staticServePath}${skinDir}js/jquery-ui-1.8.22.custom.min.js" type="text/javascript"></script>
	<#--
	<script src="${staticServePath}${skinDir}js/jquery.wysiwyg.js" type="text/javascript"></script>
	<script src="${staticServePath}${skinDir}js/wysiwyg.image.js" type="text/javascript"></script>
	<script src="${staticServePath}${skinDir}js/wysiwyg.link.js" type="text/javascript"></script>
	<script src="${staticServePath}${skinDir}js/wysiwyg.table.js" type="text/javascript"></script>
	-->
	<script src="${staticServePath}include/editor/kindeditor-min.js" type="text/javascript"></script>
	<script src="${staticServePath}include/editor/zh_CN.js" type="text/javascript"></script>
	<#--<script src="${staticServePath}include/editor/plugins/code/prettify.js"></script>-->
	<script src="${staticServePath}${skinDir}js/functions.js" type="text/javascript"></script>

</head>
<body>
<div id="container"> <!-- Container -->
	<div id="header"> <!-- Header -->
		<div id="title">
			Wray Admin Panel
			<span>${blogTitle?if_exists} 系统管理</span>
		</div>
		<div class="logged">
			<p style="height:40px"><!-- --></p>
			<p style="font-size:16px">欢迎您, ${adminNick}! | <a href="${staticServePath}" target="_blank">博客首页</a> | <a href="#" >退出登录</a></p>
		</div>
	</div>
	<#include "sidebar.ftl">