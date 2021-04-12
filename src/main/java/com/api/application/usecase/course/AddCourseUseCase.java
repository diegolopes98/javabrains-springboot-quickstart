package com.api.application.usecase.course;

import com.api.application.domain.entity.CourseEntity;
import com.api.application.domain.entity.TopicEntity;
import com.api.application.domain.model.CourseModel;
import com.api.application.domain.model.TopicModel;
import com.api.application.domain.protocol.entity.AddEntityInterface;
import com.api.application.infra.repository.CourseRepository;
import com.api.application.infra.repository.TopicRepository;
import com.api.application.presentation.exception.AlreadyExistsException;
import com.api.application.presentation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddCourseUseCase implements AddEntityInterface<CourseModel> {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TopicRepository topicRepository;

    @Override
    public <PID> CourseModel add(CourseModel course, PID parentId) throws AlreadyExistsException, NotFoundException {
        if (courseRepository.existsById(course.getId())) {
            String message = String.format("id [%s] already exists", course.getId());
            throw new AlreadyExistsException(message);
        }

        if (!topicRepository.existsById((String) parentId)) {
            throw new NotFoundException();
        }

        TopicEntity topic = topicRepository.findById((String) parentId).stream().findFirst().get();

        course.setTopic(
                new TopicModel(
                        topic.getId(),
                        topic.getName(),
                        topic.getDescription()
                )
        );

        CourseEntity newCourse = new CourseEntity(course);
        courseRepository.save(newCourse);

        return new CourseModel(
                newCourse.getId(),
                newCourse.getName(),
                newCourse.getDescription(),
                new TopicModel(
                        newCourse.getTopic().getId(),
                        newCourse.getTopic().getName(),
                        newCourse.getTopic().getDescription()
                )
        );
    }

    @Override
    public CourseModel add(CourseModel model) throws AlreadyExistsException {
        throw new UnsupportedOperationException();
    }
}
