<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>个人资料</title>

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
<style type="text/css">
#noedit{
	color: red;
}
</style>
</head>

<body>

	<div class="container">
		<tag:header />
		<div class="row">
			<!-- form: -->
			<section>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h3>个人资料  <span style="color:red;">${result }</span></h3>
				</div>

				<form id="defaultForm" method="post" class="form-horizontal">

					<div class="form-group">
						<label class="col-lg-3 control-label">用户名</label>
						<div class="col-lg-5">
							<input type="hidden" name="id" value="${user.id }">
							<input type="hidden" name="homeId" value="${user.homeId }">
							<input type="hidden" name="home.id" value="${user.home.id }">
							<input type="text" class="form-control" name="username"
								value="${user.username }" readonly="readonly"/><span id="noedit">不可更改</span>
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">邮箱地址</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="email"
								value="${user.email }" />(找回密码使用，务必填写正确)
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">性别</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" 
								value="${user.sex }" readonly="readonly"/><span id="noedit">不可更改</span>
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">家庭名称</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="home.name"
								value="${user.home.name }" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">家庭描述</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="home.note"
								value="${user.home.note }" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-lg-9 col-lg-offset-3">
							<button type="submit" class="btn btn-primary" name="register"
								value="Register">修改</button>
						</div>
					</div>

				</form>
			</div>
			</section>
			<!-- :form -->
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
					;
					$('#captchaOperation').html(
							[ randomNumber(1, 100), '+', randomNumber(1, 200),
									'=' ].join(' '));

					$('#defaultForm').bootstrapValidator({
						//        live: 'disabled',
						message : 'This value is not valid',
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						fields : {
							email : {
								validators : {
									emailAddress : {
										message : '邮箱地址不合法'
									},
								/* remote : {
									url : 'isEmailUsed.do',
									message : '该邮箱已被使用'
								} */
								}
							},
							
							
							'home.name' : {
								validators : {
									stringLength : {
										max : 12,
										message : '家庭名称至多12个字符'
									}
								}
							},
							'home.note' : {
								validators : {
									stringLength : {
										max : 50,
										message : '家庭描述至多50个字符'
									}
								}
							}
						}
					});

				});
	</script>
</body>
</html>
