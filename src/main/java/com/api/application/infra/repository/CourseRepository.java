package com.api.application.infra.repository;

import com.api.application.domain.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, String> {
    List<CourseEntity> findByTopicId(String topicId);
}
