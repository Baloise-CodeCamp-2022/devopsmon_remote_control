package com.baloise.open.devops.monitor.admin.infrastructure.web;

import com.baloise.open.devops.monitor.admin.infrastructure.web.model.EventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class DevOpsMonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenEvent_whenSent_thenKafkaStored() throws Exception{
        EventDto event = TestModelDtoFactory.createDefault();
        mockMvc.perform(MockMvcRequestBuilders.post("/event/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(event)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
