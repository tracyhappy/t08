<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@ page import="java.io.*,java.sql.*"%>
<%request.setCharacterEncoding("gb2312");%>
<html>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
<body>
<br>
<br>
<br>
<br>
<form name="form2" method="post" action="grpdailyquest.jsp" target="MainFramesales3">
<td height="100%" colspan="6">&nbsp;请选择要查询的日期：
<input class="Wdate" id="sdate" type="text" name="sdate" onClick="WdatePicker()"/>
     至
<input class="Wdate" id="edate" type="text" name="edate" onClick="WdatePicker()"/>
小组：
<input type="text" name="grpname" id="grpname" value="" size="11">
<input type="Submit" class="btn_grey" value="查询"></td>
</br>
</td>
</tr>
</form>
</body>
<script language="javascript" type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
</html>

