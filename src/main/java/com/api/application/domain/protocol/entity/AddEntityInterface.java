package com.api.application.domain.protocol.entity;

import com.api.application.presentation.exception.AlreadyExistsException;

public interface AddEntityInterface<T> {
    <PID>T add(T entity, PID parentId) throws  AlreadyExistsException;
    <PID>T add(T entity) throws AlreadyExistsException;
}
