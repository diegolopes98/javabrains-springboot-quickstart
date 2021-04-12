package com.api.application.domain.protocol.course;

import com.api.application.domain.model.CourseModel;
import com.api.application.domain.model.TopicModel;

import java.util.List;

public interface GetAllCoursesInterface {
    List<CourseModel> getAllCourses();
}
