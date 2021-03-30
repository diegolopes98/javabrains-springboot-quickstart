package com.api.application.services;

import com.api.application.entities.dto.TopicDTO;
import com.api.application.entities.model.TopicModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {

    private List<TopicDTO> topics = new ArrayList<>(
            Arrays.asList(
                    new TopicDTO("spring", "Spring Framework", "Learn Spring Faster!"),
                    new TopicDTO("adonis", "Adonis Framework", "Learn Adonis Faster!"),
                    new TopicDTO("react", "React Lib", "Learn React Faster!")
            )
    );

    public List<TopicDTO> getAllTopics() {
        return topics;
    }

    public TopicDTO getTopic(String id) {
        return topics.stream().filter(topic -> topic.getId().equals(id)).findFirst().orElse(null);
    }

    public TopicDTO addTopic(TopicModel topicData) {
        TopicDTO topicDTO = new TopicDTO(topicData.getId(), topicData.getName(), topicData.getDescription());

        TopicDTO listTopicDTO = topics
                .stream()
                .filter(topic -> topic.getId().equals(topicDTO.getId()))
                .findFirst()
                .orElse(null);

        if (listTopicDTO == null) {
            topics.add(topicDTO);
        }

        return topicDTO;
    }
}
