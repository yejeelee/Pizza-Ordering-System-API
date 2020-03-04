package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import io.swagger.DBSideItems;
import io.swagger.model.SideItem;
import io.swagger.repository.SideItemRepository;
import io.swagger.service.SideService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootTest(classes = {SideApiController.class, SideService.class})

public class SideApiControllerTest {

  @MockBean
  private SideItemRepository sideItemRepository;

  @Autowired
  private SideService sideService;

  @Autowired
  private SideApi sideApi;

  @Before
  public void setUp() {
    sideItemRepository.deleteAll();
  }

  private void setUpSideRepo() {
    SideItem side1 = sideService.addSide(DBSideItems.BREADSTICKS);
    SideItem side2 = sideService.addSide(DBSideItems.BARBECUE_SAUCE);
    SideItem side3 = sideService.addSide(
        DBSideItems.BROWNIE);
    sideItemRepository.insert(side1);
    sideItemRepository.insert(side2);
    sideItemRepository.insert(side3);

    when(sideItemRepository.findAll())
        .thenReturn(Stream
            .of(DBSideItems.BREADSTICKS, DBSideItems.BARBECUE_SAUCE, DBSideItems.BROWNIE)
            .collect(Collectors.toList()));

    when(sideItemRepository.findById("brownie"))
        .thenReturn(Optional.of(DBSideItems.BROWNIE));
  }

  @Test
  public void testGetAllSide() {
    setUpSideRepo();
    final ResponseEntity<List<SideItem>> response = sideApi.getAllSides();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(3, response.getBody().size());
  }

  @Test
  public void testGetSideById() {
    setUpSideRepo();
    final ResponseEntity<SideItem> responseOk = sideApi.getSideById("brownie");
    assertEquals(HttpStatus.OK, responseOk.getStatusCode());

    final ResponseEntity<SideItem> responseNull = sideApi.getSideById("applePie");
    assertEquals(HttpStatus.NOT_FOUND, responseNull.getStatusCode());
  }

  @Test
  public void testAddSide() {
    setUpSideRepo();
    SideItem sideItem4 = new SideItem("applePie",
        "apple pie", 2.49, "dessert");
    final ResponseEntity<SideItem> response = sideApi.addSide(sideItem4);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testDeleteSide() {
    setUpSideRepo();
    final HttpStatus deleteSide = sideApi.deleteSide("brownie").getStatusCode();
    assertEquals(HttpStatus.NO_CONTENT, deleteSide);

    final HttpStatus deleteSideNull = sideApi.deleteSide("bacon").getStatusCode();
    assertEquals(HttpStatus.NOT_FOUND, deleteSideNull);
  }
}