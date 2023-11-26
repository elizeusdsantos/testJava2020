package com.softtek.testjava2020.controller;

import com.softtek.testjava2020.domain.price.Price;
import com.softtek.testjava2020.service.PriceService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RestController
@RequestMapping("/price")
public class PriceController {
    private final PriceService priceService;

    @GetMapping
    public ResponseEntity<List<Price>> getPricesByDateAndProductIdAndBrandId(@RequestParam("date") LocalDateTime date, @RequestParam("productId") Long productId, @RequestParam("brandId") Long brandId) {

        List<Price> prices = priceService.findPricesByStartDateAndProductIdAndBrandId(date, productId, brandId);
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }
}
