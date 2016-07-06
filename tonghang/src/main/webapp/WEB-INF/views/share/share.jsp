<%@ page language="java" contentType="text/html; charset=utf-8"
  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<title>分享页</title>
<div class="row">
  <div class="col-xs-12">
    <div class="box">
      <div class="box-header">
        <div class="box-name"></div>
        <div class="box-icons">
          <a class="collapse-link"> <i class="fa fa-chevron-up"></i>
          </a> <a class="expand-link"> <i class="fa fa-expand"></i>
          </a> <a class="close-link"> <i class="fa fa-times"></i>
          </a>
        </div>
        <div class="no-move"></div>
      </div>
      <div class="box-content no-padding center">
       <h3>${bean.title}</h3>
        <div>${bean.datetime}</div>
        <div>阅读&nbsp;<div>${bean.pageView}</div> &nbsp;&nbsp;喜欢&nbsp;<div>${bean.favour}</div>&nbsp;&nbsp;评论&nbsp;<div>${bean.comment}</div>&nbsp;&nbsp;分享&nbsp;<div>${bean.share}</div>&nbsp;&nbsp;</div>
        <div>${bean.content}</div>
          <c:forEach items="${bean.imgs}" var="img">
            <img src="${img}!cover.h" alt="${img}"
              onerror='this.src="${ctx}/common/img/admin/avatar.jpg"' />
          </c:forEach>
          <div>评论区</div>
           <c:forEach items="${bean.comments}" var="comment">
            <img src="${comment.userinfo.pic}!small.w" alt="${comment.userinfo.pic}"/><div>${comment.userinfo.name}</div> 
            <div>评论内容:${comment.content}</div>
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
