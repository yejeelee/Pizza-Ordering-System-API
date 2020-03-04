package io.swagger.service;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Java6Assertions.fail;
import static org.junit.Assert.assertEquals;

import io.swagger.model.SideItem;
import io.swagger.repository.SideItemRepository;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@TestPropertySource(locations = "classpath:/application-test.properties")
public class SideServiceTest {
  @Autowired public SideService sideService;

  @Autowired public SideItemRepository sideRepo;

  @Before
  public void setUp() {
    sideRepo.deleteAll();
  }

  String WATER = "16OzWater";
  String CHOCOLATE_COOKIE = "chocolateChipCookie";

  private SideItem setUpWater() {
    SideItem side = new SideItem("16OzWater", "16 oz water", 1.49, "drink");
    sideRepo.insert(side);
    return side;
  }

  private SideItem setUpChocolateCookie() {
    SideItem side = new SideItem("chocolateChipCookie", "Chocolate chip cookie", 1.99, "dessert");
    sideRepo.insert(side);
    return side;
  }

  @Test
  public void getAllSideTest() {
    SideItem side1 = setUpWater();
    SideItem side2 = setUpChocolateCookie();

    assertEquals(2, sideRepo.count());
    List<SideItem> list = sideService.getAllSides();
    assertTrue(list.contains(side1));
    assertTrue(list.contains(side2));
  }

  @Test
  public void getSideByIdTest() {
    SideItem side = setUpWater();
    SideItem sideFromDB = sideService.getSideById(WATER);
    assertEquals(side, sideFromDB);
  }

  @Test
  public void addSideTest() {
    SideItem side = new SideItem("snack", "Snack", 5.99, "dessert");
    SideItem sideFromDB = sideService.addSide(side);
    assertEquals(side, sideFromDB);
    assertEquals(null, sideService.addSide(side));
  }

  @Test
  public void deleteSideTest() {
    setUpChocolateCookie();
    try {
      sideService.deleteSide(CHOCOLATE_COOKIE);
      assertEquals(0, sideRepo.count());
    } catch (Exception err) {
      fail(err.getMessage());
    }
  }
}
