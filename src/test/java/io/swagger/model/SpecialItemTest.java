package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class SpecialItemTest {

  public SpecialItem special1;
  public SpecialItem special2;

  @Before
  public void setUp() {
    special1 = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description");
    special2 = new SpecialItem("2", "1SodaFree", "you get 1 soda description");
  }

  @Test
  public void getIdTest() {
    assertEquals("1", special1.getId());
  }

  @Test
  public void getNameTest() {
    assertEquals("Buy1Get1Free", special1.getName());
  }

  @Test
  public void getDescriptionTest() {
    assertEquals("BuyOneGetOne description", special1.getDescription());
  }

  @Test
  public void setIdTest() {
    special2.setId("2SodaFree");
    assertEquals("2SodaFree", special2.getId());
  }

  @Test
  public void setNameTest() {
    special2.setName("2SodaFree");
    assertEquals("2SodaFree", special2.getName());
  }

  @Test
  public void setDescriptionTest() {
    special2.setDescription("you get 2 soda description");
    assertEquals("you get 2 soda description", special2.getDescription());
  }

  @Test
  public void equalTest() {
    SpecialItem sameAsSpecial1 = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description");
    assertEquals(special1, sameAsSpecial1);
    assertEquals(special1, special1);
    assertNotEquals(special1, special2);
    assertNotEquals(special1, null);
    SpecialItem special3 = new SpecialItem("2", "Buy1Get1Free", "BuyOneGetOne description");
    assertNotEquals(special1, special3);
    SpecialItem special4 = new SpecialItem("1", "Buy1Get1Free1", "BuyOneGetOne description");
    assertNotEquals(special1, special4);
    SpecialItem special5 = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description1");
    assertNotEquals(special1, special5);
    ToppingItem topping =
        new ToppingItem("pepperoni1", "pepperoni1", "meat", 2.5, 2.75, 3.0, "gluten");
    assertNotEquals(special1, topping);
  }

  @Test
  public void toStringTest() {
    assertEquals(
        special1.toString(),
        "SpecialItem{id=1, name=Buy1Get1Free, description=BuyOneGetOne description}");
  }
}
