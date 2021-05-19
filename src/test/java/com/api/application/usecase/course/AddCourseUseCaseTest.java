package com.api.application.usecase.course;

import com.api.application.domain.entity.TopicEntity;
import com.api.application.domain.model.CourseModel;
import com.api.application.domain.model.TopicModel;
import com.api.application.infra.repository.CourseRepository;
import com.api.application.infra.repository.TopicRepository;
import com.api.application.presentation.exception.AlreadyExistsException;
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
public class AddCourseUseCaseTest {
    final String MOCK_TOPIC_ID = "test topic id";
    final String MOCK_TOPIC_NAME = "test topic name";
    final String MOCK_TOPIC_DESCRIPTION = "test topic description";
    final String MOCK_COURSE_ID = "test course id";
    final String MOCK_COURSE_NAME = "test course name";
    final String MOCK_COURSE_DESCRIPTION = "test course description";

    final TopicEntity topicEntityMock = mock(TopicEntity.class);

    final TopicModel topicModelMock = mock(TopicModel.class);
    final CourseModel courseModelMock = mock(CourseModel.class);

    @Mock
    TopicRepository topicRepository;

    @Mock
    CourseRepository courseRepository;

    @InjectMocks
    AddCourseUseCase courseUseCase;

    @BeforeEach
    void setUpMocks() {
        when(topicEntityMock.getId()).thenReturn(MOCK_TOPIC_ID);
        when(topicEntityMock.getName()).thenReturn(MOCK_TOPIC_NAME);
        when(topicEntityMock.getDescription()).thenReturn(MOCK_TOPIC_DESCRIPTION);

        when(topicModelMock.getId()).thenReturn(MOCK_TOPIC_ID);
        when(topicModelMock.getName()).thenReturn(MOCK_TOPIC_NAME);
        when(topicModelMock.getDescription()).thenReturn(MOCK_TOPIC_DESCRIPTION);

        when(courseModelMock.getId()).thenReturn(MOCK_COURSE_ID);
        when(courseModelMock.getName()).thenReturn(MOCK_COURSE_NAME);
        when(courseModelMock.getDescription()).thenReturn(MOCK_COURSE_DESCRIPTION);
        when(courseModelMock.getTopic()).thenReturn(topicModelMock);

        reset(topicRepository);
        when(topicRepository.existsById(anyString())).thenReturn(true);
        when(topicRepository.findById(anyString())).thenReturn(Optional.of(topicEntityMock));

        reset(courseRepository);
        when(courseRepository.existsById(anyString())).thenReturn(false);
    }

    @Test
    void shouldThrowIfUseUnsupportedMethod() {
        assertThrows(UnsupportedOperationException.class, () -> {
            courseUseCase.<String>add(courseModelMock);
        });
    }

    @Test
    void shouldThrowIfCourseAlreadyExists() {
        when(courseRepository.existsById(anyString())).thenReturn(true);
        assertThrows(AlreadyExistsException.class, () -> {
            courseUseCase.add(courseModelMock, topicModelMock.getId());
        });
    }

    @Test
    void shouldThrowIfCourseNotExists() {
        when(topicRepository.existsById(anyString())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> {
            courseUseCase.add(courseModelMock, topicModelMock.getId());
        });
    }

    @Test
    void shouldSaveCourse() throws AlreadyExistsException, NotFoundException {
        CourseModel model = courseUseCase.add(courseModelMock, topicModelMock.getId());
        assertEquals(MOCK_COURSE_ID, model.getId());
        assertEquals(MOCK_COURSE_NAME, model.getName());
        assertEquals(MOCK_COURSE_DESCRIPTION, model.getDescription());
        assertEquals(topicModelMock.getId(), model.getTopic().getId());
        assertEquals(topicModelMock.getName(), model.getTopic().getName());
        assertEquals(topicModelMock.getDescription(), model.getTopic().getDescription());
    }
}
