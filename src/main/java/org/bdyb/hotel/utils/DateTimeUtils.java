package org.bdyb.hotel.utils;

import java.util.Date;
import org.joda.time.DateTime;

public class DateTimeUtils {

  public static Date truncateHours(Date date) {
    return new DateTime(date).withHourOfDay(0).toDate();
  }
}
