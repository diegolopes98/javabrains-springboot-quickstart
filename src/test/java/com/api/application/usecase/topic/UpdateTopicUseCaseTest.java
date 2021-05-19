package com.api.application.usecase.topic;

import com.api.application.domain.model.TopicModel;
import com.api.application.infra.repository.TopicRepository;
import com.api.application.presentation.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UpdateTopicUseCaseTest {

    final String TEST_ID = "test id";
    final String TEST_NAME = "test name";
    final String TEST_DESCRIPTION = "test description";

    final TopicModel topicModelMock = mock(TopicModel.class);

    @Mock
    TopicRepository topicRepository;

    @InjectMocks
    UpdateTopicUseCase topicUseCase;


    @BeforeEach
    void setUpMocks() {
        when(topicModelMock.getId()).thenReturn(TEST_ID);
        when(topicModelMock.getName()).thenReturn(TEST_NAME);
        when(topicModelMock.getDescription()).thenReturn(TEST_DESCRIPTION);
        reset(topicRepository);
        when(topicRepository.existsById(anyString())).thenReturn(true);
    }

    @Test
    void shouldThrowIfUseUnsupportedMethod() {
        assertThrows(UnsupportedOperationException.class, () -> {
            topicUseCase.update(topicModelMock, "any id");
        });
    }

    @Test
    void shouldThrowIfTopicNotExists() {
        when(topicRepository.existsById(anyString())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> {
            topicUseCase.update(topicModelMock);
        });
    }

    @Test
    void shouldUpdateTopic() throws NotFoundException {
        TopicModel model = topicUseCase.update(topicModelMock);
        assertEquals(topicModelMock, model);
    }
}
