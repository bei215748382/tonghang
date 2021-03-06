<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="index#articles_info">行业资讯</a></li>
			<li>添加文章</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-search"></i> <span>填写文章信息</span>
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
					id="articleForm" action="article_add_json"
					enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-sm-2 control-label">标题</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="title"
								/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">设置可见产业链</label>
						<div class="col-sm-4">
							<select class="populate placeholder" name=tradeId
								id="s2_country">
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
								id="s2_country">
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
						<input type="hidden" name="type" value="2">
								<div class="checkbox">
									<label> <input type="checkbox" name="checked" 
										value="1"> 发送推送<i class="fa fa-square-o small"></i>
									</label>
								</div>
								<div class="checkbox">
									<label> <input type="checkbox" name="hot" value="1">
										设置推荐 <i class="fa fa-square-o small"></i>
									</label>
								</div>
						</div>
					</div>	
					<div class="form-group">
						<label class="col-sm-2 control-label">添加封面</label>
						<div id="localImag" class="col-sm-4">
							<img id="preview" class="img-rounded" src="" alt="封面预览图"/>
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
						<label class="col-sm-2 control-label">编辑正文</label>
						<div class="col-sm-10">
              <script id="editor" type="text/plain" style="width:1024px;height:500px;" name=content></script>
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
	$(document).ready(function() {
// 		TinyMCEStart('#wysiwig_full', 'extreme');
		// Add tooltip to form-controls
		$('.form-control').tooltip();
		LoadBootstrapValidatorScript(DemoFormValidator);
		// Add drag-n-drop feature to boxes
		WinMove();
		
	    //实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	    var ue = UE.getEditor('editor');
	});
</script>
