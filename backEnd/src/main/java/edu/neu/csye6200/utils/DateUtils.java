package edu.neu.csye6200.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.neu.csye6200.base.enums.DayCareResultCodeEnum;
import edu.neu.csye6200.base.exceptions.DayCareException;

/**
 * 时间工具类,用于字符串与Date类型转换
 *
 * @author flm
 * @since 2019/8/23 15:39
 */

public class DateUtils {

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

  private static final SimpleDateFormat dateFormatWithSeconds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  /**
   * 获取合适的格式日期类
   *
   * @param withSeconds
   *          bool
   * @return simpleDate
   */
  public static SimpleDateFormat getDateFormat(boolean withSeconds) {
    if (withSeconds) {
      return dateFormatWithSeconds;
    } else {
      return dateFormat;
    }
  }

  /**
   * 字符串转时间
   *
   * @param dates
   *          日期字符串
   * @param withSeconds
   *          是否带s
   * @return 时间
   */
  public static Date string2Date(String dates, boolean withSeconds) {
    try {
      return getDateFormat(withSeconds).parse(dates);
    } catch (Exception e) {
      throw new DayCareException("时间解析错误", DayCareResultCodeEnum.DATE_FORMAT_ERROR);
    }
  }

  /**
   * 校验日期是否符合格式
   *
   * @param strDate
   *          时间字符串
   * @param withSeconds
   *          是否带秒
   * @return 是否符合格式
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
   * 日期转字符串
   *
   * @param date
   *          日期
   * @param withSeconds
   *          是否带秒
   * @return 字符串
   */
  public static String date2String(Date date, boolean withSeconds) {
    try {
      return getDateFormat(withSeconds).format(date);
    } catch (Exception e) {
      throw new DayCareException("时间对象转换错误", DayCareResultCodeEnum.DATE_FORMAT_ERROR);
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
      throw new DayCareException("calculate age error", DayCareResultCodeEnum.INVALID_DATE);
    }
  }

}
