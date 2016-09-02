requirejs.config({
	baseUrl : 'lib/',
	paths : {
		app : '../js/app',
		map: '../js/map',
		css : '../js/css.min',
		custom: '../js/custom',
		common: '../js/common',
		domReady: 'domReady/domReady',
		jquery : 'jquery/jquery.min',
		async: 'requirejs-plugins/async',
		react : 'react/react.min',
		angular : 'angular/angular.min',
		'angular-route': 'angular/angular-route.min',
		angularAMD: 'angularAMD/angularAMD.min',
		ngload: 'angularAMD/ngload.min',
		bootstrap : 'bootstrap/js/bootstrap.min',
		bootstrapTable : 'bootstrap-table/js/bootstrap-table.min',
		tableLanguage : 'bootstrap-table/locale/bootstrap-table-zh-CN.min',
		tableExport:'bootstrap-table/extensions/tableExport',
		bootstrapTableExport:'bootstrap-table/extensions/bootstrap-table-export.min',
		confirm : 'jquery-confirm2/jquery-confirm.min',
		'bootstrap-dialog' : 'bootstrap3-dialog/js/bootstrap-dialog.min',
		BMap : 'http://api.map.baidu.com/api?v=2.0&ak=Ua9wlu6LbqmNKVbMEGtkgSxvljqZC5fc',
		SearchInfoWindow :'http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min',
		TrafficControl : 'http://api.map.baidu.com/library/TrafficControl/1.4/src/TrafficControl_min',
		DistanceTool : 'http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min',
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
		'angular-route':{
            deps:['angular'],
            exports: 'angular-route'
        },
		angularAMD: {
			deps: ['angular']
		},
        ngload: {
        	deps: ['angularAMD'] 
        },
		bootstrap : {
			deps : [ 'jquery', 'css!../lib/bootstrap/css/bootstrap.min.css' ],
			exports : "$.fn.popover"
		},
		bootstrapTable : {
			deps : ['jquery','bootstrap',
					'css!./bootstrap-table/css/bootstrap-table.min.css' ],
			exports : '$.fn.bootstrapTable'
		},
		tableLanguage : {
			deps : [ 'jquery','bootstrapTable' ],
			exports : '$.fn.bootstrapTable.defaults'
		},
		tableExport : {
			deps: ['jquery','bootstrapTableExport'],
			exports : '$.fn.extend'
		},
		bootstrapTableExport:{
			deps:['jquery','bootstrapTable'],
			exports : '$.fn.bootstrapTable.defaults'
		},
		confirm : {
			deps : [ 'jquery','css!./jquery-confirm2/jquery-confirm.min.css' ]
		},
		'bootstrap-dialog' : {
			deps : ['bootstrap', 'css!./bootstrap3-dialog/css/bootstrap-dialog.min.css' ]
		},
		BMap: {
            deps: ['jquery','css!../css/map.css'],
            exports: 'BMap'
        },
        SearchInfoWindow :{
        	deps: ['jquery','BMap','css!http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css'],
        	exports:'BMapLib'
        },
        TrafficControl :{
        	deps: ['jquery','BMap','css!http://api.map.baidu.com/library/TrafficControl/1.4/src/TrafficControl_min.css'],
        	exports:'BMapLib'
        },
        DistanceTool :{
        	deps: ['jquery','BMap'],
        	exports:'BMapLib'
        }
	},
	//首先加载bootstrap
	deps: ['bootstrap','app'],
	//开发专用，阻止浏览器缓存
	urlArgs: "bust=" +  (new Date()).getTime(),
	//在放弃加载一个脚本之前等待的秒数。设为0禁用等待超时。默认为7秒。
	waitSeconds: 0
});
