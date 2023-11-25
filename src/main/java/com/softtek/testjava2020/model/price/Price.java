package com.softtek.testjava2020.model.price;

import com.softtek.testjava2020.model.brand.Brand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Table(name = "prices")
@Data
@AllArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Column(unique = true)
    private Long priceList;
    @Column(unique = true)
    private Long productId;
    private Integer priority;
    private BigDecimal value;
    private Currency curr;

}
