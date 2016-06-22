define(['bootstrapTable'], function(bootstrapTable) {
	var app = {};

	app.tableInit = function() {
		var data = [
					{
						"name" : "bootstrap-table",
						"stargazers_count" : "526",
						"forks_count" : "122",
						"description" : "An extended Bootstrap table with radio, checkbox, sort, pagination, and other added features. (supports twitter bootstrap v2 and v3) "
					},
					{
						"name" : "multiple-select",
						"stargazers_count" : "288",
						"forks_count" : "150",
						"description" : "A jQuery plugin to select multiple elements with checkboxes :)"
					},
					{
						"name" : "bootstrap-show-password",
						"stargazers_count" : "32",
						"forks_count" : "11",
						"description" : "Show/hide password plugin for twitter bootstrap."
					},
					{
						"name" : "blog",
						"stargazers_count" : "13",
						"forks_count" : "4",
						"description" : "my blog"
					},
					{
						"name" : "scutech-redmine",
						"stargazers_count" : "6",
						"forks_count" : "3",
						"description" : "Redmine notification tools for chrome extension."
					} ];
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