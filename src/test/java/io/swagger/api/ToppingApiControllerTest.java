package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import io.swagger.DBToppingItems;
import io.swagger.model.ToppingItem;
import io.swagger.repository.ToppingItemRepository;
import io.swagger.service.ToppingItemService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootTest(classes = {ToppingApiController.class, ToppingItemService.class})

public class ToppingApiControllerTest {

  @MockBean
  private ToppingItemRepository toppingItemRepository;

  @Autowired
  private ToppingItemService toppingItemService;

  @Autowired
  private ToppingApi toppingApi;

  @Before
  public void setUp() {
    toppingItemRepository.deleteAll();
  }

  private void setUpToppingRepo() {
    ToppingItem topping1 = toppingItemService.addTopping(DBToppingItems.BLACK_OLIVES);
    ToppingItem topping2 = toppingItemService.addTopping(DBToppingItems.GREEN_PEPPERS);
    ToppingItem topping3 = toppingItemService.addTopping(
        DBToppingItems.PEPPERONI);
    toppingItemRepository.insert(topping1);
    toppingItemRepository.insert(topping2);
    toppingItemRepository.insert(topping3);

    when(toppingItemRepository.findAll())
        .thenReturn(Stream
            .of(DBToppingItems.BLACK_OLIVES, DBToppingItems.GREEN_PEPPERS, DBToppingItems.PEPPERONI)
            .collect(Collectors.toList()));

    when(toppingItemRepository.findById("pepperoni"))
        .thenReturn(Optional.of(DBToppingItems.PEPPERONI));
  }

  @Test
  public void testGetAllTopping() {
    setUpToppingRepo();
    final ResponseEntity<List<ToppingItem>> response = toppingApi.getAllTopping();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(3, response.getBody().size());
  }

  @Test
  public void testGetToppingById() {
    setUpToppingRepo();
    final ResponseEntity<ToppingItem> responseOk = toppingApi.getToppingById("pepperoni");
    assertEquals(HttpStatus.OK, responseOk.getStatusCode());

    final ResponseEntity<ToppingItem> responseNull = toppingApi.getToppingById("bacon");
    assertEquals(HttpStatus.NOT_FOUND, responseNull.getStatusCode());
  }

  @Test
  public void testAddTopping() {
    setUpToppingRepo();
    ToppingItem toppingItem4 = new ToppingItem("bacon",
        "bacon", "meat", 2.50,
        2.75, 3.00, "gluten");
    final ResponseEntity<ToppingItem> response = toppingApi.addTopping(toppingItem4);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    final ResponseEntity<ToppingItem> response2 = toppingApi
        .addTopping(toppingItemService.getToppingById("pepperoni"));
    assertEquals(HttpStatus.FORBIDDEN, response2.getStatusCode());
  }

  @Test
  public void testDeleteTopping() {
    setUpToppingRepo();
    final HttpStatus deleteTopping = toppingApi.deleteTopping("pepperoni");
    assertEquals(HttpStatus.NO_CONTENT, deleteTopping);

    final HttpStatus deleteToppingNull = toppingApi.deleteTopping("bacon");
    assertEquals(HttpStatus.NOT_FOUND, deleteToppingNull);
  }
}
