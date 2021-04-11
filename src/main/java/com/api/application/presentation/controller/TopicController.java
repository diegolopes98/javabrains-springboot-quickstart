package com.api.application.presentation.controller;

import com.api.application.domain.protocol.GetAllTopicsInterface;
import com.api.application.presentation.exception.AlreadyExistsException;
import com.api.application.presentation.exception.NotFoundException;
import com.api.application.presentation.response.error.ConflictErrorResponse;
import com.api.application.presentation.response.error.InternalErrorResponse;
import com.api.application.presentation.response.error.NotFoundErrorResponse;
import com.api.application.presentation.response.topic.TopicResponse;
import com.api.application.usecase.GetAllTopicsUseCase;
import com.api.application.usecase.GetTopicUseCase;
import com.api.application.usecase.TopicUseCase;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TopicUseCase topicUseCase;

    @GetMapping()
    public ResponseEntity getAllTopics() {
        try {
            List<TopicResponse> allTopics = getAllTopicsUseCase
                    .getAllTopics()
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
    public ResponseEntity getTopic(@PathVariable String id) {
        try {
            TopicResponse topicResponse = new TopicResponse(getTopicUseCase.getTopic(id));
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
    public ResponseEntity addTopic(@RequestBody TopicResponse body) {
        try {
            TopicResponse topicResponse = new TopicResponse(topicUseCase.addTopic(body));
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
    public ResponseEntity updateTopic(@RequestBody TopicResponse body, @PathVariable String id) {
        try {
            body.setId(id);
            TopicResponse topicResponse = new TopicResponse(topicUseCase.updateTopic(body));
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
    public ResponseEntity deleteTopic(@PathVariable String id) {
        try {
            topicUseCase.deleteTopic(id);

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
