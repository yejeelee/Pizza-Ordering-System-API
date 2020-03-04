package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.model.SideItem;
import io.swagger.service.SideService;
import java.util.List;
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
public class SideApiController implements SideApi {

  @Autowired private SideService sideService;

  /** {@inheritDoc} HttpStatus.OK - if SideItems are successfully found. */
  @GetMapping("/side")
  @ApiOperation(
      value = "Get all SideItems",
      tags = {
        "side",
      })
  public ResponseEntity<List<SideItem>> getAllSides() {
    return new ResponseEntity<List<SideItem>>(sideService.getAllSides(), HttpStatus.OK);
  }

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if id is not found in database. HttpStatus.OK - if
   * SideItem is successfully found.
   */
  @GetMapping("/side/{id}")
  @ApiOperation(
      value = "Get a specific SideItem using id",
      tags = {
        "side",
      })
  public ResponseEntity<SideItem> getSideById(@PathVariable String id) {
    SideItem sideItem = sideService.getSideById(id);
    if (sideItem != null) {
      return new ResponseEntity<SideItem>(sideItem, HttpStatus.OK);
    }
    return new ResponseEntity<SideItem>(HttpStatus.NOT_FOUND);
  }

  /** {@inheritDoc} HttpStatus.OK- if SideItem is successfully added or updated. */
  @PostMapping("/side")
  @ApiOperation(
      value = "Add a SideItem",
      tags = {
        "side",
      })
  public ResponseEntity<SideItem> addSide(SideItem newSide) {
    return new ResponseEntity<SideItem>(sideService.addSide(newSide), HttpStatus.OK);
  }

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if id is not found in database. HttpStatus.OK - if
   * SideItem is successfully deleted.
   */
  @DeleteMapping("/side/{id}")
  @ApiOperation(
      value = "Delete a SideItem using id",
      tags = {
        "side",
      })
  public ResponseEntity<Void> deleteSide(String id) {
    SideItem sideItem = sideService.getSideById(id);
    if (sideItem != null) {
      sideService.deleteSide(id);
      return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
  }
}
