package com.api.application.usecase.course;

import com.api.application.domain.entity.CourseEntity;
import com.api.application.domain.entity.TopicEntity;
import com.api.application.domain.model.CourseModel;
import com.api.application.domain.model.TopicModel;
import com.api.application.domain.protocol.entity.UpdateEntityInterface;
import com.api.application.infra.repository.CourseRepository;
import com.api.application.infra.repository.TopicRepository;
import com.api.application.presentation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCourseUseCase implements UpdateEntityInterface<CourseModel> {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TopicRepository topicRepository;

    @Override
    public <PID> CourseModel update(CourseModel courseModel, PID parentId) throws NotFoundException {
        if(!courseRepository.existsById(courseModel.getId()) || !topicRepository.existsById((String) parentId)) {
            throw new NotFoundException();
        }

        TopicEntity topic = topicRepository.findById((String) parentId).stream().findFirst().get();

        courseModel.setTopic(
                new TopicModel(
                        topic.getId(),
                        topic.getName(),
                        topic.getDescription()
                )
        );

        courseRepository.save(new CourseEntity(courseModel));

        return courseModel;
    }

    public CourseModel update(CourseModel courseModel) throws NotFoundException {
        throw new UnsupportedOperationException();
    }
}
