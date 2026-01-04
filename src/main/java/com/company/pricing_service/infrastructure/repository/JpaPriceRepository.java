package com.company.pricing_service.infrastructure.repository;

import com.company.pricing_service.infrastructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query(""" 
            SELECT p FROM PriceEntity p 
            WHERE p.productId = :productId 
            AND p.brandId = :brandId 
            AND :applicationDate BETWEEN p.startDate AND p.endDate 
    """)
    List<PriceEntity> findApplicable(Long productId, Long brandId, LocalDateTime applicationDate);
}
