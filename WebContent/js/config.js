requirejs.config({
	baseUrl : 'lib/',
	paths : {
		app : '../js/app',
		css : '../js/css.min',
		custom: '../js/custom',
		common: '../js/common',
		jquery : 'jquery/jquery.min',
		bootstrap : 'bootstrap/js/bootstrap.min',
		bootstrapTable : 'bootstrap-table/js/bootstrap-table.min',
		react : 'react/react.min',
		angular : 'angular/angular.min',
		confirm : 'jquery-confirm/jquery.confirm.min',
		'bootstrap-dialog' : 'bootstrap3-dialog/js/bootstrap-dialog.min'
	},
	shim : {
		jquery : {
			init : function() {
				return jquery.noConflict(true);
			},
			exports : 'jquery'
		},
		custom : {
			deps: ['jquery']
		},
		common : {
			deps: ['jquery','confirm', 'bootstrap-dialog']
		},
		index : {
			deps: ['jquery']
		},
		angular : {
			exports : 'angular'
		},
		bootstrap : {
			deps : [ 'jquery', 'css!../lib/bootstrap/css/bootstrap.min.css' ],
			exports : "$.fn.popover"
		},
		bootstrapTable : {
			deps : [ 'jquery', 'bootstrap',
					'css!./bootstrap-table/css/bootstrap-table.min.css' ],
			exports : '$.fn.bootstrapTable'
		},
		confirm : {
			deps : [ 'jquery' ]
		},
		'bootstrap-dialog' : {
			deps : [ 'jquery','bootstrap', 'css!./bootstrap3-dialog/css/bootstrap-dialog.min.css' ]
		}
		
	}
});
