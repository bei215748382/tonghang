<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
	<head>
		<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
		<meta charset="utf-8">
		<title>同行---管理后台</title>
		<meta name="description" content="description">
		<meta name="author" content="bwh">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="${ctx}/common/plugins/bootstrap/bootstrap.css" rel="stylesheet">
		<link href="${ctx}/common/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">
		<link href="${ctx}/common/css/admin/font-awesome.css" rel="stylesheet">
		<link href="${ctx}/common/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
		<link href="${ctx}/common/plugins/fullcalendar/fullcalendar.css" rel="stylesheet">
		<link href="${ctx}/common/plugins/xcharts/xcharts.min.css" rel="stylesheet">
		<link href="${ctx}/common/plugins/select2/select2.css" rel="stylesheet">
		<link href="${ctx}/common/css/admin/style.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
	</head>
<body>
<!--Start Header-->
<div id="screensaver">
	<canvas id="canvas"></canvas>
	<i class="fa fa-lock" id="screen_unlock"></i>
</div>
<div id="modalbox">
	<div class="devoops-modal">
		<div class="devoops-modal-header">
			<div class="modal-header-name">
				<span>Basic table</span>
			</div>
			<div class="box-icons">
				<a class="close-link">
					<i class="fa fa-times"></i>
				</a>
			</div>
		</div>
		<div class="devoops-modal-inner">
		</div>
		<div class="devoops-modal-bottom">
		</div>
	</div>
</div>
<header class="navbar">
	<div class="container-fluid expanded-panel">
		<div class="row">
			<div id="logo" class="col-xs-12 col-sm-2">
				<a href="index">同行</a>
			</div>
			<div id="top-panel" class="col-xs-12 col-sm-10">
				<div class="row">
					<div class="col-xs-8 col-sm-4">
						<a href="#" class="show-sidebar">
						  <i class="fa fa-bars"></i>
						</a>
						<div id="search">
							<input type="text" placeholder="search"/>
							<i class="fa fa-search"></i>
						</div>
					</div>
					<div class="col-xs-4 col-sm-8 top-panel-right">
						<ul class="nav navbar-nav pull-right panel-menu">
							<li class="hidden-xs">
								<a href="index.html" class="modal-link">
									<i class="fa fa-bell"></i>
									<span class="badge">7</span>
								</a>
							</li>
							<li class="hidden-xs">
								<a class="ajax-link" href="#">
									<i class="fa fa-calendar"></i>
									<span class="badge">7</span>
								</a>
							</li>
							<li class="hidden-xs">
								<a href="#" class="ajax-link">
									<i class="fa fa-envelope"></i>
									<span class="badge">7</span>
								</a>
							</li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle account" data-toggle="dropdown">
									<div class="avatar">
										<img src="${ctx}/common/img/admin/avatar.jpg" class="img-rounded" alt="avatar" />
									</div>
									<i class="fa fa-angle-down pull-right"></i>
									<div class="user-mini pull-right">
										<span class="welcome">Welcome,</span>
										<span>user1</span>
									</div>
								</a>
								<ul class="dropdown-menu">
									<li>
										<a href="logout.do">
											<i class="fa fa-power-off"></i>
											<span>Logout</span>
										</a>
									</li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</header>
<!--End Header-->
<!--Start Container-->
<div id="main" class="container-fluid">
	<div class="row">
		<div id="sidebar-left" class="col-xs-2 col-sm-2">
			<ul class="nav main-menu">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle">
						<i class="fa fa-table"></i>
						 <span class="hidden-xs">审核</span>
					</a>
					<ul class="dropdown-menu">
						<li><a class="ajax-link" href="index_info">信息审核</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle">
						<i class="fa fa-table"></i>
						 <span class="hidden-xs">管理</span>
					</a>
					<ul class="dropdown-menu">
						<li><a class="ajax-link" href=users_info>管理用户</a></li>
						<li><a class="ajax-link" href=services_info>管理服务</a></li>
						<li><a class="ajax-link" href=industry_info>管理产业链</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle">
						<i class="fa fa-table"></i>
						 <span class="hidden-xs">资讯</span>
					</a>
					<ul class="dropdown-menu">
						<li><a class="ajax-link" href="articles_info">文章管理</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle">
						<i class="fa fa-table"></i>
						 <span class="hidden-xs">统计</span>
					</a>
					<ul class="dropdown-menu">
						<li><a class="ajax-link" href="user_increase_info">用户增长</a></li>
						<li><a class="ajax-link" href="user_distribution_info">用户分布</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle">
						<i class="fa fa-table"></i>
						 <span class="hidden-xs">账户</span>
					</a>
					<ul class="dropdown-menu">
						<li><a class="ajax-link" href="admin_user_info">后台账户管理</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle">
						<i class="fa fa-table"></i>
						 <span class="hidden-xs">权限</span>
					</a>
					<ul class="dropdown-menu">
						<li><a class="ajax-link" href="admin_user_info">权限管理</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<!--Start Content-->
		<div id="content" class="col-xs-12 col-sm-10">
			<div class="preloader">
				<img src="${ctx}/common/img/admin/devoops_getdata.gif" class="devoops-getdata" alt="preloader"/>
			</div>
			<div id="ajax-content"></div>
		</div>
		<!--End Content-->
	</div>
</div>
<!--End Container-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!--<script src="http://code.jquery.com/jquery.js"></script>-->
<script src="${ctx}/common/plugins/jquery/jquery-2.1.0.min.js"></script>
<script src="${ctx}/common/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/common/plugins/bootstrap/bootstrap.min.js"></script>
<script src="${ctx}/common/plugins/justified-gallery/jquery.justifiedgallery.min.js"></script>
<script src="${ctx}/common/plugins/tinymce/tinymce.min.js"></script>
<script src="${ctx}/common/plugins/tinymce/jquery.tinymce.min.js"></script>
<!-- All functions for this theme + document.ready processing -->
<script src="${ctx}/common/js/admin/devoops.js"></script>
</body>
</html>
