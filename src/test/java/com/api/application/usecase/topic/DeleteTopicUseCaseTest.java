package com.api.application.usecase.topic;

import com.api.application.infra.repository.TopicRepository;
import com.api.application.presentation.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeleteTopicUseCaseTest {

    final String TEST_TOPIC_ID = "TEST_TOPIC_ID";

    @Mock
    TopicRepository topicRepository;

    @InjectMocks
    DeleteTopicUseCase topicUseCase;

    @BeforeEach
    void setUpMocks() {
        reset(topicRepository);
        when(topicRepository.existsById(anyString())).thenReturn(true);
    }

    @Test
    void shouldThrowIfTopicNotExists() {
        when(topicRepository.existsById(anyString())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> {
            topicUseCase.deleteById(TEST_TOPIC_ID);
        });
    }

    @Test
    void shouldDeleteTopic() {
        assertDoesNotThrow(() -> {
            topicUseCase.deleteById(TEST_TOPIC_ID);
        });
    }
}
