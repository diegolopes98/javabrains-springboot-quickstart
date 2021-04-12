package com.api.application.domain.protocol.course;

import com.api.application.domain.model.CourseModel;
import com.api.application.presentation.exception.NotFoundException;

public interface UpdateCourseInterface {
    CourseModel updateCourse(CourseModel courseModel) throws NotFoundException;
}
