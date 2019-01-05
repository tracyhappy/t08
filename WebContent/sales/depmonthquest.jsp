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
	<title>ϵͳ��ҳ</title>

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
<th>����</th>
<th>���ʲ�����</th>
<th>΢������</th>
<th>������������</th>
<th>���ʲ��˾�</th>
<th>΢���˾�</th>
<th>���������˾�</th>
</tr>
</thead> 
<tbody>
<%

   int counts=0;
   while(rs.next())
   {%>
<tr>
<!-- �������� -->
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
<a><input type="button" class="btn_grey" value="��������" style="width:150px;height:30px"></a>
</div>
<script>
// ʹ��outerHTML���Ի�ȡ����tableԪ�ص�HTML���루����<table>��ǩ����Ȼ���װ��һ��������HTML�ĵ�������charsetΪurf-8�Է�ֹ��������
var html = "<html><head><meta charset='utf-8' /></head><body>" + document.getElementsByTagName("table")[0].outerHTML + "</body></html>";
// ʵ����һ��Blob�����乹�캯���ĵ�һ�������ǰ����ļ����ݵ����飬�ڶ��������ǰ����ļ��������ԵĶ���
var blob = new Blob([html], { type: "application/vnd.ms-excel" });
var a = document.getElementsByTagName("a")[0];
// ����URL.createObjectURL()����ΪaԪ������blob URL
a.href = URL.createObjectURL(blob);
// �����ļ���
a.download = "data.xls";
</script>
</body>
</html>
