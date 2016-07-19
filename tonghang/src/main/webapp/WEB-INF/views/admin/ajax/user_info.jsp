<%@ page language="java" contentType="text/html; charset=utf-8"
  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="row">
  <div id="breadcrumb" class="col-md-12">
    <ol class="breadcrumb">
      <li><a href="#">${user.name}</a></li>
    </ol>
  </div>
</div>
<div class="row">
  <div class="col-xs-12 col-sm-12">
    <div class="box">
      <div class="box-header">
        <div class="box-name">
          <i class="fa fa-search"></i> <span>id:${user.id}</span>
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
        <input type="hidden" value="${user.id}" id="uid" />
        <div class="wid-3">
          <img src="${user.pic}" alt="头像" style="max-width: 100%" />
        </div>
        <div class="wid-4">
          <div>姓名：${user.name}</div>
          <div>手机号码：${user.phone}</div>
          <div>性别：${user.sex}</div>
          <div>行业：${user.trade}</div>
          <div>所在城市：${user.city}</div>
          <div>个人标签：${user.remark}</div>
          <br />
          <div>所在公司：${user.company}</div>
          <div>职位：${user.position}</div>
          <div>毕业院校：${user.college}</div>
        </div>
        <div class="wid-3">
          <div>
            <select id="group">
              <option>设置用户分组</option>
              <option value="2">重要客户</option>
              <option value="1" selected>普通客户（默认分组）</option>
            </select>
          </div>
          <br />
          <div>
            <select id="state">
              <option>设置用户状态</option>
              <option value="1" selected>激活</option>
              <option value="2">冻结</option>
            </select>
          </div>
        </div>
        <div class="clearfix"></div>
      </div>
    </div>
  </div>
</div>

<div class="row">
  <div class="col-xs-12 col-sm-12">
    <div class="box">
      <div class="box-header">
        <div class="box-name">
          <i class="fa fa-search"></i> <span>他的服务</span>
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
        <table
          class="table table-bordered table-striped table-hover table-heading table-datatable"
          id="datatable-2">
          <thead>
            <tr>
              <th><label><input type="text"
                  name="search_rate" value="图片" class="search_init" /></label></th>
              <th><label><input type="text"
                  name="search_rate" value="说明" class="search_init" /></label></th>
              <th><label><input type="text"
                  name="search_rate" value="操作" class="search_init" /></label></th>
            </tr>
          </thead>
          <tbody>
            <!-- Start: list_row -->
            <c:forEach items="${services}" var="data">
              <tr>
                <td><c:forEach items="${data.pictures}" var="pic">
                    <img src="${pic}" alt="${pic}"
                      onerror='this.src="${ctx}/common/img/admin/avatar.jpg"' />
                  </c:forEach></td>
                <td>${data.title}<br />${data.description}
                </td>
                <td>${data.name}<br /> <fmt:formatDate
                    value="${data.timestamp}"
                    pattern="yyyy-MM-dd HH:MM:ss" /><br /> <c:if
                    test="${data.checked == 1}">审核通过</c:if> <c:if
                    test="${data.checked != 1}">
                    <button type="button" class="btn btn-default"
                      onclick="checkService(${data.id})">审核</button></td>
                </c:if>
              </tr>
            </c:forEach>
            <!-- End: list_row -->
          </tbody>
          <tfoot>
            <tr>
              <th>图片</th>
              <th>说明</th>
              <th>操作</th>
            </tr>
          </tfoot>
        </table>
      </div>
    </div>
  </div>
</div>


<div class="row">
  <div class="col-xs-12 col-sm-12">
    <div class="box">
      <div class="box-header">
        <div class="box-name">
          <i class="fa fa-search"></i> <span>他的行为</span>&nbsp;&nbsp;|&nbsp;&nbsp;
          <button type="button" class="btn btn-default"
            onclick="hisCircle(${user.id})">他发布的朋友圈</button>
          &nbsp;&nbsp;|&nbsp;&nbsp;
          <button type="button" class="btn btn-default"
            onclick="hisComment(${user.id})">他发布的评论</button>
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
        <table
          class="table table-bordered table-striped table-hover table-heading table-datatable"
          id="user_behavior_table">
        </table>
      </div>
    </div>
  </div>
</div>
<script src="${ctx}/common/js/admin/user_info.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		hisCircle($("#uid").val());
		// Add tooltip to form-controls
		$('.form-control').tooltip();
		// Add drag-n-drop feature to boxes
		WinMove();
		var uid = $("#uid").val();
		$("#group").change(function(){
			  $.post("user_update",{id:uid,groupId:$(this).val()},function(data){
				  console.log("更新群组操作成功");
			  });
			});
		$("#state").change(function(){
		    $.post("user_update",{id:uid,state:$(this).val()},function(data){
				  console.log("更新状态操作成功");
			  });
		});
	});
</script>
