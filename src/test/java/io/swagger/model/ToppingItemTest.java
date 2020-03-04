package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class ToppingItemTest {

  public ToppingItem topping1;
  public ToppingItem topping2;

  @Before
  public void setUp() {
    topping1 = new ToppingItem("pepperoni1", "pepperoni", "meat", 2.5, 2.75, 3.0, "gluten");
    topping2 = new ToppingItem("bacon1", "bacon", "meat", 2.50, 2.75, 3.00, "non-gluten");
  }

  @Test
  public void getIdTest() {
    assertEquals("pepperoni1", topping1.getId());
  }

  @Test
  public void setIdTest() {
    topping2.setId("onion1");
    assertEquals("onion1", topping2.getId());
  }

  @Test
  public void getNameTest() {
    assertEquals("pepperoni", topping1.getToppingName());
  }

  @Test
  public void setNameTest() {
    topping2.setToppingName("onion");
    assertEquals("onion", topping2.getToppingName());
  }

  @Test
  public void getPriceTest() {
    Double price;
    price = 2.5;
    assertEquals(price, topping1.getToppingSmallPrice());
  }

  @Test
  public void setPriceTest() {
    topping2.setToppingMediumPrice(3.00);
    assertEquals((Double) 3.00, topping2.getToppingMediumPrice());
  }

  @Test
  public void equalsTest() {
    assertNotEquals(topping2, topping1);
    assertEquals(topping2, topping2);
    assertNotNull(topping2);
    assertNotEquals(topping1, null);
    ToppingItem topping3 =
        new ToppingItem("pepperoni1", "pepperoni1", "meat", 2.5, 2.75, 3.0, "gluten");
    assertNotEquals(topping1, topping3);
    ToppingItem topping4 =
        new ToppingItem("pepperoni1", "pepperoni", "vege", 2.5, 2.75, 3.0, "gluten");
    assertNotEquals(topping1, topping4);
    ToppingItem topping5 =
        new ToppingItem("pepperoni1", "pepperoni", "meat", 3.5, 2.75, 3.0, "gluten");
    assertNotEquals(topping1, topping5);
    ToppingItem topping6 =
        new ToppingItem("pepperoni1", "pepperoni", "meat", 2.5, 3.75, 3.0, "gluten");
    assertNotEquals(topping1, topping6);
    ToppingItem topping7 =
        new ToppingItem("pepperoni1", "pepperoni", "meat", 2.5, 2.75, 4.0, "gluten");
    assertNotEquals(topping1, topping7);
    ToppingItem topping8 =
        new ToppingItem("pepperoni1", "pepperoni", "meat", 2.5, 2.75, 3.0, "non-gluten");
    assertNotEquals(topping1, topping8);
  }

  @Test
  public void hashCodeTest() {
    ToppingItem toppingItem1 =
        new ToppingItem("pepperoni1", "pepperoni", "meat", 2.5, 2.75, 3.0, "gluten");
    ToppingItem toppingItem3 =
        new ToppingItem("onion1", "onion", "vegetable", 2.0, 2.25, 2.5, "non-gluten");

    assertEquals(toppingItem1.hashCode(), toppingItem1.hashCode());
    assertNotEquals(toppingItem3.hashCode(), toppingItem1.hashCode());
  }

  @Test
  public void toStringTest() {

    final String TOPPING_ITEM_AS_STRING =
        "ToppingItem{"
            + "id='"
            + topping1.getId()
            + '\''
            + ", topping name='"
            + topping1.getToppingName()
            + '\''
            + ", topping type='"
            + topping1.getToppingType()
            + '\''
            + ", topping small price='"
            + topping1.getToppingSmallPrice()
            + '\''
            + ", topping medium price='"
            + topping1.getToppingMediumPrice()
            + '\''
            + ", topping large price='"
            + topping1.getToppingLargePrice()
            + '\''
            + ", topping gluten='"
            + topping1.getToppingGluten()
            + '\''
            + '}';

    assertEquals(TOPPING_ITEM_AS_STRING, topping1.toString());
  }

  @Test
  public void setToppingTypeTest() {
    topping1.setToppingType("vegetable");
    assertEquals("vegetable", topping1.getToppingType());
  }

  @Test
  public void setToppingSmallPriceTest() {
    assertEquals((Double) 2.5, topping1.getToppingSmallPrice());
    topping1.setToppingSmallPrice(1.00);
    assertEquals((Double) 1.00, topping1.getToppingSmallPrice());
  }

  @Test
  public void setToppingMedPriceTest() {
    assertEquals((Double) 2.75, topping1.getToppingMediumPrice());
    topping1.setToppingMediumPrice(3.00);
    assertEquals((Double) 3.00, topping1.getToppingMediumPrice());
  }

  @Test
  public void setToppingLargePriceTest() {
    topping1.setToppingLargePrice(3.00);
    assertEquals((Double) 3.00, topping1.getToppingLargePrice());
  }

  @Test
  public void setGlutenTest() {
    topping2.setToppingGluten("gluten");
    assertEquals("gluten", topping2.getToppingGluten());
  }
}
