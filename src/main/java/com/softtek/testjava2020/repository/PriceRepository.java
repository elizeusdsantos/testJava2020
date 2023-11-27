package com.softtek.testjava2020.repository;

import com.softtek.testjava2020.domain.price.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query(value = "SELECT * FROM Prices p WHERE :date BETWEEN p.start_date AND p.end_date AND p.product_id = :productId AND p.brand_id = :brandId LIMIT 1", nativeQuery = true)
    Optional<Price> findPriceByDateAndProductIdAndBrandId(LocalDateTime date, Long productId, Long brandId);
}
