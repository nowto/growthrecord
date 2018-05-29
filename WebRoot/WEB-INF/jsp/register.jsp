<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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

<title>系统注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
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
					<h2>成长脚印 注册</h2>
				</div>

				<form id="defaultForm" method="post" class="form-horizontal">

					<div class="form-group">
						<label class="col-lg-3 control-label">用户名</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="username"
								value="${user.username }" />(注册后不可更改)
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">密码</label>
						<div class="col-lg-5">
							<input type="password" class="form-control" name="password" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">确认密码</label>
						<div class="col-lg-5">
							<input type="password" class="form-control"
								name="confirmPassword" />
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
							<div class="radio">
								<label> <input type="radio" name="sex" value="男" /> 男
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="sex" value="女" /> 女
								</label>
							</div>(注册后不可更改)
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
								value="Register">注册</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="login.do" class="btn btn-default">返回</a>
						</div>
					</div>

				</form>
			</div>
			</section>
			<!-- :form -->
		</div>
	</div>

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							// Generate a simple captcha
							function randomNumber(min, max) {
								return Math.floor(Math.random()
										* (max - min + 1) + min);
							}
							;
							$('#captchaOperation').html(
									[ randomNumber(1, 100), '+',
											randomNumber(1, 200), '=' ]
											.join(' '));

							$('#defaultForm')
									.bootstrapValidator(
											{
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
																message : '用户名必填且非空'
															},
															stringLength : {
																min : 1,
																max : 30,
																message : '用户名必须1-30个字符'
															},
															/* regexp : {
																regexp : /^[a-zA-Z0-9_\.]+$/,
																message : '用户名只能包含字母，数字，点和下划线'
															}, */
															remote : {
																url : 'usernamedb.do',
																message : '该用户名已被使用'
															},
															different : {
																field : 'password',
																message : '用户名不能与密码相同'
															}
														}
													},
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
													password : {
														validators : {
															notEmpty : {
																message : '密码必填且非空'
															},
															identical : {
																field : 'confirmPassword',
																message : '密码必须与确认密码相同'
															},
															different : {
																field : 'username',
																message : '密码不能与用户名相同'
															},
															stringLength : {
																min : 6,
																max : 16,
																message : '密码必须6-16个字符'
															},
														}
													},
													confirmPassword : {
														validators : {
															notEmpty : {
																message : '确认密码必填且非空'
															},
															identical : {
																field : 'password',
																message : '密码必须与确认密码相同'
															},
															different : {
																field : 'username',
																message : '密码不能与用户名相同'
															},
															stringLength : {
																min : 6,
																max : 16,
																message : '密码必须6-16个字符'
															},
														}
													},
													sex : {
														validators : {
															notEmpty : {
																message : '性别必选'
															}
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
