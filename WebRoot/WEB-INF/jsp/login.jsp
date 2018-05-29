<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>系统登录</title>

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

<!-- Include the FontAwesome CSS if you want to use feedback icons provided by FontAwesome -->
<!--<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" />-->

<script type="text/javascript" src="vendor/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="dist/js/bootstrapValidator.js"></script>
<script src="vendor/bootstrap-submenu/dist/js/bootstrap-submenu.min.js" defer></script>
</head>

<body>
	<div class="container">
		<div class="row">
			<!-- form: -->
			<section>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h2>成长脚印 登录 </h2><span style="color:red;">${result }</span>
				</div>

				<form id="defaultForm" method="post" class="form-horizontal">

					<div class="form-group">
						<label class="col-lg-3 control-label">用户名</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="username"
								value="${user.username }" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">密码</label>
						<div class="col-lg-5">
							<input type="password" class="form-control" name="password" />
						</div>
					</div>


					<div class="form-group">
						<div class="col-lg-9 col-lg-offset-3">
							<button type="submit" class="btn btn-primary" name="register"
								value="Register">登录</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="register.do" class="btn btn-default">去注册</a>
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
							username : {
								message : '用户名不合法',
								validators : {
									notEmpty : {
										message : '用户名不能为空'
									}
								}
							},
							password : {
								message : '密码不合法',
								validators : {
									notEmpty : {
										message : '密码不能为空'
									}
								}
							}
						}
					});

				});
	</script>
</body>
</html>
