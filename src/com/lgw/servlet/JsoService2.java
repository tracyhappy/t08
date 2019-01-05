package com.lgw.servlet;

import com.lgw.dao.BarDao2;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsoService2
  extends HttpServlet
{
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    String AMT = req.getParameter("AMT");
    String ATT = req.getParameter("ATT");
    String qa = req.getParameter("qa");
    String stan = req.getParameter("stan");
    String weixin = req.getParameter("weixin");
    String app = req.getParameter("app");
    String category = req.getParameter("category");
    String scores = req.getParameter("scores");
    
    BarDao2 bDao = new BarDao2();
  }
}
