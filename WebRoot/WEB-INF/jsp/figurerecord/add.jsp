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

<title>添加成长身影</title>

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
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h3>
						添加成长身影 <span style="color:red;">${result }</span>
					</h3>
				</div>
				<form id="addbodyForm" method="post" class="form-horizontal" enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-lg-3 control-label">孩子</label>
						<div class="col-lg-5">
							<select name="kidId" class="form-control" id="choosekid">
								<c:forEach items="${kids }" var="kid">
									<option value="${kid.id }">${kid.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">类型</label>
						<div class="col-lg-5">
							<select name="type" class="form-control" id="choosetype">
								<option value="0">图片</option>
								<option value="1">视频</option>
							</select>
						</div>
					</div>
					<div class="form-group" id="note">
						<label class="col-lg-3 control-label">图片</label>
						<div class="col-lg-5">
							<input type="file" id="file" class="form-control" name="file" accept="image/png,image/jpeg"/>
						</div>
					</div>
					<div class="form-group" id="note">
						<label class="col-lg-3 control-label">描述</label>
						<div class="col-lg-5">
							<textarea rows="3" name="note" cols="3" class="form-control"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">时间</label>
						<div class="col-lg-5">
							<input type="text" id="date" class="form-control"
								name="recordDate" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-9 col-lg-offset-3">
							<button type="submit" class="btn btn-primary" name="register"
								value="Register">添加</button>
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(
				function() {
					//扩展Date的format方法   
					Date.prototype.format = function(format) {
						var o = {
							"M+" : this.getMonth() + 1,
							"d+" : this.getDate(),
							"h+" : this.getHours(),
							"m+" : this.getMinutes(),
							"s+" : this.getSeconds(),
							"q+" : Math.floor((this.getMonth() + 3) / 3),
							"S" : this.getMilliseconds()
						}
						if (/(y+)/.test(format)) {
							format = format.replace(RegExp.$1, (this.getFullYear() + "")
									.substr(4 - RegExp.$1.length));
						}
						for ( var k in o) {
							if (new RegExp("(" + k + ")").test(format)) {
								format = format.replace(RegExp.$1,
										RegExp.$1.length == 1 ? o[k] : ("00" + o[k])
												.substr(("" + o[k]).length));
							}
						}
						return format;
					}
					//调用
					$(function() {
						var now = new Date();
						document.getElementById("date").setAttribute("value",
								now.format("yyyy-MM-dd"));
					});
					// Generate a simple captcha
					function randomNumber(min, max) {
						return Math
								.floor(Math.random() * (max - min + 1) + min);
					}
					$('#captchaOperation').html(
							[ randomNumber(1, 100), '+', randomNumber(1, 200),
									'=' ].join(' '));

					$('#addbodyForm').bootstrapValidator({
						//        live: 'disabled',
						message : '该输入非法',
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						fields : {
							note : {
								validators : {
									stringLength : {
										max : 250,
										message : '描述须小于250个字符'
									}
								}
							},
							recordDate : {
								validators : {
									notEmpty : {
										message : '时间必填且非空'
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
					$('#validateBtn').click(function() {
						$('#defaultForm').bootstrapValidator('validate');
					});

					$('#resetBtn').click(
							function() {
								$('#defaultForm').data('bootstrapValidator')
										.resetForm(true);
							});
				});
	</script>
	<script type="text/javascript">
		$(function() {
			$("#choosetype").change(
					function() {
						$("#note").find("label").text(
								$(this).find("option:selected").text());
						if($(this).find("option:selected").text() == "图片"){
							$("#file").attr('accept', 'image/png,image/jpeg');
						}else if($(this).find("option:selected").text() == "视频"){
							$("#file").attr('accept', 'video/mp4, video/ogg, video/webM');
						}
					});

		});
	</script>
</body>
</html>
