package com.softtek.testjava2020.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("testDataForZara")
    void priceMustBEFoundWithCorrectArguments(String date, String productId, String brandId, String expectedItemPrice) throws Exception {
        ResultActions resultActions = performRequest(date, productId, brandId);

        resultActions.andExpectAll(
                        status().isOk()
                        , jsonPath("$.brand.id").value(1)
                        , jsonPath("$.brand.name").value("ZARA")
                        , jsonPath("$.itemPrice").value(expectedItemPrice)
                        , jsonPath("$.curr").value("EUR"))
                .andReturn();
    }

    @ParameterizedTest
    @MethodSource("testDataForPriceNotFound")
    void priceMustNotBeFoundUsingWrongArguments(String date, String productId, String brandId) throws Exception {
        ResultActions resultActions = performRequest(date, productId, brandId);

        resultActions.andExpectAll(
                status().isNotFound(),
                jsonPath("$.error_message").value("Price not found for the parameters date: " + date
                        + ", productId: " + productId
                        + ", brandId: " + brandId))
                .andReturn();
    }

    private ResultActions performRequest(String date, String productId, String brandId) throws Exception {
        return mockMvc.perform(get("/price")
                .param("date", date)
                .param("productId", productId)
                .param("brandId", brandId));
    }

    private static Stream<Arguments> testDataForZara() {
        return Stream.of(
                Arguments.of("2020-06-14T10:00:00", "35455", "1", "35.5"),
                Arguments.of("2020-06-14T16:00:00", "35455", "1", "25.45"),
                Arguments.of("2020-06-14T21:00:00", "35455", "1", "35.5"),
                Arguments.of("2020-06-15T10:00:00", "35455", "1", "30.5"),
                Arguments.of("2020-06-16T21:00:00", "35455", "1", "38.95")
        );
    }

    private static Stream<Arguments> testDataForPriceNotFound() {
        return Stream.of(
                Arguments.of("2023-06-14T10:00:10", "35455", "1", "35.5"),
                Arguments.of("2020-06-14T10:00:10", "99999", "1", "35.5"),
                Arguments.of("2020-06-14T10:00:10", "35455", "9", "35.5")
        );
    }
}