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
<form name="form2" method="post" action="grpmonthquest.jsp" target="MainFramesales4">
<td height="100%" colspan="6">&nbsp;请选择要查询的日期：
<input class="Wdate" id="sdate" type="text" name="sdate" onClick="WdatePicker()"/>
     至
<input class="Wdate" id="edate" type="text" name="edate" onClick="WdatePicker()"/>
科室：
<select name="depid" id="depid">
<option value="客服一室">客服一室</option>
<option value="客服二室">客服二室</option>
<option value="客服三室">客服三室</option>
<option value="客服四室">客服四室</option>
<option value="客服五室">客服五室</option>
<option value="客服六室">客服六室</option>
<option value="高端服务室">高端服务室</option>
</select>
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

