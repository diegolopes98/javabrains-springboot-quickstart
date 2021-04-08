package com.api.application.entities.responses.topic;

import com.api.application.entities.dto.TopicDTO;
import com.api.application.entities.model.TopicModel;

public class PostTopicResponse extends TopicModel{
    private String id;

    public PostTopicResponse(TopicDTO topic) {
        this.id = topic.getId();
    }

    public String getId() {
        return id;
    }
}
