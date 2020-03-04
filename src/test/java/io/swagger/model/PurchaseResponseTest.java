package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import io.swagger.Message;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

public class PurchaseResponseTest {

  private PurchaseResponse response1;
  private PurchaseResponse response2;
  private Receipt receipt;
  private Cart cart;
  private Card card;

  @Before
  public void setUp() {
    card = makeCard();
    cart = makeCart();
    receipt = new Receipt(cart, card);
    response1 = new PurchaseResponse(receipt);
    response2 = new PurchaseResponse(Message.CART_NOT_FOUND);
  }

  public Card makeCard() {
    return new Card("YeJee", "Lee", "4400616718352235", 1, 2021);
  }

  public Cart makeCart() {
    ObjectId id = new ObjectId();
    return new Cart("brooklyn", id);
  }

  @Test
  public void getReceiptTest() {
    assertEquals(receipt, response1.getReceipt());
    assertEquals(null, response2.getReceipt());
  }

  @Test
  public void isSuccessTest() {
    assertTrue(response1.isSuccess());
    assertFalse(response2.isSuccess());
  }

  @Test
  public void getMessageTest() {
    assertEquals(Message.CART_NOT_FOUND, response2.getMessage());
    assertNull(response1.getMessage());
  }
}
