package com.api.application.domain.protocol.course;

import com.api.application.presentation.exception.NotFoundException;

public interface DeleteCourseInterface {
    void deleteCourse(String id) throws NotFoundException;
}
