package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/** Price Response */
public class PriceResponse {

  @JsonProperty("Success")
  private boolean success;

  @JsonProperty("Price")
  private Double price;

  @JsonProperty("Currency")
  private String currency;

  @JsonProperty("Message")
  private String message;

  /**
   * Construct a new PriceResponse Use this constructor for successful responses.
   *
   * @param price price that was found.
   * @param currency currency that was found.
   */
  public PriceResponse(Double price, String currency) {
    this.success = true;
    this.price = price;
    this.currency = currency;
    this.message = null;
  }

  /**
   * Construct a new PriceResponse Use this constructur for failing responses.
   *
   * @param message message given to PriceResponse.
   */
  public PriceResponse(String message) {
    this.success = false;
    this.price = null;
    this.currency = null;
    this.message = message;
  }

  /**
   * Get the price.
   *
   * @return Double price.
   */
  @ApiModelProperty(example = "10.00")
  public Double getPrice() {
    return price;
  }

  /**
   * Get the currency unit of this price.
   *
   * @return String if it is in US dollar, return "USD"
   */
  @ApiModelProperty(example = "USD")
  public String getCurrency() {
    return currency;
  }

  /**
   * Check if the PriceResponse was successful.
   *
   * @return true if price was found, false otherwise.
   */
  @ApiModelProperty(example = "true")
  public boolean isSuccess() {
    return success;
  }

  /**
   * Get message of this PriceResponse.
   *
   * @return message of this PriceResponse
   */
  public String getMessage() {
    return this.message;
  }
}
