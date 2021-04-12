package com.api.application.domain.protocol.course;

import com.api.application.domain.model.CourseModel;
import com.api.application.presentation.exception.AlreadyExistsException;

public interface AddCourseInterface {
    CourseModel addCourse(CourseModel course) throws AlreadyExistsException;
}
