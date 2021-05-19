package com.api.application.usecase.course;

import com.api.application.infra.repository.CourseRepository;
import com.api.application.presentation.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeleteCourseUseCaseTest {

    final String TEST_TOPIC_ID = "TEST_TOPIC_ID";

    @Mock
    CourseRepository courseRepository;

    @InjectMocks
    DeleteCourseUseCase courseUseCase;

    @BeforeEach
    void setUpMocks() {
        reset(courseRepository);
        when(courseRepository.existsById(anyString())).thenReturn(true);
    }

    @Test
    void shouldThrowIfCourseNotExists() {
        when(courseRepository.existsById(anyString())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> {
            courseUseCase.deleteById(TEST_TOPIC_ID);
        });
    }

    @Test
    void shouldDeleteCourse() {
        assertDoesNotThrow(() -> {
            courseUseCase.deleteById(TEST_TOPIC_ID);
        });
    }
}
