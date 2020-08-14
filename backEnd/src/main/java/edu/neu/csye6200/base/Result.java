package edu.neu.csye6200.base;

import java.io.Serializable;

import edu.neu.csye6200.base.enums.DayCareResultCodeEnum;
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
  private String resultCode;
  private String[] msg;
  private int count;
  private T data;

  public Result(T data) {
    this.data = data;
  }

  public Result(String... msg) {
    this.msg = msg;
  }

  public Result(String resultCode) {
    this.resultCode = resultCode;
  }

  public Result(T data, String... msg) {
    this.data = data;
    this.msg = msg;
  }

  public static <T> Result<T> buildOkData(T data, String... messages) {
    return new Result<>(data, messages).setResultCode(DayCareResultCodeEnum.SUCCESS.getCode());
  }

  public static <T> Result<T> buildOkData(T data) {
    return buildOkData(data, new String[] {});
  }

  public static <T> Result<T> buildFailData(T data) {
    return new Result<>(data).setResultCode(DayCareResultCodeEnum.ERROR.getCode());
  }

  public static <T> Result<T> buildFail(String... messages) {
    return new Result<>(messages);
  }

}
