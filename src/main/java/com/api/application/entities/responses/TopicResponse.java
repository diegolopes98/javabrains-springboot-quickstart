package com.api.application.entities.responses;

import com.api.application.entities.model.TopicModel;

public class TopicResponse {

    private final String id;
    private final String name;
    private final String description;

    public TopicResponse(TopicModel topicModel) {
        this.id = topicModel.getId();
        this.name = topicModel.getName();
        this.description = topicModel.getDescription();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
