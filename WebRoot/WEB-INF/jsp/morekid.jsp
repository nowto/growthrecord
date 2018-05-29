<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<title>孩子列表</title>

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
<script type="text/javascript">
	$(function(){
		$(".removekid").click(function(event) {
			var result =confirm("确定删除此孩子吗?将一并删除此孩子的包括生理、学业、照片等全部记录，并不可恢复");
			if(result){
				var kidid = $(this).attr('id');
				$.getJSON('removekid.do', {id: kidid}, function(json, textStatus) {
					if(textStatus != "success" || json != true){
						//删除失败
					}else{
						//删除成功
					$("tr[id="+kidid+"]").remove();
					}
				});
			}
		});
	});
</script>

</head>

<body>

	<div class="container">
		<tag:header />
		<div class="row">
			<section>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<div class="row">
						<div class="col-lg-8">
							<h3>已有孩子</h3>
						</div>
						<div class="col-lg-4"><a class="btn btn-info" href="addkid.do">添加孩子</a></div>
					</div>
				</div>
				<c:choose>

					<c:when test="${empty kids}">
						您目前没有添加孩子,<a href="addkid.do">点击添加孩子</a>
					</c:when>
					<c:otherwise>
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>姓名</th>
									<th>小名</th>
									<th>性别</th>
									<th>生日</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${kids }" var="kid">
									<tr id="${kid.id }">
										<td>${kid.name }</td>
										<td>${kid.nickname }</td>
										<td>${kid.sex }</td>
										<td><fmt:formatDate value="${kid.birthday }" /></td>
										<td><a href="javascript:void(0)" id="${kid.id }" class="removekid">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="updatekid.do?id=${kid.id }">修改</a></td>
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
