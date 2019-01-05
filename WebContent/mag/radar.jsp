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
<jsp:useBean id="bd" scope="page" class="com.lgw.dao.BarDao2" />
<jsp:useBean id="bs" scope="page" class="com.lgw.servlet.BarService2" />
<%@ page import="com.lgw.dao.BarDao2" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="com.lgw.servlet.BarService2" %>

<!DOCTYPE>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ATT</title>
<!-- 使用单文件引入的方式使用ECharts.JS -->
<script src="../js/echarts.js"></script>
<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
</head>

<body>
  
  <div id="myTest" style="width: 100%; height: 60px;margin:20px auto;">
请选择要查询的日期：
<input class="Wdate" id="sdate" type="text" name="sdate" onClick="WdatePicker()"/>
     至
<input class="Wdate" id="edate" type="text" name="edate" onClick="WdatePicker()"/>
科室：
<input type="text" name="depid" id="depid" value="" size="16">
小组：
<input type="text" name="grpname" id="grpname" value="" size="16">
工号：
<input type="text" name="username" id="username" value="" size="16">
<button id="myButton" type="submit" onClick="sub()">查询
</button>
</div>
 
<div id="container" style="height: 90%; width: 100%"></div>
<script type="text/javascript">

    function sub(){ 
             

            loadData(option);
            myChart.setOption(option,true);
            
     
             
    }
    </script>
       

<script type="text/javascript">

var myChart = echarts.init(document.getElementById('container')); 
var scores=[];

    
        function loadData(option) {
       
               
            $.ajax({
                type : 'post',  //传输类型
                async : false,  //异步执行
                url : '../bar2.do', //web.xml中注册的Servlet的url-pattern
                data : {
                
                'sdate': $("input[name='sdate']").val(), 
                'edate': $("input[name='edate']").val(),
                'depid': $("input[name='depid']").val(),
                'grpname': $("input[name='grpname']").val(),
                'username': $("input[name='username']").val(),
                "table":"radar"
                                     
                },
                dataType : 'json', //返回数据形式为json
                success : function(result) {
                      
                   
                    if (result) {
                   
           
                         for (var i=0; i<result.length; i++) {
                            
                           scores.push(result[i].scores);                                   
                            
                        }
                        
                        console.log(scores);
          

                    }
                    
                   
                },
                error : function(errorMsg) {
                    alert("加载数据失败");
                }
            });//AJAX
            
            return  scores;
        }//loadData()



    


 var option = {
    title: {
        text: '三位一体雷达图'
    },
    tooltip: {},
    legend: {
        data: ['标准', '实际']
    },
    radar: {
    
        indicator: [
           { name: '重资产', max: 500000},
           { name: '掌上生活', max: 5},
           { name: '通话秒数', max: 162},
           { name: '达标率', max: 1},
           { name: '满意度', max: 1},
           { name: '微信', max: 5}
        
        ]
    },
    series: [{
        name: '标准  vs 实际',
        type: 'radar',
        // areaStyle: {normal: {}},
        data : [
         
             {
                 value: scores,
                 name : '实际' ,
                 label: {                    
                 normal: {                        
                 show: true,                        
                 formatter:
                 function(params) {                            
                     return params.value;                       
                  }              
                  }                
                  }

            },
            
             {
                 value: [500000,5,162,1,1,5],
                 name : '标准'
                 
            }
        ]
    }]
};

myChart.setOption(option,true);



       </script>
   </body>
   <script language="javascript" type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
</html>