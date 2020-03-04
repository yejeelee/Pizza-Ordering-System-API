package io.swagger.service;

import static org.assertj.core.api.Java6Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import io.swagger.model.SpecialItem;
import io.swagger.repository.SpecialItemRepository;
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
public class SpecialServiceTest {

  @Autowired public SpecialItemService specialService;

  @Autowired public SpecialItemRepository specialRepo;

  @Before
  public void setUp() {
    specialRepo.deleteAll();
  }

  private SpecialItem setupBuy1Get1FreeSpecial() {
    SpecialItem special = new SpecialItem("buy1get1Free", "Buy1Get1", "description1");
    specialRepo.insert(special);
    return special;
  }

  private SpecialItem setupFreeSodaSpecial() {
    SpecialItem special = new SpecialItem("freeSoda", "freeSoda", "description2");
    specialRepo.insert(special);
    return special;
  }

  @Test
  public void getAllSpecialsTest() {
    SpecialItem special1 = setupBuy1Get1FreeSpecial();
    SpecialItem special2 = setupFreeSodaSpecial();
    assertEquals(2, specialRepo.count());
    List<SpecialItem> allSpecials = specialService.getAllSpecials();
    assertTrue(allSpecials.contains(special1));
    assertTrue(allSpecials.contains(special2));
  }

  @Test
  public void getSpecialByIdTest() {
    SpecialItem special = setupBuy1Get1FreeSpecial();
    SpecialItem specialFromService = specialService.getSpecialById(special.getId());

    assertNotNull(specialFromService);
    assertEquals(special, specialFromService);
    assertNull(specialService.getSpecialById("noSpecial"));
  }

  @Test
  public void addSpecialTest() {
    SpecialItem special2 = new SpecialItem("free16OzSoda", "Free16OzSoda", "description2");

    SpecialItem specialFromDB = specialService.addSpecial(special2);
    assertEquals(special2, specialFromDB);
  }

  @Test
  public void deleteSpecialTest() {
    SpecialItem special = setupFreeSodaSpecial();
    try {
      specialService.deleteSpecial(special.getId());
      assertEquals(0, specialRepo.count());
    } catch (Exception err) {
      fail(err.getMessage());
    }
  }
}
