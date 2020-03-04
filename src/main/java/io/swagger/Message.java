package io.swagger;

/** Error messages. */
public class Message {
  // Cart messages
  public static final String TOO_MANY_PIZZA_TOPPINGS = "TOO_MANY_PIZZA_TOPPINGS";
  public static final String STORE_NOT_FOUND = "STORE_NOT_FOUND";
  public static final String PIZZA_SIZE_NOT_FOUND = "PIZZA_SIZE_NOT_FOUND";
  public static final String STORE_CANNOT_SERVE_GLUTEN_FREE = "STORE_CANNOT_SERVE_GLUTEN_FREE";
  public static final String SIDE_NOT_FOUND = "SIDE_NOT_FOUND";
  public static final String TOPPING_NOT_FOUND = "TOPPING_NOT_FOUND";
  public static final String CART_NOT_FOUND = "CART_NOT_FOUND";

  // Apply Special messages
  public static final String ERROR_DISCOUNT_30_PERCENT = "ERROR_CART_MUST_CONTAIN_TWO_LARGE_TOPPINGLESS_PIZZAS";
  public static final String ERROR_FREE_PIZZA = "ERROR_CART_MUST_CONTAIN_TWO_OR_MORE_PIZZAS";
  public static final String ERROR_FREE_SODA = "ERROR_CART_MUST_CONTAIN_ONE_OR_MORE_PIZZAS_AND_DRINKS";
  public static final String ERROR_INVALID_SPECIAL = "ERROR_INVALID_SPECIAL";
  public static final String ERROR_NO_CART = "ERROR_CART_NOT_AT_STORE";
  public static final String ERROR_ONE_SPECIAL = "ERROR_ONLY_ONE_SPECIAL_PER_CART";

  // Purchase messages
  public static final String CARD_EXPIRED = "CARD_EXPIRED";
  public static final String INVALID_EXP_MONTH = "INVALID_EXP_MONTH";
}
