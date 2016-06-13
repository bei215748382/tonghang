<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
										<button type="button" class="btn btn-default">审核</button></td>
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
<script type="text/javascript">
	function user_info(id){
		var url = "getUserById";
		var data = {id:id};
		$.get(url,data,function(data){
			var form = '<div class="wid-3"><img src="'+data.pic+'" alt="头像" /></div>'
			+'<div class="wid-4">'
			+'<div>姓名：'+data.name+'</div>'
			+'<div>手机号码：'+data.phone+'</div>'
			+'<div>性别：'+data.sex+'</div>'
			+'<div>行业：'+data.trade+'</div>'
			+'<div>所在城市：'+data.city+'</div>'
			+'<div>个人标签：'+data.remark+'</div>'
			+'<br/>'
			+'<div>所在公司：'+data.company+'</div>'
			+'<div>职位：'+data.position+'</div>'
			+'<div>毕业院校：'+data.college+'</div>'
			+'</div>'
			+'<div class="wid-3">'
			+'<div>'
			+'	<select>'
			+'		<option>设置用户分组</option>'
			+'		<option value="2">重要客户</option>'
			+'		<option value="1" selected>普通客户（默认分组）</option>'
			+'	</select>'
			+'</div>'
			+'<br/>'
			+'<div>'
			+'	<select>'
			+'		<option>设置用户状态</option>'
			+'		<option value="1" selected>激活</option>'
			+'		<option value="2">冻结</option>'
			+'	</select>'
			+'</div>'
			+'</div>';
			OpenModalBox(id,form);
		});
	}
	var selected;
	function select(data) {
		selected = data;
		switch (selected) {
		case 1:
			LoadAjaxContent('index_info');
			break;
		case 2:
			LoadAjaxContent('get_service_unchecked');
			break;
		default:
			LoadAjaxContent('get_comment_unchecked');
		}
	}
	function uncheck() {
		console.log(selected);
		switch (selected) {
		case 1:
			LoadAjaxContent('index_info');
			break;
		case 2:
			LoadAjaxContent('get_service_unchecked');
			break;
		default:
			LoadAjaxContent('get_comment_unchecked');
		}
	}
	function check() {
		switch (selected) {
		case 1:
			LoadAjaxContent('get_circle_checked');
			break;
		case 2:
			LoadAjaxContent('get_service_checked');
			break;
		default:
			javacript: LoadAjaxContent('get_comment_checked');
		}
	}
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
	function TestTable4() {
		var asInitVals = [];
		var oTable = $('#datatable-4')
				.dataTable(
						{
							"aaSorting" : [ [ 0, "asc" ] ],
							"sDom" : "<'box-content'<'col-sm-6'f><'col-sm-6 text-right'l><'clearfix'>>rt<'box-content'<'col-sm-6'i><'col-sm-6 text-right'p><'clearfix'>>",
							"sPaginationType" : "bootstrap",
							"oLanguage" : {
								"sSearch" : "",
								"sLengthMenu" : '_MENU_'
							},
							bAutoWidth : false
						});
		var header_inputs = $("#datatable-4 thead input");
		header_inputs.on('keyup', function() {
			/* Filter on the column (the index) of this element */
			oTable.fnFilter(this.value, header_inputs.index(this));
		}).on('focus', function() {
			if (this.className == "search_init") {
				this.className = "";
				this.value = "";
			}
		}).on('blur', function(i) {
			if (this.value == "") {
				this.className = "search_init";
				this.value = asInitVals[header_inputs.index(this)];
			}
		});
		header_inputs.each(function(i) {
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
