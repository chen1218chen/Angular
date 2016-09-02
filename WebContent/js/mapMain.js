require(['config'],function(){
	require(['app','bootstrap','map','common','custom','domReady!'],function(app,bootstrap,MyMap,common){
		
		app.controller('mapCtrl', function($scope) {
			$scope.title = "地图";
		});

		//location
		MyMap.place();
		
		//create map
		MyMap.createMap();
//		console.dir(MyMap);
		
		//tool
		MyMap.tools();
//		MyMap.loginManager();

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
