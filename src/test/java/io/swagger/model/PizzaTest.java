package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PizzaTest {
  public Pizza pizza1;
  public Pizza pizza2;

  @Before
  public void setUp() {
    pizza1 = new Pizza("small", true);
    pizza2 = new Pizza("medium", false);
  }

  @Test
  public void getSizeIdTest() {
    assertEquals("small", pizza1.getSizeID());
  }

  @Test
  public void getGlutenTest() {
    assertTrue(pizza1.isGluten());
  }

  @Test
  public void getToppingIdsTest() {
    pizza1.getToppingIDs().add("pepperoni");
    pizza1.getToppingIDs().add("sausage");

    assertTrue(pizza1.getToppingIDs().contains("pepperoni"));
    assertTrue(pizza1.getToppingIDs().contains("sausage"));
  }

  @Test
  public void equalTest() {
    Pizza sameAsPizza1 = new Pizza("small", true);
    assertEquals(pizza1, sameAsPizza1);
    assertEquals(pizza1, pizza1);
    assertNotEquals(pizza1, pizza2);
    SpecialItem special = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description");
    assertNotEquals(pizza1, special);
    assertNotEquals(pizza1, null);
    Pizza pizza3 = new Pizza("small", false);
    assertNotEquals(pizza1, pizza3);
    pizza1.getToppingIDs().add("bacon");
    Pizza pizza4 = new Pizza("small", true);
    pizza4.getToppingIDs().add("bacon1");
    assertNotEquals(pizza1, pizza4);
  }

  @Test
  public void toStringTest() {
    assertEquals("Pizza{sizeID=small, gluten=true, toppingIDs=[]}", pizza1.toString());
  }
}
