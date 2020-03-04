package io.swagger;

import io.swagger.model.PizzaSize;

/**
 * PizzaSizes to be added to database.
 */
public class DBPizzaSizes {
  public static final PizzaSize LARGE = new PizzaSize("large",
      "large", "16", 20.99);
  public static final PizzaSize MEDIUM = new PizzaSize("medium",
      "medium", "13", 17.99);
  public static final PizzaSize SMALL = new PizzaSize("small",
      "small", "11", 13.99);
}
