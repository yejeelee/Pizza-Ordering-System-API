package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

public class CartTest {

  public Cart cart;
  public ObjectId id;

  @Before
  public void setUp() {
    id = new ObjectId();
    cart = new Cart("brooklyn", id);
  }

  @Test
  public void testGetId() {
    assertEquals(id.toString(), cart.getId());
  }

  @Test
  public void testSetId() {
    ObjectId id1 = new ObjectId();
    cart.setId(id1);
    assertEquals(id1.toString(), cart.getId());
  }

  @Test
  public void testGetStoreID() {
    assertEquals("brooklyn", cart.getStoreID());
  }

  @Test
  public void testSetStoreID() {
    cart.setStoreID("stoneWay");
    assertEquals("stoneWay", cart.getStoreID());
  }

  @Test
  public void testGetPrice() {
    assertEquals((Double) 0.00, cart.getTotalAmount());
  }

  @Test
  public void testGetSide() {
    List<String> sides = cart.getSides();
    sides.add("pepperoni");
    assertEquals(1, sides.size());
    assertTrue(sides.contains("pepperoni"));
  }

  @Test
  public void testGetPizza() {
    List<Pizza> pizzas = cart.getPizzas();
    Pizza pizza = new Pizza("small", false);
    pizzas.add(pizza);
    assertEquals(1, pizzas.size());
    assertTrue(pizzas.contains(pizza));
  }

  @Test
  public void testToString() {
    String test =
        "Cart{cartId='"
            + id.toString()
            + "', storeId='brooklyn', listOfPizza=[], listOfSide=[], totalPrice=0.0, specialApplied=false}";
    assertEquals(test, cart.toString());
  }

  @Test
  public void testIsSpecialApplied() {
    assertFalse(cart.isSpecialApplied());
    cart.setSpecialApplied(true);
    assertTrue(cart.isSpecialApplied());
  }

  @Test
  public void testSettingNewCartTotalAmount() {
    assertEquals((Double) 0.00, cart.getTotalAmount());
    cart.setTotalAmount(5.00);
    assertEquals((Double) 5.00, cart.getTotalAmount());
  }

  @Test
  public void testEqual() {
    ObjectId id2 = new ObjectId();
    Cart cart2 = new Cart("eastlake", id2);
    Cart sameAsCart = new Cart("brooklyn", id);
    assertEquals(cart, cart);
    assertEquals(sameAsCart, cart);
    assertNotEquals(cart, null);
    assertNotEquals(cart2, cart);
    SpecialItem special = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description");
    assertNotEquals(cart, special);
    Cart cart3 = new Cart("brooklyn", id);
    Pizza pizza = new Pizza("small", false);
    cart3.getPizzas().add(pizza);
    assertNotEquals(cart3, cart);
    Cart cart4 = new Cart("brooklyn", id);
    cart4.getSides().add("16OzCoke");
    assertNotEquals(cart4, cart);
    Cart cart5 = new Cart("eastlake", id);
    assertNotEquals(cart5, cart);
    Cart cart6 = new Cart("brooklyn", id);
    cart6.setTotalAmount(5.00);
    assertNotEquals(cart6, cart);
    Cart cart7 = new Cart("brooklyn", id);
    cart7.setSpecialApplied(true);
    assertNotEquals(cart7, cart);
  }
}
