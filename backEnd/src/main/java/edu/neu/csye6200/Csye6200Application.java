package edu.neu.csye6200;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@MapperScan("edu.neu.csye6200.dao")
public class Csye6200Application {

    public static void main(String[] args) {
        SpringApplication.run(Csye6200Application.class, args);
    }

}
