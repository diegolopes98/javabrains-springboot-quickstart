package com.api.application.presentation.response.course;

import com.api.application.domain.model.CourseModel;
import com.api.application.presentation.protocol.ResponseInterface;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponse extends CourseModel implements ResponseInterface {

    public CourseResponse() {
    }

    public CourseResponse(CourseModel courseModel) {
        super.setId(courseModel.getId());
        super.setName(courseModel.getName());
        super.setDescription(courseModel.getDescription());
    }

    public CourseResponse(String id, String name, String description) {
        super.setId(id);
        super.setName(name);
        super.setDescription(description);
    }

    @Override
    public ResponseEntity toResponse() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this);
    }
}
