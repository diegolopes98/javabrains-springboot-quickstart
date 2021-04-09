package com.api.application.entities.dto;

import com.api.application.entities.interfaces.response.ResponseInterface;
import com.api.application.entities.model.TopicModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TopicDTO extends TopicModel implements ResponseInterface {

    public TopicDTO() {
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
