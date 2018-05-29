<%@page import="cn.edu.zzti.dto.BodyRecordSearchBean"%>
<%@page import="cn.edu.zzti.entity.BodyRecord"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<%!String typeStr = null;%>
<%
	BodyRecordSearchBean sb = (BodyRecordSearchBean) request
			.getAttribute("sb");
	switch (sb.getType()) {
		case BodyRecord.TYPE_HEIGHT :
			typeStr = "身高";
			break;
		case BodyRecord.TYPE_WEIGHT :
			typeStr = "体重";
			break;
		case BodyRecord.TYPE_FIRST :
			typeStr = "第一次";
			break;
		case BodyRecord.TYPE_OTHER :
			typeStr = "其他";
			break;

		default :
			break;
	}
%>
<base href="<%=basePath%>">

<title>生理记录管理-<%=typeStr%></title>

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
		<tag:header />
		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h3>
						生理记录-<b><%=typeStr%></b><a href="bodyrecord/add.do"
							class="btn btn-primary">添加</a><span style="color:red;">${result }</span>
					</h3>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-2 col-lg-offset-1">姓名</div>
			<div class="col-lg-3">开始时间</div>
			<div class="col-lg-3">截止时间</div>
		</div>
		<div class="row">
			<form action="" id="searchForm">
				<div class="col-lg-2 col-lg-offset-1">
					<select name="kidId" class="form-control" id="choosekid">
						<option value="">--选择孩子--</option>
						<c:forEach items="${kids }" var="kid">
							<c:choose>
								<c:when test="${kid.id == param.kidId }">
									<option value="${kid.id }" selected="selected">${kid.name }</option>
								</c:when>
								<c:otherwise>
									<option value="${kid.id }">${kid.name }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-3">
					<input class="form-control" type="text" name="startdate"
						value="<fmt:formatDate value="${sb.startdate }" pattern="yyyy-MM-dd"/>">
				</div>
				<div class="col-lg-3">
					<input class="form-control" type="text" name="enddate"
						value="<fmt:formatDate value="${sb.enddate }" pattern="yyyy-MM-dd"/>">
					<input type="hidden" name="type" value="${sb.type }">
				</div>
				<div class="col-lg-1">
					<input type="submit" value="搜索" class="btn btn-info">
				</div>
				<div class="col-lg-1">
					<button class="btn btn-warning" id="del">删除</button>
				</div>
			</form>
			<c:if
				test="${param.kidId != null and (requestScope.sb.type == 0 or requestScope.sb.type == 1)}">
				<div class="col-lg-1">
					<a target="_blank"
						href="bodyrecord/linechart.do?type=${requestScope.sb.type }&kidId=${param.kidId}">查看<%=typeStr%>曲线
					</a>
				</div>
			</c:if>
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
									<th>姓名</th>
									<th><%=typeStr%></th>
									<th>时间</th>
									<th>选中</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.data }" var="bodyrecord">
									<tr id="${bodyrecord.id }">
										<td>${bodyrecord.kid.name }</td>
										<td>${bodyrecord.note }</td>
										<td><fmt:formatDate value="${bodyrecord.recordDate }"
												pattern="yyyy-MM-dd" /></td>
										<td><input type="checkbox" name="ids"
											value="${bodyrecord.id }"></td>
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
							href="bodyrecord/more.do?type=${sb.type}&pageIndex=1&startdate=<fmt:formatDate value="${sb.startdate }" pattern="yyyy-MM-dd"/>&enddate=<fmt:formatDate value="${sb.enddate }" pattern="yyyy-MM-dd"/>&kidId=${sb.kidId}">&laquo;第一页</a></li>
						<li
							<c:if test="${page.pageIndex == 1 }">
						 class="disabled"
					</c:if>><a
							href="bodyrecord/more.do?type=${sb.type}&pageIndex=${pageIndex - 1 < 1 ? 1 : pageIndex - 1 }&startdate=<fmt:formatDate value="${sb.startdate }" pattern="yyyy-MM-dd"/>&enddate=<fmt:formatDate value="${sb.enddate }" pattern="yyyy-MM-dd"/>&kidId=${sb.kidId}">&laquo;上一页</a></li>
						<c:forEach begin="1" end="${c * 2 - 1 }" var="s">
							<c:choose>
								<c:when test="${s == i }">
									<li class="active"><a href="javascript:void(0)">${page.pageIndex - (i - 1) + s - 1 }</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="bodyrecord/more.do?type=${sb.type}&pageIndex=${page.pageIndex - (i - 1) + s - 1 }&startdate=<fmt:formatDate value="${sb.startdate }" pattern="yyyy-MM-dd"/>&enddate=<fmt:formatDate value="${sb.enddate }" pattern="yyyy-MM-dd"/>&kidId=${sb.kidId}">${page.pageIndex - (i - 1) + s - 1 }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<li
							<c:if test="${page.pageIndex == page.totalPageCount }">
						 class="disabled"
					</c:if>><a
							href="bodyrecord/more.do?type=${sb.type}&pageIndex=${page.pageIndex + 1 > page.totalPageCount ? page.totalPageCount : page.pageIndex + 1}&startdate=<fmt:formatDate value="${sb.startdate }" pattern="yyyy-MM-dd"/>&enddate=<fmt:formatDate value="${sb.enddate }" pattern="yyyy-MM-dd"/>&kidId=${sb.kidId}">下一页&raquo;</a></li>
						<li
							<c:if test="${page.pageIndex == page.totalPageCount }">
						 class="disabled"
					</c:if>><a
							href="bodyrecord/more.do?type=${sb.type}&pageIndex=${page.totalPageCount }&startdate=<fmt:formatDate value="${sb.startdate }" pattern="yyyy-MM-dd"/>&enddate=<fmt:formatDate value="${sb.enddate }" pattern="yyyy-MM-dd"/>&kidId=${sb.kidId}">最后一页&raquo;</a></li>
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

				});
		$("#del").click(function(event) {
			var bool = confirm("您确定删除这些记录吗？");
			if (bool) {
				$.ajax({
					url : 'bodyrecord/remove.do',
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

