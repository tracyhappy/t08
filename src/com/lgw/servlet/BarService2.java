package com.lgw.servlet;

import com.lgw.dao.BarDao2;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

public class BarService2 extends HttpServlet {
	
	private static final long serialVersionUID = 3350647389077170719L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("utf-8");
	    resp.setContentType("text/html;charset=utf-8");
	    
	    BarDao2 barDao = new BarDao2();
	    String sdate = req.getParameter("sdate");
	    String edate = req.getParameter("edate");
	    String table = req.getParameter("table");
	    String depid = req.getParameter("depid");
	    String grpname = req.getParameter("grpname");
	    String username = req.getParameter("username");
	    
	    String sql = getSQL(table, sdate, edate, depid, grpname, username);
	    
	    //System.out.println("sql ----->" + sql);
	    
	    String barArr = barDao.query(sql);
	    
	    resp.setContentType("text/html; charset=utf-8");
	    
	    JSONArray json = JSONArray.fromObject(barArr);
	    
	    //System.out.println(json.toString());
	    
	    PrintWriter writer = resp.getWriter();
	    writer.println(json);
	    writer.flush();
	    
	    writer.close();
    }
	
	
    public String getSQL(String table, String sdate, String edate, String dept, String group, String username) {
    	StringBuilder sqlBuilder = new StringBuilder("select category,Round(avg(scores),2) as avgscores from " + table + " where date between '" + sdate + "' and '" + edate + "'");
	    if (isNotEmpty(dept))
	    {
	      //科室
	      sqlBuilder.append(" and dep='").append(dept).append("'");
	    }
	    if (isNotEmpty(group))
	    {
	      //小组
	      sqlBuilder.append(" and grp='").append(group).append("'");
	    }
	    if (isNotEmpty(username))
	    {
	      //人员
	      sqlBuilder.append(" and username='").append(username).append("'");
	    }
	    sqlBuilder.append(" group by category");
	    return sqlBuilder.toString();
    }
    
    
    public boolean isEmpty(String str) {
    	return str == null || str.trim().length() == 0; 
    }
    
    public boolean isNotEmpty(String str) {
        return str != null && str.trim().length() > 0;
    }
  
  
}
