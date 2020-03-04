package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.model.PizzaSize;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/** PizzaSizeAPI */
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "pizzaSize", description = "Pizza Size API")
public interface PizzaSizeApi {

  /**
   * Get List of all pizza Sizes
   *
   * @return list of pizza Sizes from database
   */
  ResponseEntity<List<PizzaSize>> getAllPizzaSizes();

  /**
   * Get a specific pizza size with id
   *
   * @param id pizzaSizeId given to search
   * @return PizzaSize found using id
   */
  ResponseEntity<PizzaSize> getPizzaSizeById(
      @ApiParam(value = "PizzaSizeID") @PathVariable String id);
  /**
   * Add PizzaSize to database
   *
   * @param pizzaSize pizzaSize given to add
   * @return pizzaSize added to database
   */
  public ResponseEntity<PizzaSize> addPizzaSize(
      @ApiParam(value = "PizzaSize to add") @Valid @RequestBody PizzaSize pizzaSize);

  /**
   * Delete a PizzaSize found by id from database
   *
   * @param id id given for search
   * @return HttpStatus.NO_CONTENT if successfully removed, HttpStatus.NOT_FOUND if id wasn't found.
   */
  HttpStatus deletePizzaSize(@ApiParam(value = "PizzaSizeID") @PathVariable String id);
}
