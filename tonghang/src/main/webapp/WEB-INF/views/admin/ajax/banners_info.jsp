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
          <i class="fa fa-search"></i> <span>banner管理</span>&nbsp;&nbsp;
          <button type="button" class="btn btn-default"
            onclick="javaScript:LoadAjaxContent('banner_add')">添加banner</button>
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
              <th><label><input type="text"
                  name="search_rate" value="id" class="search_init" /></label></th>
              <th><label><input type="text"
                  name="search_rate" value="url" class="search_init" /></label></th>
              <th><label><input type="text"
                  name="search_rate" value="img" class="search_init" /></label></th>
                   <th><label><input type="text"
                  name="search_rate" value="order" class="search_init" /></label></th>
              <th><label><input type="text"
                  name="search_rate" value="操作" class="search_init" /></label></th>
            </tr>
          </thead>
          <tbody>
            <!-- Start: list_row -->
            <c:forEach items="${banners}" var="data">
              <tr>
                <td>${data.id}</td>
                <td>${data.url}</td>
                <td><img src="${data.img}" alt="${data.img}" /><br/>${data.img}</td>
                <td>${data.orderNo}</td>
                <td ><button type="button" class="btn btn-default" onclick="javaScript:LoadAjaxContent('banner_del?id=${data.id}')">删除</button><button type="button" class="btn btn-default" onclick="javaScript:LoadAjaxContent('banner_edit?id=${data.id}')">编辑</button></td>
              </tr>
            </c:forEach>
            <!-- End: list_row -->
          </tbody>
          <tfoot>
            <tr>
              <th>id</th>
              <th>url</th>
              <th>img</th>
              <th>order</th>
              <th>操作</th>
            </tr>
          </tfoot>
        </table>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
	function fun(id){
	console.log(id+","+this);
	}
	// Run Datables plugin and create 3 variants of settings
	function AllTables() {
		TestTable2();
		LoadSelect2Script(MakeSelect2);
	}
	function MakeSelect2() {
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
