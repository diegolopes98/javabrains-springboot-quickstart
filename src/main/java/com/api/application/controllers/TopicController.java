package com.api.application.controllers;

import com.api.application.entities.requests.PostTopicRequest;
import com.api.application.entities.responses.topic.GetTopicResponse;
import com.api.application.entities.responses.topic.PostTopicResponse;
import com.api.application.entities.responses.error.InternalErrorResponse;
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
            InternalErrorResponse error = new InternalErrorResponse();
            return error.toResponse();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getTopic(@PathVariable String id) {
        try {
            GetTopicResponse topic = new GetTopicResponse(topicService.getTopic(id));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(topic);
        } catch (Exception e) {
            InternalErrorResponse error = new InternalErrorResponse();
            return error.toResponse();
        }
    }

    @PostMapping()
    public ResponseEntity addTopic(@RequestBody PostTopicRequest body) {
        try {
            PostTopicResponse createdTopic = new PostTopicResponse(topicService.addTopic(body));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(createdTopic);
        } catch (Exception e) {
            InternalErrorResponse error = new InternalErrorResponse();
            return error.toResponse();
        }
    }
}
