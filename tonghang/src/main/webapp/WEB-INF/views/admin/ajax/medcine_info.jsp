<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
				<li><a href="index.do">医生交流平台</a></li>
			<li><a href="#" onclick="javacript:LoadAjaxContent('medcine_info.do')">药物管理</a></li>
			<li>药物列表</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i>
					<span>药物列表</span>|<a href="#" onclick="javacript:LoadAjaxContent('medcine_add')">添加</a>
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
							<th>名称</th>
							<th>图片</th>
							<th>描述</th>
							<th>类型</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<!-- Start: list_row -->
						<c:forEach items="${medcines}" var="medcine">
							<tr>
							<td>${medcine.id}</td>
							<td>${medcine.name }</td>
							<td><img  class="img-rounded" alt="${medcine.pic}" src="${medcine.pic}" />${medcine.pic}</td>
							<td>${medcine.description}</td>
							<td>${medcine.type}</td>
							<td><a href="#" onclick="javacript:LoadAjaxContent('medcine_edit?id=${medcine.id}')">编辑</a>|<a href="medcine_delete_json.do?id=${medcine.id}">删除</a></td>
						</tr>
						</c:forEach>
					<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
						<th>id</th>
							<th>名称</th>
							<th>图片</th>
							<th>描述</th>
							<th>类型</th>
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
