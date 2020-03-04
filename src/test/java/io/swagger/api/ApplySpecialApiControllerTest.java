package io.swagger.api;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import io.swagger.DBPizzaSizes;
import io.swagger.DBSpecialItems;
import io.swagger.DBStoreItems;
import io.swagger.Message;
import io.swagger.model.ApplySpecialResponse;
import io.swagger.model.Cart;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.repository.CartRepository;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.service.CartService;
import io.swagger.service.PizzaSizeService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@EnableAutoConfiguration
@SpringBootTest
public class ApplySpecialApiControllerTest {
  @Autowired private ApplySpecialApi applySpecialApi;
  @Autowired private CartRepository cartRepository;
  @Autowired private PizzaSizeRepository sizeRepository;
  @Autowired private CartService cartService;
  @Autowired private PizzaSizeService sizeService;

  @Before
  public void setUp() {
    cartRepository.deleteAll();
    sizeRepository.deleteAll();
  }

  @Test
  public void testApplySpecial_Success() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId);
    cartRepository.save(cart);

    PizzaSize largeSize = DBPizzaSizes.LARGE;
    sizeService.addPizzaSize(largeSize);

    Pizza pizza = new Pizza(largeSize.getId(), false);
    cartService.addPizzaToCart(cart, pizza);
    cartService.addPizzaToCart(cart, pizza);

    ResponseEntity<ApplySpecialResponse> responseEntity =
        applySpecialApi.applySpecial(
            DBSpecialItems.BUY_1_GET_1_FREE.getId(),
            DBStoreItems.BROOKLYN_STORE.getId(),
            cart.getId());

    assertTrue(responseEntity.getBody().getSuccess());
    assertNull(responseEntity.getBody().getMessage());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void testApplySpecial_Failure_CartNotAtStore() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.EASTLAKE_STORE.getId(), cartId);
    cartRepository.save(cart);
    ResponseEntity<ApplySpecialResponse> responseEntity =
        applySpecialApi.applySpecial(
            DBSpecialItems.BUY_1_GET_1_FREE.getId(),
            DBStoreItems.EASTLAKE_STORE.getId(),
            "wrongCartId");

    assertFalse(responseEntity.getBody().getSuccess());
    assertEquals(Message.ERROR_NO_CART, responseEntity.getBody().getMessage());
    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
  }

  @Test
  public void testApplySpecial_Failure_InvalidSpecial() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId);
    cartRepository.save(cart);
    ResponseEntity<ApplySpecialResponse> responseEntity =
        applySpecialApi.applySpecial(
            "invalidSpecial", DBStoreItems.BROOKLYN_STORE.getId(), cart.getId());

    assertFalse(responseEntity.getBody().getSuccess());
    assertEquals(Message.ERROR_INVALID_SPECIAL, responseEntity.getBody().getMessage());
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }

  @Test
  public void testApplySpecial_Failure_SpecialAlreadyApplied() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId);
    cart.setSpecialApplied(true);
    cartRepository.save(cart);
    ResponseEntity<ApplySpecialResponse> responseEntity =
        applySpecialApi.applySpecial(
            DBSpecialItems.BUY_1_GET_1_FREE.getId(),
            DBStoreItems.BROOKLYN_STORE.getId(),
            cart.getId());

    assertFalse(responseEntity.getBody().getSuccess());
    assertEquals(Message.ERROR_ONE_SPECIAL, responseEntity.getBody().getMessage());
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }
}
