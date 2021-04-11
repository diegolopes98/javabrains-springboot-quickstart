package com.api.application.domain.protocol;

import com.api.application.domain.model.TopicModel;
import com.api.application.presentation.exception.NotFoundException;

public interface GetTopicInterface {
    TopicModel getTopic(String id) throws NotFoundException;
}
