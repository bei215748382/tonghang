<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="index.do">用户信息管理</a></li>
			<li><a href="#"
				onclick="javacript:LoadAjaxContent('user_ext_edit.do')">修改用户详情</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-search"></i> <span>修改用户详情</span>
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
					id="defaultForm" action="user_ext_edit_json.do"
					enctype="multipart/form-data">
					<input type="hidden" name="id" value="${userExt.id}" />
					<div class="form-group">
						<label class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="name"
								value="${userExt.name}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">性别</label>
						<div class="col-sm-4">
							<c:if test="${userExt.sex=='男'}">
								<div class="radio-inline">
									<label> <input type="radio" name="sex" value="男"
										checked> 男 <i class="fa fa-circle-o"></i>
									</label>
								</div>
								<div class="radio-inline">
									<label> <input type="radio" name="sex" value="女">
										女 <i class="fa fa-circle-o"></i>
									</label>
								</div>
							</c:if>
							<c:if test="${userExt.sex=='女'}">
								<div class="radio-inline">
									<label> <input type="radio" name="sex" value="男">
										男 <i class="fa fa-circle-o"></i>
									</label>
								</div>
								<div class="radio-inline">
									<label> <input type="radio" name="sex" value="女"
										checked> 女 <i class="fa fa-circle-o"></i>
									</label>
								</div>
							</c:if>
							<c:if test="${userExt.sex==null || userExt.sex==''}">
								<div class="radio-inline">
									<label> <input type="radio" name="sex" value="男">
										男 <i class="fa fa-circle-o"></i>
									</label>
								</div>
								<div class="radio-inline">
									<label> <input type="radio" name="sex" value="女"
										checked> 女 <i class="fa fa-circle-o"></i>
									</label>
								</div>
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">是否怀孕</label>
						<div class="col-sm-4">
							<c:if test="${userExt.pregnant== 1}">
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
							</c:if>
							<c:if test="${userExt.pregnant==2}">
								<div class="radio-inline">
									<label> <input type="radio" name="pregnant" 
										value="1"> 没有<i class="fa fa-circle-o"></i>
									</label>
								</div>
								<div class="radio-inline">
									<label> <input type="radio" name="pregnant" checked value="2">
										怀孕 <i class="fa fa-circle-o"></i>
									</label>
								</div>
							</c:if>
							<c:if test="${userExt.pregnant==null || userExt.pregnant==''}">
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
							</c:if>

						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">身高</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="height" value="${userExt.height}"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">体重</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="weight" value="${userExt.weight}"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">关联的病人id</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="patientId"
								value="${userExt.patientId}" readonly="readonly" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">头像</label>
						<div id="localImag" class="col-sm-4">
							<img id="preview" class="img-rounded" src="${userExt.pic }"
								alt="${userExt.pic }" />
							<div class="margin-top-15">
								<input id="doc" type="file" name="file" value="${userExt.pic}"
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
							<select class="populate placeholder" name="userId"
								id="s2_country">
								<option value="">-- 选择一个关联用户 --</option>
								<c:forEach items="${users}" var="user">
									<c:if test="${user.id==userExt.userId}">
										<option value="${user.id}" selected>${user.phone}</option>
									</c:if>
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
	});
</script>
