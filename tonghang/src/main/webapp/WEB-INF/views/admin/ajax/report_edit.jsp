<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#" onclick="javacript:LoadAjaxContent('reports_info.do')">报告管理</a></li>
			<li><a href="#" onclick="javacript:LoadAjaxContent('report_edit.do')">修改报告</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-search"></i> <span>添加报告</span>
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
				<h4 class="page-header">填写报告</h4>
				<form class="form-horizontal" role="form" method="POST"
					id="defaultForm" action="report_edit_json.do">
					<input type="hidden" name="id" value="${param.id}">
					<div class="form-group">
						<label class="col-sm-2 control-label">标题</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="title"
								value="${report.title}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">作者</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="author"
								value="${report.author}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">内容</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="5" id="wysiwig_full"
								name="body">${report.body}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">建议</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="5" id="wysiwig_full2"
								name="suggest">${report.suggest}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">属于哪个用户</label>
						<div class="col-sm-4">
							<select class="populate placeholder" name="userId"
								id="s2_country">
								<option value="">-- 选择一个关联用户 --</option>
								<c:forEach items="${users}" var="user">
								<c:if test="${user.id==report.userId}"><option value="${user.id}" selected>${user.phone}</option></c:if>
									<option value="${user.id}">${user.phone}</option>
								</c:forEach>
							</select>
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
		//编辑富文本
		TinyMCEStart('#wysiwig_full', 'extreme');
		TinyMCEStart('#wysiwig_full2', 'extreme');
		//多选框
		$('#s2_country').select2();
		// Add tooltip to form-controls
		$('.form-control').tooltip();
		LoadBootstrapValidatorScript(validator);
		// Add drag-n-drop feature to boxes
		WinMove();
	});
	function validator() {
		$('#defaultForm').bootstrapValidator({
			feedbackIcons : {
				valid : 'fa fa-check',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			message : 'This value is not valid',
			fields : {
				title : {
					validators : {
						notEmpty : {
							message : '标题不能为空'
						}
					}
				},
				body : {
					validators : {
						notEmpty : {
							message : '内容不能为空'
						},
					}
				},
				user_id : {
					validators : {
						notEmpty : {
							message : '关联用户不能为空'
						},
					}
				}
			}
		});
	}
</script>
