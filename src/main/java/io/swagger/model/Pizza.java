package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/** Pizza class */
public class Pizza {

  // Pizzas may have up to this many toppings
  public static final int MAXIMUM_TOPPING_COUNT = 4;

  @JsonProperty("PizzaSizeID")
  private String sizeID;

  @JsonProperty("gluten")
  private boolean gluten;

  @JsonProperty("toppingIDs")
  private List<String> toppingIDs;

  /**
   * Create new Pizza
   *
   * @param sizeID pizzaSizeID given to this Pizza
   * @param gluten true if it is gluten, false if it is glutenFree
   */
  public Pizza(String sizeID, boolean gluten) {
    this.sizeID = sizeID;
    this.toppingIDs = new ArrayList<>();
    this.gluten = gluten;
  }

  /**
   * Get pizzaSizeID
   *
   * @return PizzaSizeID
   */
  @ApiModelProperty(example = "small")
  public String getSizeID() {
    return this.sizeID;
  }

  /**
   * Check if this pizza is gluten
   *
   * @return true if it is gluten, false if it is a gluten free pizza
   */
  @ApiModelProperty(example = "true")
  public boolean isGluten() {
    return this.gluten;
  }

  /**
   * Get all the toppingIDs in this Pizza
   *
   * @return list of ToppingIDs in this Pizza
   */
  @ApiModelProperty(allowableValues = "pepperoni")
  public List<String> getToppingIDs() {
    return this.toppingIDs;
  }

  /**
   * String representation of this Pizza.
   *
   * @return a String representation of this Pizza
   */
  @Override
  public String toString() {
    return "Pizza{"
        + "sizeID="
        + this.sizeID
        + ", gluten="
        + this.gluten
        + ", toppingIDs="
        + this.toppingIDs
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
    if (obj == this) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Pizza pizza = (Pizza) obj;
    return this.sizeID.equals(pizza.getSizeID())
        && this.gluten == pizza.gluten
        && this.toppingIDs.equals(pizza.getToppingIDs());
  }
}
