<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
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

<title>疫苗注射提醒</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="dist/css/bootstrapValidator.css" />
<link rel="stylesheet" href="vendor/bootstrap-submenu/dist/css/bootstrap-submenu.min.css">

<script type="text/javascript" src="vendor/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="dist/js/bootstrapValidator.js"></script>
<script src="vendor/bootstrap-submenu/dist/js/bootstrap-submenu.min.js" defer></script>
</head>

<body>
	<div class="container">
		<tag:header />
		<div class="row">
			<section>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h3>疫苗注射提醒</h3>
				</div>
				<c:choose>

					<c:when test="${empty vaccineNotifies}">
						暂无疫苗注射提醒
					</c:when>
					<c:otherwise>
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>孩子</th>
									<th>疫苗名称</th>
									<th>详情</th>
									<th>地点</th>
									<th>开始日期</th>
									<th>截止日期</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${vaccineNotifies }" var="vaccineNotify">
									<tr>
										<td>${vaccineNotify.kid.name }</td>
										<td>${vaccineNotify.vaccinePlan.name }</td>
										<td>${vaccineNotify.vaccinePlan.note }</td>
										<td>${vaccineNotify.vaccinePlan.site }</td>
										<td><fmt:formatDate value="${vaccineNotify.vaccinePlan.startDate }" /></td>
										<td><fmt:formatDate value="${vaccineNotify.vaccinePlan.endDate }" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
			</div>
			</section>
		</div>
	</div>
</body>
</html>
