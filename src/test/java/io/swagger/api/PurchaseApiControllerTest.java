package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import io.swagger.DBPizzaSizes;
import io.swagger.DBStoreItems;
import io.swagger.model.Card;
import io.swagger.model.Cart;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.PurchaseResponse;
import io.swagger.model.Receipt;
import io.swagger.repository.CartRepository;
import io.swagger.service.CartService;
import io.swagger.service.PizzaSizeService;
import io.swagger.service.PurchaseService;
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
public class PurchaseApiControllerTest {

  @Autowired
  private PurchaseApiController purchaseApi;
  @Autowired
  private CartRepository cartRepository;
  @Autowired
  private CartService cartService;
  @Autowired
  private PizzaSizeService pizzaSizeService;
  @Autowired
  private PurchaseService purchaseService;


  @Before
  public void setUp() {
    cartRepository.deleteAll();
  }

  @Test
  public void testMakePurchaseOk() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId);
    cartRepository.save(cart);

    PizzaSize largeSize = DBPizzaSizes.SMALL;
    pizzaSizeService.addPizzaSize(largeSize);

    Pizza pizza = new Pizza(largeSize.getId(), true);
    cartService.addPizzaToCart(cart, pizza);
    cartService.addPizzaToCart(cart, pizza);

    Card card = new Card("Mary", "Smith", "4400616718352235", 12, 2030);
    Receipt receipt1 = purchaseService.makeReceipt(cart, card);

    final ResponseEntity<PurchaseResponse> response = purchaseApi
        .makePurchase(cart.getId(), cart.getStoreID(), card);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(receipt1);
  }

  @Test
  public void testMakePurchaseExpCard() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId);
    cartRepository.save(cart);

    PizzaSize largeSize = DBPizzaSizes.SMALL;
    pizzaSizeService.addPizzaSize(largeSize);

    Pizza pizza = new Pizza(largeSize.getId(), true);
    cartService.addPizzaToCart(cart, pizza);
    cartService.addPizzaToCart(cart, pizza);

    Card card = new Card("Bob", "Smith", "4400616718352235", 12, 2000);

    final ResponseEntity<PurchaseResponse> response = purchaseApi
        .makePurchase(cart.getId(), cart.getStoreID(), card);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  public void testMakePurchaseInvalidCardMonth() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId);
    cartRepository.save(cart);

    PizzaSize largeSize = DBPizzaSizes.SMALL;
    pizzaSizeService.addPizzaSize(largeSize);

    Pizza pizza = new Pizza(largeSize.getId(), true);
    cartService.addPizzaToCart(cart, pizza);
    cartService.addPizzaToCart(cart, pizza);

    Card card = new Card("Bob", "Smith", "4400616718352235", 13, 2025);

    final ResponseEntity<PurchaseResponse> response = purchaseApi
        .makePurchase(cart.getId(), cart.getStoreID(), card);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  public void testMakePurchaseCartNotFound() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId);
    cartRepository.save(cart);

    PizzaSize largeSize = DBPizzaSizes.SMALL;
    pizzaSizeService.addPizzaSize(largeSize);

    Pizza pizza = new Pizza(largeSize.getId(), true);
    cartService.addPizzaToCart(cart, pizza);
    cartService.addPizzaToCart(cart, pizza);

    Card card = new Card("Mary", "Smith", "4400616718352235", 12, 2025);

    ObjectId cartId2 = new ObjectId();
    Cart cart2 = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId2);

    final ResponseEntity<PurchaseResponse> response = purchaseApi
        .makePurchase(cart2.getId(), cart2.getStoreID(), card);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());


  }
}