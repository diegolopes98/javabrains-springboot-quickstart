package com.api.application.presentation.controller;

import com.api.application.domain.model.CourseModel;
import com.api.application.presentation.exception.AlreadyExistsException;
import com.api.application.presentation.exception.NotFoundException;
import com.api.application.presentation.response.error.ConflictErrorResponse;
import com.api.application.presentation.response.error.InternalErrorResponse;
import com.api.application.presentation.response.error.NotFoundErrorResponse;
import com.api.application.presentation.response.course.CourseResponse;
import com.api.application.usecase.course.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topics/{topicId}/courses")
public class CourseController {

    @Autowired
    private GetAllCoursesUseCase getAllCoursesUseCase;

    @Autowired
    private GetCourseUseCase getCourseUseCase;

    @Autowired
    private AddCourseUseCase addCourseUseCase;

    @Autowired
    private UpdateCourseUseCase updateCourseUseCase;

    @Autowired
    private DeleteCourseUseCase deleteCourseUseCase;

    @GetMapping()
    public ResponseEntity getAll(@PathVariable String topicId) {
        try {
            List<CourseResponse> allCourses = getAllCoursesUseCase
                    .getAll()
                    .stream()
                    .map(course -> new CourseResponse(course))
                    .collect(Collectors.toList());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(allCourses);
        } catch (Exception e) {
            return InternalErrorResponse
                    .builder()
                    .build()
                    .toResponse();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable String topicId, @PathVariable String id) {
        try {
            CourseResponse courseResponse = new CourseResponse(getCourseUseCase.getById(id));
            return courseResponse.toResponse();
        } catch (NotFoundException e) {
            return NotFoundErrorResponse
                    .builder()
                    .build()
                    .toResponse();
        } catch (Exception e) {
            return InternalErrorResponse
                    .builder()
                    .build()
                    .toResponse();
        }
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody CourseModel body, @PathVariable String topicId) {
        try {
            CourseResponse courseResponse = new CourseResponse(
                    addCourseUseCase
                            .add(body, topicId)
            );
            return courseResponse.toResponse();
        } catch (AlreadyExistsException e) {
            return ConflictErrorResponse
                    .builder()
                    .setMessage(e.getMessage())
                    .build()
                    .toResponse();
        } catch (Exception e) {
            return InternalErrorResponse
                    .builder()
                    .build()
                    .toResponse();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@RequestBody CourseModel body, @PathVariable String topicId, @PathVariable String id) {
        try {
            body.setId(id);
            CourseResponse courseResponse = new CourseResponse(
                    updateCourseUseCase
                            .update(body, topicId)
            );
            return courseResponse.toResponse();
        } catch (NotFoundException e) {
            return NotFoundErrorResponse
                    .builder()
                    .build()
                    .toResponse();
        } catch (Exception e) {
            return InternalErrorResponse
                    .builder()
                    .build()
                    .toResponse();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String topicId, @PathVariable String id) {
        try {
            deleteCourseUseCase.deleteById(id);

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(null);
        } catch (NotFoundException e) {
            return NotFoundErrorResponse
                    .builder()
                    .build()
                    .toResponse();
        } catch (Exception e) {
            return InternalErrorResponse
                    .builder()
                    .build()
                    .toResponse();
        }
    }
}
