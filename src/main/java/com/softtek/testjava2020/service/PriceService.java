package com.softtek.testjava2020.service;

import com.softtek.testjava2020.domain.exception.PriceNotFoundException;
import com.softtek.testjava2020.domain.price.Price;
import com.softtek.testjava2020.repository.PriceRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Data
public class PriceService {
    private final PriceRepository priceRepository;

    public Price findPricesByStartDateAndProductIdAndBrandId(LocalDateTime date, Long productId, Long brandId) {
        return priceRepository.findPriceByDateAndProductIdAndBrandId(date, productId, brandId)
                .orElseThrow(() -> new PriceNotFoundException("Price not found for the parameters date: " + date + ", productId: " + productId + ", brandId: " + brandId));
    }
}
