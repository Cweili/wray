<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Wray Admin Panel</title>
		<link type="text/css" rel="stylesheet" href="${staticServePath}include/css/admin-login.css" charset="utf-8" />
		<script src="${staticServePath}include/js/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="${staticServePath}include/js/admin-login.js" type="text/javascript"></script>
	</head>
<body>
	<div id="container">
		<h1>${blogTitle?if_exists}管理面板 - Wray Admin Panel</h1>
		<div id="box">
			<form id="admin-login" action="admin-login" method="post">
			<p class="main">
				<label>用户名: </label>
				<input name="username" value="${username?if_exists}" /> 
				<label>密码: </label>
				<input id="password" type="password" name="password" />	
			</p>
			<p class="space">
				<span><input type="checkbox" name="rememberme" value="true" />记住我</span>
				<input type="submit" value="登 &nbsp; 录" class="login" />
			</p>
			<input id="hash" type="hidden" name="hash" />
			</form>
			<br />
			<div class="err">
				<div class="err_icon"><!-- --></div>
				<div class="desc">
					<span>Error!</span>
					<p>请确认您的用户名和密码正确.</p>
				</div>
			</div>
			<div class="succes">
				<div class="succes_icon"><!-- --></div>
				<div class="desc">
					<span>Success!</span>
					<p>登录成功！正在进入管理页面.</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>