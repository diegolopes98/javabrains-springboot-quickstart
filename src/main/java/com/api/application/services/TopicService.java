package com.api.application.services;

import com.api.application.entities.model.TopicModel;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {

    private List<TopicModel> topics = Arrays.asList(
            new TopicModel("spring", "Spring Framework", "Learn Spring Faster!"),
            new TopicModel("adonis", "Adonis Framework", "Learn Adonis Faster!"),
            new TopicModel("react", "React Lib", "Learn React Faster!")
    );

    public List<TopicModel> getAllTopics() {
        return topics;
    }

    public TopicModel getTopic(String id) {
        return topics.stream().filter(topic -> topic.getId().equals(id)).findFirst().orElse(null);
    }
}
