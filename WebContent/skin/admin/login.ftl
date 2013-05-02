<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Wray Admin Panel</title>
		<link type="text/css" rel="stylesheet" href="${staticServePath}res/css/admin-login.css" charset="utf-8" />
		<script src="${staticServePath}res/js/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="${staticServePath}res/js/admin-login.js" type="text/javascript"></script>
	</head>
<body>
	<div id="container">
		<h1>${blogTitle?if_exists}管理面板 - Wray Admin Panel</h1>
		<div id="box">
			<form id="admin-login" action="admin-login" method="post">
				<p class="main">
					<label for="username">用户名: </label>
					<input class="text" type="text" name="username" value="${username?if_exists}" tabindex="1" /> 
					<label for="password">密码: </label>
					<input class="text" type="password" name="password" tabindex="2" />	
				</p>
				<p class="main space">
					<label for="captcha">验证码: </label>
					<input class="text" type="text" name="captcha" tabindex="3" />
					<img id="captcha" alt="validate" />
				</p>
				<p class="space"></p>
				<div id="rememberme"><input class="check" type="checkbox" name="rememberme" value="true" tabindex="4" />记住我</div>
				<input type="submit" value="登 &nbsp; 录" class="login" tabindex="5"  />
				<div id="loading"></div>
				<input id="hash" type="hidden" name="hash" />
				<div class=""></div>
			</form>
			<p class="space"></p>
			<div class="err">
				<div class="err_icon"><!-- --></div>
				<div class="desc">
					<span>Error!</span>
					<p></p>
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