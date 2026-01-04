package com.company.pricing_service.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand_id", nullable = false)
    private Long brandId;
    @Column(name = "product_id", nullable = false)
    private Long productId;
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;
    @Column(name = "price_list", nullable = false)
    private Integer priceList;
    @Column(name = "priority", nullable = false)
    private Integer priority;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "currency", nullable = false)
    private String currency;
}
