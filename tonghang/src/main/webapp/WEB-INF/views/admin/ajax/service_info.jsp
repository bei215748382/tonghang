<%@ page language="java" contentType="text/html; charset=utf-8"
  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="row">
  <div class="col-xs-12">
    <div class="box">
      <div class="box-header">
        <div class="box-name">查看服务</div>
        <div class="box-icons">
          <a class="collapse-link"> <i class="fa fa-chevron-up"></i>
          </a> <a class="expand-link"> <i class="fa fa-expand"></i>
          </a> <a class="close-link"> <i class="fa fa-times"></i>
          </a>
        </div>
        <div class="no-move"></div>
      </div>
      <div class="box-content no-padding center">
        <h3>${service.title}</h3>
        <div>${service.content}</div>
        <div>阅读&nbsp;${service.pageView}&nbsp;&nbsp;喜欢&nbsp;${service.favour}&nbsp;&nbsp;评论&nbsp;${service.comment}&nbsp;&nbsp;分享&nbsp;${service.share}&nbsp;&nbsp;收藏&nbsp;1</div>
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
