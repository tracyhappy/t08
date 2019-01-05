<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.sql.*"%>
<%@ page import="com.lgw.dao.BarDao" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="com.lgw.servlet.BarService" %>



<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<jsp:useBean id="trans" scope="page" class="com.mingri.chinese.ToChinese" />
<jsp:useBean id="rst" scope="page" class="com.mingri.dbconn.DBResult"/>
<jsp:useBean id="qu" scope="page" class="com.mingri.info.QuestString" />
<jsp:useBean id="bd" scope="page" class="com.lgw.dao.BarDao" />
<jsp:useBean id="bs" scope="page" class="com.lgw.servlet.BarService" />


<!DOCTYPE>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ATT</title>
	<script type="text/javascript"></script>
</head>

<script type="text/javascript" src="../js/echarts.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>


<body>

	<div id="myTest" style="width: 600px; height: 30px;margin:20px auto;">
		请选择要查询的日期：
		<input class="Wdate" id="sdate" type="text" name="sdate" onClick="WdatePicker()"/>
		     至
		<input class="Wdate" id="edate" type="text" name="edate" onClick="WdatePicker()"/>
		<button id="myButton" type="submit" onClick="query()">查询</button>
	</div>
	
	<div id="myLineDiv" style="height: 90%; width: 100%;display:inline-block" ></div>

</body>



<script type="text/javascript">
		var myChart = echarts.init(document.getElementById('myLineDiv'));
		
		var option = {
		    title: {
		        text: '通话秒数'
		    },
		    tooltip : {
		    	trigger: 'axis',
		        show : true
		    },
		    legend : {
		        data : [ '金普卡','高端卡' ]
		    },
		    /* toolbox: {
		        feature: {
		            saveAsImage: {}
		        }
		    }, */
		    xAxis :{
		        type : 'category',
		        boundaryGap: false
		    },
		    yAxis : {
                type : 'value',
                min: 90,
                max: 170
            },
		    series : [{
		        name : '金普卡',
		        type : 'line',
		        itemStyle : { normal: {label : {show: true}}}
		    },{
		        name : '高端卡',
		        type : 'line',
		        itemStyle : { normal: {label : {show: true}}}
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
                url : '../bar.do', //web.xml中注册的Servlet的url-pattern
                data : {
	                'sdate': $("input[name='sdate']").val(), 
	                'edate': $("input[name='edate']").val(),
                	"table":"effsch"
                },
                dataType : 'json', //返回数据形式为json
                success : function(result) {
                    if (result) {
                        option.xAxis.data = [];								//x
                        option.series[0].data = [];							//金普卡data
                        option.series[1].data = [];							//高端卡data
                        for (var i=0; i<result.length; i++) {
                            option.xAxis.data.push(result[i].data);
                            option.series[0].data.push(result[i].ATT);
                            option.series[1].data.push(result[i].ATT2);
                        }
                    }
                },
                error : function(errorMsg) {
                    alert("加载数据失败");
                }
            });
        }
</script>



</html>


