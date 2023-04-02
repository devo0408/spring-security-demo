package com.devo.product.web.controllers;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTestIT extends BaseTestIT {

    @Test
    void listProducts_ok() throws Exception {
        mockMvc.perform(get("/api/v1/product"))
                .andExpect(status().isOk());
    }

}
