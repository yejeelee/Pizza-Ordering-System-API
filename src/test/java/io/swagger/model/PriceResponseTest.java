package io.swagger.model;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PriceResponseTest {

  public PriceResponse response1;
  public PriceResponse response2;

  @Before
  public void setUp() {
    response1 = new PriceResponse(5.00, "USD");
    response2 = new PriceResponse("CART_NOT_FOUND");
  }

  @Test
  public void isSuccessTest() {
    assertTrue(response1.isSuccess());
    assertFalse(response2.isSuccess());
  }

  @Test
  public void getPriceTest() {
    assertEquals((Double) 5.00, response1.getPrice());
    assertNull(response2.getPrice());
  }

  @Test
  public void getCurrencyTest() {
    assertEquals("USD", response1.getCurrency());
    assertNull(response2.getCurrency());
  }

  @Test
  public void getMessageTest() {
    assertEquals("CART_NOT_FOUND", response2.getMessage());
    assertNull(response1.getMessage());
  }
}
