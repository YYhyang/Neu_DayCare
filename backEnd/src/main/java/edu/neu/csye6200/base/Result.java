package edu.neu.csye6200.base;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Author Caspar
 * @CreateTime 2020/8/10 19:21
 * @Description:
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class Result<T> implements Serializable {

  /**
   * 序列号id
   */
  private static final long serialVersionUID = 761675137867578348L;

  /**
   * 请求结果
   */
  private boolean success = true;

  /**
   * 响应code
   */
  private String resultCode;

  /**
   * 响应描述
   */
  private String resultDesc;

  /**
   * 结果实体
   */
  private T resultObj;

  /** 本次需求，temporary */
  private int code;
  private String msg;
  private int count;
  private T data;

  public static <T> Result<T> buildData(T data) {
    return new Result<>(data);
  }

  public Result(T data) {
    this.data = data;
  }

}
