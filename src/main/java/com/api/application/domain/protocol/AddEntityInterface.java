package com.api.application.domain.protocol;

import com.api.application.presentation.exception.AlreadyExistsException;

public interface AddEntityInterface<T> {
    T add(T entity) throws AlreadyExistsException;
}
