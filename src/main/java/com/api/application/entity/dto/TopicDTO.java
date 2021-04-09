package com.api.application.entity.dto;

import com.api.application.entity.interfaces.response.ResponseInterface;
import com.api.application.entity.model.TopicModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicDTO extends TopicModel implements ResponseInterface {

    public TopicDTO() {
    }

    public TopicDTO(TopicModel topicModel) {
        super.setId(topicModel.getId());
        super.setName(topicModel.getName());
        super.setDescription(topicModel.getDescription());
    }

    public TopicDTO(String id, String name, String description) {
        super.setId(id);
        super.setName(name);
        super.setDescription(description);
    }

    @Override
    public ResponseEntity toResponse() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this);
    }
}
