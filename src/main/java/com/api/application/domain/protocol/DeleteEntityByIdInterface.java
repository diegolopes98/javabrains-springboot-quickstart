package com.api.application.domain.protocol;

import com.api.application.presentation.exception.NotFoundException;

public interface DeleteEntityByIdInterface <ID> {
    void deleteById(ID id) throws NotFoundException;
}
