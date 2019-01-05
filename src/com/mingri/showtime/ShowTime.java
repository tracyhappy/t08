package com.mingri.showtime;

import java.util.Date;

public class ShowTime
{
  private Date date = null;
  private int yy;
  private int mm;
  private int dd;
  private String sy;
  private String sm;
  private String sd;
  
  public ShowTime()
  {
    this.date = new Date();
    this.yy = (this.date.getYear() + 1900);
    this.mm = (this.date.getMonth() + 1);
    this.dd = this.date.getDate();
    this.sy = String.valueOf(this.yy);
    this.sm = String.valueOf(this.mm);
    this.sd = String.valueOf(this.dd);
    if (this.sm.length() == 1) {
      this.sm = ("0" + this.sm);
    }
    if (this.sd.length() == 1) {
      this.sd = ("0" + this.sd);
    }
  }
  
  public String getDateString()
  {
    return this.sy + this.sm + this.sd;
  }
  
  public String getDate()
  {
    return this.sy + "-" + this.sm + "-" + this.sd;
  }
}
