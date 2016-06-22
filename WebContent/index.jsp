<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CH">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>CC后台管理</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<link href="css/reset.css" rel="stylesheet">
<link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<script src="lib/requirejs/require.js" data-main="js/main"></script>
<style type="text/css">
</style>
</head>
<body>
	<div id="container">
		<!-- 	<div id="logo"><h1>后台管理</h1></div> -->
		<nav class="navbar navbar-default" id="nav-custom">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">后台管理</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
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
							<li><a href="#">退出</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
		<div id="canvas">
			<div id="nav">
				<div id="title">目录</div>
				<ul id="toggle" class="toggle">
					<li>
						<div class="active border">
							<span class="fa fa-home fa-2x"></span> <a href="#">首页</a>
						</div>
					</li>
					<li>
						<div>
							<span class="fa fa-user fa-2x"></span> <a href="#">用户 </a> <span
								class="the-btn fa fa-plus"></span>
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
			</div>
			<div id="canvas-content">
				<div id="contentTitle">
					<a type="button" class="toggle-nav" id="bars">
						<i class="fa fa-list fa-2x"></i>
					</a>
					<span>主页</span>
				</div>
				<div id="framework">
					<div id="toolbar" class="btn-group">
						<button type="button" class="btn btn-info" id="save"
							style="margin-top: 2px; margin-right: 5px; padding: 3px 12px">
							保存</button>
						<button type="button" class="btn btn-info" id="classfiSearch"
							style="margin-top: 2px; margin-right: 5px; padding: 3px 12px">
							搜索</button>
					</div>
					<table id="table" class="table table-hover" data-toggle="table"
						data-url="rest/Uploadinfo/queryAll" data-resizable="true"
						data-show-refresh="true" data-pagination="true"
						data-row-style="rowStyle"
						data-page-list="[10, 20, 50,100,500,1000]" data-sort-name="name"
						data-sort-order="desc" data-search="true" data-toolbar="#toolbar"
						data-height="300px">
						<thead>
							<tr>
								<th data-field="state" data-align="center" data-valign="middle"
									data-checkbox="true" data-visible="true" id="check"></th>
								<th data-align="center" data-formatter="runningFormatter">编号</th>
								<th data-field="name">Name</th>
								<th data-field="telephone">telephone</th>
								<th data-field="content">content</th>
								<th data-field="dataTime">dataTime</th>
							</tr>
						</thead>
					</table>
				</div>
				<!-- table end -->
			</div>
		</div>
	</div>
</body>
</html>
