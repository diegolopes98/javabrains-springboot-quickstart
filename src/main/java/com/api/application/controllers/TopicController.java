package com.api.application.controllers;

import com.api.application.entities.dto.TopicDTO;
import com.api.application.entities.responses.error.ConflictErrorResponse;
import com.api.application.entities.responses.error.InternalErrorResponse;
import com.api.application.entities.responses.error.NotFoundErrorResponse;
import com.api.application.exceptions.AlreadyExistsException;
import com.api.application.exceptions.NotFoundException;
import com.api.application.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping()
    public ResponseEntity getAllTopics() {
        try {
            List<TopicDTO> allTopics = topicService
                    .getAllTopics();

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
            return topicService
                    .getTopic(id)
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

    @PostMapping()
    public ResponseEntity addTopic(@RequestBody TopicDTO body) {
        try {
            return topicService
                    .addTopic(body)
                    .toResponse();
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
    public ResponseEntity updateTopic(@RequestBody TopicDTO body, @PathVariable String id) {
        try {
            body.setId(id);
            return topicService
                    .updateTopic(body)
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
