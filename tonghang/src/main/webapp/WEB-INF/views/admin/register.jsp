<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>慢性病管家管理后台账户注册</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${ctx}/common/plugins/bootstrap/bootstrap.css"
	rel="stylesheet">
<link href="${ctx}/common/css/admin/font-awesome.css" rel="stylesheet">
<link href="${ctx}/common/css/admin/style.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
</head>
<body>
	<div class="container-fluid">
		<div id="page-login" class="row">
			<div
				class="col-xs-12 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
				<div class="text-right">
					<a href="login.do" class="txt-default">Already have an
						account?</a>
				</div>
				<form method="POST" id="defaultForm" action="registerUser.do">
					<div class="box">
						<div class="box-content">
							<div class="text-center">
								<h3 class="page-header">慢性病管理后台账户注册</h3>
							</div>
							<div class="form-group">
								<label class="control-label">手机号</label> <input type="text"
									class="form-control" name="phone" />
							</div>
							<div class="form-group">
								<label class="control-label">密码</label> <input
									type="password" class="form-control" name="password" />
							</div>
							<div class="checkbox">
								<label> <input type="checkbox" checked>
									同意接收推荐邮件！ <i class="fa fa-square-o small"></i>
								</label>
							</div>
							<div class="text-center">
								<button type="submit" class="btn btn-primary btn-label-left">
									<span><i class="fa fa-clock-o"></i></span>注册
								</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
