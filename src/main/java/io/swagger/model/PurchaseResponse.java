package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/** Purchase Response */
public class PurchaseResponse {

  @JsonProperty("Success")
  private boolean success;

  @JsonProperty("Receipt")
  private Receipt receipt;

  @JsonProperty("Message")
  private String message;

  /**
   * Construct a successful PurchaseResponse.
   *
   * @param receipt receipt to show when user purchase
   */
  public PurchaseResponse(Receipt receipt) {
    this.success = true;
    this.receipt = receipt;
    this.message = null;
  }

  /**
   * Construct a failed PurchaseResponse.
   *
   * @param message the error message to show the user
   */
  public PurchaseResponse(String message) {
    this.success = false;
    this.receipt = null;
    this.message = message;
  }

  /**
   * Check if it is successful or failed
   *
   * @return true if PurchaseResponse created successfully, false otherwise.
   */
  @ApiModelProperty(example = "true")
  public boolean isSuccess() {
    return this.success;
  }

  /**
   * Get receipt of this PurchaseResponse
   *
   * @return receipt of this PurchaseResonse
   */
  public Receipt getReceipt() {
    return this.receipt;
  }

  /**
   * Get the message of this PurchaseResponse
   *
   * @return message of this PurchaseResponse
   */
  @ApiModelProperty(example = "null")
  public String getMessage() {
    return this.message;
  }
}
