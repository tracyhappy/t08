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
<jsp:useBean id="bd" scope="page" class="com.lgw.dao.BarDao3" />
<jsp:useBean id="bs" scope="page" class="com.lgw.servlet.BarService3" />
<%@ page import="com.lgw.dao.BarDao3" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="com.lgw.servlet.BarService3" %>

<!DOCTYPE>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ATT</title>
<!-- 使用单文件引入的方式使用ECharts.JS -->
<script src="../js/echarts.js"></script>
<script src="../js/jquery.min.js"></script>
</head>

<body>

<div id="myBarDiv" style="height: 90%; width: 100%;display:inline-block" ></div>

        <script type="text/javascript">
    <!--这个是柱状图-->
        function loadData(option) {
            $.ajax({
                type : 'post',  //传输类型
                async : false,  //同步执行
                url : '../bar3.do', //web.xml中注册的Servlet的url-pattern
                data : {
                "table":"salesch"
                },
                dataType : 'json', //返回数据形式为json
                success : function(result) {
                    if (result) {
                        //初始化xAxis[0]的data
                        option.xAxis[0].data = [];
                        for (var i=0; i<result.length; i++) {
                            option.xAxis[0].data.push(result[i].data);
                        }
                        //初始化series[0]的data
                        option.series[0].data = [];
                        for (var i=0; i<result.length; i++) {
                            option.series[0].data.push(result[i].ATT);
                        }
                    }
                },
                error : function(errorMsg) {
                    alert("加载数据失败");
                }
            });
        }

 
         
        var myChart = echarts.init(document.getElementById('myBarDiv'));
        var option = {
            title: {
                text: '三地营销进度图'
            },

            tooltip : {
                show : true
            },
            legend : {
                data : [ '营销进度' ]
            },
            xAxis : [ {
                type : 'category'

            } ],
            yAxis : [ {
                type : 'value',
                axisLabel: {
		            formatter: '{value}'
		        }
            } ],
            series : [ {
                name : '营销进度',
                type : 'bar',
                itemStyle : { normal: {
                label : {show: true,formatter:'{c}'},
                color: function (params){
                        var colorList = ['#003366', '#006699', '#4cabce','#003366', '#006699', '#4cabce','#003366', '#006699', '#4cabce'];
                        return colorList[params.dataIndex];
                    }
                }}
            }]
        };
        //加载数据到option
        loadData(option);
        //设置option
        myChart.setOption(option);
    </script>


</body>
</html>