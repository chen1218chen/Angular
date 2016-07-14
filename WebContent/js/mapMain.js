/*define('map',['jquery','async!BMap'],function(){
	var BMap={
			map:null
	};
	BMap.init= function(){
		map = new BMap.Map('container');
		var point = new BBMap.Point(116.404449, 39.914889);
		map.centerAndZoom(point, 15);
		map.enableScrollWheelZoom();
	}
	BMap.getLocation = function() {  
	    if (navigator.geolocation) {  
	        navigator.geolocation.getCurrentPosition(BMap.showBMap, handleError, { enableHighAccuracy: true, maximumAge: 1000 });  
	    } else {  
	        alert("您的浏览器不支持使用HTML 5来获取地理位置服务");  
	    }  
	}  
	BMap.showBMap = function(value) {  
	     longitude = value.coords.longitude;  
	     latitude = value.coords.latitude;  
	     gpsPoint = new BMap.Point(longitude, latitude);    // 创建点坐标  
	     var geoc = new BMap.Geocoder();  
	     BMap.Convertor.translate(gpsPoint, 0, translateCallback);  
	}
	return BMap;
})
*/

require(['config'],function(){
	
	require(['angular','bootstrap','map','custom','domReady!'],function(angular,bootstrap,MyMap){
		
		var app = angular.module("myApp",[]);
		app.controller('mapCtrl', function($scope) {
			$scope.title = "地图";
		});
		angular.element(document).ready(function() {
			angular.bootstrap(document, [ 'myApp' ]);
		});
		
		//location
		MyMap.place();
		
		//create map
		MyMap.createMap();
//		console.dir(MyMap);
		
		//tool
		MyMap.tools();
//		MyMap.loginManager();
		
		options = {
				"content" : "<button class='btn btn-danger btn-xs' id='logout' onclick='logout();'>退出登录</button>",
				"html" : true
			};
			options1 = {
				"content" : "",
				"html" : true
			};
			$('#avatar').popover(options);
			$('#avatar1').popover(options1);
		
		MyMap.setMapEvent();
		MyMap.addLayer();
//		MyMap.pointMarker();
		$("#measure").click(function() {
			MyMap.measure();
		});
		$("#clearAll").click(function() {
			MyMap.clearAll();
		});
		$("#marker").click(function() {
			MyMap.marker();
		});
		$("#place").text(" 当前位置");
		$("#place").click(function() {
			MyMap.place();
		});
		$("#road").click(function() {
//			road();
		})
	});
});