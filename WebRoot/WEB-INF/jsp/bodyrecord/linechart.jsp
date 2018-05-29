<%@page import="cn.edu.zzti.entity.BodyRecord"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>折线图</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="height:400px; width:80%;"></div>
	<!-- ECharts单文件引入 -->
	<c:if
		test="${list != null and list.size() != 0 and (list[0].type == 0 or list[0].type == 1)}">
		<script src="vendor/echarts/dist/echarts-all.js"></script>
		<script type="text/javascript">
			// 基于准备好的dom，初始化echarts图表
			var type = ${list[0].type};
			var titleText;
			switch (type) {
			case 0:
				titleText = "身高";
				break;
			case 1:
				titleText = "体重";
				break;
			default:
				break;
			}

			var myChart = echarts.init(document.getElementById('main'));
			var option = {
				title : {
					text : titleText + "变化曲线"
				},
				tooltip : {
					trigger : 'item',
					formatter : function(params) {
						var date = new Date(params.value[0]);
						data = date.getFullYear() + '-' + (date.getMonth() + 1)
								+ '-' + date.getDate();
						return data + '<br/>' + params.value[1];
					}
				},
				toolbox : {
					show : true,
					feature : {
						saveAsImage : {
							show : true
						}
					}
				},
				dataZoom : {
					show : true
				},
				legend : {
					data : [ titleText ]
				},
				// grid: {
				//     y2: 80
				// },
				xAxis : [ {
					type : 'time',
					splitNumber : 10
				} ],
				yAxis : [ {
					type : 'value'
				} ],
				series : [ {
					name : '身高',
					type : 'line',
					showAllSymbol : true,
					symbolSize : 3,
					data : [
						<c:forEach items="${list }" var="br">
						[new Date(${br.recordDate.year+1900}, ${br.recordDate.month+1}, ${br.recordDate.date }), ${br.note}],
						</c:forEach>
					        ]
				} ]
			};

			// 为echarts对象加载数据 
			myChart.setOption(option);
		</script>
	</c:if>
</body>
</html>
