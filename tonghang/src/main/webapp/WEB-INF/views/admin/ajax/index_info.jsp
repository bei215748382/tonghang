<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.wid-3{
	width:30%;
}

.wid-4{
	width:40%;
}
.wid-4,.wid-3{
	float:left;
}
</style>
<div class="row">
	<div class="col-md-12">
		<ul class="nav navbar-nav">
			<li><a href="#"
				onclick="select(1)">同行圈审核</a></li>
			<li><a href="#"
				onclick="select(2)">服务审核</a></li>
			<li><a href="#"
				onclick="select(3)">回复审核</a></li>
		</ul>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<a href="#" onclick="javacript:uncheck()">待审核</a>&nbsp;&nbsp;<a
						href="#" onclick="javacript:check()">已审核</a>
				</div>
				<div class="box-icons">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="expand-link"> <i class="fa fa-expand"></i>
					</a> <a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content no-padding">
				<table
					class="table table-bordered table-striped table-hover table-heading table-datatable"
					id="datatable-2">
					<thead>
						<tr>
							<th><label><input type="text" name="search_rate"
									value="图片" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="内容" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="作者状态" class="search_init" /></label></th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${dataList}" var="data">
							<tr>
								<td>${data.id}</td>
								<td>${data.content}</td>
								<td>${data.phone.name}<br/>
								<fmt:formatDate value="${data.datetime}"
										pattern="yyyy-MM-dd HH:MM:ss" /><br/>
										<button type="button" class="btn btn-default" onclick="user_info(${data.phone.id})">查看用户</button><br/>
										<button type="button" class="btn btn-default" onclick="checkCircle(${data.id})">审核</button></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>图片</th>
							<th>内容</th>
							<th>作者状态</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<script src="${ctx}/common/js/admin/index_info.js"></script>
<script type="text/javascript">
	// Run Datables plugin and create 3 variants of settings
	function AllTables() {
		TestTable2();
		LoadSelect2Script(MakeSelect2);
	}
	function MakeSelect2() {
		$('select').select2();
	}
	$(document).ready(function() {
		// Load Datatables and run plugin on tables 
		LoadDataTablesScripts(AllTables);
		// Add Drag-n-Drop feature
		WinMove();
	});
</script>
