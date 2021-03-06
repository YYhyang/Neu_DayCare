package edu.neu.csye6200.base;

/**
 * @Author Caspar
 * @CreateTime 2020/8/10 19:22
 * @Description:
 */
public interface DayCareLogicCallBack {

  /**
   * 实现execute方法以处理controller请求
   *
   * @return 处理结果
   * @throws Exception
   *           需要异常处理
   */
  Result<Object> execute() throws Exception;

}
