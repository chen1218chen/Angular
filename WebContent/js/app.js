define([ 'bootstrapTable', 'angular' ], function(bootstrapTable, angular) {
	'use strict';

	var app = {};

	var title = angular.module("myApp", []);
	title.controller('myCtrl', function($scope) {
		$scope.title = "主页";
	});
	
	angular.element(document).ready(function() {
		angular.bootstrap(document, [ 'myApp' ]);
	});

	app.tableInit = function() {
		$('#table').bootstrapTable({
			data : data
		});
	};

	//前台数据组装展示
	app.tableShow = function(data) {

		if (data != null) {
			var result = [];
			for (var i = 0; i < data.length; i++) {
				var obj = {};

				var pictureUrl = data[i].picturepath1;
				obj["id"] = data[i].id;
				obj["content"] = data[i].content;
				obj["telephone"] = data[i].telephone;
				obj["picturepath1"] = pictureUrl;
				if (data[i].remessage != "" && data[i].remessage != undefined) {
					obj["remessage"] = "已回复";
				} else {
					obj["remessage"] = "未回复";
				}
				obj["dataTime"] = data[i].dataTime;
				obj["cname"] = data[i].classfic.cname;
				result.push(obj);
			}
			/*$('#table').bootstrapTable({
				data : result
			});*/
			$('#table').bootstrapTable('load', result);

		}
	}
	// 数据删除
	app.infoDelete = function(id) {
		var ids = $.map($table.bootstrapTable('getSelections'), function(row) {
			return row.id;
		});

		if (ids == "") {
			common.alertView("请选择删除项");
			return;
		}
		$.confirm({
			animation : 'zoom',
			animationBounce : 2,
			keyboardEnabled : true,
			title : false,
			content : "确定要删除记录吗？",
			confirmButton : '确定',
			cancelButton : '取消',
			confirmButtonClass : 'btn-info',
			cancelButtonClass : 'btn-warning',
			theme : 'white',
			confirm : function() {
				$.ajax({
					url : "rest/Uploadinfo/delInfos",
					type : "post",
					traditional : true,
					data : {
						"ids" : ids
					},
					success : function(data) {
						common.alertView("删除成功！");
					}
				})
				$table.bootstrapTable('remove', {
					field : "id",
					values : ids
				});
			}
		});
	};

	return app;
});