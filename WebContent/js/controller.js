'use strict';

define([ 'app' ], function(app) {
	app.controller('myCtrl', function($scope) {
		$scope.title = "主页";
	});

	app.controller('mainCtrl', function($scope) {
		$("#delete").click(function() {
			var frame = $("#frame").remove();
		});
		/*$.get("rest/Uploadinfo/queryAll",function(result){
			$('#table').bootstrapTable({
				data : result
			});
		})*/
		$('#table').bootstrapTable({url:"rest/Uploadinfo/queryAll"});
	});
	
	app.controller('mapCtrl', function($scope){
		$scope.title = "地图";
	})
})
