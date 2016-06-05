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
					管理服务
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
									value="技能名称" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="技能说明" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="图片" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="姓名" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="用户手机" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="所在产业链" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="用户状态" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="操作" class="search_init" /></label></th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${services}" var="data">
							<tr>
								<td>${data.title}</td>
								<td>${data.description}</td>
								<td><c:forEach items="${data.pictures}" var="pic">
								<img src="${pic}" alt="${pic}" onerror='this.src="${ctx}/common/img/admin/avatar.jpg"'/>
								</c:forEach></td>
								<td>${data.name}</td>
								<td>${data.phone}</td>
								<td>${data.trade}</td>
								<td><c:if test="${data.state == 1}">激活</c:if><c:if test="${data.state == 2}">冻结</c:if></td>
								<td>查看服务</td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>技能名称</th>
							<th>技能说明</th>
							<th>图片</th>
							<th>姓名</th>
							<th>用户手机</th>
							<th>所在产业链</th>
							<th>用户状态</th>
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
