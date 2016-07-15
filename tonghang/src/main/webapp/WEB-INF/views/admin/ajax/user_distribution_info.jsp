<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div class="col-xs-12">
		<h1>用户分布</h1>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">终端分布</div>
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
							<th><label>IOS（<fmt:formatNumber
										value=" ${device.ios_rate}" pattern="##.##"
										minFractionDigits="2"></fmt:formatNumber>%）
							</label></th>
							<th><label>Android（<fmt:formatNumber
										value=" ${device.android_rate}" pattern="##.##"
										minFractionDigits="2"></fmt:formatNumber>%）
							</label></th>
							<th><label>其他（<fmt:formatNumber
										value=" ${device.other_rate}" pattern="##.##"
										minFractionDigits="2"></fmt:formatNumber>%）
							</label></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${device.ios}</td>
							<td>${device.android}</td>
							<td>${device.other}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">领域分布</div>
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
							<th><label>原料（10%）</label></th>
							<th><label>胚布（20%）</label></th>
							<th><label>面料（10%）</label></th>
							<th><label>家纺（10%）</label></th>
							<th><label>服装（45%）</label></th>
							<th><label>辅料/皮革（10%）</label></th>
							<th><label>纺机/配件（45%）</label></th>
							<th><label>物流货运（20%）</label></th>
							<th><label>互联网/电商（10）</label></th>
							<th><label>其他周边（10%）</label></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1111</td>
							<td>1111</td>
							<td>111</td>
							<td>1111</td>
							<td>1111</td>
							<td>111</td>
							<td>1111</td>
							<td>1111</td>
							<td>111</td>
							<td>111</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>


<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">用户类型</div>
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
							<th><label>有服务</label></th>
							<th><label>无服务</label></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${map.peopleS}</td>
							<td>${map.peopleN}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">用户类型</div>
				<div class="box-icons">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="expand-link"> <i class="fa fa-expand"></i>
					</a> <a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content no-padding">
				<input type="hidden" value="${map.peopleC}" id="peopleC">
				<table
					class="table table-bordered table-striped table-hover table-heading table-datatable"
					id="datatable-2">
					<thead>
						<tr>
							<th><label>城市</label></th>
							<th><label>数量</label></th>
							<th><label>百分比</label></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${map.peopleC}" var="data">
							<tr>
								<td>${data.device}</td>
								<td>${data.num}</td>
								<td><fmt:formatNumber
										value=" ${data.num/(map.peopleS+map.peopleN)*100}"
										pattern="##.##" minFractionDigits="2"></fmt:formatNumber>%</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	// Run Datables plugin and create 3 variants of settings
	$(document).ready(function() {
		// Add Drag-n-Drop feature
		WinMove();
	});
</script>
