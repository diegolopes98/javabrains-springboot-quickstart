package com.api.application.controllers;

import com.api.application.entities.responses.TopicResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @GetMapping()
    public List<TopicResponse> getAllTopics() {
        return Arrays.asList(
                new TopicResponse("spring", "Spring Framework", "Learn Spring Faster!"),
                new TopicResponse("adonis", "Adonis Framework", "Learn Adonis Faster!"),
                new TopicResponse("react", "React Lib", "Learn React Faster!")
        );
    }
}
