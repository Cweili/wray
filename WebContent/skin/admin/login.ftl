<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Wray Admin Panel</title>
		<link type="text/css" rel="stylesheet" href="${staticServePath}${skinDir}css/login.css" charset="utf-8" />
	</head>
<body>
	<div id="container">
		<h1>${blogTitle?if_exists}管理面板 - Wray Admin Panel</h1>
		<div id="box">
			<form action="admin-login.html" method="post">
			<p class="main">
				<label>用户名: </label>
				<input name="username" value="${username?if_exists}" /> 
				<label>密码: </label>
				<input type="password" name="password" value="" />	
			</p>
			<p class="space">
				<span><input type="checkbox" name="rememberme" value="1" />记住我</span>
				<input type="submit" value="登 &nbsp; 录" class="login" />
			</p>
			</form>
			<#if err>
			<br />
			<div class="err">
				<div class="err_icon"><!-- --></div>
				<div class="desc">
					<span>Error!</span>
					<p>请确认您的用户名和密码正确.</p>
				</div>
			</div>
			</#if>
		</div>
	</div>
</body>
</html>