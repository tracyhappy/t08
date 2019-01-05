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
<%@ page import="java.io.*,java.sql.*"%>

<jsp:useBean id="trans" scope="page" class="com.mingri.chinese.ToChinese" />
<jsp:useBean id="rst" scope="page" class="com.mingri.dbconn.DBResult"/>
<jsp:useBean id="qu" scope="page" class="com.mingri.info.QuestString" />

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
   String table="BDMS_sat_daily_per";
   String sdate=request.getParameter("sdate");
   String edate=request.getParameter("edate");
   String username=request.getParameter("username");
   String grpname=trans.trans(request.getParameter("grpname"));
   qu.setusername(username);qu.setgrpname(grpname);qu.setSdate(sdate);qu.setEdate(edate);
   String strper=qu.getper1String(table,grpname,username);
   ResultSet rs=rst.getResult(strper);
   DecimalFormat   fnum1  =   new  DecimalFormat("##0.00");
   DecimalFormat   fnum2  =   new  DecimalFormat("##0");
   SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
%>

<table class="tablesorter">
<thead>
<tr>
<th>����</th>
<th>����</th>
<th>С��</th>
<th>����</th>
<th>��������</th>
<th>�ظ�����</th>
<th>���ֽ��</th>
<th>�ͻ���</th>
<th>ѡ������</th>
<th>��������</th>
</tr>
</thead> 
<tbody>
<%

   while(rs.next())
   {%>
<tr>
<!-- �������� -->
<td><%=rs.getString(1) %></td>
<td><%=rs.getString(2) %></td>
<td><%=rs.getString(3) %></td>
<td><%=rs.getString(4) %></td>
<td><%=rs.getString(5) %></td>
<td><%=rs.getString(6) %></td>
<td><%=rs.getString(7) %></td>
<td><%=fnum2.format(rs.getFloat(8)) %></td>
<td><%=rs.getString(9) %></td>
<td><%=rs.getString(10) %></td>
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
