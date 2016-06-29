requirejs.config({
	baseUrl : 'lib/',
	paths : {
		app : '../js/app',
		css : '../js/css.min',
		custom: '../js/custom',
		common: '../js/common',
		domReady: 'domReady/domReady',
		jquery : 'jquery/jquery.min',
		react : 'react/react.min',
		angular : 'angular/angular',
	/*	"angular-route":"path/to/angular-route",*/
		bootstrap : 'bootstrap/js/bootstrap.min',
		bootstrapTable : 'bootstrap-table/js/bootstrap-table.min',
		tableLanguage : 'bootstrap-table/locale/bootstrap-table-zh-CN.min',
		tableExport:'bootstrap-table/extensions/tableExport',
		bootstrapTableExport:'bootstrap-table/extensions/bootstrap-table-export.min',
		confirm : 'jquery-confirm2/jquery-confirm.min',
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
			deps: ['confirm', 'bootstrap-dialog']
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
		tableLanguage : {
			deps : [ 'bootstrapTable' ],
			exports : '$.fn.bootstrapTable.defaults'
		},
		bootstrapTable : {
			deps : ['bootstrap',
					'css!./bootstrap-table/css/bootstrap-table.min.css' ],
			exports : '$.fn.bootstrapTable'
		},
		tableExport : {
			deps: ['jquery'],
			exports : '$.fn.extend'
		},
		bootstrapTableExport:{
			deps:['bootstrapTable'],
			exports : '$.fn.bootstrapTable.defaults'
		},
		confirm : {
			deps : [ 'jquery','css!./jquery-confirm2/jquery-confirm.min.css' ]
		},
		'bootstrap-dialog' : {
			deps : ['bootstrap', 'css!./bootstrap3-dialog/css/bootstrap-dialog.min.css' ]
		}
		
	},
	//开发专用，阻止浏览器缓存
/*	urlArgs: "bust=" +  (new Date()).getTime(),*/
	//在放弃加载一个脚本之前等待的秒数。设为0禁用等待超时。默认为7秒。
	waitSeconds: 0
});
