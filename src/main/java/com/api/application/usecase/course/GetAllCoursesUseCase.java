package com.api.application.usecase.course;

import com.api.application.domain.model.CourseModel;
import com.api.application.domain.model.TopicModel;
import com.api.application.domain.protocol.entity.GetAllEntitiesInterface;
import com.api.application.infra.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllCoursesUseCase implements GetAllEntitiesInterface<CourseModel> {

    @Autowired
    CourseRepository courseRepository;

    public <PID> List<CourseModel> getAll(PID parentId) {
        List<CourseModel> allCourses = new ArrayList<CourseModel>();

        courseRepository
                .findByTopicId((String) parentId)
                .forEach(
                        courseEntity -> allCourses
                                .add(
                                        new CourseModel(
                                                courseEntity.getId(),
                                                courseEntity.getName(),
                                                courseEntity.getDescription(),
                                                new TopicModel(
                                                        courseEntity.getTopic().getId(),
                                                        courseEntity.getTopic().getName(),
                                                        courseEntity.getTopic().getDescription()
                                                )
                                        )
                                )
                );

        return allCourses;
    }

    @Override
    public List<CourseModel> getAll() {
        throw new UnsupportedOperationException();
    }
}
