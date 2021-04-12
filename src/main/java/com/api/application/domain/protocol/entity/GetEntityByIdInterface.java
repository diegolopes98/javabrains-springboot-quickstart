package com.api.application.domain.protocol.entity;

import com.api.application.presentation.exception.NotFoundException;

public interface GetEntityByIdInterface <T, ID>{
    T getById(ID id) throws NotFoundException;
}
