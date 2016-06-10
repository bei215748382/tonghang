<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="index#articles_info">行业资讯</a></li>
			<li>编辑文章</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-search"></i> <span>修改文章信息</span>
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
				<h4 class="page-header">填写文章信息</h4>
				<form class="form-horizontal" role="form" method="POST"
					id="articleForm" action="article_edit_json"
					enctype="multipart/form-data">
					<input type="hidden" name="id" value="${article.id}">
					<input type="hidden" id="tradeValue" value="${article.trade.id}">
					<input type="hidden" id="cityValue" value="${article.area}">
					<div class="form-group">
						<label class="col-sm-2 control-label">标题</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="title" value="${article.title}"
								/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">设置可见产业链</label>
						<div class="col-sm-4">
							<select class="populate placeholder" name=tradeId
								id="trade">
								<option value="">-- 选择一个产业 --</option>
								<c:forEach items="${trades}" var="trade">
									<option value="${trade.id}">${trade.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">设置可见地区</label>
						<div class="col-sm-4">
							<select class="populate placeholder" name="area"
								id="area">
								<option value="">-- 选择一个城市 --</option>
								<c:forEach items="${cities}" var="city">
									<option value="${city.name}">${city.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">&nbsp;</label>
						<div class="col-sm-4">
								<div class="radio-inline">
									<label> <input type="radio" name="checked" 
										value="${article.checked}" id="checked"> 发送推送<i class="fa fa-circle-o"></i>
									</label>
								</div>
								<div class="radio-inline">
									<label> <input type="radio" name="hot" value="${article.hot}" id="hot">
										设置推荐 <i class="fa fa-circle-o"></i>
									</label>
								</div>
						</div>
					</div>	
					<div class="form-group">
						<label class="col-sm-2 control-label">添加封面</label>
						<div id="localImag" class="col-sm-4">
							<img id="preview" class="img-rounded" src="${article.pic}" alt="${article.pic}"/>
							<div class="margin-top-15">
								<input id="doc" type="file" name="file" value="${article.pic}"
									onchange="javascript:setImagePreview(this,localImag,preview);">
							</div>
						</div>
						<div class="col-sm-4">
							<p>说明：预览图的图片大小为400*300，图片尺寸以真实图片为准</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">编辑正文</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="5" id="wysiwig_full" name=content>${article.content}</textarea>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-2">
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
	function selected(){
		var tradeValue = $("#tradeValue").val();
		$("#trade").find("option[value='"+tradeValue+"']").attr("selected",true);//设置行业

		var cityValue = $("#cityValue").val();
		$("#area").find("option[value='"+cityValue+"']").attr("selected",true);//设置区域

		if($("#checked").val()==1){
			$("#checked").attr("checked","checked");
		}//是否发送
		if($("#hot").val()){
			$("#hot").attr("checked","checked");
		}//是否推荐
	}
	$(document).ready(function() {
		TinyMCEStart('#wysiwig_full', 'extreme');
		// Add tooltip to form-controls
		$('.form-control').tooltip();
		LoadBootstrapValidatorScript(DemoFormValidator);
		// Add drag-n-drop feature to boxes
		WinMove();
		selected();
	});
</script>
