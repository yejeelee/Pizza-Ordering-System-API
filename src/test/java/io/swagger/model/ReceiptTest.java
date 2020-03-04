package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.GregorianCalendar;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

public class ReceiptTest {

  private Receipt receipt;
  private Cart cart;
  private Card card;
  private GregorianCalendar time;

  @Before
  public void setUp() {
    cart = makeCart();
    card = makeCard();
    time = new GregorianCalendar();
    receipt = new Receipt(cart, card);
  }

  public Card makeCard() {
    return new Card("YeJee", "Lee", "4400616718352235", 1, 2021);
  }

  public Cart makeCart() {
    ObjectId id = new ObjectId();
    return new Cart("brooklyn", id);
  }

  @Test
  public void getReceiptId() {
    assertNotNull(receipt.getReceiptId());
  }

  @Test
  public void getTimePlacedTest() {
    Receipt sameReceipt = receipt;
    assertNotNull(receipt.getTimePlaced());
    assertEquals(sameReceipt.getTimePlaced(), receipt.getTimePlaced());
  }

  @Test
  public void getCartTest() {
    assertEquals(cart, receipt.getCart());
  }

  @Test
  public void getCardTest() {
    assertEquals(card, receipt.getCard());
  }

  @Test
  public void toStringTest() {
    String test =
        "Receipt{id="
            + receipt.getReceiptId()
            + ", "
            + "Time placed="
            + receipt.getTimePlaced()
            + ", "
            + "StoreId="
            + cart.getStoreID()
            + ", "
            + "Pizzas="
            + cart.getPizzas()
            + ", "
            + "Sides="
            + cart.getSides()
            + ", "
            + "Total Amount="
            + cart.getTotalAmount()
            + ", "
            + "Card=card number ending with "
            + card.getCardNumber().substring(this.card.getCardNumber().length() - 4)
            + "}";
    assertEquals(test, receipt.toString());
  }

  @Test
  public void equalsTest() {
    assertEquals(receipt, receipt);
    assertNotEquals(receipt, cart);
    assertNotEquals(receipt, null);
    Receipt sameReceipt = receipt;
    assertEquals(receipt, sameReceipt);
    Card card1 = new Card("YeJee", "Lee", "4400616718352235", 2, 2021);
    Receipt receipt1 = new Receipt(cart, card1);
    assertNotEquals(receipt, receipt1);
    ObjectId id = new ObjectId();
    Cart cart1 = new Cart("brooklyn", id);
    Receipt receipt2 = new Receipt(cart1, card);
    assertNotEquals(receipt, receipt2);
    Receipt receipt3 = new Receipt(cart, card);
    assertNotEquals(receipt, receipt3);
  }
}
