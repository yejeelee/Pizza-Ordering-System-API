package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.DBSpecialItems;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.repository.SpecialItemRepository;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/** SpecialItem */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@ApiModel
@Document(collection = "SpecialItem")
public class SpecialItem {

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  /**
   * Create new SpecialItem
   *
   * @param id id given to this SpecialItem
   * @param name name given to this SpecialItem
   * @param description description given to this SpecialItem
   */
  public SpecialItem(String id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  /**
   * Initialize SpecialItemRepository by adding the 3 specials to the database.
   *
   * @param specialItemRepository repository for SpecialItem storage
   */
  public static void initialize(SpecialItemRepository specialItemRepository) {
    if (specialItemRepository.count() == 0) {
      specialItemRepository.insert(DBSpecialItems.BUY_1_GET_1_FREE);
      specialItemRepository.insert(DBSpecialItems.BUY_1_PIZZA_GET_SODA_FREE);
      specialItemRepository.insert(DBSpecialItems.BUY_2_LARGE_PIZZA_NO_TOPPING);
    }
  }

  /**
   * Get id
   *
   * @return id
   */
  public String getId() {
    return id;
  }

  /**
   * Set id
   *
   * @param id new id given for this item
   */
  @ApiModelProperty(example = "buy1Get1Free")
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Get name
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Set name
   *
   * @param name new name given for this special item
   */
  @ApiModelProperty(example = "Buy1Get1Free")
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get description
   *
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Set description
   *
   * @param description new description for Special item
   */
  @ApiModelProperty(
      example =
          "Only one special at a time. If you buy 1 pizza,"
              + " you get 1 free pizza that is equal or less value.")
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Spring representation of a Special Item
   *
   * @return string representation of a special item
   */
  @Override
  public String toString() {
    return "SpecialItem{" + "id=" + id + ", name=" + name + ", description=" + description + "}";
  }

  /**
   * Check if two objects are equal
   *
   * @param obj object given to compare
   * @return true if two objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    SpecialItem specialItem = (SpecialItem) obj;
    return specialItem.getId().equals(this.id)
        && specialItem.getName().equals(this.name)
        && specialItem.getDescription().equals(this.description);
  }
}
