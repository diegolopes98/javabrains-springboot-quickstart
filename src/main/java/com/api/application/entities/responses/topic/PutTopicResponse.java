package com.api.application.entities.responses.topic;

import com.api.application.entities.dto.TopicDTO;
import com.api.application.entities.model.TopicModel;

public class PutTopicResponse extends TopicModel {

    public PutTopicResponse(TopicDTO topicDTO) {
        this.setName(topicDTO.getName());
        this.setDescription(topicDTO.getDescription());
    }
}
