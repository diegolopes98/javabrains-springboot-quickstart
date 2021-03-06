package com.api.application.usecase.topic;

import com.api.application.domain.entity.TopicEntity;
import com.api.application.domain.model.TopicModel;
import com.api.application.domain.protocol.entity.GetEntityByIdInterface;
import com.api.application.infra.repository.TopicRepository;
import com.api.application.presentation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTopicUseCase implements GetEntityByIdInterface<TopicModel, String> {

    @Autowired
    TopicRepository topicRepository;

    @Override
    public TopicModel getById(String id) throws NotFoundException {
        TopicEntity topicEntity = topicRepository.findById(id).orElse(null);

        if(topicEntity == null) {
            throw new NotFoundException();
        }

        return new TopicModel(
                topicEntity.getId(),
                topicEntity.getName(),
                topicEntity.getDescription()
        );
    }
}
