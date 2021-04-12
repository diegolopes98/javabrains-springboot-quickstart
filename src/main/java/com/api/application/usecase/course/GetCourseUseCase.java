package com.api.application.usecase.course;

import com.api.application.domain.entity.CourseEntity;
import com.api.application.domain.model.CourseModel;
import com.api.application.domain.protocol.course.GetCourseInterface;
import com.api.application.infra.repository.CourseRepository;
import com.api.application.presentation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCourseUseCase implements GetCourseInterface {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public CourseModel getCourse(String id) throws NotFoundException {
        CourseEntity courseEntity = courseRepository.findById(id).orElse(null);

        if(courseEntity == null) {
            throw new NotFoundException();
        }

        return new CourseModel(
                courseEntity.getId(),
                courseEntity.getName(),
                courseEntity.getDescription()
        );
    }
}
