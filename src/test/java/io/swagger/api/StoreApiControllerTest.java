package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import io.swagger.DBStoreItems;
import io.swagger.model.StoreItem;
import io.swagger.repository.StoreItemRepository;
import io.swagger.service.StoreService;
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
@SpringBootTest(classes = {StoreApiController.class, StoreService.class})

public class StoreApiControllerTest {

  @MockBean
  private StoreItemRepository storeItemRepository;

  @Autowired
  private StoreService storeService;

  @Autowired
  private StoreApi storeApi;

  @Before
  public void setUp() {
    storeItemRepository.deleteAll();
  }

  private void setUpStoreRepo() {
    StoreItem store1 = storeService.addStore(DBStoreItems.BROOKLYN_STORE);
    StoreItem store2 = storeService.addStore(DBStoreItems.EASTLAKE_STORE);
    StoreItem store3 = storeService.addStore(
        DBStoreItems.STONE_WAY_STORE);
    storeItemRepository.insert(store1);
    storeItemRepository.insert(store2);
    storeItemRepository.insert(store3);

    when(storeItemRepository.findAll())
        .thenReturn(Stream
            .of(DBStoreItems.BROOKLYN_STORE, DBStoreItems.EASTLAKE_STORE,
                DBStoreItems.STONE_WAY_STORE)
            .collect(Collectors.toList()));

    when(storeItemRepository.findById("brooklyn"))
        .thenReturn(Optional.of(DBStoreItems.BROOKLYN_STORE));
  }

  @Test
  public void testGetAllStore() {
    setUpStoreRepo();
    final ResponseEntity<List<StoreItem>> response = storeApi.getAllStores();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(3, response.getBody().size());
  }

  @Test
  public void testGetStoreById() {
    setUpStoreRepo();
    final ResponseEntity<StoreItem> responseOk = storeApi.getStoreById("brooklyn");
    assertEquals(HttpStatus.OK, responseOk.getStatusCode());
  }

  @Test
  public void testAddStore() {
    setUpStoreRepo();
    StoreItem storeItem4 = new StoreItem("broadway",
        "1234 Broadway Ave E", "Seattle", "Washington",
        "98103", true);
    final ResponseEntity<StoreItem> response = storeApi.addStore(storeItem4);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testDeleteStore() {
    setUpStoreRepo();
    final HttpStatus deleteStore = storeApi.deleteStore("brooklyn").getStatusCode();
    assertEquals(HttpStatus.NO_CONTENT, deleteStore);
  }
}