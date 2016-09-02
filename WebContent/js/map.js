define(
		[ 'jquery','async!BMap','DistanceTool','TrafficControl','SearchInfoWindow','domReady!'],
		function() {
			'use strict';

			var map = {
				map : null
			};
			
			map.createMap = function() {
				var map1 = new BMap.Map("frame"); // 创建地图实例
				var point = new BMap.Point(108.95344, 34.265664); // 创建点坐标
				// map.centerAndZoom(point, 13); // 初始化地图，设置中心点坐标和地图级别
				map1.centerAndZoom("西安", 13);
				map.map = map1;
			};
			
			// 登陆管理面板
			map.loginManager = function() {
				options = {
						"content" : "<button class='btn btn-danger btn-xs' id='logout'>退出登录</button>",
						"html" : true
					};
					options1 = {
						"content" : "请登录",
						"html" : true
					};
					$('#avatar').popover(options)
					$('#avatar1').popover(options1)
			};
			
			map.tools = function(){
				document.getElementById("tool").addEventListener("click",
						function() {
							if ($(".detail-box").css("display") == "none")
								$(".detail-box").css("display", "block");
							else
								$(".detail-box").css("display", "none");
				});
			}
			map.place = function() {
				if (navigator.geolocation) {
					var geolocation = new BMap.Geolocation();
					geolocation.getCurrentPosition(map.showPosition,
							map.handleError, {
								enableHighAccuracy : true
							})
				} else {
					alertView("您的浏览器不支持使用HTML5来获取地理位置服务");
				}
			};

			map.showPosition = function(r) {
				if (this.getStatus() == BMAP_STATUS_SUCCESS) {
					// var mk = new BMap.Marker(r.point);
					// map.addOverlay(mk);
					map.map.panTo(r.point);
					var point = new BMap.Point(r.point.lng, r.point.lat);
					var myIcon = new BMap.Icon("images/Marker-red.png", new BMap.Size(26, 26));
					var marker = new BMap.Marker(point, {
						icon : myIcon
					});
					map.map.addOverlay(marker);
					// alert('您的位置：'+r.point.lng+','+r.point.lat);
				} else {
					alert('failed' + this.getStatus());
				}
			};

			map.handleError = function(value) {
				switch (value.code) {
				case 1:
					// alert("位置服务被拒绝");
					break;
				case 2:
					// alert("暂时获取不到位置信息");
					break;
				case 3:
					// alert("获取信息超时");
					break;
				case 4:
					// alert("未知错误");
					break;
				}
			};

			map.setMapEvent = function() {
				var top_left_control = new BMap.ScaleControl({
					anchor : BMAP_ANCHOR_TOP_LEFT
				});// 左上角，添加比例尺
				var top_left_navigation = new BMap.NavigationControl(); // 左上角，添加默认缩放平移控件
				// map.addControl(top_left_control);
				// map.addControl(top_left_navigation);
				map.map.enableScrollWheelZoom(); // 启用滚轮放大缩小，默认禁用
				map.map.enableContinuousZoom(); // 启用地图惯性拖拽，默认禁用
			}

			//两点间距离
			map.measure = function() {
				/*var pointA = new BMap.Point(106.486654,29.490295);  // 创建点坐标A
				var pointB = new BMap.Point(106.581515,29.615467);  // 创建点坐标B
				var distance = (map.getDistance(pointA,pointB)).toFixed(2)+' 米。';  //获取两点距离,保留小数点后两位
				var polyline = new BMap.Polyline([pointA,pointB], {strokeColor:"red", strokeWeight:3, strokeOpacity:0.5});  //定义折线
				map.addOverlay(polyline);*/
				var myDis = new BMapLib.DistanceTool(map.map);
				myDis.open(); //开启鼠标测距
				//myDis.close();  //关闭鼠标测距大
				//地图加载时
				/*map.addEventListener("load",function(){
				});*/
			}
			
			map.clearAll = function() {
				map.map.clearOverlays();

			}
			
			//标注
			map.marker = function() {
				map.map.addEventListener("click", function(e) {
					var point = new BMap.Point(e.point.lng, e.point.lat);
					var myIcon = new BMap.Icon("images/Marker.png", new BMap.Size(26, 26));
					var marker = new BMap.Marker(point, {
						icon : myIcon
					});
					map.map.addOverlay(marker);
//				 	marker.setAnimation(BMAP_ANIMATION_BOUNCE);
//					map.removeOverlay(marker);//清除marker
//					marker.dispose();//释放内存
				});
			}

			// 添加图层
			map.addLayer = function() {
				// 交通拥堵状况
				var ctrl = new BMapLib.TrafficControl({
					//anchor: BMAP_ANCHOR_TOP_RIGHT,
					showPanel : true
				});
				// 是否显示路况提示面板
				map.map.addControl(ctrl);
				ctrl.setAnchor(BMAP_ANCHOR_TOP_LEFT);

				// 鹰眼图
				var overviewControl = new BMap.OverviewMapControl({
					anchor : BMAP_ANCHOR_BOTTOM_RIGHT,
					isOpen : true
				});
				map.map.addControl(overviewControl);
			}
			
			map.pointMarker = function() {
				$.ajax({
					url : "rest/Uploadinfo/queryAllinfo",
					type : "get",
					dataType : "json",
					success : function(result) {
						var data_info = [];
						for (var i = 0; i < result.length; i++) {
							var data = [];
							data.push(result[i].lon);
							data.push(result[i].lat);
							data.push("个人描述:" + result[i].content);
							data.push("电话:"+result[i].telephone);
							data_info.push(data);
						}
						map.panel(data_info);
					}
				});
			};
			
			map.panel = function (data_info) {
//				console.dir(data_info);
				for (var i = 0; i < data_info.length; i++) {
					var content = data_info[i][2];
					var title = data_info[i][3];
					map.addMarker(data_info[i][0], data_info[i][1], content,title);
				}
			};
			
			map.addMarker = function (lon, lat, content,title) {
				var point = new BMap.Point(lon, lat);
				var marker = new BMap.Marker(point); // 创建标注
				map.map.addOverlay(marker); // 将标注添加到地图中
				marker.setAnimation(BMAP_ANIMATION_BOUNCE); // 跳动的动画
				marker.addEventListener("click", function(e) {
					searchInfoWindow.open(marker);
				});

				// 创建检索信息窗口对象
				var searchInfoWindow = null;
				searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
					title : title, // 标题
					width : 290, // 宽度
					height : 155, // 高度
					panel : "panel", // 检索结果面板
					enableAutoPan : true, // 自动平移
					searchTypes : [ /*BMAPLIB_TAB_SEARCH, // 周边检索
					BMAPLIB_TAB_TO_HERE, // 到这里去
					BMAPLIB_TAB_FROM_HERE // 从这里出发
			*/		]
				});
				/*
				 * //创建小狐狸图标 var pt = new BMap.Point(lon, lat); var myIcon = new
				 * BMap.Icon("http://developer.baidu.com/map/jsdemo/img/fox.gif", new
				 * BMap.Size(300,157)); var marker2 = new BMap.Marker(pt,{icon:myIcon}); //
				 * 创建标注 map.addOverlay(marker2);
				 * marker2.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
				 */}
			return map;
		});
