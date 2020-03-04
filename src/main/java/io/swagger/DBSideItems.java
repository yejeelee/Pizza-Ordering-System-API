package io.swagger;

import io.swagger.model.SideItem;

/**
 * StoreItems to be added to database.
 */
public class DBSideItems {
  // Appetizers
  public static final SideItem BREADSTICKS = new SideItem("breadsticks",
      "breadsticks", 5.99, "appetizer");
  public static final SideItem CHEESE_STICKS = new SideItem("cheeseSticks",
      "cheese sticks", 6.99, "appetizer");
  public static final SideItem BARBECUE_WINGS = new SideItem("barbecueWings",
      "barbecue wings", 7.99, "appetizer");
  public static final SideItem HOT_WINGS = new SideItem("hotWings",
      "hot wings", 7.99, "appetizer");
  public static final SideItem UNSAUCED_WINGS = new SideItem("unsaucedWings",
      "unsauced wings", 6.99, "appetizer");
  // Condiments
  public static final SideItem BARBECUE_SAUCE = new SideItem("barbecueSauce",
      "barbecue sauce", 0.49, "condiment");
  public static final SideItem HOT_SAUCE = new SideItem("hotSauce",
      "hot sauce", 0.49, "condiment");
  public static final SideItem MARINARA_SAUCE = new SideItem("marinaraSauce",
      "marinara sauce", 0.49, "condiment");
  public static final SideItem RANCH_SAUCE = new SideItem("ranchSauce",
      "ranch sauce", 0.49, "condiment");
  public static final SideItem GRATED_CHEESE = new SideItem("gratedCheese",
      "grated Parmesan cheese", 0.25, "condiment");
  public static final SideItem RED_PEPPER_FLAKES = new SideItem("redPepperFlakes",
      "red pepper flakes", 0.25, "condiment");
  // Desserts
  public static final SideItem BROWNIE = new SideItem("brownie",
      "brownie", 2.49, "dessert");
  public static final SideItem CHOCOLATE_CHIP_COOKIE = new SideItem("chocolateChipCookie",
      "chocolate chip cookie", 1.99, "dessert");
  // Drinks
  public static final SideItem BIG_COKE = new SideItem("2LiterCoke",
      "2 liter Coke", 2.49, "drink");
  public static final SideItem BIG_WATER = new SideItem("2LiterWater",
      "2 liter Glaceau Smartwater", 2.49, "drink");
  public static final SideItem BIG_PEACH_CRUSH = new SideItem("2LiterPeachCrush",
      "2 liter Peach Crush", 2.49, "drink");
  public static final SideItem BIG_SPRITE = new SideItem("2LiterSprite",
      "2 liter Sprite", 2.49, "drink");
  public static final SideItem SMALL_COKE = new SideItem("16OzCoke",
      "16 oz Coke", 1.49, "drink");
  public static final SideItem SMALL_WATER = new SideItem("16OzWater",
      "16 oz Glaceau Smartwater", 1.49, "drink");
  public static final SideItem SMALL_PEACH_CRUSH = new SideItem("16OzPeachCrush",
      "16 oz Peach Crush", 1.49, "drink");
  public static final SideItem SMALL_SPRITE = new SideItem("16OzSprite",
      "16 oz Sprite", 1.49, "drink");
}
