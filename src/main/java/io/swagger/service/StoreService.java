package io.swagger.service;

import io.swagger.model.StoreItem;
import io.swagger.repository.StoreItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Service for the Store API */
@Service
public class StoreService {

  @Autowired public StoreItemRepository storeItemRepository;

  /**
   * Get all stores.
   *
   * @return a list of all StoreItems.
   */
  public List<StoreItem> getAllStores() {
    return storeItemRepository.findAll();
  }

  /**
   * Get a specific StoreItem by id
   *
   * @param id id of requested StoreItem
   * @return specified StoreItem
   */
  public Optional<StoreItem> getStoreById(String id) {
    Optional<StoreItem> store = storeItemRepository.findById(id);
    if (!store.isPresent()) {
      return null;
    }
    return store;
  }

  /**
   * Add a StoreItem.
   *
   * @param newStore new StoreItem to add
   * @return StoreItem that was added.
   */
  public StoreItem addStore(StoreItem newStore) {
    if (storeItemRepository.findById(newStore.getId()).isPresent()) {
      return null;
    } else {
      return storeItemRepository.save(newStore);
    }
  }

  /**
   * Delete a StoreItem by id.
   *
   * @param id id of StoreItem to delete
   */
  public void deleteStore(String id) {
    storeItemRepository.deleteById(id);
  }

  /**
   * Return whether the store with the given id offers gluten free.
   *
   * @param id id of the store
   * @return {@code true} if the store offers gluten free and {@code false} otherwise
   */
  public boolean storeOffersGlutenFree(String id) {
    return storeOffersGlutenFree(storeItemRepository.findById(id).get());
  }

  /**
   * Return whether the given store offers gluten free.
   *
   * @param storeItem the store
   * @return {@code true} if the store offers gluten free and {@code false} otherwise
   */
  public boolean storeOffersGlutenFree(StoreItem storeItem) {
    return storeItem.getOffersGlutenFree();
  }
}
