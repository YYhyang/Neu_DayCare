package edu.neu.csye6200.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import lombok.Data;

/**
 * @author arronshentu
 */
@Configuration
@ConfigurationProperties(prefix = "application", ignoreInvalidFields = true)
@Data
public class ApplicationProperties {

  private CorsConfiguration cors = new CorsConfiguration();

}
