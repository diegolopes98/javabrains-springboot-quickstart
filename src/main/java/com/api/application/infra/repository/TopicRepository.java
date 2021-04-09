package com.api.application.infra.repository;

import com.api.application.domain.entity.TopicEntity;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<TopicEntity, String> {
}
