package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.model.StoreItem;
import io.swagger.service.StoreService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class StoreApiController implements StoreApi {

  @Autowired private StoreService storeService;

  /** {@inheritDoc} HttpStatus.OK - if StoreItems are successfully found. */
  @GetMapping("/store")
  @ApiOperation(
      value = "Get all StoreItems",
      tags = {
        "store",
      })
  public ResponseEntity<List<StoreItem>> getAllStores() {
    return new ResponseEntity<List<StoreItem>>(storeService.getAllStores(), HttpStatus.OK);
  }

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if id is not found in database. HttpStatus.OK - if
   * StoreItem is successfully found.
   */
  @GetMapping("/store/{id}")
  @ApiOperation(
      value = "Get a specific StoreItem using id",
      tags = {
        "store",
      })
  public ResponseEntity<StoreItem> getStoreById(@PathVariable String id) {
    Optional<StoreItem> storeItem = storeService.getStoreById(id);
    if (storeItem.isPresent()) {
      return new ResponseEntity<StoreItem>(storeItem.get(), HttpStatus.OK);
    }
    return new ResponseEntity<StoreItem>(HttpStatus.NOT_FOUND);
  }

  /** {@inheritDoc} HttpStatus.OK - if StoreItem is successfully added or updated. */
  @PostMapping("/store")
  @ApiOperation(
      value = "Add a StoreItem",
      tags = {
        "store",
      })
  public ResponseEntity<StoreItem> addStore(StoreItem newStore) {
    return new ResponseEntity<StoreItem>(storeService.addStore(newStore), HttpStatus.OK);
  }

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if id is not found in database. HttpStatus.NO_CONTENT - if
   * StoreItem is successfully removed.
   */
  @DeleteMapping("/store/{id}")
  @ApiOperation(
      value = "Delete a StoreItem using id",
      tags = {
        "store",
      })
  public ResponseEntity<Void> deleteStore(@PathVariable String id) {
    Optional<StoreItem> storeItem = storeService.getStoreById(id);
    if (storeItem.isPresent()) {
      storeService.deleteStore(id);
      return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
  }
}
