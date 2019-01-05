<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%@ page import="org.jfree.chart.ChartFactory" %>
<%@ page import="org.jfree.chart.JFreeChart" %>
<%@ page import="org.jfree.data.category.DefaultCategoryDataset" %>
<%@ page import="org.jfree.chart.plot.PlotOrientation" %>
<%@ page import="org.jfree.chart.entity.StandardEntityCollection" %>
<%@ page import="org.jfree.chart.ChartRenderingInfo" %>
<%@ page import="org.jfree.chart.servlet.ServletUtilities" %>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<jsp:useBean id="trans" scope="page" class="com.mingri.chinese.ToChinese" />

<%@ page import="java.io.*,java.sql.*"%>
<jsp:useBean id="rst" scope="page" class="com.mingri.dbconn.DBResult"/>
<jsp:useBean id="qu" scope="page" class="com.mingri.info.QuestString" />

<head>
	<meta charset="utf-8">
	<title>系统首页</title>

	<!-- Demo styling -->
	<link href="../CSS/jq.css" rel="stylesheet">

	<!-- jQuery: required (tablesorter works with jQuery 1.2.3+) -->
	<script src="../js/jquery-1.2.6.min.js"></script>

	<!-- Pick a theme, load the plugin & initialize plugin -->
	<link href="../CSS/theme.default.min.css" rel="stylesheet">
	<script src="../js/jquery.tablesorter.min.js"></script>
	<script src="../js/jquery.tablesorter.widgets.min.js"></script>
	<script>
	$(function(){
		$('table').tablesorter({
			widgets        : ['zebra', 'columns'],
			usNumberFormat : false,
			sortReset      : true,
			sortRestart    : true
		});
	});
	</script>

</head>

<body>
<%
   String table="BDMS_qa_daily_per";
   String sdate=request.getParameter("sdate");
   String edate=request.getParameter("edate");
   String username=request.getParameter("username");
   String grpname=trans.trans(request.getParameter("grpname"));
   qu.setusername(username);qu.setgrpname(grpname);qu.setSdate(sdate);qu.setEdate(edate);
   String strper=qu.getperString(table,grpname,username);
   ResultSet rs=rst.getResult(strper);
   DecimalFormat   fnum1  =   new  DecimalFormat("##0.00");
   DecimalFormat   fnum2  =   new  DecimalFormat("##0");
   SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
%>


<table class="tablesorter">
<thead>
<tr>
<th>工号</th>
<th>姓名</th>
<th>小组</th>
<th>科室</th>
<th>日期</th>
<th>检测通数</th>
<th>确认需求-</th>
<th>确认需求+</th>
<th>提供解决方案-</th>
<th>提供解决方案+</th>
<th>客户利益</th>
<th>平均分</th>
<th>总得分</th>
<th>基础总分</th>
<th>达标率</th>
</tr>
</thead> 
<tbody>
<%

   int counts=0;
   while(rs.next())
   {%>
<tr>
<!-- 输出结果集 -->
<td><%=fnum2.format(rs.getFloat(1)) %></td>
<td><%=rs.getString(2) %></td>
<td><%=rs.getString(3) %></td>
<td><%=rs.getString(4) %></td>
<td><%=rs.getString(5) %></td>
<td><%=fnum2.format(rs.getFloat(6)) %></td>
<td><%=fnum2.format(rs.getFloat(7)) %></td>
<td><%=fnum2.format(rs.getFloat(8)) %></td>
<td><%=fnum2.format(rs.getFloat(9)) %></td>
<td><%=fnum2.format(rs.getFloat(10)) %></td>
<td><%=fnum2.format(rs.getFloat(11)) %></td>
<td><%=fnum1.format(rs.getFloat(12)) %></td>
<td><%=fnum2.format(rs.getFloat(13)) %></td>
<td><%=fnum2.format(rs.getFloat(14)) %></td>
<td><%=(fnum1.format((rs.getFloat(15)*100))+ "%")%></td>
</tr>
<%}
%>
</tbody>
</table>
<br />
<br />
<div align="center"> 
<a><input type="button" class="btn_grey" value="导出数据" style="width:150px;height:30px"></a>
</div>
<script>
// 使用outerHTML属性获取整个table元素的HTML代码（包括<table>标签），然后包装成一个完整的HTML文档，设置charset为urf-8以防止中文乱码
var html = "<html><head><meta charset='utf-8' /></head><body>" + document.getElementsByTagName("table")[0].outerHTML + "</body></html>";
// 实例化一个Blob对象，其构造函数的第一个参数是包含文件内容的数组，第二个参数是包含文件类型属性的对象
var blob = new Blob([html], { type: "application/vnd.ms-excel" });
var a = document.getElementsByTagName("a")[0];
// 利用URL.createObjectURL()方法为a元素生成blob URL
a.href = URL.createObjectURL(blob);
// 设置文件名
a.download = "data.xls";
</script>
</body>
</html>
