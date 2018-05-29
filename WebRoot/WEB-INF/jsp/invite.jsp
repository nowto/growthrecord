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

<title>邀请用户组为家庭</title>

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
			<!-- form: -->
			<section>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h3>
						邀请用户 组为家庭 <span style="color:red;">${result }</span>
					</h3>
				</div>

				<form id="defaultForm" method="post" class="form-horizontal">
					<div class="form-group">
						<label class="col-lg-3 control-label">要邀请的用户名</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="username"
								value="" />只能邀请一个<font color="red">异性</font>的用户组为家庭,且<font color="red">不可撤销</font>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">是否合并孩子及其记录</label>
						<div class="col-lg-5">
							<select class="form-control" name="leading">
								<option value="0">不合并</option>
								<option value="2">以自己为主导合并</option>
								<option value="3">以对方为主导合并</option>
							</select>
							(选择合并，将合并姓名、性别和生日都相同的孩子及其成长记录，
							其他不一样的数据将以您的选择为主导，
							比如发现双方都有个孩子姓名性别生日都相同，以您为主导的话，
							将以您添加的孩子数据为准进行合并
							<span style="color:red;">如果您希望合并有一个好结果</span>，
							请<span style="color:red;">确保孩子信息正确<span>后，再选择合并,邀请)
						</div>
					</div>

					<div class="form-group">
						<div class="col-lg-9 col-lg-offset-3">
							<button type="submit" class="btn btn-primary" name="register"
								value="Register">邀请</button>
						</div>
					</div>

				</form>
			</div>
			</section>
			<!-- :form -->
		</div>
	</div>
</body>
</html>
