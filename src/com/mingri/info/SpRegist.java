package com.mingri.info;

import com.mingri.dbconn.DBResult;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SpRegist
{
  private SpinfoBean spinfo;
  DBResult rst = new DBResult();
  
  public void setSpinfo(SpinfoBean spinfo)
  {
    this.spinfo = spinfo;
  }
  
  public void regist()
    throws Exception
  {
    String sql = "insert into tb_brand values(?,?,?,?,?,?,?,?,?,?,?)";
    String str = "select max(id) as maxint from tb_brand";
    ResultSet rs = this.rst.getResult(str);
    String newmax = null;
    while (rs.next())
    {
      String max = rs.getString("maxint");
      String maxi = max.substring(2, max.length());
      newmax = "SP" + String.valueOf(Integer.parseInt(maxi) + 1);
    }
    try
    {
      PreparedStatement pstmt = this.rst.getPreparedStatement(sql);
      pstmt.setString(1, newmax);
      pstmt.setString(2, this.spinfo.getSpname());
      pstmt.setString(3, this.spinfo.getJc());
      pstmt.setString(4, this.spinfo.getCd());
      pstmt.setString(5, this.spinfo.getDw());
      pstmt.setString(6, this.spinfo.getGg());
      pstmt.setString(7, this.spinfo.getBz());
      pstmt.setString(8, this.spinfo.getPh());
      pstmt.setString(9, this.spinfo.getPzwh());
      pstmt.setString(10, this.spinfo.getGysname());
      pstmt.setString(11, this.spinfo.getMemo());
      pstmt.executeUpdate();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw e;
    }
  }
}
