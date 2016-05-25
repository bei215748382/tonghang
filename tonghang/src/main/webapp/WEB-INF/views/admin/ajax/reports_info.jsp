<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#" onclick="javacript:LoadAjaxContent('reports_info.do')">报告管理</a></li>
			<li><a href="#" onclick="javacript:LoadAjaxContent('reports_info.do')">报告列表</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>报告列表</span> | <a href="#"
									onclick="javacript:LoadAjaxContent('report_add.do')">添加</a>
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
									value="查找 id" class="search_init" /></label></th>
							<th><label><input type="text" name="search_name"
									value="查找 标题" class="search_init" /></label></th>
							<th><label><input type="text" name="search_votes"
									value="查找 作者" class="search_init" /></label></th>
							<th><label><input type="text" name="search_homepage"
									value="查找 内容" class="search_init" /></label></th>
							<th><label><input type="text" name="search_version"
									value="查找 建议" class="search_init" /></label></th>
							<th><label><input type="text" name="search_homepage"
									value="查找 时间" class="search_init" /></label></th>
							<th><label><input type="text" name="search_version"
									value="查找 用户" class="search_init" /></label></th>
							<th><label><input type="text" name="search_version"
									value="更多 操作" class="search_init" /></label></th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${reports}" var="report">
							<tr>
								<td>${report.id}</td>
								<td>${report.title}</td>
								<td>${report.author}</td>
								<td>${report.body}</td>
								<td>${report.suggest}</td>
								<td><fmt:formatDate value="${report.time}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${report.userId}</td>
								<td><a
									href="#"
									onclick="javacript:LoadAjaxContent('report_edit.do?id=${report.id}')">编辑</a>|<a
									href="report_delete.do?id=${report.id}">删除</a></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>标题</th>
							<th>作者</th>
							<th>内容</th>
							<th>建议</th>
							<th>时间</th>
							<th>用户</th>
							<th>操作</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
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
