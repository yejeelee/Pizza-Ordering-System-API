package io.swagger;

import io.swagger.model.SpecialItem;

/**
 * SpecialItems to be added to database.
 */
public class DBSpecialItems {
  public static final SpecialItem BUY_1_GET_1_FREE = new SpecialItem("buy1Get1Free",
      "Buy1Get1Free", "If you buy 1 pizza, you get 1 free pizza that is of equal"
      + "or less value.");
  public static final SpecialItem BUY_1_PIZZA_GET_SODA_FREE= new SpecialItem(
      "buy1PizzaGetSodaFree",
      "Buy1PizzaGetSodaFree", "If you buy 1 pizza, you get 1 free soda.");
  public static final SpecialItem BUY_2_LARGE_PIZZA_NO_TOPPING = new SpecialItem(
      "buy2LargePizzaNoTopping",
      "Buy2LargePIzzaNoTopping30", "If you buy 2 large pizzas that have no toppings, "
      + "you get 30% off total cart price.");
}
