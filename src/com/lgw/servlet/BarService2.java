package com.lgw.servlet;

import com.lgw.dao.BarDao2;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

public class BarService2
  extends HttpServlet
{
  public String getsqlsString(String table, String s5, String s6, String s1, String s2, String s3)
  {
    if ((s1 != null) && (!s1.equals("")) && ((s2 == null) || (s2.isEmpty())) && ((s3 == null) || (s3.isEmpty())))
    {
      String s4 = "select category,Round(avg(scores),2) as avgscores from " + table + " where date between '" + s5 + "' and '" + s6 + "' and dep='" + s1 + " ' group by category";
      return s4;
    }
    if ((s2 != null) && (!s2.equals("")) && ((s1 == null) || (s1.isEmpty())) && ((s3 == null) || (s3.isEmpty())))
    {
      String s4 = "select category,Round(avg(scores),2) as avgscores from " + table + " where date between '" + s5 + "' and '" + s6 + "' and grp='" + s2 + " ' group by category";
      return s4;
    }
    if ((s3 != null) && (!s3.equals("")) && ((s2 == null) || (s2.isEmpty())) && ((s1 == null) || (s1.isEmpty())))
    {
      String s4 = "select category,Round(avg(scores),2) as avgscores from " + table + " where date between '" + s5 + "' and '" + s6 + "' and username='" + s3 + " ' group by category";
      return s4;
    }
    if (((s1 == null) || (s1.isEmpty())) && ((s2 == null) || (s2.isEmpty())) && ((s3 == null) || (s3.isEmpty())))
    {
      String s4 = "select category,Round(avg(scores),2) as avgscores from " + table + " where date between '" + s5 + "' and '" + s6 + " ' group by category ";
      return s4;
    }
    return null;
  }
  
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    req.setCharacterEncoding("utf-8");
    resp.setContentType("text/html;charset=utf-8");
    
    BarDao2 barDao = new BarDao2();
    String sdate = req.getParameter("sdate");
    String edate = req.getParameter("edate");
    String table = req.getParameter("table");
    String depid = req.getParameter("depid");
    String grpname = req.getParameter("grpname");
    String username = req.getParameter("username");
    
    String sql = getsqlsString(table, sdate, edate, depid, grpname, username);
    
    String barArr = barDao.query(sql);
    
    resp.setContentType("text/html; charset=utf-8");
    
    JSONArray json = JSONArray.fromObject(barArr);
    
    System.out.println(json.toString());
    
    PrintWriter writer = resp.getWriter();
    writer.println(json);
    writer.flush();
    
    writer.close();
  }
}
