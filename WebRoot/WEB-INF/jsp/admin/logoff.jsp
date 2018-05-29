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

<title>用户注销</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	请输入您要注销的用户的用户名：
	<br>
	<form action="" method="get">
		<input name="username"> <input type="submit" value="搜索">
	</form>
	<br>
	<c:if test="${list != null and list.size() != 0 }">
		<table>
			<thead>
				<tr><th>用户名</th><th>操作</th></tr>
			</thead>
			<c:forEach items="${list }" var="user">
				<tbody>
					<tr><td>${user.username }</td><td><a href="admin/delete.do?id=${user.id }">注销</a></td></tr>
				</tbody>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
