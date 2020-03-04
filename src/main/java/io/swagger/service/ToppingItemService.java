package io.swagger.service;

import io.swagger.model.ToppingItem;
import io.swagger.repository.ToppingItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToppingItemService {

  @Autowired public ToppingItemRepository toppingItemRepository;

  /**
   * Get all Topping Items
   *
   * @return List of Topping Item found in the database
   */
  public List<ToppingItem> getAllTopping() {
    return toppingItemRepository.findAll();
  }

  /**
   * Get ToppingItem by Id
   *
   * @param id id given to search
   * @return the toppingItem that matches with id.
   */
  public ToppingItem getToppingById(String id) {
    Optional<ToppingItem> topping = toppingItemRepository.findById(id);
    if (!topping.isPresent()) {
      return null;
    }
    return topping.get();
  }

  /**
   * Add topping to the database
   *
   * @return the toppingItem that is added
   */
  public ToppingItem addTopping(ToppingItem toppingItem) {
    toppingItemRepository.save(toppingItem);
    return toppingItem;
  }

  /**
   * Delete topping by id from database
   *
   * @param id id given to delete
   */
  public void deleteTopping(String id) {
    toppingItemRepository.deleteById(id);
  }
}
