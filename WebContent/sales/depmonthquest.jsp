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
<jsp:useBean id="trans" scope="page" class="com.mingri.chinese.ToChinese" />

<%@ page import="java.io.*,java.sql.*"%>
<jsp:useBean id="rst" scope="page" class="com.mingri.dbconn.DBResult"/>

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
   String sdate=request.getParameter("sdate");
   String edate=request.getParameter("edate");
   String strSql="select dep,sum(AMT) as sumAMT,sum(weixin),sum(app),sum(AMT)/(AVG(renshu)/0.7),sum(weixin)/(AVG(renshu)/0.7), sum(app)/(AVG(renshu)/0.7) from BDMS_sales_daily_dep where dep <> '-' and date between '"+ sdate +"'and'"+ edate + "'group by dep order by sumAMT desc";  
   ResultSet rs=rst.getResult(strSql);
   DecimalFormat   fnum1  =   new  DecimalFormat("##########0.00");
%>

<table class="tablesorter">
<thead>
<tr>
<th>科室</th>
<th>重资产总量</th>
<th>微信总量</th>
<th>掌上生活总量</th>
<th>重资产人均</th>
<th>微信人均</th>
<th>掌上生活人均</th>
</tr>
</thead> 
<tbody>
<%

   int counts=0;
   while(rs.next())
   {%>
<tr>
<!-- 输出结果集 -->
<td><%=rs.getString(1) %></td>
<td><%=fnum1.format(rs.getFloat(2)) %></td>
<td><%=rs.getString(3) %></td>
<td><%=rs.getString(4) %></td>
<td><%=fnum1.format(rs.getFloat(5)) %></td>
<td><%=fnum1.format(rs.getFloat(6)) %></td>
<td><%=fnum1.format(rs.getFloat(7)) %></td>
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
