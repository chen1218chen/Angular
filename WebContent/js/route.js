'use strict';

define(["app"],function(app){
	
	return app.config(['$routeProvider', function ($routeProvider) {
		$routeProvider
		.when("/main", {
			templateUrl: "view/main.html",
			controller: 'mainCtrl'
		})
		.when("/map", {
			templateUrl: "view/map.html",
			controller: 'mapCtrl'
		})
		.otherwise({
			redirectTo: "view/main.html"
		});
	}]);
})