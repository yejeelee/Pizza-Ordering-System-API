package io.swagger.repository;

import io.swagger.model.ToppingItem;
import org.springframework.data.mongodb.repository.MongoRepository;

/** ToppingRepository */
public interface ToppingItemRepository extends MongoRepository<ToppingItem, String> {}
