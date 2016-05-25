<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>慢性病管理后台登入</title>
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
					<a href="register.do" class="txt-default">需要一个账户？</a>
				</div>
				<form method="POST" id="defaultForm" action="login.do">
					<div class="box">
						<div class="box-content">
							<div class="text-center">
								<h3 class="page-header">慢性病管理后台登入</h3>
							</div>
							<div class="form-group">
								<label class="control-label">用户名：</label> <input type="text"
									class="form-control" name="username" />
							</div>
							<div class="form-group">
								<label class="control-label">密码：</label> <input type="password"
									class="form-control" name="password" />
							</div>
							<div class="text-center">
								<button type="submit" class="btn btn-primary btn-label-left">
									<span><i class="fa fa-clock-o"></i></span>登入
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
