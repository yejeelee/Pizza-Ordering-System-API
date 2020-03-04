package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.SpecialItem;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/** SpecialAPI interface */
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "special", description = "the special API")
public interface SpecialApi {

  /**
   * Get all special items
   *
   * @return List of all SpecialItem from database
   */
  ResponseEntity<List<SpecialItem>> getAllSpecials();

  /**
   * Get a specific specialItem by specialId
   *
   * @param id special id to find
   * @return specialItem found by Id
   */
  ResponseEntity<SpecialItem> getSpecialById(
      @ApiParam(value = "SpecialID") @PathVariable String id);

  /**
   * Add a new special to the database
   *
   * @param specialItem specialId given to add to the database
   * @return Special Item that is added
   */
  public ResponseEntity<SpecialItem> addSpecial(
      @ApiParam(value = "SpecialItem to add") @Valid @RequestBody SpecialItem specialItem);

  /**
   * Delete a special Item found by id from database
   *
   * @param id id given for search
   * @return HttpStatus.NO_CONTENT if successfully removed, HttpStatus.NOT_FOUND if id wasn't found.
   */
  HttpStatus deleteSpecial(@ApiParam(value = "SpecialID") @PathVariable String id);
}
