package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/* Card */
public class Card {

  @JsonProperty("FirstName")
  private String firstName;

  @JsonProperty("LastName")
  private String lastName;

  @JsonProperty("CardNumber")
  private String cardNumber;

  @JsonProperty("ExpirationMonth")
  private Integer expMonth;

  @JsonProperty("ExpirationYear")
  private Integer expYear;


  /**
   * Create new Card
   *
   * @param firstName firstName of this Card holder
   * @param lastName lastName of this Card holder
   * @param cardNumber cardNumber of this Card
   * @param expMonth expiration month of this Card
   * @param expYear expiration year of this Card
   */
  public Card(
      String firstName,
      String lastName,
      String cardNumber,
      Integer expMonth,
      Integer expYear) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.cardNumber = cardNumber;
    this.expMonth = expMonth;
    this.expYear = expYear;
  }

  /**
   * Get firstName of this Card holder
   *
   * @return firstName of this Card holder
   */
  @ApiModelProperty(example = "YeJee")
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Get lastName of this Card holder
   *
   * @return lastName of this Card holder
   */
  @ApiModelProperty(example = "Lee")
  public String getLastName() {
    return this.lastName;
  }

  /**
   * Get the cardNumber of this Card
   *
   * @return cardNumber of this Card
   */
  @ApiModelProperty(example = "4400651415892598")
  public String getCardNumber() {
    return this.cardNumber;
  }

  /**
   * Set the cardNumber with this securedNum(last four digits)
   *
   * @param securedNum last 4 digits of the card
   */
  public void setCardNumber(String securedNum) {
    this.cardNumber = securedNum;
  }

  /**
   * Get the expiration Month of this Card
   *
   * @return expiration month of this Card
   */
  @ApiModelProperty(example = "05")
  public Integer getExpMonth() {
    return this.expMonth;
  }

  /**
   * Get the expiration Year of this Card
   *
   * @return expiration Year of this Card
   */
  @ApiModelProperty(example = "2019")
  public Integer getExpYear() {
    return this.expYear;
  }


  /**
   * String representation of this Card.
   *
   * @return string representation of this Card
   */
  @Override
  public String toString() {
    return "Card{Name="
        + this.firstName
        + " "
        + this.lastName
        + ", "
        + "CardNumber="
        + this.cardNumber
        + ", "
        + "ExpirationDate="
        + this.expMonth
        + "/"
        + this.expYear
        + "}";
  }

  /**
   * Check if the two objects are equal
   *
   * @return true if the two objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != getClass()) {
      return false;
    }
    Card card = (Card) obj;
    return this.firstName == card.getFirstName()
        && this.lastName == card.getLastName()
        && this.cardNumber == card.getCardNumber()
        && this.expMonth.equals(card.getExpMonth())
        && this.expYear.equals(card.getExpYear());
  }
}
