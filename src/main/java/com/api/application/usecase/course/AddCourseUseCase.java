package com.api.application.usecase.course;

import com.api.application.domain.entity.CourseEntity;
import com.api.application.domain.model.CourseModel;
import com.api.application.domain.protocol.course.AddCourseInterface;
import com.api.application.infra.repository.CourseRepository;
import com.api.application.presentation.exception.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddCourseUseCase implements AddCourseInterface {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public CourseModel addCourse(CourseModel course) throws AlreadyExistsException {
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
}
