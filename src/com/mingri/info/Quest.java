package com.mingri.info;

public class Quest
{
  private String cif = "";
  private String ccif = "";
  private String qvalue = "";
  private String andor;
  private String sdate;
  private String edate;
  
  public String getCif()
  {
    return this.cif;
  }
  
  public void setCif(String cif)
  {
    this.cif = cif;
  }
  
  public String getCcif()
  {
    return this.ccif;
  }
  
  public void setCcif(String ccif)
  {
    this.ccif = ccif;
  }
  
  public String getQvalue()
  {
    return this.qvalue;
  }
  
  public void setQvalue(String qvalue)
  {
    this.qvalue = qvalue;
  }
  
  public String getAndor()
  {
    return this.andor;
  }
  
  public void setAndor(String andor)
  {
    this.andor = andor;
  }
  
  public String getSdate()
  {
    return this.sdate;
  }
  
  public void setSdate(String sdate)
  {
    this.sdate = sdate;
  }
  
  public String getEdate()
  {
    return this.edate;
  }
  
  public void setEdate(String edate)
  {
    this.edate = edate;
  }
  
  public String getString(String topsize, String table)
  {
    if (this.ccif.equals("="))
    {
      String strSql = "select top " + topsize + " * from " + table + " where" + " " + this.cif + "=" + "'" + this.qvalue + 
        "'";
      return strSql;
    }
    if (this.ccif.equals("LIKE"))
    {
      String strSql = "select top " + topsize + " * from " + table + " where" + " " + this.cif + " " + "like" + " " + 
        "'%" + this.qvalue + "%'";
      return strSql;
    }
    if (this.ccif.equals("ALL"))
    {
      String strSql = "select top " + topsize + " * from " + table;
      return strSql;
    }
    if (this.ccif.equals("<"))
    {
      String strSql = "select top " + topsize + " * from " + table + " where " + this.cif + " < '" + this.qvalue + "'";
      return strSql;
    }
    return null;
  }
  
  public String getCount(String table)
  {
    if (this.ccif.equals("="))
    {
      String strSql = "select count(*) from " + table + " where" + " " + this.cif + "=" + "'" + this.qvalue + "'";
      return strSql;
    }
    if (this.ccif.equals("LIKE"))
    {
      String strSql = "select count(*) from " + table + " where" + " " + this.cif + " " + "like" + " " + "'%" + this.qvalue + 
        "%'";
      return strSql;
    }
    if (this.ccif.equals("ALL"))
    {
      String strSql = "select count(*) from " + table;
      return strSql;
    }
    if (this.ccif.equals("<"))
    {
      String strSql = "select count(*) from " + table + " where " + this.cif + " < '" + this.qvalue + "'";
      return strSql;
    }
    return null;
  }
  
  public String getDateCount(String table)
  {
    if (this.ccif.equals("="))
    {
      String strSql = "select count(*) from " + table + " where " + this.cif + " = '" + this.qvalue + "'" + this.andor + 
        " xsdate between '" + this.sdate + "' and '" + this.edate + "'";
      return strSql;
    }
    if (this.ccif.equals("LIKE"))
    {
      String strSql = "select count(*) from " + table + " where " + this.cif + " like '%" + this.qvalue + "%'" + this.andor + 
        " xsdate between '" + this.sdate + "' and '" + this.edate + "'";
      return strSql;
    }
    if (this.ccif.equals("ALL"))
    {
      String strSql = "select count(*) from " + table;
      return strSql;
    }
    return null;
  }
  
  public String getDateString(String topsize, String table)
  {
    if (this.ccif.equals("="))
    {
      String strSql = "select top " + topsize + " * from " + table + " where " + this.cif + " = '" + this.qvalue + "'" + 
        this.andor + " xsdate between '" + this.sdate + "' and '" + this.edate + "'";
      return strSql;
    }
    if (this.ccif.equals("LIKE"))
    {
      String strSql = "select top " + topsize + " * from " + table + " where " + this.cif + " like '%" + this.qvalue + "%'" + 
        this.andor + " xsdate between '" + this.sdate + "' and '" + this.edate + "'";
      return strSql;
    }
    if (this.ccif.equals("ALL"))
    {
      String strSql = "select top " + topsize + " * from " + table;
      return strSql;
    }
    return null;
  }
  
  public String getgrpsumString(String table, String depid, String grpname)
  {
    if (depid.equals("0"))
    {
      String strSql = "select grp,dep,sum(AMT) as sumAMT,sum(weixin) as sumweixin,sum(app) as sumapp from " + table + " where date between '" + 
        this.sdate + "'and'" + this.edate + "'group by grp,dep order by sumAMT desc";
      return strSql;
    }
    String strSql = "select grp,dep,sum(AMT) as sumAMT,sum(weixin) as sumweixin,sum(app) as sumapp from " + table + " where dep='" + 
      depid + "' and grp = '" + grpname + "' and date between '" + this.sdate + "'and'" + this.edate + "'group by grp,dep order by sumAMT desc";
    return strSql;
  }
}
