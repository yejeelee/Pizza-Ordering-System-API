package io.swagger;

import io.swagger.model.PizzaSize;
import io.swagger.model.SideItem;
import io.swagger.model.SpecialItem;
import io.swagger.model.StoreItem;
import io.swagger.model.ToppingItem;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.repository.SideItemRepository;
import io.swagger.repository.SpecialItemRepository;
import io.swagger.repository.StoreItemRepository;
import io.swagger.repository.ToppingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"io.swagger", "io.swagger.api", "io.swagger.configuration"})
public class PizzaParadiseApplication implements CommandLineRunner {

  @Autowired
  PizzaSizeRepository pizzaSizeRepository;

  @Autowired
  SideItemRepository sideItemRepository;

  @Autowired
  SpecialItemRepository specialItemRepository;

  @Autowired
  StoreItemRepository storeItemRepository;

  @Autowired
  ToppingItemRepository toppingItemRepository;

  @Override
  public void run(String... arg0) {
    PizzaSize.initialize(pizzaSizeRepository);
    SideItem.initialize(sideItemRepository);
    SpecialItem.initialize(specialItemRepository);
    StoreItem.initialize(storeItemRepository);
    ToppingItem.initialize(toppingItemRepository);
  }

  public static void main(String[] args) {
    SpringApplication.run(PizzaParadiseApplication.class, args);
  }
}
