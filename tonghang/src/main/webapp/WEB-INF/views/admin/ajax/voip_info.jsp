<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
				<li><a href="index.do">医生交流平台</a></li>
			<li><a href="#" onclick="javacript:LoadAjaxContent('voip_info.do')">voip账号管理</a></li>
			<li>voip账号列表</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i>
					<span>voip账号查看---不能手动添加删除</span>
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
							<th>subAccountSid</th>
							<th>subToken</th>
							<th>voip账号</th>
							<th>voip密码</th>
							<th>创建日期</th>
							<th>friendlyName</th>
						</tr>
					</thead>
					<tbody>
					<!-- Start: list_row -->
						<c:forEach items="${data}" var="voip">
							<tr>
							<td>${voip.subAccountSid}</td>
							<td>${voip.subToken }</td>
							<td>${voip.voipAccount}</td>
							<td>${voip.voipPassword}</td>
							<td>${voip.dateCreated}</td>
							<td>${voip.friendName}</td>
						</tr>
						</c:forEach>
					<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>subAccountSid</th>
							<th>subToken</th>
							<th>voip账号</th>
							<th>voip密码</th>
							<th>创建日期</th>
							<th>friendlyName</th>
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
