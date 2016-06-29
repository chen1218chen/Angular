define([ 'confirm'], function(confirm, BootstrapDialog) {

	var common = {};
	common.init = function() {
	};

	var url = window.location;
	common.GetUrlParameter = function(url, params) {
		var a = {};
		params = params || location.search;
		if (!params)
			return {};
		params = decodeURI(params);
		params.replace(/(?:^\?|&)([^=&]+)(?:\=)([^=&]+)(?=&|$)/g, function(m,
				k, v) {
			a[k] = v;
		});
		return a;
	};

	common.logout = function() {
		$.confirm({
			title : '确定退出登陆吗？',
			content : false,
			confirmButton : '确定',
			cancelButton : '取消',
			confirmButtonClass : 'btn-success',
			cancelButtonClass : 'btn-warning',
			confirm : function() {
				location.href = "logoutAction.action";
			},
		});
	};

	common.alertView = function(str) {
		$.alert({
			animation : 'zoom',
			animationBounce : 2,
			keyboardEnabled : true,
			title : false,
			content : str,
			confirmButton : '确定',
			confirmButtonClass : 'btn-info',
			theme : 'white',
		});
	};
	common.confirmView = function(str) {
		$.confirm({
			animation : 'zoom',
			animationBounce : 2,
			keyboardEnabled : true,
			title : false,
			content : str,
			confirmButton : '确定',
			cancelButton : '取消',
			confirmButtonClass : 'btn-info',
			cancelButtonClass : 'btn-warning',
			theme : 'white',
		});
	};
	common.alertConfirm = function(str) {
		$.alert({
			animation : 'zoom',
			animationBounce : 2,
			keyboardEnabled : true,
			title : false,
			content : str,
			confirmButton : '确定',
			confirmButtonClass : 'btn-primary',
			theme : 'white',
			confirm : function() {
				location.reload();
			}
		});
	};
	return common;
});
