package edu.neu.csye6200.base.annotation;

import java.lang.annotation.*;

/**
 * @author arronshentu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogOperate {

  /**
   * 描述
   *
   * @return {String}
   */
  String value();

  /**
   * 是否保存请求的参数
   */
  boolean isSaveRequestData() default true;
}
