<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*"%>
<%@ page import="java.io.*,java.sql.*"%>
<jsp:useBean id="rst" scope="page" class="com.mingri.dbconn.DBResult" />
<jsp:useBean id="trans" scope="page" class="com.mingri.chinese.ToChinese" />
<jsp:useBean id="calendar" scope="page" class="com.mingri.showtime.ShowTime" />
<html>


<head>
	<meta charset="utf-8">
	<title>ϵͳ��ҳ</title>

	<!-- Demo styling -->
	<link href="CSS/jq.css" rel="stylesheet">

	<!-- jQuery: required (tablesorter works with jQuery 1.2.3+) -->
	<script src="js/jquery-1.2.6.min.js"></script>

	<!-- Pick a theme, load the plugin & initialize plugin -->
	<link href="CSS/theme.default.min.css" rel="stylesheet">
	<script src="js/jquery.tablesorter.min.js"></script>
	<script src="js/jquery.tablesorter.widgets.min.js"></script>
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
<%String name = (String)session.getAttribute("name");
  String grp = (String)session.getAttribute("grp");
  String username = (String)session.getAttribute("username");
  String bir = "select username,name,grp,dep,bir from dbo.user_info where grp='" + grp + "' and SUBSTRING(bir,1,2)=datepart(mm,getdate()) group by username,name,grp,dep,bir ";
  ResultSet rs = rst.getResult(bir);
  String etime = "select username,name,grp,dep,etime from dbo.user_info where grp='" + grp + "' and MONTH(etime)=datepart(mm,getdate()) group by username,name,grp,dep,etime ";
  ResultSet rs2 = rst.getResult(etime);
  String worktime = "select datediff(day,etime,getdate()) as worktime from dbo.user_info where username='" + username +"'";
  ResultSet rs3 = rst.getResult(worktime);
  while(rs3.next())
  {
  session.setAttribute("worktime", rs3.getString("worktime"));
  }
  String wtime = (String)session.getAttribute("worktime");
   
%>  
<br>
�𾴵�<%=name %> ���ã���ӭ����½�ɶ�Ӫ������ҵ�����ι���ƽ̨�����Ѿ����������й�����<%=wtime %>�졣
<br>
<br>
<br>
<br>
<br>
<br>

<br>
<br>

<p>����������Ա:</p>
	
<table class="tablesorter">

<thead>
<tr>
<th>Ա��</th>
<th>����</th>
<th>С��</th>
<th>����</th>
<th>����</th>
</tr>
</thead> 
<tbody> 
<%


   while(rs.next())
   {
   
%>

<tr>
<!-- �������� -->
<td><%=rs.getString(1) %></td>
<td><%=rs.getString(2) %></td>
<td><%=rs.getString(3) %></td>
<td><%=rs.getString(4) %></td>
<td><%=rs.getString(5) %></td>
</tr>

<%}
%>
</tbody> 
</table>
<br>
<br>
<br>
<br>


<br>
<br>
<p>������ְ��Ա:</p>


<table class="tablesorter">
</tr>
<thead>
<tr>
<th>Ա��</th>
<th>����</th>
<th>С��</th>
<th>����</th>
<th>��ְʱ��</th>
</tr>
</thead> 
<tbody> 
<%


   while(rs2.next())
   {
   
%>
<tr>
<!-- �������� -->
<td><%=rs2.getString(1) %></td>
<td><%=rs2.getString(2) %></td>
<td><%=rs2.getString(3) %></td>
<td><%=rs2.getString(4) %></td>
<td><%=rs2.getString(5) %></td>
</tr>
<%}
%>
</tbody> 
</table>
<br>



</body>
</script>
</html>








