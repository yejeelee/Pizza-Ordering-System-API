package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.model.PizzaSuggestionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class PizzaCountApiController implements PizzaCountApi {

  private final int SMALL_PIZZA_CAL = 1601;
  private final int MED_PIZZA_CAL = 2236;
  private final int LARGE_PIZZA_CAL = 3384;

  private final int ADULT_CAL = 850;
  private final int KID_CAL = 700;

  @GetMapping("/pizzaCount")
  @ApiOperation(
      value = "suggests the number of pizzas required to feed a given number of people",
      tags = {
        "pizza count",
      })
  public ResponseEntity<PizzaSuggestionResponse> getNumOfPizzaByInput(Integer adult, Integer kid) {
    int totalCal = adult * ADULT_CAL + kid * KID_CAL;

    return new ResponseEntity<PizzaSuggestionResponse>(totalPizzaNeed(totalCal), HttpStatus.OK);
  }

  private PizzaSuggestionResponse totalPizzaNeed(int totalCal) {
    Integer calResults = totalCal;
    Integer smallCount = 0;
    Integer mediumCount = 0;
    Integer largeCount = 0;

    while (calResults > 0) {
      if (calResults >= LARGE_PIZZA_CAL) {
        largeCount++;
        calResults -= LARGE_PIZZA_CAL;
        continue;
      }
      if (calResults >= MED_PIZZA_CAL) {
        mediumCount++;
        calResults -= MED_PIZZA_CAL;
        continue;
      }
      smallCount++;
      calResults -= SMALL_PIZZA_CAL;
    }

    return new PizzaSuggestionResponse(smallCount, mediumCount, largeCount);
  }
}
