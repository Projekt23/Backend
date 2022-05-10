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

  public String createExpriationDate(int hours){
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      calendar.add(Calendar.HOUR_OF_DAY, hours);
      return sdf.format(calendar.getTime());
  }
  public boolean validateDate(String date) throws ParseException {
      Date d = sdf.parse(date);
      return d.before(new Date());
  }
}
