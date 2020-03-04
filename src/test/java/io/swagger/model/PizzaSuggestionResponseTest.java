package io.swagger.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PizzaSuggestionResponseTest {

  private PizzaSuggestionResponse response1;

  @Before
  public void setUp() {
    response1 = new PizzaSuggestionResponse(1, 1, 1);
  }

  @Test
  public void getSmallTest() {
    assertEquals((Integer) 1, response1.getSmall());
  }

  @Test
  public void getMediumTest() {
    assertEquals((Integer) 1, response1.getMedium());
  }

  @Test
  public void getLargeTest() {
    assertEquals((Integer) 1, response1.getLarge());
  }
}
