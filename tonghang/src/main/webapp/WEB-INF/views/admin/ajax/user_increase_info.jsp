<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div class="col-xs-12">
		<h1>用户增长</h1>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">用户增长</div>
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
							<th><label>IOS</label></th>
							<th><label>Android</label></th>
							<th><label>其他</label></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>98</td>
							<td>98</td>
							<td>98</td>
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
				<div class="box-name">三十日增长曲线</div>
				<div class="box-icons">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="expand-link"> <i class="fa fa-expand"></i>
					</a> <a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content no-padding">
				<div id="echart-3" style="height: 600px;"></div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
//Draw all test xCharts
function drawLine(){
	  // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echart-3'));
	var date=[];
	var data1=[];
	var data2=[];
	var data3=[];
	for(var i = 0;i<30;i++){
		date.push(i);
		data1.push(Math.round(Math.random()*100));
		data2.push(Math.round(Math.random()*100));
		data3.push(Math.round(Math.random()*100));
	}
	
    // 指定图表的配置项和数据
    var option = {
    	    tooltip: {
    	        trigger: 'axis',
    	        position: function (pt) {
    	            return [pt[0], '10%'];
    	        }
    	    },
    	    title: {
    	        text: '用户数据增长图',
    	    },
    	    legend: {
    	        data:['IOS','Android','其他']
    	    },
    	    toolbox: {
    	        show: true,
    	        feature: {
    	            dataView: {show: true, readOnly: false},
    	            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
    	            restore: {show: true},
    	            saveAsImage: {show: true}
    	        }
    	    },
    	    xAxis: {
    	        type: 'category',
    	        boundaryGap: false,
    	        data: date
    	    },
    	    yAxis: {
    	        type: 'value',
    	        boundaryGap: [0, '100%']
    	    },
    	    dataZoom: [{
    	        type: 'inside',
    	        start: 0,
    	        end: 30
    	    }, {
    	        start: 0,
    	        end: 30
    	    }],
    	    series: [
    	        {
    	            name:'IOS',
    	            type:'bar',
    	            smooth:true,
    	            symbol: 'none',
    	            sampling: 'average',
    	            itemStyle: {
    	                normal: {
    	                    color: 'rgb(255, 0, 0)'
    	                }
    	            },
    	            areaStyle: {
    	                normal: {
    	                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
    	                        offset: 0,
    	                        color: 'rgb(255, 0, 0)'
    	                    }, {
    	                        offset: 1,
    	                        color: 'rgb(100, 0, 0)'
    	                    }])
    	                }
    	            },
    	            data: data1
    	        },{
    	            name:'Android',
    	            type:'bar',
    	            smooth:true,
    	            symbol: 'none',
    	            sampling: 'average',
    	            itemStyle: {
    	                normal: {
    	                    color: 'rgb(255, 255, 0)'
    	                }
    	            },
    	            areaStyle: {
    	                normal: {
    	                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
    	                        offset: 0,
    	                        color: 'rgb(255, 255, 0)'
    	                    }, {
    	                        offset: 1,
    	                        color: 'rgb(255, 0, 131)'
    	                    }])
    	                }
    	            },
    	            data: data2
    	        },{
    	            name:'其他',
    	            type:'bar',
    	            smooth:true,
    	            symbol: 'none',
    	            sampling: 'average',
    	            itemStyle: {
    	                normal: {
    	                    color: 'rgb(0, 0, 255)'
    	                }
    	            },
    	            areaStyle: {
    	                normal: {
    	                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
    	                        offset: 0,
    	                        color: 'rgb(0, 0, 255)'
    	                    }, {
    	                        offset: 1,
    	                        color: 'rgb(0, 0, 131)'
    	                    }])
    	                }
    	            },
    	            data: data3
    	        }
    	    ]
    	};


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}
	$(document).ready(function() {
		// Add Drag-n-Drop feature
		WinMove();
		//画30日增长曲线
		drawLine();
	});
</script>
