package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import io.swagger.Message;
import org.junit.Before;
import org.junit.Test;

public class ApplySpecialResponseTest {
  private ApplySpecialResponse applySpecialResponse;
  private ApplySpecialResponse sameObject;
  private ApplySpecialResponse differentObject;

  @Before
  public void setUp() {
    applySpecialResponse = new ApplySpecialResponse("buy1PizzaGetSodaFree", 0.00);
    sameObject = new ApplySpecialResponse("buy1PizzaGetSodaFree", 0.00);
    differentObject = new ApplySpecialResponse("buy1Get1Free", 1.00);
  }

  @Test
  public void testGetSpecialId() {
    assertEquals("buy1PizzaGetSodaFree", applySpecialResponse.getSpecialId());
  }

  @Test
  public void testSetSpecialId() {
    applySpecialResponse.setSpecialId("buy2LargePizzaNoTopping");
    assertEquals("buy2LargePizzaNoTopping", applySpecialResponse.getSpecialId());
  }

  @Test
  public void testGetSuccess() {
    assertFalse(new ApplySpecialResponse(Message.ERROR_INVALID_SPECIAL).getSuccess());
  }

  @Test
  public void testSetSuccess() {
    applySpecialResponse.setSuccess(true);
    assertTrue(applySpecialResponse.getSuccess());
  }

  @Test
  public void testGetMessage() {
    assertNull(applySpecialResponse.getMessage());
  }

  @Test
  public void testSetMessage() {
    assertEquals(
        "ERROR_ONLY_ONE_SPECIAL_PER_CART",
        (new ApplySpecialResponse(Message.ERROR_ONE_SPECIAL)).getMessage());
  }

  @Test
  public void testGetSavings() {
    assertEquals(0.00, (double) applySpecialResponse.getSavings(), 0.001);
  }

  @Test
  public void testSetSavings() {
    applySpecialResponse.setSavings(2.49);
    assertEquals(2.49, (double) applySpecialResponse.getSavings(), 0.001);
  }

  @Test
  public void testEquals() {
    StoreItem storeItem = new StoreItem("id", "street", "city", "state", "zip", false);

    assertEquals(applySpecialResponse, applySpecialResponse);
    assertEquals(sameObject, applySpecialResponse);
    assertNotEquals(differentObject, applySpecialResponse);
    assertNotEquals(2, applySpecialResponse);
    assertNotEquals(null, applySpecialResponse);
    assertNotNull(applySpecialResponse);
    assertEquals(differentObject.getClass(), applySpecialResponse.getClass());
    assertNotEquals(storeItem.getClass(), applySpecialResponse.getClass());

    // only different savings
    applySpecialResponse.setSpecialId("buy1Get1Free");
    differentObject.setSpecialId("buy1Get1Free");
    applySpecialResponse.setSuccess(false);
    differentObject.setSuccess(false);
    applySpecialResponse.setMessage(Message.ERROR_FREE_PIZZA);
    differentObject.setMessage(Message.ERROR_FREE_PIZZA);
    applySpecialResponse.setSavings(0.00);
    differentObject.setSavings(45.00);
    assertNotEquals(differentObject, applySpecialResponse);

    // only different ids
    differentObject.setSavings(0.00);
    differentObject.setSpecialId("buy1PizzaGetSodaFree");
    assertNotEquals(differentObject, applySpecialResponse);

    // only different success
    differentObject.setSpecialId("buy1Get1Free");
    differentObject.setSuccess(true);
    assertNotEquals(differentObject, applySpecialResponse);

    // only different messages
    differentObject.setSuccess(false);
    differentObject.setMessage(Message.ERROR_FREE_SODA);
    assertNotEquals(differentObject, applySpecialResponse);
  }

  @Test
  public void testHashCode() {
    assertEquals(sameObject.hashCode(), applySpecialResponse.hashCode());
    assertNotEquals(differentObject.hashCode(), applySpecialResponse.hashCode());
  }

  @Test
  public void testToString() {
    final String APPLY_SPECIAL_AS_STRING =
        "ApplySpecialResponse{"
            + "specialId='"
            + applySpecialResponse.getSpecialId()
            + '\''
            + ", success='"
            + applySpecialResponse.getSuccess()
            + '\''
            + ", message='"
            + applySpecialResponse.getMessage()
            + '\''
            + ", savings='"
            + applySpecialResponse.getSavings()
            + '\''
            + '}';

    assertEquals(APPLY_SPECIAL_AS_STRING, applySpecialResponse.toString());
  }
}
