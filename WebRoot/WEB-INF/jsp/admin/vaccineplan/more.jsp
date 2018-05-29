<%@page import="cn.edu.zzti.dto.BodyRecordSearchBean"%>
<%@page import="cn.edu.zzti.entity.BodyRecord"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<title>疫苗安排管理</title>

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
<link rel="stylesheet"
	href="vendor/bootstrap-submenu/dist/css/bootstrap-submenu.min.css">

<script type="text/javascript" src="vendor/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="dist/js/bootstrapValidator.js"></script>
<script src="vendor/bootstrap-submenu/dist/js/bootstrap-submenu.min.js"
	defer></script>
</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h3>
						疫苗安排管理<a href="admin/vaccineplan/add.do" class="btn btn-primary">添加</a><span
							style="color:red;">${result }</span>
					</h3>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-5 col-lg-offset-1">疫苗名称</div>
		</div>
		<div class="row">
			<form action="" id="searchForm">
				<div class="col-lg-5 col-lg-offset-1">
					<input class="form-control" name="name"
						value="${sb.name}">
				</div>
				<div class="col-lg-1" col-lg-offset-1>
					<input type="submit" value="搜索" class="btn btn-info">
				</div>
				<div class="col-lg-1 col-lg-offset-1">
					<button class="btn btn-warning" id="del">批量删除</button>
				</div>
			</form>
		</div>
		<div class="row">
			<section>
			<div class="col-lg-10 col-lg-offset-1">
				<c:choose>
					<c:when test="${page.totalItemsCount == 0 }">
						没有符合条件的结果
					</c:when>
					<c:otherwise>
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>疫苗名称</th>
									<th>疫苗描述</th>
									<th>开始日期</th>
									<th>结束日期</th>
									<th>最小年龄</th>
									<th>最大年龄</th>
									<th>选中</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.data }" var="vaccineplan">
									<tr id="${vaccineplan.id }">
										<td>${vaccineplan.name }</td>
										<td>${vaccineplan.note }</td>
										<td><fmt:formatDate value="${vaccineplan.startDate }"
												pattern="yyyy-MM-dd" /></td>
										<td><fmt:formatDate value="${vaccineplan.endDate }"
												pattern="yyyy-MM-dd" /></td>
										<td><fmt:formatNumber type="number" value="${(vaccineplan.minAge-vaccineplan.minAge%12)/12}"/>岁${vaccineplan.minAge % 12}个月</td>
										<td><fmt:formatNumber type="number" value="${(vaccineplan.maxAge-vaccineplan.maxAge%12)/12}"/>岁${vaccineplan.maxAge%12}个月</td>
										<td><input type="checkbox" name="ids"
											value="${vaccineplan.id }"></td>
										<td><a href="admin/vaccineplan/remove.do?ids=${vaccineplan.id }">删除</a>&nbsp;<a href="admin/vaccineplan/update.do?id=${vaccineplan.id }">修改</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
			</div>
			</section>
		</div>
		<div class="row">
			<div class="col-lg-10 col-lg-offset-1">
				<ul class="pagination pagination">
					<c:if test="${page.totalItemsCount != 0 }">
						<c:set var="c"
							value="${page.totalPageCount >= 9 ? 5 : (page.totalPageCount + 1) / 2 }" />


						<c:choose>
							<c:when test="${c < 5 }">
								<c:set var="i" value="${page.pageIndex }" />
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${page.pageIndex <= c }">
										<c:set var="i" value="${page.pageIndex }" />
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when
												test="${page.pageIndex > c and page.pageIndex <= c + (page.totalPageCount - 9 ) }">
												<c:set var="i" value="${5 }" />
											</c:when>
											<c:otherwise>
												<c:set var="i"
													value="${page.pageIndex - (page.totalPageCount - 9 ) }" />
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						<li
							<c:if test="${page.pageIndex == 1 }">
						 class="disabled"
					</c:if>><a
							href="admin/vaccineplan/more.do?pageIndex=1&name=${sb.name}">&laquo;第一页</a></li>
						<li
							<c:if test="${page.pageIndex == 1 }">
						 class="disabled"
					</c:if>><a
							href="admin/vaccineplan/more.do?pageIndex=${pageIndex - 1 < 1 ? 1 : pageIndex - 1 }&name=${sb.name}">&laquo;上一页</a></li>
						<c:forEach begin="1" end="${c * 2 - 1 }" var="s">
							<c:choose>
								<c:when test="${s == i }">
									<li class="active"><a href="javascript:void(0)">${page.pageIndex - (i - 1) + s - 1 }</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="admin/vaccineplan/more.do?pageIndex=${page.pageIndex - (i - 1) + s - 1 }&name=${sb.name}">${page.pageIndex - (i - 1) + s - 1 }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<li
							<c:if test="${page.pageIndex == page.totalPageCount }">
						 class="disabled"
					</c:if>><a
							href="admin/vaccineplan/more.do?pageIndex=${page.pageIndex + 1 > page.totalPageCount ? page.totalPageCount : page.pageIndex + 1}&name=${sb.name}">下一页&raquo;</a></li>
						<li
							<c:if test="${page.pageIndex == page.totalPageCount }">
						 class="disabled"
					</c:if>><a
							href="admin/vaccineplan/more.do?pageIndex=${page.totalPageCount }&name=${sb.name}">最后一页&raquo;</a></li>
					</c:if>
				</ul>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		$(document).ready(
				function() {
					// Generate a simple captcha
					function randomNumber(min, max) {
						return Math
								.floor(Math.random() * (max - min + 1) + min);
					}
					$('#captchaOperation').html(
							[ randomNumber(1, 100), '+', randomNumber(1, 200),
									'=' ].join(' '));

					$('#searchForm').bootstrapValidator({
						//        live: 'disabled',
						message : '该输入非法',
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						fields : {
							startdate : {
								validators : {
									date : {
										format : 'YYYY-MM-DD',
										message : '输入不符合格式(YYYY-MM-DD)'
									}
								}
							},
							enddate : {
								validators : {
									date : {
										format : 'YYYY-MM-DD',
										message : '输入不符合格式(YYYY-MM-DD)'
									}
								}
							}
						}
					});

					$()
				});
		$("#del").click(function(event) {
			var bool = confirm("您确定删除这些记录吗？");
			if (bool) {
				$.ajax({
					url : 'admin/vaccineplan/remove.do',
					type : 'GET',
					data : $(":checkbox:checked").serialize(),
				}).done(function() {
					document.location = document.location;
				}).fail(function() {
					console.log("error");
				}).always(function() {
					console.log("complete");
				});
			}
		});
	</script>
</body>
</html>

