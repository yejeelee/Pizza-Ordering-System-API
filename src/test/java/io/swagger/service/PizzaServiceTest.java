package io.swagger.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import io.swagger.exceptions.ToppingNotFoundException;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.ToppingItem;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.repository.ToppingItemRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@TestPropertySource("classpath:/application-test.properties")
public class PizzaServiceTest {

  @Autowired public PizzaService pizzaService;

  @Autowired private ToppingItemRepository toppingRepo;

  @Autowired private PizzaSizeRepository sizeRepo;

  @Before
  public void setUp() {
    toppingRepo.deleteAll();
    sizeRepo.deleteAll();
  }

  static final String SMALL_SIZE = "small";
  static final String MEDIUM_SIZE = "medium";
  static final String LARGE_SIZE = "large";
  static final String TOPPING_NOT_FOUND = "TOPPING_NOT_FOUND";

  private ToppingItem setupBacon() {
    ToppingItem bacon = new ToppingItem("bacon1", "bacon", "meat", 2.50, 2.75, 3.00, "gluten");
    toppingRepo.insert(bacon);
    return bacon;
  }

  private ToppingItem setupBroccoli() {
    ToppingItem broccoli =
        new ToppingItem("broccoli1", "broccoli", "vegetable", 2.00, 2.25, 2.50, "non-gluten");
    toppingRepo.insert(broccoli);
    return broccoli;
  }

  private Pizza setUpPizza(String size, boolean gluten) {
    Pizza pizza = new Pizza(size, gluten);
    return pizza;
  }

  private PizzaSize setUpSmallSize() {
    PizzaSize pizzaSize = new PizzaSize("small", "Small", "6", 9.99);
    sizeRepo.insert(pizzaSize);
    return pizzaSize;
  }

  private PizzaSize setUpMediumSize() {
    PizzaSize pizzaSize = new PizzaSize("medium", "Medium", "9", 12.99);
    sizeRepo.insert(pizzaSize);
    return pizzaSize;
  }

  private PizzaSize setUpLargeSize() {
    PizzaSize pizzaSize = new PizzaSize("large", "Large", "11", 14.99);
    sizeRepo.insert(pizzaSize);
    return pizzaSize;
  }

  @Test
  public void TestGetPizzaPrice() throws Exception {
    setUpSmallSize();
    Pizza pizza1 = setUpPizza(SMALL_SIZE, true);
    ToppingItem bacon = setupBacon();
    ToppingItem broccoli = setupBroccoli();
    pizza1.getToppingIDs().add(bacon.getId());
    pizza1.getToppingIDs().add(broccoli.getId());

    Double price1 = pizzaService.getPizzaPrice(pizza1);
    assertEquals((Double) 14.49, price1);
    setUpLargeSize();
    Pizza pizza2 = setUpPizza(LARGE_SIZE, true);
    pizza2.getToppingIDs().add(bacon.getId());
    Double price2 = pizzaService.getPizzaPrice(pizza2);
    assertEquals((Double) 17.99, price2);
  }

  @Test
  public void TestGetPizzaToppingPrice() throws Exception {
    setUpMediumSize();
    setUpLargeSize();
    ToppingItem bacon = setupBacon();
    ToppingItem broccoli = setupBroccoli();
    List<String> toppingIDs1 = new ArrayList<>();
    toppingIDs1.add(bacon.getId());
    toppingIDs1.add(broccoli.getId());

    Double price1 = pizzaService.getPizzaToppingPrice(MEDIUM_SIZE, toppingIDs1);
    assertEquals((Double) 5.00, price1);
    Double price2 = pizzaService.getPizzaToppingPrice(LARGE_SIZE, toppingIDs1);
    assertEquals((Double) 5.50, price2);

    List<String> toppingIDs2 = new ArrayList<>();
    toppingIDs2.add("noTopping");
    try {
      pizzaService.getPizzaToppingPrice(MEDIUM_SIZE, toppingIDs2);
      fail();
    } catch (ToppingNotFoundException err) {
    }
    try {
      pizzaService.getPizzaToppingPrice(LARGE_SIZE, toppingIDs2);
      fail();
    } catch (ToppingNotFoundException err) {
    }
    Double price3 = pizzaService.getPizzaToppingPrice("extraLarge", toppingIDs1);
    assertEquals((Double) 0.00, price3);
  }
}
