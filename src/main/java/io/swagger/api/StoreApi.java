package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.model.StoreItem;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "stores", description = "the store API")

/** Interface for the Store API */
public interface StoreApi {

  /**
   * Get all stores.
   *
   * @return a list of all StoreItems.
   */
  ResponseEntity<List<StoreItem>> getAllStores();

  /**
   * Get a specific StoreItem by id
   *
   * @param id id of requested StoreItem
   * @return specified StoreItem
   */
  ResponseEntity<StoreItem> getStoreById(@PathVariable String id);

  /**
   * Add a StoreItem.
   *
   * @param newStore new StoreItem to add
   * @return StoreItem added to database.
   */
  ResponseEntity<StoreItem> addStore(
      @ApiParam(value = "StoreItem to add") @Valid @RequestBody StoreItem newStore);

  /**
   * Delete a StoreItem by id.
   *
   * @param id id of StoreItem to delete
   * @return {@code HttpStatus.NO_CONTENT} if store successfully removed and {@code
   *     HttpStatus.NOT_FOUND} if id wasn't found.
   */
  ResponseEntity<Void> deleteStore(
      @ApiParam(value = "StoreItem to delete") @PathVariable String id);
}
