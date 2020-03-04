package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.ToppingItem;
import io.swagger.service.ToppingItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class ToppingApiController implements ToppingApi {

  @Autowired private ToppingItemService toppingItemService;

  /** {@inheritDoc} HttpStatus.OK - if toppingItem is successfully found. */
  @GetMapping("/topping")
  @ApiOperation(
      value = "Get all ToppingItem",
      tags = {
        "topping",
      })
  @ApiResponse(code = 200, message = "OK")
  public ResponseEntity<List<ToppingItem>> getAllTopping() {
    return new ResponseEntity<List<ToppingItem>>(toppingItemService.getAllTopping(), HttpStatus.OK);
  }

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if toppingId is not found from database. HttpStatus.OK -
   * if toppingItem is successfully found.
   */
  @GetMapping("/topping/{id}")
  @ApiOperation(
      value = "Get ToppingItem with specific id",
      tags = {
        "topping",
      })
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "NOT_FOUND")
      })
  public ResponseEntity<ToppingItem> getToppingById(String id) {
    if (toppingItemService.getToppingById(id) == null) {
      return new ResponseEntity<ToppingItem>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<ToppingItem>(toppingItemService.getToppingById(id), HttpStatus.OK);
  }

  /**
   * {@inheritDoc} HttpStatus.OK - if topping is successfully created. HttpStatus.FORBIDDEN - if
   * there is already a toppingId in database
   */
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "FORBIDDEN")
      })
  @PostMapping("/topping")
  @ApiOperation(
      value = "add a ToppingItem",
      tags = {
        "topping",
      })
  public ResponseEntity<ToppingItem> addTopping(ToppingItem toppingItem) {
    if (toppingItemService.getToppingById(toppingItem.getId()) != null) {
      return new ResponseEntity<ToppingItem>(HttpStatus.FORBIDDEN);
    }
    return new ResponseEntity<ToppingItem>(
        toppingItemService.addTopping(toppingItem), HttpStatus.OK);
  }

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if toppingId is not found from database.
   * HttpStatus.NO_CONTENT - if toppingItem is successfully removed.
   */
  @DeleteMapping("/topping/{id}")
  @ApiOperation(
      value = "delete a ToppingItem with id",
      tags = {
        "topping",
      })
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "NO_CONTENT"),
        @ApiResponse(code = 404, message = "NOT_FOUND")
      })
  public HttpStatus deleteTopping(String id) {

    if (toppingItemService.getToppingById(id) == null) {
      return HttpStatus.NOT_FOUND;
    }
    toppingItemService.deleteTopping(id);
    return HttpStatus.NO_CONTENT;
  }
}
