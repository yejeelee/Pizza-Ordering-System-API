package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/** Cart Add Response */
public class CartAddResponse {

  @JsonProperty("Success")
  private boolean success;

  @JsonProperty("Pizza")
  private Pizza pizza;

  @JsonProperty("Side")
  private SideItem side;

  @JsonProperty("CartID")
  private String cartID;

  @JsonProperty("StoreID")
  private String storeID;

  @JsonProperty("Message")
  private String message;

  /**
   * Construct a new CartAddResponse for pizza.
   *
   * @param pizza pizza added to Cart.
   * @param cartID cartId to show where it was saved.
   * @param storeID storeId to show where it was saved.
   */
  public CartAddResponse(Pizza pizza, String cartID, String storeID) {
    this.success = true;
    this.cartID = cartID;
    this.storeID = storeID;
    this.pizza = pizza;
    this.side = null;
    this.message = null;
  }

  /**
   * Construct a new CartAddResponse for side.
   *
   * @param side side added to Cart.
   * @param cartID cartId to show where it was saved.
   * @param storeID storeId to show where it was saved.
   */
  public CartAddResponse(SideItem side, String cartID, String storeID) {
    this.success = true;
    this.cartID = cartID;
    this.storeID = storeID;
    this.pizza = null;
    this.side = side;
    this.message = null;
  }

  /**
   * Construct a failed CartAddResponse.
   *
   * @param message the error message to show the user
   */
  public CartAddResponse(String message) {
    this.success = false;
    this.pizza = null;
    this.side = null;
    this.cartID = null;
    this.storeID = null;
    this.message = message;
  }

  /**
   * Check if the CartAddResponse was successful.
   *
   * @return true if it was successful, false otherwise.
   */
  @ApiModelProperty(example = "true")
  public boolean getSuccess() {
    return this.success;
  }

  /**
   * Get pizza of this CartAddResponse.
   *
   * @return pizza of this CartAddResponse.
   */
  public Pizza getPizza() {
    return this.pizza;
  }

  /**
   * Get side of this CartAddResponse.
   *
   * @return side of this CartAddResponse.
   */
  public SideItem getSide() {
    return this.side;
  }

  /**
   * Get CartId of this CartAddResponse.
   *
   * @return CartId the destination where items are saved.
   */
  @ApiModelProperty(example = "5dd325ad5793784ecfe1b74c")
  public String getCartID() {
    return this.cartID;
  }

  /**
   * Get StoreId of this CartAddResponse.
   *
   * @return storeId the destination where items are saved.
   */
  @ApiModelProperty(example = "eastlake")
  public String getStoreID() {
    return this.storeID;
  }

  /**
   * Get the message of this CartAddResponse.
   *
   * @return message to show if it was successful or failed.
   */
  public String getMessage() {
    return this.message;
  }

  /**
   * String representation of this CartAddResponse.
   *
   * @return a String representation of this CartAddResponse
   */
  @Override
  public String toString() {
    return "CartAddResponse{"
        + "success="
        + this.success
        + ", cartId="
        + this.cartID
        + ", storeID="
        + this.storeID
        + ", pizza="
        + this.pizza
        + ", side="
        + this.side
        + ", message="
        + this.message
        + "}";
  }

  /**
   * Check if two objects are equal.
   *
   * @param obj object given for comparison
   * @return true if two objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    CartAddResponse object = (CartAddResponse) obj;
    return this.success == object.getSuccess()
        && ((this.cartID == null && object.getCartID() == null)
            || this.cartID.equals(object.getCartID()))
        && ((this.pizza == null && object.getPizza() == null)
            || this.pizza.equals(object.getPizza()))
        && ((this.side == null && object.getSide() == null) || this.side.equals(object.getSide()))
        && ((this.storeID == null && object.getStoreID() == null)
            || this.storeID.equals(object.getStoreID()))
        && ((this.message == null && object.getMessage() == null)
            || this.message.equals(object.getMessage()));
  }
}
