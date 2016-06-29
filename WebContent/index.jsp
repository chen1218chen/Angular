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
<!-- <script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script> -->
<script src="lib/requirejs/require.js" data-main="js/main"></script>
<style type="text/css">
</style>
</head>

<%@include file="header.html"%>

<div id="canvas-content" >
	<div id="contentTitle" ng-controller="myCtrl">
		<a type="button" class="toggle-nav" id="bars"> <i
			class="fa fa-list fa-2x"></i>
		</a> <span>{{title}}</span>
	</div>
	<div id="frame">
		<div id="toolbar" class="btn-group">
			<button type="button" class="btn btn-info" id="save"
				style="margin-top: 2px; margin-right: 5px; padding: 3px 12px">
				保存</button>
			<button type="button" class="btn btn-info" id="delete"
				style="margin-top: 2px; margin-right: 5px; padding: 3px 12px">
				删除</button>
		</div>
		<table id="table" class="table table-hover" data-toggle="table"
			data-url="rest/Uploadinfo/queryAll" data-resizable="true"
			data-show-refresh="true" data-pagination="true"
			data-row-style="rowStyle" data-page-list="[10, 20, 50,100,500,1000]"
			data-sort-name="name" data-sort-order="desc" data-search="true"
			data-toolbar="#toolbar" data-height="800" data-show-columns="true"
			data-show-export="true">
			<thead>
				<tr>
					<th data-field="state" data-align="center" data-valign="middle"
						data-checkbox="true" data-visible="true"></th>
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
<!-- canvas-content end -->
</div>
</div>
</body>
</html>
<%-- <%@include file="foot.html" %> --%>
