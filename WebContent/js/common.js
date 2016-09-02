define([ 'confirm'], function(confirm, BootstrapDialog) {

	var common = {};
	common.init = function() {
	};
	
	//获取url中携带的参数
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
	common.avatar = function(){
		options = {
				"content" : "<button class='btn btn-danger btn-xs' id='logout' onclick='logout();'>退出登录</button>",
				"html" : true
			};
		options1 = {
			"content" : "",
			"html" : true
		};
		$('#avatar').popover(options);
		$('#avatar1').popover(options);
	}
	return common;
});
function logout(){
	require(['jquery','common'],function(jquery,common){
		common.logout();
	})
}
function avatar(){
	require(['jquery','common'],function(jquery,common){
		common.avatar();
	})
}
	//时间格式化
	Date.prototype.format=function(fmt) {           
		var o = {           
		"M+" : this.getMonth()+1, //月份           
		"d+" : this.getDate(), //日           
		"h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时           
		"H+" : this.getHours(), //小时           
		"m+" : this.getMinutes(), //分           
		"s+" : this.getSeconds(), //秒           
		"q+" : Math.floor((this.getMonth()+3)/3), //季度           
		"S" : this.getMilliseconds() //毫秒           
		};           
		var week = {           
		"0" : "/u65e5",           
		"1" : "/u4e00",           
		"2" : "/u4e8c",           
		"3" : "/u4e09",           
		"4" : "/u56db",           
		"5" : "/u4e94",           
		"6" : "/u516d"          
		};           
		if(/(y+)/.test(fmt)){           
			fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));           
		}           
		if(/(E+)/.test(fmt)){           
			fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);           
		}           
		for(var k in o){           
			if(new RegExp("("+ k +")").test(fmt)){           
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
			}           
		}           
		return fmt;           
	}  