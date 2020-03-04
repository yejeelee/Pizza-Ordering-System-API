package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Pizza Suggestion Response */
public class PizzaSuggestionResponse {

  @JsonProperty("large")
  private Integer large;

  @JsonProperty("medium")
  private Integer medium;

  @JsonProperty("small")
  private Integer small;

  /**
   * Construct a new PizzaSuggestionResponse.
   *
   * @param small the number of small pizzas suggested.
   * @param medium the number of medium pizzas suggested.
   * @param large the number of large pizzas suggested.
   */
  public PizzaSuggestionResponse(Integer small, Integer medium, Integer large) {
    this.large = large;
    this.medium = medium;
    this.small = small;
  }

  /** @return the number of small pizzas suggested. */
  public Integer getSmall() {
    return this.small;
  }

  /** @return the number of medium pizzas suggested. */
  public Integer getMedium() {
    return this.medium;
  }

  /** @return the number of large pizzas suggested. */
  public Integer getLarge() {
    return this.large;
  }
}
