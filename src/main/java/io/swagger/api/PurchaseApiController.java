package io.swagger.api;

import io.swagger.Message;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Card;
import io.swagger.model.Cart;
import io.swagger.model.PurchaseResponse;
import io.swagger.model.Receipt;
import io.swagger.service.CartService;
import io.swagger.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class PurchaseApiController implements PurchaseApi {

  @Autowired private CartService cartService;

  @Autowired private PurchaseService purchaseService;

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if storeId and cartId are not matching.
   * HttpStatus.BAD_REQUEST - if the month is less than 1 or greater than 12. HttpStatus.BAD_REQUEST
   * - if the card is expired. HttpStatus.OK - if the receipt was successfully created.
   */
  @PostMapping("/cart/{storeId}/{cartId}/purchase")
  @ApiOperation(
      value = "Create receipt when user purchase.",
      tags = {
        "purchase",
      })
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "BAD_REQUEST"),
        @ApiResponse(code = 404, message = "NOT_FOUND")
      })
  public ResponseEntity<PurchaseResponse> makePurchase(String cartId, String storeId, Card card) {
    Cart cart = cartService.getCartItemsById(storeId, cartId);
    if (cart == null) {
      return new ResponseEntity<PurchaseResponse>(
          new PurchaseResponse(Message.CART_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    if (card.getExpMonth() < 1 || card.getExpMonth() > 12) {
      return new ResponseEntity<PurchaseResponse>(
          new PurchaseResponse(Message.INVALID_EXP_MONTH), HttpStatus.BAD_REQUEST);
    }
    Receipt receipt = purchaseService.makeReceipt(cart, card);
    if (receipt == null) {
      return new ResponseEntity<PurchaseResponse>(
          new PurchaseResponse(Message.CARD_EXPIRED), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<PurchaseResponse>(new PurchaseResponse(receipt), HttpStatus.OK);
  }
}
