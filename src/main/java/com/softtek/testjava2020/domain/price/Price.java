package com.softtek.testjava2020.domain.price;

import com.softtek.testjava2020.domain.brand.Brand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Table(name = "prices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime endDate;
    @Column(unique = true)
    private Long priceList;
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private Integer priority;
    @Column(nullable = false)
    private Double itemPrice;
    @Column(nullable = false)
    private Currency curr;
}
