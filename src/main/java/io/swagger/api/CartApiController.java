package io.swagger.api;

import io.swagger.Message;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Cart;
import io.swagger.model.CartAddResponse;
import io.swagger.model.Pizza;
import io.swagger.model.PriceResponse;
import io.swagger.model.SideItem;
import io.swagger.service.CartService;
import io.swagger.service.PizzaSizeService;
import io.swagger.service.SideService;
import io.swagger.service.StoreService;
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
public class CartApiController implements CartApi {

  @Autowired private CartService cartService;
  @Autowired private PizzaSizeService sizeService;
  @Autowired private SideService sideService;
  @Autowired private StoreService storeService;

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if storeId and cartId are not matching. HttpStatus.OK - if
   * Cart was successfully found.
   */
  @GetMapping("/cart/{storeId}/{cartId}")
  @ApiOperation(
      value = "Get all items in the cart with specific id.",
      tags = {
        "cart",
      })
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "NOT_FOUND")
      })
  public ResponseEntity<Cart> getCartItemsById(String storeId, String cartId) {
    if (cartService.getCartItemsById(storeId, cartId) == null) {
      return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Cart>(cartService.getCartItemsById(storeId, cartId), HttpStatus.OK);
  }

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if storeId and cartId are not matching. HttpStatus.OK - if
   * price of the Cart was successfully found.
   */
  @GetMapping("/cart/{storeId}/{cartId}/price")
  @ApiOperation(
      value = "Get price of all items in the cart with specific id.",
      tags = {
        "cart",
      })
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "NOT_FOUND")
      })
  public ResponseEntity<PriceResponse> getPriceOfCartById(String storeId, String cartId) {
    final PriceResponse response;
    if (cartService.getCartItemsById(storeId, cartId) == null) {
      response = new PriceResponse(Message.CART_NOT_FOUND);
      return new ResponseEntity<PriceResponse>(response, HttpStatus.NOT_FOUND);
    }
    Double price = cartService.getTotalAmountInCart(cartId);
    response = new PriceResponse(price, "USD");
    return new ResponseEntity<PriceResponse>(response, HttpStatus.OK);
  }

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if the storeId is not found. HttpStatus.NOT_FOUND - if the
   * pizzaSizeId is not found. HttpStatus.BAD_REQUEST - if the store's gluten setting does not match
   * with given gluten. HttpStatus.BAD_REQUEST - if pizza toppings is greater than maximum toppings
   * number. HttpStatus.OK - if pizza was successfully added to Cart.
   */
  @PostMapping("/cart/{storeId}/{cartId}/pizza")
  @ApiOperation(
      value = "add pizza to the specific cart or by creating new cart.",
      tags = {
        "cart",
      })
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "BAD_REQUEST"),
        @ApiResponse(code = 404, message = "NOT_FOUND")
      })
  public ResponseEntity<CartAddResponse> addPizzaToCart(
      String storeId, String cartId, Pizza pizza) {
    final CartAddResponse response;
    if (pizza.getToppingIDs().size() > Pizza.MAXIMUM_TOPPING_COUNT) {
      response = new CartAddResponse(Message.TOO_MANY_PIZZA_TOPPINGS);
      return new ResponseEntity<CartAddResponse>(response, HttpStatus.BAD_REQUEST);
    }
    if (storeService.getStoreById(storeId) == null) {
      response = new CartAddResponse(Message.STORE_NOT_FOUND);
      return new ResponseEntity<CartAddResponse>(response, HttpStatus.NOT_FOUND);
    }
    if (sizeService.getPizzaSizeById(pizza.getSizeID()) == null) {
      response = new CartAddResponse(Message.PIZZA_SIZE_NOT_FOUND);
      return new ResponseEntity<CartAddResponse>(response, HttpStatus.NOT_FOUND);
    }
    if (!pizza.isGluten() && !storeService.storeOffersGlutenFree(storeId)) {
      response = new CartAddResponse(Message.STORE_CANNOT_SERVE_GLUTEN_FREE);
      return new ResponseEntity<CartAddResponse>(response, HttpStatus.BAD_REQUEST);
    }
    try {
      Cart cart = cartService.getOrCreateCart(storeId, cartId);
      cartService.addPizzaToCart(cart, pizza);
      response = new CartAddResponse(pizza, cart.getId(), storeId);
      return new ResponseEntity<CartAddResponse>(response, HttpStatus.OK);
    } catch (Exception exception) {
      return new ResponseEntity<CartAddResponse>(
          new CartAddResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if the storeId is not found. HttpStatus.NOT_FOUND - if the
   * sideId is not found. HttpStatus.OK - if side was successfully added to Cart.
   */
  @PostMapping("/cart/{storeId}/{cartId}/side")
  @ApiOperation(
      value = "add side to the specific cart or by creating new cart.",
      tags = {
        "cart",
      })
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "NOT_FOUND")
      })
  public ResponseEntity<CartAddResponse> addSideToCart(
      String storeId, String cartId, String sideId) {
    final CartAddResponse response;
    if (storeService.getStoreById(storeId) == null) {
      response = new CartAddResponse(Message.STORE_NOT_FOUND);
      return new ResponseEntity<CartAddResponse>(response, HttpStatus.NOT_FOUND);
    }
    if (sideService.getSideById(sideId) == null) {
      response = new CartAddResponse(Message.SIDE_NOT_FOUND);
      return new ResponseEntity<CartAddResponse>(response, HttpStatus.NOT_FOUND);
    }
    Cart cart = cartService.getOrCreateCart(storeId, cartId);
    SideItem side = cartService.addSideToCart(cart, sideId);
    response = new CartAddResponse(side, cart.getId(), storeId);
    return new ResponseEntity<CartAddResponse>(response, HttpStatus.OK);
  }

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if storeId and cartId are not matching.
   * HttpStatus.NO_CONTENT - if cartId is successfully removed.
   */
  @DeleteMapping("/cart/{storeId}/{cartId}")
  @ApiOperation(
      value = "Delete a Cart with id.",
      tags = {
        "cart",
      })
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "NO_CONTENT"),
        @ApiResponse(code = 404, message = "NOT_FOUND")
      })
  public HttpStatus deleteCart(String storeId, String cartId) {
    if (cartService.getCartItemsById(storeId, cartId) == null) {
      return HttpStatus.NOT_FOUND;
    }
    cartService.deleteCart(cartId);
    return HttpStatus.NO_CONTENT;
  }

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if storeId and cartId are not matching.
   * HttpStatus.NOT_FOUND - if the sideId is not found. HttpStatus.NO_CONTENT - if side is
   * successfully removed from Cart.
   */
  @DeleteMapping("/cart/{storeId}/{cartId}/side")
  @ApiOperation(
      value = "Delete a sideItem from a Cart with id.",
      tags = {
        "cart",
      })
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "NO_CONTENT"),
        @ApiResponse(code = 404, message = "NOT_FOUND")
      })
  public HttpStatus deleteSideFromCart(String storeId, String cartId, String sideId) {
    Cart cart = cartService.getCartItemsById(storeId, cartId);
    if (cart == null) {
      return HttpStatus.NOT_FOUND;
    }
    if (!cart.getSides().contains(sideId)) {
      return HttpStatus.BAD_REQUEST;
    }
    cartService.deleteSideFromCart(cart, sideService.getSideById(sideId));
    return HttpStatus.NO_CONTENT;
  }

  /**
   * {@inheritDoc} HttpStatus.NOT_FOUND - if storeId and cartId are not matching.
   * HttpStatus.BAD_REQUEST - if given pizzaIndex is less than 0. HttpStatus.NO_CONTENT - if side is
   * successfully removed from Cart.
   */
  @DeleteMapping("/cart/{storeId}/{cartId}/pizza")
  @ApiOperation(
      value = "Delete a pizza from a Cart with index number(starting zero).",
      tags = {
        "cart",
      })
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "NO_CONTENT"),
        @ApiResponse(code = 400, message = "BAD_REQUEST"),
        @ApiResponse(code = 404, message = "NOT_FOUND")
      })
  public HttpStatus deletePizzaFromCart(String storeId, String cartId, Pizza pizza)
      throws Exception {
    Cart cart = cartService.getCartItemsById(storeId, cartId);
    if (cart == null) {
      return HttpStatus.NOT_FOUND;
    }
    List<Pizza> pizzas = cart.getPizzas();
    if (!pizzas.contains(pizza)) {
      return HttpStatus.BAD_REQUEST;
    }
    cartService.deletePizzaFromCart(cart, pizza);
    return HttpStatus.NO_CONTENT;
  }
}
