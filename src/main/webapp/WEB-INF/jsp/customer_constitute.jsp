<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="width: 600px;height:400px;"></div>
</body>	
<script type="text/javascript">
$(function(){
    var arrList = new Array();   
    arrList = "${customerLevels}".replace('[','').replace(']','').split(',');   

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '客户构成分析'
        },
        tooltip: {},
        legend: {
            data:['数量']
        },
        xAxis: {
            data: arrList
        },
        yAxis: {},
        series: [{
            name: '数量',
            type: 'bar',
            data: ${customerCounts}
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    
})
</script>
</html>