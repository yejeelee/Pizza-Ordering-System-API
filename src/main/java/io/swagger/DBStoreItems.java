package io.swagger;

import io.swagger.model.StoreItem;

/**
 * StoreItems to be added to database.
 */
public class DBStoreItems {
  public static final StoreItem BROOKLYN_STORE = new StoreItem("brooklyn",
      "4717 Brooklyn Ave NE Ste A", "Seattle", "Washington",
      "98105", false);
  public static final StoreItem EASTLAKE_STORE = new StoreItem("eastlake",
      "3242 Eastlake Ave E", "Seattle", "Washington",
      "98102", true);
  public static final StoreItem STONE_WAY_STORE = new StoreItem("stoneWay",
      "4003 Stone Way N", "Seattle", "Washington",
      "98103", true);
}
