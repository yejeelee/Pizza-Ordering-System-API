package io.swagger.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = {"io.swagger.repository"})
@Configuration
public class MongoDBConfiguration implements CommandLineRunner {

  @Override
  public void run(String... strings) throws Exception {}
}
