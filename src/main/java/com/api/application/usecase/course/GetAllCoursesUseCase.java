package com.api.application.usecase.course;

import com.api.application.domain.entity.TopicEntity;
import com.api.application.domain.model.CourseModel;
import com.api.application.domain.model.TopicModel;
import com.api.application.domain.protocol.entity.GetAllEntitiesInterface;
import com.api.application.infra.repository.CourseRepository;
import com.api.application.infra.repository.TopicRepository;
import com.api.application.presentation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllCoursesUseCase implements GetAllEntitiesInterface<CourseModel> {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TopicRepository topicRepository;

    public <PID> List<CourseModel> getAll(PID parentId) throws NotFoundException {
        List<CourseModel> allCourses = new ArrayList<CourseModel>();

        TopicEntity parentTopic = topicRepository.findById((String) parentId).orElse(null);

        if(parentTopic == null) {
            throw new NotFoundException();
        }

        courseRepository
                .findByTopic(parentTopic)
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
