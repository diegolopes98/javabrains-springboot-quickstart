package com.api.application.entities.dto;

import com.api.application.entities.model.TopicModel;

public class TopicDTO extends TopicModel {

    public TopicDTO() {
    }

    public TopicDTO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
