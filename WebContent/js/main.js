function rowStyle(row, index) {
    /*var classes = ['active', 'success', 'info', 'warning', 'danger'];*/
	var classes = ['success', 'info', 'warning', 'danger'];
    
    /*if (index % 2 === 0 && index / 2 < classes.length) {
        return {
            classes: classes[index / 2]
        };
    }*/
    if(index % 2 ===0){
    	return {
    		classes:classes[index%3]
    	};
    }
    return {};
}

function runningFormatter(value, row, index) {
    return index+1;
}
require([ 'config' ], function() {
	require([ 'bootstrapTable','tableExport', 'angular','app','../js/controller','../js/route','common','tableLanguage',
	          'bootstrapTableExport','custom','domReady!', 'css!../css/reset.css',
	  		'css!../lib/font-awesome/css/font-awesome.min.css',
			'css!../css/style.css', 'css!../css/map.css'], function(bootstrapTable,tableExport,
	        angular,app,controller,route,common,tableLanguage,bootstrapTableExport,custom,domReady) {
		//页面自适应

		//define(['route'],function(){
		angular.element(document).ready(function() {
			//手动启动angular
			angular.bootstrap(document, [ 'myApp' ]);
		})
		
		$(window).resize(function() {
			$('#table').bootstrapTable('resetView');
		});
		
	/*	$("#delete").click(function(){
			var frame = $("#frame").remove();
		});*/
		$("#logout").click(function(){
			common.logout();
		});
		
		
	})
})