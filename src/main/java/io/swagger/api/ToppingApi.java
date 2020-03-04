package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.ToppingItem;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/** ToppingApi interface */
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "topping", description = "the topping API")
public interface ToppingApi {

  /**
   * Get all topping items
   *
   * @return List of all ToppingItem from database
   */
  ResponseEntity<List<ToppingItem>> getAllTopping();

  /**
   * Get a specific toppingItem by toppingId
   *
   * @param id toppingID given to find a ToppingItem
   * @return toppingItem found by Id
   */
  ResponseEntity<ToppingItem> getToppingById(
      @ApiParam(value = "ToppingID") @PathVariable String id);

  /**
   * Add Topping to the Mongo database
   *
   * @param toppingItem toppingId given to add to the database
   * @return Topping Item that is added
   */
  public ResponseEntity<ToppingItem> addTopping(
      @ApiParam(value = "ToppingItem to add") @Valid @RequestBody ToppingItem toppingItem);

  /**
   * Delete a topping Item found by id from database
   *
   * @param id id given for search
   * @return HttpStatus.NO_CONTENT if successfully removed, HttpStatus.NOT_FOUND if id wasn't found.
   */
  HttpStatus deleteTopping(@ApiParam(value = "ToppingID") @PathVariable String id);
}
