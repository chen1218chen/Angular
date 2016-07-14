<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
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

</head>

<script src="lib/requirejs/require.js" data-main="js/mapMain"></script>
<style type="text/css">
</style>
</head>

<%@include file="header.jsp"%>

<div id="canvas-content">
	<div id="contentTitle" ng-controller="mapCtrl">
		<a type="button" class="toggle-nav" id="bars"> <i
			class="fa fa-list fa-2x"></i>
		</a> <span>{{title}}</span>
	</div>
	<div id="frame"></div>
		<div id="left-panel" class="" style="height: 415px;">
		<div id="searchbox" class="clearfix"></div>
		<ul id="cards-level0" class="cardlist"></ul>
		<ul id="cards-level1" class="cardlist"></ul>
		<ul id="cards-level2" class="cardlist"></ul>
	</div>
	<div id="right-top">
		<div class="toolpanel">
			<span class="glyphicon glyphicon-map-marker" style="top: 0px;color:red">
				<!-- <img src="images/Marker.png" width="16px" height="16px"> -->
				<span id="place">天气</span>
			<!-- <span class="caret"></span> -->
			</span> <span id="road"><i class="fa fa-road"></i>路况信息</span> <span
				id="tool"><i class="fa fa-briefcase"></i>工具<span
				class="caret"></span></span>
		</div>
		<div class="detail-box" style="display: none;">
			<ul id="boxul" class="boxinfo">
				<li id="measure"><img src="images/Length.png"><i>测距</i></li>
				<li id="marker"><span class="glyphicon glyphicon-map-marker">
				</span><i>标记</i></li>
				<li id="clearAll"><span class="glyphicon glyphicon-trash">
				</span><i>清除</i></li>
			</ul>
		</div>

	</div>
	<!-- table end -->
</div>
<!-- canvas-content end -->
</div>
</div>
</body>
</html>
<%-- <%@include file="foot.html" %> --%>
