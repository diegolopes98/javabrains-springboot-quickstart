package com.api.application.controllers;

import com.api.application.entities.requests.PostTopicRequest;
import com.api.application.entities.responses.GetTopicResponse;
import com.api.application.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping()
    public List<GetTopicResponse> getAllTopics() {
        return topicService
                .getAllTopics()
                .stream()
                .map(topicDTO -> new GetTopicResponse(topicDTO)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public GetTopicResponse getTopic(@PathVariable String id) {
        return new GetTopicResponse(topicService.getTopic(id));
    }
}
