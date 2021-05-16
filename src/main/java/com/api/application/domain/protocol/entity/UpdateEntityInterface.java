package com.api.application.domain.protocol.entity;

import com.api.application.presentation.exception.NotFoundException;

public interface UpdateEntityInterface <T> {
    <PID>T update(T model, PID parentId) throws NotFoundException;
    <PID>T update(T model) throws NotFoundException;
}
