package io.swagger.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import io.swagger.model.Cart;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.SideItem;
import io.swagger.model.ToppingItem;
import io.swagger.repository.CartRepository;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.repository.SideItemRepository;
import io.swagger.repository.StoreItemRepository;
import io.swagger.repository.ToppingItemRepository;
import org.bson.types.ObjectId;
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
public class CartServiceTest {

  @Autowired public CartService cartService;

  @Autowired public CartRepository cartRepo;

  @Autowired public StoreItemRepository storeRepo;

  @Autowired private ToppingItemRepository toppingRepo;

  @Autowired private PizzaSizeRepository sizeRepo;

  @Autowired private SideItemRepository sideRepo;

  @Before
  public void setUp() {
    cartRepo.deleteAll();
    storeRepo.deleteAll();
    toppingRepo.deleteAll();
    sizeRepo.deleteAll();
    sideRepo.deleteAll();
  }

  String TEST_STORE_1 = "eastlake";
  String TEST_STORE_2 = "brooklyn";
  String BACON = "bacon1";
  String SMALL_SIZE = "small";
  String WATER = "16OzWater";

  private SideItem setUpWater() {
    SideItem side = new SideItem("16OzWater", "16 oz water", 1.49, "drink");
    sideRepo.insert(side);
    return side;
  }

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

  private Cart setUpCart1() {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1, cartId);
    cartRepo.insert(cart);
    return cart;
  }

  @Test
  public void testGetCartById() {
    Cart cart = setUpCart1();
    Cart cartFromDB = cartService.getCartItemsById(TEST_STORE_1, cart.getId());
    assertEquals(cart, cartFromDB);
  }

  @Test
  public void testGetTotalAmountInCart() {
    Cart cart = setUpCart1();
    assertEquals((Double) 0.00, cartService.getTotalAmountInCart(cart));
  }

  @Test
  public void testGetOrCreateCart() {
    Cart cart1 = setUpCart1();
    assertEquals(cart1, cartService.getOrCreateCart(TEST_STORE_1, cart1.getId()));
    Cart cart2 = cartService.createCart(TEST_STORE_1);
    assertEquals(cart2, cartService.getOrCreateCart(TEST_STORE_1, cart2.getId()));
    Cart cart3 = cartService.getOrCreateCart(TEST_STORE_1, "NoCartId");
    assertEquals(cart3, cartService.getOrCreateCart(TEST_STORE_1, cart3.getId()));
  }

  @Test
  public void testGetCartByIdChecksStore() {
    Cart cart = setUpCart1();
    // Getting a cart from a different store should return null
    Cart cartFromDB = cartService.getCartItemsById(TEST_STORE_2, cart.getId());
    assertNull(cartFromDB);
  }

  @Test
  public void addPizzaToCartTest() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1, cartId);
    setupBacon();
    setUpSmallSize();
    Pizza smallPizza = setUpPizza(SMALL_SIZE, true);
    smallPizza.getToppingIDs().add(BACON);
    cartRepo.insert(cart);
    Pizza pizzaFromService = cartService.addPizzaToCart(cart, smallPizza);
    assertEquals(smallPizza, pizzaFromService);
  }

  @Test
  public void getPizzasPriceTest() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1, cartId);
    setupBacon();
    setUpSmallSize();
    Pizza smallPizza = setUpPizza(SMALL_SIZE, true);
    smallPizza.getToppingIDs().add(BACON);
    cart.getPizzas().add(smallPizza);
    cartRepo.insert(cart);
    Double price = cartService.getPizzasPrice(cart);
    assertEquals((Double) 12.49, price);
  }

  @Test
  public void getSidePriceTest() {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1, cartId);
    setUpWater();
    cart.getSides().add(WATER);
    cartRepo.insert(cart);
    assertEquals((Double) 1.49, cartService.getSidesPrice(cart));
  }

  @Test
  public void addSideToCartTest() {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1, cartId);
    SideItem water = setUpWater();
    cartRepo.insert(cart);
    SideItem side = cartService.addSideToCart(cart, WATER);
    assertEquals(water, side);
  }

  @Test
  public void deleteCartTest() {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1, cartId);
    cartRepo.insert(cart);
    try {
      cartService.deleteCart(cartId);
    } catch (Exception err) {
      fail(err.getMessage());
    }
  }

  @Test
  public void deleteSideFromCartTest() {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1, cartId);
    SideItem water = setUpWater();
    cart.getSides().add(WATER);
    cartRepo.insert(cart);

    cartService.deleteSideFromCart(cart, water);
    assertEquals((Double) 0.00, cartService.getSidesPrice(cart));
  }

  @Test
  public void deletePizzaFromCartTest() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1, cartId);
    setupBacon();
    setUpSmallSize();
    Pizza smallPizza = setUpPizza(SMALL_SIZE, true);
    smallPizza.getToppingIDs().add(BACON);

    cartRepo.insert(cart);
    assertFalse(cartService.deletePizzaFromCart(cart, smallPizza));
    assertEquals((Double) 0.00, cartService.getPizzasPrice(cart));
    cart.getPizzas().add(smallPizza);
    assertTrue(cartService.deletePizzaFromCart(cart, smallPizza));
    assertEquals((Double) 0.00, cartService.getPizzasPrice(cart));
  }
}
