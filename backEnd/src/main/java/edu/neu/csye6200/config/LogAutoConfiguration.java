package edu.neu.csye6200.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import edu.neu.csye6200.base.aop.LogAspect;
import lombok.AllArgsConstructor;

/**
 * @author arronshentu
 */
@EnableAsync
@Configuration
@AllArgsConstructor
@EnableAspectJAutoProxy
@ConditionalOnWebApplication
public class LogAutoConfiguration {

  @Bean
  public LogAspect loggingAspect() {
    return new LogAspect();
  }

}
