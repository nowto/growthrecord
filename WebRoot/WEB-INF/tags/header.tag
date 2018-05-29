<%@ tag description="输出头部" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="totalNotifyNum" value="${vaccineNotifyNum + byInviteNotifyNum + inviteNotifyNum  }"></c:set>
<style>
#about{
	color:gray;
	font-size: 5px;
	padding-left: 30px;
}
</style>

<div class="row">
	<div class="col-md-12">
		<h1>成长脚印 系统 <span id="about"> by:李伟博&nbsp;反馈:<a href="mailto:liweibor@163.com">liweibor@163.com</a></span></h1>
		
	</div>
</div>
<div class="row">
	<div class="col-md-8 col-md-offset-3">
		<div class="btn-group ">
			<div class="btn-group">
				<button class="btn btn-default dropdown-toggle"
					data-toggle="dropdown" type="button">
					家庭管理<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="invite.do">邀请配偶</a></li>
					<li><a href="morelover.do">查看配偶</a></li>
				</ul>
			</div>
			<div class="btn-group">
				<button class="btn btn-default dropdown-toggle"
					data-toggle="dropdown" type="button">
					孩子管理<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="addkid.do">添加孩子</a></li>
					<li><a href="morekid.do">已有孩子</a></li>
				</ul>
			</div>
			<div class="btn-group">
				<button class="btn btn-default dropdown-toggle"
					data-toggle="dropdown" type="button">
					记录管理<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li>&nbsp;&nbsp;生理记录</li>
					<ul>
						<li><a href="bodyrecord/more.do?type=0">身高</a></li>
						<li><a href="bodyrecord/more.do?type=1">体重</a></li>
						<li><a href="bodyrecord/more.do?type=2">第一次</a></li>
						<li><a href="bodyrecord/more.do?type=3">其他</a></li>
						<li><a href="bodyrecord/add.do">添加</a></li>
					</ul>
					<li><a href="studyrecord/more.do">学业记录</a></li>
					<li>&nbsp;&nbsp;成长身影</li>
					<ul>
						<li><a href="figurerecord/more.do?type=0">图片</a></li>
						<li><a href="figurerecord/more.do?type=1">视频</a></li>
						<li><a href="figurerecord/add.do">添加</a></li>
					</ul>
				</ul>
			</div>


			<div class="btn-group">
				<button class="btn btn-default dropdown-toggle"
					data-toggle="dropdown" type="button">
					消息通知<span class="badge">${totalNotifyNum == 0 ? "" : totalNotifyNum}</span><span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="moreVaccinePlanNotify.do">疫苗提醒<span class="badge">${vaccineNotifyNum == 0 ? "" : vaccineNotifyNum}</span></a></li>
					<li><a href="moreByInvite.do">被邀为家人<span class="badge">${byInviteNotifyNum ==0 ? "" : byInviteNotifyNum}</span></a></li>
					<li><a href="moreInviteReply.do">邀请被回复<span class="badge">${inviteNotifyNum ==0 ? "" : inviteNotifyNum}</span></a></li>
				</ul>
			</div>
			
			<div class="btn-group">
				<button class="btn btn-default dropdown-toggle"
					data-toggle="dropdown" type="button">
					我<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="personal.do">个人资料</a></li>
					<li><a href="changepwd.do">修改密码</a></li>
					<li><a href="logout.do">退出登录</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
