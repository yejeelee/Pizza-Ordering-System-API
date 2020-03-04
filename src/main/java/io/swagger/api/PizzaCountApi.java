package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.model.PizzaSuggestionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "pizzaCount", description = "PizzaCount API")
public interface PizzaCountApi {

  // get suggested pizza order count
  ResponseEntity<PizzaSuggestionResponse> getNumOfPizzaByInput(
      @ApiParam(value = "Adult") @RequestParam("adult") Integer adult,
      @ApiParam(value = "Kid") @RequestParam("kid") Integer kid);
}
