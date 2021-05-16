package com.api.application.domain.protocol.entity;

import com.api.application.presentation.exception.NotFoundException;

import java.util.List;

public interface GetAllEntitiesInterface <T> {
    List<T> getAll();
    <PID>List<T> getAll(PID parentId) throws NotFoundException;
}
