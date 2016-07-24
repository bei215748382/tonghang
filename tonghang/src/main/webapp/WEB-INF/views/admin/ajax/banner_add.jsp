<%@ page language="java" contentType="text/html; charset=utf-8"
  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
  <div id="breadcrumb" class="col-md-12">
    <ol class="breadcrumb">
      <li><a href="index#banners_info">banner管理</a></li>
      <li>添加banner</li>
    </ol>
  </div>
</div>
<div class="row">
  <div class="col-xs-12 col-sm-12">
    <div class="box">
      <div class="box-header">
        <div class="box-name">
          <i class="fa fa-search"></i> <span>填写banner信息</span>
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
        <h4 class="page-header">填写banner信息</h4>
        <form class="form-horizontal" role="form" method="POST"
          id="bannerForm" action="set_banner"
          enctype="multipart/form-data">
          <div class="form-group">
            <label class="col-sm-2 control-label">url</label>
            <div class="col-sm-4">
              <input type="text" class="form-control" name="url" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">添加封面</label>
            <div id="localImag" class="col-sm-4">
              <img id="preview" class="img-rounded" src="" alt="封面预览图" />
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
            <div class="col-sm-offset-2 col-sm-2">
              <button type="submit"
                class="btn btn-primary btn-label-left">
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
