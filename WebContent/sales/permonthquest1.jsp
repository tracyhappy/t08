<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@ page import="java.io.*,java.sql.*"%>
<%request.setCharacterEncoding("gb2312");%>
<html>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<body>
<br>
<br>
<br>
<br>
<form name="form2" method="post" action="permonthquest.jsp" target="MainFramesales7">
<td height="100%" colspan="6">&nbsp;��ѡ��Ҫ��ѯ�����ڣ�
<input class="Wdate" id="sdate" type="text" name="sdate" onClick="WdatePicker()"/>
     ��
<input class="Wdate" id="edate" type="text" name="edate" onClick="WdatePicker()"/>
С�飺
<input type="text" name="grpname" id="grpname" value="" size="16">
��Ա���ţ�
<input type="text" name="username" id="username" value="" size="16">
<input type="Submit" class="btn_grey" value="��ѯ"></td>
</br>
</td>
</tr>
</form>
</body>
<script language="javascript" type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
</html>
