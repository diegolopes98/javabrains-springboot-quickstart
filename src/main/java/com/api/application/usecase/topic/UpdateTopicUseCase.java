package com.api.application.usecase.topic;

import com.api.application.domain.entity.TopicEntity;
import com.api.application.domain.model.TopicModel;
import com.api.application.domain.protocol.UpdateTopicInterface;
import com.api.application.infra.repository.TopicRepository;
import com.api.application.presentation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTopicUseCase implements UpdateTopicInterface {

    @Autowired
    TopicRepository topicRepository;

    @Override
    public TopicModel updateTopic(TopicModel topicModel) throws NotFoundException {
        if(!topicRepository.existsById(topicModel.getId())) {
            throw new NotFoundException();
        }

        topicRepository.save(new TopicEntity(topicModel));

        return topicModel;
    }
}
