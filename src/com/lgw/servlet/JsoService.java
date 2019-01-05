package com.lgw.servlet;

import com.lgw.dao.BarDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsoService
  extends HttpServlet
{
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    String data = req.getParameter("data");
    String ATT = req.getParameter("ATT");
    BarDao bDao = new BarDao();
  }
}
