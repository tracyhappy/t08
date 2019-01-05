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
   String table="user_info";
   String username=request.getParameter("username");
   String grpname=trans.trans(request.getParameter("grpname"));
   String depid=trans.trans(request.getParameter("depid"));
   qu.setusername(username);qu.setgrpname(grpname);qu.setdepid(depid);
   String per=qu.getper2String(table, depid, grpname, username);
   ResultSet rs=rst.getResult(per);

%>

<table class="tablesorter">
<thead>
<tr>
<th>����</th>
<th>����</th>
<th>С��</th>
<th>����</th>
<th>sap</th>
<th>��λ</th>
<th>��ְʱ��</th>
<th>ת��ʱ��</th>
<th>�Ա�</th>
<th>����</th>
<th>�ù���ʽ</th>
<th>����</th>
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
<td><%=rs.getString(8) %></td>
<td><%=rs.getString(9) %></td>
<td><%=rs.getString(10) %></td>
<td><%=rs.getString(11) %></td>
<td><%=rs.getString(12) %></td>
</tr>
<%}
%>
</tbody>
</table>
<br />
<br />

</body>
</html>
