package com.company.pricing_service.infrastructure.repository;

import com.company.pricing_service.domain.model.Price;
import com.company.pricing_service.domain.repository.PriceRepository;
import com.company.pricing_service.infrastructure.entity.PriceEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PriceRepositoryAdapter implements PriceRepository {

    private final JpaPriceRepository jpaPriceRepository;

    public PriceRepositoryAdapter(JpaPriceRepository jpaPriceRepository) {
        this.jpaPriceRepository = jpaPriceRepository;
    }

    @Override
    public List<Price> findApplicablePrices(Long productId, Long brandId, LocalDateTime applicationDate) {
        return jpaPriceRepository.findApplicable(productId, brandId, applicationDate)
                .stream().map(this::toDomain).toList();
    }

    private Price toDomain(PriceEntity entity) {
        return Price.builder()
                .brandId(entity.getBrandId())
                .productId(entity.getProductId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .priceList(entity.getPriceList())
                .priority(entity.getPriority())
                .price(entity.getPrice())
                .currency(entity.getCurrency())
                .build();
    }
}
