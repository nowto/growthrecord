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

<title>添加孩子</title>

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
					<h3>添加孩子</h3>
				</div>

				<form id="addkidForm" method="post" class="form-horizontal">


					<div class="form-group">
						<label class="col-lg-3 control-label">姓名</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="name" value="${kid.name }"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">小名</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="nickname" value="${kid.nickname }" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">性别</label>
						<div class="col-lg-5">
							<div class="radio">
								<label> <input type="radio" name="sex" value="男"
									checked="checked" /> 男
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="sex" value="女" /> 女
								</label>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">生日</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="birthday" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">幼儿教育起止时间</label>
						<div class="col-lg-3">
							<input type="text" class="form-control" name="youstart" />
						</div>
						<div class="col-lg-1" style="text-align: center;">--</div>
						<div class="col-lg-3">
							<input type="text" class="form-control" name="youend" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">小学教育起止时间</label>
						<div class="col-lg-3">
							<input type="text" class="form-control" name="xiaostart" />
						</div>
						<div class="col-lg-1" style="text-align: center;">--</div>
						<div class="col-lg-3">
							<input type="text" class="form-control" name="xiaoend" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">初中教育起止时间</label>
						<div class="col-lg-3">
							<input type="text" class="form-control" name="chustart" />
						</div>
						<div class="col-lg-1" style="text-align: center;">--</div>
						<div class="col-lg-3">
							<input type="text" class="form-control" name="chuend" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">高中教育起止时间</label>
						<div class="col-lg-3">
							<input type="text" class="form-control" name="gaostart" />
						</div>
						<div class="col-lg-1" style="text-align: center;">--</div>
						<div class="col-lg-3">
							<input type="text" class="form-control" name="gaoend" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">高等教育起止时间</label>
						<div class="col-lg-3">
							<input type="text" class="form-control" name="dastart" />
						</div>
						<div class="col-lg-1" style="text-align: center;">--</div>
						<div class="col-lg-3">
							<input type="text" class="form-control" name="daend" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-9 col-lg-offset-3">
							<button type="submit" class="btn btn-primary" name="signup"
								value="Sign up">添加</button>
						</div>
					</div>
				</form>
			</div>
			</section>
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
							$('#captchaOperation').html(
									[ randomNumber(1, 100), '+',
											randomNumber(1, 200), '=' ]
											.join(' '));

							$('#addkidForm')
									.bootstrapValidator(
											{
												//        live: 'disabled',
												message : '该输入非法',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													name : {
														message : '该姓名输入非法',
														validators : {
															notEmpty : {
																message : '姓名必填且非空'
															},
															stringLength : {
																max : 10,
																message : '姓名最多为10个字符'
															},
														}
													},
													nickname : {
														message : '该小名输入非法',
														validators : {
															stringLength : {
																max : 10,
																message : '小名最多为10个字符'
															},
														}
													},
													birthday : {
														validators : {
															notEmpty : {
																message : '出生日期必填且非空'
															},
															date: {
										                        format: 'YYYY-MM-DD',
										                        message: '输入不符合格式(YYYY-MM-DD)'
										                    }
														}
													},
													youstart :{
														validators :{
															date: {
										                        format: 'YYYY-MM-DD',
										                        message: '输入不符合格式(YYYY-MM-DD)'
										                    }
														}
													},
													youend :{
														validators :{
															date: {
										                        format: 'YYYY-MM-DD',
										                        message: '输入不符合格式(YYYY-MM-DD)'
										                    }
														}
													},
													xiaostart :{
														validators :{
															date: {
										                        format: 'YYYY-MM-DD',
										                        message: '输入不符合格式(YYYY-MM-DD)'
										                    }
														}
													},
													xiaoend :{
														validators :{
															date: {
										                        format: 'YYYY-MM-DD',
										                        message: '输入不符合格式(YYYY-MM-DD)'
										                    }
														}
													},
													chustart :{
														validators :{
															date: {
										                        format: 'YYYY-MM-DD',
										                        message: '输入不符合格式(YYYY-MM-DD)'
										                    }
														}
													},
													chuend :{
														validators :{
															date: {
										                        format: 'YYYY-MM-DD',
										                        message: '输入不符合格式(YYYY-MM-DD)'
										                    }
														}
													},
													gaostart :{
														validators :{
															date: {
										                        format: 'YYYY-MM-DD',
										                        message: '输入不符合格式(YYYY-MM-DD)'
										                    }
														}
													},
													gaoend :{
														validators :{
															date: {
										                        format: 'YYYY-MM-DD',
										                        message: '输入不符合格式(YYYY-MM-DD)'
										                    }
														}
													},
													dastart :{
														validators :{
															date: {
										                        format: 'YYYY-MM-DD',
										                        message: '输入不符合格式(YYYY-MM-DD)'
										                    }
														}
													},
													daend :{
														validators :{
															date: {
										                        format: 'YYYY-MM-DD',
										                        message: '输入不符合格式(YYYY-MM-DD)'
										                    }
														}
													}
												}
											});

							// Validate the form manually
							$('#validateBtn').click(
									function() {
										$('#defaultForm').bootstrapValidator(
												'validate');
									});

							$('#resetBtn').click(
									function() {
										$('#defaultForm').data(
												'bootstrapValidator')
												.resetForm(true);
									});
						});
	</script>
</body>
</html>
