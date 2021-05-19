package com.api.application.usecase.course;

import com.api.application.domain.entity.CourseEntity;
import com.api.application.domain.entity.TopicEntity;
import com.api.application.domain.model.CourseModel;
import com.api.application.infra.repository.CourseRepository;
import com.api.application.presentation.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GetCourseUseCaseTest {
    final String TEST_ID = "test id";
    final String TEST_NAME = "test name";
    final String TEST_DESCRIPTION = "test description";

    final CourseEntity courseEntityMock = mock(CourseEntity.class);
    final TopicEntity topicEntityMock = mock(TopicEntity.class);

    @Mock
    CourseRepository courseRepository;

    @InjectMocks
    GetCourseUseCase courseUseCase;

    @BeforeEach
    void setUpMocks() {
        when(courseEntityMock.getId()).thenReturn(TEST_ID);
        when(courseEntityMock.getName()).thenReturn(TEST_NAME);
        when(courseEntityMock.getDescription()).thenReturn(TEST_DESCRIPTION);
        when(courseEntityMock.getTopic()).thenReturn(topicEntityMock);

        when(courseRepository.findById(anyString())).thenReturn(Optional.of(courseEntityMock));
    }

    @Test
    void shouldThrowIfNotExists() {
        when(courseRepository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> {
            courseUseCase.getById("any id");
        });
    }

    @Test
    void shouldGetCourse() throws NotFoundException {
        CourseModel model = courseUseCase.getById("any id");
        assertEquals(TEST_ID, model.getId());
        assertEquals(TEST_NAME, model.getName());
        assertEquals(TEST_DESCRIPTION, model.getDescription());
        verify(topicEntityMock, atLeastOnce()).getId();
        verify(topicEntityMock, atLeastOnce()).getName();
        verify(topicEntityMock, atLeastOnce()).getDescription();
    }
}
