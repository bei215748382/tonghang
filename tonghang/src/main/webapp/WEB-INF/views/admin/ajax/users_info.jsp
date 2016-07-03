<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					管理用户
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
									value="用户手机" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="姓名" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="所在产业" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="服务" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="分组" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="状态" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="活跃度" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="操作" class="search_init" /></label></th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${users}" var="data">
							<tr>
								<td>${data.phone}</td>
								<td>${data.name}</td>
								<td>${data.trade.name}</td>
								<td>${data.services.size()}</td>
								<td><c:if test="${data.groupId == 0}">普通</c:if><c:if test="${data.groupId == 1}">重要</c:if></td>
								<td><c:if test="${data.state == 1}">激活</c:if><c:if test="${data.state == 2}">冻结</c:if></td>
								<td></td>
								<td><button type="buttion"  class="btn btn-default"  onclick="javaScript:LoadAjaxContent('user_info?id=${data.id}')">查看用户</button></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>用户手机</th>
							<th>姓名</th>
							<th>所在行业</th>
							<th>服务</th>
							<th>分组</th>
							<th>状态</th>
							<th>活跃度</th>
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
	function AllTables() {
		TestTable2();
		LoadSelect2Script(MakeSelect2);
	}
	function MakeSelect2() {
		$('select').select2();
		$('.dataTables_filter').each(
				function() {
					$(this).find('label input[type=text]').attr('placeholder',
							'Search');
				});
	}
	$(document).ready(function() {
		// Load Datatables and run plugin on tables 
		LoadDataTablesScripts(AllTables);
		// Add Drag-n-Drop feature
		WinMove();
	});
</script>
