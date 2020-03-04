package io.swagger.api;

import static org.junit.Assert.assertEquals;

import io.swagger.model.PizzaSuggestionResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootTest(classes = {PizzaCountApiController.class})

public class PizzaCountApiControllerTest {

  @Autowired
  private PizzaCountApi pizzaCountApi;

  @Test
  public void testGetNumOfPizzaByInput() {
    int adult = 4;
    int kid = 2;
    final ResponseEntity<PizzaSuggestionResponse> response = pizzaCountApi
        .getNumOfPizzaByInput(adult, kid);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testGetNumOfPizzaByInputMedium() {
    int adult = 2;
    int kid = 1;
    final ResponseEntity<PizzaSuggestionResponse> response = pizzaCountApi
        .getNumOfPizzaByInput(adult, kid);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

}