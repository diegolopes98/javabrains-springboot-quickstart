package com.api.application.usecase.course;

import com.api.application.domain.entity.TopicEntity;
import com.api.application.domain.model.CourseModel;
import com.api.application.domain.model.TopicModel;
import com.api.application.infra.repository.CourseRepository;
import com.api.application.infra.repository.TopicRepository;
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
import static org.mockito.Mockito.when;

@SpringBootTest
public class UpdateCourseUseCaseTest {
    final String TOPIC_MOCK_ID = "topic mock id";
    final String TOPIC_MOCK_NAME = "topic mock name";
    final String TOPIC_MOCK_DESCRIPTION = "topic mock description";

    final String COURSE_MOCK_ID = "course mock id";
    final String COURSE_MOCK_NAME = "course mock name";
    final String COURSE_MOCK_DESCRIPTION = "course mock description";

    final TopicEntity topicEntityMock = mock(TopicEntity.class);
    final TopicModel topicModelMock = mock(TopicModel.class);
    final CourseModel courseModelMock = mock(CourseModel.class);

    @Mock
    TopicRepository topicRepository;

    @Mock
    CourseRepository courseRepository;

    @InjectMocks
    UpdateCourseUseCase courseUseCase;

    @BeforeEach
    void setUpMocks() {
        when(topicEntityMock.getId()).thenReturn(TOPIC_MOCK_ID);
        when(topicEntityMock.getName()).thenReturn(TOPIC_MOCK_NAME);
        when(topicEntityMock.getDescription()).thenReturn(TOPIC_MOCK_DESCRIPTION);

        when(topicModelMock.getId()).thenReturn(TOPIC_MOCK_ID);
        when(topicModelMock.getName()).thenReturn(TOPIC_MOCK_NAME);
        when(topicModelMock.getDescription()).thenReturn(TOPIC_MOCK_DESCRIPTION);

        when(courseModelMock.getId()).thenReturn(COURSE_MOCK_ID);
        when(courseModelMock.getName()).thenReturn(COURSE_MOCK_NAME);
        when(courseModelMock.getDescription()).thenReturn(COURSE_MOCK_DESCRIPTION);
        when(courseModelMock.getTopic()).thenReturn(topicModelMock);

        reset(topicRepository);
        when(topicRepository.existsById(anyString())).thenReturn(true);
        when(topicRepository.findById(anyString())).thenReturn(Optional.of(topicEntityMock));

        reset(courseRepository);
        when(courseRepository.existsById(anyString())).thenReturn(true);
    }

    @Test
    void shouldThrowIfUseUnsupportedMethod() {
        assertThrows(UnsupportedOperationException.class, () -> {
            courseUseCase.update(courseModelMock);
        });
    }

    @Test
    void shouldThrowIfTopicDoesNotExists() {
        when(topicRepository.existsById(anyString())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> {
            courseUseCase.update(courseModelMock, "any id");
        });
    }

    @Test
    void shouldThrowIfCourseDoesNotExists() {
        when(courseRepository.existsById(anyString())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> {
            courseUseCase.update(courseModelMock, "any id");
        });
    }

    @Test
    void shouldUpdateCourse() throws NotFoundException {
        CourseModel model = courseUseCase.update(courseModelMock, "any id");
        assertEquals(courseModelMock, model);
    }
}
