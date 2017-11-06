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
	<div id="main" style="width: 600px;height:500px;"></div>
</body>	
<script type="text/javascript">
$(function(){
	 options = {
	    		url : '${ctx}/customerConsumption/getCakes.do',
	    		dataType : 'json',
	    		success : function(news){
	    			var cakeList = new Array();
	    			var serveTypesList = new Array();
	    			for(var i=0;i<news.length;i++){
	    				cakeList.push(news[i]);
	    				serveTypesList.push(news[i].name)
	    			}
	    		    // 基于准备好的dom，初始化echarts实例
	    		    var myChart = echarts.init(document.getElementById('main'));
	    		    // 指定图表的配置项和数据
	    		    var option = {
	    		    	    tooltip: {
	    		    	        trigger: 'item',
	    		    	        formatter: "{a} <br/>{b}: {c} ({d}%)"
	    		    	    },
	    		    	    legend: {
	    		    	        orient: 'vertical',
	    		    	        x: 'left',
	    		    	        data: serveTypesList
	    		    	    },
	    		    	    series: [
	    		    	        {
	    		    	            name:'访问来源',
	    		    	            type:'pie',
	    		    	            radius: ['50%', '70%'],
	    		    	            avoidLabelOverlap: false,
	    		    	            label: {
	    		    	                normal: {
	    		    	                    show: false,
	    		    	                    position: 'center'
	    		    	                },
	    		    	                emphasis: {
	    		    	                    show: true,
	    		    	                    textStyle: {
	    		    	                        fontSize: '30',
	    		    	                        fontWeight: 'bold'
	    		    	                    }
	    		    	                }
	    		    	            },
	    		    	            labelLine: {
	    		    	                normal: {
	    		    	                    show: false
	    		    	                }
	    		    	            },
	    		    	            data: cakeList
	    		    	        }
	    		    	    ]
	    		    	};

	    		    // 使用刚指定的配置项和数据显示图表。
	    		    myChart.setOption(option);
	    		}
	    }
	$.ajax(options)
})
</script>
</html>