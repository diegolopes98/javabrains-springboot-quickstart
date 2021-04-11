package com.api.application.usecase;

import com.api.application.domain.model.TopicModel;
import com.api.application.domain.protocol.GetAllTopicsInterface;
import com.api.application.infra.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllTopicsUseCase implements GetAllTopicsInterface {

    @Autowired
    TopicRepository topicRepository;

    public List<TopicModel> getAllTopics() {
        List<TopicModel> allTopics = new ArrayList<TopicModel>();

        topicRepository
                .findAll()
                .forEach(
                        topicEntity -> allTopics
                                .add(
                                        new TopicModel(
                                                topicEntity.getId(),
                                                topicEntity.getName(),
                                                topicEntity.getDescription()
                                        )
                                )
                );

        return allTopics;
    }
}
