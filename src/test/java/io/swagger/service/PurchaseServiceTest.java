package io.swagger.service;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import io.swagger.model.Card;
import io.swagger.model.Cart;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.Receipt;
import io.swagger.model.ToppingItem;
import io.swagger.repository.CartRepository;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.repository.StoreItemRepository;
import io.swagger.repository.ToppingItemRepository;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@TestPropertySource(locations = "classpath:/application-test.properties")
public class PurchaseServiceTest {

  @Autowired public CartRepository cartRepo;

  @Autowired public StoreItemRepository storeRepo;

  @Autowired private ToppingItemRepository toppingRepo;

  @Autowired private PizzaSizeRepository sizeRepo;

  @Autowired private PurchaseService purchaseService;

  @Before
  public void setUp() {
    cartRepo.deleteAll();
    storeRepo.deleteAll();
    toppingRepo.deleteAll();
    sizeRepo.deleteAll();
  }

  String TEST_STORE_1 = "eastlake";
  String BACON = "bacon1";
  String SMALL_SIZE = "small";

  private PizzaSize setUpSmallSize() {
    PizzaSize pizzaSize = new PizzaSize("small", "Small", "6", 9.99);
    sizeRepo.insert(pizzaSize);
    return pizzaSize;
  }

  private ToppingItem setupBacon() {
    ToppingItem bacon = new ToppingItem("bacon1", "bacon", "meat", 2.50, 2.75, 3.00, "gluten");
    toppingRepo.insert(bacon);
    return bacon;
  }

  private Pizza setUpPizza(String size, boolean gluten) {
    Pizza pizza = new Pizza(size, gluten);
    return pizza;
  }

  private Cart setUpNewCart() {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1, cartId);
    setupBacon();
    setUpSmallSize();
    Pizza smallPizza = setUpPizza(SMALL_SIZE, true);
    smallPizza.getToppingIDs().add(BACON);
    cartRepo.insert(cart);
    return cart;
  }

  @Test
  public void validateExpDateTest() {
    assertTrue(purchaseService.validateExpDate(12, 2025));
    assertFalse(purchaseService.validateExpDate(11, 2019));
  }

  @Test
  public void secureCardNumberTest() {
    Card card = new Card("YeJee", "Lee", "4400616718352235", 1, 2021);
    assertEquals("2235", purchaseService.secureCardNumber(card).getCardNumber());
  }

  @Test
  public void makeReceiptTest() {
    Cart cart = setUpNewCart();
    Card card1 = new Card("YeJee", "Lee", "4400616718352235", 12, 2030);
    Receipt receipt1 = purchaseService.makeReceipt(cart, card1);
    assertNotNull(receipt1);

    Card card2 = new Card("YeJee", "Lee", "4400616718352235", 11, 2019);
    Receipt receipt2 = purchaseService.makeReceipt(cart, card2);
    assertNull(receipt2);
  }
}
