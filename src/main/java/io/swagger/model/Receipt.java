package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.GregorianCalendar;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/* Receipt */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@ApiModel
@Document(collection = "Receipt")
public class Receipt {
  @Id
  @JsonProperty("ReceiptId")
  private ObjectId receiptId;

  @JsonProperty("TimePlaced")
  private GregorianCalendar timePlaced;

  @JsonProperty("Cart")
  private Cart cart;

  @JsonProperty("Card")
  private Card card;

  /**
   * Create new Receipt
   *
   * @param cart cart given to this Receipt
   * @param card card given for payment of this Receipt
   */
  public Receipt(Cart cart, Card card) {
    this.receiptId = new ObjectId();
    this.timePlaced = new GregorianCalendar();
    this.cart = cart;
    this.card = card;
  }

  /**
   * Get the ReceiptId of this Receipt
   *
   * @return receiptId of this Receipt
   */
  @ApiModelProperty(example = "5dde3ac83157466b630de3a9")
  public String getReceiptId() {
    return this.receiptId.toString();
  }

  /**
   * Get the timePlaced of this Receipt
   *
   * @return the time of this Receipt created
   */
  public GregorianCalendar getTimePlaced() {
    return this.timePlaced;
  }

  /**
   * Get the cart of this Receipt
   *
   * @return the cart of this Receipt
   */
  public Cart getCart() {
    return this.cart;
  }

  /**
   * Get the card paid in this Receipt
   *
   * @return get the card paid in this Receipt
   */
  public Card getCard() {
    return this.card;
  }

  /**
   * String representation of this Receipt
   *
   * @return string representation of this Receipt
   */
  @Override
  public String toString() {
    return "Receipt{id="
        + this.receiptId.toString()
        + ", "
        + "Time placed="
        + this.timePlaced
        + ", "
        + "StoreId="
        + this.cart.getStoreID()
        + ", "
        + "Pizzas="
        + this.cart.getPizzas()
        + ", "
        + "Sides="
        + this.cart.getSides()
        + ", "
        + "Total Amount="
        + this.cart.getTotalAmount()
        + ", "
        + "Card=card number ending with "
        + this.card.getCardNumber().substring(this.card.getCardNumber().length() - 4)
        + "}";
  }

  /**
   * Check if two objects are equal
   *
   * @return true if two objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != getClass()) {
      return false;
    }
    Receipt receipt = (Receipt) obj;
    return receipt.getReceiptId().toString() == this.receiptId.toString()
        && receipt.getTimePlaced().compareTo(this.timePlaced) == 0
        && receipt.getCard() == this.card
        && receipt.getCart() == this.cart;
  }
}
