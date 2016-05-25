<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="index.do">用户管理</a></li>
			<li><a href="index.do#users_info.do">手机注册用户表</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>手机注册用户</span>|<a href="#" onclick="javacript:LoadAjaxContent('user_add.do')">添加</a>
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
							<th><label><input type="text" name="search_rate"
									value="查找 手机号" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="查找 注册时间" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="查找 操作" class="search_init" /></label></th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${users}" var="user">
							<tr>
								<td>${user.id}</td>
								<td>${user.phone}</td>
								<td><fmt:formatDate value="${user.date}"
										pattern="yyyy-MM-dd HH:MM:ss" /></td>
								<td><a
									href="#" onclick="javacript:LoadAjaxContent('user_edit.do?id=${user.id}&phone=${user.phone}')">编辑</a>|<a
									href="user_delete.do?id=${user.id}">删除</a></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>手机号</th>
							<th>注册时间</th>
							<th>操作</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		
			<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>大众用户详细信息</span>
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
					id="datatable-4">
					<thead>
						<tr>
							<th><label><input type="text" name="search_rate"
									value="查找 id" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="查找 姓名" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="查找 年龄" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="查找 头像路径" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="查找 关联用户" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="查找 关联病人" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="查找 操作" class="search_init" /></label></th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${userExts}" var="user">
							<tr>
								<td>${user.id}</td>
								<td>${user.name}</td>
								<td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/></td>
								<td><img class="img-rounded" src="${user.pic }" alt="${user.pic }" onerror="this.src='../common/img/admin/avatar.jpg'"/>${user.pic }</td>
								<td>${user.userId }</td>
								<td>${user.patientId }</td>
								<td><a
									href="#" onclick="javacript:LoadAjaxContent('user_ext_edit.do?id=${user.id}')">编辑</a>|<a
									href="user_ext_delete.do?id=${user.id}">删除</a></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>姓名</th>
							<th>出生</th>
							<th>头像路径</th>
							<th>关联用户</th>
							<th>关联病人</th>
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
		TestTable4();
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
	function TestTable4(){
		var asInitVals = [];
		var oTable = $('#datatable-4').dataTable( {
			"aaSorting": [[ 0, "asc" ]],
			"sDom": "<'box-content'<'col-sm-6'f><'col-sm-6 text-right'l><'clearfix'>>rt<'box-content'<'col-sm-6'i><'col-sm-6 text-right'p><'clearfix'>>",
			"sPaginationType": "bootstrap",
			"oLanguage": {
				"sSearch": "",
				"sLengthMenu": '_MENU_'
			},
			bAutoWidth: false
		});
		var header_inputs = $("#datatable-4 thead input");
		header_inputs.on('keyup', function(){
			/* Filter on the column (the index) of this element */
			oTable.fnFilter( this.value, header_inputs.index(this) );
		})
		.on('focus', function(){
			if ( this.className == "search_init" ){
				this.className = "";
				this.value = "";
			}
		})
		.on('blur', function (i) {
			if ( this.value == "" ){
				this.className = "search_init";
				this.value = asInitVals[header_inputs.index(this)];
			}
		});
		header_inputs.each( function (i) {
			asInitVals[i] = this.value;
		});
	}
	$(document).ready(function() {
		// Load Datatables and run plugin on tables 
		LoadDataTablesScripts(AllTables);
		// Add Drag-n-Drop feature
		WinMove();
	});
</script>
