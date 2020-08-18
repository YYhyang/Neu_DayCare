package edu.neu.csye6200.base.aop;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import edu.neu.csye6200.base.annotation.LogOperate;
import edu.neu.csye6200.utils.ClassUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author arronshentu
 */
@Aspect
@Slf4j
@SuppressWarnings("unchecked")
public class LogAspect {

  private void parseParams(ProceedingJoinPoint point, Map<String, Object> paramMap) {
    // 参数值
    Object[] argValues = point.getArgs();
    // 参数名称
    MethodSignature ms = (MethodSignature)point.getSignature();
    Method method = ms.getMethod();
    if (argValues != null) {
      for (int i = 0; i < argValues.length; i++) {
        // 读取方法参数
        MethodParameter methodParam = ClassUtils.getMethodParameter(method, i);
        // PathVariable 参数跳过
        PathVariable pathVariable = methodParam.getParameterAnnotation(PathVariable.class);
        if (pathVariable != null) {
          continue;
        }
        RequestBody requestBody = methodParam.getParameterAnnotation(RequestBody.class);
        Object value = argValues[i];
        // 如果是body的json则是对象
        if (requestBody != null && value != null) {
          paramMap.putAll(BeanMap.create(value));
          continue;
        }
        // 处理 List
        if (value instanceof List) {
          value = ((List)value).get(0);
        }
        // 处理 参数
        if (value instanceof HttpServletRequest) {
          paramMap.putAll(((HttpServletRequest)value).getParameterMap());
        } else if (value instanceof WebRequest) {
          paramMap.putAll(((WebRequest)value).getParameterMap());
        } else if (value instanceof MultipartFile) {
          MultipartFile multipartFile = (MultipartFile)value;
          String name = multipartFile.getName();
          String fileName = multipartFile.getOriginalFilename();
          paramMap.put(name, fileName);
        } else if (value instanceof HttpServletResponse) {
        } else if (value instanceof InputStream) {
        } else if (value instanceof InputStreamSource) {
        } else {
          // 参数名
          RequestParam requestParam = methodParam.getParameterAnnotation(RequestParam.class);
          String paraName;
          if (requestParam != null && StringUtils.isNotBlank(requestParam.value())) {
            paraName = requestParam.value();
          } else {
            paraName = methodParam.getParameterName();
          }
          paramMap.put(paraName, value);
        }
      }
    }
  }

  @Around("@annotation(logOperate)")
  @SneakyThrows
  public Object around(ProceedingJoinPoint point, LogOperate logOperate) {
    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    String requestURI = Objects.requireNonNull(request).getRequestURI();
    String requestMethod = request.getMethod();

    // 构建成一条长 日志，避免并发下日志错乱
    StringBuilder beforeReqLog = new StringBuilder(300);
    // 日志参数
    List<Object> beforeReqArgs = new ArrayList<>();
    // 打印路由
    beforeReqLog.append("Request===> {}: {}");

    log.info("Start request " + request.getRequestURI() + " from " + request.getRemoteAddr() + " | "
      + request.getHeader("x-forwarded-for"));

    beforeReqArgs.add(requestMethod);
    beforeReqArgs.add(requestURI);
    // 请求参数处理
    final Map<String, Object> paramMap = new HashMap<>(16);

    parseParams(point, paramMap);

    if (!paramMap.isEmpty()) {
      beforeReqLog.append(" Parameters: {}");
    }
    log.info(beforeReqLog.toString(), beforeReqArgs.toArray());
    // aop 执行后的日志
    StringBuilder afterReqLog = new StringBuilder(200);
    // 日志参数
    List<Object> afterReqArgs = new ArrayList<>();
    long startNs = System.nanoTime();
    long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
    Object result = point.proceed();
    afterReqLog.append(" time ({} ms) ");
    afterReqArgs.add(tookMs);
    log.info(afterReqLog.toString(), afterReqArgs.toArray());
    beforeReqArgs.clear();
    afterReqArgs.clear();
    log.info("Finish request\n");
    return result;
  }

}
