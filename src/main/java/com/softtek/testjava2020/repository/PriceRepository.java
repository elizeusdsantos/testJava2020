package com.softtek.testjava2020.repository;

import com.softtek.testjava2020.domain.price.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query("SELECT p FROM Price p WHERE :date BETWEEN p.startDate AND p.endDate AND p.productId = :productId AND p.brand.id = :brandId")
    List<Price> findPricesByDateAndProductIdAndBrandId(@Param("date") LocalDateTime date, @Param("productId") Long productId, @Param("brandId") Long brandId);
}
