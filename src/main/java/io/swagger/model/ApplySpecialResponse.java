package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/** Apply Special Response Model */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@ApiModel
public class ApplySpecialResponse {
  @JsonProperty("specialId")
  private String specialId;

  @JsonProperty("success")
  private boolean success = false;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("savings")
  private Double savings = 0.00;

  /**
   * Construct an ApplySpecialResponse with the given specialId and savings.
   *
   * @param specialId identifier of the special being applied
   * @param savings amount saved after special has been applied
   */
  public ApplySpecialResponse(String specialId, Double savings) {
    this.specialId = specialId;
    this.savings = savings;
    this.success = true;
  }

  /**
   * Construct a failing ApplySpecialResponse.
   *
   * @param message the error message explaining the failure
   */
  public ApplySpecialResponse(String message) {
    this.message = message;
    this.success = false;
  }

  /**
   * Get special's id.
   *
   * @return special's id.
   */
  @ApiModelProperty(example = "buy1PizzaGetSodaFree", required = true, value = "")
  @NotNull
  @Valid
  public String getSpecialId() {
    return specialId;
  }

  /**
   * Set special's id.
   *
   * @param specialId special's id
   */
  public void setSpecialId(String specialId) {
    this.specialId = specialId;
  }

  /**
   * Get success of applying special.
   *
   * @return {@code true} if applying special successful and {@code false} otherwise.
   */
  @ApiModelProperty(example = "false", required = true, value = "")
  @NotNull
  @Valid
  public boolean getSuccess() {
    return success;
  }

  /**
   * Set success of applying special.
   *
   * @param success success of applying special
   */
  public void setSuccess(boolean success) {
    this.success = success;
  }

  /**
   * Get message regarding applying special.
   *
   * @return message regarding applying special: {@code null} if successful application and an error
   *     message otherwise.
   */
  @ApiModelProperty(example = "ERROR_ONLY_ONE_SPECIAL_PER_CART", required = false, value = "")
  @Valid
  public String getMessage() {
    return message;
  }

  /**
   * Set message regarding applying special.
   *
   * @param message message regarding applying special: {@code null} if successful application and
   *     an error message otherwise
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Get savings received from special.
   *
   * @return savings received from special.
   */
  @ApiModelProperty(example = "7.77", required = true, value = "")
  @NotNull
  @Valid
  public Double getSavings() {
    return savings;
  }

  /**
   * Set savings received from special.
   *
   * @param savings savings received from special
   */
  public void setSavings(Double savings) {
    this.savings = savings;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param o - the reference object with which to compare
   * @return {@code true} if this object is the same as the o argument and {@code false}
   *     otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplySpecialResponse applySpecialResponse = (ApplySpecialResponse) o;
    return Objects.equals(this.specialId, applySpecialResponse.specialId)
        && Objects.equals(this.success, applySpecialResponse.success)
        && Objects.equals(this.message, applySpecialResponse.message)
        && Objects.equals(this.savings, applySpecialResponse.savings);
  }

  /**
   * Returns a hash code value for an ApplySpecial.
   *
   * @return hash code value for an ApplySpecial.
   */
  @Override
  public int hashCode() {
    return Objects.hash(specialId, success, message, savings);
  }

  /**
   * Returns a string representation of an ApplySpecial. Format -
   * ApplySpecialResponse{specialId='specialId', success='applySpecialResponseSuccess',
   * message='applySpecialResponseMessage', savings='x.xx'}
   *
   * @return string representation of a ApplySpecial.
   */
  @Override
  public String toString() {
    return "ApplySpecialResponse{"
        + "specialId='"
        + specialId
        + '\''
        + ", success='"
        + success
        + '\''
        + ", message='"
        + message
        + '\''
        + ", savings='"
        + savings
        + '\''
        + '}';
  }
}
