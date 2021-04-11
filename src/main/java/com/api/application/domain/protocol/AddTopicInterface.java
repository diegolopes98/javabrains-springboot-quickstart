package com.api.application.domain.protocol;

import com.api.application.domain.model.TopicModel;
import com.api.application.presentation.exception.AlreadyExistsException;

public interface AddTopicInterface {
    TopicModel addTopic(TopicModel topic) throws AlreadyExistsException;
}
