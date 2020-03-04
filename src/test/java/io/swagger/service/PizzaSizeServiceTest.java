package io.swagger.service;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Java6Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import io.swagger.model.PizzaSize;
import io.swagger.repository.PizzaSizeRepository;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@TestPropertySource(locations = "classpath:/application-test.properties")
@SpringBootTest
public class PizzaSizeServiceTest {

  @Autowired public PizzaSizeService sizeService;

  @Autowired private PizzaSizeRepository sizeRepo;

  @Before
  public void setUp() {
    sizeRepo.deleteAll();
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

  @Test
  public void getAllSizeTest() {
    PizzaSize pizzaSize1 = setUpSmallSize();
    PizzaSize pizzaSize2 = setUpMediumSize();

    List<PizzaSize> list = sizeService.getAllPizzaSizes();
    assertEquals(2, list.size());
    assertTrue(list.contains(pizzaSize1));
    assertTrue(list.contains(pizzaSize2));
  }

  @Test
  public void getPizzaSizeByIdTest() {
    PizzaSize pizzaSize1 = setUpSmallSize();

    PizzaSize sizeFromDB = sizeService.getPizzaSizeById(pizzaSize1.getId());
    assertEquals(pizzaSize1, sizeFromDB);

    assertNull(sizeService.getPizzaSizeById("noSmall"));
  }

  @Test
  public void TestAddPizzaSize() {
    PizzaSize test = new PizzaSize("medium", "Medium", "9", 12.99);
    PizzaSize pizzaSize2 = sizeService.addPizzaSize(test);
    assertEquals(pizzaSize2, test);
  }

  @Test
  public void TestDeletePizzaSize() {
    PizzaSize pizzaSize = setUpMediumSize();

    try {
      sizeService.deletePizzaSize(pizzaSize.getId());
      assertEquals(0, sizeRepo.count());
    } catch (Exception err) {
      fail(err.getMessage());
    }
  }
}
