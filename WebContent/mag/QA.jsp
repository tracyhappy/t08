<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<jsp:useBean id="trans" scope="page" class="com.mingri.chinese.ToChinese" />

<%@ page import="java.io.*,java.sql.*"%>
<jsp:useBean id="rst" scope="page" class="com.mingri.dbconn.DBResult"/>
<jsp:useBean id="qu" scope="page" class="com.mingri.info.QuestString" />
<jsp:useBean id="bd" scope="page" class="com.lgw.dao.BarDao" />
<jsp:useBean id="bs" scope="page" class="com.lgw.servlet.BarService" />
<%@ page import="com.lgw.dao.BarDao" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="com.lgw.servlet.BarService" %>

<!DOCTYPE>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello ECharts</title>
<!-- 使用单文件引入的方式使用ECharts.JS -->
<script src="../js/echarts.js"></script>
<script src="../js/jquery.min.js"></script>
</head>

<body>

<div id="myTest" style="width: 600px; height: 30px;margin:20px auto;">
	请选择要查询的日期：
	<input class="Wdate" id="sdate" type="text" name="sdate" onClick="WdatePicker()"/>
	     至
	<input class="Wdate" id="edate" type="text" name="edate" onClick="WdatePicker()"/>
	<button id="myButton" type="submit" onClick="query()">查询
	</button>
</div>

<div id="myLineDiv" style="height: 90%; width: 100%;display:inline-block" ></div>

<script type="text/javascript">
         
        var myChart = echarts.init(document.getElementById('myLineDiv'));
        
        var option = {
            title: {
                text: '满意度'
            },
            tooltip : {
            	trigger: 'axis',
                show : true
            },
            legend : {
            	data : [ '金普卡','高端卡' ]
            },
            xAxis :{
		        type : 'category',
		        boundaryGap: false
		    },
            yAxis : {
                type : 'value',
                min: 90,
                max: 100,
            	axisLabel: {
	            	formatter: '{value}%',
	            	//fontFamily: 'Courier New'						//y轴字体
	        	}
            },
            series : [{
                name : '金普卡',
                type : 'line',
               	label : {
               		show: true,
               		formatter:'{c}%'
               		//fontFamily: 'Courier New',				   //数字字体
               		//fontSize:20
               	}
            },{
                name : '高端卡',
                type : 'line',
                label : {
                	show: true,
                	formatter:'{c}%'
                }
            }]
        };
        
        function query(){ 
            loadData(option);
            myChart.setOption(option); 
        }
        
        function loadData(option) {
            $.ajax({
                type : 'post',  //传输类型
                async : false,  //异步执行
                url : '../bar.do', 
                data : {
	                'sdate': $("input[name='sdate']").val(), 
	                'edate': $("input[name='edate']").val(),
	                "table":"qasch"
                },
                dataType : 'json', //返回数据形式为json
                success : function(result) {
                    if (result) {
                    	option.xAxis.data = [];								//x
                        option.series[0].data = [];							//金普卡data
                        option.series[1].data = [];							//高端卡data
                        for (var i=0; i<result.length; i++) {
                            option.xAxis.data.push(result[i].data);
                            option.series[0].data.push(result[i].stan*100);
                            option.series[1].data.push(result[i].stan2*100);
                        }
                    }
                },
                error : function(errorMsg) {
                    alert("加载数据失败");
                }
            });
        }
    </script>

</body>
<script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
</html>


