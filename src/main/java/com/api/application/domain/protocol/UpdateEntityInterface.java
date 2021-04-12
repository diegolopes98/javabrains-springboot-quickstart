package com.api.application.domain.protocol;

import com.api.application.presentation.exception.NotFoundException;

public interface UpdateEntityInterface <T> {
    T update(T entity) throws NotFoundException;
}
