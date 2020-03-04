package io.swagger.repository;

import io.swagger.model.SideItem;
import org.springframework.data.mongodb.repository.MongoRepository;

/** SideItem Repository interface */
public interface SideItemRepository extends MongoRepository<SideItem, String> {}
