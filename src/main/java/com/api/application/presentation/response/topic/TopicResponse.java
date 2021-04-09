package com.api.application.presentation.response.topic;

import com.api.application.presentation.protocol.ResponseInterface;
import com.api.application.domain.model.TopicModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicResponse extends TopicModel implements ResponseInterface {

    public TopicResponse() {
    }

    public TopicResponse(TopicModel topicModel) {
        super.setId(topicModel.getId());
        super.setName(topicModel.getName());
        super.setDescription(topicModel.getDescription());
    }

    public TopicResponse(String id, String name, String description) {
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
