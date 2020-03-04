package io.swagger.service;

import io.swagger.exceptions.ToppingNotFoundException;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.ToppingItem;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.repository.ToppingItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
  public static final String SIZE_SMALL = "small";
  public static final String SIZE_MEDIUM = "medium";
  public static final String SIZE_LARGE = "large";

  @Autowired private ToppingItemRepository toppingRepository;

  @Autowired private PizzaSizeRepository sizeRepository;

  /**
   * Get the price of the given pizza.
   *
   * @param pizza pizza given to calculate the pizza price.
   * @return Pizza that contains updated pizza price.
   * @throws ToppingNotFoundException throw exception if invalid topping.
   */
  public Double getPizzaPrice(Pizza pizza) throws ToppingNotFoundException {
    Double price = 0.00;
    String sizeID = pizza.getSizeID();
    PizzaSize pizzaSize = sizeRepository.findById(sizeID).get();
    List<String> toppings = pizza.getToppingIDs();
    price += pizzaSize.getPrice();
    price += getPizzaToppingPrice(sizeID, toppings);
    price = Math.round(price * 100.0) / 100.0;
    return price;
  }

  /**
   * SUB-Function of getPizzaPrice() Calculate the price of all toppings based on the size of pizza.
   * If size of pizza is small, then we calculate the small topping prices.
   *
   * @param sizeID sizeId given to provide the size of pizza
   * @param toppings list of toppings given to calculate the price
   * @return price of all toppings in the pizza.
   * @throws ToppingNotFoundException throw exception if invalid topping.
   */
  public Double getPizzaToppingPrice(String sizeID, List<String> toppings)
      throws ToppingNotFoundException {
    Double price = 0.00;
    for (String toppingID : toppings) {
      Optional<ToppingItem> topping = toppingRepository.findById(toppingID);
      if (!topping.isPresent()) {
        throw new ToppingNotFoundException(toppingID);
      }
      if (sizeID.equals(SIZE_SMALL)) {
        price += topping.get().getToppingSmallPrice();
      } else if (sizeID.equals(SIZE_MEDIUM)) {
        price += topping.get().getToppingMediumPrice();
      } else if (sizeID.equals(SIZE_LARGE)) {
        price += topping.get().getToppingLargePrice();
      }
    }
    return Math.round(price * 100.0) / 100.0;
  }
}
