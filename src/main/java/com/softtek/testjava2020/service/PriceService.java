package com.softtek.testjava2020.service;

import com.softtek.testjava2020.domain.price.Price;
import com.softtek.testjava2020.repository.PriceRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Data
public class PriceService {
    private final PriceRepository priceRepository;

    public List<Price> findPricesByStartDateAndProductIdAndBrandId(LocalDateTime date, Long productId, Long chainId) {
        return priceRepository.findPricesByDateAndProductIdAndBrandId(date, productId, chainId);
    }
}
