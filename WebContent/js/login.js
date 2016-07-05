require(['config'],function(){
	
	require(['angular','bootstrap','domReady!'],function(angular,bootstrap){
	
		
		var app = angular.module("myApp",[]);
		app.controller('loginCtrl', function($scope) {
			$scope.title = "主页";
		});
		angular.element(document).ready(function() {
			angular.bootstrap(document, [ 'myApp' ]);
		});
	});
});