package com.api.application.presentation.controller;

import com.api.application.domain.model.TopicModel;
import com.api.application.presentation.exception.AlreadyExistsException;
import com.api.application.presentation.exception.NotFoundException;
import com.api.application.presentation.response.error.BadRequestErrorResponse;
import com.api.application.presentation.response.error.ConflictErrorResponse;
import com.api.application.presentation.response.error.InternalErrorResponse;
import com.api.application.presentation.response.error.NotFoundErrorResponse;
import com.api.application.presentation.response.topic.TopicResponse;
import com.api.application.usecase.topic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private GetAllTopicsUseCase getAllTopicsUseCase;

    @Autowired
    private GetTopicUseCase getTopicUseCase;

    @Autowired
    private AddTopicUseCase addTopicUseCase;

    @Autowired
    private UpdateTopicUseCase updateTopicUseCase;

    @Autowired
    private DeleteTopicUseCase deleteTopicUseCase;

    @GetMapping()
    public ResponseEntity getAll() {
        try {
            List<TopicResponse> allTopics = getAllTopicsUseCase
                    .getAll()
                    .stream()
                    .map(topicModel -> new TopicResponse(topicModel))
                    .collect(Collectors.toList());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(allTopics);
        } catch (Exception e) {
            return InternalErrorResponse
                    .builder()
                    .build()
                    .toResponse();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable String id) {
        try {
            TopicResponse topicResponse = new TopicResponse(getTopicUseCase.getById(id));
            return topicResponse.toResponse();
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
    public ResponseEntity add(@RequestBody TopicModel body) {
        try {
            TopicResponse topicResponse = new TopicResponse(addTopicUseCase.add(body));
            return topicResponse.toResponse();
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
    public ResponseEntity put(@RequestBody TopicModel body, @PathVariable String id) {
        try {
            body.setId(id);
            TopicResponse topicResponse = new TopicResponse(updateTopicUseCase.update(body));
            return topicResponse.toResponse();
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
    public ResponseEntity delete(@PathVariable String id) {
        try {
            deleteTopicUseCase.deleteById(id);

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(null);
        } catch (DataIntegrityViolationException e) {
            String message = String.format("Can't delete topic [%s] because it's has associated courses!", id);
            return BadRequestErrorResponse
                    .builder()
                    .setMessage(message)
                    .build()
                    .toResponse();
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
