package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.PizzaSize;
import io.swagger.service.PizzaSizeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/** PizzaSizeApiController */
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class PizzaSizeApiController implements PizzaSizeApi {

  @Autowired private PizzaSizeService sizeService;

  /**
   * Get all pizza sizes from database
   *
   * @return list of pizza sizes from database HttpStatus.OK - successfully found all pizzaSizes
   *     from database
   */
  @GetMapping("/size")
  @ApiOperation(
      value = "Get all PizzaSizes",
      tags = {
        "pizza size",
      })
  @ApiResponse(code = 200, message = "OK")
  public ResponseEntity<List<PizzaSize>> getAllPizzaSizes() {
    return new ResponseEntity<List<PizzaSize>>(sizeService.getAllPizzaSizes(), HttpStatus.OK);
  }

  /**
   * Get a pizzaSize by using id
   *
   * @param id pizzaSizeId given to search
   * @return PizzaSize found by id HttpStatus.OK - successfully found PizzaSize by id
   *     HttpStatus.NOT_FOUND - not able to find PizzaSize by id
   */
  @GetMapping("/size/{id}")
  @ApiOperation(
      value = "Get specific PizzaSize with id",
      tags = {
        "pizza size",
      })
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "NOT_FOUND")
      })
  public ResponseEntity<PizzaSize> getPizzaSizeById(@PathVariable String id) {
    if (sizeService.getPizzaSizeById(id) == null) {
      return new ResponseEntity<PizzaSize>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<PizzaSize>(sizeService.getPizzaSizeById(id), HttpStatus.OK);
  }

  /**
   * Add PizzaSize to database
   *
   * @param pizzaSize pizzaSize given to add
   * @return pizzaSize added to database HttpStatus.FORBIDDEN - if pizzaSize already exist in the
   *     Database HttpStatus.OK - if pizzaSize successfully added
   */
  @PostMapping("/size")
  @ApiOperation(
      value = "add PizzaSize",
      tags = {
        "pizza size",
      })
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "FORBIDDEN")
      })
  public ResponseEntity<PizzaSize> addPizzaSize(PizzaSize pizzaSize) {
    if (sizeService.getPizzaSizeById(pizzaSize.getId()) != null) {
      return new ResponseEntity<PizzaSize>(HttpStatus.FORBIDDEN);
    }
    return new ResponseEntity<PizzaSize>(sizeService.addPizzaSize(pizzaSize), HttpStatus.OK);
  }

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if specialId is not found from database.
   * HttpStatus.NO_CONTENT - if specialItem is successfully removed.
   */
  @DeleteMapping("/size/{id}")
  @ApiOperation(
      value = "delete a PizzaSize with id",
      tags = {
        "pizza size",
      })
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "NO_CONTENT"),
        @ApiResponse(code = 404, message = "NOT_FOUND")
      })
  public HttpStatus deletePizzaSize(String id) {
    if (sizeService.getPizzaSizeById(id) == null) {
      return HttpStatus.NOT_FOUND;
    }
    sizeService.deletePizzaSize(id);
    return HttpStatus.NO_CONTENT;
  }
}
