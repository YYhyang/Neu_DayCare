package edu.neu.csye6200.base.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ArrayUtil;
import edu.neu.csye6200.base.Result;
import lombok.extern.slf4j.Slf4j;

/**
 * @author somewhere
 * @date 2019/2/1
 *       全局的的异常处理器
 */
@Slf4j
@RestControllerAdvice
@Configuration
public class GlobalExceptionHandler {
  /**
   * 全局异常.
   *
   * @param e
   *          the e
   * @return R
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public <T> Result<T> exception(Exception e) {
    log.error("全局异常信息 ex={}", e.getMessage());
    return Result.buildFail(e.getMessage());
  }

  /**
   * validation Exception
   *
   * @param exception
   * @return R
   */
  @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
  @ResponseStatus(BAD_REQUEST)
  public <T> Result<T> bodyValidExceptionHandler(MethodArgumentNotValidException exception) {
    List<String> messages = new ArrayList<>();
    messages.add(exception.getMessage()); // todo
    log.warn("Valid Error:" + messages);
    return Result.buildFail(ArrayUtil.toArray(messages, String.class));
  }

  /**
   * RuntimeMsgException
   *
   * @param exception
   * @return R
   */
  @ExceptionHandler({RuntimeMsgException.class})
  public <T> Result<T> bodyRuntimeMsgExceptionHandler(RuntimeMsgException exception) {
    log.error("Runtime msg={}", exception.getMessage(), exception);
    return Result.buildFail(exception.getMessage());
  }

  /**
   * 处理 badException
   */
  @ExceptionHandler(value = {DayCareException.class})
  public <T> Result<T> badException(Exception e) {
    log.error(ExceptionUtil.stacktraceToString(e));
    return Result.buildFail(e.getMessage());
  }

}
