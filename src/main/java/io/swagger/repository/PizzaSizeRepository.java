package io.swagger.repository;

import io.swagger.model.PizzaSize;
import org.springframework.data.mongodb.repository.MongoRepository;

/** PizzaSizeRepository */
public interface PizzaSizeRepository extends MongoRepository<PizzaSize, String> {}
