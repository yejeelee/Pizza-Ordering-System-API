package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import io.swagger.DBPizzaSizes;
import io.swagger.model.PizzaSize;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.service.PizzaSizeService;
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
@SpringBootTest(classes = {PizzaSizeApiController.class, PizzaSizeService.class})

public class PizzaSizeApiControllerTest {

  @MockBean
  private PizzaSizeRepository pizzaSizeRepository;

  @Autowired
  private PizzaSizeService sizeService;

  @Autowired
  private PizzaSizeApi pizzaSizeApi;

  @Before
  public void setUp() {
    pizzaSizeRepository.deleteAll();
  }

  private void setUpPizzaSizeRepo() {
    PizzaSize size1 = sizeService.addPizzaSize(DBPizzaSizes.LARGE);
    PizzaSize size2 = sizeService.addPizzaSize(DBPizzaSizes.MEDIUM);
    PizzaSize size3 = sizeService.addPizzaSize(
        DBPizzaSizes.SMALL);
    pizzaSizeRepository.insert(size1);
    pizzaSizeRepository.insert(size2);
    pizzaSizeRepository.insert(size3);

    when(pizzaSizeRepository.findAll())
        .thenReturn(Stream
            .of(DBPizzaSizes.LARGE, DBPizzaSizes.MEDIUM, DBPizzaSizes.SMALL)
            .collect(Collectors.toList()));

    when(pizzaSizeRepository.findById("large"))
        .thenReturn(Optional.of(DBPizzaSizes.LARGE));
  }

  @Test
  public void testGetAllSize() {
    setUpPizzaSizeRepo();
    final ResponseEntity<List<PizzaSize>> response = pizzaSizeApi.getAllPizzaSizes();
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testGetSizeById() {
    setUpPizzaSizeRepo();
    final ResponseEntity<PizzaSize> responseOk = pizzaSizeApi.getPizzaSizeById("large");
    assertEquals(HttpStatus.OK, responseOk.getStatusCode());

    final ResponseEntity<PizzaSize> responseNull = pizzaSizeApi.getPizzaSizeById("xLarge");
    assertEquals(HttpStatus.NOT_FOUND, responseNull.getStatusCode());
  }

  @Test
  public void testAddSize() {
    setUpPizzaSizeRepo();
    PizzaSize sizeItem4 = new PizzaSize("xLarge",
        "extra large", "20", 24.99);
    final ResponseEntity<PizzaSize> response = pizzaSizeApi.addPizzaSize(sizeItem4);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testDeletePizzaSize() {
    setUpPizzaSizeRepo();
    final HttpStatus deletePizzaSize = pizzaSizeApi.deletePizzaSize("large");
    assertEquals(HttpStatus.NO_CONTENT, deletePizzaSize);

    final HttpStatus deletePizzaSizeNull = pizzaSizeApi.deletePizzaSize("xLarge");
    assertEquals(HttpStatus.NOT_FOUND, deletePizzaSizeNull);
  }
}