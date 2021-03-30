package com.api.application.entities.responses;

import com.api.application.entities.model.TopicModel;

public class PostTopicResponse {
    private String id;

    public PostTopicResponse(TopicModel topic) {
        this.id = topic.getId();
    }

    public String getId() {
        return id;
    }
}
