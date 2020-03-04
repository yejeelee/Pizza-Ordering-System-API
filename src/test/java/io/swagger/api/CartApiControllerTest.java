package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import io.swagger.DBPizzaSizes;
import io.swagger.DBSideItems;
import io.swagger.DBStoreItems;
import io.swagger.DBToppingItems;
import io.swagger.model.Cart;
import io.swagger.model.CartAddResponse;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.PriceResponse;
import io.swagger.model.SideItem;
import io.swagger.model.StoreItem;
import io.swagger.model.ToppingItem;
import io.swagger.repository.CartRepository;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.repository.SideItemRepository;
import io.swagger.repository.StoreItemRepository;
import io.swagger.repository.ToppingItemRepository;
import io.swagger.service.CartService;
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
public class CartApiControllerTest {

  @Autowired
  private CartApi cartApi;
  @Autowired
  private CartRepository cartRepo;
  @Autowired
  private SideItemRepository sideRepo;
  @Autowired
  private PizzaSizeRepository sizeRepo;
  @Autowired
  private ToppingItemRepository toppingRepo;
  @Autowired
  private StoreItemRepository storeRepo;
  @Autowired
  private CartService cartService;

  @Before
  public void setUp() {
    cartRepo.deleteAll();
    storeRepo.deleteAll();
    toppingRepo.deleteAll();
    sizeRepo.deleteAll();
    sideRepo.deleteAll();
  }

  StoreItem TEST_STORE_1 = DBStoreItems.EASTLAKE_STORE;
  StoreItem TEST_STORE_2 = DBStoreItems.BROOKLYN_STORE;
  StoreItem TEST_STORE_3 = DBStoreItems.STONE_WAY_STORE;
  String PEPPERONI = "pepperoni";
  String ONION = "onion";
  String SMALL_SIZE = "small";
  String MEDIUM_SIZE = "medium";
  String BROWNIE = "brownie";

  private SideItem setUpBrownie() {
    SideItem brownie = DBSideItems.BROWNIE;
    SideItem side = new SideItem(brownie.getId(), brownie.getName(), brownie.getPrice(),
        brownie.getType());
    sideRepo.insert(side);
    return side;
  }

  private PizzaSize setUpSmallSize() {
    PizzaSize smallSize = DBPizzaSizes.SMALL;
    PizzaSize pizzaSize = new PizzaSize(smallSize.getId(), smallSize.getSizeName(),
        smallSize.getSizeInch(), smallSize.getPrice());
    sizeRepo.insert(pizzaSize);
    return pizzaSize;
  }

  private PizzaSize setUpMediumSize() {
    PizzaSize mediumSize = DBPizzaSizes.MEDIUM;
    PizzaSize pizzaSize = new PizzaSize(mediumSize.getId(), mediumSize.getSizeName(),
        mediumSize.getSizeInch(), mediumSize.getPrice());
    sizeRepo.insert(pizzaSize);
    return pizzaSize;
  }

  private ToppingItem setupPepperoni() {
    ToppingItem pepperoni = DBToppingItems.PEPPERONI;
    ToppingItem pepperoniTopping = new ToppingItem(pepperoni.getId(), pepperoni.getToppingName(),
        pepperoni.getToppingType(), pepperoni.getToppingSmallPrice(),
        pepperoni.getToppingMediumPrice(), pepperoni.getToppingLargePrice(),
        pepperoni.getToppingGluten());
    toppingRepo.insert(pepperoniTopping);
    return pepperoniTopping;
  }

  private ToppingItem setupOnion() {
    ToppingItem onion = DBToppingItems.ONION;
    ToppingItem onionTopping = new ToppingItem(onion.getId(), onion.getToppingName(),
        onion.getToppingType(), onion.getToppingSmallPrice(), onion.getToppingMediumPrice(),
        onion.getToppingLargePrice(), onion.getToppingGluten());
    toppingRepo.insert(onionTopping);
    return onionTopping;
  }

  private Pizza setUpPizza(String size, boolean gluten) {
    Pizza pizza = new Pizza(size, gluten);
    return pizza;
  }

  private Cart setUpCart1() {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1.getId(), cartId);
    cartRepo.insert(cart);
    return cart;
  }

  @Test
  public void testGetCartById() {
    Cart cart = setUpCart1();
    Cart cartFromDB = cartService.getCartItemsById(TEST_STORE_1.getId(), cart.getId());
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
    assertEquals(cart1, cartService.getOrCreateCart(TEST_STORE_1.getId(), cart1.getId()));
    Cart cart2 = cartService.createCart(TEST_STORE_1.getId());
    assertEquals(cart2, cartService.getOrCreateCart(TEST_STORE_1.getId(), cart2.getId()));
    Cart cart3 = cartService.getOrCreateCart(TEST_STORE_1.getId(), "NoCartId");
    assertEquals(cart3, cartService.getOrCreateCart(TEST_STORE_1.getId(), cart3.getId()));

  }

  @Test
  public void testGetCartByIdChecksStore() {
    Cart cart = setUpCart1();
    final ResponseEntity<Cart> response = cartApi
        .getCartItemsById(TEST_STORE_1.getId(), cart.getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());

    //Getting a cart from a different store should return null
    Cart cartFromDB = cartService.getCartItemsById(TEST_STORE_2.getId(), cart.getId());
    assertNull(cartFromDB);
    final ResponseEntity<Cart> response2 = cartApi
        .getCartItemsById(TEST_STORE_2.getId(), cart.getId());
    assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
  }

  @Test
  public void addPizzaToCartTest() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1.getId(), cartId);
    setupPepperoni();
    setUpSmallSize();
    Pizza smallPizza = setUpPizza(SMALL_SIZE, true);
    smallPizza.getToppingIDs().add(PEPPERONI);
    cart.getPizzas().add(smallPizza);
    cartRepo.insert(cart);
    Pizza pizzaFromService = cartService.addPizzaToCart(cart, smallPizza);
    assertEquals(smallPizza, pizzaFromService);
    setUpMediumSize();
    Pizza mediumPizza = setUpPizza(MEDIUM_SIZE, false);

    final ResponseEntity<CartAddResponse> testInvalidStoreIdResponse = cartApi
        .addPizzaToCart(TEST_STORE_2.getId(), cart.getId(), mediumPizza);
    assertEquals(HttpStatus.NOT_FOUND, testInvalidStoreIdResponse.getStatusCode());

    final ResponseEntity<CartAddResponse> testInvalidPizzaSize = cartApi
        .addPizzaToCart(cart.getStoreID(), cart.getId(), mediumPizza);
    assertEquals(HttpStatus.NOT_FOUND, testInvalidPizzaSize.getStatusCode());


  }

  @Test
  public void testAddPizzaToCartTooManyToppings() throws Exception {
    ObjectId cartId2 = new ObjectId();
    Cart cart2 = new Cart(TEST_STORE_2.getId(), cartId2);
    setupOnion();
    setUpMediumSize();
    Pizza mediumPizza = setUpPizza(MEDIUM_SIZE, false);
    mediumPizza.getToppingIDs().add(ONION);
    mediumPizza.getToppingIDs().add(ONION);
    mediumPizza.getToppingIDs().add(ONION);
    mediumPizza.getToppingIDs().add(ONION);
    mediumPizza.getToppingIDs().add(ONION);
    cartRepo.insert(cart2);

    Pizza pizzaFromService2 = cartService.addPizzaToCart(cart2, mediumPizza);
    assertEquals(mediumPizza, pizzaFromService2);

    final ResponseEntity<CartAddResponse> testTooManyToppings = cartApi
        .addPizzaToCart(cart2.getStoreID(), cart2.getId(), mediumPizza);
    assertEquals(HttpStatus.BAD_REQUEST, testTooManyToppings.getStatusCode());

  }

  @Test
  public void testAddPizzaToCartGluten() throws Exception {
    ObjectId cartId2 = new ObjectId();
    Cart cart2 = new Cart(TEST_STORE_3.getId(), cartId2);
    setupOnion();
    setUpMediumSize();
    Pizza mediumPizza = setUpPizza(MEDIUM_SIZE, false);
    mediumPizza.getToppingIDs().add(ONION);
    cart2.getPizzas().add(mediumPizza);
    cartRepo.insert(cart2);

    Pizza pizzaFromService2 = cartService.addPizzaToCart(cart2, mediumPizza);
    assertEquals(mediumPizza, pizzaFromService2);

    final ResponseEntity<CartAddResponse> testGlutenResponse = cartApi
        .addPizzaToCart(DBStoreItems.BROOKLYN_STORE.getId(), cart2.getId(), mediumPizza);
    assertEquals(HttpStatus.NOT_FOUND, testGlutenResponse.getStatusCode());

  }


  @Test
  public void getPizzasPriceTest() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1.getId(), cartId);
    setupPepperoni();
    setUpSmallSize();
    Pizza smallPizza = setUpPizza(SMALL_SIZE, true);
    smallPizza.getToppingIDs().add(PEPPERONI);
    cart.getPizzas().add(smallPizza);
    cartRepo.insert(cart);
    Double price = cartService.getPizzasPrice(cart);
    assertEquals((Double) 16.49, price);

    final ResponseEntity<PriceResponse> cartPrice = cartApi
        .getPriceOfCartById(TEST_STORE_1.getId(), cart.getId());
    assertEquals(HttpStatus.OK, cartPrice.getStatusCode());

    final ResponseEntity<PriceResponse> cartPrice2 = cartApi
        .getPriceOfCartById(TEST_STORE_2.getId(), cart.getId());
    assertEquals(HttpStatus.NOT_FOUND, cartPrice2.getStatusCode());
  }

  @Test
  public void getSidePriceTest() {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1.getId(), cartId);
    setUpBrownie();
    cart.getSides().add(BROWNIE);
    cartRepo.insert(cart);
    assertEquals((Double) 2.49, cartService.getSidesPrice(cart));

    final ResponseEntity<PriceResponse> cartPrice = cartApi
        .getPriceOfCartById(TEST_STORE_1.getId(), cart.getId());
    assertEquals(HttpStatus.OK, cartPrice.getStatusCode());

    final ResponseEntity<PriceResponse> cartPrice2 = cartApi
        .getPriceOfCartById(TEST_STORE_2.getId(), cart.getId());
    assertEquals(HttpStatus.NOT_FOUND, cartPrice2.getStatusCode());
  }

  @Test
  public void addSideToCartTest() {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1.getId(), cartId);
    SideItem brownie = setUpBrownie();
    cartRepo.insert(cart);
    SideItem side = cartService.addSideToCart(cart, BROWNIE);
    assertEquals(brownie, side);

    final ResponseEntity<CartAddResponse> responseStoreNotFound = cartApi
        .addSideToCart(TEST_STORE_2.getId(), cart.getId(), DBSideItems.BROWNIE.getId());
    assertEquals(HttpStatus.NOT_FOUND, responseStoreNotFound.getStatusCode());

    final ResponseEntity<CartAddResponse> responseSideIdNotFound = cartApi
        .addSideToCart(TEST_STORE_1.getId(), cart.getId(), DBSideItems.BREADSTICKS.getId());
    assertEquals(HttpStatus.NOT_FOUND, responseSideIdNotFound.getStatusCode());
  }

  @Test
  public void deleteCartTest() {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1.getId(), cartId);
    cartRepo.insert(cart);

    final HttpStatus deleteCartResponse = cartApi.deleteCart(cart.getStoreID(), cart.getId());
    assertEquals(HttpStatus.NO_CONTENT, deleteCartResponse);

    final HttpStatus deleteCartResponse2 = cartApi.deleteCart(TEST_STORE_2.getId(), cart.getId());
    assertEquals(HttpStatus.NOT_FOUND, deleteCartResponse2);
  }

  @Test
  public void deleteSideFromCartTest() {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1.getId(), cartId);
    SideItem brownie = setUpBrownie();
    cart.getSides().add(BROWNIE);
    cartRepo.insert(cart);

    final HttpStatus responseNoContent = cartApi
        .deleteSideFromCart(cart.getStoreID(), cart.getId(), brownie.getId());
    assertEquals(HttpStatus.NO_CONTENT, responseNoContent);

    cartService.deleteSideFromCart(cart, brownie);
    assertEquals((Double) 0.00, cartService.getSidesPrice(cart));

    final HttpStatus responseBadRequest = cartApi
        .deleteSideFromCart(cart.getStoreID(), cart.getId(), brownie.getId());
    assertEquals(HttpStatus.BAD_REQUEST, responseBadRequest);

    final HttpStatus responseNotFound = cartApi
        .deleteSideFromCart(TEST_STORE_2.getId(), cart.getId(), brownie.getId());
    assertEquals(HttpStatus.NOT_FOUND, responseNotFound);
  }

  @Test
  public void deletePizzaFromCartTest() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(TEST_STORE_1.getId(), cartId);
    setupPepperoni();
    setUpSmallSize();
    Pizza smallPizza = setUpPizza(SMALL_SIZE, true);
    smallPizza.getToppingIDs().add(PEPPERONI);
    cart.getPizzas().add(smallPizza);
    cartRepo.insert(cart);

    final HttpStatus deletePizzaResponse1 = cartApi
        .deletePizzaFromCart(cart.getStoreID(), cart.getId(), smallPizza);
    assertEquals(HttpStatus.NO_CONTENT, deletePizzaResponse1);

    final HttpStatus deletePizzaResponse = cartApi
        .deletePizzaFromCart(TEST_STORE_2.getId(), cart.getId(), smallPizza);
    assertEquals(HttpStatus.NOT_FOUND, deletePizzaResponse);

    ObjectId cartId2 = new ObjectId();
    Cart cart2 = new Cart(DBStoreItems.STONE_WAY_STORE.getId(), cartId2);
    setUpMediumSize();
    Pizza mediumPizza = setUpPizza(MEDIUM_SIZE, false);
    cart2.getPizzas().add(mediumPizza);
    cartRepo.insert(cart2);

    final HttpStatus deletePizzaResponse2 = cartApi
        .deletePizzaFromCart(cart2.getStoreID(), cart2.getId(), smallPizza);
    assertEquals(HttpStatus.BAD_REQUEST, deletePizzaResponse2);
  }

}

