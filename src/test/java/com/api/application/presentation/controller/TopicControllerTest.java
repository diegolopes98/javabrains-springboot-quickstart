package com.api.application.presentation.controller;

import com.api.application.domain.entity.TopicEntity;
import com.api.application.domain.model.TopicModel;
import com.api.application.infra.repository.TopicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class TopicControllerTest {
    final String TOPIC_TEST_ID = "topic_test";
    final String TOPIC_TEST_NAME = "topic name";
    final String TOPIC_TEST_DESCRIPTION = "topic description";

    TopicModel topicRequest = new TopicModel(TOPIC_TEST_ID, TOPIC_TEST_NAME, TOPIC_TEST_DESCRIPTION);

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    TopicController topicController;

    @Autowired
    TopicRepository topicRepository;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(topicController).build();
    }

    @Test
    void shouldCreateTopic() throws Exception {
        final String RESOURCE_URL = "/topics";

        mockMvc.perform(
                MockMvcRequestBuilders
                    .post(RESOURCE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(topicRequest))
        )
        .andExpect(status().isOk());

        TopicEntity topicEntity = topicRepository.findById(TOPIC_TEST_ID).get();

        assertEquals(TOPIC_TEST_ID, topicEntity.getId());
        assertEquals(TOPIC_TEST_NAME, topicEntity.getName());
        assertEquals(TOPIC_TEST_DESCRIPTION, topicEntity.getDescription());

    }

}
