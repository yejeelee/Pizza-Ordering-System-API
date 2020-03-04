package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Card;
import io.swagger.model.PurchaseResponse;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "purchase", description = "Purchase API")
/** Interface for Purchase API */
public interface PurchaseApi {

  /**
   * Make purchase using CartId, StoreId, and the Card information
   *
   * @param cartId cartId given to find all items that needs to purchase
   * @param storeId storeId given to find the cart
   * @param card card information given to purchase
   * @return successful PurchaseResponse contains receipt and failed PurchaseResponse contains error
   *     message.
   */
  public ResponseEntity<PurchaseResponse> makePurchase(
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId,
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "Card information to purchase") @Valid @RequestBody Card card);
}
