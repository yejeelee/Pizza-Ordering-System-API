package io.swagger.model;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class CartAddResponseTest {

  public CartAddResponse response1;
  public CartAddResponse response2;
  public CartAddResponse response3;
  public Pizza pizza;
  public SideItem side;

  @Before
  public void setUp() {
    pizza = new Pizza("small", true);
    side = new SideItem("2LiterPeachCrush", "2 liter Peach Crush", 2.99, "drink");
    response1 = new CartAddResponse(pizza, "cart1", "brooklyn");
    response2 = new CartAddResponse("STORE_NOT_FOUND");
    response3 = new CartAddResponse(side, "cart2", "eastlake");
  }

  @Test
  public void TestGetPizza() {
    assertNull(response2.getPizza());
    assertEquals(pizza, response1.getPizza());
  }

  @Test
  public void TestGetSide() {
    assertNull(response2.getSide());
    assertEquals(side, response3.getSide());
  }

  @Test
  public void TestIsSuccess() {
    assertTrue(response1.getSuccess());
    assertFalse(response2.getSuccess());
  }

  @Test
  public void TestGetCartID() {
    assertNull(response2.getCartID());
    assertEquals("cart1", response1.getCartID());
  }

  @Test
  public void TestGetStoreID() {
    assertNull(response2.getStoreID());
    assertEquals("eastlake", response3.getStoreID());
  }

  @Test
  public void TestGetMessage() {
    assertNull(response3.getMessage());
    assertEquals("STORE_NOT_FOUND", response2.getMessage());
  }

  @Test
  public void TestToString() {
    assertEquals(
        "CartAddResponse{success=true, cartId=cart1, storeID=brooklyn, "
            + "pizza=Pizza{sizeID=small, gluten=true, toppingIDs=[]},"
            + " side=null, message=null}",
        response1.toString());
  }

  @Test
  public void TestEqual() {
    CartAddResponse sameAsResponse1 = new CartAddResponse(pizza, "cart1", "brooklyn");
    CartAddResponse sameAsResponse2 = new CartAddResponse("STORE_NOT_FOUND");
    CartAddResponse sameAsResponse3 = new CartAddResponse(side, "cart2", "eastlake");
    assertEquals(response1, response1);
    assertEquals(sameAsResponse1, response1);
    assertEquals(sameAsResponse2, response2);
    assertEquals(sameAsResponse3, response3);
    assertNotEquals(response1, response2);
    assertNotEquals(response2, response3);
    assertNotNull(response1);
    SpecialItem special = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description");
    assertNotEquals(special, response1);
    assertNotEquals(null, response1);
    CartAddResponse response4 = new CartAddResponse(pizza, "cart1", "eastlake");
    assertNotEquals(response4, response1);
    Pizza pizza2 = new Pizza("medium", true);
    CartAddResponse response5 = new CartAddResponse(pizza2, "cart1", "brooklyn");
    assertNotEquals(response5, response1);
    SideItem side2 = new SideItem("2LiterCoke", "2 liter Coke", 3.99, "drink");
    CartAddResponse response6 = new CartAddResponse(side2, "cart2", "eastlake");
    assertNotEquals(response6, response3);
    assertNotEquals(response6, response1);
    CartAddResponse response7 = new CartAddResponse("CART_NOT_FOUND");
    assertNotEquals(response7, response2);
  }
}
