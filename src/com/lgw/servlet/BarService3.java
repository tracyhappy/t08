package com.lgw.servlet;

import com.lgw.dao.BarDao3;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

public class BarService3 extends HttpServlet {
	
	private static final long serialVersionUID = -4572719508810094340L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("utf-8");
	    resp.setContentType("text/html;charset=utf-8");
	    
	    BarDao3 barDao = new BarDao3();
	    String table = req.getParameter("table");
	    
	    String barArr = barDao.query(table);
	    
	    resp.setContentType("text/html; charset=utf-8");
	    
	    JSONArray json = JSONArray.fromObject(barArr);
	    
	    System.out.println(json.toString());
	    
	    PrintWriter writer = resp.getWriter();
	    writer.println(json);
	    writer.flush();
	    
	    writer.close();
	  }
}
