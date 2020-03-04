package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.model.SideItem;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "sides", description = "the side API")

/** Interface for the Side API */
public interface SideApi {

  /**
   * Get all sides.
   *
   * @return a list of all SideItems.
   */
  ResponseEntity<List<SideItem>> getAllSides();

  /**
   * Get a specific SideItem by id
   *
   * @param id id of requested SideItem
   * @return specified SideItem
   */
  ResponseEntity<SideItem> getSideById(@PathVariable String id);

  /**
   * Add a SideItem.
   *
   * @param newSide new SideItem to add
   * @return SideItem added to database.
   */
  ResponseEntity<SideItem> addSide(
      @ApiParam(value = "SideItem to add") @Valid @RequestBody SideItem newSide);

  /**
   * Delete a SideItem by id.
   *
   * @param id id of SideItem to delete
   * @return {@code HttpStatus.NO_CONTENT} if side successfully removed and {@code
   *     HttpStatus.NOT_FOUND} if id wasn't found.
   */
  ResponseEntity<Void> deleteSide(@PathVariable String id);
}
