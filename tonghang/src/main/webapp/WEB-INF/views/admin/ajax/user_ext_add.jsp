<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="index.do">用户信息管理</a></li>
			<li><a href="#"
				onclick="javacript:LoadAjaxContent('user_ext_add.do')">增加用户详情</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-search"></i> <span>增加用户详情</span>
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
					id="defaultForm" action="user_ext_add_json.do"
					enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="name" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">性别</label>
						<div class="col-sm-4">
							<div class="radio-inline">
								<label> <input type="radio" name="sex" checked value="男">
									男 <i class="fa fa-circle-o"></i>
								</label>
							</div>
							<div class="radio-inline">
								<label> <input type="radio" name="sex" value="女">
									女 <i class="fa fa-circle-o"></i>
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">是否怀孕</label>
						<div class="col-sm-4">
							<div class="radio-inline">
								<label> <input type="radio" name="pregnant" checked
									value="1"> 没有<i class="fa fa-circle-o"></i>
								</label>
							</div>
							<div class="radio-inline">
								<label> <input type="radio" name="pregnant" value="2">
									怀孕 <i class="fa fa-circle-o"></i>
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">身高</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="height" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">体重</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="weight" />
						</div>
					</div>
					<div class="form-group has-feedback">
						<label class="col-sm-2 control-label">出生年月</label>
						<div class="col-sm-2">
							<input type="text" id="input_date" class="form-control"
								placeholder="Date" name="birthday"> <span
								class="fa fa-calendar txt-danger form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">头像</label>
						<div id="localImag" class="col-sm-4">
							<img id="preview" class="img-rounded" alt="头像" />
							<div class="margin-top-15">
								<input id="doc" type="file" name="file"
									onchange="javascript:setImagePreview(this,localImag,preview);">
							</div>
						</div>
						<div class="col-sm-4">
							<p>说明：预览图的图片大小为400*300，图片尺寸以真实图片为准</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">属于哪个用户</label>
						<div class="col-sm-4">
							<select class="populate placeholder" name="userId" id="userId">
								<option value="">-- 选择一个关联用户 --</option>
								<c:forEach items="${users}" var="user">
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
		// Add tooltip to form-controls
		$('.form-control').tooltip();
		LoadBootstrapValidatorScript(DemoFormValidator);
		// Add drag-n-drop feature to boxes
		WinMove();
		// Initialize datepicker
		$('#input_date').datepicker({
			setDate : new Date()
		});
		$("#userId").select2();
	});

	function DemoFormValidator(){

		$('#defaultForm').bootstrapValidator(
				{
					feedbackIcons : {
						valid : 'fa fa-check',
						invalid : 'glyphicon glyphicon-remove',
						validating : 'glyphicon glyphicon-refresh'
					},
					message : 'This value is not valid',
					fields : {
						userId : {
							validators : {
								notEmpty : {
									message : '关联用户不能为空'
								}
							}
						},
						birthday : {
							validators : {
								notEmpty : {
									message : '日期不能为空'
								},
								date : {
									format : 'MM/DD/YYYY',
									message : '格式不正确'
								}
							}
						}
					}
				});
		}
</script>
