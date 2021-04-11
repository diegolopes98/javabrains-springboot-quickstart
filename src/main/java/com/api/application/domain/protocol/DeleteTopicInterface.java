package com.api.application.domain.protocol;

import com.api.application.presentation.exception.NotFoundException;

public interface DeleteTopicInterface {
    void deleteTopic(String id) throws NotFoundException;
}
