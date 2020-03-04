package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import io.swagger.DBSpecialItems;
import io.swagger.model.SpecialItem;
import io.swagger.repository.SpecialItemRepository;
import io.swagger.service.SpecialItemService;
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
@SpringBootTest(classes = {SpecialApiController.class, SpecialItemService.class})

public class SpecialApiControllerTest {

  @MockBean
  private SpecialItemRepository specialItemRepository;

  @Autowired
  private SpecialItemService specialItemService;

  @Autowired
  private SpecialApi specialApi;

  @Before
  public void setUp() {
    specialItemRepository.deleteAll();
  }

  private void setUpSpecialRepo() {
    SpecialItem topping1 = specialItemService.addSpecial(DBSpecialItems.BUY_1_GET_1_FREE);
    SpecialItem topping2 = specialItemService.addSpecial(DBSpecialItems.BUY_1_PIZZA_GET_SODA_FREE);
    SpecialItem topping3 = specialItemService.addSpecial(
        DBSpecialItems.BUY_2_LARGE_PIZZA_NO_TOPPING);
    specialItemRepository.insert(topping1);
    specialItemRepository.insert(topping2);
    specialItemRepository.insert(topping3);

    when(specialItemRepository.findAll())
        .thenReturn(Stream
            .of(DBSpecialItems.BUY_1_GET_1_FREE, DBSpecialItems.BUY_1_PIZZA_GET_SODA_FREE,
                DBSpecialItems.BUY_2_LARGE_PIZZA_NO_TOPPING)
            .collect(Collectors.toList()));

    when(specialItemRepository.findById("buy1Get1Free"))
        .thenReturn(Optional.of(DBSpecialItems.BUY_1_GET_1_FREE));
  }

  @Test
  public void testGetAllSpecial() {
    setUpSpecialRepo();
    final ResponseEntity<List<SpecialItem>> response = specialApi.getAllSpecials();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(3, response.getBody().size());
  }

  @Test
  public void testGetSpecialById() {
    setUpSpecialRepo();
    final ResponseEntity<SpecialItem> responseOk = specialApi.getSpecialById("buy1Get1Free");
    assertEquals(HttpStatus.OK, responseOk.getStatusCode());

    final ResponseEntity<SpecialItem> responseNull = specialApi.getSpecialById("buy2Get2Free");
    assertEquals(HttpStatus.NOT_FOUND, responseNull.getStatusCode());
  }

  @Test
  public void testAddSpecial() {
    setUpSpecialRepo();
    SpecialItem specialItem4 = new SpecialItem("buy2Get2Free",
        "Buy2Get2Free", "If you buy 2 pizza, you get 2 free pizza that is of equal"
        + "or less value.");
    final ResponseEntity<SpecialItem> response = specialApi.addSpecial(specialItem4);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    final ResponseEntity<SpecialItem> response2 = specialApi
        .addSpecial(specialItemService.getSpecialById("buy1Get1Free"));
    assertEquals(HttpStatus.FORBIDDEN, response2.getStatusCode());
  }

  @Test
  public void testDeleteSpecial() {
    setUpSpecialRepo();
    final HttpStatus deleteSpecial = specialApi.deleteSpecial("buy1Get1Free");
    assertEquals(HttpStatus.NO_CONTENT, deleteSpecial);

    final HttpStatus deleteSpecialNull = specialApi.deleteSpecial("buy2Get2Free");
    assertEquals(HttpStatus.NOT_FOUND, deleteSpecialNull);
  }
}