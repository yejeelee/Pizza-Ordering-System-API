package io.swagger.service;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import io.swagger.model.ToppingItem;
import io.swagger.repository.ToppingItemRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@TestPropertySource(locations = "classpath:/application-test.properties")
public class ToppingItemServiceTest {

  @MockBean private ToppingItemRepository toppingRepo;
  @Autowired private ToppingItemService toppingService;
  private ToppingItem bacon;
  private ToppingItem broccoli;

  @Before
  public void setUp() {
    broccoli = setupBroccoli();
    bacon = setupBacon();
    when(toppingRepo.findAll()).thenReturn(Stream.of(broccoli, bacon).collect(Collectors.toList()));

    when(toppingRepo.findById("bacon1")).thenReturn(Optional.of(bacon));
  }

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

  @Test
  public void getAllToppingsTest() {
    ToppingItem topping1 = setupBacon();
    ToppingItem topping2 = setupBroccoli();

    List<ToppingItem> allToppings = toppingService.getAllTopping();
    assertTrue(allToppings.contains(topping1));
    assertTrue(allToppings.contains(topping2));
  }

  @Test
  public void getToppingByIdTest() {
    ToppingItem topping = setupBacon();
    ToppingItem toppingFromDB = toppingService.getToppingById(topping.getId());
    assertNull(toppingService.getToppingById("noTopping"));
    assertEquals(topping, toppingFromDB);
  }

  @Test
  public void addToppingTest() {
    ToppingItem broccoli =
        new ToppingItem("broccoli1", "broccoli", "vegetable", 2.00, 2.25, 2.50, "non-gluten");

    ToppingItem toppingFromServer = toppingService.addTopping(broccoli);
    assertEquals(broccoli, toppingFromServer);
  }

  @Test
  public void deleteToppingTest() {
    ToppingItem broccoli = setupBroccoli();
    try {
      toppingService.deleteTopping(broccoli.getId());
      assertEquals(0, toppingRepo.count());
    } catch (Exception err) {
      fail(err.getMessage());
    }
  }
}
