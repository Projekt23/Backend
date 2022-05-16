package com.project23.app.helper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;
@AllArgsConstructor
@Component
public class DateHelper {
  private final SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss");

  public Date createExpriationDate(int days, int hours, int minutes ,int seconds){
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      calendar.add(Calendar.DATE, days);
      calendar.add(Calendar.HOUR_OF_DAY, hours);
      calendar.add(Calendar.MINUTE, minutes);
      calendar.add(Calendar.SECOND, seconds);
      return calendar.getTime();
  }

  public boolean validateDate(String date) throws ParseException {
      Date d = sdf.parse(date);
      return d.before(new Date());
  }
}
