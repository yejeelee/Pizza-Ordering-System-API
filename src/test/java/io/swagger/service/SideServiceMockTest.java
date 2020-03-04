package io.swagger.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import io.swagger.model.SideItem;
import io.swagger.repository.SideItemRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@TestPropertySource(locations = "classpath:/application-test.properties")
public class SideServiceMockTest {
  final String TEST_SIDE_ID = "cheeseSticks";

  @Autowired private SideService sideService;

  @MockBean private SideItemRepository sideItemRepository;
  private SideItem testSide;
  private SideItem testSide2;
  private SideItem testSide3;

  @Before
  public void setUp() {
    testSide = new SideItem(TEST_SIDE_ID, "Cheesesticks", 7.99, "appetizer");
    testSide2 = new SideItem("chocolateChipCookie", "Chocolate chip cookie", 1.99, "dessert");
    testSide3 = new SideItem("brownie", "Brownie", 2.49, "dessert");

    when(sideItemRepository.findAll())
        .thenReturn(Stream.of(testSide, testSide2, testSide3).collect(Collectors.toList()));

    when(sideItemRepository.findById(TEST_SIDE_ID)).thenReturn(Optional.of(testSide));
  }

  @Test
  public void getAllSidesTest() {
    // Test size of list of sides
    assertEquals(3, sideService.getAllSides().size());
  }

  @Test
  public void getSideByIdTest() {

    for (SideItem item : sideService.getAllSides()) {
      System.err.println(item.toString());
    }

    final SideItem actualSide = sideService.getSideById(TEST_SIDE_ID);

    assertEquals(testSide, actualSide);
    assertEquals(testSide.toString(), actualSide.toString());
  }

  @Test
  public void getSideByIncorrectIdTest() {
    final String NONEXISTENT_ID = "1989";

    assertNull(sideService.getSideById(NONEXISTENT_ID));
  }

  @Test
  public void equalityTest() {
    assertEquals(testSide, testSide);
    assertNotEquals(testSide, testSide2);
    assertNotEquals(testSide, null);
  }

}
