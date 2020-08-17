package edu.neu.csye6200.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author arronshentu
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

  @Resource
  ApplicationProperties applicationProperties;

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = applicationProperties.getCors();
    if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
      source.registerCorsConfiguration("/v1/**", config);
    }
    return new CorsFilter(source);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
      .addMapping("/**").allowedHeaders("Content-Type", "X-Requested-With", "accept,Origin",
        "Access-Control-Request-Method", "Access-Control-Request-Headers", "token")
      .allowedMethods("*").allowedOrigins("*").allowCredentials(true);
  }
}
