package com.devo.product.web.controllers;

import org.junit.jupiter.api.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class CustomerControllerIT extends BaseIT {

    @Test
    void listProducts_ok() throws Exception {
        mockMvc.perform(get("/api/v1/customer")
                .with(httpBasic("devo", "supersecreto")))
                .andExpect(status().isOk());
    }

}
