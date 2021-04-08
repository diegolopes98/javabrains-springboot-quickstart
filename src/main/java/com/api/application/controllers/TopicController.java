package com.api.application.controllers;

import com.api.application.entities.requests.TopicRequest;
import com.api.application.entities.responses.error.ConflictErrorResponse;
import com.api.application.entities.responses.error.NotFoundErrorResponse;
import com.api.application.entities.responses.topic.GetTopicResponse;
import com.api.application.entities.responses.topic.PostTopicResponse;
import com.api.application.entities.responses.error.InternalErrorResponse;
import com.api.application.entities.responses.topic.PutTopicResponse;
import com.api.application.exceptions.AlreadyExistsException;
import com.api.application.exceptions.NotFoundException;
import com.api.application.services.TopicService;
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
    private TopicService topicService;

    @GetMapping()
    public ResponseEntity getAllTopics() {
        try {
            List<GetTopicResponse> allTopics = topicService
                    .getAllTopics()
                    .stream()
                    .map(topicDTO -> new GetTopicResponse(topicDTO)).collect(Collectors.toList());

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
            GetTopicResponse topic = new GetTopicResponse(topicService.getTopic(id));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(topic);
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
    public ResponseEntity addTopic(@RequestBody TopicRequest body) {
        try {
            PostTopicResponse createdTopic = new PostTopicResponse(topicService.addTopic(body));

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createdTopic);
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
    public ResponseEntity updateTopic(@RequestBody TopicRequest body, @PathVariable String id) {
        try {
            body.setId(id);
            PutTopicResponse updatedTopic = new PutTopicResponse(topicService.updateTopic(body));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updatedTopic);
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
            topicService.deleteTopic(id);

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
