<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="index#articles_info">文章管理</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
	<h1>${article.title}</h1>
<fmt:formatDate value="${article.datetime}" pattern="yyyy年MM月dd日HH:mm:dd"/>
	<div>${article.content}</div>
	<div><img src="${article.pic}" alt="${article.pic}"/></div>
	<div>阅读&nbsp;${article.pageView}&nbsp;&nbsp;喜欢&nbsp;${article.favour}&nbsp;&nbsp;评论&nbsp;${article.comments.size()}&nbsp;&nbsp;分享&nbsp;${article.pageView}&nbsp;&nbsp;收藏&nbsp;${article.pageView}</div>
	<hr style="border-top: 1px solid black;"/>
	<h2>评论区（${article.comments.size()}）</h2>
	<c:forEach items="${article.comments}" var="comment">
		<div class="media">
   <a class="pull-left" href="#">
      <img class="media-object" src="${comment.ppic}" 
      alt="${comment.ppic}">
   </a>
   <div class="media-body">
      <h4 class="media-heading">${comment.pname}</h4>
      ${comment.content}
   </div>
</div>
	</c:forEach>
	</div>
</div>