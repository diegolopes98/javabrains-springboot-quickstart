package com.api.application.usecase.course;

import com.api.application.domain.entity.CourseEntity;
import com.api.application.domain.model.CourseModel;
import com.api.application.domain.protocol.entity.AddEntityInterface;
import com.api.application.infra.repository.CourseRepository;
import com.api.application.presentation.exception.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddCourseUseCase implements AddEntityInterface<CourseModel> {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public <PID> CourseModel add(CourseModel course, PID parentId) throws AlreadyExistsException {
        if (courseRepository.existsById(course.getId())) {
            String message = String.format("id [%s] already exists", course.getId());
            throw new AlreadyExistsException(message);
        }

        CourseEntity newCourse = new CourseEntity(course);
        courseRepository.save(newCourse);

        return new CourseModel(
                newCourse.getId(),
                newCourse.getName(),
                newCourse.getDescription()
        );
    }

    @Override
    public CourseModel add(CourseModel entity) throws AlreadyExistsException {
        throw new UnsupportedOperationException();
    }
}
