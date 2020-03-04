package io.swagger.repository;

import io.swagger.model.StoreItem;
import org.springframework.data.mongodb.repository.MongoRepository;

/** StoreItem Repository interface */
public interface StoreItemRepository extends MongoRepository<StoreItem, String> {}
