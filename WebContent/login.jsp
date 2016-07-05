<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css" /> -->
<script>
	function sheetLoaded() {
		// Do something interesting; the sheet has been loaded
		alert("sa");
	}

	function sheetError() {
		alert("An error occurred loading the stylesheet!");
	}
</script>
<meta http-equiv="X-UA_compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<script src="lib/requirejs/require.js" data-main="js/login"></script>
<title>登陆页面</title>
<!-- <link rel="stylesheet" href="mystylesheet.css" onload="sheetLoaded()" onerror="sheetError()">
 -->
<style type="text/css">
html {
	height: 100%;
}

body {
	color: #999;
	font: 13px/1.4;
	margin: 0 auto;
	/* 	background: url("images/login_bg2.jpg") no-repeat center top; */
	height: 100%;
	min-heigth: min-height:100%;
	height: auto !important;
	background-size: cover;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
}

a {
	color: #4078c0;
	text-decoration: none;
}

a:hover {
	text-decoration: none;
}

a:active {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
	border: 0px;
}

label {
	display: block; 
	font-weight : normal;
	margin-bottom: 7px;
	font-size: 13px;
	font-weight: normal;
}

.login {
	margin: 0 auto;
	width: 340px;
	padding-top: 80px;
}

.form-header {
	/* background-color: transparent; */
	background-color: #fff;
	border-radius: 3px; color : #333;
	margin-bottom: 15px;
	padding: 5px 0;
	text-align: center;
	text-shadow: none;
	color: #333;
}

.form-body {
	border-radius: 5px;
	border: 1px solid #d8dee2;
	background-color: #fff;
	border-radius: 0 0 3px 3px;
	font-size: 14px;
	padding: 20px;
}

.form-header h1 {
	font-size: 24px;
	font-weight: 300;
	letter-spacing: -0.5px;
	text-decoration: none;
	text-align: center;
}

.input-block {
	margin-bottom: 15px;
	margin-top: 5px;
	display: block;
	width: 100%;
}

.label-link {
	float: right;
	font-size: 12px;
}

.register {
	background-color: #fff;
	border: 1px solid #d8dee2;
	border-radius: 5px;
	padding: 15px 20px;
	text-align: center;
	margin-bottom: 10px;
	margin-top: 12px !important;
}
.container {
    margin-left: auto;
    margin-right: auto;
    width: 980px;
}
.site-footer {
 text-align: center;
    border-top: 1px solid #eee;
    color: #767676;
    font-size: 12px;
    line-height: 1.5;
    margin-top: 40px;
    padding-bottom: 40px;
    padding-top: 40px;
    position: relative;
}
.site-footer-links li {
    display: inline-block;
    line-height: 16px;
    padding-right: 10px;
}
</style>
</head>
<body>
	<div class="login" id="login" ng-controller="loginCtrl">
		<form method="post" action="" accept-charset="UTF-8">
			<div class="form-header">
				<h1 >{{title}}</h1>
			</div>
			<div class="form-body">
				<label for="login_field"> 用户名/邮箱 </label> <input type="text"
					tabindex="1" name="login" id="login_field" ng-model="user.name"
					class="form-control input-block" autofocus="autofocus"
					autocorrect="off" autocapitalize="off"> <label
					for="password"> 密码 <a class="label-link"
					href="/password_reset">忘记密码?</a>
				</label> <input type="password" tabindex="2" name="password" id="password"
					class="form-control form-control input-block" ng-model="user.password">  <input
					type="submit" value="登陆" tabindex="3" name="commit"
					class="btn btn-success btn-block">
			</div>
		</form>
		<p class="register">
			新用户注册? <a href="/join?source=login">创建新用户</a> .
		</p>
	</div>
	<div class="container">
		<div class="site-footer">
			<ul class="site-footer-links">
				<li><a href="">Terms</a></li>
				<li><a href="">Privacy</a></li>
				<li><a href="">Security</a></li>
				<li><a href="">Contact</a></li>
			</ul>
			{{user.name}}
		</div>
	</div>
</body>
</html>