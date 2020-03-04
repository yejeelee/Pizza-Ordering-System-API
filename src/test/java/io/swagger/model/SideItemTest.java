package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import io.swagger.DBSideItems;
import org.junit.Test;

public class SideItemTest {
  private SideItem sideItem = DBSideItems.HOT_WINGS;
  private SideItem sameObject = DBSideItems.HOT_WINGS;
  private SideItem differentObject = DBSideItems.CHOCOLATE_CHIP_COOKIE;

  @Test
  public void testGetId() {
    assertEquals("hotWings", sideItem.getId());
  }

  @Test
  public void testSetId() {
    sideItem.setId("cheeseSticks");
    assertEquals("cheeseSticks", sideItem.getId());
  }

  @Test
  public void testGetName() {
    assertEquals("hot wings", sideItem.getName());
  }

  @Test
  public void testSetName() {
    sideItem.setName("cheese sticks");
    assertEquals("cheese sticks", sideItem.getName());
  }

  @Test
  public void testGetPrice() {
    Double price = 7.99;
    assertEquals(price, sideItem.getPrice());
  }

  @Test
  public void testSetPrice() {
    Double price = 3.99;
    sideItem.setPrice(price);
    assertEquals(price, sideItem.getPrice());
  }

  @Test
  public void testGetType() {
    assertEquals("appetizer", sideItem.getType());
  }

  @Test
  public void testSetType() {
    sideItem.setType("dessert");
    assertEquals("dessert", sideItem.getType());
  }

  @Test
  public void testEquals() {
    assertEquals(sameObject, sideItem);
    assertNotEquals(differentObject, sideItem);
    assertNotEquals(2, sideItem);
    assertNotEquals(sideItem, null);
    SpecialItem special = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description");
    assertNotEquals(sideItem, special);
    SideItem side2 = new SideItem("hotWings1", "Hot wings", 7.99, "appetizer");
    assertNotEquals(side2, sideItem);
    SideItem side3 = new SideItem("hotWings", "Hot wings1", 7.99, "appetizer");
    assertNotEquals(side3, sideItem);
    SideItem side4 = new SideItem("hotWings", "Hot wings", 8.99, "appetizer");
    assertNotEquals(side4, sideItem);
    SideItem side5 = new SideItem("hotWings", "Hot wings", 7.99, "appetizer1");
    assertNotEquals(side5, sideItem);
  }

  @Test
  public void testHashCode() {
    assertEquals(sameObject.hashCode(), sideItem.hashCode());
    assertNotEquals(differentObject.hashCode(), sideItem.hashCode());
  }

  @Test
  public void testToString() {
    final String SIDE_ITEM_AS_STRING =
        "SideItem{"
            + "id='"
            + "cheeseSticks"
            + '\''
            + ", name='"
            + "hot wings"
            + '\''
            + ", price='"
            + 7.99
            + '\''
            + ", type='"
            + "appetizer"
            + '\''
            + '}';

    assertEquals(SIDE_ITEM_AS_STRING, sideItem.toString());
  }
}
