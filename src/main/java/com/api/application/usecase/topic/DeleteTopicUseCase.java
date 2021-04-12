package com.api.application.usecase.topic;

import com.api.application.domain.protocol.entity.DeleteEntityByIdInterface;
import com.api.application.infra.repository.TopicRepository;
import com.api.application.presentation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTopicUseCase implements DeleteEntityByIdInterface<String> {

    @Autowired
    TopicRepository topicRepository;

    @Override
    public void deleteById(String id) throws NotFoundException {
        if(!topicRepository.existsById(id)){
            throw new NotFoundException();
        }

        topicRepository.deleteById(id);
    }
}
