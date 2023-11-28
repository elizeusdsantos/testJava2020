package com.softtek.testjava2020.controller;

import com.softtek.testjava2020.domain.price.Price;
import com.softtek.testjava2020.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Data
@RestController
@RequestMapping("/price")
public class PriceController {
    private final PriceService priceService;

    @Operation(summary = "Get list of prices by date, product id and brand id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Price.class))
            })})
    @GetMapping
    public ResponseEntity<Price> getPricesByDateAndProductIdAndBrandId(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date,
                                                                       @RequestParam("productId") Long productId,
                                                                       @RequestParam("brandId") Long brandId) {

        Price price = priceService.findPricesByStartDateAndProductIdAndBrandId(date, productId, brandId);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }
}
