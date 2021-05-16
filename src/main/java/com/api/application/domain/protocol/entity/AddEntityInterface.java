package com.api.application.domain.protocol.entity;

import com.api.application.presentation.exception.AlreadyExistsException;
import com.api.application.presentation.exception.NotFoundException;

public interface AddEntityInterface<T> {
    <PID>T add(T model, PID parentId) throws  AlreadyExistsException, NotFoundException;
    <PID>T add(T model) throws AlreadyExistsException;
}
