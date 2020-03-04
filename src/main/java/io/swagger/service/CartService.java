package io.swagger.service;

import io.swagger.exceptions.ToppingNotFoundException;
import io.swagger.model.Cart;
import io.swagger.model.Pizza;
import io.swagger.model.SideItem;
import io.swagger.repository.CartRepository;
import io.swagger.repository.SideItemRepository;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

  @Autowired private CartRepository cartRepository;

  @Autowired private SideItemRepository sideRepository;

  @Autowired private PizzaService pizzaService;

  /**
   * Get Cart information by StoreID and CartID.
   *
   * @param storeId storeId given to connect cart.
   * @param cartId cartId given to this function.
   * @return Cart if cartId was found from the store. Null if cartID and storeID are not connected.
   */
  public Cart getCartItemsById(String storeId, String cartId) {
    for (Cart cart : cartRepository.findAll()) {
      if (cart.getId().equals(cartId) && cart.getStoreID().equals(storeId)) {
        return cart;
      }
    }
    return null;
  }

  /**
   * Create Cart using the objectId
   *
   * @param storeId storeId given to connect cart.
   * @return Cart that was created or get from database.
   */
  public Cart createCart(String storeId) {
    Cart cart = new Cart(storeId, new ObjectId());
    cartRepository.insert(cart);
    return cart;
  }

  /**
   * Get or create Cart using the objectId using String cartId.
   *
   * @param storeId storeId given to connect cart.
   * @param cartId cartId given to get or create.
   * @return Cart that was created or get from database.
   */
  public Cart getOrCreateCart(String storeId, String cartId) {
    Cart cart = getCartItemsById(storeId, cartId);
    if (cart == null) {
      cart = createCart(storeId);
    }
    return cart;
  }

  /**
   * This function add pizza to Cart.
   *
   * @param cart cart given to add pizza.
   * @param pizza pizza given to add to Cart.
   * @return Pizza that was added to Cart.
   * @throws ToppingNotFoundException if the topping in the pizza is not valid.
   */
  public Pizza addPizzaToCart(Cart cart, Pizza pizza) throws ToppingNotFoundException {
    Double pizzaPrice = pizzaService.getPizzaPrice(pizza);
    cart.getPizzas().add(pizza);
    Double price = cart.getTotalAmount() + pizzaPrice;
    price = Math.round(price * 100.0) / 100.0;
    cart.setTotalAmount(price);
    cart.setSpecialApplied(false);
    cartRepository.save(cart);
    return pizza;
  }

  /**
   * This function add side to Cart. If the cartId doesn't exist in the store, we create new Cart in
   * the store and add side. Note that if input cartID doesn't exist, finalized cartID is newly
   * made.
   *
   * @param cart cart given to add SideItem.
   * @param sideID sideId given to add to the Cart.
   * @return SideItem that was added.
   */
  public SideItem addSideToCart(Cart cart, String sideID) {
    SideItem side = sideRepository.findById(sideID).get();
    Double price = cart.getTotalAmount() + side.getPrice();
    price = Math.round(price * 100.0) / 100.0;
    cart.setTotalAmount(price);
    cart.getSides().add(sideID);
    cart.setSpecialApplied(false);
    cartRepository.save(cart);
    return side;
  }

  /**
   * This function delete the Cart.
   *
   * @param cartId cartId given to delete.
   */
  public void deleteCart(String cartId) {
    cartRepository.deleteById(cartId);
  }

  /**
   * This function delete the Cart.
   *
   * @param cartId cartId given to delete.
   */
  public void deleteCart(ObjectId cartId) {
    deleteCart(cartId.toString());
  }

  /**
   * Get the total price of items(pizzas + sides) in the Cart using cartId.
   *
   * @param cartId cartId given to get the total price of whole items.
   * @return total price of the cart.
   */
  public Double getTotalAmountInCart(String cartId) {
    Cart cart = cartRepository.findById(cartId).get();
    Double price = 0.0;
    price += getSidesPrice(cart);
    price += getPizzasPrice(cart);
    return Math.round(price * 100.0) / 100.0;
  }

  /**
   * Get the total price of items(pizzas + sides) in the Cart using cart object.
   *
   * @param cart cart given to get the total price of whole items.
   * @return total price of the cart.
   */
  public Double getTotalAmountInCart(Cart cart) {
    return getTotalAmountInCart(cart.getId());
  }

  /**
   * Get the total price of all sideItems in the Cart.
   *
   * @param cart cart given to get the total price of side items.
   * @return price of all side items in the Cart.
   */
  public Double getSidesPrice(Cart cart) {
    Double price = 0.00;
    List<String> sides = cart.getSides();
    for (String sideId : sides) {
      SideItem sideItem = sideRepository.findById(sideId).get();
      price += sideItem.getPrice();
    }
    return Math.round(price * 100.0) / 100.0;
  }

  /**
   * Get the total price of all Pizzas in the Cart.
   *
   * @param cart cart given to calculate the total price of all pizzas in this Cart.
   * @return price of all pizzas in the Cart.
   * @throws RuntimeException if topping of the pizza is invalid
   */
  public Double getPizzasPrice(Cart cart) {
    Double price = 0.00;
    List<Pizza> pizzas = cart.getPizzas();
    for (Pizza pizza : pizzas) {
      try {
        price += pizzaService.getPizzaPrice(pizza);
      } catch (ToppingNotFoundException e) {
        // Remember that the assert won't happen in production
        throw new RuntimeException(e);
      }
    }
    return Math.round(price * 100.0) / 100.0;
  }

  /**
   * Delete a side from a Cart.
   *
   * @param cart cart given to delete side.
   * @param side side given to delete from cart.
   */
  public void deleteSideFromCart(Cart cart, SideItem side) {
    cart.getSides().remove(side.getId());
    cartRepository.save(cart);
    cart.setTotalAmount(getTotalAmountInCart(cart));
    cart.setSpecialApplied(false);
    cartRepository.save(cart);
  }

  /**
   * Delete a pizza from a Cart.
   *
   * @param cart cart given to delete a pizza.
   * @param pizza pizza given to delete from list of pizza.
   * @return true if the pizza was successfully deleted, false otherwise.
   */
  public boolean deletePizzaFromCart(Cart cart, Pizza pizza) {
    boolean deletedPizza = cart.getPizzas().remove(pizza);
    if (!deletedPizza) {
      return false;
    }
    cartRepository.save(cart);
    cart.setSpecialApplied(false);
    cart.setTotalAmount(getTotalAmountInCart(cart));
    cartRepository.save(cart);
    return true;
  }
}
