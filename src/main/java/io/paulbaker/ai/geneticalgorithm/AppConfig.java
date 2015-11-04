package io.paulbaker.ai.geneticalgorithm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * Created by paulbaker on 11/4/15.
 */
@Configuration
public class AppConfig {

  @Bean
  public Random random() {
    return new Random();
  }

}
