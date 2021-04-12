package com.api.application.domain.protocol.topic;

import com.api.application.domain.model.TopicModel;

import java.util.List;

public interface GetAllTopicsInterface {
    List<TopicModel> getAllTopics();
}
