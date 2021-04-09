package com.api.application.entity.repository;

import com.api.application.entity.model.TopicModel;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<TopicModel, String> {
}
