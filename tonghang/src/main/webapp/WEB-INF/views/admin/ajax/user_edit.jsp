<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="index.do">用户信息管理</a></li>
			<li><a href="#" onclick="javacript:LoadAjaxContent('user_edit.do')">修改用户</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-search"></i> <span>修改用户信息</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="expand-link"> <i class="fa fa-expand"></i>
					</a> <a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content">
				<h4 class="page-header">填写用户信息</h4>
				<form class="form-horizontal" role="form" method="POST"
					id="defaultForm" action="user_edit_json.do"
					>
					<input type="hidden" name="id" value="${param.id}">
					<div class="form-group">
						<label class="col-sm-2 control-label">手机号</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="phone" value="${param.phone}" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">密码</label>
						<div class="col-sm-4">
							<input type="password" class="form-control" name="password" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-4">
							<input type="password" class="form-control"
								name="confirmPassword" />
						</div>
					</div>
					
					<div class="clearfix"></div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-2">
							<button type="cancel" class="btn btn-default btn-label-left">
								<span><i class="fa fa-clock-o txt-danger"></i></span> 取消
							</button>
						</div>
						<div class="col-sm-2">
							<button type="submit" class="btn btn-primary btn-label-left">
								<span><i class="fa fa-clock-o"></i></span> 提交
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		// Add tooltip to form-controls
		$('.form-control').tooltip();
		LoadBootstrapValidatorScript(validator);
		// Add drag-n-drop feature to boxes
		WinMove();
	});
	function validator(){
		$('#defaultForm').bootstrapValidator({
			feedbackIcons: {
	            valid: 'fa fa-check',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
			message: 'This value is not valid',
			fields: {
				password: {
					validators: {
						notEmpty: {
							message: '密码不能为空'
						},
						identical: {
							field: 'confirmPassword',
							message: '2次密码不一致'
						}
					}
				},
				confirmPassword: {
					validators: {
						notEmpty: {
							message: '确认密码不能为空'
						},
						identical: {
							field: 'password',
							message: '2次密码输入不一致，请重新输入'
						}
					}
				}
			}
		});
	}
</script>
