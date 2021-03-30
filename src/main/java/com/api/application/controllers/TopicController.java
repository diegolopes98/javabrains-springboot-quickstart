package com.api.application.controllers;

import com.api.application.entities.responses.TopicResponse;
import com.api.application.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping()
    public List<TopicResponse> getAllTopics() {
        return topicService
                .getAllTopics()
                .stream()
                .map(topicModel -> new TopicResponse(topicModel)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TopicResponse getTopic(@PathVariable String id) {
        return new TopicResponse(topicService.getTopic(id));
    }
}
