<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<title>邀请人列表</title>

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
					<h3>邀请人  <span style="color:red;" id="ret">${result }</span></h3>
				</div>
				<c:choose>

					<c:when test="${empty invites}">
						您没有新邀请
					</c:when>
					<c:otherwise>
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>用户名</th>
									<th>邮箱</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${invites }" var="invite">
									<tr id="${invite.id }" leading="${invite.leading }" gong="${invite.gong.id }" shou="${invite.shou.id }">
										<td>${invite.gong.username }</td>
										<td>${invite.gong.email }</td>
										<td><a href="javascript:void(0)" id="${invite.id }"
											class="agree">同意</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="refuse.do?id=${invite.id }">拒绝</a></td>
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
	<script type="text/javascript">
		$(function (){
			$(".agree").click(function(event) {
				var inviteId = $(this).attr("id");
				var $tr = $("tr[id="+ inviteId +"]");
				var leading = $tr.attr("leading");
				var gong=$tr.attr("gong");
				var shou=$tr.attr("shou");
				var str;
				if(leading == gong){
					str = "同意将组为家庭(以对方为主导合并)\n\n█☆注意☆█\n您可以点击取消，确保孩子信息正确后再点击同意，将会有更良好的合并效果";
				}else if(leading == shou){
					str = "同意将组为家庭(以自己为主导合并)\n\n【】您可以点击取消，确保孩子信息正确后再点击同意，将会有更良好的合并效果";
				}else{
					str = "同意将组为家庭(不合并)"
				}
				var result = confirm(str);
				if(result){
					var kidid = $(this).attr('id');
					$.getJSON('together.do', {id: inviteId}, function(json, textStatus) {
						if(textStatus != "success" || json != true){
							$("#ret").text("合并失败");
						}else{
							//删除成功
							$("#ret").text("合并成功");
							$("#16").remove();
						}
					});
				}
			});
		});
	</script>
</body>
</html>
