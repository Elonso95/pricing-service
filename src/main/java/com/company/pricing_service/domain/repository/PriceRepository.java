package com.company.pricing_service.domain.repository;

import com.company.pricing_service.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {

    List<Price> findApplicablePrices(Long productId, Long brandId, LocalDateTime applicationDate);
}
