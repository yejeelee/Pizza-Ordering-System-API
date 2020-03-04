package io.swagger;

import io.swagger.model.ToppingItem;

/**
 * ToppingItems to be added to database.
 */
public class DBToppingItems {
  public static final ToppingItem BLACK_OLIVES = new ToppingItem("blackOlives",
      "black olives", "vegetable", 2.00,
      2.25, 2.50, "non-gluten");
  public static final ToppingItem GREEN_PEPPERS = new ToppingItem("greenPeppers",
      "green peppers", "vegetable", 2.00,
      2.25, 2.50, "non-gluten");
  public static final ToppingItem MUSHROOMS = new ToppingItem("mushrooms",
      "mushrooms", "vegetable", 2.00,
      2.25, 2.50, "non-gluten");
  public static final ToppingItem ONION = new ToppingItem("onion",
      "onion", "vegetable", 2.00,
      2.25, 2.50, "non-gluten");
  public static final ToppingItem PEPPERONI = new ToppingItem("pepperoni",
      "pepperoni", "meat", 2.50,
      2.75, 3.00, "gluten");
  public static final ToppingItem SAUSAGE = new ToppingItem("sausage",
      "sausage", "meat", 2.50,
      2.75, 3.00, "gluten");
}
