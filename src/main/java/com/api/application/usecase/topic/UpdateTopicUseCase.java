package com.api.application.usecase.topic;

import com.api.application.domain.entity.TopicEntity;
import com.api.application.domain.model.TopicModel;
import com.api.application.domain.protocol.entity.UpdateEntityInterface;
import com.api.application.infra.repository.TopicRepository;
import com.api.application.presentation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTopicUseCase implements UpdateEntityInterface<TopicModel> {

    @Autowired
    TopicRepository topicRepository;

    @Override
    public TopicModel update(TopicModel topicModel) throws NotFoundException {
        if(!topicRepository.existsById(topicModel.getId())) {
            throw new NotFoundException();
        }

        topicRepository.save(new TopicEntity(topicModel));

        return topicModel;
    }
}
