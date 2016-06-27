<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					后台账户&nbsp;&nbsp;|&nbsp;&nbsp;<button  type="button" class="btn btn-default" onclick="openModal()">添加</button>
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
									value="账户名" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="账户类型" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="说明" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="操作" class="search_init" /></label></th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${users}" var="data">
							<tr>
								<td>${data.username}</td>
								<td>${data.role}</td>
								<td>${data.description}</td>
								<td><button type="button" class="btn btn-default" onclick="edit(${data.id})">编辑</button></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script src="${ctx}/common/js/admin/admin_user_info.js"></script>
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
