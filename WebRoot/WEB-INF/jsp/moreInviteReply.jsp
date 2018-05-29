<%@page import="cn.edu.zzti.entity.Invite"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>邀请回复列表</title>

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
					<h3>
						回复表 <span style="color:red;">${result }</span>
					</h3>
				</div>
				<c:choose>

					<c:when test="${empty invites}">
						您没有新回复
					</c:when>
					<c:otherwise>
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>用户名</th>
									<th>邮箱</th>
									<th>回复</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${invites }" var="invite">
									<tr id="${invite.id }" leading="${invite.leading }"
										gong="${invite.gong.id }" shou="${invite.shou.id }">
										<td>${invite.shou.username }</td>
										<td>${invite.shou.email }</td>
										<td>
											<c:choose>
												<c:when test="${invite.agree == null }">
													拒绝了您的邀请
												</c:when>
												<c:otherwise>
													<c:if test="${invite.agree == true }">
													同意了，已组为家庭
													</c:if>														
												</c:otherwise>
											</c:choose>
										</td>
										<td><a href="haveread.do?id=${invite.id }" title="标记后，将找寻不到此消息">标记已读</a>
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
	<!-- <script type="text/javascript">
		$(function() {
			$(".agree").click(function(event) {
				var inviteId = $(this).attr("id");
				var $tr = $("tr[id=" + inviteId + "]");
				var leading = $tr.attr("leading");
				var gong = $tr.attr("gong");
				var shou = $tr.attr("shou");
				var str;
				if (leading = gong) {
					str = "同意将组为家庭(以对方为主导合并）";
				} else if (leading = shou) {
					str = "同意将组为家庭(以自己为主导合并)"
				} else {
					str = "同意将组为家庭(不合并)"
				}
				var result = confirm(str);
			});
		});
	</script> -->
</body>
</html>
