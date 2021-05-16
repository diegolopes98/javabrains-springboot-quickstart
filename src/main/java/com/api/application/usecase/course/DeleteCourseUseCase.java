package com.api.application.usecase.course;

import com.api.application.domain.protocol.entity.DeleteEntityByIdInterface;
import com.api.application.infra.repository.CourseRepository;
import com.api.application.presentation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCourseUseCase implements DeleteEntityByIdInterface<String> {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public void deleteById(String id) throws NotFoundException {
        if(!courseRepository.existsById(id)){
            throw new NotFoundException();
        }

        courseRepository.deleteById(id);
    }
}
