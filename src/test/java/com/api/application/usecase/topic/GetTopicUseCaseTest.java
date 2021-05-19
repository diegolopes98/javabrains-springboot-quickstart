package com.api.application.usecase.topic;

import com.api.application.domain.entity.TopicEntity;
import com.api.application.domain.model.TopicModel;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GetTopicUseCaseTest {
    final String TEST_ID = "test id";
    final String TEST_NAME = "test name";
    final String TEST_DESCRIPTION = "test description";

    final TopicEntity topicEntityMock = mock(TopicEntity.class);

    @Mock
    TopicRepository topicRepository;

    @InjectMocks
    GetTopicUseCase topicUseCase;

    @BeforeEach
    void setUpMocks() {
        when(topicEntityMock.getId()).thenReturn(TEST_ID);
        when(topicEntityMock.getName()).thenReturn(TEST_NAME);
        when(topicEntityMock.getDescription()).thenReturn(TEST_DESCRIPTION);

        when(topicRepository.findById(anyString())).thenReturn(Optional.of(topicEntityMock));
    }

    @Test
    void shouldThrowIfNotExists() {
        when(topicRepository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> {
            topicUseCase.getById("any id");
        });
    }

    @Test
    void shouldGetTopic() throws NotFoundException {
        TopicModel model = topicUseCase.getById("any id");
        assertEquals(TEST_ID, model.getId());
        assertEquals(TEST_NAME, model.getName());
        assertEquals(TEST_DESCRIPTION, model.getDescription());
    }
}
