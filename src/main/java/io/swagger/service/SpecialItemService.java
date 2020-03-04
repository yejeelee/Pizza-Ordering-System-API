package io.swagger.service;

import io.swagger.model.SpecialItem;
import io.swagger.repository.SpecialItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialItemService {

  @Autowired public SpecialItemRepository specialItemRepository;

  /**
   * Get all Special Items
   *
   * @return List of Special Items found in the database
   */
  public List<SpecialItem> getAllSpecials() {
    return specialItemRepository.findAll();
  }

  /**
   * Get SpecialItem by Id
   *
   * @param id id given to search
   * @return the specialItem that matches with id.
   */
  public SpecialItem getSpecialById(String id) {
    Optional<SpecialItem> special = specialItemRepository.findById(id);
    if (!special.isPresent()) {
      return null;
    }
    return special.get();
  }

  /**
   * Add special to the database
   *
   * @param specialItem specialItem given to add.
   * @return the specialItem that is added
   */
  public SpecialItem addSpecial(SpecialItem specialItem) {
    return specialItemRepository.save(specialItem);
  }

  /**
   * Delete special by id from database
   *
   * @param id id given to delete
   */
  public void deleteSpecial(String id) {
    specialItemRepository.deleteById(id);
  }
}
