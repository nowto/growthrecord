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

<title>添加疫苗提醒</title>

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
			<!-- form: -->
			<section>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h2>录入疫苗提醒</h2>
				</div>

				<form id="defaultForm" method="post" class="form-horizontal">

					<div class="form-group">
						<label class="col-lg-3 control-label">名称</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="name" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">描述</label>
						<div class="col-lg-5">
							<textarea rows="2" class="form-control" name="note"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">地点</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="site" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">开始日期</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="startDate" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">结束日期</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="endDate" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">最小年龄</label>
						<div class="col-lg-2">
							<input type="number" min="0" class="form-control" name="minYear" value="0"/>
						</div>
						<div class="col-lg-1">岁</div>
						<div class="col-lg-2">
							<input type="number" min="0" max="11" class="form-control" name="minMonth" value="0"/>
						</div>
						<div class="col-lg-1">个月</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">最大年龄</label>
						<div class="col-lg-2">
							<input type="number" min="0" class="form-control" name="maxYear" value="0"/>
						</div>
						<div class="col-lg-1">岁</div>
						<div class="col-lg-2">
							<input type="number" min="0" max="11" class="form-control" name="maxMonth" value="0"/>
						</div>
						<div class="col-lg-1">个月</div>
					</div>
					<div class="form-group">
						<div class="col-lg-9 col-lg-offset-3">
							<button type="submit" class="btn btn-primary" name="signup"
								value="Sign up">录入</button>
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

    $('#defaultForm').bootstrapValidator({
												//        live: 'disabled',
												message : 'This value is not valid',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													name : {
														message : '该名称不合法',
														validators : {
															notEmpty : {
																message : '名称不能为空'
															},
															stringLength : {
																max : 50,
																message : '名称最长50个字符'
															}
														}
													},
													site : {
														message : '该地点不合法',
														validators : {
															notEmpty : {
																message : '地点不能为空'
															},
															stringLength : {
																max : 50,
																message : '名称最长250个字符'
															}
														}
													},
													note : {
														validators : {
															stringLength : {
																max : 250,
																message : '描述最长250个字符'
															}
														}
													},
													startDate : {
														validators : {
															notEmpty : {
																message : '开始日期不能为空'
															},
															date : {
																format : 'YYYY-MM-DD',
																message : '输入不符合格式(YYYY-MM-DD)'
															}
														}
													},
													endDate : {
														validators : {
															notEmpty : {
																message : '结束日期不能为空'
															},
															date : {
																format : 'YYYY-MM-DD',
																message : '输入不符合格式(YYYY-MM-DD)'
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
