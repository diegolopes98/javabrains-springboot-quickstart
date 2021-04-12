package com.api.application.usecase.course;

import com.api.application.domain.entity.CourseEntity;
import com.api.application.domain.model.CourseModel;
import com.api.application.domain.protocol.course.UpdateCourseInterface;
import com.api.application.infra.repository.CourseRepository;
import com.api.application.presentation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCourseUseCase implements UpdateCourseInterface {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public CourseModel updateCourse(CourseModel courseModel) throws NotFoundException {
        if(!courseRepository.existsById(courseModel.getId())) {
            throw new NotFoundException();
        }

        courseRepository.save(new CourseEntity(courseModel));

        return courseModel;
    }
}
