package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.DBSideItems;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.repository.SideItemRepository;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/** StoreItem */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@ApiModel
@Document(collection = "SideItem")
public class SideItem {
  public static final String TYPE_APPETIZER = "appetizer";
  public static final String TYPE_CONDIMENT = "condiment";
  public static final String TYPE_DESSERT = "condiment";
  public static final String TYPE_DRINK = "drink";

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("price")
  private Double price;

  @JsonProperty("type")
  private String type;

  /**
   * Construct a SideItem with the given id, name, price, and type.
   *
   * @param id side's unique identifier
   * @param name name of side item
   * @param price price of side item
   * @param type the type of side item: drink, condiment, or appetizer
   */
  public SideItem(String id, String name, Double price, String type) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.type = type;
  }

  /**
   * Initialize SideItemRepository by adding the 21 sides to the database.
   *
   * @param sideItemRepository repository for SideItem storage
   */
  public static void initialize(SideItemRepository sideItemRepository) {
    if (sideItemRepository.count() == 0) {
      sideItemRepository.insert(DBSideItems.BREADSTICKS);
      sideItemRepository.insert(DBSideItems.CHEESE_STICKS);
      sideItemRepository.insert(DBSideItems.BARBECUE_WINGS);
      sideItemRepository.insert(DBSideItems.HOT_WINGS);
      sideItemRepository.insert(DBSideItems.UNSAUCED_WINGS);
      sideItemRepository.insert(DBSideItems.BARBECUE_SAUCE);
      sideItemRepository.insert(DBSideItems.HOT_SAUCE);
      sideItemRepository.insert(DBSideItems.MARINARA_SAUCE);
      sideItemRepository.insert(DBSideItems.RANCH_SAUCE);
      sideItemRepository.insert(DBSideItems.GRATED_CHEESE);
      sideItemRepository.insert(DBSideItems.RED_PEPPER_FLAKES);
      sideItemRepository.insert(DBSideItems.BROWNIE);
      sideItemRepository.insert(DBSideItems.CHOCOLATE_CHIP_COOKIE);
      sideItemRepository.insert(DBSideItems.BIG_COKE);
      sideItemRepository.insert(DBSideItems.BIG_PEACH_CRUSH);
      sideItemRepository.insert(DBSideItems.BIG_SPRITE);
      sideItemRepository.insert(DBSideItems.BIG_WATER);
      sideItemRepository.insert(DBSideItems.SMALL_COKE);
      sideItemRepository.insert(DBSideItems.SMALL_PEACH_CRUSH);
      sideItemRepository.insert(DBSideItems.SMALL_SPRITE);
      sideItemRepository.insert(DBSideItems.SMALL_WATER);
    }
  }

  /**
   * Get side's id.
   *
   * @return side's id.
   */
  @ApiModelProperty(example = "hotWings", required = true, value = "")
  @NotNull
  @Valid
  public String getId() {
    return id;
  }

  /**
   * Set side's id.
   *
   * @param id side's id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Get side's name.
   *
   * @return side's name.
   */
  @ApiModelProperty(example = "Hot wings", required = true, value = "")
  @NotNull
  @Valid
  public String getName() {
    return name;
  }

  /**
   * Set side's name.
   *
   * @param name side's name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get side's price.
   *
   * @return side's price.
   */
  @ApiModelProperty(example = "3.89", required = true, value = "")
  @NotNull
  @Valid
  public Double getPrice() {
    return price;
  }

  /**
   * Set side's price.
   *
   * @param price side's price
   */
  public void setPrice(Double price) {
    this.price = price;
  }

  /**
   * Get side's type.
   *
   * @return side's type.
   */
  @ApiModelProperty(example = "condiment", required = true, value = "")
  @NotNull
  @Valid
  public String getType() {
    return type;
  }

  /**
   * Set side's type.
   *
   * @param type side's type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param o - the reference object with which to compare
   * @return {@code true} if this object is the same as the o argument and {@code false}
   *     otherwise.
   */
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SideItem sideItem = (SideItem) o;
    return Objects.equals(this.id, sideItem.id)
        && Objects.equals(this.name, sideItem.name)
        && Objects.equals(this.price, sideItem.price)
        && Objects.equals(this.type, sideItem.type);
  }

  /**
   * Returns a hash code value for a SideItem.
   *
   * @return hash code value for a SideItem.
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, name, price, type);
  }

  /**
   * Returns a string representation of a SideItem. Format - SideItem{id='sideItemId',
   * name='sideItemName', price='sideItemPrice', type='sideItemPrice'}
   *
   * @return string representation of a SideItem.
   */
  @Override
  public String toString() {
    return "SideItem{"
        + "id='"
        + id
        + '\''
        + ", name='"
        + name
        + '\''
        + ", price='"
        + price
        + '\''
        + ", type='"
        + type
        + '\''
        + '}';
  }
}
