<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
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

<title>首页</title>

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
		<tag:header/>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h3>修改密码  <span style="color:red;">${result }</span></h3>
				</div>
				<form id="defaultForm" method="post" class="form-horizontal">

					<div class="form-group">
						<label class="col-lg-3 control-label">原密码</label>
						<div class="col-lg-5">
							<input type="password" class="form-control" name="prePassword"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">新密码</label>
						<div class="col-lg-5">
							<input type="password" class="form-control" name="password"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">重复新密码</label>
						<div class="col-lg-5">
							<input type="password" class="form-control" name="confirmPassword"/>
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
		</div>
	</div>
</body>
</html>
