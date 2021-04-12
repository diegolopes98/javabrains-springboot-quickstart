package com.api.application.usecase.topic;

import com.api.application.domain.model.TopicModel;
import com.api.application.infra.repository.TopicRepository;
import com.api.application.presentation.exception.AlreadyExistsException;
import com.api.application.presentation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicUseCase {

    @Autowired
    TopicRepository topicRepository;

    private List<TopicModel> topics = new ArrayList<>(
            Arrays.asList(
                    new TopicModel("spring", "Spring Framework", "Learn Spring Faster!"),
                    new TopicModel("adonis", "Adonis Framework", "Learn Adonis Faster!"),
                    new TopicModel("react", "React Lib", "Learn React Faster!")
            )
    );

    public TopicModel getTopic(String id) throws NotFoundException {
        TopicModel topicResponse = topics.stream().filter(topic -> topic.getId().equals(id)).findFirst().orElse(null);

        if (topicResponse == null) {
            throw new NotFoundException();
        }

        return topicResponse;
    }

    public TopicModel addTopic(TopicModel topicData) throws AlreadyExistsException {
        TopicModel topicResponse = new TopicModel(topicData.getId(), topicData.getName(), topicData.getDescription());

        TopicModel listTopicModel = topics
                .stream()
                .filter(topic -> topic.getId().equals(topicResponse.getId()))
                .findFirst()
                .orElse(null);

        if (listTopicModel != null) {
            String message = String.format("id [%s] already exists", topicData.getId());
            throw new AlreadyExistsException(message);
        }

        topics.add(topicResponse);

        return topicResponse;
    }

    public TopicModel updateTopic(TopicModel topicData) throws NotFoundException {
        TopicModel topicResponse = new TopicModel(topicData.getId(), topicData.getName(), topicData.getDescription());

        for(int i = 0; topics.size() > 0 && i < topics.size(); i ++) {
            if (topics.get(i).getId().equals(topicResponse.getId())) {
                topics.set(i, topicResponse);
                return topicResponse;
            }
        }

        throw new NotFoundException();
    }

    public void deleteTopic(String id) throws NotFoundException {
        TopicModel topicToDelete = topics
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
