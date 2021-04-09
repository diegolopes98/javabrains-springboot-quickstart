package com.api.application.service;

import com.api.application.entity.dto.TopicDTO;
import com.api.application.entity.model.TopicModel;
import com.api.application.exception.AlreadyExistsException;
import com.api.application.exception.NotFoundException;
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

    public TopicDTO getTopic(String id) throws NotFoundException {
        TopicDTO topicDTO = topics.stream().filter(topic -> topic.getId().equals(id)).findFirst().orElse(null);

        if (topicDTO == null) {
            throw new NotFoundException();
        }

        return topicDTO;
    }

    public TopicDTO addTopic(TopicModel topicData) throws AlreadyExistsException {
        TopicDTO topicDTO = new TopicDTO(topicData.getId(), topicData.getName(), topicData.getDescription());

        TopicDTO listTopicDTO = topics
                .stream()
                .filter(topic -> topic.getId().equals(topicDTO.getId()))
                .findFirst()
                .orElse(null);

        if (listTopicDTO != null) {
            String message = String.format("id [%s] already exists", topicData.getId());
            throw new AlreadyExistsException(message);
        }

        topics.add(topicDTO);

        return topicDTO;
    }

    public TopicDTO updateTopic(TopicModel topicData) throws NotFoundException {
        TopicDTO topicDTO = new TopicDTO(topicData.getId(), topicData.getName(), topicData.getDescription());

        for(int i = 0; topics.size() > 0 && i < topics.size(); i ++) {
            if (topics.get(i).getId().equals(topicDTO.getId())) {
                topics.set(i, topicDTO);
                return topicDTO;
            }
        }

        throw new NotFoundException();
    }

    public void deleteTopic(String id) throws NotFoundException {
        TopicDTO topicToDelete = topics
                .stream()
                .filter(topic -> topic.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (topicToDelete == null) {
            throw new NotFoundException();
        }

        topics.remove(topicToDelete);
    }
}
