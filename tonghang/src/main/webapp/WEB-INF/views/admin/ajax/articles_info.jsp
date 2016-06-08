<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-file"></i><span>文章管理 </span>&nbsp;|&nbsp;<button type="button" class="btn btn-default" onclick="javaScript:LoadAjaxContent('article_add')" >添加文章</button>
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
									value="文章标题" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="发布时间" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="可见产业链" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="可见职能" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="浏览" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="喜欢" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="回复" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="推荐" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="操作" class="search_init" /></label></th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${articles}" var="data">
							<tr>
								<td>${data.title}</td>
								<td><fmt:formatDate value='${data.datetime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
								<td>${data.tradeId }</td>
								<td>可见职能</td>
								<td>${data.pageView}</td>
								<td>${data.favour}</td>
								<td>${data.comment}</td>
								<td><c:if test="${data.hot == 1}">是</c:if><c:if test="${data.hot == 2}">否</c:if></td>
								<td>查看 编辑 冻结</td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>文章标题</th>
							<th>发布时间</th>
							<th>可见产业链</th>
							<th>可见职能</th>
							<th>浏览</th>
							<th>喜欢</th>
							<th>回复</th>
							<th>推荐</th>
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
