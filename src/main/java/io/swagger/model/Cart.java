package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/** Cart class */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@ApiModel
@Document(collection = "Cart")
public class Cart {

  @Id
  @JsonProperty("CartID")
  private ObjectId id;

  @JsonProperty("ListOfPizzas")
  private List<Pizza> pizzas;

  @JsonProperty("ListOfSides")
  private List<String> sides;

  @JsonProperty("StoreID")
  private String storeID;

  @JsonProperty("TotalPrice")
  private Double totalAmount;

  @JsonProperty("SpecialApplied")
  private boolean specialApplied;

  /**
   * Create new Cart. By default, total price is 0.0, list of pizzas and sides are empty, and no
   * specials are applied.
   *
   * @param id cartID given to this new Cart
   * @param storeID storeID given to connect this Cart.
   */
  public Cart(String storeID, ObjectId id) {
    this.storeID = storeID;
    this.id = id;
    this.totalAmount = 0.0;
    this.pizzas = new ArrayList<>();
    this.sides = new ArrayList<>();
    this.specialApplied = false;
  }

  /**
   * Get cartID of this Cart.
   *
   * @return cartID
   */
  @ApiModelProperty(example = "5dd325ad5793784ecfe1b74c")
  public String getId() {
    return this.id.toHexString();
  }

  /**
   * Set cartID of this Cart.
   *
   * @param id new cartID given to this Cart
   */
  public void setId(ObjectId id) {
    this.id = id;
  }

  /**
   * Get storeID of this Cart.
   *
   * @return storeID
   */
  @ApiModelProperty(example = "brooklyn")
  public String getStoreID() {
    return this.storeID;
  }

  /**
   * Set storeID to this Cart.
   *
   * @param id new storeID given to this Cart
   */
  public void setStoreID(String id) {
    this.storeID = id;
  }

  /**
   * Get the totalAmount in this Cart.
   *
   * @return totalAmount in this Cart
   */
  @ApiModelProperty(example = "10.00")
  public Double getTotalAmount() {
    return this.totalAmount;
  }

  /**
   * Set the totalAmount in this Cart.
   *
   * @param totalAmount new totalAmount given to this Cart
   */
  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  /**
   * Get list of pizzas in this Cart.
   *
   * @return list of pizzas in this Cart
   */
  public List<Pizza> getPizzas() {
    return this.pizzas;
  }

  /**
   * Get list of sideID in this Cart.
   *
   * @return list of sideID in this Cart
   */
  @ApiModelProperty(allowableValues = "16OzCoke, 2LiterCoke")
  public List<String> getSides() {
    return this.sides;
  }

  /**
   * Check if special is already applied in this Cart.
   *
   * @return true if special is applied, false if there is no special in this cart
   */
  @ApiModelProperty(example = "false")
  public boolean isSpecialApplied() {
    return this.specialApplied;
  }

  /**
   * Set the specialApplied to this Cart.
   *
   * @param applied true if special is applied, false if special is not applied.
   */
  public void setSpecialApplied(boolean applied) {
    this.specialApplied = applied;
  }

  /**
   * String representation of this Cart.
   *
   * @return a String representation of this Cart
   */
  @Override
  public String toString() {
    return "Cart{"
        + "cartId='"
        + id
        + '\''
        + ", storeId='"
        + storeID
        + '\''
        + ", listOfPizza="
        + pizzas.toString()
        + ", listOfSide="
        + sides.toString()
        + ", totalPrice="
        + totalAmount
        + ", specialApplied="
        + specialApplied
        + '}';
  }

  /**
   * Check if two objects are equal.
   *
   * @param obj object given for comparison
   * @return true if two objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Cart cart = (Cart) obj;
    return cart.getSides().equals(this.sides)
        && cart.getPizzas().equals(this.pizzas)
        && cart.getId().equals(this.id.toString())
        && cart.getStoreID().equals(this.storeID)
        && Double.compare(cart.getTotalAmount(), this.totalAmount) == 0
        && cart.isSpecialApplied() == this.specialApplied;
  }
}
