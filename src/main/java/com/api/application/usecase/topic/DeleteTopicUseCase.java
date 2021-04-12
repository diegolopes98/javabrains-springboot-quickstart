package com.api.application.usecase.topic;

import com.api.application.domain.protocol.topic.DeleteTopicInterface;
import com.api.application.infra.repository.TopicRepository;
import com.api.application.presentation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTopicUseCase implements DeleteTopicInterface {

    @Autowired
    TopicRepository topicRepository;

    @Override
    public void deleteTopic(String id) throws NotFoundException {
        if(!topicRepository.existsById(id)){
            throw new NotFoundException();
        }

        topicRepository.deleteById(id);
    }
}
