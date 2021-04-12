package com.api.application.usecase.course;

import com.api.application.domain.model.CourseModel;
import com.api.application.domain.protocol.course.GetAllCoursesInterface;
import com.api.application.infra.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllCoursesUseCase implements GetAllCoursesInterface {

    @Autowired
    CourseRepository courseRepository;

    public List<CourseModel> getAllCourses() {
        List<CourseModel> allCourses = new ArrayList<CourseModel>();

        courseRepository
                .findAll()
                .forEach(
                        topicEntity -> allCourses
                                .add(
                                        new CourseModel(
                                                topicEntity.getId(),
                                                topicEntity.getName(),
                                                topicEntity.getDescription()
                                        )
                                )
                );

        return allCourses;
    }
}
