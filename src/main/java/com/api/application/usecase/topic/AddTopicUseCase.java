package com.api.application.usecase.topic;

import com.api.application.domain.entity.TopicEntity;
import com.api.application.domain.model.TopicModel;
import com.api.application.domain.protocol.entity.AddEntityInterface;
import com.api.application.infra.repository.TopicRepository;
import com.api.application.presentation.exception.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddTopicUseCase implements AddEntityInterface<TopicModel> {

    @Autowired
    TopicRepository topicRepository;

    @Override
    public TopicModel add(TopicModel topic) throws AlreadyExistsException {
        if (topicRepository.existsById(topic.getId())) {
            String message = String.format("id [%s] already exists", topic.getId());
            throw new AlreadyExistsException(message);
        }

        TopicEntity newTopic = new TopicEntity(topic);
        topicRepository.save(newTopic);

        return new TopicModel(
                newTopic.getId(),
                newTopic.getName(),
                newTopic.getDescription()
        );
    }
}
