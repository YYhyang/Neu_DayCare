package edu.neu.csye6200;

import java.net.InetAddress;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableTransactionManagement
@Slf4j
@MapperScan("edu.neu.csye6200.dao")
public class DayCareApplication {
  static String SERVER_PORT = "server.port";
  static String SPRING_APPLICATION_NAME = "spring.application.name";

  public static void main(String[] args) throws Exception {
    SpringApplication app = new SpringApplication(DayCareApplication.class);
    final ApplicationContext applicationContext = app.run(args);
    Environment env = applicationContext.getEnvironment();
    log.info(
      "\n----------------------------------------------------------\n\t"
        + "Application '{}' is running! Access URLs:\n\t" + "Local: \t\thttp://localhost:{}\n\t"
        + "External: \thttp://{}:{}\n----------------------------------------------------------",
      env.getProperty(SPRING_APPLICATION_NAME), env.getProperty(SERVER_PORT),
      InetAddress.getLocalHost().getHostAddress(), env.getProperty(SERVER_PORT));
  }
}
