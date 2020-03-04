package io.swagger.service;

import io.swagger.model.PizzaSize;
import io.swagger.repository.PizzaSizeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaSizeService {

  @Autowired private PizzaSizeRepository pizzaSizeRepository;

  /**
   * Find all pizza sizes from database
   *
   * @return list of pizza sizes
   */
  public List<PizzaSize> getAllPizzaSizes() {
    List<PizzaSize> sizes = pizzaSizeRepository.findAll();
    return sizes;
  }

  /**
   * Find a pizza size using id
   *
   * @param id sizeId given to search
   * @return a pizza size found by id
   */
  public PizzaSize getPizzaSizeById(String id) {
    Optional<PizzaSize> size = pizzaSizeRepository.findById(id);
    if (!size.isPresent()) {
      return null;
    }
    return size.get();
  }

  /**
   * Add PizzaSize to the database
   *
   * @param pizzaSize pizzaSize given to add
   * @return the PizzaSize that is added
   */
  public PizzaSize addPizzaSize(PizzaSize pizzaSize) {
    pizzaSizeRepository.save(pizzaSize);
    return pizzaSize;
  }

  /**
   * Delete PizzaSize by id from database
   *
   * @param id id given to delete
   */
  public void deletePizzaSize(String id) {
    pizzaSizeRepository.deleteById(id);
  }
}
