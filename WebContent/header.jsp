<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8"> -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<link href="css/map.css" rel="stylesheet" type="text/css" />
<body>
	<div id="container" class="display-nav">
		<!-- 	<div id="logo"><h1>后台管理</h1></div> -->
		<!-- <nav class="navbar navbar-default" id="nav-custom">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">后台管理</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">菜单一 <span class="sr-only">(current)</span></a></li>
					<li><a href="#">菜单二</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">下拉菜单 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">二级菜单</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">二级菜单</a></li>
						</ul></li>
				</ul>
				<form class="navbar-form navbar-left" role="search" action="">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">提交</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">设置</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">用户 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">修改用户信息</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#" id="logout">退出</a></li>
						</ul></li>
				</ul>
			</div>
			/.navbar-collapse
		</nav> -->
		<div id="canvas">
			<div id="nav">
				<div id="title">
					<shiro:user>
						<img id="avatar" src="images/user.jpg" data-toggle="popover"
							data-placement="bottom"
							data-title='欢迎您：<%out.println(session.getAttribute("uname"));%>' />
					</shiro:user>
					<shiro:guest>
						<img id="avatar1" src="images/user.jpg" data-toggle="popover"
							data-placement="bottom" data-title='请登录' />
					</shiro:guest>
				</div>
				<ul id="toggle" class="toggle">
					<li>
						<div class="active border">
							<span class="fa fa-home fa-2x"></span> <a href="map.jsp">首页</a>
						</div>
					</li>
					<li>
						<div>
							<span class="fa fa-user fa-2x"></span> <a href="index.jsp">用户
							</a> <span class="the-btn fa fa-plus"></span>
						</div>
						<ul class="twoLevel">
							<li><a href="#">二级菜单1</a></li>
							<li><a href="#">二级菜单2</a></li>
						</ul>
					</li>
					<li>
						<div>
							<span class="fa fa-briefcase fa-2x"></span> <a href="#">pro</a><span
								class="the-btn fa fa-plus"></span>
						</div>
						<ul class="twoLevel">
							<li><a href="#">二级菜单1</a></li>
							<li><a href="#">二级菜单2</a></li>
						</ul>
					</li>
					<li>
						<div>
							<span class="fa fa-envelope  fa-2x"></span> <a href="#">联系我们</a>
						</div>
					</li>
					<li>
						<div>
							<span class="fa fa-cog fa-2x"></span> <a href="#">设置</a>
						</div>
					</li>
				</ul>
				<div id="footer">
					<p>版权归属：@西安天绘</p>
				</div>
			</div>