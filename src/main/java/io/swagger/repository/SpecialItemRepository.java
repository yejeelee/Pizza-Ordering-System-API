package io.swagger.repository;

import io.swagger.model.SpecialItem;
import org.springframework.data.mongodb.repository.MongoRepository;

/** SpecialItemRepository */
public interface SpecialItemRepository extends MongoRepository<SpecialItem, String> {}
