package com.api.application.domain.protocol.topic;

import com.api.application.domain.model.TopicModel;
import com.api.application.presentation.exception.NotFoundException;

public interface UpdateTopicInterface {
    TopicModel updateTopic(TopicModel topicModel) throws NotFoundException;
}