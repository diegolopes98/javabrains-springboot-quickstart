package com.api.application.entities.responses;

import com.api.application.entities.dto.TopicDTO;
import com.api.application.entities.model.TopicModel;

public class GetTopicResponse extends TopicModel {

    public GetTopicResponse(TopicDTO topicDTO) {
        this.id = topicDTO.getId();
        this.name = topicDTO.getName();
        this.description = topicDTO.getDescription();
    }
}
