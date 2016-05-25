<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
				<li><a href="index.do">医生交流平台</a></li>
			<li><a href="#" onclick="javacript:LoadAjaxContent('users_info.do')">用户信息管理</a></li>
			<li>用户提醒设置</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i>
					<span>用户提醒设置</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>
					<a class="expand-link">
						<i class="fa fa-expand"></i>
					</a>
					<a class="close-link">
						<i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content no-padding">
				<table class="table table-bordered table-striped table-hover table-heading table-datatable" id="datatable-1">
					<thead>
						<tr>
							<th>id</th>
							<th>类型</th>
							<th>提醒时间</th>
							<th>重复</th>
							<th>关联用户</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<!-- Start: list_row -->
						<c:forEach items="${reminds}" var="remind">
							<tr>
							<td>${remind.id}</td>
							<td>${remind.typeId}</td>
							<td><fmt:formatDate value="${remind.remindTime}" pattern="HH:mm:ss" /></td>
							<td>${remind.remindWeek}</td>
							<td>${remind.userId}</td>
							<td><a href="remind_delete_json.do?id=${remind.id}">删除</a></td>
						</tr>
						</c:forEach>
					<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
						<th>id</th>
							<th>类型</th>
							<th>提醒时间</th>
							<th>重复</th>
							<th>关联用户</th>
							<th>操作</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
// Run Datables plugin and create 3 variants of settings
function AllTables(){
	TestTable1();
	LoadSelect2Script(MakeSelect2);
}
function MakeSelect2(){
	$('select').select2();
	$('.dataTables_filter').each(function(){
		$(this).find('label input[type=text]').attr('placeholder', 'Search');
	});
}
$(document).ready(function() {
	// Load Datatables and run plugin on tables 
	LoadDataTablesScripts(AllTables);
	// Add Drag-n-Drop feature
	WinMove();
});
</script>
