package com.api.application.domain.protocol.entity;

import com.api.application.presentation.exception.AlreadyExistsException;

public interface AddEntityInterface<T> {
    T add(T entity) throws AlreadyExistsException;
}
