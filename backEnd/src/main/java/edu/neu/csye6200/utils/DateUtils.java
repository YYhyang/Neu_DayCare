package edu.neu.csye6200.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.neu.csye6200.base.enums.ResultCodeEnum;
import edu.neu.csye6200.base.exceptions.DayCareException;

/**
 * Date Utilities, conversion between date and string
 *
 * @author flm, Yue Fang
 * @since 2019/8/23 15:39
 */

public class DateUtils {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

  private static final SimpleDateFormat DATE_FORMAT_WITH_SECONDS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  /**
   * get date format
   *
   * @param withSeconds
   *          bool
   * @return simpleDate
   */
  public static SimpleDateFormat getDateFormat(boolean withSeconds) {
    if (withSeconds) {
      return DATE_FORMAT_WITH_SECONDS;
    } else {
      return DATE_FORMAT;
    }
  }

  /**
   * Parse String to Date
   *
   * @param dates
   *          String of date
   * @param withSeconds
   *          bool, whether the format with second
   * @return Date
   */
  public static Date string2Date(String dates, boolean withSeconds) {
    try {
      return getDateFormat(withSeconds).parse(dates);
    } catch (Exception e) {
      throw new DayCareException("parse string to date error", ResultCodeEnum.DATE_FORMAT_ERROR);
    }
  }

  /**
   * Check the Format of Date
   *
   * @param strDate
   *          String of date
   * @param withSeconds
   *          bool, whether the format with second
   * @return bool
   */
  public static boolean isFormatDate(String strDate, boolean withSeconds) {
    try {
      string2Date(strDate, withSeconds);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Convert Date to String
   *
   * @param date
   *          String of date
   * @param withSeconds
   *          bool, whether the format with second
   * @return String of date
   */
  public static String date2String(Date date, boolean withSeconds) {
    try {
      return getDateFormat(withSeconds).format(date);
    } catch (Exception e) {
      throw new DayCareException("convert date to string error", ResultCodeEnum.DATE_FORMAT_ERROR);
    }
  }

  /**
   * calculate age(months) according to birthday
   *
   * @param birthday
   *          Date
   *
   * @return int
   */
  public static int calculateAge(Date birthday) {
    try {
      Date now = new Date();
      return (int)((now.getTime() - birthday.getTime()) / (24 * 60 * 60 * 1000)) / 30;
    } catch (Exception e) {
      throw new DayCareException("calculate age error", ResultCodeEnum.INVALID_DATE);
    }
  }

  /**
   * Calculate the date by adding months
   *
   * @param inputDate
   *          Date to Calculate
   * @param monthToAdd
   *          Month to add to the input date
   * @return Date
   */
  public static Date addMonth(Date inputDate, int monthToAdd) {
    try {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(inputDate);
      calendar.add(Calendar.MONTH, monthToAdd);
      Date resultDate = calendar.getTime();

      return resultDate;
    } catch (Exception e) {
      throw new DayCareException("add month to date error", ResultCodeEnum.INVALID_DATE);
    }
  }
}
