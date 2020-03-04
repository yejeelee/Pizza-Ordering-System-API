package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.SpecialItem;
import io.swagger.service.SpecialItemService;
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
public class SpecialApiController implements SpecialApi {

  @Autowired private SpecialItemService specialItemService;

  /** {@inheritDoc} HttpStatus.OK - if specialItem is successfully found. */
  @GetMapping("/special")
  @ApiOperation(
      value = "Get all SpecialItems",
      tags = {
        "special",
      })
  @ApiResponse(code = 200, message = "OK")
  public ResponseEntity<List<SpecialItem>> getAllSpecials() {
    return new ResponseEntity<List<SpecialItem>>(
        specialItemService.getAllSpecials(), HttpStatus.OK);
  }

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if specialId is not found from database. HttpStatus.OK -
   * if specialItem is successfully found.
   */
  @GetMapping("/special/{id}")
  @ApiOperation(
      value = "Get SpecialItem with specific id",
      tags = {
        "special",
      })
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "NOT_FOUND")
      })
  public ResponseEntity<SpecialItem> getSpecialById(String id) {
    if (specialItemService.getSpecialById(id) == null) {
      return new ResponseEntity<SpecialItem>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<SpecialItem>(specialItemService.getSpecialById(id), HttpStatus.OK);
  }

  /**
   * {@inheritDoc} HttpStatus.OK - if special is successfully created. HttpStatus.FORBIDDEN - if
   * there is already a specialId in database
   */
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "FORBIDDEN")
      })
  @PostMapping("/special")
  @ApiOperation(
      value = "add a SpecialItem",
      tags = {
        "special",
      })
  public ResponseEntity<SpecialItem> addSpecial(SpecialItem specialItem) {
    if (specialItemService.getSpecialById(specialItem.getId()) != null) {
      return new ResponseEntity<SpecialItem>(HttpStatus.FORBIDDEN);
    }
    return new ResponseEntity<SpecialItem>(
        specialItemService.addSpecial(specialItem), HttpStatus.OK);
  }

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if specialId is not found from database.
   * HttpStatus.NO_CONTENT - if specialItem is successfully removed.
   */
  @DeleteMapping("/special/{id}")
  @ApiOperation(
      value = "delete a SpecialItem with id",
      tags = {
        "special",
      })
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "NO_CONTENT"),
        @ApiResponse(code = 404, message = "NOT_FOUND")
      })
  public HttpStatus deleteSpecial(String id) {

    if (specialItemService.getSpecialById(id) == null) {
      return HttpStatus.NOT_FOUND;
    }
    specialItemService.deleteSpecial(id);
    return HttpStatus.NO_CONTENT;
  }
}
