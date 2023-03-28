package com.devo.product.web.controllers;

import com.devo.product.services.CustomerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

  @Mock
  CustomerService customerService;

  @InjectMocks
  CustomerController customerController;

  MockMvc mockMvc;

  ObjectMapper objectMapper;


  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
    mockMvc = MockMvcBuilders
        .standaloneSetup(customerController)
        .build();
  }

  @Test
  void findCustomers() throws Exception {
    List<String> input = List.of(
        UUID.randomUUID().toString(),
        UUID.randomUUID().toString()
    );
    doReturn(input).when(customerService).getAllCustomerIds();

    MvcResult mvcResult = mockMvc.perform(get("/api/v1/customer"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    List<String> customerUuids = objectMapper.readValue(
        mvcResult.getResponse().getContentAsString(),
        new TypeReference<>() {}
    );

    assertEquals(input, customerUuids);
  }

}
