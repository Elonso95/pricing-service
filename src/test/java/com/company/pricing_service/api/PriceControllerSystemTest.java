package com.company.pricing_service.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerSystemTest {

    @Autowired
    private MockMvc mockMvc;
    private static final Long PRODUCT_ID = 35455L;
    private static final Long BRAND_ID = 1L;

    @Test
    void test1_priceAt_2020_06_14_10_00() throws Exception {
        mockMvc.perform(get("/prices").param("productId", PRODUCT_ID.toString()).param("brandId", BRAND_ID.toString()).param("applicationDate", "2020-06-14T10:00:00")).andExpect(status().isOk()).andExpect(jsonPath("$.productId").value(PRODUCT_ID.intValue())).andExpect(jsonPath("$.brandId").value(BRAND_ID.intValue())).andExpect(jsonPath("$.priceList").value(1)).andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void test2_priceAt_2020_06_14_16_00() throws Exception {
        mockMvc.perform(get("/prices").param("productId", PRODUCT_ID.toString()).param("brandId", BRAND_ID.toString()).param("applicationDate", "2020-06-14T16:00:00")).andExpect(status().isOk()).andExpect(jsonPath("$.priceList").value(2)).andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    void test3_priceAt_2020_06_14_21_00() throws Exception {
        mockMvc.perform(get("/prices").param("productId", PRODUCT_ID.toString()).param("brandId", BRAND_ID.toString()).param("applicationDate", "2020-06-14T21:00:00")).andExpect(status().isOk()).andExpect(jsonPath("$.priceList").value(1)).andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void test4_priceAt_2020_06_15_10_00() throws Exception {
        mockMvc.perform(get("/prices").param("productId", PRODUCT_ID.toString()).param("brandId", BRAND_ID.toString()).param("applicationDate", "2020-06-15T10:00:00")).andExpect(status().isOk()).andExpect(jsonPath("$.priceList").value(3)).andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    void test5_priceAt_2020_06_16_21_00() throws Exception {
        mockMvc.perform(get("/prices").param("productId", PRODUCT_ID.toString()).param("brandId", BRAND_ID.toString()).param("applicationDate", "2020-06-16T21:00:00")).andExpect(status().isOk()).andExpect(jsonPath("$.priceList").value(4)).andExpect(jsonPath("$.price").value(38.95));
    }
}
