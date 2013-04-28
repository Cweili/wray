<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN" xml:lang="zh-CN">
<head>
	<title>${blogTitle?if_exists} 管理面板 - Wray Admin Panel</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="${staticServePath}" />
	<link rel="stylesheet" type="text/css" href="${staticServePath}res/css/admin-style.css" />
	
	<script type="text/javascript">
		var authority = "${authority?if_exists}";
		var authorityTime = "${authorityTime?if_exists}";
	</script>
	
	<script type="text/javascript" src="${staticServePath}res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${staticServePath}res/js/jquery-ui-1.10.2.min.js"></script>
	<script type="text/javascript" src="${staticServePath}res/js/jquery.validate.min.js"></script>
	<#--
	<script src="${staticServePath}res/js/jquery.wysiwyg.js" type="text/javascript"></script>
	<script src="${staticServePath}res/js/wysiwyg.image.js" type="text/javascript"></script>
	<script src="${staticServePath}res/js/wysiwyg.link.js" type="text/javascript"></script>
	<script src="${staticServePath}res/js/wysiwyg.table.js" type="text/javascript"></script>
	-->
	<script type="text/javascript" src="${staticServePath}res/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${staticServePath}res/editor/zh_CN.js"></script>
	<#--
	<script type="text/javascript" src="${staticServePath}res/editor/plugins/code/prettify.js"></script>
	-->
	<script type="text/javascript" src="${staticServePath}res/js/admin-functions.js"></script>

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
				<span id="welcome">欢迎您</span>, ${adminNick} !&nbsp;|&nbsp;
				<a href="${staticServePath}" target="_blank">博客首页</a> &nbsp;|&nbsp;
				<a href="admin-logout-${authority?if_exists}" >退出登录</a>
			</p>
		</div>
	</div>
	<#include "sidenav.ftl">