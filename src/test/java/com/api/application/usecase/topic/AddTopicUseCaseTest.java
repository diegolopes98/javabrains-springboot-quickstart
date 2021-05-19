package com.api.application.usecase.topic;

import com.api.application.domain.model.TopicModel;
import com.api.application.infra.repository.TopicRepository;
import com.api.application.presentation.exception.AlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AddTopicUseCaseTest {
    final String TEST_ID = "test id";
    final String TEST_NAME = "test name";
    final String TEST_DESCRIPTION = "test description";

    final TopicModel topicModelMock = mock(TopicModel.class);

    @Mock
    TopicRepository topicRepository;

    @InjectMocks
    AddTopicUseCase topicUseCase;

    @BeforeEach
    void setUpMocks() {
        when(topicModelMock.getId()).thenReturn(TEST_ID);
        when(topicModelMock.getName()).thenReturn(TEST_NAME);
        when(topicModelMock.getDescription()).thenReturn(TEST_DESCRIPTION);
        reset(topicRepository);
        when(topicRepository.existsById(anyString())).thenReturn(false);
    }

    @Test
    void shouldThrowIfUseUnsupportedMethod() {
        assertThrows(UnsupportedOperationException.class, () -> {
            topicUseCase.<String>add(topicModelMock, "any id");
        });
    }

    @Test
    void shouldThrowIfAlreadyExists() {
        when(topicRepository.existsById(anyString())).thenReturn(true);
        assertThrows(AlreadyExistsException.class, () -> {
            topicUseCase.add(topicModelMock);
        });
    }

    @Test
    void shouldSaveTopic() throws AlreadyExistsException {
        TopicModel model = topicUseCase.add(topicModelMock);
        assertEquals(TEST_ID, model.getId());
        assertEquals(TEST_NAME, model.getName());
        assertEquals(TEST_DESCRIPTION, model.getDescription());
    }
}
