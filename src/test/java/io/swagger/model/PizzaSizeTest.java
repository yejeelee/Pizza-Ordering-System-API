package io.swagger.model;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class PizzaSizeTest {

  public PizzaSize size1;
  public PizzaSize size2;

  @Before
  public void setUp() {
    size1 = new PizzaSize("small", "small", "11", 13.00);
    size2 = new PizzaSize("medium", "medium", "13", 15.00);
  }

  @Test
  public void getIdTest() {
    assertEquals("small", size1.getId());
  }

  @Test
  public void getSizeNameTest() {
    assertEquals("small", size1.getSizeName());
  }

  @Test
  public void getSizeInchTest() {
    assertEquals("11", size1.getSizeInch());
  }

  @Test
  public void getPriceTest() {
    assertEquals((Double) 13.00, size1.getPrice());
  }

  @Test
  public void setIdTest() {
    size2.setId("large");
    assertEquals("large", size2.getId());
  }

  @Test
  public void setSizeNameTest() {
    size2.setSizeName("large");
    assertEquals("large", size2.getSizeName());
  }

  @Test
  public void setSizeInchTest() {
    size2.setSizeInch("18");
    assertEquals("18", size2.getSizeInch());
  }

  @Test
  public void setPriceTest() {
    size2.setPrice(25.00);
    assertEquals((Double) 25.00, size2.getPrice());
  }

  @Test
  public void equalTest() {
    PizzaSize sameAsSize1 = new PizzaSize("small", "small", "11", 13.00);

    assertTrue(size1.equals(sameAsSize1));
    assertTrue(size1.equals(size1));
    assertFalse(size1.equals(size2));
    assertFalse(size1.equals(null));
    SpecialItem special = new SpecialItem("2", "Buy1Get1Free", "BuyOneGetOne description");
    assertNotEquals(size1, special);
    PizzaSize size3 = new PizzaSize("small", "small1", "11", 13.00);
    assertNotEquals(size1, size3);
    PizzaSize size4 = new PizzaSize("small", "small", "13", 13.00);
    assertNotEquals(size1, size4);
    PizzaSize size5 = new PizzaSize("small", "small", "11", 15.00);
    assertNotEquals(size1, size5);
  }

  @Test
  public void toStringTest() {
    assertEquals(size1.toString(), "PizzaSize{id=small, sizeName=small, sizeInch=11, price=13.0}");
  }
}
