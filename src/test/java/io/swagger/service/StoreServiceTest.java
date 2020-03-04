package io.swagger.service;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import io.swagger.model.StoreItem;
import io.swagger.repository.StoreItemRepository;
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
public class StoreServiceTest {
  @Autowired public StoreService storeService;

  @Autowired public StoreItemRepository storeRepo;

  @Before
  public void setUp() {
    storeRepo.deleteAll();
  }

  String BROOKLYN = "brooklyn";
  String EASTLAKE = "eastlake";

  private StoreItem setUpBrooklynStore() {
    StoreItem store =
        new StoreItem("brooklyn", "4060 9th ave", "Seattle", "Washington", "98105", false);
    storeRepo.insert(store);
    return store;
  }

  private StoreItem setUpEastLakeStore() {
    StoreItem store =
        new StoreItem("eastlake", "4115 Roosevelt way NE", "Seattle", "Washington", "98105", true);
    storeRepo.insert(store);
    return store;
  }

  @Test
  public void getAllStoresTest() {
    StoreItem store1 = setUpBrooklynStore();
    StoreItem store2 = setUpEastLakeStore();

    assertEquals(2, storeRepo.count());
    List<StoreItem> list = storeService.getAllStores();
    assertTrue(list.contains(store1));
    assertTrue(list.contains(store2));
  }

  @Test
  public void getStoreByIdTest() {
    StoreItem store = setUpBrooklynStore();
    assertEquals(store, storeService.getStoreById(BROOKLYN).get());
  }

  @Test
  public void addStoreTest() {
    StoreItem store =
        new StoreItem("stoneWay", "777 Plank Rd", "Baton Rouge", "Louisiana", "98105", false);
    storeService.addStore(store);
    assertEquals(1, storeRepo.count());
    assertEquals(store, storeService.getStoreById("stoneWay").get());
  }

  @Test
  public void deleteStoreTest() {
    setUpEastLakeStore();
    try {
      storeService.deleteStore(EASTLAKE);
      assertEquals(0, storeRepo.count());
    } catch (Exception err) {
      fail(err.getMessage());
    }
  }

  @Test
  public void TestStoreCanServeGlutenFree() {
    StoreItem store = setUpBrooklynStore();
    assertFalse(storeService.storeOffersGlutenFree(store.getId()));
  }

  @Test
  public void TestStoreItemCanServeGlutenFree() {
    StoreItem store =
        new StoreItem("stoneWay", "777 Plank Rd", "Baton Rouge", "Louisiana", "98105", true);
    assertTrue(storeService.storeOffersGlutenFree(store));
  }
}
