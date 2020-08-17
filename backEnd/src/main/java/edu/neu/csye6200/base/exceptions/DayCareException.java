package edu.neu.csye6200.base.exceptions;

import edu.neu.csye6200.base.enums.ResultCodeEnum;

/**
 * @Author Caspar
 * @CreateTime 2020/8/10 19:24
 * @Description:
 */
public class DayCareException extends RuntimeException {

  /** 错误码 */
  private ResultCodeEnum errorCode;

  /**
   * 错误码构造器
   *
   * @param errorCode
   *          错误码
   */
  public DayCareException(ResultCodeEnum errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * 常用构造器
   *
   * @param message
   *          异常信息
   * @param errorCode
   *          错误码
   */
  public DayCareException(String message, ResultCodeEnum errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  /**
   * 构造器
   *
   * @param message
   *          异常信息
   * @param errorCode
   *          错误码
   * @param cause
   *          异常
   */
  public DayCareException(String message, ResultCodeEnum errorCode, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public DayCareException(String message, Throwable cause, ResultCodeEnum errorCode) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public DayCareException(Throwable cause, ResultCodeEnum errorCode) {
    super(cause);
    this.errorCode = errorCode;
  }

  public DayCareException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
    ResultCodeEnum errorCode) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.errorCode = errorCode;
  }

  /**
   * Get the value of errorCode
   *
   * @return the value of errorCode
   */
  public ResultCodeEnum getErrorCode() {
    return errorCode;
  }

  /**
   * Set the errorCode
   *
   * @param errorCode
   *          errorCode
   */
  public void setErrorCode(ResultCodeEnum errorCode) {
    this.errorCode = errorCode;
  }
}
