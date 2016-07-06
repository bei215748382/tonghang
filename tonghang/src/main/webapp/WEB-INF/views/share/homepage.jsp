<%@ page language="java" contentType="text/html; charset=utf-8"
  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="row">
  <div class="col-xs-12">
    <div class="box">
      <div class="box-header">
        <div class="box-name">homepage分享页</div>
        <div class="box-icons">
          <a class="collapse-link"> <i class="fa fa-chevron-up"></i>
          </a> <a class="expand-link"> <i class="fa fa-expand"></i>
          </a> <a class="close-link"> <i class="fa fa-times"></i>
          </a>
        </div>
        <div class="no-move"></div>
      </div>
      <div class="box-content no-padding center">
        <h3>个人信息</h3>
        <div>${user.name}</div>
        <div>${user.trade}</div>
        <div>${user.company}</div>
        <div>${user.position}</div>
        <h3>同行圈</h3>
        <h3>${circle.title}</h3>
        <div>${circle.datetime}</div>
        <div>阅读&nbsp;<div>${circle.pageView}</div> &nbsp;&nbsp;喜欢&nbsp;<div>${circle.favour}</div>&nbsp;&nbsp;评论&nbsp;<div>${circle.comment}</div>&nbsp;&nbsp;分享&nbsp;<div>${circle.share}</div>&nbsp;&nbsp;</div>
        <div>${circle.content}</div>
        <div>
          <c:forEach items="${circle.pics}" var="pic">
            <img src="${pic}" alt="${pic}"
              onerror='this.src="${ctx}/common/img/admin/avatar.jpg"' />
          </c:forEach>
        </div>
        <h3>服务</h3>
        <h3>${service.title}</h3>
        <div>${service.datetime}</div>
        <div>阅读&nbsp;<div>${service.pageView}</div> &nbsp;&nbsp;喜欢&nbsp;<div>${service.favour}</div>&nbsp;&nbsp;评论&nbsp;<div>${service.comment}</div>&nbsp;&nbsp;分享&nbsp;<div>${service.share}</div>&nbsp;&nbsp;</div>
        <div>${service.content}</div>
        <div>
          <c:forEach items="${service.pics}" var="pic">
            <img src="${pic}" alt="${pic}"
              onerror='this.src="${ctx}/common/img/admin/avatar.jpg"' />
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		// Add Drag-n-Drop feature
		WinMove();
	});
</script>
